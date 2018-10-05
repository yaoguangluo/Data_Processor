package imageProcessor;
public class HoughTransform {
	public int [][]HTMatrix;
	public void HoughTransformLoop(int[][] g, int pix,int scale) {
		// TODO Auto-generated method stub
		for(int i=0;i<g.length;i++){
			for(int j=0;j<g[0].length;j++){
				if(g[i][j]==pix){
					for(int p=0;p<360;p++){
						double sita=p*Math.PI/360;
						int psita=(int) (i*Math.cos(sita)+j*Math.sin(sita));
						if (psita<0) psita=0;
						HTMatrix[psita][p]=HTMatrix[psita][p]+scale;
					}
				}
			}
		}
	}

	public void initHTMatrix(int[][] g) {
		int max=0;
		for(int i=0;i<g.length;i++){
			for(int j=0;j<g[0].length;j++){
				for(int p=0;p<360;p++){
					double sita=p*Math.PI/360;
					int temp=(int) (i*Math.cos(sita)+j*Math.sin(sita));
					if (temp>=max) {
						max=temp;
					}
				}
			}
		}   
		HTMatrix=new int[max+1][360];	  
		for(int p=0;p<360;p++) {
			for(int psita=0;psita<max+1;psita++ )  {
				HTMatrix[psita][p]=0;
			}
		}
	}
}
