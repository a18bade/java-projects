//written by bade0149 and awad0020 
//using some code from TextScan.java that was provided for file IO.
// Ahmed Bade and Dadir Awad

import java.util.Scanner;
import java.io.*;

public class Hash<T>{

	private NGen<T>[] hashTable;
	private boolean knownHash = false;
	private String fileName = "";

	//constructor for unknown data
	public Hash(String file){

		hashTable = (NGen<T>[]) new NGen[101];
		fileName = file;
		Scanner readFile = null;
		String s;

        try {
            readFile = new Scanner(new File(file));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + file + " not found");
            System.exit(1);  
        }

        while (readFile.hasNext()) {
            s = readFile.next();
         	this.add((T) s);
        }

	}

	// constructor for known data
	public Hash(String file,int length){

		hashTable = (NGen<T>[]) new NGen[length];
		fileName = file;
		knownHash = true;

		Scanner readFile = null;
		String s;

		try {
            readFile = new Scanner(new File(file));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + file + " not found");
            System.exit(1);  
        }

        while (readFile.hasNext()) {
            s = readFile.next();
         	this.add((T) s);
        }

	}

	public void add(T item){

		int index = this.hash(item);
		NGen<T> ptr;
		boolean isFound = false;

		if (hashTable[index] == null)
			hashTable[index] = new NGen<>(item,null);
		else{
			
			ptr = hashTable[index];
			
			while(ptr!= null){

				if(ptr.getData().equals(item))
					isFound = true;

				ptr = ptr.getNext();

			}

			if (isFound == false)
				hashTable[index] = new NGen<>(item,hashTable[index]);

		}

	}

	//displays every token in the hashtable and the following stats:
	// number of empty cells in array
	// average length of the list
	// smallest length : non-zero
	// largest length
	// number of cells greater than 1

	public void display(){

		int index = 0;
		int numberEmpty = 0;
		int totalLength = 0;
		int largestLength = 0;
		int smallestLength = 1000;

		int numberOfNonZeroLengths = 0;

		int nodeLength = 0;

		System.out.println();
		System.out.println("Name of file: " + fileName);

		for(NGen<T> node : hashTable){

			int length = 0;
			nodeLength = 0;

			if (node == null){
				System.out.println(index + ": null");
				numberEmpty++;
			}
			else{

				numberOfNonZeroLengths++;

				String str = "";

				while(node != null){

					str+= "[" + node.getData() + "]";
					node = node.getNext();
					nodeLength++;
				}

				totalLength += nodeLength;

				if (nodeLength > largestLength){
					largestLength = nodeLength;
				}
				if (nodeLength < smallestLength){
					smallestLength = nodeLength;
				}

				System.out.println(index + ": " + str);

			}

			index++;
		}

		System.out.println("Number of empty cells: " + numberEmpty);
		System.out.println("Shortest Length: " + smallestLength);
		System.out.println("Longest Length: " + largestLength);
		System.out.println("Average Length: " + ((int) totalLength/numberOfNonZeroLengths));
		
	}

	// add hash keys for known and unknown
	private int hash(T key){

		String keys = (String) key;

		int hashkey = 0;

		if (knownHash == false){

			int sum = 0;

			for(int x = 0; x< keys.length(); x++){
				sum+= keys.charAt(x);
			}
			hashkey = (sum) % 101;
		}
		else {

			hashkey = ((keys.charAt(0) * keys.charAt(keys.length()-2) + keys.length() * 819) % 50);

		}

		return hashkey;

	}

	//creates and displays hashtables for each file given.
	public static void main(String[] args){

		String[] filesArray = new String[]{"keywords.txt","canterbury.txt","gettysburg.txt","proverbs.txt","that_bad.txt"};

		Hash<String>[] hashArray = (Hash<String>[]) new Hash[5];

		int index = 0;
		for(String file : filesArray){

			if(file.equals("keywords.txt"))
				hashArray[index] = new Hash<>(file,50);
			else{
				hashArray[index] = new Hash<>(file);
			}
			index++;
		}

		for(int x = 0; x< hashArray.length; x++){
			System.out.println("Hash Table " + (x+1) + ":");
			hashArray[x].display();
			System.out.println();
			System.out.println();

		}

	}

}