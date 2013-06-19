package source.util.abstractPaints;
import source.scriptFramework.abstractUserInterface.AbstractPaintEvent;
import source.scriptFramework.execution.event.Event;
import source.scripts.prosFishRusher.ProsFishRusherScript;
public class MousePaint extends AbstractPaintEvent {

	public MousePaint(Event container) {
		super(container);
	}

	@Override
	public void drawPaint() {
		//horizontal line
		int mouseX = (int) getCtx().mouse.getLocation().getX(),
				mouseY = (int) getCtx().mouse.getLocation().getY();
		int hx1 = 0, hx2 = mouseX-10, hy1 = mouseY, hy2 = hy1;
		graphics.drawLine(hx1,hy1,hx2,hy2);

		hx1 = mouseX+10;
		hx2 = (int)getCtx().game.getDimensions().getWidth();
		graphics.drawLine(hx1,hy1,hx2,hy2);

		//vertical line
		int vx1 = mouseX, vx2 = vx1, vy1 = 0, vy2 = mouseY-10;
		graphics.drawLine(vx1,vy1,vx2,vy2);

		vy1 = mouseY+10;
		vy2 = (int)(getCtx().game.getDimensions().getHeight());
		graphics.drawLine(vx1, vy1, vx2, vy2);

		graphics.drawRect(mouseX-10, mouseY-10, 20, 20);
	}

	@Override
	public boolean accept() {
		return getCtx().game.isLoggedIn();
	}

}
