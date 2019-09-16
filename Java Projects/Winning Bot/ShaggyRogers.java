
public class ShaggyRogers implements BotAPI {

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

    private PlayerAPI me, opponent;
    private BoardAPI board;
    private CubeAPI cube;
    private MatchAPI match;
    private InfoPanelAPI info;

    ShaggyRogers(PlayerAPI me, PlayerAPI opponent, BoardAPI board, CubeAPI cube, MatchAPI match, InfoPanelAPI info) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.cube = cube;
        this.match = match;
        this.info = info;
    }

    public String getName() {
        return "ShaggyRogers"; // must match the class name
    }

    
	 public String getCommand(Plays possiblePlays) {

	    int move = findMove(possiblePlays);

	    double odds = getOdds();
	    	
	    if(checkOdds(odds) && match.canDouble(me.getId()) && ((cube.isOwned() == false) || (cube.getOwnerId() == me.getId()))) {
	   		return "double";
	   	}
	    		
	    return Integer.toString(move);   
	 }
	 
	 /*
	  * Function that processes all plays within possiblePlays
	  */	
	 public int findMove(Plays possiblePlays) {
		 
    	int weight = 0;
		int i = 1;
    	int move=i;
    	int check = -5000;
    	for(Play play : possiblePlays) {
			
			weight = evaluatePlay(play);
			if(weight > check) {
				check = weight;
				move = i;
			}
			i++;
			
    	}
    	return move;
    }
    
	 /*
	  * function that determines the odds of winning or loosing the game
	  */
    private double getOdds() {
		
		int numOfCheckers = 0;
		int myDistance = 0;
		int opponentDistance = 0;
		int[][] checkers = board.get();
		int[] myBoard = checkers[me.getId()];
		int[] opponentBoard = checkers[opponent.getId()];
		
		for(int i = 1;i<myBoard.length-1;i++) {
			numOfCheckers = 0;
			if(myBoard[i] > 0) {
				for(int j = 0; j< myBoard[i]; j++) {
					numOfCheckers++;
				}
				myDistance += (i)*numOfCheckers;
			}
		}
		
		for(int i = 1;i<opponentBoard.length-1;i++) {
			numOfCheckers = 0;
			if(opponentBoard[i] > 0) {
				for(int j = 0; j< opponentBoard[i]; j++) {
					numOfCheckers++;
				}
				opponentDistance += (i)*numOfCheckers;
			}
		}
		
		double odds = myDistance * 100 /opponentDistance;
		if(opponentDistance <= 40) {
			if(odds >= 150) {
				odds = 200;
			}
		}
		return odds;
	}
    
    /*
     * function that determines whether you should double and also is used to determine whether to
     * accept or reject a double offer
     */
	private boolean checkOdds(double odds) {
		
		boolean decision = false;
		if(checkScore() == false && odds <= 66 && odds >=35) {
			decision = true;
		}
		else if(checkScore() &&  odds <= 50) {
			decision = true;
		}
			
		return decision;
	}
	
	/*
	 * checks if any player is 2 points away from winning
	 */
	private boolean checkScore() {
		
		return ((me.getScore() == match.getLength() - 2) && ( opponent.getScore() == match.getLength() -2));
	}
	
	/*
	 * Function that returns double decision, either to accept or reject
	 */
	public String getDoubleDecision() {
        double odds = getOdds();
        String check = "";
        if(checkScore() && odds > 142) {
        	check = "n";
        }
        else if(checkScore() == false && odds > 167){
        	check = "n";
        }
        else {
        	check = "y";
        }
        return check;
    }
	/*
	 * Function that evaluate a weight of each play
	 */
	private int evaluatePlay(Play play) {
		
		int[][] checkers = board.get();
		int[] myBoard = checkers[me.getId()];
		int weightValue = 0;
		
		//need to check if all pieces are passed, if so make move farthest from your end else check moves
		
		for(Move move: play) {
			if(move.isHit()) {
				weightValue += calculateHit(myBoard,checkers);
			}
			if(calculateCanMoveOff(move)) {
				weightValue+=100;
			}
			myBoard[move.getFromPip()]--;
			myBoard[move.getToPip()]++;	
		}
		
		checkers = board.get();
		
		weightValue+= calculateStrongPosition(myBoard,checkers);
		weightValue+= calculateBlot(myBoard,checkers);
		weightValue+= calculateStrong(myBoard);
		
		return weightValue;
	}
	
	/*
	 * Function that calculater whether you have 4 or more Strong points (not blots) on pips between 1 and 7
	 */
	private int calculateStrong(int[] myBoard) {
		int point=0;
		int weight=0;
		for(int i = 1;i<8;i++) {
			if(myBoard[i] >= 2) {
				point++;
			}
		}
		if(point ==4) {
			weight+=40;
		}
		else if(point>4){
			
				weight+=45;
				
			
		}
		
		return weight;
	}
	
	/*
	 * Function that checks if  you can move off the board
	 */
	private boolean calculateCanMoveOff(Move move) {
		
		return (move.getToPip() == 0);
	}
	
	/*
	 * Calculates weight of the move based on the amount of Strong pips you have on the board
	 */
	private int calculateStrongPosition(int[] myBoard, int[][] checkers) {
		// check if pieces have made a prime if so add high weight
		// if prime has been made on certain pips add higher weight
		int weight = 0;
		for(int i = 1;i<myBoard.length-1;i++) {
			if(myBoard[i] >= 2 && checkers[me.getId()][i] < 2) {
				if(i>=1 && i<=8 || i >=21 && i<=22) {
					weight+=25;
				}else {
					weight+=15;
				}
			}
		}
		
		return weight;
	}
	
	/*
	 * Calculates weight of the move based on the amount of blots you have on the board
	 */

	private int calculateBlot(int[] myBoard, int[][] checkers) {
		// check where blots have been made and how many have been made
		// if blots have been made in opponent base add negative else add nothing for normal blot
		int weight = 0;
		for(int i = 1;i<myBoard.length-3;i++) {
			if(myBoard[i] == 1) {
				if(i<= 6 && i>=1) {
					weight-=30;
				}else {
					weight-=20;
				}
			}
		}
		return weight;
	}

	/*
	 * Calculates weight of the move if it involves a hit
	 */
	private int calculateHit(int[] myBoard, int[][] checkers) {
		//if home board is strong give high value to hit else give normal value to hit
		int point = 0;
		int weight = 0;
		for(int i = 1;i<8;i++) {
			if(checkers[me.getId()][i] >= 2) {
				point++;
			}
		}
		if(point >=4) {
			weight+=25;
		}
		else {
			weight+=15;
		}
		return weight;
	}

}
