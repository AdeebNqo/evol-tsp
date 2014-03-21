class Util{
	public static crossover(Chromosome one, Chromosome two, Crossover mode){
		switch(mode){
			case OnePoint:
				int crossoverPoint = getRandomIndex(0,one.cityList.length);
				int end = one.cityList.length-1;
				for (int i=crossoverPoint; i<=end; ++i){
					int tmp = one.cityList[i];
					one.cityList[i] = two.cityList[i];
					two.cityList[i] = tmp;
				}
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
