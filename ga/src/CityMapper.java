class CityMapper{
	HashMap<String,int> reference;
	City[] cities = new City[];	
	public CityMapper(City[] cities, String[] encoding){
		this.cities = cities;
		//caching the key-city mappings
		reference = new HashMap<String, int>();
		for (int i=0; i<cities.length; ++i){		
			reference.put(encoding[i],i);
		}
	}
	public int getCity(String encoding){
		return reference.get(encoding);
	}
}
