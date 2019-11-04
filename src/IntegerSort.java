import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class IntegerSort implements Sorter {
	
	protected int[] globalList;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File fileList = new File("2power4.txt");
		Scanner scanCount = new Scanner(fileList);
		Scanner scan = new Scanner(fileList);
		
		IntegerSort o = new IntegerSort();
		
		int index = 0; // Index to track how long the list of numbers is
		
		// Counts how many numbers are in the file
		while (scanCount.hasNextInt())
		{
			index += 1;
			scanCount.nextInt();
		}
			
		
		int[] unsortedList = new int[index];
		
		index = 0; // Index to add the numbers from the file to an array
		
		while (scan.hasNextInt())
		{
			unsortedList[index] = scan.nextInt();
			index += 1;
		}
		
		o.setList(unsortedList);
		
		// Start and end values to pass into sort_method3
		int start = 0;
		int end = o.globalList.length;
		
		double timeStart = System.nanoTime();
		o.sort(2);
		double timeEnd = System.nanoTime();
		
		System.out.println((timeEnd - timeStart)/1e9);
		
		/*for (int i = 0; i < o.globalList.length; i += 1)
		{
			System.out.println(o.globalList[i]);
		}*/
		
		scanCount.close();
		scan.close();
		
	}

	public void sort_method1(int[] list)
	{
		for (int j = list.length - 1; j > 0; j -= 1)
		{
			for (int i = 0; i < j; i += 1)
			{
				if (list[i + 1] < list[i])
				{
					int temp = list[i + 1];
					list[i + 1] = list[i];
					list [i] = temp;
				}
			}
		}
	}
	
	public void sort_method2(int[] list)
	{
		for (int i = 0; i < list.length - 1; i += 1)
		{
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
	
	public void sort_method3(int start, int end)
	{
		if (end - start == 0)
				return;
		
		else
		{
			int middle = (start + end)/2;
			
			sort_method3(start, middle);
			sort_method3(middle + 1, end);
			
			combineArray(start, end);
		}
			
	}
	
	CONTINUE WRITING COMBINEARRAY
	public void combineArray(int start, int end)
	{
		int middle = (start + end)/2;
		
		int[] list1 = new int[middle + 1];
		int[] list2 = new int[middle + 1];
		int[] list3 = new int[end];
		
		// Indexes tracking through lists 1, 2 and 3
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		
		while (i1 < list1.length && i2 < list2.length)
		{
			if (list1[i1] <= list2[i2])
			{
				list3[i3] = list1[i1];
				
				i1 += 1;
				i3 += 1;
			}
			else if (list2[i2] < list1[i1])
			{
				list3[i3] = list2[i2];
				
				i2 += 1;
				i3 += 1;
			}	
		}
		
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
		
		if (type == 1)
		{
			sort_method1(globalList);
		}
		
		if (type == 2)
		{
			sort_method2(globalList);
		}
		
		/* if (type == 3)
		 * {
		 * sort_method3(0, 0);
		 * } */
		
	}

}
