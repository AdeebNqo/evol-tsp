class Util{
	public static crossover(Chromosome one, Chromosome two, Crossover mode){
		switch(mode){
			case OnePoint:
				int crossoverPoint = getRandomIndex(0,one.cityList.length);
								
				break;
		}
	}
	/* 
	 * Method for generating nums in a range
	 *
	 */
	public static int getRandomIndex(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
}
