package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TwentyThree {
	
	enum OP { hlf, tpl, inc, jmp, jie, jio };	
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		List<Instruction> program = new ArrayList<Instruction>();
		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			String[] split = line.split(" ");
			String ins = split[0];
			String arg1 = split[1].split(",")[0];
			if(split.length == 2) {
				if (arg1.contains("a") || arg1.contains("b")) {
					program.add(new Instruction(OP.valueOf(ins), arg1, null));
				}
				else {
					program.add(new Instruction(OP.valueOf(ins), Integer.parseInt(arg1), null));
				}
			}
			else {
				String arg2 = split[2];
				program.add(new Instruction(OP.valueOf(ins), arg1, Integer.parseInt(arg2)));
			}
		}
		
		System.out.println(program);
		runProgram(program);
	}
	
	public static void runProgram(List<Instruction> instructions) {
		int programCounter = 0;
		int a = 0;
		int b = 0;
		
		while(true) {
			if (programCounter >= instructions.size() || programCounter < 0) {
				break;
			}
			Instruction instruction = instructions.get(programCounter);
			switch(instruction.operation) {
			case hlf:
				if (((String)instruction.arg1).equals("a")) {
					a = a / 2;
				}
				else {
					b = b / 2;
				}				
				break;
			case inc:
				if (((String)instruction.arg1).equals("a")) {
					a++;
				}
				else {
					b++;
				}				
				break;
			case jie:
				if (((String)instruction.arg1).equals("a")) {
					if (a % 2 == 0) {
						programCounter += (Integer)instruction.arg2 - 1;
					}					
				}
				else {
					if (b % 2 == 0) {
						programCounter += (Integer)instruction.arg2 - 1;
					}
				}
				break;
			case jio:
				if (((String)instruction.arg1).equals("a")) {
					if (a % 2 == 1) {
						programCounter += (Integer)instruction.arg2 - 1;
					}					
				}
				else {
					if (b % 2 == 1) {
						programCounter += (Integer)instruction.arg2 - 1;
					}
				}
				break;
			case jmp:
				programCounter = programCounter + (Integer)instruction.arg1 - 1;
				break;
			case tpl:
				if (((String)instruction.arg1).equals("a")) {
					a=a*3;
				}
				else {
					b=b*3;
				}
				break;
			default:
				throw new RuntimeException("Instruction did not decode " + instruction.operation);					
			}
			programCounter++;
			System.out.println(instruction);
			System.out.println(String.format("At instruction end a=%d b=%d pc=%d", a,b,programCounter));
		}
		System.out.println(String.format("At program end a=%d b=%d pc=%d", a,b,programCounter));
	}
	
	public static class Instruction {
		public final OP operation;
		public final Object arg1;
		public final Object arg2;
		public Instruction(OP operation, Object arg1, Object arg2) {
			this.operation = operation;
			this.arg1 = arg1;
			this.arg2 = arg2;
		}
		@Override
		public String toString() {
			return "Instruction [operation=" + operation + ", arg1=" + arg1 + ", arg2=" + arg2 + "]";
		}
	}
}
