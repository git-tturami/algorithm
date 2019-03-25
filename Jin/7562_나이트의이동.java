import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N;
	static int n_count;

	static Chess[] chess;

	static int[][] visit;
	
	static class point {
		int x;
		int y;
		int count;

		point(int x, int y,int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}

	static Queue<point> queue;

	static class Chess {
		int l;
		int x1, y1;
		int x2, y2;
	}

	static int[] dx = { 2, 1, -1, -2 };
	static int[] dy = { 2, 1, -1, -2 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		int[] num = new int[N];
		chess = new Chess[N];
		n_count = 0;

		for (int i = 0; i < N; i++) {
			chess[i] = new Chess();
			chess[i].l = scan.nextInt();
			chess[i].x1 = scan.nextInt();
			chess[i].y1 = scan.nextInt();
			chess[i].x2 = scan.nextInt();
			chess[i].y2 = scan.nextInt();
		}
		

		for (int i = 0; i < N; i++) {
			visit = new int[chess[i].l][chess[i].l];
			queue = new LinkedList<>();
			queue.clear();
			findCount(i);
		    System.out.println(n_count);
		    n_count = 0;
		}

	}

	public static void findCount(int n) {
		if (chess[n].x1 == chess[n].x2 && chess[n].y1 == chess[n].y2) {
			n_count = 0;
		} 
		
		else {
			bfs(chess[n].x1, chess[n].y1,n,0);
		}
	}

	public static void bfs(int x,int y,int n,int count) {
		for (int j = 0; j < 4; j++) {
			for (int k = 0; k < 4; k++) {
				if (Math.abs(dx[j]) != Math.abs(dy[k])) {
					int xx = x + dx[j];
					int yy = y + dy[k];

					
					if(xx < chess[n].l && yy < chess[n].l && xx>=0 && yy>=0) {
						if(xx == chess[n].x2 && yy == chess[n].y2) {
							n_count = count+1;
							queue.clear();
							return;
						}
						else {
							if(visit[xx][yy] != 1)
							queue.add(new point(xx, yy,count));
							visit[xx][yy] = 1;
						}
					}
					
				}
			}
		}
		
		if(!queue.isEmpty()) {
			point mpoint = queue.poll();
			bfs(mpoint.x, mpoint.y,n,(mpoint.count)+1);
		}
		else return;
	}
}
