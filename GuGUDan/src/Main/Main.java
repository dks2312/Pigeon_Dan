
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {
	
	static int RankerNum = 10;
	static int[] Rank = new int[RankerNum];
	static String[] RankerName = {"","","","","","","","","",""};
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	static boolean out = true;
	static int count=0;
	static Timer time = new Timer();
	static int cuttingScore= 30;
	static int saveTime = 30;
	static int exp = 0;
	static ScheduledExecutorService  timer = Executors.newSingleThreadScheduledExecutor();			//Ÿ�̸� �ν���Ʈȭ
	static int stage=1;
	static int rest;
	static boolean gameOver = false;
	static boolean abc;												//Ÿ�̸� Ŭ������ ���� ���� ����
	static ScheduledFuture<?> futureTask = null;					//������ Ÿ���� futureTask �� ���ֱ�
        
    
	public static void main(String[] args) {
		
		rand.setSeed(System.currentTimeMillis());
		
		// 1  2 3
		// 5�� 10�� 15��
		// 30�� 30�� + 6���� + 10���߰�
		char str;
		char RankStr;
		String RankName = "";
		do {
			int a, b, c, answer = 0, score=0, step=1;
			long startTime=0, endTime=0;
			saveTime = 30;
			int io=0;
			gameOver=false;
			stage=1;
			rest=0;
			exp=0;
			System.out.print("99�� ���� �Ͻðڳ��� [Y/N]\n");
			System.out.print("A : ");
			str = scan.next().charAt(0);
			startTime = System.currentTimeMillis();
			abc = false;
			while(str == 'y' || str == 'Y') {	
				// �ܰ� ���
				if(exp == (10*stage)) {
					step++;
					stage = stage + 1;
					saveTime -= 2;
					cuttingScore += 6 * (5*step) + 10;
	
					startTime = System.currentTimeMillis();
					futureTask = timer.scheduleAtFixedRate(Timer_stage.timer_stage(exp,stage), 3,1,TimeUnit.SECONDS);	//Ÿ�̸� Ŭ�������� �Լ��� �����ٿ� ����ֱ�
				}	
				
				
				//  ====== ���� ���� ======
				a = 2+ rand.nextInt(8);
				b = 2+ rand.nextInt(8);
				c = a * b;
				io = io + 1;
				if(io==1) {
					 futureTask = timer.scheduleAtFixedRate(Timer_stage.timer_stage(exp,stage), 1, 1,TimeUnit.SECONDS);	////Ÿ�̸� Ŭ�������� �Լ��� �����ٿ� ����ֱ�
				}
				clearConsole();
				System.out.println("==========================================");
				System.out.print("[");
				for(int i = 0; i < rest; i++) System.out.print("��");
				for(int i = rest; i < 10; i++) System.out.print("��");
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
				
				if(out == false) {						//�ð� ������ ���Ӿƿ� ��Ű��
					gameOver = true;
		
				}
				
				//  ====== �� �Է� �� ======
				// Ÿ�ӿ��� �˻�
				endTime = System.currentTimeMillis();
				if(endTime - startTime > saveTime*1000 || answer != c) {
					abc=true;									//���� Ʋ���� Ÿ�̸� Ŭ������ Ʋ�ȴٰ� �˷��༭ Ÿ�̸� ���߰��ϱ�
					gameOver = true;
				}
				
				// �� üũ
				if(!gameOver && answer == c) {
					score += 5 * step;
					exp+=1;
					rest=(exp%10);
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

