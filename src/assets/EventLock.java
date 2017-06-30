package assets;

public class EventLock {
	enum eventtype {NONE, MOVECARD}
	
	String CardSelected;
	eventtype event;
	
	
	public EventLock(){
		event = eventtype.NONE;
	}
	
	public void initLock(String Card){
		if(isAvail()){
			CardSelected = Card;
		}
	}
	
	private boolean isAvail(){
		return event == eventtype.NONE;
	}
}
