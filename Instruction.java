import java.util.String;
import java.util.Random;

public class Instruction(string s, int i, int j) extends ProcessControlBlock{
    //Holds instruction value and operand value
    string instruction = s;
    int operand1 = i;
    int operand2 = j;



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

    public static int getOperand(x){
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

    public static void setOperand1(int num){
        operand1 = num;
    }

    public static void setOperand2(int num){
        operand2 = num;
    }

    public static string getInstruction(){
        return instruction;
    }

    public static void setInstruction(string s){
        instruction = s;
    }

    



}