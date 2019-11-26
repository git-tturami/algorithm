#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>

using namespace::std;
char cube[30][30][30];

int L, R, C;
int Start_End[2][3] = { '0', };
bool isFindE = false;
int counter = 100000;
typedef struct {
	int a;
	int b;
	int c;
}position;

stack <position> pos;
position p_temp;


int callfunction(int a, int  b, int c);

int main()
{
	// 1:상, 2:하, 3:북, 4:남, 5:동 6:서
	while (1) {
		
			scanf("%d %d %d", &L, &R, &C);
			
		if (L == 0 && R == 0 && C == 0)
		{
			
			return 0;
		}
		//cube에 채우기 
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					scanf(" %c", &cube[i][j][k]);
					//'S' 위치 기억하기
					if (cube[i][j][k] == 'S') {
						Start_End[0][0] = i;
						Start_End[0][1] = j;
						Start_End[0][2] = k;
					}
					//E' 위치 기억하기
					else if (cube[i][j][k] == 'E')
					{
						Start_End[1][0] = i;
						Start_End[1][1] = j;
						Start_End[1][2] = k;
					}
				}
			}
		}


		//처음으로 콜
		callfunction(Start_End[0][0], Start_End[0][1], Start_End[0][2]);
		// 모든곳을 들르고 나서 E를 못찾으면
		if (pos.size() == 0 && isFindE == false) {
			printf("Trapped!\n");
		}
		// 모든곳을 들르고 나서 E를 찾으면
		else if (pos.size() == 0 && isFindE == true) {
			printf("Escaped in %d minute(s).\n", counter);
		}
		//초기화
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					cube[i][j][k] = '0';
				}
			}
		}
		//초기화
		isFindE = false;
		counter = 100000;

	}
}


int callfunction(int a, int  b, int c) {
	
		//갈 수 있는 길이 아니면
		if (cube[a][b][c] != '.'&& cube[a][b][c] != 'S'&& cube[a][b][c] != 'E') {
			//can not come close
			return 0;
		}//상하동서남북에 E 가있으면 counter에 stack 개수 기억
			if (cube[a + 1][b][c] == 'E' || cube[a][b + 1][c] == 'E' || cube[a][b][c + 1] == 'E' || cube[a - 1][b][c] == 'E' || cube[a][b - 1][c] == 'E' || cube[a][b][c - 1] == 'E'){
				isFindE = true;
				counter = min((int)(pos.size()) + 1, counter);
				
				return 0;
			}


		p_temp.a = a;
		p_temp.b = b;
		p_temp.c = c;
		pos.push(p_temp);
		//스택에 저장하고 있던자리 못지나가는 자리로 만들기
		cube[a][b][c] = '#';

		callfunction(a + 1, b, c);
		callfunction(a, b + 1, c);
		callfunction(a, b, c + 1);
		callfunction(a - 1, b, c);
		callfunction(a, b - 1, c);
		callfunction(a, b, c - 1);
		pos.pop();
		
			
	
	return 0;

}
