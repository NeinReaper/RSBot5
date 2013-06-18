package source.scriptFramework.execution.event;

import source.scriptFramework.execution.EventContainer;

public abstract class PrimaryEvent extends Event {
	private EventContainer eventContainer;
	public PrimaryEvent(Event container){
		super(container);
		eventContainer = new EventContainer();
	}
	@Override
	public void cycle() {
		for(Event event : eventContainer){
			if(event.accept()){
				event.execute();
			}
		}
	}
	
	public EventContainer getEventContainer(){
		return eventContainer;
	}
	
	public void submit(Event...events){
		for(Event event : events){
			if(!getEventContainer().contains(event)){
				getEventContainer().add(event);
			}
		}
	}


}
