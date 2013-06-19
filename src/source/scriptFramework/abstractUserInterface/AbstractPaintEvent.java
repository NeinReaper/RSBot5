package source.scriptFramework.abstractUserInterface;

import java.awt.Graphics;
import java.awt.Graphics2D;

import source.scriptFramework.execution.event.Event;

public abstract class AbstractPaintEvent extends Event{
	protected Graphics graphics;
	protected Graphics2D g2D;
	public AbstractPaintEvent(Event container) {
		super(container);
	}

	@Override
	public void cycle(){
		if(graphics != null){
			drawPaint();
		} else {
			graphics = getG();
			g2D = ((Graphics2D)graphics);
		} 
	}
	
	private Graphics getG(){
		AbstractPaintController controller = (AbstractPaintController)getMasterContainer();
		return controller != null ? controller.getGraphics() : null;
	}
	
	public Graphics getGraphics(){
		return graphics;
	}
	
	public abstract void drawPaint();
	
}
