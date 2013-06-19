package source.scripts;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;

import source.scriptFramework.execution.event.ControlEvent;
import source.scriptFramework.execution.event.Event;
import source.scriptFramework.execution.event.StandAloneEvent;
public class Example extends PollingScript {
	private boolean hasStarted = false;
	private Event controller;
	
	private void startUp(){
		controller = new ControlEvent(controller, this, ctx){
			@Override
			public boolean accept() {
				return ctx.game.isLoggedIn();
			}
		};
		
		((ControlEvent)controller).submit(new StandAloneEvent(controller){

	

			@Override
			public boolean accept() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected int loop() {
				// TODO Auto-generated method stub
				return 0;
			}
			
		});
		hasStarted = true;
	}
	
	@Override
	public int poll() {
		if(hasStarted){
			if(controller.accept()){
				controller.execute();
			}
		} else {
			startUp();
		}
		return Random.nextInt(550, 650);
	}

}
