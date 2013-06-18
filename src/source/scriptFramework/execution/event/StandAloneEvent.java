package source.scriptFramework.execution.event;

public abstract class StandAloneEvent extends Event{
	public StandAloneEvent(Event container) {
		super(container);
	}

	private Thread myThread;
	
	@Override
	public void cycle() {
		if(myThread == null){
			myThread = new Thread(new Runnable(){

				@Override
				public void run() {
					while(!shutDown && accept()){
						try {
							Thread.sleep(loop());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				
			});
		}
		
	}
	
	protected abstract int loop();
}
