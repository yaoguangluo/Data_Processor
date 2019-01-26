package sortProcessor;
public class Heap_2D_Sort {
	int[] sort(int[] data){
		int startIndex = getParentIndex(data.length - 1);
		for (int i = startIndex; i >= 0; i--) {
			maxHeapify(data, data.length, i);
		}
		heapSort(data);
		return data;
	}
	private void maxHeapify(int[] data, int heapSize, int index){
		int left = getChildLeftIndex(index);
		int right = getChildRightIndex(index);

		int largest = index;
		if (left < heapSize && data[index] < data[left]) {
			largest = left;
		}
		if (right < heapSize && data[largest] < data[right]) {
			largest = right;
		}
		if (largest != index) {
			int temp = data[index];
			data[index] = data[largest];
			data[largest] = temp;
			maxHeapify(data, heapSize, largest);
		}
	}
	private void heapSort(int[] data){
		for (int i = data.length - 1; i > 0; i--) {
			int temp = data[0];
			data[0] = data[i];
			data[i] = temp;
			maxHeapify(data, i, 0);
		}
	}
	private int getParentIndex(int current){
		return (current - 1) >> 1;
	}

	private int getChildLeftIndex(int current){
		return (current << 1) + 1;
	}

	private int getChildRightIndex(int current){
		return (current << 1) + 2;
	}
}
