package advent.seven;

public enum Operation {
	AND(true), OR(true), LSHIFT(true), RSHIFT(true), 
	NOT(false), IDENTITY(false)	
	;	
		public final boolean binary; 
		private Operation(boolean binary) {
			this.binary = binary;
		}	
	}
