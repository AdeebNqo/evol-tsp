class BinStringGenerator{
	int length;
	int upperbound;
	int current  = 0;
	public BinStringGenerator(int len){
		this.length = len;
		upperbound = (int)Math.pow(2,len);
	}
	public String next() throws Exception{
		if (current<upperbound){
			String encoding = Integer.toBinaryString(current);
			++current;
			if (encoding.length()==length)
				return encoding;
			else
				return pad(encoding);
		}
		else{
			throw new Exception("Cannot generate binary string. Reached upper bound.");
		}
	}
	private String pad(String encoding){
		int len = encoding.length();
		int numzeros = length-len;
		for (int i=0; i<numzeros; ++i){
			encoding = "0"+encoding;
		}
		return encoding;
	}
}
