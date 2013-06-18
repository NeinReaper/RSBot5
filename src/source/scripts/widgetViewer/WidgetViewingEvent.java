package source.scripts.widgetViewer;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Widget;
import source.scriptFramework.execution.event.Event;
public class WidgetViewingEvent extends Event {
	public static final int CANVAS_COMPONENT_INDEX = 0;
	public static final int[] UNWANTED_INDEXES = new int[]{CANVAS_COMPONENT_INDEX};
	private Component widgetToDraw = null;
	public WidgetViewingEvent(Event container) {
		super(container);
	}

	@Override
	public void cycle() {
		Component smallestChild = null;
		for(Widget widget : getCtx().widgets.getLoaded()){
			if(widget != null){
				if(widget.getComponentCount() > 0){
					for(Component component : widget.getComponents()){
						if(component != null){
							if(component.getChildrenCount() > 0){
								for(Component child : component.getChildren()){
									if(child != null){
										if(child.contains(getCtx().mouse.getLocation())){
											if(smallestChild != null){
												if((child.getHeight() * child.getWidth()) < (smallestChild.getHeight() * smallestChild.getWidth())){
													smallestChild = child;
												}
											} else {
												smallestChild = child;
											}
										}
									}
								}
							}
							if(component.contains(getCtx().mouse.getLocation())){
								if(smallestChild != null){
									if((component.getHeight() * component.getWidth()) < (smallestChild.getHeight() * smallestChild.getWidth())){
										smallestChild = component;
									}
								} else {
									smallestChild = component;
								}
							}
						}
					}
				}
			}
		}
		if(smallestChild != null && isNotUnwantedIndex(smallestChild.getIndex())){
			widgetToDraw = smallestChild;
			((WidgetViewer)getScript()).setChildToDraw(widgetToDraw);
		}
		
	}
	
	private boolean isNotUnwantedIndex(int index){
		for(int i : UNWANTED_INDEXES){
			if(index == i){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean accept() {
		return (widgetToDraw == null || !(widgetToDraw.contains(getCtx().mouse.getLocation())));
	}

}
