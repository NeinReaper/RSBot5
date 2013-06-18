package source.scriptFramework.execution.event;

public abstract class ChainEvent extends Event {
	private ChainEvent next;
	public ChainEvent(Event container, ChainEvent next){
		super(container);
		this.next = next;
	}
	
	public void setNext(ChainEvent next){
		this.next = next;
	}
	
	public ChainEvent getNext(){
		return next;
	}
	
	@Override
	public void cycle(){
		chain();
		if(next.accept()){
			next.chain();
		}
	}
	
	protected abstract void chain();

}
