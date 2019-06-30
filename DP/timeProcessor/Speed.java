package timeProcessor;
public class Speed{
	static int t= 0;
	public static void main(String[] argv){
		TimeCheck timeCheck= new TimeCheck();
		timeCheck.begin();
		for(int i= 0;i< 10000;i++) {
			for(int j= 0;j< 100;j++) {
				for(int k= 0;k< 10000;k++) {
					if(!(i> 20|| k< 50)) {
						t++;
					}
				}
			}
		}
		timeCheck.end();
		timeCheck.duration();
		System.out.println(t);
	}
}