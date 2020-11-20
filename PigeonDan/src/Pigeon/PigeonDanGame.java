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
	
	// �Է� ���� ����
	static Scanner scan = new Scanner(System.in);
	
	// ���� ���� ����
	static Random rand = new Random();
	
	// �ð� ���� ����
	static Timer time = new Timer();
	static ScheduledExecutorService  timer = Executors.newSingleThreadScheduledExecutor();			//Ÿ�̸� �ν���Ʈȭ
	
	// ���� ���� ���� 
	static int saveTime = 30;	// ���ѽð� 
	static int exp = 0;			// ��ü ���� ���� ��
	
	static boolean gameOver = false;	// ���� ��
	static boolean timeOver = false;	// Ÿ�̸� ����
	
		
	
	static ScheduledFuture<?> futureTask = null;					//������ Ÿ���� futureTask �� ���ֱ�
        
    
	public static void state() {
		
		//  ======== ���� ����  ========
		while(true){	
			rand.setSeed(System.currentTimeMillis());	// ���� �� �����ϰ� ����(?)
			
			int live = 10;
			int score=0, stage=1, sumRest = 0;
			int sumTime = 0,maxTime = 0, minTime = saveTime, startTime=0;
			
			String[] wrongAnswers = new String[live];
			
			init();	// ���� ���� �� ���� ���� �ʱ�ȭ 
			
			
			// ======== �ܰ� ���� ========
			while(true) {
				int rest = 0;
				
				// Ÿ�̸� �۵�
				futureTask = timer.scheduleAtFixedRate(Timer_stage.timer_stage(exp,saveTime), 0, 1,TimeUnit.SECONDS);	//Ÿ�̸� Ŭ�������� �Լ��� �����ٿ� ����ֱ�
				
				// �ܰ� ���
				if(exp != 0) {
					stage++;
					if(saveTime> 3 +2) saveTime -= 2;	// �ּ� �ð� 3��
				}	
				
				
				
				//  ======== ���� ����  ========
				do{	
					startTime = Timer_stage.timeCount;
					int a, b, c, answer=0;
					
					a = 2+ rand.nextInt(8);
					b = 2+ rand.nextInt(8);
					c = a * b;
					
					clearConsole();
					System.out.println("==========================================");
					System.out.print("[");
					for(int i = 0; i < rest; i++) System.out.print("��");
					for(int i = rest; i < live; i++) System.out.print("��");
					System.out.println("]\n");
					
					System.out.printf("[ %d�ܰ� | ���ѽð� : %d�� ] ",stage, saveTime);
					System.out.printf("[���� �ð� : %d��  | ���� ���� : %d ]\n",saveTime - Timer_stage.timeCount, score);
					System.out.printf("Q : %d * %d\n", a, b);
					System.out.println("==========================================\n");
					System.out.print("A : ");
					
					//  ====== �� �Է� ======
					// �Է°��� ���ڰ� ���� �� ���ѹݺ�
					boolean flag;
					do {
						flag= false;
						
						try {	
							answer = scan.nextInt();
						}
						catch(InputMismatchException e){ 
							flag= true;
							scan.nextLine();
							System.out.print("���ڰ� ���ԵǾ����ϴ� �ٽ� �з�: "+ !gameOver +"&&"+ (rest!=10) +"="+ (!gameOver && rest!=10));
						}
					}while(!gameOver && flag);
					
					rest++;	
					//  ====== �� �Է� �� ======		
					// �� üũ
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
				
				// ������ �ϳ��� Ʋ�� ��� ���� ���� 
				if(exp==0 || exp%live != 0) {
					gameOver = true;
				}
				
				// ==== ����  ��  =====
				if (gameOver) break;
			}
			if (gameOver) {
				char str;
				clearConsole();
				System.out.printf("=== Ʋ�� ���� ����Ʈ�Դϴ� ===\n");
				for(int i=0; i<wrongAnswers.length; i++) if(wrongAnswers[i]!=null) System.out.println((i+1)+"�� - "+wrongAnswers[i]);
				
				System.out.printf("\n\n**��� �ð��� �Ҽ������� ������� �ʾ� ��Ȯ���� ���� �� �ֽ��ϴ�.**\n");
				System.out.printf("[ �� �Һ��� �ð�: %d��, Ǭ ����: %d��, ���� ����: %d��, �ִ� �ܰ�: %d�ܰ� ]\n",sumTime, sumRest, exp, stage);
				System.out.printf("[ �� ������ �Һ��� ��� �ð�: %d��, ������ �Һ��� �ִ� �ð�: %d��, ������ �Һ��� �ּ� �ð�: %d�� ]\n", sumTime/sumRest, maxTime, minTime);
				System.out.printf("[ ���� ���� : %d�� ]\n\n",score);
				
				
				str = getYN("����� �����Ͻðڽ��ϱ�? [Y/N]");
				if(str == 'Y' || str == 'y') RankSystem(score);
				
				str = getYN("�ٽ� �Ͻðڽ��ϱ�? [Y/N]");
				if(str=='n' || str == 'N') break;
			}
			clearConsole();
		}
	}
	
	// y or n �Է� �޴°� �� �־ ���� �޼ҵ� 
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
		
		System.out.println("[�̸��� �����ּ���] (�ִ� 3��)");
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

