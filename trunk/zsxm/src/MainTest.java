import com.group.zsxm.Job.SchedulerJob;


public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xx = "A1# ";
		
		System.out.println(xx.split("#")[1].trim());
	}
	
	public static int xx(int v){
		if(v == 0){
			return v;
		}else {
			int x = v*xx(v-1);
			System.out.println(x);
			return x;
		}
	}
}
