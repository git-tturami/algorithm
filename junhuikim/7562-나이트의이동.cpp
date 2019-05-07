#include <stdio.h>
#include <iostream>
#include <queue>

using namespace std;

int dr[8] = {-1, -2, -2, -1, 1, 2, 2, 1};
int dc[8] = {-2, -1, 1, 2, 2, 1, -1, -2};

int map[300][300];

struct position {
    int row;
    int col;

    int time;
};

queue<position> q;

void init() {
    for (int i = 0; i < 300; i++) {
        for (int j = 0; j < 300; j++) {
            map[i][j] = 0;
        }
    }

    while (!q.empty()) {
        q.pop();
    }
}

int main(void) {
    int T = 0;

    int mapSize, kRow, kCol, dstRow, dstCol;

    cin >> T;

    for (int test = 0; test < T; test++) {
        init();

        cin >> mapSize >> kRow >> kCol >> dstRow >> dstCol;

        q.push({kRow, kCol, 0});

        map[kRow][kCol]++;

        while (!q.empty()) {
            position pos = q.front();
            q.pop();

            if (pos.row == dstRow && pos.col == dstCol) {
                cout << pos.time << endl;
                break;
            }

            for (int i = 0; i < 8; i++) {
                int nextRow = pos.row + dr[i];
                int nextCol = pos.col + dc[i];

                if (nextRow >= 0 && nextRow < mapSize && nextCol >= 0 && nextCol < mapSize) {
                    if (map[nextRow][nextCol] == 0) {
                        q.push({nextRow, nextCol, pos.time + 1});
                        map[nextRow][nextCol]++;
                    }
                }
            }
        }
    }

    return 0;
}
