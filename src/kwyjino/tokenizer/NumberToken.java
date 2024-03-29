package kwyjino.tokenizer;

public class NumberToken implements Token {
    public final int value;

    public NumberToken(final int value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof NumberToken &&
                value == ((NumberToken)other).value);
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
	public String toString() {
		return "NumberToken [value=" + value + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		output=output+" "+value;
		return output;
	}
}