//Rob Alcorn
//11-21-2019

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class ProcessManager {
	
	//create and initialize variables
	static ArrayList<ProcessControlBlock> readyQueue = new ArrayList<ProcessControlBlock>();
	static ArrayList<ProcessControlBlock> history = new ArrayList<ProcessControlBlock>();
	static boolean isTrue = true;
	static int processCount = 0;
	static int pid = 1;
	static int time = 0;
	static int quantum = 1;
	static int index = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		//create a new GUI to display process information
		JFrame frame = new JFrame("Process Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(1000,400);
	    frame.setVisible(true);
	    			
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
		
		//add processes to GUI
		for(int i = 0; i < 10; i++) {
			
		}
		
		//will run indefinitely
		while(isTrue) {
			//display current time
			System.out.println("Time: " + time);
									
			//if there are less than 10 processes in the system create another one
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
			
			//if 10 process have run then reset the index
			if(index == 10) {
				index = 0;
			}
			
			//set process states
			for(int i = 0; i < 10; i++) {
				if( i == index ) {
					readyQueue.get(i).setState("running");
				}
				else {
					readyQueue.get(i).setState("waiting");
				}
			}
			
			//round robin
			int timeLeft = readyQueue.get(index).getRemaining();
			
			//process completes
			if( timeLeft == quantum ) {	
				System.out.println("Process " + readyQueue.get(index).getID() + " completed at time " + (time + 1));
				int temp = (time+1) - readyQueue.get(index).getArrival();
				int temp2 = temp - readyQueue.get(index).getBurst();
				
				//remove the process from the ready queue and update all info in its pcb
				readyQueue.get(index).setCompletion(time+1);
				readyQueue.get(index).setWait(temp2);
				readyQueue.get(index).setTurnaround(temp);
				readyQueue.get(index).setState("terminated");				
				history.add(readyQueue.get(index));
				readyQueue.remove(index);
				processCount--;
				time++;
				Thread.sleep(1000);
			}
			//otherwise process isn't done yet 
			else if( timeLeft > quantum ) {
				readyQueue.get(index).setRemaining( timeLeft -1);
				System.out.println("Process " + readyQueue.get(index).getID() + " has " + readyQueue.get(index).getRemaining() + " time remaining");
				time++;
				index++;
				Thread.sleep(1000);
			}		
		}
	}
}
