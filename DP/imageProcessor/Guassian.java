package imageProcessor;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Guassian {
	public int[][] Processor2D(int[][] g, int d, int e, double sig) throws IOException {
		int[][] flac_grn= new Reflection().PadImage(g, d, e); 
		double kenel[][]= new double[d][e];
		//GAUSSIAN KENEL
		double sumhere= 0;
		double t= 0;
		for(int k= 0; k<d; ++k){
			for(int l= 0; l<e; ++l){    	    
				t= (Math.pow(k-(d/2), 2)+Math.pow(l-(e/2), 2))/(2*Math.pow(sig, 2));
				t= Math.exp(-t); 
				t= (1*t)/(2*Math.PI*Math.pow(sig, 2));
				kenel[k][l]= t;
				sumhere= sumhere+ kenel[k][l];
			}	 
		}
		System.out.println("--->"+ sumhere);    	
		//--normalization      
		double sum1= 0;
		for(int i=0; i<d; ++i){
			for(int j=0; j<e; ++j){
				kenel[i][j]= kenel[i][j]/ sumhere;
				sum1= sum1+ kenel[i][j];
			}
		}
		//--end of producing gaussian matrix
		System.out.println("gaussian sum: " + sum1);
		//GAUSSIAN pro
		//int[][] neib=new int[15][15];//for sort
		for (int i= d/2;i< g.length+d/2; i++){
			for(int j= e/2;j< g[0].length+ e/2; j++){
				double sum= 0;
				for(int k=0; k<d; k++){
					for(int l=0; l<e; l++){
						sum= (float)(sum+ flac_grn[i+ k- d/ 2][j+ l- e/ 2]* kenel[k][l]);	
					}
				}
				// System.out.println(sum);
				g[i- d/2][j- e/2]= (int)(sum);	
			}
		}
		return  new CheckRange().Processor(g); 		  
	}
	public int[][] Processor1D(int[][] g,int d,int e,double sig) throws IOException {
		int [][]flac_grn= new Reflection().PadImage(g, d, e); 
		double kenel[][] = new double[d][e];
		//GAUSSIAN KENEL
		double sumrow= 0;
		double sumcol= 0;
		double t= 0;
		for(int k= 0; k<d; ++k){
			for(int l= e/ 2; l< e/ 2+ 1; ++l){    	    
				t= (Math.pow(k- d/2, 2)+ Math.pow(l- e/2, 2))/(2* Math.pow(sig, 2));
				t= Math.exp(-t); 
				t= (1*t)/(2*Math.PI*Math.pow(sig, 2));
				kenel[k][l]= t;
				sumrow= sumrow+ kenel[k][l];
			}	 
		}
		System.out.println("--->"+ sumrow);    
		//--normalization row      
		double sum1= 0;
		for(int i= 0; i<d; ++i){
			for(int j= e/2; j< e/2+1; ++j){
				kenel[i][j]= kenel[i][j]/ sumrow;
				sum1= sum1+ kenel[i][j];
			}
		}
		//--end of producing gaussian matrix
		System.out.println("gaussian sum: " + sum1);
		////GAUSSIAN KENEL col
		for(int k= d/2; k<d/2+1; ++k){
			for(int l= 0; l<e; ++l){    	    
				t= (Math.pow(k-d/2, 2)+Math.pow(l-e/2, 2))/(2*Math.pow(sig, 2));
				t= Math.exp(-t); 
				t= (1* t)/ (2* Math.PI*Math.pow(sig, 2));
				kenel[k][l]= t;
				sumcol= sumcol+ kenel[k][l];
			}	 
		}
		System.out.println("--->"+sumcol);
		//--normalization col      
		double sum2= 0;
		for(int i= d/ 2; i< d/ 2+ 1; ++i){
			for(int j=0; j<e; ++j){
				kenel[i][j]= kenel[i][j]/ sumcol;
				sum2= sum2+ kenel[i][j];
			}
		}
		//--end of producing gaussian matrix
		System.out.println("gaussian sum: " + sum2);	    	
		//GAUSSIAN pro for row
		for (int i=d/2;i<g.length+d/2;i++){
			for(int j=e/2;j<g[0].length+e/2;j++){
				double sum=0;
				for(int k=0;k<d;k++){
					for(int l=e/2;l<e/2+1;l++){
						sum=(float)(sum+flac_grn[i+k-d/2][j+l-e/2]*kenel[k][l]);	
					}
				}
				// System.out.println(sum+"|");
				// System.out.println(i-d/2);
				// System.out.println(j-e/2);
				//System.out.println(g.length);
				//if(j-e/2>)
				g[i-d/2][j-e/2]=(int)(sum);
				// 	flac_grn[i][j]=(int)sum;
			}
		}    	
		//REFLACTION a new of rowed grn
		flac_grn=new Reflection().PadImage(g, d, e); 
		//GAUSSIAN pro for col		    	
		for (int i=d/2;i<g.length+d/2;i++){
			for(int j=e/2;j<g[0].length+e/2;j++){
				double sum=0;
				for(int k=d/2;k<d/2+1;k++){
					for(int l=0;l<e;l++){
						sum=(float)(sum+flac_grn[i+k-d/2][j+l-e/2]*kenel[k][l]);	
					}
				}
				// System.out.println(sum);
				g[i-d/2][j-e/2]=(int)(sum);	
			}
		}  		
		return  new CheckRange().Processor(g);	  
	}

	public BufferedImage Processor(BufferedImage lygimage, double d, double e,double k) throws IOException {
		//image to r[][] g[][] b[][]
		//r[][]
		//g[][]
		//b[][]
		//r[][]g[][]b[][] to image
		//image to r[][] g[][] b[][]
		int r[][]=new ReadWritePng().REDpngRead(lygimage);
		int g[][]=new ReadWritePng().GRNpngRead(lygimage);
		int b[][]=new ReadWritePng().BLUpngRead(lygimage);
		//r[][]
		r=Processor2D(r,(int)d,(int)e,k);   
		//g[][]
		g=Processor2D(g,(int)d,(int)e,k);   
		//b[][]
		b=Processor2D(b,(int)d,(int)e,k);  
		//r[][]g[][]b[][] to image
		lygimage=new ReadWritePng().createBufferImage(r,g,b);
		return lygimage;
	}
}