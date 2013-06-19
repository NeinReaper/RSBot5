package source.scripts.widgetViewer;
import java.awt.Color;
import java.awt.Graphics;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Component;

import source.scriptFramework.execution.event.ControlEvent;
import source.scriptFramework.execution.event.Event;
@Manifest(authors = { "Protog" },
description = "Widget Viewer", 
name = "Widget Viewer")
public class WidgetViewer extends PollingScript implements PaintListener{
	private boolean hasStarted = false;
	private Event controller, widgetEvent;
	private Component childToDraw;

	private void startUp(){
		controller = new ControlEvent(controller, this, ctx){
			@Override
			public boolean accept() {
				return ctx.game.isLoggedIn();
			}
		};
		widgetEvent = new WidgetViewingEvent(controller);
		
		((ControlEvent)controller).submit(widgetEvent);
		hasStarted = true;
	}
	
	@Override
	public void repaint(Graphics g) {
		if(!(g.getColor().equals(Color.RED))){
			g.setColor(Color.RED);
		}	
		if(childToDraw != null){
			childToDraw.draw(g);
		}
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
	
	public void setChildToDraw(Component childToDraw){
		this.childToDraw = childToDraw;
	}

}
