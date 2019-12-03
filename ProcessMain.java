//Rob Alcorn
//12-2-2019

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;
import java.util.Random;

public class ProcessMain {
	
	static ArrayList<ProcessControlBlock> readyQueue = new ArrayList<ProcessControlBlock>();
	static LinkedList<Integer> avgWait = new LinkedList<Integer>();
	static LinkedList<Integer> avgTurn = new LinkedList<Integer>();	
    static ArrayList<Instruction> memoryList = new ArrayList<Instruction>();
	static boolean isTrue = true;
	static int processCount = 0;
	static int pid = 1;
	static int time = 0;
	static int quantum = 1;
	static int index = 0;
	static int averageWait = 0;
	static int averageTurnaround = 0;
	static int[] operandArray = new Random().ints(100, 0, 100).toArray();
	static int totalInstruction = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		//create gui
		JFrame frame = new JFrame("Process Manager");
		JPanel mainPanel = new JPanel();
		JPanel timePanel = new JPanel();
		JPanel avgPanel = new JPanel();
		JPanel procPanel = new JPanel();
		JPanel avgNoPanel = new JPanel();
		mainPanel.add(timePanel);
		mainPanel.add(avgPanel);
		mainPanel.add(procPanel);
		mainPanel.add(avgNoPanel);
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(1000,400);
	    frame.setVisible(true);
	    frame.setResizable(true);

		//will run indefinitely
		while(isTrue) {
			
			//add time to GUI
			JLabel timeLab = new JLabel("Time: " + time);
			timePanel.add(timeLab);
			timePanel.repaint();
			timePanel.revalidate();
			
			//add average wait and turnaround time to GUI
			JLabel avgLab = new JLabel("Average Waiting Time: " + averageWait + "   Average Turnaround Time: " + averageTurnaround);
			avgPanel.add(avgLab);
			avgPanel.repaint();
			avgPanel.revalidate();
			
			//add process count to GUI
			JLabel countLab = new JLabel("Number of Processes in the System: " + processCount);
			procPanel.add(countLab);
			procPanel.repaint();
			procPanel.revalidate();
			
			System.out.println("Time: " + time);
			
			//flip a coin to decide whether to add a new process to the ready queue
			int temp = (int) (Math.random() * 3) + 1;
			if( temp == 1 ) {
				//create new process control block class 
				ProcessControlBlock pcb = new ProcessControlBlock();
				int temp2 = (int) (Math.random() * 10) + 3;	
                int numInstruction = temp2;
                totalInstruction = totalInstruction + temp2;
                int ctrInstruction = 0;
						
				//set burst time, process id, and process state
				pcb.setID(pid);
				pcb.setBurst(temp2);
				pcb.setState("ready");
				pcb.setRemaining(temp2);
				pcb.setArrival(time);
                pcb.setInstructionPtr(totalInstruction);

                //Creating the randomized instructions of the form LOAD > {ADD/SUB} > STORE
                
                Instruction loadInstruction = new Instruction("LOAD", totalInstruction);

                memoryList.add(loadInstruction);

                for (int x = 0; x < totalInstruction - 2; x++){
                    int randBool = (int) (Math.random() * 3) + 1;
                    ctrInstruction++;

                    if (randBool == 1){
                        Instruction instructBlock = new Instruction("ADD", pcb.getInstructionPtr() + pcb.getInstructionCtr());
                        
                        memoryList.add(instructBlock);
                    }
                    else if (randBool == 2){
                        Instruction instructBlock = new Instruction("SUB", pcb.getInstructionPtr() + pcb.getInstructionCtr());
                        memoryList.add(instructBlock);
                    }
                    numInstruction--;
                    
                }

                Instruction storeInstruction = new Instruction("STORE", pcb.getInstructionPtr() + pcb.getInstructionCtr());
				memoryList.add(storeInstruction);
				
				
				System.out.println("Total instructions: " +totalInstruction);
						
				//add process control block to list of pcb objects
				readyQueue.add(pcb);
				System.out.println("Created new process with Process ID: " + pcb.getID() + "\tand Burst Time: " + pcb.getBurst());
				pid++;
				processCount++;
				
			}
			
			//if the index gets higher than the ready queue size then reset it
			if(index == readyQueue.size() || index > readyQueue.size()) {
				index = 0;
			}
			
			//if there are processes in the queue then begin executing
			if(readyQueue.isEmpty() == false) {
				
				//display processes on gui
				
				ProcessControlBlock tempProc = readyQueue.get(index);
				int tempTime = tempProc.getRemaining() - 1;
				int tempID = readyQueue.get(index).getID();
				tempProc.setRemaining(tempTime);
				System.out.println("Process " + tempID + " has time remaining: " + tempTime);
				
				
				if(tempProc.getRemaining() == 0) {
					System.out.println("Process " + tempID + " completed at time " + time);
					int temp1 = time - readyQueue.get(index).getArrival();
					int temp2 = temp1 - readyQueue.get(index).getBurst() + 1;
					avgWait.add(temp2);
					avgTurn.add(temp1);
					readyQueue.remove(index);
					processCount--;
				}
				else{
				//Running instructions
				cpu.fetch(tempProc);
				cpu.execute(memoryList.get(index));
				}

				index++;				
			}
			
			if( avgWait.size() > 0 ) {
				averageWait = (sum(avgWait)) / avgWait.size();
				averageTurnaround = (sum(avgTurn)) / avgWait.size();
				System.out.println("Average Wait Time: " + averageWait + "   Average Turnaround Time: " + averageTurnaround);				
			}	
			Thread.sleep(1000);
			time++;
			
			timePanel.remove(timeLab);
			avgPanel.remove(avgLab);
			procPanel.remove(countLab);
		}
	}
	
	public static int sum(LinkedList<Integer> list) {
		int sum = 0;
		for (int i: list) {
				sum += i;
		}
		return sum;
	}	
	
	public static void setMemory(int address, int operand){
		operandArray[address] = operand;
	}

	public static int getMemory(int address){
		return operandArray[address];
	}
}