package source.scriptFramework.execution.event;

public abstract class ChainEvent extends Event {
	private Event next;
	public ChainEvent(Event container, Event next){
		super(container);
		this.next = next;
	}
	
	public void setNext(Event next){
		this.next = next;
	}
	
	public Event getNext(){
		return next;
	}
	
	@Override
	public void cycle(){
		chain();
		if(next.accept()){
			next.execute();
		}
	}
	
	protected abstract void chain();

}
