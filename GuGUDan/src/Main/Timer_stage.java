import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.TimerTask;


public class Timer_stage {
	
	static boolean out = true;
	static int count=0;
	static Timer time = new Timer();
	static int saveTime = 30;
	static int exp = 0;
	static ScheduledExecutorService  timer = Executors.newSingleThreadScheduledExecutor();
	static int stage=1;
	static int rest;
	static boolean gameOver = false;
	static ScheduledFuture<?> futureTask = null;
	public static Timer three_time = new Timer();
	public static TimerTask three_second;
	static Runnable timer_stage(int exp,int stage) {				//Ÿ�̸� �Լ�
		timer = Executors.newSingleThreadScheduledExecutor();
		count=0;
		boolean abc=false;
		Runnable  terminate = new Runnable() {						//Ÿ�̸� ����
        @Override
        public void run() {
        	System.out.printf("%d\n",Main.saveTime);				//�����
        	if(exp % 10 == 0 && stage != 1) {
    			futureTask.cancel(true);
    			timer.shutdown();
    		}
        	
        	System.out.println("Terminate task executed");          //�����
            count = count + 1;
            System.out.printf("%d\n",count);						//�����
    		if(count == Main.saveTime) {
    			System.out.printf("%d\n",count);				    //�����
    			out = false;
    			futureTask.cancel(true);
    			timer.shutdown();
    		}
    		if(Main.abc==true) {
    			futureTask.cancel(true);
    			timer.shutdown();
    		}
    		}
        };
        return terminate;
	}
	
}