import java.util.*;

class State {
	char[] board;
	int theoretic_value = 0;
	public State(char[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
	}

	public int getScore() {

		// TO DO: return game theoretic value of the board
		if(this.isTerminal()){
			int dark_num = 0;
			int light_num = 0;
			for (int i = 0; i < board.length; i++) {
				if (board[i] == '1') {
					dark_num++;
				} else if (board[i] == '2') {
					light_num++;
				}
			}
			if (dark_num > light_num) {
				this.theoretic_value = 1;
			} else if (light_num > dark_num) {
				this.theoretic_value = -1;
			}
		}
		
		return this.theoretic_value;
	}

	public boolean isTerminal() {

		// TO DO: determine if the board is a terminal node or not and return
		// boolean
		if(this.getSuccessors('1').equals(this.getSuccessors('2'))){
			return true;
		}
		for (int i = 0; i < board.length; i++) {
			if (board[i] == '0') {
				return false;
			}
		}
		return true;

		// return is_terminal;
	}

	public State[] getSuccessors(char player) {

        // TO DO: get all successors and return them in proper order
    	char opponent; 
    	
    	ArrayList<State> list = new ArrayList<State>();
    	if (player == '1'){
    		opponent = '2';
    	}
    	else{
    		opponent = '1';
    	}
    	for(int i = 0; i <board.length; i++){
    		ArrayList<Integer> flop = new ArrayList<Integer>();
    		if(board[i] == '0'){
    			State s = new State(board);
    			if (i%4 >= 2){ //left
    				if(board[i-1] == opponent && board[i-2] == player){
    					
    					flop.add(i-1);
    				}
    			}
    			if (i%4 < 2){ //right
    				if(board[i+1] == opponent && board[i+2] == player){
    					flop.add(i+1);
    				}
    			}
    			if (i/4 >= 2){ //up
    				if(board[i-4] == opponent && board[i-8] == player){
    					flop.add(i-4);
    			}
    			if (i/4 < 2){ //down
    				if(board[i+4] == opponent && board[i+8] == player){
    					flop.add(i+4);
    				}
    			}
    			
    			if (i%4 >= 1 && i/4 >= 1){ //left-up
    				if(board[i-5] == opponent && board[i-10] == player){
    					flop.add(i-5);
    				}
    			}
    			if (i%4 <= 2 && i/4 <= 2){ //right-down
    				if(board[i+5] == opponent && board[i+10] == player){
    					flop.add(i+5);
    				}
    			}
    			if (i%4 <= 2 && i/4 >= 1){ //right-up
    				if(board[i-3] == opponent && board[i-6] == player){
    					flop.add(i-3);
	    			}
    			}
    			if (i%4 >= 1 && i/4 <= 2){ //left-down
    				if(board[i+3] == opponent && board[i+6] == player){
    					flop.add(i+3);
    				}
    			}
    			
    		
    		}
    			if(flop.size() > 0){
    				for(Integer j:flop){
    					s.board[j] = player;
    				}
    				s.board[i] = player;
    				list.add(s);
    			}
    	}
    	if(list.size() == 0){
    		list.add(this);
    	}
    		
    	}
    	State[] successors = new State[list.size()];
		successors = list.toArray(successors);
		return successors;
    	
  
    }

	public void printState(int option, char player) {

		// TO DO: print a State based on option (flag)
		if(option == 1){
			
			State[] successors = this.getSuccessors(player);
			for(State s: successors){
				System.out.println(s.board);
			}
		}
		if(option == 2){
			if (!this.isTerminal()) {
				System.out.println("non-terminal");
			}
			else{
				System.out.println(this.getScore());
			}
		}
		if(option == 3){
			Minimax.states_num = 0;
			int first_line = Minimax.run(this, player);	
			System.out.println(first_line);
			System.out.println(Minimax.states_num);
			}
		if(option == 5){
			Minimax.states_num = 0;
			int first_line = Minimax.run_with_pruning(this, player);	
			System.out.println(first_line);
			System.out.println(Minimax.states_num);
		}
		if(option == 4){
			Minimax.run(this, player);
			System.out.println(Minimax.move.board);
		}
		if(option == 6){
			Minimax.run_with_pruning(this, player);
			System.out.println(Minimax.move.board);
			
		}
			
	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			builder.append(this.board[i]);
		}
		return builder.toString().trim();
	}

	public boolean equals(State src) {
		for (int i = 0; i < 16; i++) {
			if (this.board[i] != src.board[i])
				return false;
		}
		return true;
	}
}

class Minimax {
	public static int states_num = 0;
	public static State move;
	private static int max_value(State curr_state) {
		states_num ++;
		if(curr_state.isTerminal()){
			int terminal_value = curr_state.getScore();
			return terminal_value;
		}
		int alpha = Integer.MIN_VALUE;
		State[] successors = curr_state.getSuccessors('1');
		for(State s:successors){
			alpha = Math.max(alpha, min_value(s));
		}
		for(State s:successors){
			move = null;
			if(s.getScore() == alpha)
			{
				move = s;
				break;
			}
		}
		curr_state.theoretic_value = alpha;
		return alpha;
		// TO DO: implement Max-Value of the Minimax algorithm

	}

	private static int min_value(State curr_state) {
		states_num ++;
		if(curr_state.isTerminal()){
			int terminal_value = curr_state.getScore();
			return terminal_value;
		}
		int beta = Integer.MAX_VALUE;
		State[] successors = curr_state.getSuccessors('2');
		for(State s:successors){
			beta = Math.min(beta, max_value(s));
		}
		for(State s:successors){
			move=null;
			if(s.getScore() == beta){
				move = s;
				break;
			}
		}
		return beta;

		// TO DO: implement Min-Value of the Minimax algorithm

	}

	private static int max_value_with_pruning(State curr_state, int alpha, int beta) {
		states_num ++;
		int value = 0;
		if(curr_state.isTerminal()){
			int terminal_value = curr_state.getScore();
			return terminal_value;
		}
		State[] successors = curr_state.getSuccessors('1');
		for(State s:successors){
			alpha = Math.max(alpha, min_value_with_pruning(s, alpha, beta));
			if(alpha >= beta){
				value = beta;
				break;
			}
			value = alpha;
		}
		for(State s:successors){
			move = null;
			if(s.getScore() == value){
				move = s;
				break;
			}
		}
		curr_state.theoretic_value = value;
		return value;

		// TO DO: implement Max-Value of the alpha-beta pruning algorithm

	}

	private static int min_value_with_pruning(State curr_state, int alpha, int beta) {

		// TO DO: implement Min-Value of the alpha-beta pruning algorithm
		int value = 0;
		states_num ++;
		if(curr_state.isTerminal()){
			int terminal_value = curr_state.getScore();
			return terminal_value;
		}
		State[] successors = curr_state.getSuccessors('2');
		for(State s:successors){
			beta = Math.min(beta, max_value_with_pruning(s, alpha, beta));
			if(alpha >= beta){
				value = alpha;
				break;
			}
			value = beta;
		}
		for(State s:successors){
			move = null;
			if(s.getScore() == value){
				move = s;
				break;
			}
		}
		curr_state.theoretic_value = value;
		return value;
	}

	public static int run(State curr_state, char player) {

		// TO DO: run the Minimax algorithm and return the game theoretic value
		if(player == '1'){
			return max_value(curr_state);
		}
		else{
			return min_value(curr_state);
		}
	}

	public static int run_with_pruning(State curr_state, char player) {

		// TO DO: run the alpha-beta pruning algorithm and return the game
		// theoretic value
		if(player == '1'){
			return max_value_with_pruning(curr_state, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
		else{
			return min_value_with_pruning(curr_state, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
	}
}

public class Reversi {
	public static void main(String args[]) {
		if (args.length != 3) {
			System.out.println("Invalid Number of Input Arguments");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		char[] board = new char[16];
		for (int i = 0; i < 16; i++) {
			board[i] = args[2].charAt(i);
		}
		int option = flag / 100;
		char player = args[1].charAt(0);
		if ((player != '1' && player != '2') || args[1].length() != 1) {
			System.out.println("Invalid Player Input");
			return;
		}
		State init = new State(board);
		init.printState(option, player);
	}
}
