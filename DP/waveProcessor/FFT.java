package waveProcessor;
public class FFT{
	double pi= 3.1416926;
	public double[] fft1(double[] input) {
		double[] output= new double[input.length];
		cp[] x= new cp[input.length];
		for(int i= 0; i<input.length; i++) {
			x[i]= new cp();
			x[i].image= input[i];
		}
		cp[] y= fftk(x);
		for(int i= 0;i< input.length; i++) {
			output[i]= Math.sqrt(y[i].real* y[i].real + y[i].image* y[i].image);
		}
		return output;
	}

	public cp[] fftk(cp[] x) {
		int N= x.length;
		if (N== 1) return new cp[] {x[0]};
		if (N % 2!= 0) {throw new RuntimeException("N is not a power of 2");}
		cp[] even= new cp[N>> 1];
		for (int k= 0; k< N>> 1; k++) { 
			even[k]= x[2* k];
		}

		cp[] q= fftk(even);
		cp[] odd= even; 
		for (int k= 0; k< N>> 1; k++) {
			odd[k]= x[2* k+ 1];
		}

		cp[] r= fftk(odd);
		cp[] y= new cp[N];
		for (int k= 0; k< N; k++) {
			y[k]= new cp();
		}
		for (int k= 0; k< N>> 1; k++) {
			double kth= k* pi/ N;
			cp wk= new cp();
			wk.real= Math.cos(kth);
			wk.image= Math.sin(kth);

			cp times= new cp();
			times.real= wk.real* r[k].real- wk.image* r[k].image;
			times.image= wk.real* r[k].image+ wk.image* r[k].real;

			y[k].real= q[k].real+ times.real;
			y[k].image= q[k].image+ times.image;
			y[k+ N/ 2].real= q[k].real- times.real;
			y[k+ N/ 2].image= q[k].image- times.image;
		}
		return y;
	}
}

class cp{
	double real;
	double image;
	double value;
}

