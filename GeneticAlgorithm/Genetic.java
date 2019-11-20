import java.util.Random;
import java.util.LinkedList;

public class Genetic {

	//population of a generation
	int population = 100;
	int num;
	
	//k-tournament selection
	int k = 10;
	
	int restarted=0, generation=0;
	
	//probability of mutation
	int mut_possiblity = 100;
	
	Random generator = new Random();
	
	public Genetic(int num)
	{
		this.num = num;
	}
	
	public int getRestarted()
	{
		return this.restarted;
	}
	
	public int getGeneration()
	{
		return generation;
	}
	
	/**
	 * select 10% of the population to make the parents
	 * with k-tournament selection
	 * @param curr_pool		previous generation pool
	 * @return				the selected 10%
	 */
	public LinkedList<Gene> select(LinkedList<Gene> curr_pool)
	{
		int i, point=10000 , selected = 0, j;
		//LinkedList<Gene> next_pool = new LinkedList<Gene>();
		
		//only 10% of population goes directly to next generation
		for(i=0; i<population/10; i++)
		{
			point = 10000;
			//check k individuals front of pool
			for(j=0; j<k; j++)
			{
				if(curr_pool.get(j).check() < point)
				{
					selected = j;
					point = curr_pool.get(j).check();
				}
			}
			for(j=0; j<k; j++)
			{
				if(j == selected)
					curr_pool.add(curr_pool.remove(0));
				else
					curr_pool.remove(0);
			}
		}
		for(i=0; i<(population - k*population/10);i++)
			curr_pool.remove(0);
	
		return curr_pool;
	}
	
	/**
	 * generating next generation pool with selected 10% parents
	 * select two parents and generate random integer n
	 * integrate two parent's queens array
	 * with first parent's 0 ~ n-1 column and
	 * second parent's n-1 ~ num column
	 * then insert the generated child individual in pool
	 * @param curr_pool		get pool of selected parents
	 * @return curr_pool	return pool of total pool
	 */
	public LinkedList<Gene> crossOver(LinkedList<Gene> curr_pool)
	{
		Gene a, b, temp;
		int i,j, ran;
		int[] tempQ1, tempQ2;
		
		for(i=0; i<population*9/10; i++)
		{
			ran = generator.nextInt(num);
			a = curr_pool.get(generator.nextInt(population/10));
			b = curr_pool.get(generator.nextInt(population/10));
			
			tempQ1 = a.getQueen();
			tempQ2 = b.getQueen();
	
			temp = new Gene(num);
			
			for(j=0; j<ran; j++)
			{
				temp.setQueen(j, tempQ1[j]);
			}
			
			for(j=ran; j<num; j++)
			{
				temp.setQueen(j, tempQ2[j]);
			}
			
			curr_pool.add(temp);
		}
		return curr_pool;
	}	
	

	/**
	 * mutating the population
	 * of percentage of mut_possibility
	 * @param curr_pool		pool of individuals
	 * @return curr_pool	mutated pool of individuals
	 */
	public LinkedList<Gene> mutate(LinkedList<Gene> curr_pool)
	{
		int i;
		for(i=population/10; i<population; i++)
			if(generator.nextInt(100) < mut_possiblity)
			{
				
				//change one value of board in random location to random number
				curr_pool.get(i).setQueen(generator.nextInt(num), generator.nextInt(num));
			}
		return curr_pool;
	}
	
	/**
	 * initiate all individuals in pool randomly
	 * this method is used when GA can't escape local maximum
	 * @param pool
	 * @return
	 */
	public LinkedList<Gene> restart(LinkedList<Gene> pool)
	{
		Gene newboard = new Gene(num);
		pool = new LinkedList<Gene>();
		int i;
		
		for(i=0; i<population; i++)
		{
			newboard = new Gene(num);
			newboard.init();
			pool.add(newboard);
		}
		return pool;
	}
	

	
	public String search()
	{
		LinkedList<Gene> pool = new LinkedList<Gene>();

		int i, s_generation = 0;
		int[] answerQ = new int[num];
		String rst = new String();
		
		//generate first generation individuals
		pool = restart(pool);
		while(true)
		{	
			//this code is for the case of local maximum
			//try this if the code can't escape the loop
			/*
			if(s_generation > 1000)
			{
				pool = restart(pool);
				s_generation = 0;
			}
			s_generation++;
			*/
			
			//check if there is suitable board in pool
			for(i=0; i<population ; i++)
			{
				if(pool.get(i).check() == 0)
				{
					answerQ = pool.get(i).getQueen();
					for(int j=0; j<num; j++)						
						rst += answerQ[j]+" ";
					rst+="\n";
					
					return rst;
				}
					
			}
			
			//select the better ones
			//the pool population decreases for 10%
			pool = select(pool);

			//crossover the parents that is 10% of population
			//create 90% of population with 10%
			pool = crossOver(pool);

			//mutate the pool
			pool = mutate(pool);


		}

	}
	
}
