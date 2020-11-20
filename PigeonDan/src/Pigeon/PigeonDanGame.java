package Pigeon;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PigeonDanGame {
	
	// 입력 관련 변수
	static Scanner scan = new Scanner(System.in);
	
	// 랜덤 관련 변수
	static Random rand = new Random();
	
	// 시간 관련 변수
	static Timer time = new Timer();
	static ScheduledExecutorService  timer = Executors.newSingleThreadScheduledExecutor();			//타이머 인스턴트화
	
	// 게임 관련 변수 
	static int saveTime = 30;	// 제한시간 
	static int exp = 0;			// 전체 맞힌 문제 수
	
	static boolean gameOver = false;	// 게임 끝
	static boolean timeOver = false;	// 타이머 멈춤
	
		
	
	static ScheduledFuture<?> futureTask = null;					//스케줄 타입의 futureTask 값 없애기
        
    
	public static void state() {
		
		//  ======== 게임 시작  ========
		while(true){	
			rand.setSeed(System.currentTimeMillis());	// 랜덤 값 랜덤하게 변경(?)
			
			int live = 10;
			int score=0, stage=1, sumRest = 0;
			int sumTime = 0,maxTime = 0, minTime = saveTime, startTime=0;
			
			String[] wrongAnswers = new String[live];
			
			init();	// 게임 시작 전 전역 변수 초기화 
			
			
			// ======== 단계 시작 ========
			while(true) {
				int rest = 0;
				
				// 타이머 작동
				futureTask = timer.scheduleAtFixedRate(Timer_stage.timer_stage(exp,saveTime), 0, 1,TimeUnit.SECONDS);	//타이머 클래스에서 함수를 스케줄에 집어넣기
				
				// 단계 상승
				if(exp != 0) {
					stage++;
					if(saveTime> 3 +2) saveTime -= 2;	// 최소 시간 3초
				}	
				
				
				
				//  ======== 문제 시작  ========
				do{	
					startTime = Timer_stage.timeCount;
					int a, b, c, answer=0;
					
					a = 2+ rand.nextInt(8);
					b = 2+ rand.nextInt(8);
					c = a * b;
					
					clearConsole();
					System.out.println("==========================================");
					System.out.print("[");
					for(int i = 0; i < rest; i++) System.out.print("■");
					for(int i = rest; i < live; i++) System.out.print("□");
					System.out.println("]\n");
					
					System.out.printf("[ %d단계 | 제한시간 : %d초 ] ",stage, saveTime);
					System.out.printf("[남은 시간 : %d초  | 현재 점수 : %d ]\n",saveTime - Timer_stage.timeCount, score);
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
							System.out.print("문자가 포함되었습니다 다시 압력: "+ !gameOver +"&&"+ (rest!=10) +"="+ (!gameOver && rest!=10));
						}
					}while(!gameOver && flag);
					
					rest++;	
					//  ====== 답 입력 후 ======		
					// 답 체크
					if(!gameOver) {
						if(answer == c) {
							score += 5 * stage;
							exp++;
						}else{
							wrongAnswers[(sumRest+rest-1)-exp] = a + " * " + b;
						}
						
						
						
						int answerTime = Timer_stage.timeCount - startTime;
						if(maxTime < answerTime) maxTime = answerTime;
						if(minTime > answerTime) minTime = answerTime;
					}
				}while(!gameOver && rest<live);
				
				sumRest += rest;
				sumTime += Timer_stage.timeCount;
				
				// 문제를 하나라도 틀린 경우 게임 오버 
				if(exp==0 || exp%live != 0) {
					gameOver = true;
				}
				
				// ==== 게임  끝  =====
				if (gameOver) break;
			}
			if (gameOver) {
				char str;
				clearConsole();
				System.out.printf("=== 틀린 문제 리스트입니다 ===\n");
				for(int i=0; i<wrongAnswers.length; i++) if(wrongAnswers[i]!=null) System.out.println((i+1)+"번 - "+wrongAnswers[i]);
				
				System.out.printf("\n\n**모든 시간은 소수점까지 계산하지 않아 정확하지 않을 수 있습니다.**\n");
				System.out.printf("[ 총 소비한 시간: %d초, 푼 문제: %d개, 맞춘 문제: %d개, 최대 단계: %d단계 ]\n",sumTime, sumRest, exp, stage);
				System.out.printf("[ 한 문제당 소비한 평균 시간: %d초, 문제에 소비한 최대 시간: %d초, 문제에 소비한 최소 시간: %d초 ]\n", sumTime/sumRest, maxTime, minTime);
				System.out.printf("[ 최종 점수 : %d점 ]\n\n",score);
				
				
				str = getYN("기록을 저장하시겠습니까? [Y/N]");
				if(str == 'Y' || str == 'y') RankSystem(score);
				
				str = getYN("다시 하시겠습니까? [Y/N]");
				if(str=='n' || str == 'N') break;
			}
			clearConsole();
		}
	}
	
	// y or n 입력 받는게 좀 있어서 만든 메소드 
	public static char getYN(String string) {
		char str;
		
		do {
			System.out.println(string);
			System.out.print("A : ");
			str = scan.next().charAt(0);
		}while(str != 'y' && str != 'n' && str != 'Y' && str != 'N');
		
		return str;
	}
	
	public static void RankSystem(int score) {
		String rankName;
		
		System.out.println("[이름을 적어주세요] (최대 3자)");
		System.out.print("A : ");
		do {
			rankName = scan.next();
		}while(rankName.equals(""));
		
		RankKing.Add(rankName, score);
	
		RankKing.RankList();
	}
	
	static void clearConsole() {
		for(int i = 0; i < 100; i++) System.out.println("");
	}

	
	static private void init() {
		saveTime = 30;
		exp = 0;
		gameOver = false;
		timeOver = false;
	}
}

