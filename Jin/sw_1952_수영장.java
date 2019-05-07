import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static class testcase{
		ArrayList<Integer> price = new ArrayList<>();
		ArrayList<Integer> month = new ArrayList<>();
		
	}
	
	static int min;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 0;
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		min = 0;
		
		ArrayList<testcase> tc = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			testcase t = new testcase();
			for(int j = 0;j<4;j++) {
				int tmp = in.nextInt();
				t.price.add(tmp);
			}
			
			for(int j = 0;j<12;j++) {
				int tmp = in.nextInt();
				t.month.add(tmp);
			}
			tc.add(t);
		}
		
		int s = 0;
		
		for(testcase t:tc) {
			s++;
			min = 99999;
			int count = 0;
			dfs(t,count,0);
			if(min >= t.price.get(3)) {
				min = t.price.get(3);
			}
			
			System.out.println("#" + s + " " + min);
		}
	}

	public static void dfs(testcase t, int count, int num) {
		if(num <= 11) {
			count += t.month.get(num) * t.price.get(0);
			dfs(t, count, num+1);
			count -= t.month.get(num) * t.price.get(0);
			
			
			if(t.month.get(num)!=0) {
				count += t.price.get(1);
				dfs(t, count, num+1);
				count -= t.price.get(1);
			}
			
			if(num<=9) {
				if(t.month.get(num)!=0 || t.month.get(num+1)!=0 || t.month.get(num+2)!=0) {
					count += t.price.get(2);
					dfs(t, count, num+3);
					count -= t.price.get(2);
				}
				return;
			}
			else return;
		}
		
		if (min >= count && num == 12) {
			min = count;
		}
		
		//count = 0;
		
		return;
	}
}
