//written by bade0149 and awad0020

public class LinkedList<T extends Comparable<T>> implements List<T>{

	//my implementation was a headed list.
	
	private boolean isSorted;
	private int size0fList = 0;
	private Node<T> list;

	public LinkedList(){

		boolean isSorted = true;
		list = new Node<>();
		list.setData(null);
		list.setNext(null);

	}

    //adds a element to the list and shifts elements accordinly.
    public boolean add(T element){

    	if (element == null)
    		return false;

    	isSorted = false;
    	size0fList++;
    	Node<T> ptr = list;

    	while(ptr.getNext()!= null){
    		ptr = ptr.getNext();
    		}

    	Node<T> newNode = new Node<>(element,null);
    	ptr.setNext(newNode);

    	return true;

    }


    public boolean add(int index, T element){

    	if (index < 0 || index >= size0fList){
    		return false;
    	}
    	
    	isSorted = false;
    	size0fList++;
    	Node<T> ptr = list;

    	for (int x = 0; x<index; x++){

    		ptr = ptr.getNext();

    	}
    	Node<T> newNode = new Node<>(element,ptr.getNext());
    	ptr.setNext(newNode);

    	return true;

    }

    //clears array by changing head to point to null.
    public void clear(){
    	size0fList = 0;
    	isSorted = true;
    	list.setNext(null);
    }

    // checks if an element is apart of a list. terminates early if isSorted is true and we reach a element
    // that is greater than the element we are looking for.
    public boolean contains(T element){

    	Node<T> ptr = list.getNext();
    	boolean isIn = false;

    	if (isSorted == false){
    		for(int x = 0; x<size0fList; x++){
    			if (ptr.getData().equals(element))
    				isIn = true;
    			ptr = ptr.getNext();
    		}

    	}
    	
    	return isIn;

    }

     // returns the element at that index.
    public T get(int index){

    	if (index < 0 || index >= size0fList)
    		return null;

    	Node<T> ptr = list.getNext();

    	for(int x = 0; x<index; x++){

    		if (ptr!= null)
    			ptr = ptr.getNext();
    	}

    	return ptr.getData();

    }

    // returns the index of the first occuring element. returns -1 if the element isn't in the list.
    // terminates early if isSorted is true and we reach an element that is greater than the element
    // we are looking for

    public int indexOf(T element){

    	if (element == null)
    		return -1;

    	Node<T> ptr = list.getNext();
    	int index = 0;

    	while(ptr != null){

    		T data = ptr.getData();

    		if (isSorted == true && data.compareTo(element)>0){
    			return -1;
    		}

    		if (data.equals(element))
    			return index;

    		ptr = ptr.getNext();

    		index++;
    	}

    	
    	return -1;

    // checks if the list is empty by checking variable size0fList;

    }

    public boolean isEmpty(){

    	if (size0fList == 0)
    		return true;

    	return false;
    }

    // returns the last index of the element. two variables to keep track previousIndex and index
    // returns previousIndex which is initialzed to -1 if we can't find the element
    // or returns the last element we found.
    // terminates early same as previous methods.
    public int lastIndexOf(T element){

    	if (element == null)
    		return -1;

    	Node<T> ptr = list.getNext();
    	int index = 0;
    	int previousIndex = -1;

    	while(ptr != null){

    		T data = ptr.getData();

    		if(data.equals(element)){
    			previousIndex = index;
    		}

    		if (isSorted == true && data.compareTo(element) > 0){
    			return previousIndex;
    		}
    		ptr = ptr.getNext();
    		index++;
    	}

    	return previousIndex;
    }

    // sets the element at the index to the element given.
    // returns prevElement

    public T set(int index, T element){

    	if (index < 0 || index >= size0fList || element == null)
			return null;

		isSorted = false;

		Node<T> ptr = list.getNext();

		for(int x = 0; x < index; x++){
			ptr = ptr.getNext();
		}

		T oldElement = ptr.getData();
		ptr.setData(element);

		return oldElement;


    }
    // returns size0fList which is updated in other methods accordingly.
    public int size(){

    	return size0fList;

    }
    // there are 3 cases of sorts
    // if its already sorted and order is true terminates early
    // if isSorted is true and order is false (we reverse the list) O(n)
    // otherwise sort according to order.
    public void sort(boolean order){

    	if (isSorted == true && order == true)
    		return;
    	else if (isSorted == true && order == false){

    		Node<T> prev = list.getNext();
    		Node<T> curr = prev.getNext();
    		Node<T> next = null;

    		while(curr!= null){
    			next = curr.getNext();
    			curr.setNext(prev);
    			prev = curr;
    			curr = next;
    		}
    		list.getNext().setNext(null);
    		list.setNext(prev);
    		isSorted = false;
    	}

    	else if (isSorted == false && order == true){

    		Node<T> trailer;
    		Node<T> ptr;

    		T trailerData;
    		T ptrData;
    		T temp;

    		int stop = 0;

    		for(int x = 1; x < size0fList; x++){

    			trailer = list.getNext();
    			ptr = trailer.getNext();

    			for(int y = 1; y < size0fList-stop; y++){

    				trailerData = trailer.getData();
    				ptrData = ptr.getData();

    				if (trailerData.compareTo(ptrData)>0){
    					temp = ptrData;
    					ptr.setData(trailerData);
    					trailer.setData(temp);
    				}

    			trailer = trailer.getNext();
    			ptr = ptr.getNext();

    			}

    		stop++;
    		}
    		isSorted = true;


    	}

    	else if (isSorted == false && order == false){

    		Node<T> trailer;
    		Node<T> ptr;

    		T trailerData;
    		T ptrData;
    		T temp;

    		int stop = 0;

    		for(int x = 1; x < size0fList; x++){

    			trailer = list.getNext();
    			ptr = trailer.getNext();

    			for(int y = 1; y < size0fList-stop; y++){

    				trailerData = trailer.getData();
    				ptrData = ptr.getData();

    				if (trailerData.compareTo(ptrData)<0){
    					temp = ptrData;
    					ptr.setData(trailerData);
    					trailer.setData(temp);
    				}

    			trailer = trailer.getNext();
    			ptr = ptr.getNext();

    			}

    		stop++;
    		}

    	isSorted = false;

    	}

    }
    // removes element if in list and changes pointers for nodes.
    // terminates early if isSorted is true and we reach a value greater than the one we are looking for.
    public boolean remove(T element){

    	Node<T> trailer = list;
    	Node<T> ptr = list.getNext();

    	while(ptr!= null){

    		T data = ptr.getData();
    		if (data.equals(element)){
    			trailer.setNext(ptr.getNext());
    			ptr.setNext(null);
    			size0fList--;
    			return true;
    		}
    		if (isSorted == true && data.compareTo(element) > 0)
    			return false;

    		ptr = ptr.getNext();
    		trailer = trailer.getNext();
    	}


    	return false;

    }

    //removes a certain index and changes pointers.

    public T remove(int index){

    	if (index < 0 || index >= size0fList)
    		return null;

    	Node<T> trailer = list;
    	Node<T> ptr = list.getNext();

    	for(int i = 0; i < index; i++){

    		trailer = trailer.getNext();
    		ptr = ptr.getNext();
    	}

    	T oldValue = ptr.getData();
    	trailer.setNext(ptr.getNext());
    	ptr.setNext(null);
    	size0fList--;

    	return oldValue;

    }

    public String toString(){

    	String str = "";
    	Node<T> ptr = list.getNext();
    	while(ptr!= null){
    		str += ptr.getData() + "\n";
    		ptr = ptr.getNext();
    	}

    	return str;
    }

    public static void main(String[] args){

    	/* Debugging
    	LinkedList<Integer> linkedList = new LinkedList<>();

    	System.out.println("Elements of the list:");
    	linkedList.add(1);
    	linkedList.add(98);
    	linkedList.add(6);
    	linkedList.add(872);
    	linkedList.add(10);
    	linkedList.add(867);
    	linkedList.add(11);
    	linkedList.add(204);
    	linkedList.add(13);
    	linkedList.add(15);
    	linkedList.add(25);
    	linkedList.add(11);
    	linkedList.add(2,100);
    	linkedList.add(11,381);
    	linkedList.add(0,405);
		System.out.println();

    	System.out.println(linkedList);
    	System.out.println("Testing contains..");
    	System.out.println("The list contains 1 :" + linkedList.contains(1));
    	System.out.println("the list contains 9 :" + linkedList.contains(6));
    	System.out.println("the list contains 200 :" + linkedList.contains(200));

    	System.out.println("Testing gets..");
    	System.out.println("the 3rd index is  " + linkedList.get(3));
    	System.out.println("the 5rd index is  " + linkedList.get(5));
    	System.out.println("the 9rd index is  " + linkedList.get(9));

    	System.out.println("Testing indexOf..");
    	System.out.println("the index of 1 is " + linkedList.indexOf(1));
    	System.out.println("the index of 7 is " + linkedList.indexOf(7));
    	System.out.println("the index of 10 is " + linkedList.indexOf(10));

    	System.out.println("Testing lastIndex of..");
    	System.out.println("the last index of 11 is " + linkedList.lastIndexOf(11));
    	System.out.println("the last index of 13 is " + linkedList.lastIndexOf(13));
    	System.out.println("the last index of 9 is " + linkedList.lastIndexOf(9));

    	System.out.println("Testing set of..");
    	System.out.println("Setting the 3rd index to 191 " + linkedList.set(3,9));
    	System.out.println("Setting the 7rd index to 259 " + linkedList.set(7,259));
    	System.out.println("Setting the 0rd index to 81 " + linkedList.set(0,81));
    	System.out.println("Setting the 10rd index to 191 " + linkedList.set(10,191));
    	System.out.println(linkedList);


    	System.out.println("Testing remove (int version)....");
    	System.out.println("Removing index 4 from the list:  " + linkedList.remove(4));
    	System.out.println("Removing index 9 from the list:  " + linkedList.remove(9));
    	System.out.println("Removing index 0 from the list:  " + linkedList.remove(0));
    	System.out.println(linkedList);

    	System.out.println("Testing remove (value version)....");
    	System.out.println("Removing index 13 from the list:  " + linkedList.remove((Integer)13));
    	System.out.println("Removing index 13 from the list:  " + linkedList.remove((Integer)13));
    	System.out.println("Removing index 81 from the list:  " + linkedList.remove((Integer)81));
    	System.out.println(linkedList);


    	System.out.println("Testing sort list normally...");
    	linkedList.sort(true);
    	System.out.println(linkedList);

    	System.out.println("Testing sort descending...");
    	linkedList.sort(false);
    	System.out.println(linkedList);

    	System.out.println("Testing sort (reversing a linked list).....");
    	linkedList.sort(false);
    	System.out.println(linkedList);

    	System.out.println("Testing clear..");
    	linkedList.clear();
    	System.out.println("Is the list empty?: " + linkedList.isEmpty());
    	linkedList.add(10);
    	System.out.println("New list below");
    	System.out.println("Is the list empty?: " + linkedList.isEmpty());
    	System.out.println(linkedList);

    */

    }





	
}