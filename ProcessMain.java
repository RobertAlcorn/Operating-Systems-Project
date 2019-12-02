//Rob Alcorn
//12-2-2019

import java.util.ArrayList;

public class ProcessMain {
	
	static ArrayList<ProcessControlBlock> readyQueue = new ArrayList<ProcessControlBlock>();
	static boolean isTrue = true;
	static int processCount = 0;
	static int pid = 1;
	static int time = 0;
	static int quantum = 1;
	static int index = 0;
	static int averageWait = 0;
	static int averageTurnaround = 0;
	
	public static void main(String[] args) throws InterruptedException {

		//will run indefinitely
		while(isTrue) {
			
			System.out.println("Time: " + time);
			
			//flip a coin to decide whether to add a new process to the ready queue
			int temp = (int) (Math.random() * 2) + 1;
			if( temp == 1 ) {
				//create new process control block class 
				ProcessControlBlock pcb = new ProcessControlBlock();
				int temp2 = (int) (Math.random() * 10) + 1;	
						
				//set burst time, process id, and process state
				pcb.setID(pid);
				pcb.setBurst(temp2);
				pcb.setState("ready");
				pcb.setRemaining(temp2);
				pcb.setArrival(time);
						
				//add process control block to list of pcb objects
				readyQueue.add(pcb);
				System.out.println("Created new process with Process ID: " + pcb.getID() + "\tand Burst Time: " + pcb.getBurst());
				pid++;
				processCount++;
			}
			
			if(index == readyQueue.size()) {
				index = 0;
			}
			
			//if there are processes in the queue then begin executing
			if(readyQueue.isEmpty() == false) {
				ProcessControlBlock tempProc = readyQueue.get(index);
				int tempTime = tempProc.getRemaining() - 1;
				int tempID = readyQueue.get(index).getID();
				tempProc.setRemaining(tempTime);
				System.out.println("Process " + tempID + " has time remaining: " + tempTime);
				
				if(tempProc.getRemaining() == 0) {
					System.out.println("Process " + tempID + " completed at time " + time);
					readyQueue.remove(index);
					processCount--;
				}
				index++;
				
			}
			
			Thread.sleep(1500);
			time++;
		}
	}
}