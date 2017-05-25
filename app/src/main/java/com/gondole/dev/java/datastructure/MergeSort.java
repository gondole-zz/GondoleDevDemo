package com.gondole.dev.java.datastructure;

/**
 * Created by dell on 2017/5/19.
 */

public class MergeSort {
	public static void mergeSort(int[] arr) {
		mSort(arr, 0, arr.length - 1);
	}

	/**
	 * 递归分治
	 *
	 * @param arr   待排数组
	 * @param left  左指针
	 * @param right 右指针
	 */
	public static void mSort(int[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		
		int mid = (left + right) / 2;

		mSort(arr, left, mid); //递归排序左边
		mSort(arr, mid + 1, right); //递归排序右边
		
		merge(arr, left, mid, right); //合并
	}

	/**
	 * 合并两个有序数组
	 *
	 * @param arr   待合并数组
	 * @param left  左指针
	 * @param mid   中间指针
	 * @param right 右指针
	 */
	public static void merge(int[] arr, int left, int mid, int right) {
		//[left, mid] [mid+1, right]
		int[] tempArray = new int[right - left + 1]; //中间数组

		
		int leftIndexFromLeft = left;	//左边序列左指针
		int leftIndexFromRight = mid + 1;	//右边序列左指针
		
		int tempArrIndex = 0;
		//左序列左指针在左序列范围之内，右序列左指针在右序列范围之内
		while (leftIndexFromLeft <= mid && leftIndexFromRight <= right) {	

			//比较左序列左指针对应数字和右序列左指针对应数字，选择较小的数字移动到临时数组内
//			if (arr[leftIndexFromLeft] <= arr[leftIndexFromRight]) {	
//				tempArray[tempArrIndex++] = arr[leftIndexFromLeft++];
//			} else {
//				tempArray[tempArrIndex++] = arr[leftIndexFromRight++];
//			}

			tempArray[tempArrIndex++] = arr[leftIndexFromLeft] <= arr[leftIndexFromRight] ? 
					arr[leftIndexFromLeft++] : arr[leftIndexFromRight++];
		}

		while (leftIndexFromLeft <= mid) {
			//合并左序列左指针右侧剩余的数字到临时数组
			tempArray[tempArrIndex++] = arr[leftIndexFromLeft++];	
		}

		while (leftIndexFromRight <= right) {
			//合并右序列右指针右侧剩余的数字到临时数组
			tempArray[tempArrIndex++] = arr[leftIndexFromRight++];	
		}

		for (int p = 0; p < tempArray.length; p++) {
			arr[left + p] = tempArray[p];	//复制临时数组内的元素到原数组内
		}
	}
}
