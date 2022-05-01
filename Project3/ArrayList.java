//written by bade0149 and awad0020

public class ArrayList<T extends Comparable<T>> implements List<T>{

	private boolean isSorted;
	private T[] list;
	private int nextOpen;

	public ArrayList(){

		isSorted = true;
		nextOpen = 0;
		list = (T[]) new Comparable[2];

	}

    //function to resize array and copy elements over.

	public void reSize(){

		T[] newArray = (T[]) new Comparable[2 * list.length];
		for (int x = 0; x < list.length; x++){
			if (list[x] != null)
				newArray[x] = list[x];
		}

		list = newArray;

	}

    //adds a element to the list and shifts elements accordinly.
	public boolean add(T element){

		if(element == null)
			return false;

		if (nextOpen >= list.length){
			reSize();
		}

		list[nextOpen] = element;

		/*checks if the the list is already sorted and if the new element added is greater than or
			equal to the element before it. It allows it isSorted to remain true*/

		if (isSorted == true && nextOpen > 1 && list[nextOpen-1].compareTo(list[nextOpen]) <= 0){
			isSorted = true;
		}
		else
			isSorted = false;

		nextOpen++;

		return true;

	}

    public boolean add(int index, T element){

    	if (index < 0 || index > nextOpen || element == null)
    		return false;

    	if (nextOpen >= list.length){
    		reSize();
    	}

    	if (index == nextOpen){
    		nextOpen++;
    		list[index] = element;
    	}
    	else{
    		for(int x = nextOpen-1; x >= index; x--){
    			list[x+1] = list[x];
    		}
    		list[index] = element;
    		nextOpen++;
    	}

    	isSorted = false;

    	return true;

    }

    //clears array and resets isSorted and nextOpen

    public void clear(){

    	for(int x = 0; x < list.length; x++){
    		list[x] = null;
    	}
    	nextOpen = 0;
    	isSorted = true;

    }

    // checks if an element is apart of a list. terminates early if isSorted is true and we reach a element
    // that is greater than the element we are looking for.
    public boolean contains(T element){

    	if (element == null){
    		return false;
    	}

    	int x = 0;

    	while(list[x]!= null){
    		if(list[x].compareTo(element) == 0){
    			return true;
    		}
    		if(isSorted == true && list[x].compareTo(element) > 0){
    			return false;

    		}
    		x++;
    	}

    	return false;

    }

    // returns the element at that index.

    public T get(int index){

    	if (index < 0 || index >= nextOpen){
    		return null;
    	}

    	return list[index];

    }

    // returns the index of the first occuring element. returns -1 if the element isn't in the list.
    // terminates early if isSorted is true and we reach an element that is greater than the element
    // we are looking for
    public int indexOf(T element){

    	if (element == null){
    		return -1;
    	}

    	int index = 0;

    	for(T data: list){

    		if (data!= null){
    			if (data.compareTo(element) == 0)
    				return index;

    			if (isSorted == true && data.compareTo(element)>0)
    				return -1;
    			index++;
    		}

    	}

    	return -1;

    }

    // checks if the list is empty by checking if the first element is null;
    public boolean isEmpty(){

    	return (list[0] == null);

    }

    // returns the last index of the element. two variables to keep track previousIndex and index
    // returns previousIndex which is initialzed to -1 if we can't find the element
    // or returns the last element we found.
    // terminates early same as previous methods.

    public int lastIndexOf(T element){

    	if (element == null)
    		return -1;

    	int index = 0;
    	int previousIndex = -1;

    	for (T data : list){

    		if (data!= null && data.compareTo(element)==0)
    			previousIndex = index;
    		if (data!= null && isSorted == true && data.compareTo(element) > 0)
    			return previousIndex;
    		index++;
    	}

    	return previousIndex;

    }

    // sets the element at the index to the element given.
    // returns prevElement

    public T set(int index, T element){

    	if (element == null || index < 0 || index >= nextOpen){
    		return null;
    	}

    	T prevElement;

    	prevElement = list[index];
    	list[index] = element;

    	return prevElement;

    }

    // returns nextOpen which is updated in other methods accordingly.
    public int size(){

    	return nextOpen;

    }
    // there are 3 cases of sorts
    // if its already sorted and order is true terminates early
    // if isSorted is true and order is false (we reverse the list) O(n)
    // otherwise sort according to order.

    public void sort(boolean order){

    	if (isSorted == true && order == true){
    		return;
    	}

    	else if (isSorted == true && order == false){

    		T[] newArray = (T[]) new Comparable[list.length];

    		int index = 0;

    		for(int x = nextOpen-1; x>=0; x--){
    			newArray[index] = list[x];
    			index++;
    		}

    		list = newArray;
    		isSorted = false;
    	}

    	else if (isSorted == false && order == true){

    		int index = nextOpen - 1;

    		for (int x = 0; x < index; x++){

    			for(int y = 0; y < index - x; y++){

    				if (list[y].compareTo(list[y+1])>0){
    					T temp = list[y];
    					list[y] = list[y+1];
    					list[y+1] = temp;

    				}

    			}
    		}

    		isSorted = true;
    	}

    	else if(isSorted == false && order == false){

    		int index = nextOpen - 1;

    		for (int x = 0; x < index; x++){

    			for(int y = 0; y < index - x; y++){

    				if (list[y].compareTo(list[y+1])<0){
    					T temp = list[y];
    					list[y] = list[y+1];
    					list[y+1] = temp;

    				}

    			}
    		}

    		isSorted = false;
    	}

    }

    // removes element if in list and shifts all other values to the left.
    public boolean remove(T element){

    	int index = 0;
    	boolean elementRemoved = false;

    	int x = 0;
    	while(list[x]!= null && elementRemoved == false){
    		if (list[x].compareTo(element) == 0){
    			list[x] = null;
    			index = x;
    			elementRemoved = true;
    		}
    		else if(isSorted == true && list[x].compareTo(element)>0){
    			return elementRemoved;

    		}
    		x++;

    	}

    	if (elementRemoved){
    		for(int y = index; y < nextOpen; y++){
    			list[y] = list[y+1];
    			list[y+1] = null;
    		}

    		nextOpen--;
    	}

    	return elementRemoved;

    }

    //removes a certain index and shifts all values to the left.

    public T remove(int index){

    	if (index < 0 || index >= nextOpen){
    		return null;
    	}

    	T removedObject = list[index];
    	list[index] = null;

    	for(int x = index; x < nextOpen; x++){
    		list[x] = list[x+1];
    		list[x+1] = null;
    	}

    	nextOpen--;

    	return removedObject;

    }

    public String toString(){

    	String str = "";

    	for(T element : list){
    		str += element + "\n";
    	}

    	str+= "\nAdditional Space in Array:" + (list.length - nextOpen) + "\n";

    	return str;
    }


    public static void main(String[] args){

    	/* Debugging
    	ArrayList<Integer> array = new ArrayList<>();

    	System.out.println("Elements of the list:");
    	array.add(9);
    	array.add(98);
    	array.add(11);
    	array.add(872);
    	array.add(13);
    	array.add(867);
    	array.add(11);
    	array.add(204);
    	array.add(13);
    	array.add(15);
    	array.add(15);
    	array.add(11);
    	array.add(2,100);
		array.add(11,381);
    	array.add(0,405);
		System.out.println();
    	System.out.println(array);
    	System.out.println("Next open spot: " + array.nextOpen);

    	
    	System.out.println("Testing contains..");
    	System.out.println("The list contains 405 :" + array.contains(405));
    	System.out.println("the list contains 25 :" + array.contains(25));
    	System.out.println("the list contains 204 :" + array.contains(204));
    	System.out.println("Next open spot: " + array.nextOpen);

		
    	System.out.println("Testing gets..");
    	System.out.println("the 3rd index is  " + array.get(3));
    	System.out.println("the 5rd index is  " + array.get(5));
    	System.out.println("the 9rd index is  " + array.get(9));
    	System.out.println("Next open spot: " + array.nextOpen);

    	
    	System.out.println("Testing indexOf..");
    	System.out.println("the index of 11 is " + array.indexOf(11));
    	System.out.println("the index of 867 is " + array.indexOf(867));
    	System.out.println("the index of 381 is " + array.indexOf(381));
    	System.out.println("Next open spot: " + array.nextOpen);

    	
    	System.out.println("Testing lastIndex of..");
    	System.out.println("the last index of 405 is " + array.lastIndexOf(405));
    	System.out.println("the last index of 13 is " + array.lastIndexOf(13));
    	System.out.println("the last index of 190 is " + array.lastIndexOf(190));
    	System.out.println("Next open spot: " + array.nextOpen);

    	

    	System.out.println("Testing set of..");
    	System.out.println("Setting the 3rd index to 191 " + array.set(3,191));
    	System.out.println("Setting the 7rd index to 259 " + array.set(15,259));
    	System.out.println("Setting the 0rd index to 81 " + array.set(0,81));
    	System.out.println("Setting the 10rd index to 191 " + array.set(10,191));
    	System.out.println(array);
    	System.out.println("Next open spot: " + array.nextOpen);

    	


    	System.out.println("Testing remove (int version)....");
    	System.out.println("Removing index 4 from the list:  " + array.remove(4));
    	System.out.println("Removing index 9 from the list:  " + array.remove(9));
    	System.out.println("Removing index 0 from the list:  " + array.remove(0));
    	System.out.println(array);
    	System.out.println("Next open spot: " + array.nextOpen);

    	

    	System.out.println("Testing remove (value version)....");
    	System.out.println("Removing index 1000000 from the list:  " + array.remove((Integer)100000));
    	System.out.println("Removing index 13 from the list:  " + array.remove((Integer)13));
    	System.out.println("Removing index 11 from the list:  " + array.remove((Integer)11));
    	System.out.println(array);
    	System.out.println("Next open spot: " + array.nextOpen);

    	

    	System.out.println("Testing sort list normally...");
    	array.sort(true);
    	System.out.println(array);
    	System.out.println("Next open spot: " + array.nextOpen);

    	
    	System.out.println("Testing sort descending...");
    	array.sort(false);
    	System.out.println(array);
    	System.out.println("Next open spot: " + array.nextOpen);

    	System.out.println("Testing sort (reversing an array list).....");
    	array.sort(false);
    	System.out.println(array);
    	System.out.println("Next open spot: " + array.nextOpen);

    	System.out.println("Testing clear..");
    	array.clear();
    	System.out.println("Is the array empty? :" + array.isEmpty());
    	System.out.println("Next open spot: " + array.nextOpen);

    	array.add(10);
    	System.out.println("New array below");
    	System.out.println(array);
    	System.out.println("Is the array empty? :" + array.isEmpty());
    	System.out.println("Next open spot: " + array.nextOpen);
		*/


    }


}