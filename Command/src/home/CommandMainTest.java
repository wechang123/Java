package home;

public class CommandMainTest {

	public CommandMainTest() {
                SimpleRemoteControl rc = new SimpleRemoteControl(); // Invoker

                Light lamp = new Light(); // Receiver
                Command lampOnCommand = new LightOnCommand(lamp); // Command
                Command lampOffCommand = new LightOffCommand(lamp); // Command

                rc.setCommand("lampOn", lampOnCommand); //
                rc.setCommand("lampOff", lampOffCommand);
                
                Fan fan = new Fan(); // Receiver
                Command fanStartCommand = new FanStartCommand(fan); // Command
                Command fanStopCommand = new FanStopCommand(fan); // Command
                rc.setCommand("fanStart", fanStartCommand);
                rc.setCommand("fanStop", fanStopCommand);

                Television tv = new Television(); // Receiver
                Command tvOnCommand = new TelevisionOnCommand(tv); // Command
                Command tvOffCommand = new TelevisionOffCommand(tv); // Command
                rc.setCommand("tvOn", tvOnCommand);
                rc.setCommand("tvOff", tvOffCommand);

                rc.execute("lampOn");
                rc.execute("lampOff");
                rc.execute("fanStart");
                rc.execute("fanStop");
                rc.execute("tvOn");
                rc.undo("tvOn");   
	}

}
