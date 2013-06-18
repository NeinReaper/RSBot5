package source.scriptFramework.execution;
import java.util.ArrayList;
import source.scriptFramework.execution.event.Event;

public class EventContainer extends ArrayList<Event>{
	private Event[] events;
	private int lastSize = size();
	
	public void cycle(){
		if(events != null && lastSize == size()){
			for(Event event : events){
				if(event.accept()){
					event.execute();
				}
			}
		} else {
			events = toArray(new Event[size()]);
		}
		lastSize = size();
	}
	
	public void give(Event...events){
		for(Event event : events){
			add(event);
		}
	}
	
	public void shutDown(){
		if(events != null){
			for(int i = 0; i < events.length; i++){
				if(events[i] != null){
					events[i] = null;
				}
			}
		}
	}

}
