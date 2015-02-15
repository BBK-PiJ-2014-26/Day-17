import java.util.Scanner;

/**
 * Answer to question 4, Day-17.
 */

public class ResponsiveUI implements Runnable {
	private Task myTask;
	private static String taskMonitor = "";

	public ResponsiveUI(Task myTask) {
		this.myTask = myTask;
	}

	@Override
	public synchronized void run() {
		try {
			Thread.sleep(myTask.getTaskDuration());
			addToTaskMonitor();
		} catch (InterruptedException ex) {}
	}

	public synchronized void addToTaskMonitor() {
		if(taskMonitor.isEmpty()) {
			taskMonitor = myTask.getTaskCount();
		} else {
			taskMonitor = taskMonitor + ", " + myTask.getTaskCount();
		}
	}

	public static void printFinishedTasks() {
		System.out.println("Finished tasks: " + taskMonitor);
		taskMonitor = "";
	}

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			System.out.print("Enter the duration (in ms) of task " + i + ": ");
			Scanner sc = new Scanner(System.in);
			int taskDuration = sc.nextInt();
			Task myTask = new Task(taskDuration, i);
			Runnable r = new ResponsiveUI(myTask);
			Thread t = new Thread(r);
			t.start();
			if(!ResponsiveUI.taskMonitor.isEmpty()) {
				printFinishedTasks();
			}
		}
	}
}