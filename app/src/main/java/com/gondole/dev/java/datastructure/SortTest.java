package com.gondole.dev.java.datastructure;

/**
 * http://www.codeceo.com/article/10-sort-algorithm-interview.html#0-tsina-1-10490-397232819ff9a47a7b7e80a40613cfe1
 */
public class SortTest {
	public static void main(String[] args) {
		int[] arr = {2, 5, 3, 8, 1};

		BubbleSort.bubbleSort(arr);
		BucketSort.bucketSort(arr);
		CountSort.countSort(arr);
		HeapSort.heapSort(arr);
		InsertSort.insertSort(arr);
		MergeSort.mergeSort(arr);
		QuickSort.sort(arr);
		RadixSort.radixSort(arr);
		SelectSort.selectSort(arr);
		ShellSort.shellSort(arr);
		
		
		System.out.println(print(arr));
	}
	
	public static String print(int[] arr) {
		StringBuilder sb = new StringBuilder();

		for (int i : arr) {
			sb.append(i + " ");
		}
		
		return sb.toString();
	}
}
