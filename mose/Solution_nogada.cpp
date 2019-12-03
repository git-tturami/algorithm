#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <algorithm>

using namespace std;

int main()
{
	int width;
	int height;
	int min1=0;
	int min_final=100;
	int B_cnt=0;
	int W_cnt=0;

	scanf("%d", &height);
	scanf("%d", &width);
	char chess[50][50];

	for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
			//chess대입
			scanf(" %c", &chess[i][j]);
		}

	}

	//첫번째  B
	//두번째  W
	width = width - 7;
	height = height - 7;
	for (int i = 0; i < height; i++){
		for( int j=0; j<width; j++){
			for (int m = 0; m < 8; m++) {
				for (int n = 0; n< 8; n++) {
					if ((i + j + m + n) % 2 == 0) {//합이 짝수
						if (chess[i + m][j + n] == 'W') {
							B_cnt++;
						}
					}
					else {//합이 홀수
						if (chess[i + m][j + n] == 'B') {
							W_cnt++;
						}
					}
				}

			}
			min1 = min(B_cnt + W_cnt, 64 - B_cnt - W_cnt);
			min_final = min(min1, min_final);
			B_cnt = 0;
			W_cnt = 0;
		}

	}
	printf("%d", min_final);


	return 0;
}

