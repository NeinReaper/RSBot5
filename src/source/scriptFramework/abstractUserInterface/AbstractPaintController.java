package source.scriptFramework.abstractUserInterface;

import java.awt.Graphics;

import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;

import source.scriptFramework.execution.event.ControlEvent;
import source.scriptFramework.execution.event.Event;

public abstract class AbstractPaintController extends ControlEvent {
	private Graphics graphics = null;
	public AbstractPaintController(Event container, PollingScript script, MethodContext methodContext) {
		super(container, script, methodContext);
		
	}
	
	public Graphics getGraphics(){
		return graphics;
	}
	
	public void setGraphics(Graphics graphics){
		this.graphics = graphics;
	}
}
