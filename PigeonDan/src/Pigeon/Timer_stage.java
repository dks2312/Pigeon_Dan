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
	
	static Runnable timer_stage(int exp, int saveTime) {				//Ÿ�̸� �Լ�
		timer = Executors.newSingleThreadScheduledExecutor();
		timeCount = 0;
		Runnable  terminate = new Runnable() {						//Ÿ�̸� ����
			@Override
	        public void run() {
				
//	        	System.out.println("Terminate task executed");         
//	        	System.out.printf("%d\n",saveTime);
//	        	System.out.printf("%d\n",timeCount);	
				timeCount++;
				
				// Ÿ�̸� �ʱ�ȭ? �缳��? �ƹ�ư.. �־�� ��
	        	if(exp != 0  && exp % 10 == 0) {	// ù �ܰ� ����, �ܰ� ���۸��� 
	        		TimeOver();
	    		}
	        	
	        	// main�� Ÿ�� ���� �˻� 
	        	if(timeCount == saveTime) {
//	        		System.out.println("��");
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