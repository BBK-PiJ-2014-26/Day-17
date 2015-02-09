import java.util.Scanner;

/**
 * Answer to question 4, Day-17.
 */

public class ResponsiveUI implements Runnable {
	private final int taskDuration;
	private final int taskCount;

	public ResponsiveUI(int taskDuration, int taskCount) {
		this.taskDuration = taskDuration;
		this.taskCount = taskCount;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(taskDuration);
			System.out.println("Finished task: " + taskCount);
		} catch (InterruptedException ex) {
		}
	}

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			System.out.print("Enter the duration (in ms) of task " + i + ": ");
			Scanner sc = new Scanner(System.in);
			int taskDuration = sc.nextInt();
			Runnable r = new ResponsiveUI(taskDuration, i);
			Thread t = new Thread(r);
			t.start();
		}
	}
}