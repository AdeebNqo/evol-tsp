class Chromosome {
	/**
	 * The list of cities, which are the genes of this chromosome.
	 */
	protected int[] cityList;

	/**
	 * The cost of following the cityList order of this chromosome.
	 */
	protected double cost;

	/**
	 * @param cities
	 *            The order that this chromosome would visit the cities.
	 */
	int[][] path;
	/*
	 * 
	 * Reference for the city encoding
	 * 
	 * @params lenEncoding is the number of bits encoding a city in the path
	 * field
	 */
	int[] cityReference;
	/*
	 * Length of city encoding in a path
	 */
	int lenEncoding;

	Chromosome(int[] cities, int lenEncoding) {
		path = new int[lenEncoding][cities.length];
		this.cityReference = cities;
		this.lenEncoding = lenEncoding;
		// Generating random path using Knuth shuffle
		for (int i = cities.length - 1; i >= 0; --i) {
			int j = getRandomIndex(0, i);
			int tmp = cities[j];
			cities[j] = cities[i];
			cities[i] = tmp;
		}
		// encoding the path into one string
		String binEncoding = "";
		for (int city : cities) {
			binEncoding += Integer.toBinaryString(city);
		}
		System.out.println(binEncoding);
		System.out.println("This is a " + binEncoding.length() + " bits number");
	}

	/**
	 * Calculate the cost of the specified list of cities.
	 * 
	 * @param cities
	 *            A list of cities.
	 */
	void calculateCost(City[] cities) {
		cost = 0;
		for (int i = 0; i < cityList.length - 1; i++) {
			double dist = cities[cityList[i]]
					.proximity(cities[cityList[i + 1]]);
			cost += dist;
		}
	}

	/**
	 * Get the cost for this chromosome. This is the amount of distance that
	 * must be traveled.
	 */
	double getCost() {
		return cost;
	}

	/**
	 * @param i
	 *            The city you want.
	 * @return The ith city.
	 */
	int getCity(int i) {
		return cityList[i];
	}

	/**
	 * Set the order of cities that this chromosome would visit.
	 * 
	 * @param list
	 *            A list of cities.
	 */
	void setCities(int[] list) {
		for (int i = 0; i < cityList.length; i++) {
			cityList[i] = list[i];
		}
	}

	/**
	 * Set the index'th city in the city list.
	 * 
	 * @param index
	 *            The city index to change
	 * @param value
	 *            The city number to place into the index.
	 */
	void setCity(int index, int value) {
		cityList[index] = value;
	}

	/*
	 * 
	 * mate: Variation (cross-over and/or mutation method)
	 */

	int mate() {

		// TO DO
		return 0;
	}

	/**
	 * Sort the chromosomes by their cost.
	 * 
	 * @param chromosomes
	 *            An array of chromosomes to sort.
	 * @param num
	 *            How much of the chromosome list to sort.
	 */

	public static void sortChromosomes(Chromosome chromosomes[], int num) {
		Chromosome ctemp;
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 0; i < num - 1; i++) {
				if (chromosomes[i].getCost() > chromosomes[i + 1].getCost()) {
					ctemp = chromosomes[i];
					chromosomes[i] = chromosomes[i + 1];
					chromosomes[i + 1] = ctemp;
					swapped = true;
				}
			}
		}
	}

	/*
	 * 
	 * Method for generating nums in a range
	 */
	public int getRandomIndex(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
}
