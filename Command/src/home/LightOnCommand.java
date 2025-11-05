package home;
//Command for Light On
public class LightOnCommand implements Command {

	private final Light light;
	
	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.on();
	}

	@Override
	public void undo() {
		System.out.println("undo LightOnCommand");
		light.off();
	}
}
