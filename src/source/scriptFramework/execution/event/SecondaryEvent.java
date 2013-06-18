package source.scriptFramework.execution.event;
import source.scriptFramework.execution.EventContainer;

public abstract class SecondaryEvent extends Event{
	private EventContainer arm;
	public SecondaryEvent(Event container, Event[] arm){
		super(container);
		this.arm = new EventContainer();
		this.arm.give(arm);
	}
	
	public void cycle(){
		arm.cycle();
	}
	
	public EventContainer getArm(){
		return arm;
	}
}
