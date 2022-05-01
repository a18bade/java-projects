// written by bade0149

public class Quadratic{

	private float a , b, c;


	public Quadratic(float a, float b, float c){

		this.a = a;
		this.b = b;
		this.c = c;


	}

	public Quadratic add(Quadratic other){
		float newA = this.a + other.a;
		float newB = this.b + other.b;
		float newC = this.c + other.c;

		return new Quadratic(newA,newB,newC);

	}

	public Quadratic subtract(Quadratic other){
		float newA = this.a - other.a;
		float newB = this.b - other.b;
		float newC = this.c - other.c;

		return new Quadratic(newA,newB,newC);
	}


	public Roots findRoots(){

		Roots roots = new Roots(this.a, this.b, this.c);

		return roots;


	}


	public String toString(){ // returns string representation of quadratic function

		String str = this.a + "x^2 + " + this.b + "x + " + this.c;

		return str;

	}


	public boolean equals(Object otherObject){

		Quadratic temp = null;

		if (otherObject instanceof Quadratic)
			temp = (Quadratic) otherObject;
			Quadratic other = temp;

		float valueDiffofA = this.a - other.a;
		float valueDiffofB = this.b - other.b;
		float valueDiffofC = this.c - other.c;


		if ((valueDiffofA < 0.0001 && valueDiffofB < 0.0001 && valueDiffofC < 0.0001) &&

		 	(valueDiffofA > -0.0001 && valueDiffofB > -0.0001 && valueDiffofC > -0.0001))

			return true;


		return false;


	}

	// Main methods for Quadratic


	public static void main(String[] args){


		System.out.println("Lets create three quadratic functions and print their string representations.\n");

		Quadratic q1 = new Quadratic(3.9999f,3,5.0001f);
		Quadratic q2 = new Quadratic(2,3,4);
		Quadratic q3 = new Quadratic(1.8f,-2,3.45f);
		Quadratic q8 = new Quadratic(1,9,20);
		Quadratic q9 = new Quadratic(1,15,36);
		Quadratic q10 = new Quadratic(1,15,36);


		Quadratic[] myQuadratics = new Quadratic[] {q1,q2,q3};

		for(int x = 1; x < 4; x++){
			System.out.println("Quadratic #" + x + " = " + myQuadratics[x-1]);
		}


		System.out.println("\nLets now test the adding and subtracting quadratic methods.");

		Quadratic q4 = q1.add(q2);
		Quadratic q5 = q1.subtract(q2);

		System.out.println(q1 + "      plus    " + q2 + "       = " + q4);
		System.out.println(q1 + "      minus      " + q2 + "     = " + q5 + "\n");



		System.out.println("Testing findRoots().." + "\n");

		System.out.println("The roots of " + q1 + ":");

		System.out.println(q1.findRoots());

		System.out.println("The roots of " + q2 + ":");


		System.out.println(q2.findRoots());

		System.out.println("The roots of " + q8 + ":");


		System.out.println(q8.findRoots());

		System.out.println("The roots of " + q9 + ":");


		System.out.println(q9.findRoots() + "\n");


		System.out.println("Testing .equals().....");

		System.out.println("Is " + q1 + " equal to " + q2 + ": " + q1.equals(q2));

		System.out.println("Is " + q9 + " equal to " + q10 + ": " + q9.equals(q10) + "\n");









	}


}