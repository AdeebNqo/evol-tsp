import java.util.ArrayList;
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
						if (a<one.cityList.length){						
							ArrayList<Integer> newchr = toArrayList(newChromosome);
							if (!newchr.contains(two.cityList[j])){
								newChromosome[a] = two.cityList[j];
								++a;
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
				if (start<max){
					for (int i=0; i<one.cityList.length; ++i){
						if (!toArrayList(newChromosome).contains(two.cityList[i])){
							newChromosome[start] = two.cityList[i];
							++start;
							if (start>=max){
								break;
							}
						}
					}
				}
				return new Chromosome(newChromosome);
				}
			case None:{
				return new Chromosome(Math.random() <= 0.5? two:one);
				}
			case Mix:{
				return crossover(one, two, one.cost > two.cost? Crossover.OnePoint: Crossover.TwoPoint);
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
	/*

	Method for checking if chromosome is valid

	*/
	public static boolean isValid(Chromosome chromo, City[] cities){
		final int  mzip = 99999;
		boolean[] bitmap = new boolean[mzip+1];
		for (int item: chromo.cityList){
			if (!(bitmap[item] ^= true)){
				return false;
			}
		}
		//no duplicates -- check if all cities appear
		for (int i=0; i<cities.length; ++i){		
			if (!toArrayList(chromo.cityList).contains(i)){
				System.out.println("missing cities");
				return false;
			}
		}
		return true;	
	}
	/*

	Array to arraylist

	*/
	public static ArrayList toArrayList(int[] values){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int val: values){
			list.add(val);
		}
		return list;
	}
}
