package kwyjino.tokenizer;

public class MultToken implements Token {
	@Override
    public boolean equals(final Object other) {
        return other instanceof MultToken;
    }

    @Override
    public int hashCode() {
        return 17;
    }
    
    @Override
	public String toString() {
		return "MultToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}