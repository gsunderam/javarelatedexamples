package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.*;

/**
 * Time complexity: Let n = no of strings in reviews and l = length of each string.
 * O(ln + m) where m = no of keywords
 * Space: O(m)
 * assuming that PQ is implemented as a binary max heap.
 */
public class TopKWords {
    public List<String> getTopK(int k, String [] keywords, String [] reviews) {
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((e1, e2) -> {
            if (e1.getValue() == e2.getValue()) {
                return e1.getKey().compareTo(e2.getKey());
            }

            return e2.getValue() - e1.getValue();
        });

        Map<String, Integer> counts = new HashMap<>();

        Arrays.stream(reviews).forEach(review -> countWords(review, keywords, counts)); //O(ln)
        counts.entrySet().forEach(queue::offer); //O(m)

        List<String> result = new ArrayList<>();
        while (k-- > 0) { //O(k logm)
            result.add(queue.poll().getKey());
        }

        return result;
    }

    public Map<String, Integer> countWords(String review, String [] keywords, Map<String, Integer> counts) {
        String[] words = review.split("\\W");
        List<String> wordlist = Arrays.asList(keywords);

        Arrays.stream(words).forEach(word -> {
            word = word.toLowerCase();
            if (wordlist.contains(word)) {
                counts.put(word, counts.getOrDefault(word, 0) + 1);
            }
        });

        return counts;
    }

    public static void main(String[] args) {
        int k = 4;
        String [] keywords = {"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"};
        String [] reviews = {
                "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services",
                "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell",
                "Betacellular is better than deltacellular."
        };

        Logger.stdout(new TopKWords().getTopK(k, keywords, reviews));
    }
}
