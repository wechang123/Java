package home;

public class TelevisionOffCommand implements Command {
	private final Television television;
	
	public TelevisionOffCommand(Television television) {
		this.television = television;
	}

	@Override
	public void execute() {
		television.turnOff();
	}

	@Override
	public void undo() {
		System.out.println("undo TelevisionOffCommand");
		television.turnOn();
	}

}
