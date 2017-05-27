package com.gondole.dev.java.datastructure;


import static com.gondole.dev.java.datastructure.SortTest.print;
import static com.gondole.dev.java.datastructure.SortTest.println;
import static com.gondole.dev.java.datastructure.SortTest.printlnArray;

/**
 * Created by dell on 2017/5/19.
 */

public class QuickSort {
	public static void sort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 * 递归划分子序列
	 *
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static void quickSort(int[] arr, int left, int right) {

		System.out.println("递归划分子序列：" + print(arr, left, right));

		if (left >= right) {
			println("长度只有1，无需排序");
			println("");
			return;
		}
		
		int pivotPos = partition(arr, left, right);
		
		quickSort(arr, left, pivotPos - 1);
		quickSort(arr, pivotPos + 1, right);
	}
	
	/**
	 * 稍优的划分逻辑
	 * 一次划分流程
	 * 
	 * 按照把左边第一个数字作为基准数的话，逻辑如下：
	 * 1. 右指针从右往左找到比基准数小的数字，找到时指针停止移动，且给左指针数字赋值为右指针对应的数字
	 * 2. 左指针从左往右找到比基准数大的数字，找到时指针停止移动，且给右指针数字赋值为左指针对应的数字
	 * 3. 交换相遇时的数字和基准数
	 * 4. 此时基准数左边都是比基准数小的数字，基准数右边都是比基准数大的数字
	 * 5. 此时一轮划分执行完毕
	 *
	 * @param arr
	 * @param left 左指针
	 * @param right 右指针
	 * @return
	 */
	public static int partition(int[] arr, int left, int right) {
		int pivotKey = arr[left];
	
		println("基准数为：" + pivotKey);
		
		while (left < right) {
			while (left < right && arr[right] >= pivotKey) {
				right--;
			}
			
			if (left < right) {
				arr[left] = arr[right]; //把小的数字移动到左边
				System.out.println(print(arr));
			}
			
			while (left < right && arr[left] <= pivotKey) {
				left++;
			}
			
			if (left < right) {
				arr[right] = arr[left]; //把大的移动到右边
				System.out.println(print(arr));
			}
		}

		println("把基准数赋值到arr[" + left + "]");
		
		//当left = right时两指针相遇
		arr[left] = pivotKey; //最后把pivot赋值到中间
		
		printlnArray(arr);
		
		return left;
	}

	/**
	 * 
	 * 代码中基准数已经在 pivotKey 中保存了，所以不需要每次交换都设置一个 temp 变量，
	 * 在交换左右指针的时候只需要先后覆盖就可以了。
	 * 这样既能减少空间的使用还能降低赋值运算的次数
	 * 
	 * 一次划分流程
	 *
	 * 按照把左边第一个数字作为基准数的话，逻辑如下：
	 * 1. 右指针从右往左找到比基准数小的数字，找到时指针停止移动
	 * 2. 左指针从左往右找到比基准数大的数字，找到时指针停止移动
	 * 3. 交换这两个数字
	 * 4. 重复步骤1和步骤2，如果两者相遇时，则停止移动
	 * 5. 交换相遇时的数字和基准数
	 * 6. 此时基准数左边都是比基准数小的数字，基准数右边都是比基准数大的数字
	 * 7. 此时一轮划分执行完毕
	 *
	 * @param arr
	 * @param left 左指针
	 * @param right 右指针
	 * @return
	 */
	public static int partitionOld(int[] arr, int left, int right) {
		int pivotKey = arr[left];
		int initLeft = left;
		
		while (left < right) {
			while (left < right && arr[right] >= pivotKey) {
				right--;
			}

			while (left < right && arr[left] <= pivotKey) {
				left++;
			}
			
			swap(arr, left, right);
			System.out.println(print(arr));
		}

		swap(arr, initLeft, left);
		System.out.println(print(arr));
		return left;
	}

	public static void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
}
