import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
class PopulationPool{
	
	ArrayList<Chromosome> pop;
	int populationSize;
	public PopulationPool(int populationSize){
		this.populationSize = populationSize;
		pop = new ArrayList<Chromosome>();	
	}
	public void add(Chromosome chromo){
		pop.add(chromo);
	}
	public void add(Chromosome[] chromos){
		for (Chromosome item: chromos){
			add(item);
		}
	}
	public double getRandomIndex(double min, double max) {
		return min + (double) (Math.random() * ((max - min) + 1));
	}
	public Chromosome[] getSurvivors(SurvivorSelection mode){
		Chromosome[] tmp = new Chromosome[populationSize];
		switch(mode){
			case Elitism:
				Collections.sort(pop,new ChromosomeCompare());
				int popSize = pop.size();
				int j = 0;
				for (int i=popSize-1; j<=99; --i,++j){
					tmp[j] = (Chromosome) pop.get(i);
				}
				return tmp;
		}
		return null;
	}
	public Chromosome[] getParents(ParentSelection method, int numParents){
		switch(method){
			case RouletteWheel:
				System.out.println("hey");
				/*double S = 0;
				for (Chromosome individual:this.pop){
					S+=individual.getCost();
				}
				double r = getRandomIndex(0,S);
				double s = 0;
				for (int i=0; s<=r; ++i){
					tmp[i] = (Chromosome)pop.get(i);
				}
				return tmp;/*
				break;
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
