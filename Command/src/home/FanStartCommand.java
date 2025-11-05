package home;
// Command for Fan Start Rotating
public class FanStartCommand implements Command {
	private final Fan fan;

	public FanStartCommand(Fan fan) {
		this.fan = fan;
	}

	@Override
	public void execute() {
		fan.startRotate();
	}

	@Override
	public void undo() {
		System.out.println("undo FanStartCommand");
		fan.stopRotate();
	}

}
