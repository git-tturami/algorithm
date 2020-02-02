#include <iostream>
#include <stdio.h>
#include <vector>
using namespace std;
int N, M, T;
int ** arr;
int **rotation;
int x, d, k, temp;
int total_sum;
vector <int > lcm;
vector <pair<int, int >> dup_location;
vector <int> rotation_temp;

void allocate();
void rotate();
void duplicate();
void delocate();
void print_check();
void cal_sum();


int main() {

	allocate();
	rotate();
	cal_sum();
	delocate();



	return 0;
}
void rotate() {
	//모든 회전 1번
	for (int i = 0; i < T; i++) {
		x = rotation[i][0];
		d = rotation[i][1];
		k = rotation[i][2];
		lcm.clear();
		for (int i = 1; x*i <= N; i++) {
			if (x*i <= N) {
				lcm.push_back(x*i);
			}
		}
		//원판들 회전
		for (int i = 0; i < lcm.size(); i++)
		{//한줄
			int current_row = lcm.at(i) - 1;
			//수정필요
			rotation_temp.clear();
			if (d == 0) {//0번 시계방향 ->방향
				for (int j = 0; j < k; j++) {
					rotation_temp.push_back(arr[current_row][M - k + j]);
				}
				for (int j = M - k; j > 0; j--) {
					arr[current_row][j - 1 + k] = arr[current_row][j - 1];
				}
				for (int j = 0; j < k; j++)
				{
					arr[current_row][j] = rotation_temp.at(j);
				}

			}
			else {//d==1 반시계방향 <- 방향
				for (int j = 0; j < k; j++) {
					rotation_temp.push_back(arr[current_row][j]);
				}
				for (int j = k; j < M; j++) {
					arr[current_row][j - k] = arr[current_row][j];
				}
				for (int j = 0; j < k; j++)
				{
					arr[current_row][M - k + j] = rotation_temp.at(j);
				}
			}

		}
		//cout << "Before duplicate" << endl;
		//print_check();
		duplicate();
		//cout << "After duplicate" << endl;
		//print_check();
	}

}
void allocate() {
	cin >> N >> M >> T;


	arr = new int*[N];
	rotation = new int*[T];
	for (int i = 0; i < N; i++) {
		arr[i] = new int[M];
	}
	for (int i = 0; i < T; i++) {
		rotation[i] = new int[3];
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> arr[i][j];
		}
	}
	for (int i = 0; i < T; i++)
	{
		for (int j = 0; j < 3; j++) {
			cin >> rotation[i][j];
		}
	}

}
void delocate() {
	for (int i = 0; i < N; i++)
		delete[] arr[i];
	delete[] arr;
	for (int i = 0; i < T; i++)
		delete[] rotation[i];
	delete[] rotation;
}
void print_check() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << arr[i][j];
		}
		cout << endl;
	}
}
void duplicate() {
	dup_location.clear();
	//가로 체크
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {//->방향 기준으로 확인
			if (j == M - 1) {// 마지막 끝원소는 따로 검사
				if (arr[i][j] == arr[i][0] && arr[i][j] != 0) {
					dup_location.push_back(make_pair(i, j));
					dup_location.push_back(make_pair(i, 0));
				}
			}
			else {
				if (arr[i][j] == arr[i][j + 1] && arr[i][j] != 0) {
					dup_location.push_back(make_pair(i, j));
					dup_location.push_back(make_pair(i, j + 1));
				}
			}
		}
	}
	//세로 체크
	for (int i = 0; i < N - 1; i++) {
		for (int j = 0; j < M; j++) {//->방향 기준으로 확인
			if (arr[i][j] == arr[i + 1][j] && arr[i][j] != 0) {
				dup_location.push_back(make_pair(i, j));
				dup_location.push_back(make_pair(i + 1, j));

			}

		}
	}
	//같은 숫자 있음->중복 제거
	if (dup_location.size() != 0) {
		for (int i = 0; i < dup_location.size(); i++) {
			arr[dup_location.at(i).first][dup_location.at(i).second] = 0;
		}
	}
	//같은 숫자 없음
	else {
		float sum = 0;
		float avg = 0;
		float count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {//->방향 기준으로 확인
				if (arr[i][j] != 0) {
					count++;
					sum += arr[i][j];
				}
			}
		}
		avg = sum / count;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {//->방향 기준으로 확인
				if (arr[i][j] != 0) {
					if (arr[i][j] < avg) {
						arr[i][j] += 1;
					}
					else if (arr[i][j] > avg) {
						arr[i][j] -= 1;
					}
				}
			}
		}
	}


}
void cal_sum() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			total_sum += arr[i][j];
		}
	}
	cout << total_sum;
}