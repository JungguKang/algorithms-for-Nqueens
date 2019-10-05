
public class check {
	public static boolean checkGoal(int[] queens)
	{
		int len = queens.length, i, j;
		
		for(i=0; i < len; i++)
		{
			j = i+1;
			while(j < len)
			{
				//check if there is queen in same line
				if(queens[i] == queens[j])
					return false;
				
				//check if there is queen in diagonal position
				if((j - i) == Math.abs(queens[j] - queens[i]))
					return false;
				j++;
			}
		}		
		return true;
	}
	
}
