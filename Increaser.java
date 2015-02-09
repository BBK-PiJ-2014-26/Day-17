public class Increaser implements Runnable {
	private Counter c;

	public Increaser(Counter counter) {
		this.c = counter;
	}

	public static void main(String args[]) {
		for (int i = 0; i < 100; i++) {
			Counter counter = new Counter();
			Increaser increaserTask = new Increaser(counter);
			Thread t = new Thread(increaserTask);
			t.start();
		}
	}

	public void run() {
		System.out.println("Starting at " + c.getCount());
		for (int i = 0; i < 1000; i++) {
			c.increase();
		}
		System.out.println("Stopping at " + c.getCount());
	}
}
