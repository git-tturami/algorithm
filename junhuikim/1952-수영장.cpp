#include <stdio.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

int price[4] = {0, 0, 0, 0};
int plan[12] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
int minPrice;

void solve(int m, int cost);

int main(void) {
    int T;
    scanf("%d", &T);
    for (int i = 0; i < T; i++) {
        for (int j = 0; j < 4; j++) {
            scanf("%d", &price[j]);
        }

        for (int j = 0; j < 12; j++) {
            scanf("%d", &plan[j]);
        }

        minPrice = price[3];
        solve(0, 0);

        printf("#%d %d\n", i + 1, minPrice);
    }

    return 0;
}

void solve(int m, int cost) {
    if (m >= 12) {
        if (cost < minPrice) {
            minPrice = cost;
        }

        return;
    }

    if (plan[m] == 0) {
        solve(m + 1, cost);
    }

    // 3 month
    solve(m + 3, cost + price[2]);
    // 1 month
    solve(m + 1, cost + price[1]);
    // 1 day
    solve(m + 1, cost + price[0] * plan[m]);
}
