package advent.seven;

import java.util.ArrayList;
import java.util.List;

public class Wire {
	public final String name;
	public boolean set;
	public Signal signal;
	
	private List<BinaryGate> notify = new ArrayList<BinaryGate>();
	
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
		System.out.println("DEBUG: setting " + name);
		
		this.signal = sig;
		if (sig != Signal.getNullSignal()) {
			this.set = true;
		}
		for (BinaryGate gate : notify) {			
			gate.setOutput();
		}
	}
	
	public void addNotifier(BinaryGate g) {
		if (this == NullWire 
				|| name.matches("^[0-9]+")) { // constant
			return;
		}
		notify.add(g);
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