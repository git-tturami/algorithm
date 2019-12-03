#include <iostream>
using namespace std;

int cal(int s_w, int s_h);

int w, h;
char before = 'S';
int best = 64;
int temp = 0;
char board[50][50];

int main() {
	
	cin >> h;
	cin >> w;

	for (int i = 0; i < h; i++) {
		cin >> board[i];
	}

	for (int i = 0; i < h - 7; i++) {
		for (int j = 0; j < w - 7; j++) {
			cal(i, j);
		}
	}

	cout << best << endl;
	return 0;
}


int cal(int s_h, int s_w) {

	before = 'S';
	temp = 0;
	for (int i = s_h; i < s_h + 8; i++) {

		if (before == 'W') before = 'B';
		else if (before == 'B') before = 'W';
		else before = 'S';

		for (int j = s_w; j < s_w + 8; j++) {
			if (before == 'W') {
				if (board[i][j] == 'W') {
					temp++;
					/*if (temp >= best) {
						return 0;
					}*/
				}
				before = 'B';
			}
			else if (before == 'B') {
				if (board[i][j] == 'B') {
					temp++;
					/*if (temp >= best) {
						return 0;
					}*/
				}
				before = 'W';
			}
			else before = board[i][j]; 
		}
	}
	if (best > temp) best = temp;

	before = 'S';
	temp = 0;

	for (int i = s_h; i < s_h + 8; i++) {

		if (before == 'W') before = 'B';
		else if (before == 'B') before = 'W';
		else before = 'S';

		for (int j = s_w; j < s_w + 8; j++) {
			if (before == 'W') {
				if (board[i][j] == 'W') {
					temp++;
					/*if (temp >= best) {
						return 0;
					}*/
				}
				before = 'B';
			}
			else if (before == 'B') {
				if (board[i][j] == 'B') {
					temp++;
					/*if (temp >= best) {
						return 0;
					}*/
				}
				before = 'W';
			}
			else { 
				temp++;
				if (board[i][j] == 'W') {
					before = 'B';
				}
				else before = 'W';
			}
		}
	}
	if (best > temp) best = temp;

	return 0;
}