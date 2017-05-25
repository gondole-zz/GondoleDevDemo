package com.gondole.dev.java.datastructure;

import java.util.Arrays;

/**
 * Created by dell on 2017/5/19.
 */

public class CountSort {
	public static void countSort(int[] arr) {
		if (arr == null || arr.length == 0)
			return;

		int max = max(arr); //寻找数组中最大值

		int[] count = new int[max + 1]; //建立临时数组, 长度为max+1
		Arrays.fill(count, 0);	//使用0填充数组中的元素

		//使用待排序数组的元素作为临时数组的下标，并且统计每个元素的个数
		for (int i = 0; i < arr.length; i++) {
			count[arr[i]] = count[arr[i]] + 1; 
		}

		
		int arrIndex = 0;
		for (int i = 0; i <= max; i++) {	//从0到max挨个遍历
			//遍历临时数组某下标对应的数组元素值，数组元素值代表下标值出现了几次，依次添加到目标数组
			for (int j = 0; j < count[i]; j++) {	
				arr[arrIndex++] = i; 
			}
		}
	}

	public static int max(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int ele : arr) {
			if (ele > max)
				max = ele;
		}

		return max;
	}
}
