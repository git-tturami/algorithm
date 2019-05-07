import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
		static int[][] result = new int[101][101];
		static int N = 0;
		static int[] X = new int[20];
		static int[] Y = new int[20];
		static int[] D = new int[20];
		static int[] G = new int[20];
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total=0;
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		
		for(int i = 0 ; i < N ; i++) {
			X[i] = input.nextInt();
			Y[i] = input.nextInt();
			D[i] = input.nextInt();
			G[i] = input.nextInt();
		}		
		input.close();
		
		for(int i = 0 ; i < 101; i++) {
			for(int j = 0 ; j < 101; j++) {
				result[i][j] = 0;
			}
		}
		
		for(int i = 0; i< N; i++) {
			make_function(X[i], Y[i], D[i], G[i]);
		}
		
		total = find_function();
		
		System.out.print(total);
	}
	
	public static void make_function(int X, int Y, int D, int G) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(D);
		
		for(int i = 0; i < G; i++) {
			ArrayList<Integer> buffer = new ArrayList<Integer>();
			
			for(int j = arr.size()-1 ; j >= 0; j--) {
				if(arr.get(j)==0) {
					buffer.add(1);
				}
				else if(arr.get(j)==1) {
					buffer.add(2);
				}
				else if(arr.get(j)==2) {
					buffer.add(3);
				}
				else if(arr.get(j)==3) {
					buffer.add(0);
				}
			}
			
			for(int k = 0; k<buffer.size();k++) {
				arr.add(buffer.get(k));
			}
		}
		
		int x = X; int y= Y;
		result[x][y] = 1;
		
		for(int i =0; i<arr.size();i++) {
			if(arr.get(i)==0) {
				x = x+1;
			}
			else if(arr.get(i)==1) {
				y = y-1;
			}
			else if(arr.get(i)==2) {
				x = x-1;
			}
			else if(arr.get(i)==3) {
				y = y+1;
			}
			
			if(x >100 || x <0 || y>100 || y<0) {
				break;
			}
			
			result[x][y] = 1;
		}
		
	}
	
	public static int find_function() {
		int rst = 0;
		for(int i = 0 ; i <100; i++) {
			for(int j = 0 ; j <100; j++) {
				if(result[i][j]==1 &&result[i+1][j]==1 && result[i][j+1]==1 && result[i+1][j+1]==1) {
					rst++;
				}
			}
		}
		
		return rst;
	}

}
