package com.gondole.dev.java.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dell on 2017/5/19.
 */

public class BucketSort {
	public static void bucketSort(int[] arr) {
		if (arr == null && arr.length == 0)
			return;

//		int bucketNums = 10; //这里默认为10，规定待排数[0,100)
		
		List<List<Integer>> buckets = new ArrayList<>(); //桶的索引

		for (int i = 0; i < 10; i++) {
			buckets.add(new LinkedList<Integer>()); //用链表比较合适
		}

		//划分桶
		for (int i = 0; i < arr.length; i++) {
			buckets.get(f(arr[i])).add(arr[i]);
		}

		//对每个桶进行排序
		for (int i = 0; i < buckets.size(); i++) {
			if (!buckets.get(i).isEmpty()) {
				Collections.sort(buckets.get(i)); //对每个桶进行快排
			}
		}

		//还原排好序的数组
		int k = 0;
		for (List<Integer> bucket : buckets) {
			for (int ele : bucket) {
				arr[k++] = ele;
			}
		}
	}

	/**
	 * 映射函数
	 *
	 * @param x
	 * @return
	 */
	public static int f(int x) {
		return x / 10;
	}
}
