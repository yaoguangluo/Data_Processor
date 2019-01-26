package imageProcessor;
public class PrintMoments{
	public void PrintCurrent(int[][]g,int pix) {
		double m00 = 0,m01 = 0,m02 = 0,m10 = 0,m11 = 0,m20 = 0;
		double p00 = 0,p01 = 0,p02 = 0,p10 = 0,p11 = 0,p20 = 0;
		for(int q=0;q<g.length;q++){
			for(int p=0;p<g[0].length;p++){
				if(g[q][p]==pix){
					m00=m00+Math.pow(q, 0)*Math.pow(p, 0);
					m01=m01+Math.pow(q, 0)*Math.pow(p, 1);
					m02=m02+Math.pow(q, 0)*Math.pow(p, 2);
					m10=m10+Math.pow(q, 1)*Math.pow(p, 0);
					m11=m11+Math.pow(q, 1)*Math.pow(p, 1);
					m20=m20+Math.pow(q, 2)*Math.pow(p, 0);
				}
			}
		}
		double mean_x= m10/m00, mean_y=m01/m00;
		for(int q=0;q<g.length;q++){
			for(int p=0;p<g[0].length;p++){
				if(g[q][p]==pix){
					p00= (p00+Math.pow(q-mean_x, 0)*Math.pow(p-mean_y, 0));
					p01= (p01+Math.pow(q-mean_x, 0)*Math.pow(p-mean_y, 1));
					p02= (p02+Math.pow(q-mean_x, 0)*Math.pow(p-mean_y, 2));
					p10= (p10+Math.pow(q-mean_x, 1)*Math.pow(p-mean_y, 0));
					p11= (p11+Math.pow(q-mean_x, 1)*Math.pow(p-mean_y, 1));
					p20= (p20+Math.pow(q-mean_x, 2)*Math.pow(p-mean_y, 0));  	 
				}
			}
		}
		System.out.println("region--->"+pix);
		System.out.println("p00------>"+p00);
		System.out.println("p01------>"+p01);
		System.out.println("p02------>"+p02);
		System.out.println("p10------>"+p10);
		System.out.println("p11------>"+p11);
		System.out.println("p20------>"+p20);
	}
}