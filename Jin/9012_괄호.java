import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Stack<Character> stack = new Stack<Character>();
		ArrayList<String> arrayList = new ArrayList<String>();
		int N = scan.nextInt();
		String n = scan.nextLine();
		for (int i = 0; i < N; i++) {
			String tmp = scan.nextLine();
			arrayList.add(tmp);
		}

		for (int i = 0; i < N; i++) {
			stack.clear();
			for (int j = 0; j < arrayList.get(i).length(); j++) {
				if (arrayList.get(i).charAt(j) == '(') {
					stack.add(arrayList.get(i).charAt(j));
				}
				else if(arrayList.get(i).charAt(j) == ')') {
					if(!stack.empty() && stack.peek().equals('(')) {
						stack.pop();
					}
					else {
						stack.add(arrayList.get(i).charAt(j));
						break;
					}
				}
			}
			if(!stack.empty()) {
				System.out.println("NO");
			}
			else {
				System.out.println("YES");
			}
		}

	}

}
