import java.util.Arrays;
import java.util.Scanner;

public class Sorting {

	private int[] mixedSort(int size, int[] array) {
		if (size <= 10) {
			for(int i = 0 ; i < array.length ; i ++) {
				for(int j = 0 ; j < array.length -i - 1 ; j ++) {
					if(array[j] > array[j+1]) {
						int temp = array[j];
						array[j] = array[j+1];
						array[j+1] = temp;
					}
				}
			}
			return array.clone();
		}
		else {
			int left = 0;
	        int right = size - 1;
	        int pivotSize = right/2;
	        int pivot = array[pivotSize];
	        
	        do{
	            while(array[left] < pivot) {
	            	left++;
	            }
	            while(array[right] > pivot) {
	            	right--;
	            }
	            if(left <= right){    
	            	int temp = array[left];
	            	array[left] = array[right];
	            	array[right] = temp;
	                left++;
	                right--;
	            }
	        }while (left <= right);
	        int[] returnArray = array.clone();
	        if(0 < right) {
	        	int[] half = mixedSort(right + 1, Arrays.copyOfRange(array, 0, right + 1));
	        	System.arraycopy(half, 0, returnArray, 0, half.length);
	        }
	        
	        if((size-1) > left) {
	        	int[] half = mixedSort(size - left, Arrays.copyOfRange(array, left, size));
	        	System.arraycopy(half, 0, returnArray, left, half.length);
	        }		
        	return returnArray;
	        
		}
		

	}
	
	
	
	public static void main(String args[]) {
		
		Scanner input = new Scanner(System.in);

		int inputSize;
		
		inputSize = input.nextInt();
		
		int[] inputArray = new int[inputSize];
		for (int i = 0; i < inputSize; i++) {
			inputArray[i] = input.nextInt();
		}
		
		Sorting sorter = new Sorting();
		
		int[] resultArray = sorter.mixedSort(inputSize, inputArray);

		for (int i = 0; i < inputSize; i++) {
			System.out.printf("%d ", resultArray[i]);
		}
		
		input.close();
	}
	
}