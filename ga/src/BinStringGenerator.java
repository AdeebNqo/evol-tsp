class BinStringGenerator{
	int length;
	int upperbound;
	int current  = 0;
	public BinStringGenerator(int len){
		this.length = len;
		upperbound = Math.pow(2,len);
	}
	public String next(){
		if (current<upperbound){
			String encoding = Integer.toBinaryString(current);
			++current;
			(encoding.length()==length) ? encoding : pad(encoding);
		}
		else{
			throw Exception("Cannot generate binary string. Reached upper bound.");
		}
	}
	private String pad(String encoding){
		int len = encoding.length();
		int numzeros = length-len;
		for (int i=0; i<numzeros; ++i){
			"0"+encoding
		}
		return encoding;
	}
}
