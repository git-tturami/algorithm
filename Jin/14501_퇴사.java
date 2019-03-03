package hii;

import java.util.Scanner;

public class Main {

	static class work {
		int t;
		int p;
	}

	static int N;
	static work[] Work;
	static int max;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		int t, p;
		Work = new work[N];

		for (int i = 0; i < N; i++) {
			t = scan.nextInt();
			p = scan.nextInt();
			Work[i] = new work();
			Work[i].t = t;
			Work[i].p = p;
		}

		max = 0;

		for(int i = 0;i<N;i++){
			Working(i,0);
		}

		System.out.println(max);

	}

	static void Working(int start, int sum) {

		if (start < N && start + Work[start].t <= N) {
			int money = 0;
			int day = 0;
			
			money = sum + Work[start].p;
			day = start + Work[start].t;
			
			for(int i=day;i<N;i++){
				Working(i, money);
			}
			
			if(money > max){
				max = money;
			}
		}


	}
}
