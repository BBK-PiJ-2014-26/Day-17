import java.util.Scanner;

/**
 * Answer to question 4, Day-17.
 */

public class ResponsiveUI implements Runnable {
	private int taskCount;
	private Task myTask;
	private static String taskMonitor = "";

	public ResponsiveUI(int taskCount) {
		this.taskCount = taskCount;
		this.myTask = null;
	}

	@Override
	public synchronized void run() {
		try {
			System.out.print("Enter the duration (in ms) of task " + taskCount + ": ");
			Scanner sc = new Scanner(System.in);
			int taskDuration = sc.nextInt();
			myTask = new Task(taskDuration, taskCount);
			doTask();
			while(taskMonitor.isEmpty()) {
				wait();
			}
			printFinishedTasks();
		} catch (InterruptedException ex) {}
	}

	public void doTask() {
		try {
			Thread.sleep(myTask.getTaskDuration());
			addToTaskMonitor();
		} catch (InterruptedException ex) {}
	}

	public synchronized void addToTaskMonitor() {
		if(taskMonitor.isEmpty()) {
			taskMonitor = myTask.getTaskCount();
			notifyAll();
		} else {
			taskMonitor = taskMonitor + ", " + myTask.getTaskCount();
			notifyAll();
		}
	}

	public synchronized void printFinishedTasks() {
		System.out.println("Finished tasks: " + taskMonitor);
	}

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			Runnable r = new ResponsiveUI(i);
			Thread t = new Thread(r);
			t.start();
		}
	}
}