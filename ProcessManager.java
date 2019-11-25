//Rob Alcorn
//11-21-2019

import java.util.ArrayList;

public class ProcessManager {
	
	//create and initialize variables
	static ArrayList<ProcessControlBlock> readyQueue = new ArrayList<ProcessControlBlock>();
	static boolean isTrue = true;
	static int processCount = 0;
	static int pid = 1;
	
	public static void main(String[] args) throws InterruptedException {
				
		//10 processes will exist at a time so create the first initial 10 processes
		for(int i = 0; i < 10; i++) {
			
			//create new process control block class 
			ProcessControlBlock pcb = new ProcessControlBlock();
			int temp = (int) (Math.random() * 10) + 1;	
			
			//set burst time, process id, and process state
			pcb.setID(pid);
			pcb.setBurst(temp);
			pcb.setState("ready");
			
			//add process control block to list of pcb objects
			readyQueue.add(pcb);
			System.out.println("Process ID: " + pcb.getID() + "\tProcess State: " + pcb.getState() + "\tBurst Time: " + pcb.getBurst());
			pid++;
			processCount++;
		}
		
		//will run indefinitely
		while(isTrue) {
			//if there are less than 10 processes in the system create another one
			if(processCount < 10) {
				ProcessControlBlock pcb = new ProcessControlBlock();
				int temp = (int) (Math.random() * 10) + 1;	
				
				pcb.setID(pid);
				pcb.setBurst(temp);
				pcb.setState("ready");
				
				readyQueue.add(pcb);
				System.out.println("Process ID: " + pcb.getID() + "\tProcess State: " + pcb.getState() + "\tBurst Time: " + pcb.getBurst());
				pid++;
				processCount++;
			}
			
			processCount--;
			Thread.sleep(1000);
		}
	}
}
