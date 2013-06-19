package source.scripts.prosFishRusher;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;
import source.scriptFramework.abstractUserInterface.AbstractPaintController;
import source.scriptFramework.execution.event.*;
import source.scripts.prosFishRusher.event.*;
import source.scripts.prosFishRusher.event.events.*;
import source.scripts.prosFishRusher.userInterface.FishPaintEvent;
import source.util.abstractPaints.MousePaint;
@Manifest(authors = { "Protog" },
 			name = "ProsFishRusher",
			description = "Efficeint Fishing Guild Fisher for RSBot-5"
			)
public class ProsFishRusherScript extends PollingScript implements PaintListener, MouseListener{
	private boolean hasStarted = false, showPaint = true;
	private Event controller, paintController, paintEvent, fishArm, fishEvent;
	private Event[] fishArmEvents;
	
	
	public int currentFishNpcId = 312;
	
	
	private void startUp(){
		controller = new ControlEvent(controller, this, ctx){
			@Override
			public boolean accept() {
				return ctx.game.isLoggedIn();
			}
		};
		paintController = new AbstractPaintController(controller, this, ctx){

			@Override
			public boolean accept() {
				return controller.accept();
			}
			
		};
		paintEvent = new FishPaintEvent(paintController);
		fishEvent = new FishEvent(fishArm);
		fishArmEvents = new Event[]{ fishArm };
		fishArm = new FishArm(controller, fishArmEvents);
		
		((ControlEvent)controller).submit(fishArm);
		((AbstractPaintController)paintController).submit(new MousePaint(paintController), paintEvent);
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
	
	@Override
	public void repaint(Graphics g) {
		if(paintController != null){
			if(((AbstractPaintController)paintController).getGraphics() != null){
				if(paintController.accept()){
					paintController.execute();
				}
			} else {
				((AbstractPaintController)paintController).setGraphics(g);
			}
		}
	}
	
	public boolean showPaint(){
		return showPaint;
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {	}

	@Override
	public void mouseReleased(MouseEvent arg0) { }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		//TODO minimizer
	}

	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent arg0) { }
}
