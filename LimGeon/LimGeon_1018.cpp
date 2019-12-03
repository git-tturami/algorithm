#include <stdio.h>
#include <algorithm>

using namespace std;

char board[50][51];

int main() {

	//input
	int n, m;
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++)
		scanf("%s", board[i]);
	
	//process
	int minColor = 1e9;
	for (int i = 0; i + 7 < n; i++) {
		for (int j = 0; j + 7 < m; j++) {
			int sum1[2] = { 0, }; //idx 0: W sum idx 1: B sum
			int sum2[2] = { 0, }; //idx 0: W sum idx 1: B sum
			for (int k = i; k < i + 8; k++) {
				for (int l = j; l < j + 8; l++) {
					if ((k + l) % 2 == 0) {
						if(board[k][l]=='W') sum1[0] += (int)board[k][l];
						else if (board[k][l] == 'B') sum1[1] += (int)board[k][l];
					}
					else {
						if (board[k][l] == 'W') sum2[0] += (int)board[k][l];
						else if (board[k][l] == 'B') sum2[1] += (int)board[k][l];
					}
				}
			}
			
			int wsum = (int)'W' * 32;
			int bsum = (int)'B' * 32;
			int caseA = (32 - (wsum - sum1[0]) / 'W') + (32 - (bsum - sum2[1]) / 'B');
			int caseB = (32 - (wsum - sum2[0]) / 'W') + (32 - (bsum - sum1[1]) / 'B');
			int tmp = min(caseA, caseB);
			minColor = min(minColor, tmp);
		}
	}
	printf("%d", minColor);
	return 0;
}