package kwyjino.tokenizer;

public class PrintToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof PrintToken;
    }

    @Override
    public int hashCode() {
        return 14;
    }

    @Override
	public String toString() {
		return "PrintToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}}