
public class Phase {
	public enum phasetype {STANDBY, DRAWPHASE, MAINPHASE1, BATTLE, MAINPHASE2, ENDPHASE, OPPONENT};
	
	phasetype phase;
	
	public Phase(int player){
		if(player == 0){
			phase = phasetype.STANDBY;
		}
		else{
			phase = phasetype.OPPONENT;
		}
	}

	
}
