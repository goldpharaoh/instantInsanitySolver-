import java.lang.Math;
import java.io.*;
import java.util.*;

//NOTE: all second order solutions will be greater than first order solutions by the fact that all possibilities less than the first order solution have already been checked
class stackTrace extends Object{
	int array;
	int index;
	
	public stackTrace(int array,int index){
		this.array = array;
		this.index=index;
	}
	
	public void push(Stack myStack){
		myStack.push(this);
	}
	
	public int getArray() {
		return array;
	}
	
	public int getIndex() {
		return index;
	}
	
	public static void push(Stack myStack, stackTrace tr){
		myStack.push(tr);
	}
	
	public static stackTrace pop(Stack myStack){
		return (stackTrace)myStack.pop();
	}
	
}

class solver{
		
	int[][] a0;
	int[][] a1;
	int[][] a2;
	
	int[] solution0;
	int[] solution1;
	
	boolean startseen = false;
	
	Stack stack0;
	Stack stack1;
	
	Vector secondOrderStore;
	
	public solver(String filename){
		int problemSize = insanitySize(filename);
		if (problemSize==-1) System.exit(-1);
		a0 = new int[problemSize][2];
		a1 = new int[problemSize][2];
		a2 = new int[problemSize][2];
		
		solution0= new int[problemSize];
		solution1= new int[problemSize];
		
		stack0=new Stack();
		stack1=new Stack();
		
		secondOrderStore = new Vector();
		
		getData(filename);
		
		//specifically initialize solution0;
		for (int i=0; i< problemSize; i++) solution0[i]=0;
	}
	
	public void getFirstSolution(){
		boolean val = getSolution(solution0);
		boolean solved = false;

		for (int i=0; i< solution0.length; i++){
			System.out.print(solution0[i] + ",");
		}
		System.out.println();
		
		for (int i=0; i<solution1.length; i++){
			solution1[i]=solution0[i];
		}
		
		solution1 = get_next_test(solution1);
		boolean val2=getSolution(solution1);
		
		for (int i=0; i< solution0.length; i++){
			System.out.print(solution1[i] + ",");
		}
		System.out.println();

		solved=isSolution(solution0,solution1);
		if (solved) {
			System.out.println();
			System.out.println("Solution found");
			for (int i=0; i< solution0.length; i++){
				System.out.print(solution0[i] + ",");
			}
			System.out.println();
			for (int i=0; i< solution0.length; i++){
				System.out.print(solution1[i] + ",");
			}
			System.out.println();
			System.out.println();
			return;
		}
		
		while (!solved){
		
			int[] temp = solution0.clone();
			secondOrderStore.add(temp);
		
			solution0 = solution1.clone();
		
			solution1 = get_next_test(solution1);
			val2=getSolution(solution1);
		
			for (int i=0; i< solution0.length; i++){
				System.out.print(solution1[i] + ",");
			}
			System.out.println();
			
			solved=isSolution(solution0,solution1);
			if (solved) {
				System.out.println();
				System.out.println("Solution found");
				for (int i=0; i< solution0.length; i++){
					System.out.print(solution0[i] + ",");
				}
				System.out.println();
				for (int i=0; i< solution0.length; i++){
					System.out.print(solution1[i] + ",");
				}
				System.out.println();
				System.out.println();
				return;
			}
			int[] tmp2 = isSolution(solution0,secondOrderStore);
			if (tmp2 != null){
				System.out.println();
				System.out.println("Solution found");
				for (int i=0; i< solution0.length; i++){
					System.out.print(solution0[i] + ",");
				}
				System.out.println();
				for (int i=0; i< solution0.length; i++){
					System.out.print(tmp2[i] + ",");
				}
				System.out.println();
				System.out.println();
				return;
			}
		}		
	}


	public void getAllSolutions(){
		boolean val = getSolution(solution0);
		boolean solved = false;
		
		boolean booltemp = true;

		for (int i=0; i< solution0.length; i++){
			System.out.print(solution0[i] + ",");
		}
		System.out.println();
				
		for (int i=0; i<solution1.length; i++){
			solution1[i]=solution0[i];
		}
		
		solution1 = get_next_test(solution1);
		boolean val2=getSolution(solution1);
		
		for (int i=0; i< solution0.length; i++){
			System.out.print(solution1[i] + ",");
		}
		System.out.println();
		
		
		solved=isSolution(solution0,solution1);
		if (solved) {
			System.out.println();
			System.out.println("Solution found");
			for (int i=0; i< solution0.length; i++){
				System.out.print(solution0[i] + ",");
			}
			System.out.println();
			for (int i=0; i< solution0.length; i++){
				System.out.print(solution1[i] + ",");
			}
			System.out.println();
			System.out.println();
			return;
		}
		while (!startseen) {
		 solved=false;
		while (!solved){
		
			int[] temp = solution0.clone();
			secondOrderStore.add(temp);
		
			solution0 = solution1.clone();
		
			solution1 = get_next_test(solution1);
			val2=getSolution(solution1);
			for (int i=0; i< solution0.length; i++){
				System.out.print(solution1[i] + ",");
				if (solution1[i] != 0) booltemp = false;
			}
			if (booltemp) {
				System.out.println(" ... END OF DATA SET");
				startseen = true;
				break;
			}
			System.out.println();
			booltemp = true;
			solved=isSolution(solution0,solution1);
			if (solved) {
				System.out.println();
				System.out.println("Solution found");
				for (int i=0; i< solution0.length; i++){
					System.out.print(solution0[i] + ",");
				}
				System.out.println();
				for (int i=0; i< solution0.length; i++){
					System.out.print(solution1[i] + ",");
				}
				System.out.println();
				System.out.println();
				break;
			}
			int[] tmp2 = isSolution(solution1,secondOrderStore);
			if (tmp2 != null){
				System.out.println();
				System.out.println("Solution found");
				for (int i=0; i< solution0.length; i++){
					System.out.print(solution0[i] + ",");
				}
				System.out.println();
				for (int i=0; i< solution0.length; i++){
					System.out.print(tmp2[i] + ",");
				}
				System.out.println();
				System.out.println();
				break;
			}
		}		
		}
	}


	//returns the size of the problem as specifiedby lines of the file
	private int insanitySize(String filename){	
		try { 
			BufferedReader in1 = new BufferedReader(new FileReader(filename));
			int size = 0;
			String temp = in1.readLine();
			while (temp!=null){
				size++;
				temp = in1.readLine();
			}
			in1.close();	
			return size;
		} catch (Exception FileOneNotFound) {System.exit(0);}
		return -1;
	}
	
	//gets the data from the file and puts it into the appropriate arrays
	private void getData(String filename){
		try { 
			BufferedReader in1 = new BufferedReader(new FileReader(filename));
			int index = 0;
			String temp = in1.readLine();
			while (temp!=null){
				if (temp.charAt(0) == '(') {
					temp = temp.substring(1,temp.length()-1);
				}
				
				String[] numsString = temp.split(",");
				for (int i =0; i < numsString.length; i++) numsString[i] = numsString[i].trim();
				
				int[] nums = new int[6];
				for (int i=0;i<6;i++){
					nums[i] = Integer.parseInt(numsString[i]) - 1; //to correct for indexing
				}
				a0[index][0] = nums[0];
				a0[index][1] = nums[1];
				a1[index][0] = nums[2];
				a1[index][1] = nums[3];
				a2[index][0] = nums[4];
				a2[index][1] = nums[5];
				index++;
				temp = in1.readLine();
			}
			in1.close();	
		} catch (Exception FileOneNotFound) {System.exit(0);}
	}
	
	
	//determines if the passed array represents an acceptable solution
	// USED FOR TESTING ONLY
	public boolean acceptable(int[] arg){
		int[] tracker = new int[arg.length];
		int which_array;
		
		for (int i = 0; i < arg.length; i++){
			which_array = arg[i];
			if (which_array == 0) {
				tracker[a0[i][0]]++;
				tracker[a0[i][1]]++;
				if 	(tracker[a0[i][0]] > 2) return false;
				if 	(tracker[a0[i][1]] > 2) return false;
			}
			else if (which_array == 1) {
				tracker[a1[i][0]]++;
				tracker[a1[i][1]]++;
				if 	(tracker[a1[i][0]] > 2) return false;
				if 	(tracker[a1[i][1]] > 2) return false;
			}
			else {
				tracker[a2[i][0]]++;
				tracker[a2[i][1]]++;
				if 	(tracker[a2[i][0]] > 2) return false;
				if 	(tracker[a2[i][1]] > 2) return false;
			}
		}
		return true;	
	}
	
	
	//returns true if the two arrays passed to it don't share any values in common at each level
	public boolean isSolution(int[] in1, int[] in2){
		for (int i =0; i<in1.length; i++){
			if (in1[i]==in2[i]) return false;
		}
		return true;
	}
	
	
	//returns true if the two arrays passed to it don't share any values in common at each level
	public int[] isSolution(int[] in1, Vector in2){
		
		int ln = in2.toArray().length;
		for (int j = 0; j<ln; j++){
			boolean good = true;
			int[] myTest = (int[])in2.get(j);
			for (int i =0; i<in1.length; i++){
				if (in1[i]==myTest[i]) { 
					good=false;
					break;
				}
			}
			if (good) {
				return myTest;
			}
		}
		return null;
	}
	
	
	private boolean getSolution(int[] solution0){
		int[] edges = new int[solution0.length];
		for (int i=0; i<edges.length; i++) edges[i] = 0;
		boolean val = getSolution(solution0,edges,0);
		return val;
	}	
	
	
	private boolean getSolution(int[] solution0, int[] edges, int level){
		boolean solved = true;
		if (level==edges.length) return true;
		do {
			if (!solved) {
				stackTrace myTrace = stackTrace.pop(stack0);
				int ar = myTrace.getArray();
				int in = myTrace.getIndex();
				if (ar==0){
					edges[a0[in][0]]--;
					edges[a0[in][1]]--;
					solution0[in]++;
				}
				if (ar==1){
					edges[a1[in][0]]--;
					edges[a1[in][1]]--;
					solution0[in]++;
				}
				if (ar==2){
					edges[a2[in][0]]--;
					edges[a2[in][1]]--;
					solution0[in]=0;
					return false;
				}
			}
			if (solution0[level]==0) {
				
				if ((a0[level][0]==a0[level][1])&&(edges[a0[level][0]]>=1)) {
						solution0[level]++;
				}
				
				else if ((edges[a0[level][0]] < 2) && (edges[a0[level][1]] < 2)){
					new stackTrace(0,level).push(stack0);
					edges[a0[level][0]]++;
					edges[a0[level][1]]++;	
				}
				
				else {
					solution0[level]++;
				}
			}
			if (solution0[level]==1) {
				if ((a1[level][0]==a1[level][1])&&(edges[a1[level][0]]>=1)) {
						solution0[level]++;
				}
				else if ((edges[a1[level][0]] < 2) && (edges[a1[level][1]] < 2)){
					new stackTrace(1,level).push(stack0);	
					edges[a1[level][0]]++;
					edges[a1[level][1]]++;	
				}
				else {
					solution0[level]++;
				}
			}
			if (solution0[level]==2) {
				if ((a2[level][0]==a2[level][1])&&(edges[a2[level][0]]>=1)) {
					solution0[level] = 0;
					return false;
				}
				else if ((edges[a2[level][0]] < 2) && (edges[a2[level][1]] < 2)){
					new stackTrace(2,level).push(stack0);	
					edges[a2[level][0]]++;
					edges[a2[level][1]]++;	
				}
				else {
					solution0[level] = 0;
					return false;
				}
			}
			solved = getSolution(solution0, edges, level+1);
		} while (!solved);
		return true;
	}	

	//returns the next puzzle orientation to test (solution store)
	public int[] get_next_test(int[] in){
		int[] out = in;		
		boolean carry = true;
		int index = out.length - 1;				
		while (carry){
			if (index<0) return null;
			out[index] += 1;
			if (out[index] % 3 == 0) {
				out[index--] = 0;
				carry=true;
			} 
			else carry=false;			
		}
		return out;
	}
	
	//given a solution array (which simply indicates which array side to choose) this function converts the solution back to the actual values
	public int[][] convertSolutionToValues(int[] sol){
		int[][] solved = new int[sol.length][sol.length];
		for (int i = 0; i < sol.length; i++){
			if (sol[i]==0) {
				solved[i][0]=a0[i][0];
				solved[i][1]=a0[i][1];
			}
			else if (sol[i]==1) {
				solved[i][0]=a1[i][0];
				solved[i][1]=a1[i][1];
			}
			else if (sol[i]==2) {
				solved[i][0]=a2[i][0];
				solved[i][1]=a2[i][1];
			}
		}
		return solved;
	} 

	
	public static void main(String[] args){
		long tm = System.currentTimeMillis();
		solver mySolver = new solver("cubes.txt");
		mySolver.getAllSolutions();
		long tm2 = System.currentTimeMillis();
		System.out.println();
		long seconds = (tm2-tm)/1000;
		long h_hours = seconds/3600;
		long m_minutes =(seconds - (h_hours*3600))/60;
		long s_seconds = seconds - (h_hours*3600) - (m_minutes*60);
		System.out.println( "Time elapsed in = " + h_hours + ":" + m_minutes + ":" + s_seconds );
		System.exit(0);
	}
}