package source.scriptFramework.execution.event;

import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;

public abstract class Event{
	protected boolean shutDown = false;
	public abstract void cycle();
	public abstract boolean accept();
	private Event container;
	public Event(Event container){
		this.container = container;
	}
	public void execute(){
		if(!shutDown){
			cycle();
		}
	}
	public void shutDown(){
		shutDown = true;
	}
	
	public Event getContainer(){
		return container;
	}
	
	public ControlEvent getMasterContainer(){
		Event currentEvent = this;
		if(currentEvent != null){
			while(!(currentEvent instanceof ControlEvent)){
				if(currentEvent.getContainer() != null){
					currentEvent = currentEvent.getContainer();
				}
				
			}
		}
		
		return ((ControlEvent)currentEvent);
	}
	
	public PollingScript getScript(){
		ControlEvent controller = getMasterContainer();
		return((controller != null) ? controller.getPollingScript() : null);
	}
	
	public MethodContext getCtx(){
		return getMasterContainer().getContext();
	}
}
