package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import advent.seven.*;

public class Seven {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		Map<String, Wire> wires = new HashMap<String, Wire>();
		Wire nullWire = Wire.getNullWire();		
		wires.put("null", nullWire);
		
		while((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			String split[] = line.split(" -> ");
			String left = split[0];
			Wire rwire = getWireOrCreate(wires, split[1]);
			if (left.contains(" ")) { // GATE
				String leftSplit[] = left.split(" ");
				Wire lrwire = null;
				Wire llwire = null;
				if (leftSplit.length == 3) {
					llwire = getWireOrCreate(wires, leftSplit[0]);
					lrwire = getWireOrCreate(wires, leftSplit[2]);
					Operation op = Operation.valueOf(leftSplit[1]);
					new BinaryGate(llwire, lrwire, rwire, op);
				}
				if (leftSplit.length == 2) {
					lrwire = getWireOrCreate(wires, leftSplit[1]);
					Operation op = Operation.valueOf(leftSplit[0]);
					new BinaryGate(nullWire, lrwire, rwire, op);					
				}						
			}
			else if (left.matches("^[0-9]+$")) { // SIGNAL
				Signal sig = new Signal(Integer.parseInt(left));
				rwire.setSignal(sig);				
			}
			else if (left.matches("^[a-z]+$")) { // WIRE TO WIRE
				Wire lwire = getWireOrCreate(wires, left);
				new BinaryGate(nullWire, lwire, rwire, Operation.IDENTITY);
			}
			else {
				throw new RuntimeException("could not parse line: " + line);				
			}
		}
		
		Wire a = getWireOrCreate(wires, "a");		
		Signal valueA = a.getOutput(new HashMap<String, Signal>());
		System.out.println("First Part: " + valueA);
		
		int aValue = valueA.getValue();
		Wire b = getWireOrCreate(wires, "b");
		b.setSignal(new Signal(aValue));		
		System.out.println("Second Part: " + a.getOutput(new HashMap<String, Signal>()).toString());
	}
	
	public static Wire getWireOrCreate(Map<String, Wire> wires, String name) {
		if (!wires.containsKey(name)) {
			Wire wire = new Wire(name);
			if (name.matches("[0-9]+")) {
				Signal sig = new Signal(Integer.parseInt(name));
				wire.setSignal(sig);
			}
			wires.put(name, wire);
		}
		return wires.get(name);		
	}
}
