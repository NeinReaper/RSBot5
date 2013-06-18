package source.scriptFramework.execution.event;

import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;

public abstract class ControlEvent extends PrimaryEvent {
	private PollingScript script;
	private MethodContext methodContext;
	public ControlEvent(Event container) {
		super(container);
	}
	
	public ControlEvent(Event container, PollingScript script, MethodContext methodContext){
		super(null);
		this.script = script;
		this.methodContext = methodContext;
	}
	
	public PollingScript getPollingScript(){
		return script;
	}
	
	public MethodContext getContext(){
		return methodContext;
	}

}
