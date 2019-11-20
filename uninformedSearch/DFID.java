
public class DFID {

	int num;
	public DFID(int num)
	{
		this.num = num;
	}
	
	public boolean rdls(int[] queens, int limit, int row)
	{
		int col;
		boolean rst;
		if(row==num)
		{
			if(check.checkGoal(queens))
				return true;
			else
				return false;
		}
		
		//has to search until limit
		//if limit is reached, return
		if(!(row<limit))
		{
			return false;
		}
		
		//expand node
		for(col=0; col<num; col++)
		{
			queens[row] = col;
			rst = rdls(queens, limit, row+1);
			
			if (rst)
				return true;				
		}
		//all node searched
		
		return false;
	}
	
	/**
	 * executing rdls()
	 * @param limit predefined limit of tree
	 * @param queens location of queens
	 * @return if there is answer
	 */
	public boolean dls(int limit, int[] queens)
	{
		
		boolean rst;
		rst = rdls(queens, limit, 0);
		return rst;
	}
	
	/**
	 * executing dls()
	 * @return answer string
	 */
	public String ids()
	{
		int limit;
		int[] queens = new int[num];
		String rst = ">DFID\n";
		
		//increase limit 
		for(limit = 0; limit<=num; limit++)
		{
			if(dls(limit, queens))
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
		
		}
		rst += "No Solution\n";
		return rst;
	}
}
