//Stephen McGovern
//11-29-2019
import java.lang.String;
import java.util.stack;


public class CPU {

	public static void main(String[] args) {

		int MAR; //Holds index of current memoryAddress value
		int MDR; //Two way register to hold operand/instruction value
		int programCounter;
		int CIR;
		int accumulator;

        Stack<string> SystemCallStack = new Stack<>();
		Instruction memoryAddress[] = new Instruction[];
		
		int i = 0;

		PCBTest = new ProcessControlBlock();
		
		


	}

	public static setInstruction(string instruction, int x, int y){
		memoryAddress[i] = instruction + " " + x + " " + y;
		i++;
	}

	//Need to increment j each time this is called
	public static getInstruction(){
		
		return memoryAddress[j];
	}

	public static fetch(ProcessControlBlock PCB){

		//Program Counter holds instruction address (array index)
		ProgramCounter = PCB.getInstructionPointer();

		//Memory Address Register copies that same address (array index)
		MAR = ProgramCounter;

		//Data pointed to by array index is copied into Memory Data Register (array index value)
		MAR = MDR; 

		//Data held in MDR is copied to Current Instruction Register (CIR) (array index value)
		CIR = MDR;

	}



	public static execute(string instruction, int operand1, int operand2){

		//List of operands we need to handle 
		//Data Movement - LOAD / STORE / MOVE
		//Arithmetic - ADD / SUB / AND / XOR / SHIFT

		// ? Questionable ones ?
		//Branch - JUMP / JZ / JNZ - Alters Program Counter -- Could Implement
		//Procedure Call - CALL / RETURN -- Very Difficult
		//Other - NOP (do nothing) / EI (enable interrupt) -- Could implement

		if (operator == "LOAD"){
			LOAD(operand1);
		}

		if (operator == "ADD"){
			ADD(operand1);
		}

		if (operator == "SUB"){
			SUB(operand1);
		}

		// if (operator == "MUL"){
			
		// 	while (operand1 != 0){
		// 		accumulator += accumulator;
		// 		operand1--;
		// 	}
		// }

		//This stores the value from MDR into X, where X is a space in memory pointed at by 'instruction', Equivalent to '='
		if (operand == "STORE"){
			STORE(operand1);
		}

		if (operator == "MOVE"){
			MOVE(operand1,operand2);
		}

		//if (operand == "AND"){
		//
		//}

	}

    public static interrupt(){
        //Step 1 - Suspend Execute


        //Step 2 - Push MAR value onto system call stack
        SystemCallStack.push(MAR);

        //Step 3 - Load Program Counter with address of
        //the current interrupt handler
        //This step needs to be implemented in a different way


        MAR =  SystemCallStack.pop();


    }

	public static void ADD(int operand){
		accumulator += operand;
	}
	public static void SUB(int operand){
		accumulator -= operand;
	}
	public static void STORE(int operand){
		MDR = accumulator;
		setMemory(operand, MDR);
	}
	public static void LOAD(int operand){
		accumulator = operand;
	}
	public static void MOVE(int op1, op2){
		memoryAddress[op2] = op1; 
	}


}


