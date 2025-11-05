package home;

public class TelevisionOnCommand implements Command {
	private final Television television;
	
	public TelevisionOnCommand(Television television) {
		this.television = television;
	}

	@Override
	public void execute() {
		television.turnOn();
	}

	@Override
	public void undo() {
		System.out.println("undo TelevisionOnCommand");
		television.turnOff();
	}

}
