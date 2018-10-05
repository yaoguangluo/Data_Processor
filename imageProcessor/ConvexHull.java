package imageProcessor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Vector;
public class ConvexHull {
	double seconds;
	public List<Vertex> convexHull(List<Vertex> vertices){
		List<Vertex> polygon = new Vector<Vertex>();		 
		int sx,lx; 
		Vertex vcurr = null; 
		Vertex vnext = null; 
		int i,x;
		lx = vertices.get(0).x;
		sx = vertices.get(0).x;
		for(i=1;i<vertices.size();i++) {
			x = vertices.get(i).x;
			if(x>lx) lx = x;
			if(x<sx) sx = x;
		}
		for(Vertex v : vertices) {
			if(v.x==sx && (vcurr==null || v.y>vcurr.y)) {
				vcurr = v;
			}
		}  
		int dxs,dys; 
		int dxc,dyc; 
		int dxt,dyt; 
		int lqc,lqt; 
		int scmp;  
		polygon.add(vcurr);
		dys = 1; dxs = 0;
		while(vcurr.x<=lx) {
			dyc = -1; dxc = 0; lqc = 0;
			for(Vertex v : vertices) {
				if(v.x>=vcurr.x) {
					dyt = v.y - vcurr.y;
					dxt = v.x - vcurr.x;
					if(compareSlope(dyt,dxt,dys,dxs)==-1) {
						scmp = compareSlope(dyt,dxt,dyc,dxc);
						lqt  = dyt*dyt+dxt*dxt;
						if(scmp>=0) {
							if(scmp>0 || lqt>lqc) {
								vnext = v;
								dyc = dyt;
								dxc = dxt;
								lqc = lqt;
							}
						}
					}
				}
			}
			if(vnext==null) {break;}
			dys = dyc; dxs = dxc;
			polygon.add(vnext);
			vertices.remove(vnext);
			vcurr = vnext; vnext = null;
		}
		dys = 1; dxs = 0;
		while(vcurr.x>sx) {
			dyc=-1; dxc = 0; lqc = 0;
			for(Vertex v : vertices) {
				if(v.x<vcurr.x) {
					dyt = v.y - vcurr.y;
					dxt = v.x - vcurr.x;
					if(compareSlope(dyt,dxt,dys,dxs)==-1) {
						scmp = compareSlope(dyt,dxt,dyc,dxc);
						lqt  = dyt*dyt+dxt*dxt;
						if(scmp>=0) {
							if(scmp>0 || lqt>lqc) {
								vnext = v;
								dyc = dyt;
								dxc = dxt;
								lqc = lqt;
							}
						}
					}
				}
			}

			if(vnext==null) break;
			dys = dyc; dxs = dxc;
			polygon.add(vnext);
			vertices.remove(vnext);
			vcurr = vnext; vnext = null;
		}    
		return polygon;
	}
	public int compareSlope(int dy2, int dx2, int dy1, int dx1) {
		if(dx2!=0 && dx1!=0) {
			double test = dy2*dx1-dy1*dx2;
			return (int)Math.signum(test);
		} else {
			if(dx2!=0 || dx1!=0) {
				if(dx2==0) {
					return dy2>=0 ? 1 : -1;
				} else {
					return dy1>=0 ? -1 : 1;
				}
			} else {
				if(dy2>=0) {
					return dy1>=0 ? 0 : 1;
				} else {
					return dy1>=0 ? -1 : 0;
				}
			}
		}
	}
	public class Vertex {
		public int x;
		public int y;
		public Vertex(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public void PNGWrite(int[][] g, int pix, String output) throws IOException {
		int [][]g1=new int[g.length][g[0].length]; 
		int n=0;
		for(int p=0;p<g.length;p++){
			for(int q=0;q<g[0].length;q++){
				if(g[p][q]==pix){
					g1[p][q]=g[p][q];
					n+=2;
				}else{
					g1[p][q]=0;
				}
			}  
		}		  
		int[] points = new int[n];
		int n1=0;
		//converxto vetx
		for(int p=0;p<g1.length;p++){
			for(int q=0;q<g1[0].length;q++){
				if(g1[p][q]!=0){
					points[n1++]=p;
					points[n1++]=q;
				}
			}
		}
		List<Vertex> vertices = new Vector<Vertex>();
		for(int i1=0;i1<points.length;i1+=2) {
			Vertex v = new Vertex(points[i1],points[i1+1]);
			vertices.add(v);
		}
		//find  
		long begin = System.currentTimeMillis();
		List<Vertex> polygon = convexHull(vertices);
		long elapsed = System.currentTimeMillis()-begin;
		seconds = elapsed/1000.0;
		//converx to g[][]
		for(int p=0;p<g1.length;p++){
			for(int q=0;q<g1[0].length;q++){
				g1[p][q]=0;
			}
		}
		for(int i1=0;i1<polygon.size();i1++) {
			g1[polygon.get(i1).x][polygon.get(i1).y]=255;
		}    
		new ReadWritePng().writePNG(output, g1); 
	}
	public void CSVWrite(int[][] g, int pix, String outputcsv) throws IOException {
		int [][]g1=new int[g.length][g[0].length]; 
		int n=0;
		for(int p=0;p<g.length;p++){
			for(int q=0;q<g[0].length;q++){
				if(g[p][q]==pix){
					g1[p][q]=g[p][q];
					n+=2;
				}
				else{
					g1[p][q]=0;
				}
			}  
		}		  
		int[] points = new int[n];
		int n1 = 0;
		//converxto vetx
		for(int p=0;p<g1.length;p++){
			for(int q=0;q<g1[0].length;q++){
				if(g1[p][q]!=0){
					points[n1++]=p;
					points[n1++]=q;
				}
			}
		}
		List<Vertex> vertices = new Vector<Vertex>();
		for(int i1=0;i1<points.length;i1+=2) {
			Vertex v = new Vertex(points[i1],points[i1+1]);
			vertices.add(v);
		}
		//find  
		long begin = System.currentTimeMillis();
		List<Vertex> polygon = convexHull(vertices);
		long elapsed = System.currentTimeMillis()-begin;
		seconds = elapsed/1000.0;
		//converx to g[][]
		for(int p=0;p<g1.length;p++){
			for(int q=0;q<g1[0].length;q++){
				g1[p][q]=0;
			}
		}
		for(int i1=0;i1<polygon.size();i1++) {
			g1[polygon.get(i1).x][polygon.get(i1).y]=255;
		}    
		File file = new File(outputcsv); 
		FileOutputStream out = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(out, "GB2312");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("cod_x" + "," + "cod_y" + "\r\n");
		for(int i1=0;i1<polygon.size();i1++) {
			bw.write(polygon.get(i1).x+","+polygon.get(i1).y+ "\r\n");
		}    
		bw.close();
		osw.close();
		out.close();
	}
}
