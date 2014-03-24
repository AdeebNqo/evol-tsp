import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
class PopulationPool{
	
	ArrayList<Chromosome> pop;
	int populationSize;
	
	int oldpopIndex  =0;
	public PopulationPool(int populationSize){
		this.populationSize = populationSize;
		pop = new ArrayList<Chromosome>();	
	}
	public void add(Chromosome chromo){
		pop.add(chromo);
	}
	public void add(Chromosome[] chromos){
		oldpopIndex = chromos.length;
		for (Chromosome item: chromos){
			add(item);
		}
	}
	public Chromosome[] getSurvivors(SurvivorSelection mode){
		Chromosome[] tmp = new Chromosome[populationSize];
		switch(mode){
			case Elitism:{
				Collections.sort(pop,new ChromosomeCompare());
				int popSize = pop.size();
				int j = 0;
				for (int i=popSize-1; j<=populationSize && i>=0; --i,++j){					
					tmp[j] = (Chromosome) pop.get(i);
				}
				return tmp;
			}
			case Children:{
				int currentSize = pop.size();
				int index = 0;
				for (int i=oldpopIndex; i<currentSize; ++i, ++index){
					tmp[index] = (Chromosome)pop.get(i);
				}
				return tmp;
			}
		}
		return null;
	}
	public Chromosome[] getParents(ParentSelection method, double prob){
		Chromosome[] tmp = new Chromosome[2];		
		switch(method){
			case RouletteWheel: 
				double S = 0;
				for (Chromosome individual:this.pop){
					S+=individual.getCost();
				}
				for (int j=0; j<2; ++j){
					double r = Util.getRandomIndex(0,S);//n
					double s = 0;
					int i=0;
					for (; s<=r; ++i){
						s+=((Chromosome)pop.get(i)).getCost();
					}
					tmp[j] =  (Chromosome)pop.get(i);
				}
				return tmp;
			case Tournament:
				for (int i=0; i<2; ++i){
					double R = Math.random();
					//randomly picking two individuals from the populationSize
					int one = Util.getRandomIndex(0,populationSize-1);
					int two = Util.getRandomIndex(0,populationSize-1);
					if (R < prob){
						tmp[i] = (Chromosome) pop.get(one);
					}
					else{
						tmp[i] = (Chromosome) pop.get(two);
					}
				}
				return tmp;
			default:
				return null;
		}
	}
	class ChromosomeCompare implements Comparator<Chromosome>{
			/*
			Method for allowing comparisons between chromosomes

			*/
			public int compare(Chromosome one, Chromosome two){
				if (one.cost == two.cost){
					return 0;
				}
				else if (one.cost > two.cost){
					return 1;
				}
				else{
					return -1;
				}
			}
	}
}
