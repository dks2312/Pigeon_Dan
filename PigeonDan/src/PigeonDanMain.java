import java.util.*;

public class PigeonDanMain {
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
		do {
			int a, b, c, answer = 0, score=0, saveTime = 30, step=1, cuttingScore= 30;
			long startTime=0, endTime=0;
			boolean gameOver = false;
			
			System.out.print("99단 도전 하시겠나요(y/Y)");
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
				
				System.out.printf("%d * %d= ?  (제한 시간:%d, 남은시간:%d, 단계:%d, 점수:%d)\n", 
						a, b, saveTime, (endTime - startTime), step, score);
				
				
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
				if(endTime - startTime > saveTime*1000) {
					gameOver = true;
				}
				
				// 답 체크
				if(!gameOver && answer == c) {
					score += 5 * step;
				}
				
				
				// ==== 게임  끝  =====
				if (gameOver) {
					System.out.println(score + "점");
					
					do {
						System.out.println("다시 하시겠습니까?(예: y / 아니요: n)");
						str = scan.next().charAt(0);
					}while(str != 'y' && str != 'n');
					break;
				}
			}
		}while(str == 'y');
		
		System.out.println("끝.");
	}
}

