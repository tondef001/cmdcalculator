
public class CommandData {
	private String command;
	private int result;
	
	public CommandData(String command, int result){
		this.command = command;
		this.result = result;
	}
	
	public String getCommand(){
		return command;
	}
	
	public int getResult(){
		return result;
	}
}
