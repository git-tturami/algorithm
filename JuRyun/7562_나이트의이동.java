import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Point{
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		Point() {
			// TODO Auto-generated constructor stub
		}
		int x ;
		int y ;
	}
	
	static int[] dx = {-2, -2, -1, -1, 2, 2, 1, 1};
	static int[] dy = {-1, 1, 2, -2, -1, 1, 2, -2};
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	int testCount = 0;
	
	Scanner input = new Scanner(System.in);
	
	testCount = input.nextInt();
	int[] length = new int[testCount];
	Point[] startPoint = new Point[testCount];
	Point[] endPoint = new Point[testCount];
	
	for(int i = 0 ; i < testCount; i ++) {
		startPoint[i] = new Point();
		endPoint[i] = new Point();
		length[i] = input.nextInt();
		startPoint[i].x = input.nextInt();
		startPoint[i].y = input.nextInt();
		endPoint[i].x = input.nextInt();
		endPoint[i].y = input.nextInt();
	}
	
	for(int i = 0; i < testCount; i++) {
		System.out.println(bfs(length[i], startPoint[i], endPoint[i]));	
	}
	
	
	}

	 static int bfs(int length, Point startPoint, Point endPoint) {
		int count = 0;
		int[][] Map = new int[length][length];
		for(int i = 0 ; i < length; i++) {
			for(int j = 0 ; j < length; j ++) {
				Map[i][j] = 999;
			}
		}
		
		Queue<Point> queue = new LinkedList<>();
		queue.offer(startPoint);
		Map[startPoint.y][startPoint.x] = 0;
		
		if(startPoint.x == endPoint.x && startPoint.y == endPoint.y) {
			return 0;
		}
		
		Point PopedPoint = new Point();
		PopedPoint.x = 999;
		PopedPoint.y = 999;
		while(PopedPoint.x != endPoint.x || PopedPoint.y != endPoint.y ) {
			 PopedPoint = queue.poll();
			 count = Map[PopedPoint.y][PopedPoint.x] +1;
			 
			 for(int i = 0; i < 8; i++) {
				 Point pnt = new Point(PopedPoint.x+dx[i],PopedPoint.y+dy[i]);
				 if(pnt.x >=0 && pnt.y>=0 && pnt.x <length && pnt.y < length && Map[pnt.y][pnt.x] == 999) {
					 	queue.offer(pnt);
					 		Map[pnt.y][pnt.x] = count;
				 }
			 }
		}
		
		
		return Map[endPoint.y][endPoint.x];
	}
	
}
