import java.util.*;

class State {
	int[] board;
	State parentPt;
	int depth;

	
	public State(int[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
		this.parentPt = null;
		this.depth = 0;
	}

	public void switch_Num(int i, int j)
	{
		this.board[i] = this.board[j];
		this.board[j] = 0;
	}
	public State swap_Num(int[] list, int empty, int num){
		State new_State = new State(list);
		new_State.board[empty] = list[num];
		new_State.board[num] = 0;
		return new_State;
	}
	
	public State[] getSuccessors() {
		//TO DO: get all four successors and return them in sorted order
			State[] successors = new State[4];
			successors[0] = new State(this.board);
			successors[1] = new State(this.board);
			successors[2] = new State(this.board);
			successors[3] = new State(this.board);
			for(int i = 0; i < 4; i++) {
				successors[i].depth = this.depth + 1;
				successors[i].parentPt = new State(this.board);
				successors[i].parentPt.depth = this.depth;
				successors[i].parentPt.parentPt = this.parentPt;
			}
			if(this.board[0] == 0)
			{
				successors[0].switch_Num(0, 1);
				successors[1].switch_Num(0, 2);
				successors[2].switch_Num(0, 3);
				successors[3].switch_Num(0, 6);
				int[] sort = {successors[0].getNumber(),successors[1].getNumber(),successors[2].getNumber(),successors[3].getNumber()};
				Arrays.sort(sort);
				for(int i = 0; i < 4; i++) {
					successors[i].put_Number(sort[i]);
				}
			}
			else if(this.board[1] == 0)
			{
				successors[0].switch_Num(1, 0);
				successors[1].switch_Num(1, 2);
				successors[2].switch_Num(1, 4);
				successors[3].switch_Num(1, 7);
				int[] sort = {successors[0].getNumber(),successors[1].getNumber(),successors[2].getNumber(),successors[3].getNumber()};
				Arrays.sort(sort);
				for(int i = 0; i < 4; i++) {
					successors[i].put_Number(sort[i]);
				}
			}
			else if(this.board[2] == 0)
			{
				successors[0].switch_Num(2, 0);
				successors[1].switch_Num(2, 1);
				successors[2].switch_Num(2, 5);
				successors[3].switch_Num(2, 8);
				int[] sort = {successors[0].getNumber(),successors[1].getNumber(),successors[2].getNumber(),successors[3].getNumber()};
				Arrays.sort(sort);
				for(int i = 0; i < 4; i++) {
					successors[i].put_Number(sort[i]);
				}
			}
			else if(this.board[3] == 0)
			{
				successors[0].switch_Num(3, 0);
				successors[1].switch_Num(3, 4);
				successors[2].switch_Num(3, 5);
				successors[3].switch_Num(3, 6);
				int[] sort = {successors[0].getNumber(),successors[1].getNumber(),successors[2].getNumber(),successors[3].getNumber()};
				Arrays.sort(sort);
				for(int i = 0; i < 4; i++) {
					successors[i].put_Number(sort[i]);
				}
			}
			else if(this.board[4] == 0)
			{
				successors[0].switch_Num(4, 1);
				successors[1].switch_Num(4, 3);
				successors[2].switch_Num(4, 5);
				successors[3].switch_Num(4, 7);
				int[] sort = {successors[0].getNumber(),successors[1].getNumber(),successors[2].getNumber(),successors[3].getNumber()};
				Arrays.sort(sort);
				for(int i = 0; i < 4; i++) {
					successors[i].put_Number(sort[i]);
				}
			}
			else if(this.board[5] == 0)
			{
				successors[0].switch_Num(5, 2);
				successors[1].switch_Num(5, 3);
				successors[2].switch_Num(5, 4);
				successors[3].switch_Num(5, 8);
				int[] sort = {successors[0].getNumber(),successors[1].getNumber(),successors[2].getNumber(),successors[3].getNumber()};
				Arrays.sort(sort);
				for(int i = 0; i < 4; i++) {
					successors[i].put_Number(sort[i]);
				}
			}
			else if(this.board[6] == 0)
			{
				successors[0].switch_Num(6, 0);
				successors[1].switch_Num(6, 3);
				successors[2].switch_Num(6, 7);
				successors[3].switch_Num(6, 8);
				int[] sort = {successors[0].getNumber(),successors[1].getNumber(),successors[2].getNumber(),successors[3].getNumber()};
				Arrays.sort(sort);
				for(int i = 0; i < 4; i++) {
					successors[i].put_Number(sort[i]);
				}
			}
			else if(this.board[7] == 0)
			{
				successors[0].switch_Num(7, 1);
				successors[1].switch_Num(7, 4);
				successors[2].switch_Num(7, 6);
				successors[3].switch_Num(7, 8);
				int[] sort = {successors[0].getNumber(),successors[1].getNumber(),successors[2].getNumber(),successors[3].getNumber()};
				Arrays.sort(sort);
				for(int i = 0; i < 4; i++) {
					successors[i].put_Number(sort[i]);
				}
			}
			else
			{
				successors[0].switch_Num(8, 2);
				successors[1].switch_Num(8, 5);
				successors[2].switch_Num(8, 6);
				successors[3].switch_Num(8, 7);
				int[] sort = {successors[0].getNumber(),successors[1].getNumber(),successors[2].getNumber(),successors[3].getNumber()};
				Arrays.sort(sort);
				for(int i = 0; i < 4; i++) {
					successors[i].put_Number(sort[i]);
				}
			}
			return successors;
		}
			public State[] sort_State(State[] successors){    	
				long[] sort_Int = new long[4];
				long finalInt = 0;
				for (int i = 0; i < 4; i++){
					StringBuilder strb = new StringBuilder();
					for (int j = 0; j < 9; j++){
						strb.append(successors[i].board[j]);
						finalInt = Long.parseLong(strb.toString());     	   
					}
					sort_Int[i] = finalInt;  
					finalInt = 0;
				}
				Arrays.sort(sort_Int);  

				for (int i = 0; i < 4; i++){ // 4 long number 
					int j = 8;
					while (sort_Int[i]>0){
						successors[i].board[j] = (int) (sort_Int[i] % 10);
						sort_Int[i] = sort_Int[i] / 10;
						j--;
					}
				}      
				return successors;
			}
			public void put_Number(int num) {
				String number = "";
				if(num > 100000000) {
					number = num + "";
				}
				else {
					number = "0" + num;
				}
				for(int i = 0; i < 9; i++) {
					this.board[i] = Character.getNumericValue(number.charAt(i));
				}
			}

			public void printState(int option) {

				// TO DO: print a torus State based on option (flag)
				if((option == 1)||(option == 2)||(option == 4))
				{
					System.out.println(this.getBoard());
				}
				else if(option == 3)
				{
					System.out.println(this.getBoard() + " parent " + this.parentPt.getBoard());
				}
			}

			public String getBoard() {
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < 9; i++) {
					builder.append(this.board[i]).append(" ");
				}
				return builder.toString().trim();
			}

			public boolean isGoalState() {
				for (int i = 0; i < 9; i++) {
					if (this.board[i] != (i + 1) % 9)
						return false;
				}
				return true;
			}
			public int getNumber() {
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < 9; i++) {
					builder.append(this.board[i]).append("");
				}
				return Integer.parseInt(builder.toString().trim());
			}

			

			public boolean equals(State src) {
				for (int i = 0; i < 9; i++) {
					if (this.board[i] != src.board[i])
						return false;
				}
				return true;
			}
		}

		public class Torus {

			public static void main(String args[]) {
				if (args.length < 10) {
					System.out.println("Invalid Input");
					return;
				}
				int flag = Integer.valueOf(args[0]);
				int[] board = new int[9];
				for (int i = 0; i < 9; i++) {
					board[i] = Integer.valueOf(args[i + 1]);
				}
				int option = flag / 100;
				int cutoff = flag % 100;
				if (option == 1) {
					State init = new State(board);
					State[] successors = init.getSuccessors();
					for (State successor : successors) {
						successor.printState(option);
					}
				} else {
					int goalCheck = 0;
					State init = new State(board);
					Stack<State> stack = new Stack<>();
					List<State> prefix = new ArrayList<>();
					boolean succeed = false;
					int maxStackSize = Integer.MIN_VALUE;
					int[] spaceHolder = {0,0,0,0,0,0,0,0,0};
					State noParentNode = new State(spaceHolder);
					init.parentPt = noParentNode;
					if((option == 2)||(option == 3)) {
						stack.push(init);
						int i = 0;
						State toCheck = null;
						State lastCheck = null;
						while(i < cutoff) {
							State currState = stack.pop();
							currState.printState(option);
							prefix.add(currState);
							if(currState.isGoalState()) {
								succeed = true;
								break;
							}
							goalCheck ++;
							if(currState.depth == 0) {
								for(int j = 0; j < 4; j++) {
									stack.push(init.getSuccessors()[j]);
								}
							}
							else {
								for(int k = 0; k < 4; k++) {
									if(!check_Prefix(prefix,currState.getSuccessors()[k]))
									{
										stack.push(currState.getSuccessors()[k]);
									}
								}		
							}
							i++;
						}		
						while ((!stack.isEmpty()) && (!succeed)) {
							lastCheck = toCheck;
							toCheck = stack.pop();
							if(toCheck.isGoalState()) {
								toCheck.printState(option);
								succeed = true;
								break;
							}
							goalCheck++;
							toCheck.printState(option);
							if(toCheck.depth < cutoff) {
								if(!((toCheck.depth > lastCheck.depth)&&(toCheck.depth != cutoff))) {
									for(int m = 0; m < (lastCheck.depth - toCheck.depth); m++)
									{
										prefix.remove(prefix.size() - 1);
									}
								}
								prefix.add(toCheck);
								for(int k = 0; k < 4; k++) {
									if(!check_Prefix(prefix,toCheck.getSuccessors()[k]))
									{
										stack.push(toCheck.getSuccessors()[k]);
									}
								}
							}
						}
					}
					else if(option == 4) {
						stack.push(init);
						int i = 0;
						while(i < cutoff) {
							State toCheck = stack.pop();
							prefix.add(toCheck);
							if(toCheck.isGoalState()) {
								succeed= true;
								goalCheck++;
								break;
							}
							
							if(toCheck.depth == 0)
							{
								for(int j = 0; j < 4; j++) {
									stack.push(init.getSuccessors()[j]);
								}
							}
							else
							{
								for(int k = 0; k < 4; k++) {
									if(!check_Prefix(prefix,toCheck.getSuccessors()[k]))
									{
										stack.push(toCheck.getSuccessors()[k]);
									}
								}
							}
							i++;
						}
						if(!succeed) {
							prefix.add(stack.pop());
						}
						Iterator<State> iter = prefix.iterator();
						while(iter.hasNext()) {
							iter.next().printState(option);
						}
					}
					else {			
		          
		                goalCheck = 0;
		                int maxStack = 0;
		                int currentStack = 0;
		                int cutOff = 0;
		                while(!succeed) {
		                	prefix = new ArrayList<>();
		                	maxStack = 0;
		                	currentStack = 0;
		                	stack.push(init);
		                	currentStack ++;
		                	if(currentStack > maxStack) {
		                		maxStack = currentStack;
		                	}
		    				int i = 0;
		    				State toCheck = null;
		    				State lastCheck = null;
		    				while(i < cutOff) {
		    					State toCheck1 = stack.pop();
		    					currentStack --;
		    					prefix.add(toCheck1);
		    					if(toCheck1.isGoalState()) {
		    						succeed = true;
		    						goalCheck ++;
		    						break;
		    					}
		    					goalCheck ++;
		    					if(toCheck1.depth == 0)
		    					{
		    						for(int j = 0; j < 4; j++) {
		    							stack.push(init.getSuccessors()[j]);
		    							currentStack ++;
		    							if(currentStack > maxStack) {
		    		                		maxStack = currentStack;
		    		                	}
		    						}
		    					}
		    					else
		    					{
		    						for(int k = 0; k < 4; k++) {
		    							if(!check_Prefix(prefix,toCheck1.getSuccessors()[k]))
		    							{
		    								stack.push(toCheck1.getSuccessors()[k]);
		    								currentStack ++;
		    								if(currentStack > maxStack) {
		    			                		maxStack = currentStack;
		    			                	}
		    							}
		    						}
		    						
		    					}
		    					i++;
		    				}
		    				
		    				while ((!stack.isEmpty())&&(!succeed)) {
		    					lastCheck = toCheck;
		    					toCheck = stack.pop();
		    					currentStack --;
		    					if(toCheck.isGoalState()) {
		    						succeed = true;
		    						prefix.add(toCheck);
		    						goalCheck++;
		    						break;
		    					}
		    					goalCheck++;
		    					if(toCheck.depth < cutOff) {
		    						if(!((toCheck.depth > lastCheck.depth)&&(toCheck.depth != cutoff))) {
		    							for(int m = 0; m < (lastCheck.depth - toCheck.depth); m++)
		    							{
		    								prefix.remove(prefix.size() - 1);
		    							}
		    						}
		    						prefix.add(toCheck);
		    						for(int k = 0; k < 4; k++) {
		    							if(!check_Prefix(prefix,toCheck.getSuccessors()[k]))
		    							{
		    								stack.push(toCheck.getSuccessors()[k]);
		    								currentStack++;
		    								if(currentStack > maxStack) {
		    			                		maxStack = currentStack;
		    			                	}
		    							}
		    						}
		    					}
		    				}
		    				cutOff ++;
		                }
						Iterator<State> iter1 = prefix.iterator();
						while(iter1.hasNext()) {
							iter1.next().printState(2);
						}
						System.out.println("Goal-check " + goalCheck);
						System.out.println("Max-stack-size " + maxStack);
					}					
				}
			}
			public static boolean check_Prefix (List<State> prefix, State curr){		
				Iterator<State> itr = prefix.iterator();
				while (itr.hasNext()){
					State check = itr.next();
					if (check.equals(curr)){
						return true;
					}
				}		 
				return false;		
			}
		}