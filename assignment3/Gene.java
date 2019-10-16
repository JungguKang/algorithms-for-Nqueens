import java.util.Random;

public class Gene {

	int num;
	int[] queens;
	
	public Gene(int num)
	{
		this.num = num;
		queens = new int[num];
	}
	
	
	

	public int[] getQueen()
	{
		return queens;
	}
	
	public void setQueen(int col, int val)
	{
		queens[col] = val;
	}
	public void init()
	{
		Random generator = new Random();
		int col;
		for(col = 0; col<num; col++)
		{
			this.queens[col] = generator.nextInt(num);
			
		}

	}
	
	public int check()
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
	
}
