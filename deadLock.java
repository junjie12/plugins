package zhong;

public class deadLock implements Runnable{
boolean flag = true;
static Object o1 = new Object();
static Object o2 = new Object();
public deadLock(boolean flag) {
	this.flag = flag;
}
public void run() {
	if(flag) {
	synchronized (o1) {
		System.out.println("我需要o2!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (o2) {
			System.out.println("我得到o2了!");
		}
	}
	}else {
		synchronized (o2) {
			System.out.println("我需要o1!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (o1) {
				System.out.println("我得到o1了!");
			}
		}
	}
}
public static void main(String[]args) {
	new Thread(new deadLock(true)).start();
	new Thread(new deadLock(false)).start();
}
}
