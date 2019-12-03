
public class Instruction{

    //Holds instruction value and operand value
    String instruction;
    int operand1;

    public Instruction(String instruction, int operand1){
    this.instruction = instruction;
    this.operand1 = operand1;
    }
    

    public static void main(){
    }

    public int getOperand(){
       
            return operand1;
    }

    public void setOperand1(int num){
        operand1 = num;
    }

    public String getInstruction(){
        return instruction;
    }

    public void setInstruction(String s){
        instruction = s;
    }

    



}