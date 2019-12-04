import java.util.Scanner;

public class Main {
	
		// N (1 <= N <= 15)	- 일할 수 있는 횟수
		// Ti <= 5 			- 걸리는 일수
		// 1 <= P1 <= 1000	- 일에 대한 보수
		static int N = 0;
		static int[] T = new int[15];
		static int[] P = new int[15];
		static int max = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		
		N = input.nextInt();
		
		for(int i = 0 ; i < N; i ++) {
			T[i] = input.nextInt();
			P[i] = input.nextInt();
		}
		input.close();
		
		work(1, 0);
		System.out.println("결과 : "+ max);
	}
	
	static void work(int day, int totalP) {
		// 최대 일수 넘기면 리턴
		if(day > N+1) {
			return;
		}
		
		// 최댓값보다 크면 최댓값 갱신
		if(day == N+1) {
			if(totalP > max) {
				max = totalP;
				return;
			}
		}
		
		// 상담을 하는 경우, 조건문은 상담일수를 넘어가 탐색하려는 경우 방지
		if(day <= N) {
		work(day+T[day-1], totalP + P[day-1]);
		}
		
		// 상담을 하지 않고 하루를 흘려보내는 경우
		work(day+1, totalP);
	}
	
	
}
