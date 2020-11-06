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
		
		Ranking rank = new Ranking();
		
		rand.setSeed(System.currentTimeMillis());
		
		// 1  2 3
		// 5점 10점 15점
		// 30점 30점 + 6문제 + 10점추가
		char str;
		int a, b, c, answer = 0, score=0, saveTime = 10, step=1, cuttingScore= 30;
		long startTime, endTime;
		boolean gameOver = false;
		String overText="";
		
		System.out.print("99단 도전 하시겠나요(y/Y)");
		str = scan.next().charAt(0);
		
		startTime = endTime = System.currentTimeMillis();
		
		while(str == 'y' || str == 'Y') {
			//  ====== 문제 준비 ======
			
			// 단계 상승
			if(score >= cuttingScore) {
				step++;
				saveTime -= 2;
				cuttingScore += 6 * (5*step) + 10;
				startTime = System.currentTimeMillis();
			}
			
			//  ====== 문제 시작 ======
			a = rand.nextInt(8)+1;
			b = rand.nextInt(8)+1;
			c = a * b;
			
			System.out.printf("%d * %d= ?  (제한 시간:%d, 남은시간:%d, 단계:%d, 점수:%d)\n", 
					a, b, saveTime, endTime - startTime, step, score);
			
			
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
			
			// 타임오버
			endTime = System.currentTimeMillis();
			if(endTime - startTime > saveTime*1000) {
				gameOver = true;
				overText += "제한 시간이 다 되었습니다\n";
				break;
			}
			
			// 답오버
			if(answer == c) {
				score += 5 * step;
			}
			else {
				gameOver = true;
				overText += "문제의 답이 틀렸습니다\n";
				break;
			}
		}
		System.out.println(score + "점");
		
		// 게임 오버
		if (gameOver) {
			System.out.println(overText);

			//rank.ranking(score);
			
			System.out.println("다시 하시겠습니까?(예: y / 아니요: n)");
			str = scan.next().charAt(0);
			if(str == 'n') {
				return;
			}
		}
	}
}

