// written by bade0149

public class Roots{


	private float a, b, c;
	private float discriminant;
	private Complex4 positiveRoot;
	private Complex4 negativeRoot;


	public Roots(float a, float b, float c){

		this.a = a;
		this.b = b;
		this.c = c;

		discriminant = (float) Math.pow(this.b,2) - (4*this.a*this.c);


		if (a == 0)

			System.out.println("Cannot compute since a = 0. You cannot divide by zero");

		else if (discriminant < 0){

			System.out.println("There are no real roots! Here are the Complex roots!");

			this.positiveRoot = new Complex4(-this.b/(2 * this.a), discriminant/(2 * this.a));
			this.negativeRoot = new Complex4(-this.b/(2 * this.a), -discriminant/(2 * this.a));


		}

		else{

			System.out.println("Here are the two real roots!");

			this.positiveRoot = new Complex4((-this.b + Math.sqrt(discriminant))/ (2 * this.a),0);
			this.negativeRoot = new Complex4((-this.b - Math.sqrt(discriminant))/ (2 * this.a),0);
		}

	}

	public String toString() {

			String str = "Positive Root: " + this.positiveRoot + "\n" + "Negative Root: " + this.negativeRoot + "\n";

			return str;


		}


}



	



