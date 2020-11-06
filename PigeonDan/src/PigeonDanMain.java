import java.util.*;

public class PigeonDanMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		Timer time = new Timer();
		TimerTask m_time = new TimerTask() {
			public void run() {
				System.out.print("안영");
			}
		};
		
		
		rand.setSeed(System.currentTimeMillis());
		
		// 1  2 3
		// 5점 10점 15점
		// 30점 30점 + 6문제 + 10점추가
		char str;
		int a, b, c, str1, score=0, saveTime = 10, step=0, Problem= 30;
		long startTime, endTime;
		
		System.out.print("99단 도전 하시겠나요(y/Y)");
		str = scan.next().charAt(0);
		
		while(str == 'y' || str == 'Y') {
			//  ====== 문제 준비 ======
			a = rand.nextInt(8)+1;
			b = rand.nextInt(8)+1;
			c = a * b;
			step++;

			//  ====== 문제 시작 ======
			startTime = System.currentTimeMillis();
			System.out.println(a + "*" + b + "= ?");
			
			
			//  ====== 답 입력 전 ======
			try {	
				str1 = Integer.parseInt(scan.next());
			}
			catch(NumberFormatException e){
				break;
			}
			
			
			//  ====== 답 입력 후 ======
			
			
			
			// 타임오버
			endTime = System.currentTimeMillis();
			if(endTime - startTime > saveTime*1000) {
				break;
			}
			
			// 답오버
			if(str1 == c) {
				score += 10;
			}
			else {
				break;
			}
			
			
			
		}
		System.out.println(score + "점");
	}

}

