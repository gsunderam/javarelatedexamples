package com.sg.dp.amazon;

/**
 * @author CZ
 * @date 6/13/2021 11:32 AM
 */
public class SortColours {
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        if (nums.length == 1) return;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 2) {
                while (boundsCheck(right, nums.length) && nums[right] == 2) right--;

                if (i < right) swap(nums, i, right);
            }

            if (nums[i] == 0) {
                while (boundsCheck(left, nums.length) && nums[left] == 0) left++;

                if (left < i) swap(nums, i, left);
            }
        }
    }

    public boolean boundsCheck(int value, int len) {
        return value >= 0 && value < len;
    }

    public void swap(int [] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
