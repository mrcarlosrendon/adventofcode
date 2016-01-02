package advent.seven;

import java.lang.reflect.Method;

public class BinaryGate {
	final Wire input1;
	final Wire input2;
	final Wire output;
	final Operation op;
	
	public BinaryGate(Wire input1, Wire input2, Wire output, Operation op) {			
		this.input1 = input1;
		input1.addNotifier(this);
		this.input2 = input2;
		input2.addNotifier(this);
		this.output = output;
		this.op = op;
		setOutput();
	}
	
	public void setOutput() {
		if (output.set) {
			return;
		}
		System.out.println("DEBUG: Evaluating: " + this);
		if ((op.binary && input1.set && input2.set) 
				|| (!op.binary && input2.set)) {
			try {
				Method m = Signal.class.getMethod(op.toString(), Signal.class, Signal.class);
				Signal nullSignal = null; // for static reflection
				output.setSignal((Signal)m.invoke(nullSignal, input1.signal, input2.signal));
			} catch (Exception e) {
				throw new RuntimeException("bad reflection for op: " + op, e);
			}
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