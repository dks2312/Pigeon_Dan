package Pigeon;

import java.util.Scanner;

public class RankKing {
	static Scanner scan = new Scanner(System.in);
	
	static int[] Rank = new int[10];
	static String[] RankerName = new String[10];
	
	// 랭킹 배열을 형식에 맞게 출력
	public static void RankList() {
		System.out.println("======== 랭킹 ========");
		String Name = "";
		String num = "0";
		for(int i = 0; i < RankerName.length; i++) {
			Name = RankerName[i] == null ? "---" : RankerName[i];
			num = Rank[i] == 0 ? "---" : Rank[i]+"";
			System.out.printf("%2d | %3s   %s\n\n",i+1,Name,num);
		}
	}
	
	// 랭킹 배열에 이름, 점수를 추가
	public static void Add(String rankName, int score) {
		for(int i = 0; i < RankerName.length; i++) {
			if(Rank[i] < score) {
				RankSort(Rank,i,score);
				RankerSort(RankerName,i,rankName);
				break;
			}
		}
	}
		
	static void RankSort(int[] list,int i, int score) {
		int temp = list[i];
		int temp2 = list[i];
		
		list[i] = score;
		for(int j = i+1; j<list.length; j++) {
			temp2 = list[j];
			list[j] = temp;
			temp = temp2;
		}
	}
	
	static void RankerSort(String[] list,int i ,String name) {
		String temp = list[i];
		String temp2 = list[i];
		
		list[i] = name;
		for(int j = i+1; j<list.length; j++) {
			temp2 = list[j];
			list[j] = temp;
			temp = temp2;
		}
	}
}
