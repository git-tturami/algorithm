import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static class point {
		int x;
		int y;

		point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		boolean equal(point p) {
			if (p.x == this.x && p.y == this.y) {
				return true;
			}
			return false;
		}
	}

	static int[][] map;
	static int count;
	static int max;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N, M, D;
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		D = in.nextInt();
		
		if(N <3 || N>15 || M<3 || M>15 || D<1 || D>10) {
			return;
		}
		
		map = new int[N + 1][M];
		max = 0;

		ArrayList<point> pointMap = new ArrayList<>();


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(in.nextInt() == 1) {
					pointMap.add(new point(i, j));
				}
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = i+1; j < M; j++) {
				for (int k = j+1; k < M; k++) {
					ArrayList<point> tmpList = new ArrayList<>();
					for(point p:pointMap) {
						tmpList.add(new point(p.x,p.y));
					}
					dfs(N,M, D, i, j, k,tmpList);
				}
			}
		}

		System.out.println(max);

	}

	public static void dfs(int N, int M, int D, int i, int j, int k, ArrayList<point> tmpList) {
		count = 0;
		int numss = 0;
		
		ArrayList<Integer> index = new ArrayList<>();
		
		while (tmpList.size() > 0) {
			index.clear();
			tmpList = distance(tmpList, N, D, i, j, k);
			if (max < count) {
				max = count;
				numss = count;
			}
				
			for (point p : tmpList) {
				if (p.x < N-1) {
					p.x += 1;
				} else {
					index.add(tmpList.indexOf(p));
				}
			}
			
			if(index.size()>0) {
				for(int a = index.size()-1;a>=0;a--) {
					int indexing = index.get(a);
					tmpList.remove(indexing);
				}
			}
			
		}
	}

	public static ArrayList<point> distance(ArrayList<point> tmpList, int N, int D, int i, int j, int k) {
		int first = -1, second = -1, third = -1;
		int firstmin = 100, secondmin = 100, thirdmin = 100;
		//ArrayList<Integer> indexnum = new ArrayList<>();

		for (point p : tmpList) {
			int f = Math.abs(N-p.x) + Math.abs(i-p.y);
			int s = Math.abs(N-p.x) + Math.abs(j-p.y);
			int t = Math.abs(N-p.x) + Math.abs(k-p.y);
			
			if (f <= D && f<=firstmin) {
				if(f == firstmin) {
					if(p.y<tmpList.get(first).y) {
						first = tmpList.indexOf(p);
					}
				}
				else {
					first = tmpList.indexOf(p);
					firstmin = f;
				}
				
			}
			
			if (s <= D && s<=secondmin) {
				if(s == secondmin) {
					if(p.y < tmpList.get(second).y) {
						second = tmpList.indexOf(p);
					}
				}
				else {
					second = tmpList.indexOf(p);
					secondmin = s;
				}
				
			}
			
			if (t <= D && t<=thirdmin) {
				if(t == thirdmin) {
					if(p.y<tmpList.get(third).y) {
						third = tmpList.indexOf(p);
					}
				}
				else {
					third = tmpList.indexOf(p);
					thirdmin = t;
				}
				
			}
			
		}
		
		
		int[] nums = new int[3];
		nums[0] = first;
		nums[1] = second;
		nums[2] = third;
		
		Arrays.sort(nums);
		
		first = nums[0];
		second = nums[1];
		third = nums[2];
		
		if(first >=0 || second >= 0 || third >= 0) {
			if(first == second && first== third) {
				tmpList.remove(first);
				count++;
			}
			else if(first == second) {
				if(third >= 0) {
					tmpList.remove(third);
					count++;
				}
				if(first >=0) {
					tmpList.remove(first);
					count++;
				}
				
			}
			else if(second == third) {
				if(second >=0) {
					tmpList.remove(second);
					count++;
				}
				if(first >=0) {
					tmpList.remove(first);
					count++;
				}
				
			}
			
			else {
				if(third >= 0) {
					tmpList.remove(third);
					count++;
				}
				if(second >= 0) {
					tmpList.remove(second);
					count++;
				}
				if(first >= 0) {
					tmpList.remove(first);
					count++;
				}
				
			}

		}
		
		
		return tmpList;
	}
}
