package com.sample;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

public class GameHandler {
	
	private Map map;
	private Player[] players;
	private Player currentPlayer;
	
	
	private int turn = -1;
	
	public GameHandler(Player[] players, Map map) {
		this.players = players;
		this.map = map;
	}
	
	public void initGame() {
		this.changeTurn();
	}
	
	public void changeTurn() {
		this.turn = (this.turn + 1) % this.players.length;
		this.currentPlayer = this.players[this.turn];
	}
	
	public void spawnPlayers() {
		for(Player player : this.players) {
			this.map.insertEntityAtRandom(player);
		}
	}

	public Player[] getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Map getMap() {
		return map;
	}
	
	public static void main(String[] args) {
		
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");
			
			
			Player[] players = {new Player("*Dima")};
//			Player player1 = new Player("Dima");
//			kSession.insert(players);
			Map map = new Map(10, 10);
			GameHandler game = new GameHandler(players, map);
			kSession.insert(game);
			kSession.fireAllRules();

//			kSession.fireAllRules();
			
			createEventListener(kSession);
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
	
	public static void createEventListener(KieSession kSession) {
		RuleRuntimeEventListener listener = new RuleRuntimeEventListener() {
			// Listen to the update event
			public void objectUpdated(ObjectUpdatedEvent arg0) {
				System.out.println("*****Object Updated*****\n"
						+arg0.getObject().toString());
				
			}
			
			// Listen to the insert event
			public void objectInserted(ObjectInsertedEvent arg0) {
				System.out.println("*****Object inserted***** \n"
						+ arg0.getObject().toString());
				
			}
			
			// Listen to the delete event
			public void objectDeleted(ObjectDeletedEvent arg0) {
				System.out.println("*****Object Retracted*****\n"
						+ arg0.getOldObject().toString());
				
			}
		};
		
		// Add the event listener to the session
		kSession.addEventListener(listener);
	}
	

}
