//Stephen McGovern
//11-29-2019



public class cpu {
	

	static int MAR; //Holds index of current memoryAddress value
	static int MDR; //Two way register to hold operand/instruction value
	static int programCounter;
	static int CIR;
	static int accumulator;
    static int programCounterCtr = 0;

	static Instruction[] memoryAddress = new Instruction[256];

	public static void main(String[] args) {

	}


	public static void fetch(ProcessControlBlock PCB){

		//Program Counter holds instruction address (array index)
		programCounter = PCB.getInstructionPtr() + PCB.getInstructionCtr();

		//Memory Address Register copies that same address (array index)
		MAR = programCounter;

		//Data pointed to by array index is copied into Memory Data Register (array index value)
		MDR = ProcessMain.getMemory(MAR);

		//Data held in MDR is copied to Current Instruction Register (CIR) (array index value)
		CIR = MDR;

		//Increment PC by 1 to get next address loaded
		PCB.incrementInstructionCtr();

		System.out.println("Fetch ran: PC = " + programCounter + " PCB InstructionCtr::" + PCB.getInstructionCtr() + " PCB InstructionPtr::" + PCB.getInstructionPtr());
		System.out.println("MAR = " + MAR );

	}



	public static void execute(Instruction currentInstruction){

		String instruction = currentInstruction.instruction;
		int operand1 = currentInstruction.operand1;

		if (instruction == "LOAD"){
			LOAD(operand1);
		}

		if (instruction == "ADD"){
			ADD(operand1);
		}

		if (instruction == "SUB"){
			SUB(operand1);
		}

		if (instruction == "STORE"){
			STORE(operand1);
		}

		System.out.println("Execution ran: instruction = " + instruction + operand1);

	}
	public static void ADD(int operand){
		accumulator += operand;
	}
	public static void SUB(int operand){
		accumulator -= operand;
	}
	public static void STORE(int operand){
		MDR = accumulator;
		ProcessMain.setMemory(operand, MDR);
	}
	public static void LOAD(int operand){
		accumulator = operand;
	}


}


