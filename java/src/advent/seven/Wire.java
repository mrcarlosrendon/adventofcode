package advent.seven;

import java.util.Map;

public class Wire {
	public final String name;
	public boolean set;
	public Signal signal;	
	private BinaryGate valueComputer;
	
	public Wire(String name) {			
		this.name = name;
		this.signal = null;
		this.set = false;
	}
	
	private Wire() {
		this.name = "null";
		this.signal = null;
		this.set = false;
	}
	
	private static Wire NullWire = null;
	
	public static Wire getNullWire() {
		if (NullWire == null) {
			NullWire = new Wire();
		}
		return NullWire;
	}
		
	public void setSignal(Signal sig) {
		this.signal = sig;
		if (sig != Signal.getNullSignal()) {
			this.set = true;
		}		
	}	

	public void addValueComputer(BinaryGate binaryGate) {
		this.valueComputer = binaryGate;
	}	
	
	public boolean isConstant() {
		return this == NullWire || name.matches("^[0-9]+");
	}
		
	public Signal getOutput(Map<String, Signal> memoized) {
		if (memoized.containsKey(name)) {
			return memoized.get(name);
		}
		if (isConstant() || this.valueComputer == null) {			
			memoized.put(name, signal);
			return signal;
		}
		Signal signal = this.valueComputer.compute(memoized);		
		memoized.put(name, signal);
		return signal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wire other = (Wire) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Wire [name=" + name + ", set=" + set + ", signal=" + signal + "]";
	}
}