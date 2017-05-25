package com.gondole.dev.java.datastructure;

/**
 * Created by dell on 2017/5/19.
 */

public class ShellSort {
	public static void shellSort(int[] arr) {
		if (arr == null || arr.length == 0)
			return;
		int gapSize = arr.length / 2;
		while (gapSize >= 1) {
			shellInsert(arr, gapSize);
			SortTest.printlnArray(arr);
			gapSize /= 2;
		}
	}
	
	/**
	 * 希尔排序的一趟插入
	 *
	 * @param arr 待排数组
	 * @param gapSize   增量
	 */
	public static void shellInsert(int[] arr, int gapSize) {
		System.out.println("");
		System.out.println("步长大小为：" + gapSize);
		
		for (int rightNumIndex = gapSize; rightNumIndex < arr.length; rightNumIndex++) {
			int leftNumIndex = rightNumIndex - gapSize;
			
			int rightNum = arr[rightNumIndex];    //记录要插入的数据  

			System.out.println("比较 ：" + " 索引 " + leftNumIndex + " : " + arr[leftNumIndex] + " vs " + "索引 " + rightNumIndex + " : " + arr[rightNumIndex]);
			
			while (leftNumIndex >= 0 && arr[leftNumIndex] > rightNum) {  //从后向前，找到比其小的数的位置   
				arr[leftNumIndex + gapSize] = arr[leftNumIndex];    //向后挪动  
				leftNumIndex = leftNumIndex - gapSize;
			}
			
			if (leftNumIndex != rightNumIndex - gapSize) {    //存在比其小的数 
				arr[leftNumIndex + gapSize] = rightNum;
			}

//			sortAndSwap(arr, leftNumIndex, rightNumIndex);
		}
	}

	public static void sortAndSwap(int[] arr, int left, int right) {
		int rightNum = arr[right];
		if (arr[left] > rightNum) {
			arr[right] = arr[left];
			arr[left] = rightNum;
		}
	}
}
