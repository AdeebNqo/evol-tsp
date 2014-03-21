import java.util.Arrays;
class Util{
	public static Chromosome crossover(Chromosome one, Chromosome two, Crossover mode){
		switch(mode){
			case OnePoint:{
				int crossoverPoint = getRandomIndex(0,one.cityList.length-1);
				int end = one.cityList.length-1;
				int[] newChromosome = new int[one.cityList.length];
				for (int index=0; index<one.cityList.length; ++index){
					newChromosome[index] = -1;
				}
				int a=0;				
				for (; a<=crossoverPoint; ++a){
					newChromosome[a] = one.cityList[a];
				}
				if (a<one.cityList.length){
					for (int j=0; j<=end; ++j){
						if (! Arrays.asList(newChromosome).contains(two.cityList[j])){
							newChromosome[a] = two.cityList[j];
							++a;
							if (!(a<one.cityList.length)){
								break;
							}
						}
					}
				}
				return new Chromosome(newChromosome);
				}
			case TwoPoint:{
				int firstcrossoverPoint = getRandomIndex(0,one.cityList.length-1);
				int secondcrossoverPoint = getRandomIndex(0,one.cityList.length-1);
				while(secondcrossoverPoint==firstcrossoverPoint){
					secondcrossoverPoint = getRandomIndex(0,one.cityList.length);
				}
				int min = firstcrossoverPoint > secondcrossoverPoint ? secondcrossoverPoint : firstcrossoverPoint;
				int max = firstcrossoverPoint < secondcrossoverPoint ? secondcrossoverPoint : firstcrossoverPoint;
				int[] newChromosome = new int[one.cityList.length];
				for (int index=0; index<one.cityList.length; ++index){
					newChromosome[index] = -1;
				}
				for (int i=0; i<=min; ++i){
					newChromosome[i] = one.cityList[i];
				}
				for (int i=max; i<one.cityList.length; ++i){
					newChromosome[i] = one.cityList[i];
				}
				//fill in mid section with cities from chromosome two
				int start = min+1;
				for (int i=0; i<one.cityList.length; ++i){
					if (!Arrays.asList(newChromosome).contains(two.cityList[i])){
						newChromosome[start] = two.cityList[i];
						++start;
						if (start>=max){
							break;
						}
					}
				}
				return new Chromosome(newChromosome);
				}
			case None:{
				return new Chromosome(one==null? two:one);
				}
		}
		return null;
	}
	/* 
	 * Method for generating nums in a range
	 *
	 */
	public static int getRandomIndex(int min, int max) {
	return min + (int) (Math.random() * ((max - min) + 1));
	}
	public static double getRandomIndex(double min, double max) {
		return min + (double) (Math.random() * ((max - min) + 1));
	}
}
