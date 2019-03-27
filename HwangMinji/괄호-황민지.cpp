#pragma warning (disable:4996)
#include <cstdio>
#include <string.h>
using namespace std;

#define N 52 

struct stack {
	char arr[N] = { 0, };
	int ptr = -1;

	char top() {
		return arr[ptr];
	}

	void push(char c) {
		ptr++;
		arr[ptr] = c;
	}

	void pop() {
		if (ptr == -1) printf("Stack is empty.\n");
		else {
			arr[ptr] = 0;
			ptr--;
		}
	}

	int stkSize() {
		return ptr + 1;
	}
};

int main() {
	freopen("input.txt.", "r", stdin);
	int tc;
	scanf("%d", &tc);

	for (int T = 1; T <= tc; T++) {
		char str[N] = { 0, };
		scanf("%s", str);

		stack stk;

		for (int i = 0; i < strlen(str); i++) {
			if (str[i] == '(') {
				stk.push(str[i]);
			}
			else if (str[i] == ')') {
				if (stk.top() == '(') {
					stk.pop();
				}
				else {
					stk.push(str[i]);
					break;
				}
			}
		}

		if (stk.stkSize() == 0) printf("YES\n");
		else printf("NO\n");
	}
	return 0;
}