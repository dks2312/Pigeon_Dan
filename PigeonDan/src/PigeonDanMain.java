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
		
		
		rand.setSeed(System.currentTimeMillis());
		
		// 1  2 3
		// 5�� 10�� 15��
		// 30�� 30�� + 6���� + 10���߰�
		char str;
		int a, b, c, str1, score=0, saveTime = 10, step=0, Problem= 30;
		long startTime, endTime;
		
		System.out.print("99�� ���� �Ͻðڳ���(y/Y)");
		str = scan.next().charAt(0);
		
		while(str == 'y' || str == 'Y') {
			//  ====== ���� �غ� ======
			a = rand.nextInt(8)+1;
			b = rand.nextInt(8)+1;
			c = a * b;
			step++;

			//  ====== ���� ���� ======
			startTime = System.currentTimeMillis();
			System.out.println(a + "*" + b + "= ?");
			
			
			//  ====== �� �Է� �� ======
			try {	
				str1 = Integer.parseInt(scan.next());
			}
			catch(NumberFormatException e){
				break;
			}
			
			
			//  ====== �� �Է� �� ======
			
			
			
			// Ÿ�ӿ���
			endTime = System.currentTimeMillis();
			if(endTime - startTime > saveTime*1000) {
				break;
			}
			
			// �����
			if(str1 == c) {
				score += 10;
			}
			else {
				break;
			}
			
			
			
		}
		System.out.println(score + "��");
	}

}

