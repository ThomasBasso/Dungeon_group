package fil.coo.adventure.places;

import fil.coo.adventure.entities.monsters.util.*;

public class Exit extends Room {
	public Exit() {
		super();
		// Rajoute un dragon comme 'Boss de fin'
		this.monsters.add(new Dragon());
	}
	
	// Quitte la salle
	public boolean isExit() {
		return true;
	}
}
