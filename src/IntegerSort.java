// Arsen Cui
// ICS4U1-01
// November 10, 2019
// Mr. Radulovic
// ICS4U1 Efficiency of Algorithms Assignment
/*This program compares the speeds and time efficiencies of 3 different sorting methods: 
Methods 1, 2, and 3. The program will read from a file of integers and output the amount of time 
taken to sort the integers from least to greatest. Based on user input, they can choose whether to
use Method 1, 2 or 3, and also choose the size of the file of integers they want to sort, from 
2^4 integers to 2^20 integers.*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IntegerSort implements Sorter {

	private int[] globalList;

	public static void main(String[] args) throws FileNotFoundException {

		File fileList = new File("2power19.txt");
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

		//System.out.println(o.toString());

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

	/*
	 * Recursive method that breaks down the list into smaller components, and then
	 * recombines them in sorted order using combineArray
	 */
	public void sort_method3(int start, int end) {

		// When the list is split until each single value is separate, exit the
		// recursive loop
		if (end - start == 0)
			return;

		/*If the values have not been split into separate single values 
		yet, find the middle of each index range, and split them again*/
		else {
			int middle = (start + end) / 2;

			// Splits the first half of the array repeatedly
			sort_method3(start, middle);
			
			/*Use the start and middle values from each exit of the first sort_method3
			instance to call itself again to split the second half */
			sort_method3(middle + 1, end);

			/*Use the start and end indexes from each exit from the previous sort_method3 calls 
			to call combineArray, which sorts the values within the start and end indexes*/
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
			list1[i] = getList()[globalIndex];

			i += 1;
			globalIndex += 1;

		}

		i = 0; // Reset index to track the second half of the index range

		// Runs through the second half of the current index range, adding the values
		// from the main list to the subarray
		while (i < length / 2 + 1) {
			list2[i] = getList()[globalIndex];

			i += 1;
			globalIndex += 1;
		}

		/*
		 * Indexes tracking through the subarrays, sorting and combining the values in
		 * the two halves of the current index range
		 */
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;

		// Runs through the two subarrays and adds the values in sorted order to the
		// third subarray
		while (i1 < list1.length && i2 < list2.length) {
			/*
			 * If the current value in the first subarray is smaller than the current value
			 * in the second subarray, add the smaller value to the third subarray
			 */
			if (list1[i1] <= list2[i2]) {
				list3[i3] = list1[i1];

				i1 += 1;
				i3 += 1;

				/*
				 * If the current value in the second subarray is smaller than the current value
				 * in the first subarray, add the smaller value to the third subarray
				 */
			} else if (list2[i2] < list1[i1]) {
				list3[i3] = list2[i2];

				i2 += 1;
				i3 += 1;
			}
		}

		/*
		 * If one subarray reaches its end before the other, add the rest of the values
		 * in the subarray to the list as you know it is already sorted
		 */

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
		
		String s = "";
		
		for (int i = 0; i < globalList.length; i += 1) 
		{
			s += globalList[i] + ", ";
		}

		return s;
	}

}
