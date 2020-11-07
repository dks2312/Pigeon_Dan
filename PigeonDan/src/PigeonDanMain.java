import java.util.*;

public class PigeonDanMain {
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
		do {
			int a, b, c, answer = 0, score=0, saveTime = 30, step=1, cuttingScore= 30;
			long startTime=0, endTime=0;
			boolean gameOver = false;
			
			System.out.print("99�� ���� �Ͻðڳ���(y/Y)");
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
				
				System.out.printf("%d * %d= ?  (���� �ð�:%d, �����ð�:%d, �ܰ�:%d, ����:%d)\n", 
						a, b, saveTime, (endTime - startTime), step, score);
				
				
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
				if(endTime - startTime > saveTime*1000) {
					gameOver = true;
				}
				
				// �� üũ
				if(!gameOver && answer == c) {
					score += 5 * step;
				}
				
				
				// ==== ����  ��  =====
				if (gameOver) {
					System.out.println(score + "��");
					
					do {
						System.out.println("�ٽ� �Ͻðڽ��ϱ�?(��: y / �ƴϿ�: n)");
						str = scan.next().charAt(0);
					}while(str != 'y' && str != 'n');
					break;
				}
			}
		}while(str == 'y');
		
		System.out.println("��.");
	}
}

