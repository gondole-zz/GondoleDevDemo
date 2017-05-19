package com.gondole.dev.java.datastructure;

import java.util.Arrays;

/**
 * Created by dell on 2017/5/19.
 */

public class CountSort {
	public static void countSort(int[] arr) {
		if(arr == null || arr.length == 0)
			return ;

		int max = max(arr);

		int[] count = new int[max+1];
		Arrays.fill(count, 0);

		for(int i=0; i<arr.length; i++) {
			count[arr[i]] ++;
		}

		int k = 0;
		for(int i=0; i<=max; i++) {
			for(int j=0; j<count[i]; j++) {
				arr[k++] = i;
			}
		}

	}

	public static int max(int[] arr) {
		int max = Integer.MIN_VALUE;
		for(int ele : arr) {
			if(ele > max)
				max = ele;
		}

		return max;
	}
}
