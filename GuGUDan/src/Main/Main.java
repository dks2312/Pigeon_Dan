package Main;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	
	static int RankerNum = 10;
	static int[] Rank = new int[RankerNum];
	static String[] RankerName = {"","","","","","","","","",""};
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
		String RankName = "";
		
		do {
			int a, b, c, answer = 0, score=0, saveTime = 30, step=1, cuttingScore= 30,exp = 0;
			long startTime=0, endTime=0;
			boolean gameOver = false;
			
			System.out.print("99�� ���� �Ͻðڳ��� [Y/N]\n");
			System.out.print("A : ");
			str = scan.next().charAt(0);
			
			startTime = System.currentTimeMillis();
			while(str == 'y' || str == 'Y') {	
				// �ܰ� ���
				if(exp >= 10) {
					step++;
					exp = 0;
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
				System.out.print("[");
				for(int i = 0; i < exp; i++) System.out.print("��");
				for(int i = exp; i < 10; i++) System.out.print("��");
				System.out.println("]\n");
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
					exp+=1;
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
						clearConsole();
						System.out.println("[�̸��� �����ּ���] (�ִ� 3��)");
						System.out.print("A : ");
						do {
							RankName = scan.next();
						}while(RankName.equals(""));
						
						for(int i = 0; i < RankerNum; i++) {
							if(Rank[i] < score) {
								RankSort(Rank,i,score);
								RankerSort(RankerName,i,RankName);
								break;
							}
						}
						
						clearConsole();
						
						System.out.println("======== ��ŷ ========");
						String Name = "";
						String num = "0";
						for(int i = 0; i < RankerNum; i++) {
							Name = RankerName[i].equals("") ? "---" : RankerName[i];
							num = Rank[i] == 0 ? "---" : Rank[i]+"";
							System.out.printf("%2d | %3s   %s\n\n",i+1,Name,num);
						}
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
	
	static void RankSort(int[] list,int i, int score) {
		int temp = list[i];
		int temp2 = list[i];
		list[i] = score;
		for(int j = i+1; j<RankerNum; j++) {
			temp2 = list[j];
			list[j] = temp;
			temp = temp2;
		}
	}
	
	static void RankerSort(String[] list,int i ,String name) {
		String temp = list[i];
		String temp2 = list[i];
		
		list[i] = name;
		for(int j = i+1; j<RankerNum; j++) {
			temp2 = list[j];
			list[j] = temp;
			temp = temp2;
		}
	}
}

