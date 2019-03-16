/* 14501Ήψ: Επ»η */

#include <cstdio>
#include <vector>
using namespace std;

#define pi pair<int, int>
#define N 16
int n, t, p;
pi list[N];
int d[N];

int main() {
	scanf("%d", &n);
	for (int i = 1; i <= n; i++) {
		scanf("%d %d", &t, &p);
		list[i] = { t,p };
	}

	for(int i = 1; i <= n; i++) {
		int nd = i + list[i].first - 1;
		int np = d[i - 1] + list[i].second;

		if (nd > n) continue;
		for (int j = nd; j <= n; j++)
			d[j] = np > d[j] ? np : d[j];
	}

	printf("%d\n", d[n]);
	return 0;
}