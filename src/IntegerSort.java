// Arsen Cui
// ICS4U1-01
// November 10, 2019
// Mr. Radulovic
// ICS4U1 Efficiency of Algorithms Assignment
/*This program compares the speeds and time efficiencies of 3 different sorting methods: 
Methods 1, 2, and 3. The program will read from a file of integers and output the amount of time
taken to sort the integers from least to greatest. Based on user input, they can choose whether to
use Method 1, 2 or 3, and also choose the size of the file of integers they want to sort, from
2^4 integers to 2^20 integers. */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IntegerSort implements Sorter {

	private int[] globalList; // List that is passed into the sort methods to be sorted

	public static void main(String[] args) throws FileNotFoundException {

		File fileList = new File("INPUT FILE PATH HERE"); // File containing the list of integers
		
		// Scanner used to read the file, getting the amount of integers there are
		Scanner scanCount = new Scanner(fileList);
		
		// Scanner used to store values from the file to a list
		Scanner scan = new Scanner(fileList); 

		// Object of the IntegerSort class to use its own methods in the main method
		IntegerSort o = new IntegerSort(); 

		int index = 0; // Index to track how long the list of integers is

		// Counts how many integers are in the file
		while (scanCount.hasNextInt())
		{
			index += 1;
			scanCount.nextInt();
		}

		int[] unsortedList = new int[index]; // Array used to store the unsorted list of integers

		index = 0; // Index to add the integers from the file to unsortedList

		// Reads from the file and stores the values in unsortedList
		while (scan.hasNextInt())
		{
			unsortedList[index] = scan.nextInt();
			index += 1;
		}

		// Transfers values from unsortedList to globalList, which is passed into the sort methods
		o.setList(unsortedList); 

		// Tracks amount of time taken to sort the values in globalList, and prints time in seconds
		
		double timeStart = System.nanoTime();
		o.sort(3); // INPUT SORT TYPE HERE
		double timeEnd = System.nanoTime();

		System.out.println((timeEnd - timeStart) / 1e9 + " seconds");

		scanCount.close();
		scan.close();

	}

	// First method used to sort the list of integers
	public void sort_method1(int[] list) {
		
		/*Starting from the first and second index, this method compares 2 integers, and switches
		the order of them when necessary so the smaller integer is before the larger integer. This 
		process is repeated with each successive pair of integers. This is continued until the 
		indexes reach the end of the list, and then the process is redone from the start. At the 
		end of one cycle, the largest integer sorted in that cyclewill be at the bottom, so each 
		time you re run the cycle, you can stop at the largest integer that was sorted in the last 
		cycle because you know it is already in order. All of this is repeated until the list is 
		sorted.*/
		
		// j represents the end index of the currently unsorted integers.
		for (int j = list.length - 1; j > 0; j -= 1)
		{
			for (int i = 0; i < j; i += 1)
			{
				// If the second integer is smaller than the first, switch them
				if (list[i + 1] < list[i])
				{
					int temp = list[i + 1];
					list[i + 1] = list[i];
					list[i] = temp;
				}
			}
		}
	}

	// Second method used to sort the list of integers
	public void sort_method2(int[] list) {
	
		/*Starting from the first and second index, this method compares 2 integers, and switches
		the order of them when necessary so the smaller integer is before the larger integer. This 
		process is repeated with the second index moving down one, and the first index stays. This
		is continued until the second index reaches the end of the list, where you know the top 
		value is in sorted order. and then the process is redone from the start, this time with the
		first index moved down one. All of this is repeated until the list is sorted.*/
		
		// i represents the first index
		for (int i = 0; i < list.length - 1; i += 1)
		{
			// j represents the second index
			for (int j = i + 1; j < list.length; j += 1)
			{
				if (list[j] < list[i])
				{
					int temp = list[j];
					list[j] = list[i];
					list[i] = temp;
				}
			}
		}
	}

	/* Recursive method that breaks down the unsorted list into smaller components,
	 * and then recombines them in sorted order using combineArray */
	public void sort_method3(int start, int end) {

		// When the list is split until each single value is separate, exit the
		// recursive loop
		if (end - start == 0)
			return;

		/* If the values have not been split into separate single values
		 * yet, find the middle of each index range, and split them again */
		else
		{
			int middle = (start + end) / 2;
			
			// Splits the first half of the array repeatedly
			sort_method3(start, middle);

			/* Uses the middle value from each exit of the first sort_method3
			 * instance to call itself again to split the second half of the array */
			sort_method3(middle + 1, end);

			/*Use the start and end indexes from each exit from the previous sort_method3 calls to 
			call combineArray, which sorts the values within the start and end indexes */
			combineArray(start, end);
		}

	}

	/*Method used to combine the values within the passed in indexes
	in sorted order, and then add it back to globalList */
	public void combineArray(int start, int end) {
		
		int length = end - start; // Finds 

		/* Arrays used to sort the values within the current specified indexes. Split the values 
		 * between the 2 indexes into 2 separate arrays, list1 and list2. list3 is used to combine 
		 * list1 and list2 in sorted order. */
		
		int[] list1 = new int[length / 2 + 1];
		int[] list2 = new int[length / 2 + 1];
		int[] list3 = new int[length + 1];

		/*Index used to track list1 and list2, adding the unsorted
		values from the main list to list1 and list2*/
		int i = 0;

		// Index tracking the main list containing the unsorted values
		int globalIndex = start;

		/* Runs through the first half of the current index range,
		 * adding the values from the main list to list1 */
		
		while (i < list1.length)
		{
			list1[i] = getList()[globalIndex];

			i += 1;
			globalIndex += 1;

		}

		i = 0; // Reset index to track the second half of the index range

		/*Runs through the second half of the current index range,
		adding the values from the main list to list2*/
		
		while (i < list2.length)
		{
			list2[i] = getList()[globalIndex];

			i += 1;
			globalIndex += 1;
		}

		/* Indexes tracking through list1, list2, and list3, sorting and combining the values in
		 * the two halves of the current index range */
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;

		// Runs through list1 and list2 and adds the values in sorted order to list3
		while (i1 < list1.length && i2 < list2.length)
		{
			/* If the current value in list1 is smaller than the current value
			 * in list2, add the smaller value to list3 */
			if (list1[i1] <= list2[i2])
			{
				list3[i3] = list1[i1];

				i1 += 1;
				i3 += 1;

			/* If the current value in list2 is smaller than the current value
			 * in list1, add the smaller value to list3 */
			} else if (list2[i2] < list1[i1])
			{
				list3[i3] = list2[i2];

				i2 += 1;
				i3 += 1;
			}
		}

		/* If one subarray reaches its end before the other, add the rest of the values
		 * in the other subarray to the list as you know it is already sorted */

		while (i1 < list1.length)
		{
			list3[i3] = list1[i1];

			i1 += 1;
			i3 += 1;
		}

		while (i2 < list2.length)
		{
			list3[i3] = list2[i2];

			i2 += 1;
			i3 += 1;
		}

		// Transfers the sorted values to globalList
		for (int x = 0; x <= length; x += 1)
			globalList[start + x] = list3[x];

	}

	// Method used to set globalList to the list that was read from the file
	@Override
	public void setList(int[] list) {
		globalList = list;

	}

	// Method used to return globalList if needed
	@Override
	public int[] getList() {
		return globalList;
	}

	// Method used to call the 3 separate sort methods
	@Override
	public void sort(int type) {

		if (type == 1)
		{
			sort_method1(globalList);
		}

		if (type == 2)
		{
			sort_method2(globalList);
		}

		if (type == 3)
		{
			sort_method3(0, globalList.length - 1);
		}

	}

	// Method used to return the current state of the list
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
