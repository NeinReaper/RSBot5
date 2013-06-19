package source.scripts.prosFishRusher.event.events;
import org.powerbot.script.util.Filter;
import org.powerbot.script.wrappers.Npc;
import source.scriptFramework.execution.event.ChainEvent;
import source.scriptFramework.execution.event.Event;
import source.scripts.prosFishRusher.ProsFishRusherScript;
public class SpotFishEvent extends ChainEvent{
	private Npc fishToInteract;
	public SpotFishEvent(Event container, Event next) {
		super(container, next);
	}

	@Override
	protected void chain() {
		for(Npc npc : getCtx().npcs.id(getMyScript().currentFishNpcId).nearest().first().select(new Filter<Npc>(){

			@Override
			public boolean accept(Npc arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
		})){
			
		}
	}
	
	class FishNpcFilter implements Filter<Npc>{

		@Override
		public boolean accept(Npc arg0) {
			return false;
		}
		
	}
	
	public ProsFishRusherScript getMyScript(){
		return ((ProsFishRusherScript)getScript());
	}

	@Override
	public boolean accept() {
		return fishToInteract == null;
	}

}
