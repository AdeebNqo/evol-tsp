import java.util.Arrays;
class Util{
	public static void crossover(Chromosome one, Chromosome two, Crossover mode){
		switch(mode){
			case OnePoint:
				int crossoverPoint = getRandomIndex(0,one.cityList.length-1);
				int end = one.cityList.length-1;
				int[] newChromosome = new int[one.cityList.length];
				int a=0;				
				for (; a<=crossoverPoint; ++a){
					newChromosome[a] = one.cityList[a];
				}
				for (int j=0; j<=end; ++j){
					if (!Arrays.asList(newChromosome).contains(two.cityList[j])){
						newChromosome[a] = two.cityList[j];
						++a;
					}
				}
				break;
			case TwoPoint:
				int firstcrossoverPoint = getRandomIndex(0,one.cityList.length-1);
				int secondcrossoverPoint = getRandomIndex(0,one.cityList.length-1);
				while(secondcrossoverPoint==firstcrossoverPoint){
					secondcrossoverPoint = getRandomIndex(0,one.cityList.length);
				}
				int min = firstcrossoverPoint > secondcrossoverPoint ? secondcrossoverPoint : firstcrossoverPoint;
				int max = firstcrossoverPoint < secondcrossoverPoint ? secondcrossoverPoint : firstcrossoverPoint;
				for (int i=0; i<=min; ++i){
					int tmp = one.cityList[i];
					one.cityList[i] = two.cityList[i];
					two.cityList[i] = tmp;
				}
				for (int i=min; i<=max; ++i){
					int tmp = one.cityList[i];
					one.cityList[i] = two.cityList[i];
					two.cityList[i] = tmp;
				}
				for (int i=max; i<=one.cityList.length-1; ++i){
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
