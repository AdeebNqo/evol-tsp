class Chromosome{
	/**
	 * The list of cities, which are the genes of this chromosome.
	 */
	protected int[] cityList;

	/**
	 * The cost of following the cityList order of this chromosome.
	 */
	protected double cost;
	
	/*
	
	CityMapper

	*/
	CityMapper citymapper;

	Chromosome(City[] cities, CityMapper citymapper) {
		this.citymapper = citymapper;	
	}
	public Chromosome(Chromosome someChromo){
		this.cost = someChromo.cost;
		this.cityList = someChromo.cityList;
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
	 * mate: mutation method
	 */

	int mate() {
		transpose();
		return 0;
	}
	/*
	
	mutation:
	Transposition
	
	*/
	private void transpose(){
		int indexone = getRandomIndex(0, cityList.length-1);
		int indextwo = getRandomIndex(0, cityList.length-1);
		int temp = cityList[indexone];
		cityList[indexone] = cityList[indextwo];
		cityList[indextwo] = temp;
	}
	/*
	mutation:
	Inversion
	*/
	private void inversion(){

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
