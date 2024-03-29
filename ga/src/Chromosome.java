import java.math.BigInteger;
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
		
	Cache of the cities

	*/
	City[] cities;
	Chromosome(City[] cities) {
		this.cities = cities;
		cityList = new int[cities.length];
		for (int i=0; i<cities.length; ++i){
			cityList[i] = i;
		}		
		//Knuth shuffle
		for (int i=cities.length-1; i>=0; --i){
			int j = Util.getRandomIndex(0, i);
			int tmp  = cityList[j];
			cityList[j] = cityList[i];
			cityList[i] = tmp;
		}
		calculateCost();
	}
	Chromosome(int[] cityList) {
		this.cityList = cityList;
	}
	Chromosome(Chromosome original) {
		this.cityList = original.cityList;
		this.cities = original.cities;
		//calculateCost(cities);
	}
	
	/**
	 * Calculate the cost of the default list of cities.
	 * 
	 */
	void calculateCost() {
		cost = 0;
		for (int i = 0; i < cityList.length - 1; i++) {
			double dist = cities[cityList[i]]
					.proximity(cities[cityList[i + 1]]);
			cost += dist;
		}
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
	double mutationRate = 0.01;
	int mutate(Mutation mode) {
		switch(mode){
			case NormalRandom:{
				for (int i=0; i<cityList.length; ++i){
					if (Math.random() < mutationRate){
						int indexone = Util.getRandomIndex(0, cityList.length-1);
						int temp = cityList[indexone];
						cityList[indexone] = cityList[i];
						cityList[i] = temp;
					}
				}
				break;
			}
			case RandomOnlyImproving:{
				double cacheCost = this.cost;
				for (int i=0; i<cityList.length; ++i){
					if (Math.random() < mutationRate){
						int indexone = Util.getRandomIndex(0, cityList.length-1);
						int temp = cityList[indexone];
						cityList[indexone] = cityList[i];
						cityList[i] = temp;

						if (getCost() < cacheCost){
							//rearrange to original
							int temp2 = cityList[indexone];
							cityList[indexone] = cityList[i];
							cityList[i] = temp2;
						}
					}
				}
			}
		}
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

	Method for printing out a chromosome
	
	*/
	public void display(){
		for (int cityid: cityList){
			System.out.print(cityid+"->");
		}
		System.out.println();
	}
}
