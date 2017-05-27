package com.gondole.dev.other;

import java.util.Random;

/**
 * Created by dell on 2017/5/27.
 */

public class Sort {

	public static void doQsort() {

		int[] arr = getRandomArr();   //随机数组
//				int[] arr = getAscArr(); //升序数组
//				int[] arr = getDescArr(); //降序数组
		//		int[] arr = getRepeatArr(); //重复数组

		long start = System.currentTimeMillis();
		
//		QsortCommon(arr, 0, arr.length - 1);          //固定基准元
		QsortRandom(arr, 0, arr.length - 1);          //随机基准元
//		QsortMedianOfThree(arr, 0, arr.length - 1);   //三数取中
//		QsortThreeInsert(arr, 0, arr.length - 1);     //三数取中+插排
//		QsortThreeInsertGather(arr, 0, arr.length - 1); //三数取中+插排+聚集相同元素

		long end = System.currentTimeMillis();
		System.out.println("执行时间 : " + (end - start) + "ms");
	}

	private static int[] getRepeatArr() {
		int[] arr = new int[100000];
		//重复数组
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 5768461;
		}
		return arr;
	}

	private static int[] getDescArr() {
		int[] arr = new int[100000];
		//降序数组
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr.length - 1 - i;
		}
		return arr;
	}

	private static int[] getAscArr() {
		int[] arr = new int[100000];
		//升序数组
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		return arr;
	}

	private static int[] getRandomArr() {
		int[] arr = new int[100000];                        //10W个空间大小的数组
		Random rd = new Random();
		 /*随机数组*/
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rd.nextInt();
		}
		return arr;
	}

	/// 1.0 固定基准元（基本的快速排序）
	public static void QsortCommon(int[] arr, int low, int high) {
		if (low >= high)
			return;                        //递归出口
		int partition = Partition(arr, low, high);      //将 >= x 的元素交换到右边区域，将 <= x 的元素交换到左边区域
		QsortCommon(arr, low, partition - 1);
		QsortCommon(arr, partition + 1, high);
	}

	/// 2.0 随机基准元
	public static void QsortRandom(int[] arr, int low, int high) {
		if (low >= high)
			return;                        //递归出口
		PartitionRandom(arr, low, high);                //随机基准元
		int partition = Partition(arr, low, high);      //将 >= x 的元素交换到右边区域，将 <= x 的元素交换到左边区域
		QsortRandom(arr, low, partition - 1);
		QsortRandom(arr, partition + 1, high);
	}

	/// 3.0 三数取中
	public static void QsortMedianOfThree(int[] arr, int low, int high) {
		if (low >= high)
			return;                        //递归出口
		PartitionMedianOfThree(arr, low, high);         //三数取中
		int partition = Partition(arr, low, high);      //将 >= x 的元素交换到右边区域，将 <= x 的元素交换到左边区域
		QsortMedianOfThree(arr, low, partition - 1);
		QsortMedianOfThree(arr, partition + 1, high);
	}

	/// 4.0 三数取中+插排
	public static void QsortThreeInsert(int[] arr, int low, int high) {
		if (high - low + 1 < 10) {
			InsertSort(arr, low, high);
			return;
		}                                               //插排，递归出口
		PartitionMedianOfThree(arr, low, high);         //三数取中
		int partition = Partition(arr, low, high);      //将 >= x 的元素交换到右边区域，将 <= x 的元素交换到左边区域
		QsortMedianOfThree(arr, low, partition - 1);
		QsortMedianOfThree(arr, partition + 1, high);
	}

	/// 5.0 三数取中+插排+聚集相同元素
	public static void QsortThreeInsertGather(int[] arr, int low, int high) {
		if (high - low + 1 < 10) {
			InsertSort(arr, low, high);
			return;
		}                                               //插排，递归出口
		PartitionMedianOfThree(arr, low, high);         //三数取中

		//进行左右分组（处理相等元素）
		int first = low;
		int last = high;
		int left = low;
		int right = high;
		int leftLength = 0;
		int rightLength = 0;
		int key = arr[first];
		while (first < last) {
			while (first < last && arr[last] >= key) {
				if (arr[last] == key)                   //处理相等元素
				{
					swap(arr, last, right);
					right--;
					rightLength++;
				}
				last--;
			}
			arr[first] = arr[last];
			while (first < last && arr[first] <= key) {
				if (arr[first] == key) {
					swap(arr, first, left);
					left++;
					leftLength++;
				}
				first++;
			}
			arr[last] = arr[first];
		}
		arr[first] = key;

		//一次快排结束
		//把与基准元key相同的元素移到最终位置周围
		int i = first - 1;
		int j = low;
		while (j < left && arr[i] != key) {
			swap(arr, i, j);
			i--;
			j++;
		}
		i = last + 1;
		j = high;
		while (j > right && arr[i] != key) {
			swap(arr, i, j);
			i++;
			j--;
		}
		QsortThreeInsertGather(arr, low, first - leftLength - 1);
		QsortThreeInsertGather(arr, first + rightLength + 1, high);
	}

	/// 固定基准元，默认数组第一个数为基准元，左右分组，返回基准元的下标
	public static int Partition(int[] arr, int low, int high) {
		int first = low;
		int last = high;
		int key = arr[low];                             //取第一个元素作为基准元
		while (first < last) {
			while (first < last && arr[last] >= key)
				last--;
			arr[first] = arr[last];
			while (first < last && arr[first] <= key)
				first++;
			arr[last] = arr[first];
		}
		arr[first] = key;                               //基准元居中
		return first;
	}

	/// 随机基准元，将确定好的基准元与第一个数交换，无返回值
	public static void PartitionRandom(int[] arr, int low, int high) {
		Random rd = new Random();
		int randomIndex = rd.nextInt() % (high - low) + low;//取数组中随机下标
		swap(arr, randomIndex, low);                     //与第一个数交换
	}

	/// 三数取中确定基准元，将确定好的基准元与第一个数交换，无返回值
	public static void PartitionMedianOfThree(int[] arr, int low, int high) {
		int mid = low + (high + -low) / 2;
		if (arr[mid] > arr[high]) {
			swap(arr, mid, high);
		}
		if (arr[low] > arr[high]) {
			swap(arr, low, high);
		}
		if (arr[mid] > arr[low]) {
			swap(arr, mid, low);
		}                                                //将中间大小的数与第一个数交换
	}

	/// 插入排序
	public static void InsertSort(int[] arr, int low, int high) {
		for (int i = low + 1; i <= high; i++) {
			if (arr[i] < arr[i - 1]) {
				for (int j = low; j < i; j++) {
					if (arr[j] > arr[i]) {
						swap(arr, i, j);
					}
				}
			}
		}
	}

	public static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
}
