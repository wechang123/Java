package home;
//Command for Fan Stop Rotating
public class FanStopCommand implements Command {
	private final Fan fan;

	public FanStopCommand(Fan fan) {
		this.fan = fan;
	}

	@Override
	public void execute() {
		fan.stopRotate();
	}

	@Override
	public void undo() {
		System.out.println("undo FanStopCommand");
		fan.startRotate();
	}

}
