// written by bade0149

public class Random{


	private int p1, p2, m, seed, rNew, rOld;


	public Random(int p1, int p2, int m){

		this.p1 = p1;
		this.p2 = p2;
		this.m = m;
		this.seed = 0;
		this.rOld = this.seed;
	}

	//setters and getters

	public void setSeed(int seed){

		this.seed = seed;

	}

	public int getSeed(){

		return this.seed;

	}

	public int getMaximum(){

		return this.m;

	}

	// Random Methods

	public int random(){

		this.rNew = ((this.p1 * this.rOld) + this.p2) % this.m;

		this.rOld = this.rNew;

		return this.rNew;
	}

	public int randomInteger(int lower, int upper){

		int lowValue, upperValue;

		if (lower > upper){

			lowValue = upper;
			upperValue = lower;
		}
		else{

			lowValue = lower;
			upperValue = upper;
		}

		upperValue++;

		int randomValue = (random() % (upperValue - lowValue)) + lowValue;

		return randomValue;

	}


	public boolean randomBoolean(){

		int randomValue = randomInteger(0,1);

		if (randomValue == 0)
			return false;
		else
			return true;
	
	}

	/* returns a random double by first finding the range (upper - lower). Second calculating a 
		percenatge by dividing the integer returned by random() with M. Since M is always greater than random 
		this will produce a percentage. And finally multiplying that percentage with our range and adding our lowValue*/

	public double randomDouble(double lower, double upper){


		double lowValue, upperValue, randomValue;

		if (lower > upper){

			lowValue = upper;
			upperValue = lower;
		}
		else{

			lowValue = lower;
			upperValue = upper;
		}


		double range = upperValue - lowValue;

		double value = ((double)random())/this.m;

		return (range * value) + lowValue;


	}

	// MAIN FUNCTION

	public static void main(String[] args){


		Random r1 = new Random(7919,65537,102611);


		System.out.println("Our current seed value is: " + r1.getSeed() + "\n");

		System.out.println("Lets change our seed to 23" + "\n");

		r1.setSeed(23);

		System.out.println("Our current seed value is: " + r1.getSeed() + "\n");


		System.out.println("\n" + "Testing random()....");
		
		for(int x = 0; x < 20; x++){

			System.out.println("A random number less than our modulus (102611) is: " + r1.random());

			x++;

		}

		System.out.println("\n" + "Testing randomInteger()...");


		for(int x = 0; x < 20; x++){

			System.out.println("A random number between 10 and 25 is: " + r1.randomInteger(10,25));


		}

		System.out.println("\n" + "Testing randomBoolean()....");

		for(int x = 0; x < 20; x++){

			System.out.println(r1.randomBoolean());


		}

		System.out.println("\n" + "Testing randomDouble()....");

		for (int x = 0; x < 20; x++){

			System.out.println("A random double between 1.5 and 70.6 is: " + r1.randomDouble(1.5, 70.6));

		}




	}

}


