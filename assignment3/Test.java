import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test {

	public static void main(String[] args)throws IOException
	{
		int num = 10;
		String rst = new String();
		String path = new String();
		double atime, btime;
		
		
		if(args != null)
		{
			num = Integer.parseInt(args[0]);
			path = args[1];
		}
		else
			System.out.println("No arguements");
		
		
		//executing genetic search
		Genetic a = new Genetic(num);
		rst += ">Genetic Algorithm\n";
		atime = System.currentTimeMillis();
		rst += a.search();
		btime = System.currentTimeMillis();
		rst += "Total Elapsed Time : "+ (btime-atime)/1000.0 +"\n";
		

		
		try {
			OutputStream output = new FileOutputStream(path + "\\result"+num+".txt");
			byte[] by=rst.getBytes();
			output.write(by);
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
}
