package skyfly33.sarojaba;

import com.sarojaba.stopwatch.Stopwatch;

public class StopwatchTest {

	static Runnable tooSlowOperation = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	static long delay = Stopwatch.task(tooSlowOperation).mili().time();

	public static void main(String[] args) {
		System.out.println("time : " + delay);
	}
}
