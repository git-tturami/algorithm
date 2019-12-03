#include <iostream>
#include <queue>
using namespace std;

char building[30][30][30];
char building_t[30][30][30];
int l, r, c;
int s_l, s_r, s_c;
int d_l, d_r, d_c;
int t_lrc[3];
int best;
int temp;
char s[10];

struct point{
	int n_l;
	int n_r;
	int n_c;

	int move;
};

point point_t;

queue<point> q;

int main() {
	while (1) {
		best = 9999;
		temp = 0;
		cin >> l;
		cin >> r;
		cin >> c;

		if (l == 0 && r == 0 && c == 0) break;

		for (int i = 0; i < l; i++) {
			for (int j = 0; j < r; j++) {
				cin >> building[i][j];
			}
			cin.getline(s, 5);
		}

		for (int i = 0; i < l; i++) {
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					if (building[i][j][k] == '.') building_t[i][j][k] = '1';
					else if (building[i][j][k] == 'S') {
						q.push({ i,j,k,0 });
						building_t[i][j][k] = '0';
					} else if (building[i][j][k] == 'E') {
						building_t[i][j][k] = '2';
					} else building_t[i][j][k] = '0';
				}
			}
		}

		while (1) {
			if (best != 9999) break;
			if (q.empty()) {
				cout << "Trapped!" << endl;
				break;
			}
			point_t = q.front();
			q.pop();
			t_lrc[0] = point_t.n_l;
			t_lrc[1] = point_t.n_r;
			t_lrc[2] = point_t.n_c;
			temp = ++point_t.move;
			for (int i = 0; i < 3; i++) {
				t_lrc[i]++;
				if (t_lrc[0] < l && t_lrc[1] < r && t_lrc[2] < c) {
					if (building_t[t_lrc[0]][t_lrc[1]][t_lrc[2]] == '1') {
						q.push({ t_lrc[0], t_lrc[1], t_lrc[2], temp });
						building_t[t_lrc[0]][t_lrc[1]][t_lrc[2]] = '0';
					}
					if (building_t[t_lrc[0]][t_lrc[1]][t_lrc[2]] == '2') {
						best = temp;

						cout << "Escaped in " << best <<" minute(s)." << endl;
						break;
					}
				}
				t_lrc[i] -= 2;
				if (t_lrc[0] >= 0 && t_lrc[1] >= 0 && t_lrc[2] >= 0) {
					if (building_t[t_lrc[0]][t_lrc[1]][t_lrc[2]] == '1') {
						q.push({ t_lrc[0], t_lrc[1], t_lrc[2], temp });
						building_t[t_lrc[0]][t_lrc[1]][t_lrc[2]] = '0';
					}
					if (building_t[t_lrc[0]][t_lrc[1]][t_lrc[2]] == '2') {
						best = temp;

						cout << "Escaped in " << best << " minute(s)." << endl;
						break;
					}
				}
				t_lrc[i]++;
			}
		}
		while (!q.empty()) {
			q.pop();
		}
		
	}

	


	return 0;
}


