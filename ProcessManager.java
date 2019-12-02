//Rob Alcorn
//11-21-2019

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class ProcessManager {
	
	//create and initialize variables
	static ArrayList<ProcessControlBlock> readyQueue = new ArrayList<ProcessControlBlock>();
	static ArrayList<ProcessControlBlock> history = new ArrayList<ProcessControlBlock>();
	static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	static ArrayList<JLabel> labels2 = new ArrayList<JLabel>();
	static boolean isTrue = true;
	static int processCount = 0;
	static int pid = 1;
	static int time = 0;
	static int quantum = 1;
	static int index = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		//create a new GUI to display process information
		JFrame frame = new JFrame("Process Manager");
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(1000,400);
	    frame.setVisible(true);
	    frame.setResizable(false);
	    			
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
		
		//display processes on GUI
		for(int i = 0; i < 10; i++) {			
			String temp = Integer.toString(readyQueue.get(i).getID());
			String temp2 = readyQueue.get(i).getState();
			int temp3 = readyQueue.get(i).getRemaining();
			JLabel label = new JLabel("Process: " + temp + "   State: " + temp2 + "   Time Remaining: " + temp3);
			panel.add(label);
			labels.add(label);

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
				
				String temp0 = Integer.toString(readyQueue.get(9).getID());
				String temp2 = readyQueue.get(9).getState();
				int temp3 = readyQueue.get(9).getRemaining();
				JLabel label = new JLabel("Process: " + temp0 + "   State: " + temp2 + "   Time Remaining: " + temp3);
				panel.add(label);			
				labels.add(label);
				panel.repaint();
				panel.revalidate();
				
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
//					labels.remove(i);
//					String temp = Integer.toString(readyQueue.get(i).getID());
//					String temp2 = readyQueue.get(i).getState();
//					int temp3 = readyQueue.get(i).getRemaining();
//					JLabel label = new JLabel("Process: " + temp + "   State: " + temp2 + "   Time Remaining: " + temp3);
//					panel.add(label);
//					labels.add(label);
//					panel.repaint();
//					panel.revalidate();
					
				}
				else {
					readyQueue.get(i).setState("waiting");
//					labels.remove(i);
//					String temp = Integer.toString(readyQueue.get(i).getID());
//					String temp2 = readyQueue.get(i).getState();
//					int temp3 = readyQueue.get(i).getRemaining();
//					JLabel label = new JLabel("Process: " + temp + "   State: " + temp2 + "   Time Remaining: " + temp3);
//					panel.add(label);
//					labels.add(label);
//					panel.repaint();
//					panel.revalidate();
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
				labels.remove(index);
				panel.remove(index);
				panel.repaint();
				panel.revalidate();
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
				//JLabel label = new JLabel("Process: " + temp + "   State: " + temp2 + "   Time Remaining: " + temp3);
				labels.get(index).setText("Process: " + temp + "   State: " + temp2 + "   Time Remaining: " + temp3);
				panel.repaint();
				panel.revalidate();
				time++;
				index++;
				Thread.sleep(1500);
			}		
		}
	}
}
