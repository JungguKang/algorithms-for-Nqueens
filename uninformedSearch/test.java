import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class test {

	public static void main(String[] args) throws IOException{
		
		int num = 0;
		String path = new String();
		String rst = new String();
		long atime, btime;

		if(args != null)
		{
			num = Integer.parseInt(args[0]);
			path = args[1];
		}
		else
			System.out.println("No arguements");
		
		new DFS(num).dfs();
		
		//Executing DFS
		DFS dfs = new DFS(num);
		atime = System.currentTimeMillis();
		rst += dfs.dfs();
		btime = System.currentTimeMillis();
		rst += "Time : "+ (btime-atime)/1000.0 +"\n";
		
		//Executing BFS
		BFS a = new BFS(num);
		atime = System.currentTimeMillis();
		rst += a.bfs();
		btime = System.currentTimeMillis();
		rst += "Time : "+ (btime-atime)/1000.0 +"\n";

		//Executing DFID
		DFID c = new DFID(num);
		atime = System.currentTimeMillis();
		rst += c.ids();
		btime = System.currentTimeMillis();
		rst += "Time : "+ (btime-atime)/1000.0 +"\n";
	
		
		try {
			OutputStream output = new FileOutputStream(path + "\\result"+num+".txt");
			byte[] by=rst.getBytes();
			output.write(by);
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
}
