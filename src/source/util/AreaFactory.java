package source.util;

import java.util.ArrayList;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

public class AreaFactory {
	private MethodContext ctx;
	private ArrayList<Area> areas = new ArrayList<Area>();
	public AreaFactory(MethodContext ctx){
		this.ctx = ctx;
	}
	
	public Area newArea(Tile...tileArray){
		return new Area(this, tileArray);
	}
	
	public MethodContext getCtx(){
		return ctx;
	}
	
	
}
