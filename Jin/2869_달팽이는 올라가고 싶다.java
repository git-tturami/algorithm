import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a,b,v = 0;
		Scanner in = new Scanner(System.in);
		a = in.nextInt();
		b = in.nextInt();
		v = in.nextInt();
		
		int num = (v-b-1)/(a-b) + 1;
		
		System.out.println(num);
		
	}
}
