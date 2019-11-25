//Rob Alcorn
//11-21-2019

public class ProcessControlBlock {

	int processID;
	String processState;
	int priority;
	int completionTime;
	int waitTimefromLoad;
	int parentProcessID;
	int burstTime = 0;
		
	//getters and setters will go here for all parameters
	public int getID() {
		return processID;
	}
	
	public void setID(int processID) {
		this.processID = processID;
	}
	
	public String getState() {
		return processState;
	}
	
	public void setState(String processState) {
		this.processState = processState;
	}
	
	public int getBurst() {
		return burstTime;
	}
	
	public void setBurst(int burstTime) {
		this.burstTime = burstTime;
	}
		
	public String toString() {
		return "ProcessID: " + processID + "\tProcess Burst Time: " + burstTime + "\tProcess State: " + processState + "\n";
	}
}
