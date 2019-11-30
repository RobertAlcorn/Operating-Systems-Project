//Rob Alcorn
//11-21-2019

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class ProcessManager {
	
	//create and initialize variables
	static ArrayList<ProcessControlBlock> readyQueue = new ArrayList<ProcessControlBlock>();
	static ArrayList<ProcessControlBlock> activeQueue = new ArrayList<ProcessControlBlock>();
	static boolean isTrue = true;
	static int processCount = 0;
	static int pid = 1;
	static int time = 0;
	static int quantum = 1;
	static int index = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		//GUIBuilder.main(args);
//		JFrame frame = new JFrame("Process Manager");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.setSize(400,400);
//	    //JButton button = new JButton("Press");
//	    //frame.getContentPane().add(button); // Adds Button to content pane of frame
//	    frame.setVisible(true);
	    
				
		//10 processes will exist at a time so create the first initial 10 processes
		for(int i = 0; i < 10; i++) {
			
			//create new process control block class 
			ProcessControlBlock pcb = new ProcessControlBlock();
			int temp = (int) (Math.random() * 10) + 1;	
			int temp2 = (int) (Math.random() * 10) + time;
			
			//set burst time, process id, and process state
			pcb.setID(pid);
			pcb.setBurst(temp);
			pcb.setState("ready");
			pcb.setRemaining(temp);
			pcb.setArrival(temp2);
			
			//add process control block to list of pcb objects
			readyQueue.add(pcb);
			System.out.println("Process ID: " + pcb.getID() + "\tProcess State: " + pcb.getState() + "\tBurst Time: " + pcb.getBurst());
			pid++;
			processCount++;
//			JLabel label1 = new JLabel("Process ID: " + pcb.getID() + "\tProcess State: " + pcb.getState() + "\tBurst Time: " + pcb.getBurst());
//			frame.add(label1);
		}
		
		//will run indefinitely
		while(isTrue) {
			//if there are less than 10 processes in the system create another one
			if(processCount < 10) {
				ProcessControlBlock pcb = new ProcessControlBlock();
				int temp = (int) (Math.random() * 10) + 1;	
				int temp2 = (int) (Math.random() * 10) + time;
				
				pcb.setID(pid);
				pcb.setBurst(temp);
				pcb.setState("ready");
				pcb.setRemaining(temp);
				pcb.setArrival(temp2);
				
				readyQueue.add(pcb);
				System.out.println("Process ID: " + pcb.getID() + "\tProcess State: " + pcb.getState() + "\tBurst Time: " + pcb.getBurst());
				pid++;
				processCount++;
			}
			if(index == 10) {
				index = 0;
			}
			
			//round robin
			int timeLeft = readyQueue.get(index).getRemaining();
			if( timeLeft == quantum ) {			
				System.out.println("Process " + readyQueue.get(index).getID() + " completed at time " + time);
				readyQueue.remove(index);
				processCount--;
				time++;
				index++;
				Thread.sleep(1000);
			}
			else if( timeLeft > quantum ) {
				readyQueue.get(index).setRemaining( timeLeft -1);
				System.out.println("Process " + readyQueue.get(index).getID() + " has " + readyQueue.get(index).getRemaining() + " time remaining");
				time++;
				index++;
				Thread.sleep(1000);
			}
			
			
			//processCount--;
//			Thread.sleep(1000);
//			time++;
//			index++;
		}
	}
}
