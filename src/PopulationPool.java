import java.util.ArrayList;
class PopulationPool{
	
	ArrayList<Chromosome> pop;
	public PopulationPool(){
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
		switch(mode){
			case Elitism:
				break;
			case Tornament:
				break;
		}
		return null;
	}
}
