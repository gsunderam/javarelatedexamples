package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Time complexity: O(2n) where n = no of input lists
 * Space: O(2n)
 */
public class ItemAssociations {
    public List<String> findLargeAssociation(List<PairString> input) {
        List<List<String>> inputList = input.stream().map(ps -> {
            List<String> list = new ArrayList<>();
            list.add(ps.first);//Assume first is always < second lexically
            list.add(ps.second);
            return list;
        }).collect(Collectors.toList());

        return getAssocs(findLAssoc(inputList, 0));
    }

    /**
     * @param inputList input as strings list
     * @param index List to process next
     * @return List of list strings containing the associations
     */
    public List<List<String>> findLAssoc(List<List<String>> inputList, int index) {
        ArrayList<List<String>> lists = new ArrayList<>();
        lists.add(inputList.get(0));
        return combine(inputList, lists, 1);
    }

    /**
     *  Recursively inspect all lists. Beginning with [List1] and List2, the algorithm gets new lists. Each of the new
     *  lists is them combined with the next list referred to by imdex.
     * @param inputList orginal input list
     * @param listAssocs These get refreshed after every call. Will have the results when recursion ends.
     * @param index [0 to n - 1] of the input list
     * @return
     */
    public List<List<String>> combine(List<List<String>> inputList, List<List<String>> listAssocs, int index) {
        if (index >= inputList.size()) return listAssocs;

        List<String> next = inputList.get(index);
        List<List<String>> resultList = process(listAssocs, next);

        return combine(inputList, resultList, index + 1);
    }

    /**
     * Examines if the lists in the listAssocs, thus far, intersect with the "next" list. If so, they are
     * combined into one. Else proceed to next list. This is done using Sets
     * @param listAssocs
     * @param next
     * @return
     */
    private List<List<String>> process(List<List<String>> listAssocs, List<String> next) {
        boolean hasNoCommon = true;
        List<List<String>> resultList = new ArrayList<>();

        for (List list: listAssocs) { //use treeset so the elements get sorted
            Set<String> set = new TreeSet<>(list); //optimize this. these 2 lines are needed only if the flag is true
            set.addAll(next);

            if (set.size() < list.size() + next.size()) {
                resultList.add(new ArrayList<>(set)); //Coz List needs to be returned per reqt.
                hasNoCommon = false;
            } else {
                resultList.add(list);
            }
        }

        if (hasNoCommon) resultList.add(next);

        return resultList;
    }

    /**
     * Using Priority Queue to get the result element. PQ's are sorted based on comparator provided, per the
     * requirement by Amazon. Sort by list size first, then lexically if needed
     * @param allAssocs All the result sets
     * @return the answer to the problem: List of large assocs
     */
    public List<String> getAssocs(List<List<String>> allAssocs) {
        Queue<List<String>> queue = new PriorityQueue<>((list1, list2) -> {
                            int diff = list2.size() - list1.size();
                            if (diff == 0) return list2.get(0).compareTo(list1.get(0));
                            return diff;
                        });

        queue.addAll(allAssocs);
        return queue.poll();
    }

    public static void main(String[] args) {
        ItemAssociations items = new ItemAssociations();
        PairString p1 = new PairString("item1", "item2");
        PairString p2 = new PairString("item3", "item4");
        PairString p3 = new PairString("item4", "item5");
        PairString p4 = new PairString("item1", "item7");
        PairString p5 = new PairString("item2", "item8");
        List<PairString> input = new ArrayList<>();
        input.add(p1);
        input.add(p2);
        input.add(p3);
        input.add(p4);
        input.add(p5);

        List<String> largeAssociation = items.findLargeAssociation(input);
        Logger.stdout("Answer is " + largeAssociation);
    }

    public void print(List<List<String>> lists, int index) {
        Logger.stdout("index is " + index);
        int i = 0;

        for (List<String> list: lists)
            Logger.stdout("List at " +  (i++) + " is " + list);
    }

    static class PairString {
        String first;
        String second;

        public PairString(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }
}
