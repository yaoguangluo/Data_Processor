package imageProcessor;
import java.io.IOException;
public class Sobel {
	public int[][] Processor( int[][] g, int choice) throws IOException {		 
		int[][] refG=  new Reflection().PadImage(g, 3, 3);  
		int[]size= {g.length,g[0].length};
		double[][] Gx= new double [size[0]][size[1]];
		double[][] Gy= new double [size[0]][size[1]];
		int[][] gxk= new int[][]{{-1, 0, 1},{-2, 0, 2},{-1, 0, 1}};;
		int[][] gyk= new int[][]{{-1,-2,-1},{ 0, 0, 0},{ 1, 2, 1}};
		//GROUP OPERATION
		Gx= new GroupOperator().GO(gxk, refG, size);
		Gy= new GroupOperator().GO(gyk, refG, size);
		//MAG operation
		int[][] outmag= mag(Gx,Gy,size);
		//DIR operation
		int[][] outdir= dir(Gx,Gy,size);     
		switch (choice){
			case 1: return outmag;
			case 2: return outdir;
			default: return null;
		}	      
	}
	private int[][] mag(double[][] gx,double[][] gy,int[]size) throws IOException{
		double[][] mag= new double[size[0]][size[1]];
		int[][] outmag= new int[size[0]][size[1]];
		for(int i= 0; i<size[0];i++){
			for(int j= 0; j<size[1];j++){
				mag[i][j]= Math.sqrt(Math.pow(gx[i][j], 2)+ Math.pow(gy[i][j], 2));
				outmag[i][j]= (int)(mag[i][j]/(1020*Math.sqrt(2))*255);
			}
		}
		return  new CheckRange().Processor(outmag); 
	}
	private int[][] dir(double[][] gx,double[][] gy, int[]size) throws IOException{	
		double[][] dir= new double[size[0]][size[1]];
		int[][] outdir= new int[size[0]][size[1]];
		for(int i = 0; i<size[0]; i++){
			for(int j = 0; j<size[1]; j++){
				dir[i][j]= Math.atan2(gy[i][j], gx[i][j]);
				outdir[i][j]= (int)((dir[i][j]+Math.PI)/ (2*Math.PI)*32)*8;
				if(outdir[i][j]> 255){
					outdir[i][j]= 255;
				}
			}
		}
		return new CheckRange().Processor(outdir); 
	}
}