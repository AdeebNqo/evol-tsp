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
	double eliteProportion = 0.1;
	public Chromosome[] getSurvivors(SurvivorSelection mode){
		Chromosome[] tmp = new Chromosome[populationSize];
		switch(mode){
			case Elitism:{
				//retrieving the elite group
				Collections.sort(pop,new ChromosomeCompare());				
				int eliteSize = (int) eliteProportion*populationSize;
				int end = pop.size()-eliteSize;
				int j=0;				
				for (int i=pop.size()-1; i>end; --i,++j){
					tmp[j] = pop.get(i);
					pop.remove(i);
				}
				//filling in left spots
				int leftspots = populationSize-eliteSize;
				for (int i=0; i<leftspots; ++i, ++j){
					int index = Util.getRandomIndex(0,pop.size()-1);
					tmp[j] = pop.get(index);
					pop.remove(index);
				}
				return tmp;
			}
			case Children:{
				int currentSize = pop.size();
				int index = 0;
				for (int i=oldpopIndex; i<currentSize; ++i, ++index){
					tmp[index] = pop.get(i);
				}
				return tmp;
			}
		}
		return null;
	}
	public Chromosome[] getParents(ParentSelection method){
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
					Chromosome curr = null;
					int size = pop.size();
					for (; s<=r && i<size; ++i){
						curr = pop.get(i);
						s+=curr.getCost();
					}
					tmp[j] =  curr;
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
