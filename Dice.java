import java.util.Random;
import java.util.Scanner;
public class Dice {
private int sidesNum;
private Random die = new Random();

Scanner in = new Scanner(System.in);
public Dice() {
	System.out.println("How many sides will your die have?");
	sidesNum = in.nextInt();
}
public int roll() {
	return die.nextInt(sidesNum);
}
	public static void main(String[] args) {
Dice one = new Dice();
Dice two = new Dice();
Dice three = new Dice();
double singleGreater = 0;
double doubleGreater = 0;

for (int i = 0; i < 1000; i++) {
	if((one.roll()+two.roll())>three.roll()) {
		doubleGreater++;}
	else {
		singleGreater++;
	}
	}
System.out.println("Sum of 2 dice was greater than a single die: "+doubleGreater+"/1000");
System.out.println("A single die was greater than sum of 2 dice: "+singleGreater+"/1000");
if (doubleGreater > singleGreater) {
	System.out.println("The probability of the sum of 2 dice being greater "
			+"than a single die is approximately "+ doubleGreater/10);}
else {System.out.println("The probability of a single die being greater "
		+"than the sum of 2 dice is approximately "+ singleGreater/10);}
	
}
}


