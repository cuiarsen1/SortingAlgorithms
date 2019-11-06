import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IntegerSort implements Sorter {

	// TRY WITHOUT RECURSION, USING 2D ARRAYS

	protected int[] globalList;

	public static void main(String[] args) throws FileNotFoundException {

		File fileList = new File("2power20.txt");
		Scanner scanCount = new Scanner(fileList);
		Scanner scan = new Scanner(fileList);

		IntegerSort o = new IntegerSort();

		int index = 0; // Index to track how long the list of numbers is

		// Counts how many numbers are in the file
		while (scanCount.hasNextInt()) {
			index += 1;
			scanCount.nextInt();
		}

		int[] unsortedList = new int[index];

		index = 0; // Index to add the numbers from the file to an array

		while (scan.hasNextInt()) {
			unsortedList[index] = scan.nextInt();
			index += 1;
		}

		o.setList(unsortedList);

		double timeStart = System.nanoTime();
		o.sort(3);
		double timeEnd = System.nanoTime();

		System.out.println((timeEnd - timeStart) / 1e9);

		/*
		 * for (int i = 0; i < o.globalList.length; i += 1) {
		 * System.out.println(o.globalList[i]); }
		 */

		scanCount.close();
		scan.close();

	}

	public void sort_method1(int[] list) {
		for (int j = list.length - 1; j > 0; j -= 1) {
			for (int i = 0; i < j; i += 1) {
				if (list[i + 1] < list[i]) {
					int temp = list[i + 1];
					list[i + 1] = list[i];
					list[i] = temp;
				}
			}
		}
	}

	public void sort_method2(int[] list) {
		for (int i = 0; i < list.length - 1; i += 1) {
			for (int j = i + 1; j < list.length; j += 1) {
				if (list[j] < list[i]) {
					int temp = list[j];
					list[j] = list[i];
					list[i] = temp;
				}
			}
		}
	}

	/*Recursive method that breaks down the list into smaller components,
	and then recombines them in sorted order using combineArray*/
	public void sort_method3(int start, int end) {
		
		// When the list is split until each single value is separate, exit the recursive loop
		if (end - start == 0)
			return;

		// If the values have not been split into separate 
		else {
			int middle = (start + end) / 2;

			sort_method3(start, middle);
			sort_method3(middle + 1, end);

			combineArray(start, end);
		}

	}

	public void combineArray(int start, int end) {
		int length = end - start;

		/*
		 * Arrays used to sort the values within the current specified indexes. Split
		 * the values between the 2 indexes into 2 separate arrays
		 */
		int[] list1 = new int[length / 2 + 1];
		int[] list2 = new int[length / 2 + 1];
		int[] list3 = new int[length + 1];

		// Index tracking the subarrays, used to add the unsorted values from the main
		// list to them

		int i = 0;

		// Index tracking the main list containing the unsorted values
		int globalIndex = start;

		// Runs through the first half of the current index range, adding the values
		// from the main list to the subarray
		while (i < length / 2 + 1) {

			list1[i] = globalList[globalIndex];

			i += 1;
			globalIndex += 1;

		}

		i = 0; // Reset index to track the second half of the index range

		// Runs through the second half of the current index range, adding the values
		// from the main list to the subarray
		while (i < length / 2 + 1) {
			list2[i] = globalList[globalIndex];

			i += 1;
			globalIndex += 1;
		}

		/*Indexes tracking through the subarrays, sorting 
		and combining the two halves of the index range*/
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;

		while (i1 < list1.length && i2 < list2.length) {
			
			if (list1[i1] <= list2[i2]) 
			{
				list3[i3] = list1[i1];

				i1 += 1;
				i3 += 1;
				
			}else if (list2[i2] < list1[i1]) 
			{
				list3[i3] = list2[i2];

				i2 += 1;
				i3 += 1;
			}
		}

		/*If one subarray reaches its end before the other, add the rest of the 
		values in the subarray to the list as you know it is already sorted*/
		
		while (i1 < list1.length) {
			list3[i3] = list1[i1];

			i1 += 1;
			i3 += 1;
		}

		while (i2 < list2.length) {
			list3[i3] = list2[i2];

			i2 += 1;
			i3 += 1;
		}

		// Transfers the sorted values to the main list
		for (int x = 0; x <= length; x += 1)
			globalList[start + x] = list3[x];

	}

	@Override
	public void setList(int[] list) {
		globalList = list;

	}

	@Override
	public int[] getList() {
		return globalList;
	}

	@Override
	public void sort(int type) {

		if (type == 1) {
			sort_method1(globalList);
		}

		if (type == 2) {
			sort_method2(globalList);
		}

		if (type == 3) {
			sort_method3(0, globalList.length - 1);
		}

	}

	@Override
	public String toString() {

		return null;
	}

}
