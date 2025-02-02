// Arsen Cui
// ICS4U1-01
// November 10, 2019
// Mr. Radulovic
// ICS4U1 Efficiency of Algorithms Assignment
/*This is an interface containing the setList, getList, sort, and toString methods that are 
implemented in the IntegerSort class.*/

public interface Sorter {
	/**
	 * This method sets the current list that is used in this class. 
	 * @param list - a reference to the list of elements to be sorted.
	 */
	public void setList(int[] list);
	
	/**
	 * This method returns the reference to the current list
	 * @return - a reference to the current list of elements
	 */
	public int[] getList();
	
	/**
	 * This method sorts the list in increasing order.
	 * @param type - parameter to be used to choose an internal sorting method.
	 * If type = 1 use sorting method 1
	 * If type = 2 use sorting method 2
	 * etc....
	 */
	public void sort(int type);
	
	/**
	 * This method prints out the current state of the list
	 * @return a String representing the list, either sorted or not depending on whether sort() was previously called.
	 * The format of the String is: num1, num2, num3, ...
	 */
	public String toString();
	
}