package source.scripts.prosFishRusher.event;

import source.scriptFramework.execution.event.Event;
import source.scriptFramework.execution.event.SecondaryEvent;

public class FishArm extends SecondaryEvent {

	public FishArm(Event container, Event[] arm) {
		super(container, arm);
	}

	@Override
	public boolean accept() {
		return getCtx().inventory.count() < 28 && getCtx().players.getLocal().getInteracting() == null;
	}

}
