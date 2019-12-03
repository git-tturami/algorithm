#include <iostream>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

int SetMoney[4] = { 0, };
int MonthArray[12] = { 0, };
int MonthPrice[12] = { 0, };
int TotalMinMoney = 0;

void minPrice(int month, int cost);

int main()
{
	int Tcount;
	printf("T의 값을 입력하세요");
	scanf_s("%d", &Tcount);
	printf("%d\n", Tcount);

	for (int i = 0; i < Tcount; i++)
	{
		printf("가격 4개\n");
		for (int j = 0; j < 4; j++)//각 일 월 3월 1년 기준으로 가격 정하기
		{

			scanf_s("%d", &SetMoney[j]);
		}
		for (int j = 0; j < 4; j++)//각 일 월 3월 1년 기준으로 가격 정하기
		{

			printf("%d", SetMoney[j]);
		}
		printf("달별 요금 12개\n");
		for (int j = 0; j < 12; j++)
		{

			scanf_s("%d", &MonthArray[j]);
			if (SetMoney[0] * MonthArray[j] >= SetMoney[1])// 1일권과 한달권 비교 사용
			{
				MonthPrice[j] = SetMoney[1];
			}
			else
			{
				MonthPrice[j] = SetMoney[0] * MonthArray[j];
			}

		}
		TotalMinMoney = SetMoney[3];
		minPrice(0, 0);
		printf("#%d %d", i + 1, TotalMinMoney);
		TotalMinMoney = 0;

	}

	return 0;

}

void minPrice(int month, int cost)
{
	if (month >= 12) //최종 합이 12월이 초과되면 return으로 끝내기.
	{
		if (TotalMinMoney > cost)
		{
			TotalMinMoney = cost;
		}
		return;
	}

	if (MonthArray[month] == 0) //해당월에 사람이 없을 때 다음날 로 넘어감
	{
		minPrice(month + 1, cost);
	}

	minPrice(month + 3, cost + SetMoney[2]); // 3달권을 추가
	minPrice(month + 1, cost + MonthPrice[month]);//하루권과 한달권의 최저값을 계산한 배열 값


}