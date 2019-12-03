#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int getMin(int*,int);

int main()
{	
	int N=0, M=0;
	
	char scan[50][51] = {0,};

	//입력

	scanf("%d %d", &N,&M);
	for (int i = 0; i < N; i++) {
		scanf("%s", &scan[i]);
	}
	printf("\n");

	int oddsum = 0;
	int evensum = 0;
	int w = ('W') * 32;
	int b = ('B') * 32;
	int q = 'W' - 'B';
	int store[1850] = { 0, };
	int m = 0;
	
	//자르기
	
	for (int i = 0; i + 7 < N; i++) 
	{
		for (int j = 0; j + 7 < M; j++) 
		{
			
			int flag = 0;

			//첫번째 블럭 확인 하기
			if (scan[i][j] == 'B')
			{
				for (int k = i; k < i + 8; k++)
				{
					for (int l = j; l < j + 8; l++)
					{
						if (flag == 0) {
							if (k + l % 2 == 0) oddsum += (int)scan[k][l];
							else evensum += (int)scan[k][l];
						}
						else {
							if (k + l % 2 == 1) oddsum += (int)scan[k][l];
							else evensum += (int)scan[k][l];
						}
						
					}
					
					//첫 번째 블럭 B
					int case_B = abs(w - evensum) / q + abs(b - oddsum) / q;
					store[m] = case_B;
					m++;
				}
			}
			
			else if (scan[i][j] == 'W')
			{
				for (int k = i; k < i + 8; k++)
				{
					for (int l = j; l < j + 8; l++)
					{
						if (flag == 0) {
							if (k + l % 2 == 0) oddsum += (int)scan[k][l];
							else evensum += (int)scan[k][l];
						}
						else {
							if (k + l % 2 == 1) oddsum += (int)scan[k][l];
							else evensum += (int)scan[k][l];
						}
						
					}

					//첫 번째 블럭 W
					int case_W = abs(w - oddsum) / q + abs(b - evensum) / q;
					store[m] = case_W;
					m++;
				}
			}
			flag++;
				
		}
	}
	
	int min=getMin(store, sizeof(store) / sizeof(int));
	printf("%d", min);
	 
	return 0;


}

int getMin(int* n, int size)
{
	int min = n[0];

	for (int i = 1; i < size; i++)
		if (n[i] < min) min = n[i];

	return min;
}