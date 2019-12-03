import java.util.Random;

public class Instruction{

    //Holds instruction value and operand value
    String instruction;
    int operand1;
    int operand2;

    public Instruction(String s, int i, int j){
    String instruction = s;
    int operand1 = i;
    int operand2 = j;
    }
    
    public Instruction(String s, int i){
    String instruction = s;
    int operand1 = i;
    }

    public static void main(){
        Random rand = new Random();

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
        // }







    }

    public int getOperand(int x){
        if (x == 1){
            return operand1;
        }
        else if (x == 2){
            return operand2;
        }
        else {
            return operand1;
        }
    }

    public void setOperand1(int num){
        operand1 = num;
    }

    public void setOperand2(int num){
        operand2 = num;
    }

    public String getInstruction(){
        return instruction;
    }

    public void setInstruction(String s){
        instruction = s;
    }

    



}