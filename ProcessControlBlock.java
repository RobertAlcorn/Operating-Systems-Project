//Rob Alcorn
//11-21-2019

public class ProcessControlBlock {

	int processID;
	String processState;
	int completionTime;
	int parentProcessID;
	int burstTime;
	int timeRemaining;
	int arrivalTime;
	int turnaroundTime;
	int responseTime;
	int waitingTime;
	int instructionPointer;
		
	//getters and setters will go here for all parameters	
	public int getWait() {
		return waitingTime;
	}
	
	public void setWait(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	public int getResponse() {
		return responseTime;
	}
	
	public void setResponse(int responseTime) {
		this.responseTime = responseTime;
	}
	
	public int getCompletion() {
		return completionTime;
	}
	
	public void setCompletion(int completionTime) {
		this.completionTime = completionTime;
	}
	
	public int getTurnaround() {
		return turnaroundTime;
	}
	
	public void setTurnaround(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}
	
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
	
	public int getRemaining() {
		return timeRemaining;
	}
	
	public void setRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	
	public int getArrival() {
		return arrivalTime;
	}
	
	public void setArrival(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setInstructionPointer(int instructionPointer){
		this.instructionPointer = instructionPointer;
	}
	
	public int getInstructionPointer() {
		return instructionPointer;
	}
		
	public String toString() {
		return "ProcessID: " + processID + "\tProcess Burst Time: " + burstTime + "\tProcess State: " + processState + "\n";
	}
}