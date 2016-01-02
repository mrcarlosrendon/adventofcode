package advent.seven;

public class Signal {		
	private final String value;
	private final String intValue;
	public boolean set;
	
	public Signal(int val) {
		String binValue = Integer.toBinaryString(val);
		value = padding(binValue) + binValue;
		intValue = "" + Integer.parseInt(value, 2);
		set = true;
	}
	
	public Signal(String val) {
		if (val.length() > 16) {
			throw new RuntimeException("Bad length: " + val.length());
		}
		if (!val.matches("[01]+")) {
			throw new RuntimeException("Bad characters in signal: " + val);
		}
		value = padding(val) + val;
		intValue = "" + Integer.parseInt(value, 2);
		set = true;
	}
	
	private String padding(String val) {
		int zerosToPad = 16-val.length();
		String padding = "";
		for(int i=0; i<zerosToPad; i++) {
			padding += "0";
		}
		return padding;
	}
	
	private Signal() {
		value = null;
		intValue = "null";
		set = false;
	}
	
	private static Signal NullSignal = null;
	
	public static Signal getNullSignal() {
		if (NullSignal == null) {
			NullSignal = new Signal();
		}
		return NullSignal;
	}

	@Override
	public String toString() {
		return "Signal [value=" + value + ", intValue=" + intValue + "]";
	}

	public static Signal AND(Signal a, Signal b) {
		String output = "";
		for (int i=0; i<a.value.length(); i++) {
			if (a.value.charAt(i) == '1' && a.value.charAt(i) == b.value.charAt(i)) {
				output += "1";
			}
			else {
				output += "0";
			}
		}
		return new Signal(output);
	}
	
	public static Signal OR(Signal a, Signal b) {
		String output = "";
		for (int i=0; i<a.value.length(); i++) {
			if (a.value.charAt(i) == '1' || b.value.charAt(i) == '1') {
				output += "1";
			}
			else {
				output += "0";
			}
		}
		return new Signal(output);
	}
	
	// 010
	// 100
	public static Signal LSHIFT(Signal a, Signal b) {
		int amount = Integer.parseInt(b.intValue);
		String value = a.value.substring(amount, a.value.length());
		int toPad = 16 - value.length();
		for(int i=0; i < toPad; i++) {
			value += "0";
		}
		return new Signal(value);
	}
	
	// 010
	// 001
	public static Signal RSHIFT(Signal a, Signal b) {
		int amount = Integer.parseInt(b.intValue);
		return new Signal(a.value.substring(0, a.value.length() - amount));
	}
	
	public static Signal NOT(Signal a, Signal b) {
		String output = "";
		for (int i=0; i<b.value.length(); i++) {
			if (b.value.charAt(i) == '1') {
				output += "0";
			}
			else {
				output += "1";
			}
		}
		return new Signal(output);
	}
	
	public static Signal IDENTITY(Signal a, Signal b) {		
		return new Signal(Integer.parseInt(b.intValue));
	}
}