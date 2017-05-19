package com.gondole.dev.java.datastructure;

/**
 * 冒泡排序是最简单的排序之一了，其大体思想就是通过与相邻元素的比较和交换来把小的数交换到最前面。这个过程类似于水泡向上升一样，因此而得名。举个栗子，对 5,3,8,6,4 这个无序序列进行冒泡排序。首先从后向前冒泡，4 和 6 比较，把 4 交换到前面，序列变成 5,3,8,4,6。同理 4 和 8 交换，变成 5,3,4,8,6,3 和 4 无需交换。5 和 3 交换，变成 3,5,4,8,6,3. 这样一次冒泡就完了，把最小的数 3 排到最前面了。对剩下的序列依次冒泡就会得到一个有序序列。冒泡排序的时间复杂度为 O(n^2)。
 */
public class BubbleSort {
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length == 0)
			return;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					swap(arr, j - 1, j);
				}
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
