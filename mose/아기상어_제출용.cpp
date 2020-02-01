#include <stdio.h>
#include <iostream>
#include <vector>
#include <math.h>
#include <queue>
#include <algorithm>
#include <time.h>

using namespace std;
int shark_lv = 2;
int array_size;
int shark_eat_count = 0;
int shark_location[2];
int arr[22][22];
int arr_temp[22][22];
int arr_temp_copy[22][22];
int for_best_count = 0; // 한 위치를 찾을때의 최소의 count를 찾기위해
int final_count = 10000; // 같은 먹이를 찾을때의 작은 count의 먹이를 가이 위해
int total_final_count = 0; // 여태까지움직인 횟수의 count
int isFind = 0;
int isFinish = 0;
int temp_x = 0, temp_y = 0, temp_count = 0;

//queue <pair<int, int,int>> finds;

struct location_count { int x; int y; int count; };
location_count a;
queue<location_count> finds;
vector<vector <int>> available_eat;


void check_food_exist();
void find_min_location(int location_x, int location_y);
void call_function(int location_x, int location_y, int count);

int main()
{
	scanf("%d", &array_size);
	for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 20; j++) {
			arr_temp[i][j] = 1;
		}
	}

	for (int i = 0; i < array_size; i++) {
		for (int j = 0; j < array_size; j++) {
			//array 값 할당
			scanf(" %d", &arr[i][j]);
			if (arr[i][j] == 9) {
				shark_location[0] = i;
				shark_location[1] = j;

			}
		}
	}

	while (isFinish == 0) {
		available_eat.clear();
		check_food_exist();
		find_min_location(shark_location[0], shark_location[1]);
	}
	printf("%d", total_final_count);


}
// 먹을 수 있는 물고기가 있는지 체크 하고  vector 에 추가
void check_food_exist() {
	int temp_location[2];
	for (int i = 0; i < array_size; i++) {
		for (int j = 0; j < array_size; j++) {
			//array 값 할당
			if (arr[i][j] == 9) {
				arr_temp_copy[i][j] = 1;
			}
			else if (arr[i][j] == 0) {
				arr_temp_copy[i][j] = 0;
			}
			else if (arr[i][j] < shark_lv && arr[i][j] != 0) {
				arr_temp_copy[i][j] = 0;
				temp_location[0] = i;
				temp_location[1] = j;
				vector <int> a;
				a.push_back(temp_location[0]);
				a.push_back(temp_location[1]);
				available_eat.push_back(a);
			}
			else if (arr[i][j] == shark_lv) {
				arr_temp_copy[i][j] = 0;
			}
			else {
				arr_temp_copy[i][j] = 1;
			}
		}
	}


}
//location_x,y 는 시작 위치
void find_min_location(int location_x, int location_y) {
	int temp_location[2];
	int final_location[2] = { 20,20 };// max값을 입력 해둠
	int temp_count = 0;
	int isFind_overone = 0;
	if (available_eat.empty())
	{
		isFinish = 1;
	}
	//리스트중 한개를 선택하는 과정
	while (!available_eat.empty()) {
		// 사용할 것들 초기화
		for (int i = 0; i < array_size; i++) {
			for (int j = 0; j < array_size; j++) {
				arr_temp[i][j] = arr_temp_copy[i][j];
			}
		}

		isFind = 0;
		for_best_count = 10000;
		queue<location_count> empty;
		swap(finds, empty);

		temp_location[0] = available_eat.back()[0];
		temp_location[1] = available_eat.back()[1];
		available_eat.pop_back();

		arr_temp[temp_location[0]][temp_location[1]] = 2;

		// 함수 실행
		if (final_count >= abs(location_x - temp_location[0]) + abs(location_y - temp_location[1])) {
			call_function(location_x, location_y, temp_count);

			int temp;
			if (isFind) {
				temp = for_best_count;
				isFind_overone = 1;

				//더 작은 count일경우
				if (final_count > temp) {
					final_count = temp;
					final_location[0] = temp_location[0];
					final_location[1] = temp_location[1];
				}// count가 같은것을 발견 했을때
				else if (final_count == temp) {
					// 세로를 비교(더 위쪾에 있는것을 선택)
					if (final_location[0] > temp_location[0]) {
						final_count = temp;
						final_location[0] = temp_location[0];
						final_location[1] = temp_location[1];
					}// 가로를 비교( 더 왼쪽에 있는것을 선택)
					else if (final_location[0] == temp_location[0]) {
						if (final_location[1] > temp_location[1]) {
							final_count = temp;
							final_location[0] = temp_location[0];
							final_location[1] = temp_location[1];
						}
					}
				}
			}
			else {
				temp = 0;
			}

			arr_temp[temp_location[0]][temp_location[1]] = 0;
			
		}

	}
	if (isFind_overone == 1) {//현재 위치하는 자리를 빈칸으로 만든다
		arr[shark_location[0]][shark_location[1]] = 0;
		// 이동의 변경 사항들
		total_final_count += final_count;
		final_count = 10000;
		shark_location[0] = final_location[0];
		shark_location[1] = final_location[1];
		arr[shark_location[0]][shark_location[1]] = 9;
		shark_eat_count++;

		//shark의 레벨업
		if (shark_lv == shark_eat_count) {
			shark_lv++;
			shark_eat_count = 0;

		}
	}
	else {
		isFinish = 1;
	}



}
// count해주는 함수
void call_function(int location_x, int location_y, int count) {
	if (isFind == 0) {

		// 범위 넘어가는것 체크 못하는 현상
		if (location_x - 1 >= 0 && location_x - 1 < array_size &&location_y >= 0 && location_y < array_size &&
			arr_temp[location_x - 1][location_y] != 1) {
			if (arr_temp[location_x - 1][location_y] == 2) {
				isFind = 1;
				for_best_count = count + 1;
			}
			else {
				a.x = location_x - 1;
				a.y = location_y;
				a.count = count + 1;
				finds.push(a);
				arr_temp[location_x - 1][location_y] = 1;

			}
		}if (location_x >= 0 && location_x < array_size &&location_y + 1 >= 0 && location_y + 1 < array_size &&
			arr_temp[location_x][location_y + 1] != 1) {
			if (arr_temp[location_x][location_y + 1] == 2) {
				isFind = 1;
				for_best_count = count + 1;
			}
			else {
				a.x = location_x;
				a.y = location_y + 1;
				a.count = count + 1;
				finds.push(a);
				arr_temp[location_x][location_y + 1] = 1;

			}
		}if (location_x + 1 >= 0 && location_x + 1 < array_size &&location_y >= 0 && location_y < array_size &&
			arr_temp[location_x + 1][location_y] != 1) {
			if (arr_temp[location_x + 1][location_y] == 2) {
				isFind = 1;
				for_best_count = count + 1;
			}
			else {
				a.x = location_x + 1;
				a.y = location_y;
				a.count = count + 1;
				finds.push(a);
				arr_temp[location_x + 1][location_y] = 1;

			}

		}if (location_x >= 0 && location_x < array_size &&location_y - 1 >= 0 && location_y - 1 < array_size &&
			arr_temp[location_x][location_y - 1] != 1) {
			if (arr_temp[location_x][location_y - 1] == 2) {
				isFind = 1;
				for_best_count = count + 1;
			}
			else {
				a.x = location_x;
				a.y = location_y - 1;
				a.count = count + 1;
				finds.push(a);
				arr_temp[location_x][location_y - 1] = 1;

			}

		}if (isFind == 0 && finds.size() != 0) {
			temp_x = finds.front().x;
			temp_y = finds.front().y;
			temp_count = finds.front().count;
			finds.pop();
			call_function(temp_x, temp_y, temp_count);
		}
	}

}