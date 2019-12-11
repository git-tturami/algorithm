#define _CRT_SECURE_NO_WARNINGS
#include "pch.h"
#include <iostream>
#include <algorithm>

using namespace std;

void  function(int play_horse, int current_count, int temp_tot, int dice_abcd[][2]);

int final_tot = 0;
int isFinish[4] = {0,};

int dice_number[10] = {0,};
		//couunt, location(a,b,c,d)=0,1,2,3
//25번째 : 1=9; 2=13; 3=19   40 :20번째는 0도 포함
int a_route[5][22] = { {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,35,36,38,40},
{10,13,16,19,25},{ 20,22,24,25}, { 30,28,27,26,25} 
,{25,30,35,40,} };


int main()
{
	
	for (int i = 0; i < 10; i++) {
		scanf("%d", &dice_number[i]);
	}
	int dice_abcd[4][2] = { -1, };
	function(0, 0, 0, dice_abcd);	//horse_player, current_count;
	cout << final_tot << endl;
	cout << "Hello World!\n"; 
}

void  function(int play_horse,int current_count,int temp_tot, int dice_abcd[][2]) {
	
	for (int i=0; i < 4; i++){	//같은 칸에있는 숫자가있는지
		if (i != play_horse)
		if(current_count>0&& current_count<10)
		if (a_route[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]]!=0&&a_route[dice_abcd[i][1]][dice_abcd[i][0]] == a_route[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]]) {
			return;
		}
		else if (current_count >= 10) {
			
			return; 
		}
	}	

		
		if (a_route[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]] == 10) {
			dice_abcd[play_horse][0] -= 5;
			dice_abcd[play_horse][1] = 1;
		}
		else if (a_route[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]] == 20) {
			dice_abcd[play_horse][0] -= 10;
			dice_abcd[play_horse][1] = 2;
		}
		else if (a_route[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]] == 30) {
			dice_abcd[play_horse][0] -= 15;
			dice_abcd[play_horse][1] = 3;
		}
		else if (a_route[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]] == 25){
			if (dice_abcd[play_horse][1] == 1 || dice_abcd[play_horse][1] == 3) {
				dice_abcd[play_horse][0] -= 4;
				dice_abcd[play_horse][1] = 4;
			}
			else if (dice_abcd[play_horse][1] == 2){
				dice_abcd[play_horse][0] -= 3;
				dice_abcd[play_horse][1] = 4;
			}

		}
		else if (a_route[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]] == 40 && dice_abcd[play_horse][1] == 0) {
			dice_abcd[play_horse][0] -= 17;
			dice_abcd[play_horse][1] = 4;

		}
		dice_abcd[play_horse][0] += dice_number[current_count];  //나온 주사위 값을  현재의 노드값에 추가 1,2,3,4,5
		temp_tot += a_route[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]];
		final_tot = max(temp_tot, final_tot);
		cout << "이번 주사위는 : " << dice_number[current_count] << "\t현재의 값은 : " << a_route[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]] << "\t 누적값은 : " << temp_tot << endl;

		function(0, current_count+1, temp_tot, dice_abcd);
		function(1, current_count+1, temp_tot, dice_abcd);
		function(2, current_count+1, temp_tot, dice_abcd);
		function(3, current_count+1, temp_tot, dice_abcd);

}

