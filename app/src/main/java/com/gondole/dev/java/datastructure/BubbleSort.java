package com.gondole.dev.java.datastructure;

/**
 * 冒泡排序是最简单的排序之一了，其大体思想就是通过与相邻元素的比较和交换来把小的数交换到最前面。
 * 这个过程类似于水泡向上升一样，因此而得名。
 * 
 * 举个栗子，对 5,3,8,6,4 这个无序序列进行冒泡排序。
 * 首先从后向前冒泡，4 和 6 比较，把 4 交换到前面，序列变成 5,3,8,4,6。
 * 同理 4 和 8 交换，变成 5,3,4,8,6,
 * 3 和 4 无需交换。
 * 5 和 3 交换，变成 3,5,4,8,6,3. 
 * 这样一次冒泡就完了，把最小的数 3 排到最前面了。
 * 
 * 对剩下的序列依次冒泡就会得到一个有序序列。冒泡排序的时间复杂度为 O(n^2)。
 * @author wb
 */
public class BubbleSort {
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}

		//倒序冒泡
//		for (int i = 0; i < arr.length - 1; i++) {
//			System.out.println("");
//			System.out.println("第" + (i + 1 )+ "轮");
//			for (int j = arr.length - 1; j > i; j--) {
//				System.out.println("比较两个数大小 " + arr[j] + " 和 " + arr[j - 1]);
//				if (arr[j] < arr[j - 1]) {
//					swap(arr, j - 1, j);
//					System.out.println("交换位置, 当前数组：" + SortTest.print(arr));
//				} else {
//					System.out.println("位置不变, 当前数组："  + SortTest.print(arr));
//				}
//			}
//		}

		//正序冒泡
		for (int i = 0; i < arr.length - 1; i++) {
			System.out.println("");
			System.out.println("第" + (i + 1 )+ "轮");
			for (int j = 0; j < arr.length - i - 1; j ++) {
				System.out.println("比较两个数大小 " + arr[j] + " 和 " + arr[j + 1]);
				if (arr[j] > arr[j + 1]) {
					swap(arr, j + 1, j);
					System.out.println("交换位置, 当前数组：" + SortTest.print(arr));
				} else {
					System.out.println("位置不变, 当前数组："  + SortTest.print(arr));
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
