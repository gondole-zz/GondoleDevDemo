package com.gondole.dev.java.datastructure;

import static com.gondole.dev.java.datastructure.SortTest.printlnArray;

/**
 * Created by dell on 2017/5/19.
 */

public class InsertSort {
	public static void insertSort(int[] arr) {
		if (arr == null || arr.length == 0)
			return;

		//假设第一个数位置时正确的；要往后移，必须要假设第一个。
		for (int itemIndex = 1; itemIndex < arr.length; itemIndex++) { 

			int j = itemIndex;
			int target = arr[itemIndex]; //待插入的目标数字

			while (j > 0 && target < arr[j - 1]) {
				arr[j] = arr[j - 1];
				j--;
				printlnArray(arr);
			}

			//插入 
			arr[j] = target;
			printlnArray(arr);
		}
	}
}
