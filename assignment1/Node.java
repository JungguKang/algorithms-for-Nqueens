

public class Node {

	public int num;
	public int[] queens;
	public int pos;
	
	
	public Node(int pos, int val, int num)
	{
		this.num = num;
		this.queens = new int[num];
		this.queens[pos] = val;
		this.pos = pos;
	}
	public Node(int num)
	{
		this.num = num;
		this.queens = new int[num];
	}
	public int showPos()
	{
		return this.pos;
	}

	/**
	 * this function decides whether the node can expand or not
	 * @return true if node can expand, else false
	 */
	public boolean expand()
	{
		if(this.pos < num-1)
		{
			return true;
		}
		else
			return false;
	}
	
	/**
	 * defines child node 
	 * increase position
	 * and return
	 * @param nextval gets child nodes value
	 * @return child node
	 */
	public Node expNode(int nextval)
	{
		Node newnode = new Node(num);
		System.arraycopy(this.queens, 0, newnode.queens, 0, num);
		newnode.pos = this.pos + 1;
		newnode.queens[newnode.pos] = nextval; 
		newnode.num = this.num;
		return newnode;
	}
	
	

	
}
