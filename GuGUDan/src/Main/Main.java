package Main;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	
	static int RankerNum = 10;
	static int[] Rank = new int[RankerNum];
	static String[] RankerName = new String[RankerNum];
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	static Timer time = new Timer();
	
	static TimerTask m_time = new TimerTask() {
		public void run() {
			System.out.print("안영");
	}};
	
	
	public static void main(String[] args) {
		
		rand.setSeed(System.currentTimeMillis());
		
		// 1  2 3
		// 5점 10점 15점
		// 30점 30점 + 6문제 + 10점추가
		char str;
		char RankStr;
		String RankName;
		
		do {
			int a, b, c, answer = 0, score=0, saveTime = 30, step=1, cuttingScore= 30;
			long startTime=0, endTime=0;
			boolean gameOver = false;
			
			System.out.print("99단 도전 하시겠나요 [Y/N]\n");
			System.out.print("A : ");
			str = scan.next().charAt(0);
			
			startTime = System.currentTimeMillis();
			while(str == 'y' || str == 'Y') {	
				// 단계 상승
				if(score >= cuttingScore) {
					step++;
					saveTime -= 2;
					cuttingScore += 6 * (5*step) + 10;
					startTime = System.currentTimeMillis();
				}	
				
				
				//  ====== 문제 시작 ======
				a = 2+ rand.nextInt(8);
				b = 2+ rand.nextInt(8);
				c = a * b;
				
				clearConsole();
				System.out.println("==========================================");
				System.out.printf("[ %d단계 | 제한시간 : %d초  | 현재 점수 : %d ]\n",step,saveTime, score);
				System.out.printf("Q : %d * %d\n", a, b);
				System.out.println("==========================================\n");
				System.out.print("A : ");
				//  ====== 답 입력 ======
				// 입력값애 문자가 있을 때 무한반복
				boolean flag;
				do {
					flag= false;
					
					try {	
						answer = scan.nextInt();
					}
					catch(InputMismatchException e){ 
						flag= true;
						scan.nextLine();
					}
				}while(flag);
				
				
				//  ====== 답 입력 후 ======
				// 타임오버 검사
				endTime = System.currentTimeMillis();
				if(endTime - startTime > saveTime*1000 || answer != c) {
					gameOver = true;
				}
				
				// 답 체크
				if(!gameOver && answer == c) {
					score += 5 * step;
				}
				
				
				// ==== 게임  끝  =====
				if (gameOver) {
					clearConsole();
					System.out.printf("[ 최종 점수 : %d점 ]\n\n",score);
					
					do {
						System.out.println("기록을 저장하시겠습니까? [Y/N]");
						System.out.print("A : ");
						RankStr = scan.next().charAt(0);
					}while(RankStr != 'y' && RankStr != 'n' && RankStr != 'Y' && RankStr != 'N');
					
					if(RankStr == 'Y' || RankStr == 'y') {
						System.out.println("[이름을 적어주세요]");
						System.out.print("A : ");
						do {
							RankName = scan.next();
						}while(RankName.equals(""));
						
						
					}
					
					
					do {
						System.out.println("다시 하시겠습니까? [Y/N]");
						System.out.print("A : ");
						str = scan.next().charAt(0);
						System.out.println("\n"+str);
					}while(str != 'y' && str != 'n' && str != 'Y' && str != 'N');
					break;
				}
			}
			clearConsole();
		}while(str == 'y' || str == 'Y');
		
		System.out.println("[ 게임을 종료합니다 ]");
	}
	
	static void clearConsole() {
		for(int i = 0; i < 100; i++) System.out.println("");
	}
}

