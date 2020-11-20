package Pigeon;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.TimerTask;


public class Timer_stage {
	
	static int timeCount=0;
	
	static Timer time = new Timer();
	static Timer three_time = new Timer();
	static TimerTask three_second;
	
	static ScheduledExecutorService  timer = Executors.newSingleThreadScheduledExecutor();
	static ScheduledFuture<?> futureTask = null;
	
	static Runnable timer_stage(int exp, int saveTime) {				//타이머 함수
		timer = Executors.newSingleThreadScheduledExecutor();
		timeCount = 0;
		Runnable  terminate = new Runnable() {						//타이머 변수
			@Override
	        public void run() {
				
//	        	System.out.println("Terminate task executed");         
//	        	System.out.printf("%d\n",saveTime);
//	        	System.out.printf("%d\n",timeCount);	
				timeCount++;
				
				// 타이머 초기화? 재설정? 아무튼.. 있어야 함
	        	if(exp != 0  && exp % 10 == 0) {	// 첫 단계 제외, 단계 시작마다 
	        		TimeOver();
	    		}
	        	
	        	// main의 타임 오버 검사 
	        	if(timeCount == saveTime) {
//	        		System.out.println("끝");
	    			PigeonDanGame.gameOver = true;
	    			TimeOver();
	    		}
			}
	    };
	    
        return terminate;
	}
	
	static public void TimeOver() {
		futureTask.cancel(true);
		timer.shutdown();
	}
	
}