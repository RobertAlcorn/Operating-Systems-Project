//Rob Alcorn
//12-2-2019

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProcessManagement {
	
	static ArrayList<ProcessControlBlock> readyQueue = new ArrayList<ProcessControlBlock>();	
	static LinkedList<Integer> avgWait = new LinkedList<Integer>();
	static LinkedList<Integer> avgTurn = new LinkedList<Integer>();
	static boolean isTrue = true;
	static int processCount = 0;
	static int pid = 1;
	static int time = 0;
	static int quantum = 1;
	static int index = 0;
	static int averageWait = 0;
	static int averageTurnaround = 0;

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
			pcb.setRemaining(temp);
			pcb.setArrival(time);
					
			//add process control block to list of pcb objects
			readyQueue.add(pcb);
			System.out.println("Created new process with Process ID: " + pcb.getID() + "\tand Burst Time: " + pcb.getBurst());
			pid++;
			processCount++;
		}
			
		//will run indefinitely
		while(isTrue) {
				
			//display current time
			System.out.println("Time: " + time);
				
			if(processCount < 10) {
				ProcessControlBlock pcb = new ProcessControlBlock();
				int temp = (int) (Math.random() * 10) + 1;	
					
				pcb.setID(pid);
				pcb.setBurst(temp);
				pcb.setState("ready");
				pcb.setRemaining(temp);
				pcb.setArrival(time);
					
				readyQueue.add(pcb);
				System.out.println("Created new process with Process ID: " + pcb.getID() + "\tand Burst Time: " + pcb.getBurst());
											
				pid++;
				processCount++;
			}
				
			//if index hits 10 then reset
			if(index == 10) {
				index = 0;
			}
				
			//round robin
			int timeLeft = readyQueue.get(index).getRemaining();
			
			//process completes
			if( timeLeft == quantum ) {	
				
				System.out.println("Process " + readyQueue.get(index).getID() + " completed at time " + (time + 1));
				int temp = (time+1) - readyQueue.get(index).getArrival();
				int temp2 = temp - readyQueue.get(index).getBurst();
				avgWait.add(temp2);
				avgTurn.add(temp);
				
				//remove the process from the ready queue and update all info in its pcb
				readyQueue.get(index).setCompletion(time+1);
				readyQueue.get(index).setWait(temp2);
				readyQueue.get(index).setTurnaround(temp);
				readyQueue.get(index).setState("terminated");				
				readyQueue.remove(index);
				processCount--;
				time++;
				Thread.sleep(1500);
			}
			//otherwise process isn't done yet 
			else if( timeLeft > quantum ) {
				
				readyQueue.get(index).setRemaining( timeLeft -1);
				System.out.println("Process " + readyQueue.get(index).getID() + " has " + readyQueue.get(index).getRemaining() + " time remaining");
				String temp = Integer.toString(readyQueue.get(index).getID());
				String temp2 = readyQueue.get(index).getState();
				int temp3 = readyQueue.get(index).getRemaining();
				time++;
				index++;
				Thread.sleep(1500);
			}
		}			
	}		
}

