
public class DFS {
	
	int num;
	int[] queens;
	
	public DFS(int num)
	{
		this.num = num;
		this.queens = new int[num];
	}

	/**
	 * recursive dfs function
	 * @param queens acts like node which stores queens location
	 * @param num number of queens
	 * @param row location of rows on the board
	 * @return if there is answer
	 */
	public static boolean rdfs(int[] queens, int num, int row)
	{
		int col;
		boolean didgoal;
		
		if(row == num)
		{
			if(check.checkGoal(queens))
				return true;
				
			return false;
		}
		
		//expand
		for(col = 0; col < num; col++)
		{
			queens[row] = col;
			didgoal = rdfs(queens, num, row+1);
			if(didgoal)
				return true;
		}
		return false;
	}
	
	/**
	 * executing rdfs()
	 * @return answer string
	 */
	public String dfs()
	{
		String rst = ">DFS\n";
		
		if(rdfs(queens, num, 0))
		{
			rst+= "Location :";
			for(int i=0 ; i<num; i++)
			{
				rst += " ";
				rst+=Integer.toString(queens[i]);
			}
			rst+="\n";
			return rst;
		}
		else
		{
			rst += "No Solution\n";
			return rst;
		}
	}
}
