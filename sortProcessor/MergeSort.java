package sortProcessor;
public class MergeSort{
	public int[] sort(int [] array) {
		int mid;
		int l=0;
		int h=array.length-1;
		if(l<h)
		{mid=(l+h)/2;
		sort(array,l,mid);
		sort(array,mid+1,h);
		merge(array,l,mid,h);}
		return array;}

	private void sort(int[] array, int l, int h) {
		int mid;
		if(l<h)
		{mid=(l+h)/2;
		sort(array,l,mid);
		sort(array,mid+1,h);
		merge(array,l,mid,h);}}
	private void merge(int[] a, int l, int mid, int h) {
		// TODO Auto-generated method stub
		int i,j,k;
		int c[]=new int[a.length];
		i=l;
		k=l;
		j=mid+1;
		while((i<=mid)&&(j<=h))
		{if(a[i]<a[j]){c[k]=a[i];k++;i++;}
		else{c[k]=a[j];k++;j++;}}
		while(i<=mid)
		{c[k]=a[i];k++;i++;}
		while(j<=h)
		{c[k]=a[j];k++;j++;}
		for(i=l;i<k;i++)
		{a[i]=c[i];}}}