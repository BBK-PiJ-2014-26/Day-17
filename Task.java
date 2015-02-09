/**
 * Parts of the answer to q4 on Day-17.
 */

public class Task {
	private final int taskDuration;
	private final int taskCount;

	public Task(int taskDuration, int taskCount) {
		this.taskDuration = taskDuration;
		this.taskCount = taskCount;
	}

	public int getTaskDuration() {
		return taskDuration;
	}

	public String getTaskCount() {
		String result = Integer.toString(taskCount);
		return result;
	}
}