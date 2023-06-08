package com.sg.dp.amazon;

import static com.sg.dp.log.Logger.stdout;

/**
 * Search min in a rotated aray: (GS interview prep)
 * Time complexity. (log N)
 * Sunderam G
 * June 5 2023
 */
public class RotatedArraySearch {
    public static void main(String[] args) {
        int [] nums = {5, 6, 7, 8, 11, 14, 2, 3, 4};
        int min = searchArray(nums, 0, nums.length - 1);
        stdout("Answer is " + min);
    }

    private static int searchArray(int[] nums, int start, int end) {
        if (start <= end) {
            int mid = (start + end) / 2;
            int found = -1;

            if (leftCheck(nums, mid) && rightCheck(nums, mid)) {
                stdout("Min is " + nums[mid]);
                return nums[mid];
            }

            if (found == -1 || nums[mid] >= nums[start]) {
                found = searchArray(nums, mid + 1, end);
            }

            if (found == -1 || nums[mid] < nums[start]) {
                found = searchArray(nums, start, mid - 1);
            }

            return found;
        }

        return -1;
    }

    private static boolean leftCheck(int[] nums, int mid) {
        return mid - 1 < 0 || nums[mid] < nums[mid - 1];
    }

    private static boolean rightCheck(int[] nums, int mid) {
        return mid + 1 >= nums.length || nums[mid] < nums[mid + 1];
    }
}
