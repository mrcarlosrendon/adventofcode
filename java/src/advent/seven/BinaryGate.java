package advent.seven;

import java.lang.reflect.Method;
import java.util.Map;

public class BinaryGate {
	final Wire input1;
	final Wire input2;
	final Wire output;
	final Operation op;
	
	public BinaryGate(Wire input1, Wire input2, Wire output, Operation op) {			
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
		output.addValueComputer(this);
		this.op = op;	
	}	
	public Signal compute(Map<String, Signal> memoized) {	
		Signal a = input1.getOutput(memoized);
		Signal b = input2.getOutput(memoized);
		
		try {
			Method m = Signal.class.getMethod(op.toString(), Signal.class, Signal.class);
			Signal nullSignal = null; // for static reflection
			return (Signal)m.invoke(nullSignal, a, b);
		} catch (Exception e) {
			throw new RuntimeException("bad reflection for op: " + op, e);
		}
	}

	@Override
	public String toString() {
		if (op.binary) {
			return input1.name + " " + op + " " + input2.name + " -> " + output.name;
		}
		else {
			return op + " " + input2.name + " -> " + output.name;
		}
	}		
}