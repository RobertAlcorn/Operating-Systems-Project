//import java.util.Random;

public class Instruction{

    //Holds instruction value and operand value
    String instruction;
    int operand1;

    public Instruction(String instruction, int operand1){
    this.instruction = instruction;
    this.operand1 = operand1;
    }
    

    public static void main(){
        //Random rand = new Random();

        // int randInt1 = rand.nextInt(10);
        // int randInt2 = rand.nextInt(10);
        // int randBool = rand.nextInt(2);
        

        // if (randBool == 1){
        //     setOperand1(randInt1);
        //     setInstruction("ADD");
        // }
        // else if (randBool == 2){
        //     setOperand1(randInt1);
        //     setInstruction("SUB")
        //

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