package com.gondole.dev.java.datastructure;

/**
 * Created by dell on 2017/5/19.
 */

public class HeapSort {

	public static void heapSort(int[] arr) {
		if (arr == null || arr.length == 0)
			return;

		//建立大顶堆
		for (int i = arr.length / 2; i >= 0; i--) {
			//重构堆
			heapAdjust(arr, i, arr.length - 1);
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			//首尾元素交换，并且重构除去尾巴元素的剩余堆
			swap(arr, 0, i);
			//重构堆
			heapAdjust(arr, 0, i - 1);
		}
	}

	/**
	 * 堆筛选，除了start之外，start~end均满足大顶堆的定义。
	 * 调整之后start~end称为一个大顶堆。
	 *
	 * @param arr   待调整数组
	 * @param startIndex 起始指针
	 * @param endIndex   结束指针
	 */
	public static void heapAdjust(int[] arr, int startIndex, int endIndex) {
		int currentParentValue = arr[startIndex];

		//左右孩子的节点分别为2*i+1,2*i+2
		
		//检测当前节点是否有左孩子
		for (int leftChildIndex = getLeftChildIndexFromParent(startIndex); 
			 leftChildIndex <= endIndex; leftChildIndex *= 2) {
			
			int maxChildIndex = leftChildIndex;
			//如果当前左孩子不是末尾元素，是如果左孩子不是末尾元素，那么肯定也有右孩子
			if (leftChildIndex < endIndex) {
				//如果左孩子小于右孩子
				if (arr[leftChildIndex] < arr[leftChildIndex + 1]) {
					//获取右孩子下标
					maxChildIndex = leftChildIndex + 1;
				}
			}
			
			//比较当前父节点和最大孩子节点
			if (currentParentValue >= arr[maxChildIndex]) {
				break; //已经为大顶堆，=保持稳定性。
			}
			
			//将最大子孩子移动到其父节点位置
			arr[startIndex] = arr[maxChildIndex]; //将子节点上移
			
			startIndex = maxChildIndex; //下一轮筛选
		}

		arr[startIndex] = currentParentValue; //插入正确的位置
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int getLeftChildIndexFromParent(int parentIndex) {
		return parentIndex * 2 + 1;
	}
}
