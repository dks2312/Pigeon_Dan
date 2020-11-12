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
			System.out.print("�ȿ�");
	}};
	
	
	public static void main(String[] args) {
		
		rand.setSeed(System.currentTimeMillis());
		
		// 1  2 3
		// 5�� 10�� 15��
		// 30�� 30�� + 6���� + 10���߰�
		char str;
		char RankStr;
		String RankName;
		
		do {
			int a, b, c, answer = 0, score=0, saveTime = 30, step=1, cuttingScore= 30;
			long startTime=0, endTime=0;
			boolean gameOver = false;
			
			System.out.print("99�� ���� �Ͻðڳ��� [Y/N]\n");
			System.out.print("A : ");
			str = scan.next().charAt(0);
			
			startTime = System.currentTimeMillis();
			while(str == 'y' || str == 'Y') {	
				// �ܰ� ���
				if(score >= cuttingScore) {
					step++;
					saveTime -= 2;
					cuttingScore += 6 * (5*step) + 10;
					startTime = System.currentTimeMillis();
				}	
				
				
				//  ====== ���� ���� ======
				a = 2+ rand.nextInt(8);
				b = 2+ rand.nextInt(8);
				c = a * b;
				
				clearConsole();
				System.out.println("==========================================");
				System.out.printf("[ %d�ܰ� | ���ѽð� : %d��  | ���� ���� : %d ]\n",step,saveTime, score);
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
					}
				}while(flag);
				
				
				//  ====== �� �Է� �� ======
				// Ÿ�ӿ��� �˻�
				endTime = System.currentTimeMillis();
				if(endTime - startTime > saveTime*1000 || answer != c) {
					gameOver = true;
				}
				
				// �� üũ
				if(!gameOver && answer == c) {
					score += 5 * step;
				}
				
				
				// ==== ����  ��  =====
				if (gameOver) {
					clearConsole();
					System.out.printf("[ ���� ���� : %d�� ]\n\n",score);
					
					do {
						System.out.println("����� �����Ͻðڽ��ϱ�? [Y/N]");
						System.out.print("A : ");
						RankStr = scan.next().charAt(0);
					}while(RankStr != 'y' && RankStr != 'n' && RankStr != 'Y' && RankStr != 'N');
					
					if(RankStr == 'Y' || RankStr == 'y') {
						System.out.println("[�̸��� �����ּ���]");
						System.out.print("A : ");
						do {
							RankName = scan.next();
						}while(RankName.equals(""));
						
						
					}
					
					
					do {
						System.out.println("�ٽ� �Ͻðڽ��ϱ�? [Y/N]");
						System.out.print("A : ");
						str = scan.next().charAt(0);
						System.out.println("\n"+str);
					}while(str != 'y' && str != 'n' && str != 'Y' && str != 'N');
					break;
				}
			}
			clearConsole();
		}while(str == 'y' || str == 'Y');
		
		System.out.println("[ ������ �����մϴ� ]");
	}
	
	static void clearConsole() {
		for(int i = 0; i < 100; i++) System.out.println("");
	}
}

