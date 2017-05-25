package com.gondole.dev.java.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 举例：待排序数组为：17，12，15，25，27

 判断所有数字的最大长度，该例子中长度为 2
 分配数字到临时数组内，依次数字的右边向左边为基准进行分配：2 --> 12；5 -- > 15, 25；7 --> 17, 27
 按照倒数第二位分配：1-->12,15,17；2-->25,27
 4. 直至第一位，然后遍历临时数组把数字重新收集到原数组内；
 12, 15, 17, 25, 27。结束；
 */
public class RadixSort {
	public static void radixSort(int[] arr) {
		if (arr == null && arr.length == 0)
			return;

		int maxBit = getMaxBit(arr);

		for (int i = 1; i <= maxBit; i++) {
			List<List<Integer>> buf = distribute(arr, i); //分配
			collecte(arr, buf); //收集
		}
	}

	/**
	 * 分配
	 *
	 * @param arr  待分配数组
	 * @param iBit 要分配第几位
	 * @return
	 */
	public static List<List<Integer>> distribute(int[] arr, int iBit) {
		List<List<Integer>> buf = new ArrayList<>();
		for (int j = 0; j < 10; j++) {
			buf.add(new LinkedList<Integer>());
		}
		for (int i = 0; i < arr.length; i++) {
			buf.get(getNBit(arr[i], iBit)).add(arr[i]);
		}
		return buf;
	}

	/**
	 * 收集
	 *
	 * @param arr 把分配的数据收集到arr中
	 * @param buf
	 */
	public static void collecte(int[] arr, List<List<Integer>> buf) {
		int k = 0;
		for (List<Integer> bucket : buf) {
			for (int ele : bucket) {
				arr[k++] = ele;
			}
		}
	}

	/**
	 * 获取最大位数
	 *
	 * @return
	 */
	public static int getMaxBit(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int ele : arr) {
			int len = (ele + "").length();
			if (len > max) {
				max = len;
			}
		}
		return max;
	}

	/**
	 * 获取x的第n位，如果没有则为0.
	 *
	 * @param x
	 * @param n
	 * @return
	 */
	public static int getNBit(int x, int n) {

		String sx = x + "";
		if (sx.length() < n) {
			return 0;
		} else {
			return sx.charAt(sx.length() - n) - '0';
		}
	}
}
