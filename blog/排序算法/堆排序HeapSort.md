研究了半天，一步一步试验DEBU，才明白堆排序的原理，整理记录一下；
相关参考：
[排序算法之堆排序（Heapsort）解析](http://www.ayqy.net/blog/%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95%E4%B9%8B%E5%A0%86%E6%8E%92%E5%BA%8F%EF%BC%88heapsort%EF%BC%89%E8%A7%A3%E6%9E%90/)
[堆排序及其分析](http://www.cnblogs.com/zabery/archive/2011/07/26/2117103.html)

---

**堆排序**（Heapsort）是指利用[堆]这种数据结构所设计的一种[排序算法]。堆积是一个近似[完全二叉树]的结构，并同时满足*堆积的性质*：即子结点的键值或索引总是小于（或者大于）它的父节点。

## 堆节点的访问
通常堆是通过一维[数组](https://zh.wikipedia.org/wiki/%E6%95%B0%E7%BB%84)来实现的。在数组起始位置为 0 的情形中：
- 父节点 i 的左子节点在位置 (2*i+1);
- 父节点 i 的右子节点在位置 (2*i+2);
- 子节点 i 的父节点在位置 floor((i-1)/2);

## 堆的操作
在堆的数据结构中，堆中的最大值总是位于根节点 (在优先队列中使用堆的话堆中的最小值位于根节点)。堆中定义以下几种操作：
- 最大堆调整（Max_Heapify）：将堆的末端子节点作调整，使得子节点永远小于父节点
- 创建最大堆（Build_Max_Heap）：将堆所有数据重新排序
- 堆排序（HeapSort）：移除位在第一个数据的根节点，并做最大堆调整的[递归]运算

```
public class HeapSort {

	public static void heapSort(int[] arr) {
		if (arr == null || arr.length == 0)
			return;

		//建立大顶堆
		for (int i = arr.length / 2; i >= 0; i--) {
			heapAdjust(arr, i, arr.length - 1);
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			swap(arr, 0, i);
			heapAdjust(arr, 0, i - 1);
		}
	}

	/**
	 * 堆筛选，除了start之外，start~end均满足大顶堆的定义。
	 * 调整之后start~end称为一个大顶堆。
	 *
	 * @param arr   待调整数组
	 * @param start 起始指针
	 * @param end   结束指针
	 */
	public static void heapAdjust(int[] arr, int start, int end) {
		int currentParentValue = arr[start];

		//左右孩子的节点分别为2*i+1,2*i+2
		
		//检测当前节点是否有左孩子
		for (int leftChildIndex = getLeftChildIndexFromParent(start); 
			 leftChildIndex <= end; leftChildIndex *= 2) {
			
			int maxChildIndex = leftChildIndex;
			//如果当前左孩子不是末尾元素
			if (leftChildIndex < end) {
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
			arr[start] = arr[maxChildIndex]; //将子节点上移
			
			start = maxChildIndex; //下一轮筛选
		}

		arr[start] = currentParentValue; //插入正确的位置
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
```
## 白话分析排序过程
举例：
存在数组 A = {1, 3, 4, 5, 7, 2, 6, 8, 0}，按如下步骤执行：
1. 构造初始堆，即根据待排序序列构造第一个大根堆或者小根堆；
2. 首尾交换，断尾重构，即对断尾后剩余部分重新构造大（小）根堆
3. 重复第二步，直到首尾重叠，排序完成

下面是图解：


![建堆图解.png](http://upload-images.jianshu.io/upload_images/5993633-4e6cbff7c5926c71.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 构建初始大顶堆
从下标n/2处遍历 n/2 - 0 范围的节点进行构建符合堆特性的堆，这里使用大顶堆，即根节点为最大的数字；数组长度为9，即先从 9/2 即 A[4]处开始遍历。
1. 图1.1：A[4]元素为7，判断7是否有左右子节点，当前无左右子节点，跳转至A[3]。

2. 图1.1：A[3]元素为5，当前节点有左右子节点，取较大子节点，此时A[7]元素8较大，那么交换A[3]和A[7]即交换数字5和8。交换后迭代遍历被交换的子节点是否也有子节点，如果有，则执行相同的操作，直至被交换的子节点没有孩子节点为止，此处A[7]位置5没有子节点。

3. 图1.2：A[2]元素为4，且有左右子节点，其中A[6]元素6较大，交换A[2]和A[6]即交换数字4和6。交换后A[6]元素没有子节点，该轮结束。

4. 图1.3和1.4：A[1]元素为3，和子节点较大者A[3]元素8交换；交换后A[3]元素3和其子节点A[7]元素5交换；交换后A[7]没有子节点，该轮结束。

5. 图1.5： 最后轮到A[0]元素为1，和子节点较大者A[1]元素8交换；交换后A[1]元素1和子节点较大者A[4]元素7进行交换。交换后A[4]元素7没有子节点，该轮结束。

此时，大顶堆的构建流程完毕。

**如下代码中：heapAdjust对当前堆重建之后可以保证根节点为最大值。**
```
		//建立大顶堆
		for (int i = arr.length / 2; i >= 0; i--) {
			heapAdjust(arr, i, arr.length - 1);
		}
```

![首尾交换以及重建堆.png](http://upload-images.jianshu.io/upload_images/5993633-6a6d126b129ba139.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 首尾交换，断尾重构堆

- 建堆完成之后，堆如图 1.7 是个大根堆。
- 将 A[0] = 8 与 A[heapLen-1] 交换, 然后 heapLen 减一，如图 2.1，然后 AdjustHeap(A, heapLen-1, 0)，如图 2.2。
- 如此交换堆的第一个元素和堆的最后一个元素即**首尾交换**，然后堆的大小 heapLen 减一，对堆的大小为 heapLen 的堆进行调堆即**断尾重构**，
- 如此循环，直到 heapLen == 1 时停止，最后得出结果如图 3。


![排序结果.png](http://upload-images.jianshu.io/upload_images/5993633-d50faf5974d11799.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)