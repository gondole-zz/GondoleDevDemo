package com.gondole.dev.java.datastructure;

/**
 * http://www.codeceo.com/article/10-sort-algorithm-interview.html#0-tsina-1-10490-397232819ff9a47a7b7e80a40613cfe1
 */
public class SortTest {
	public static void main(String[] args) {
//		int[] arr = {2, 5, 3, 8};
//		int[] arr = {6, 5, 3, 1, 8, 7, 2, 4, 2};
		int[] arr = {49,38,65,97,76,13,27,49};
		
		
		System.out.println("初始数组为：" + SortTest.print(arr));
		
//		BubbleSort.bubbleSort(arr);	//冒泡排序
//		SelectSort.selectSort(arr);	//选择排序
//		InsertSort.insertSort(arr);	//插入排序
//		QuickSort.sort(arr);		//快速排序
//		HeapSort.heapSort(arr);		//堆排序
//		ShellSort.shellSort(arr);	//希尔排序
//		MergeSort.mergeSort(arr);	//归并排序
//		CountSort.countSort(arr);	//计数排序
//		BucketSort.bucketSort(arr);	//桶排序
		RadixSort.radixSort(arr);	//基数排序

		System.out.println("");
		System.out.println("最终排序结果：" + print(arr));
	}
	
	public static String print(int[] arr) {
		StringBuilder sb = new StringBuilder();

		for (int i : arr) {
			sb.append(i + " ");
		}
		
		return sb.toString();
	}

	public static void printlnArray(int[] arr) {
		StringBuilder sb = new StringBuilder();

		for (int i : arr) {
			sb.append(i + " ");
		}

		System.out.println("");
		System.out.println(sb.toString());
	}
}
