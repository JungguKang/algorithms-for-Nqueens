import java.util.Random;

public class HC {
	
	int num;
	public HC(int num)
	{
		this.num = num;
	}
	
	/**
	 * initialize the array with random number
	 * @return		array with random number
	 */
	public int[] restart() 
	{
		int queens[] = new int[num];
		Random generator = new Random();
		int col;
		for(col = 0; col<num; col++)
		{
			queens[col] = generator.nextInt(num);
		}
		return queens;
	}
	
	/**
	 * measure the score that determines what's the next step
	 * if there is attackable queen on certain queen add score
	 * @param		queens
	 * @return		value of the steps
	 */
	public int check(int[] queens)
	{
		int i, j, points=0;
		int num = queens.length;
		
		for(i=0; i<num; i++)
		{
			for(j = i+1; j<num; j++)
			{
				if(queens[i] == queens[j])
					points++;
				if((j - i) == Math.abs(queens[j] - queens[i]))
					points++;
			}
		}
		return points;
	}
	
	/**
	 * search for n-queens problem with hill-climbing algorithm
	 * restarts when it meets local maximum
	 * returns answer when it meets maximum
	 * @return		answer string
	 */
	public String search()
	{

		int i, j, score = 0, initscore;
		String rst = new String();
		
		int[] queens = restart();
		
		//loop until find answer of N-Queens
		while(true)
		{
			initscore = check(queens);
			score = initscore;
			//climb the hill
			score = nextStep(queens, score);
			
			//state when it's top of hill
			if(initscore == score)
			{
				
				//if it is local max, restart
				if(check(queens) != 0)
				{
					queens = restart();
				}
				
				//return answer if it is total max
				else
				{
					rst += queens[0];
					for(i=1; i<num; i++)
						rst += " "+queens[i];
					rst += "\n";
					return rst;
				}
				
			}
		}

	}
	
	/**
	 * determines what is the next step
	 * moves a queen better place
	 * uses first-choice hill climbing
	 * @param queens	location of queens
	 * @param score		score of current state
	 * @return			score of next state
	 */
	public int nextStep(int[] queens, int score)
	{
		int i, j, checkscore;
		for(i=0; i<num; i++)
		{
			for(j=0; j<num; j++)
			{
				queens[i]++;
				if(queens[i] >= num)
					queens[i] = queens[i]%num;
				checkscore = check(queens);
				if(checkscore < score)
				{
					score = checkscore;
					return score;
				}
			}			
		}
		return score;
	}
	
}
