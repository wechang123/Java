package home;
//Command for Light Off
public class LightOffCommand implements Command {

	private final Light light;
	
	public LightOffCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.off();
	}

	@Override
	public void undo() {
		System.out.println("undo LightOffCommand");
		light.on();
	}

}
