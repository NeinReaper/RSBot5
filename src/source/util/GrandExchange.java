package source.util;

import org.powerbot.script.AbstractScript;
import org.powerbot.script.lang.LocatableIdNameQuery;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Npcs;
import org.powerbot.script.methods.Widgets;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Widget;

public class GrandExchange {
	public static final int BOX1_CHILD_ID = 19, BOX2_CHILD_ID = 35, 
			BOX3_CHILD_ID = 51, BOX4_CHILD_ID = 70,
			BOX5_CHILD_ID = 89, BOX6_CHILD_ID = 108,
			SEARCH_BUTTON_CHILD_ID = 190, SEARCH_WIDGET_ID = 389,
			SEARCH_CHILD_ID = 4, QUANTITY_CHILD_ID = 167;
	public static final int[] BOX_CHILD_IDS = new int[]{BOX1_CHILD_ID, BOX2_CHILD_ID,
										BOX3_CHILD_ID, BOX4_CHILD_ID,
										BOX5_CHILD_ID, BOX6_CHILD_ID};
	public final int[] EXCHANGER_IDS = new int[]{1419,2593};
	public final int GE_WIDGET_ID = 105, GE_CHILD_ID = 1;
	public Widget geWidget;
	private AbstractScript script;
	
	public GrandExchange(AbstractScript script){
		this.script = script;
		geWidget = this.script.getContext().widgets.get(GE_WIDGET_ID);
	}
	
	public Npc getNearestExchanger(){
		for(Npc npc : script.getContext().npcs.id(EXCHANGER_IDS).first()){
			if(npc != null){
				return npc;
			}
		}
		return null;
	}

	public boolean isOnScreen(){
		Npc exchanger;
		return (((exchanger = getNearestExchanger())!= null) && exchanger.isOnScreen()) ;
	}
	
	public boolean isOpen(){
		return geWidget.getComponent(GE_CHILD_ID).isOnScreen();
	}
	
	public boolean open(){
		Npc exchanger = getNearestExchanger();
		if(exchanger != null){
			if(exchanger.isOnScreen()){
				return exchanger.interact("Exchange");
			} else {
				script.getContext().camera.turnTo(exchanger);
			}
		}
		return isOpen();
	}
	
}
