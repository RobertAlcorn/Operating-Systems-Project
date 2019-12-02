//Stephen McGovern
//11-29-2019
import java.lang.String;

public class CPU {

	public static void main(String[] args) {

		int MAR;
		int MDR;
		int ProgramCounter;
		int CIR;
		int accumulator;

		int memoryAddress[];
		
		int i = 0;

		PCBTest = new ProcessControlBlock();

		fetch()

	}

	public static setInstuction(string operand, int x, int y){
		memoryAddress[i] = operand + " " + x + " " + y;
		i++;
	}

	//Need to increment j each time this is called
	public static getInstruction(){

		return memoryAddress[j];
	}

	public static fetch(ProcessControlBlock PCB){

		//Program Counter holds instruction address (array index)
		ProgramCounter = PCB.getInstruction();

		//Memory Address Register copies that same address (array index)
		MAR = ProgramCounter;

		//Data pointed to by array index is copied into Memory Data Register (array index value)
		MAR = MDR; 

		//Data held in MDR is copied to Current Instruction Register (CIR) (array index value)
		CIR = MDR;

	}



	public static execute(string operand, string instruction){

		//List of operands we need to handle 
		//Data Movement - LOAD / STORE / MOVE
		//Arithmetic - ADD / SUB / AND / XOR / SHIFT

		// ? Questionable ones ?
		//Branch - JUMP / JZ / JNZ - Alters Program Counter -- Could Implement
		//Procedure Call - CALL / RETURN -- Very Difficult
		//Other - NOP (do nothing) / EI (enable interrupt) -- Could implement

		if (operand == "LOAD"){
			accumulator = instruction;
			break;
		}

		if (operand == "ADD"){
			accumulator += instruction;
			break;
		}

		if (operand == "SUB"){
			accumulator -= instruction;
			break;
		}

		//This stores the value from MDR into X, where X is a space in memory pointed at by 'instruction', Equivalent to '='
		if (operand == "STORE"){
			MDR = accumulator;
			setMemory(x, MDR);
			break;
		}


	}


}
