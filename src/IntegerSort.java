import java.io.File;
public class IntegerSort implements Sorter {
	
	protected int[] globalList;

	public static void main(String[] args) {
		
		File fileList = new File("2power4.txt");
		
		DO FILE INPUT HERE, SET THE VALUES TO globalList IN HERE I THINK
		
	}

	public int[] sort_method1(int[] list)
	{
		return list;
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
		
	}

}
