import java.util.*;

public class PigeonDanMain {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		Timer time = new Timer();
		TimerTask m_time = new TimerTask() {
			public void run() {
				System.out.print("�ȿ�");
			}
		};
		
		Ranking rank = new Ranking();
		
		rand.setSeed(System.currentTimeMillis());
		
		// 1  2 3
		// 5�� 10�� 15��
		// 30�� 30�� + 6���� + 10���߰�
		char str;
		int a, b, c, answer = 0, score=0, saveTime = 10, step=1, cuttingScore= 30;
		long startTime, endTime;
		boolean gameOver = false;
		String overText="";
		
		System.out.print("99�� ���� �Ͻðڳ���(y/Y)");
		str = scan.next().charAt(0);
		
		startTime = endTime = System.currentTimeMillis();
		
		while(str == 'y' || str == 'Y') {
			//  ====== ���� �غ� ======
			
			// �ܰ� ���
			if(score >= cuttingScore) {
				step++;
				saveTime -= 2;
				cuttingScore += 6 * (5*step) + 10;
				startTime = System.currentTimeMillis();
			}
			
			//  ====== ���� ���� ======
			a = rand.nextInt(8)+1;
			b = rand.nextInt(8)+1;
			c = a * b;
			
			System.out.printf("%d * %d= ?  (���� �ð�:%d, �����ð�:%d, �ܰ�:%d, ����:%d)\n", 
					a, b, saveTime, endTime - startTime, step, score);
			
			
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
			
			// Ÿ�ӿ���
			endTime = System.currentTimeMillis();
			if(endTime - startTime > saveTime*1000) {
				gameOver = true;
				overText += "���� �ð��� �� �Ǿ����ϴ�\n";
				break;
			}
			
			// �����
			if(answer == c) {
				score += 5 * step;
			}
			else {
				gameOver = true;
				overText += "������ ���� Ʋ�Ƚ��ϴ�\n";
				break;
			}
		}
		System.out.println(score + "��");
		
		// ���� ����
		if (gameOver) {
			System.out.println(overText);

			//rank.ranking(score);
			
			System.out.println("�ٽ� �Ͻðڽ��ϱ�?(��: y / �ƴϿ�: n)");
			str = scan.next().charAt(0);
			if(str == 'n') {
				return;
			}
		}
	}
}

