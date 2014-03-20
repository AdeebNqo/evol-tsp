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
	public Chromosome[] getSurvivors(SurvivorSelection mode){
		Chromosome[] tmp = new Chromosome[populationSize];
		switch(mode){
			case RouletteWheel:
				System.out.println("RouletteWheel chosen");
				break;
		}
		return null;
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
