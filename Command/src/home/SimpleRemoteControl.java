package home;

import java.util.HashMap;
import java.util.Map;

// Invoker class
public class SimpleRemoteControl {
	private Map<String, Command> map = new HashMap<>();
	
	public void setCommand(String key, Command command) {
		this.map.put(key, command);
	}
	
	public void execute(String key) {
		Command command = this.map.get(key);
		if (command == null)
			throw new IllegalStateException("no command set for " + key);
		command.execute();
	}

	public void undo(String key) {
		Command command = this.map.get(key);
		if (command == null)
			throw new IllegalStateException("no command set for " + key);
		command.undo();
	}

}
