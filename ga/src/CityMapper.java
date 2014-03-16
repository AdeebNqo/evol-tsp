import java.util.HashMap;
class CityMapper{
	HashMap<String,Integer> reference;
	City[] cities;	
	public CityMapper(City[] cities, String[] encoding){
		this.cities = cities;
		//caching the key-city mappings
		reference = new HashMap<String, Integer>();
		for (int i=0; i<cities.length; ++i){		
			reference.put(encoding[i],i);
		}
	}
	public int getCity(String encoding){
		return reference.get(encoding);
	}
}
