/* SWEA-1952 */

#include <cstdio>
using namespace std;

#define N 12

int MAX, day, month, thMonth;
int schedule[N + 1];
int d[N + 1];

void dfs(int cnt, int sum) {
	if (sum > MAX) return;
	if (cnt > 12) {
		MAX = sum < MAX ? sum : MAX;
		return;
	}

	dfs(cnt + 1, sum + d[cnt]);
	dfs(cnt + 3, sum + thMonth);
}

int main() {
	int tc;
	scanf("%d", &tc);

	for (int T = 1; T <= tc; T++) {
		scanf("%d %d %d %d", &day, &month, &thMonth, &MAX);

		for (int i = 1; i <= N; i++) {
			scanf("%d", &schedule[i]);
			d[i] = schedule[i] * day < month ? schedule[i] * day : month;
		}

		dfs(1, 0);
		printf("#%d %d\n", T, MAX);
	}

	return 0;
}