import java.util.*;

public class BFS {
	private int num;
	public BFS(int num)
	{
		this.num = num;
	}

	//create queue 
	private  LinkedList<Node> queue = new LinkedList<Node>();
	
	/**
	 * this function search location of queen in bfs
	 * @param sendnode store answer location
	 * @return true if there is answer else, false
	 */
	public boolean search(Node sendnode)
	{
		int i, j;
		boolean isgoal = false;
		Node curr_node, child_node;
		//enqueue first level
		for(i=0; i<num; i++)
			queue.add(new Node(0,i, num));
		
		while(!queue.isEmpty())
		{
			//dequeue head node and enqueue adjacent nodes
			curr_node = queue.get(0);
			queue.remove(0);
			
			//expand dequeued node test, enqueue
			for(j=0; j<num; j++)
			{
				if(curr_node.showPos() == num-1)
				{
					isgoal = check.checkGoal(curr_node.queens);
					if(isgoal)		
					{
						System.arraycopy(curr_node.queens, 0, sendnode.queens,0, num);
						return true;
					}
				}
				//go to next node when can't expand
				if(!curr_node.expand())
					break;
				
				//expand and enqueue
				child_node = curr_node.expNode(j);
				queue.add(child_node);
			}
		}
		return false;
	}
	

	/**
	 * executing search() and return answer location
	 * @return answer string
	 */
	public String bfs()
	{
		String rst = ">BFS\n";
		Node testnode = new Node(num);
		if(search(testnode))
		{
			rst+= "Location :";
			for(int i=0 ; i<num; i++)
			{
				rst += " ";
				rst+=testnode.queens[i];
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
