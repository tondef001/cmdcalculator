import java.util.Scanner;
import java.util.Stack;

/**
 * Command list:
 * add - add the numbers listed after
 * sub - subtract the numbers listed after
 * mul - multiply the numbers listed after
 * div - divide the numbers listed after
 * history - display the command history and results
 * clear - clear the command history
 * !x - retrieve a result from the xth command in history 
 * square - square the following number
 * exit - quits the program
 * 
 * @authors Tony Fay, Josh Leonard
 * Tony - implement use cases 3,4,5,6, test others
 * Josh - implement use cases 1,2,7,8, test others
 */
public class Main {	
	
	//This comment is to test Travis!
	
	private static Stack<CommandData> history = new Stack<CommandData>(); // History of valid commands
	private static final String regex = "![0-9]*"; //format for variable results
	private boolean printResult;
	private int result;
	
	/**
	 * Main method. 
	 * Continuously asks the user for input and sends it to Main.
	 * @author leonardj
	 */
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		while (true){
			System.out.print("Enter Command: ");
			String line = scanner.nextLine();
			new Main(line);
		}
	}
	
	/**
	 * Main Constructor that looks at the first word and executes the 
	 * case that matches the first word.
	 * @param line - Command to parse
	 * @author leonardj
	 */
	public Main(String line){
		if (line != null){
			String[] command = line.split("\\s+");
			printResult = true;
			result = 0;
			if (command.length > 0){
				switch (command[0]) {
					case "add":
						result = add(command);
						break;
					case "sub" :
						result = sub(command);
						break;
					case "mul" :
						result = multiply(command);
						break;
					case "div" :
						result = divide(command);
						break;
					case "history" :
						viewHistory();
						break;
					case "clear" :
						clearHistory();
						break;
					case "square" : 
						result = square(command);
						break;
					case "exit" :
						System.exit(0);
					default :
						printResult = false;
						System.err.println("Invalid Command\n");
				}
				if (printResult){
					CommandData commandData = new CommandData(line, result);
					history.push(commandData);
					System.out.println(result + "\n");
				}
			}
		} else {
			System.err.println("Command can not be null");
		}
	}
	
	/**
	 * Adds all the listed integers. If the command is invalid in anyway,
	 * the valid boolean is set to false and an error message printed.
	 * @param command - add command entered by user
	 * @return the sum of the integers
	 * @author leonardj
	 */
	private int add(String[] command){
		int result = 0;
		if (command.length < 2){
			System.out.println("add needs an argument");
			printResult = false;
		}
		for (int i = 1; i < command.length; i++){
			try {
				int nextInt = 0;
				if (command[i].matches(regex)){
					nextInt = getResultOnIndex(command[i]);
				} else {
					nextInt = Integer.parseInt(command[i]);
				}
				long temp = (long)result + nextInt;
				if (temp > Integer.MAX_VALUE){
					printResult = false;
					System.err.println("Values exceed Integer Max Value");
				} else if (temp < Integer.MIN_VALUE){
					printResult = false;
					System.err.println("Values below Integer Min Value");
				}
				result += nextInt;
			} catch (Exception e) {
				printResult = false;
				System.err.println("\"" + command[i] + "\" is an invalid integer");
			}
		}
		return result;
	}
	
	/**
	 * Subtracts all the listed integers from the first integer. If the command is
	 * invalid in anyway, the valid boolean is set to false and an error message printed.
	 * @param command - sub command entered by user
	 * @return the subtraction of given integers
	 * @author leonardj
	 */
	private int sub(String[] command){
		int result = 0;
		if (command.length < 2){
			System.out.println("sub needs an argument");
			printResult = false;
		}
		for (int i = 1; i < command.length; i++){
			try {
				int nextInt = 0;
				if (command[i].matches(regex)){
					nextInt = getResultOnIndex(command[i]);
				} else {
					nextInt = Integer.parseInt(command[i]);
				}
				long temp = (long)result - nextInt;
				if (temp < Integer.MIN_VALUE){
					printResult = false;
					System.err.println("Values below Integer Min Value");
				}  else if (temp > Integer.MAX_VALUE){
					printResult = false;
					System.err.println("Values exceed Integer Max Value");
				}
				if (i == 1){
					result = nextInt;
				} else {
					result -= nextInt;
				}
			} catch (Exception e) {
				printResult = false;
				System.err.println("\"" + command[i] + "\" is an invalid integer");
			}
		}
		return result;
	}
	
	/**
	 * Multiplies all listed numbers together and displays the product
	 * @param command multiplication command entered by user
	 * @return the product of all provided numers
	 * @author Tony Fay
	 */
	private int multiply(String[] command){
		int result = 1;
		if (command.length < 2){
			System.out.println("mul needs an argument");
			printResult = false;
		}
		for (int i = 1; i < command.length; i++){
			try {
				int nextInt = 0;
				if (command[i].matches(regex)){
					nextInt = getResultOnIndex(command[i]);
				} else {
					nextInt = Integer.parseInt(command[i]);
				}
				long temp = (long)result * nextInt;
				if (temp > Integer.MAX_VALUE){
					printResult = false;
					System.err.println("Values exceed Integer Max Value");
				}
				if (temp < Integer.MIN_VALUE){
					printResult = false;
					System.err.println("Values exceed Integer Min Value");
				}
				result *= nextInt;
			} catch (Exception e) {
				printResult = false;
				System.err.println("\"" + command[i] + "\" is an invalid integer");
			}
		}
		return result;
	}
	
	/**
	 * Divides the first number provided as an argument by all the remaining numbers
	 * @param command Division command provided by the user
	 * @return the quotient of all provided numbers
	 * @author Tony Fay
	 */
	private int divide(String[] command){
		int result = 1;
		if (command.length < 2){
			System.out.println("div needs an argument");
			printResult = false;
		}
		for (int i = 1; i < command.length; i++){
			try {
				int nextInt = 1;
				if (command[i].matches(regex)){
					nextInt = getResultOnIndex(command[i]);
				} else {
					nextInt = Integer.parseInt(command[i]);
				}
				if (i == 1){
					result = nextInt;
				} else {
					result /= nextInt;
				}
			} catch (ArithmeticException ae){
				printResult = false;
				System.err.println("Cannot divide by 0");
			}
			catch (Exception e) {
				printResult = false;
				System.err.println("\"" + command[i] + "\" is an invalid integer");
			}
		}
		return result;
	}
	
	/**
	 * Clear the calculator history
	 * @author Tony Fay
	 */
	private void clearHistory(){
		history.clear();
		printResult = false;
	}
	
	/**
	 * View the calculator history, numbered with the numbers needed to recall history values
	 * @author Tony Fay
	 */
	private void viewHistory(){
		int i = 1;
		for(CommandData cd : history){
			System.out.println( (i++) + ".)  " + cd.getResult());
		}
		printResult = false;
	}
	
	/**
	 * Subtracts all the listed integers from the first integer. If the command is
	 * invalid in anyway, the valid boolean is set to false and an error message printed.
	 * @param command - sub command entered by user
	 * @return the subtraction of given integers
	 * @author leonardj
	 */ 
	private int square(String[] command){
		int result = 0;
		if (command.length == 2){
			try {
				int nextInt = 0;
				if (command[1].matches(regex)){
					nextInt = getResultOnIndex(command[1]);
				} else {
					nextInt = Integer.parseInt(command[1]);
				}
				long temp = (long)nextInt * nextInt;
				if (temp > Integer.MAX_VALUE){
					printResult = false;
					System.err.println("Value exceed Integer Max Value");
				}
				result = nextInt * nextInt;
			} catch (ArrayIndexOutOfBoundsException e) {
				printResult = false;
				System.err.println("No integer specified");
			} catch (Exception e) {
				printResult = false;
				System.err.println("\"" + command[1] + "\" is an invalid integer");
			}
		} else {
			printResult = false;
			System.err.println("Can only enter one integer");
		}
		return result;
	}
	
	/**
	 * Looks for the history index and returns the result of that command.
	 * @param var example: !2
	 * @return result of the indexed command
	 * @author leonardj
	 */
	private int getResultOnIndex(String var){
		int value = 0;
		try {
			int index = Integer.parseInt(var.substring(1)) - 1;
			if (index > -1 && index < history.size()){
				value = history.get(index).getResult();
			} else {
				System.err.println(var + " is out of bounds");
				printResult = false;
			}
		} catch (Exception e) {
			System.err.println(var + " is not an integer");
			printResult = false;
		}
		return value;
	}
	
	/**
	 * For Testing
	 * @return if the result is printed
	 */
	public boolean prints(){
		return printResult;
	}
	
	/**
	 * For Testing
	 * @return the current result
	 */
	public int getResult(){
		return result;
	}
	
	/**
	 * For Testing
	 * @return the history stack
	 */
	public Stack getHistory(){
		return history;
	}
}
