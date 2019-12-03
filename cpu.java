//Stephen McGovern
//11-29-2019

//import java.util.Stack;


public class cpu {
	

	static int MAR; //Holds index of current memoryAddress value
	static int MDR; //Two way register to hold operand/instruction value
	static int programCounter;
	static int CIR;
	static int accumulator;
    static int programCounterCtr = 0;

	//static Stack<int> SystemCallStack = new Stack<>();
	static Instruction[] memoryAddress = new Instruction[256];

	public static void main(String[] args) {

		
		


	}


	public static void fetch(ProcessControlBlock PCB){

		//Program Counter holds instruction address (array index)
		programCounter = PCB.getInstructionPtr() + programCounterCtr;

		//Memory Address Register copies that same address (array index)
		MAR = programCounter;

		//Data pointed to by array index is copied into Memory Data Register (array index value)
		MDR =ProcessMain.getMemory(MAR);

		//Data held in MDR is copied to Current Instruction Register (CIR) (array index value)
		CIR = MDR;

		//Increment PC by 1 to get next address loaded
		programCounterCtr++;

	}



	public static void execute(Instruction currentInstruction){

		//List of operands we need to handle 
		//Data Movement - LOAD / STORE / MOVE
		//Arithmetic - ADD / SUB / AND / XOR / SHIFT

		// ? Questionable ones ?
		//Branch - JUMP / JZ / JNZ - Alters Program Counter -- Could Implement
		//Procedure Call - CALL / RETURN -- Very Difficult
		//Other - NOP (do nothing) / EI (enable interrupt) -- Could implement
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

	}

    // public static void interrupt(){
    //     //Step 1 - Suspend Execute


    //     //Step 2 - Push MAR value onto system call stack
    //     SystemCallStack.push(MAR.toString(int));

    //     //Step 3 - Load Program Counter with address of
    //     //the current interrupt handler
    //     //This step needs to be implemented in a different way


    //     MAR =  SystemCallStack.pop();


    // }

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


