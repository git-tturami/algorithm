#define _CRT_SECURE_NO_WARNINGS
#include "pch.h"
#include <iostream>
#include <algorithm>

using namespace std;

void  function(int play_horse, int current_count, int temp_tot, int dice_abcd[][2]);
int map_value(int play_horse, int dice_abcd[][2]);
int map_plus_value(int play_horse, int number);
void set_map_index(int play_horse, int number, int dice_abcd[][2]);
void set_map_location(int play_horse, int number, int dice_abcd[][2]);
void add_map_location(int play_horse, int number, int dice_abcd[][2]);
int get_map_index(int play_horse, int dice_abcd[][2]);
int get_map_location(int play_horse, int dice_abcd[][2]);
int map_value(int play_horse, int dice_abcd[][2]);
void check_algorithm(int play_horse, int dice_number, int dice_abcd[][2]);


int final_tot = 0;
int isFinish[4] = { 0, };
int dice_number[10] = { 0, };

//couunt, location(a,b,c,d)=0,1,2,3
//25번째 : 1=9; 2=13; 3=19   40 :20번째는 0도 포함
int map[5][22] = { {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,35,36,38},
{10, 13, 16, 19}, {20, 22, 24}, { 30, 28, 27, 26}, {25, 30, 35, 40, 0} };




int map_value(int play_horse, int dice_abcd[][2]) {
	return map[dice_abcd[play_horse][1]][dice_abcd[play_horse][0]];
}
int map_plus_value(int play_horse, int number) {
	return map[get_map_index(play_horse)][get_map_location(play_horse) + number];
}
void set_map_index(int play_horse, int number, int dice_abcd[][2]) {
	dice_abcd[play_horse][1] = number;
}
void set_map_location(int play_horse, int number, int dice_abcd[][2]) {
	dice_abcd[play_horse][0] = number;
}
void add_map_location(int play_horse, int number, int dice_abcd[][2]) {
	dice_abcd[play_horse][0] += number;
}

int get_map_index(int play_horse, int dice_abcd[][2]) {
	return dice_abcd[play_horse][1];
}
int get_map_location(int play_horse, int dice_abcd[][2]) {
	return dice_abcd[play_horse][0];
}

int main()
{
	int dice_abcd[5][2] = { -1, };   //0 =  맻번째 맵에서 몇번째 칸인지,  1 =  맵의 몇번째 것인지 , 마지막은 temp용
	for (int i = 0; i < 10; i++) {
		scanf("%d", &dice_number[i]);
	}

	function(0, 0, 0, dice_abcd);   //horse_player(0~3), current_count(0~9),temp_tot(임시값), int dice_abcd[][2];
	cout << final_tot << endl;
	cout << "Hello World!\n";
}

void  function(int play_horse, int current_count, int temp_tot, int dice_abcd[][2]) {

	if (current_count < 10) {

		for (int i = 0; i < 4; i++) {   //같은 칸에있는 숫자가있는지
			if (play_horse != i) {

				//지금 현재 말이 '시작점'or '종료점'에 있지 않고, 나온 값을 더해 봤을 때 그자리에 말이 없어야함
				if (map_value(play_horse, dice_abcd) != 0) {
					check_algorithm(5, dice_number[current_count], dice_abcd); //temp 용에 나온 dice값을 더한다.
					if (map_value(5, dice_abcd) == map_value(i, dice_abcd)) {
						return;
					}
				}
			}
		}

		check_algorithm(play_horse, dice_number[current_count], dice_abcd); //나온 주사위 값을  현재의 노드값에 추가 1,2,3,4,5
		temp_tot += map_value(play_horse, dice_abcd);
		final_tot = max(temp_tot, final_tot);
		cout << "이번 주사위는 : " << dice_number[current_count] << "\t현재의 값은 : " << map_value(play_horse, dice_abcd) << "\t 누적값은 : " << temp_tot << endl;


		check_algorithm(play_horse, dice_number[current_count], dice_abcd);

		function(0, current_count + 1, temp_tot, dice_abcd);
		function(1, current_count + 1, temp_tot, dice_abcd);
		function(2, current_count + 1, temp_tot, dice_abcd);
		function(3, current_count + 1, temp_tot, dice_abcd);
		return;
	}
	else {
		printf("%d", final_tot);
		return;

	}

}

void check_algorithm(int play_horse, int dice_number, int dice_abcd[][2]) {
	// 10, 20, 30,  에 걸렸을때 맵의 index 이동

	if (map_plus_value(play_horse, dice_number) == 10) {
		set_map_index(play_horse, 1, dice_abcd);
		add_map_location(play_horse, dice_number - 5, dice_abcd);
	}
	else if (map_plus_value(play_horse, dice_number) == 20) {
		set_map_index(play_horse, 2, dice_abcd);
		add_map_location(play_horse, dice_number - 10, dice_abcd);
	}
	//TODO: 30이 2개임
	else if (map_plus_value(play_horse, dice_number) == 30 && get_map_index(play_horse, dice_abcd) == 0) {
		set_map_index(play_horse, 3, dice_abcd);
		add_map_location(play_horse, dice_number - 15, dice_abcd);
	}


	//현재 위치에 + 새로나온 주사위 수  가 마지막 인덱스를 넘어갈때 -> 25 이상으로 넘어갈때
	else if (get_map_location(play_horse, dice_abcd) + dice_number >= sizeof(map[get_map_index(play_horse, dice_abcd)]) / sizeof(int)) {
		if (get_map_index(play_horse, dice_abcd) == 1 || get_map_index(play_horse, dice_abcd) == 3) {
			set_map_index(play_horse, 4, dice_abcd);
			add_map_location(play_horse, dice_number - 4, dice_abcd);
		}
		else if (get_map_index(play_horse, dice_abcd) == 2) {
			set_map_index(play_horse, 4, dice_abcd);
			add_map_location(play_horse, dice_number - 3, dice_abcd);
		}
		// 40에 걸렸을때 
		else if (get_map_index(play_horse, dice_abcd) == 0) {
			set_map_index(play_horse, 4, dice_abcd);
			add_map_location(play_horse, -17, dice_abcd);

		}
	}
	// 40에 이상 일때  0(종착점)으로 고정시키기
	if (get_map_index(play_horse, dice_abcd) == 4 && (get_map_location(play_horse, dice_abcd) > 3)) {
		set_map_location(play_horse, 4, dice_abcd);
	}
}

