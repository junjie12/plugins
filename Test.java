package zhong;


import java.util.concurrent.Exchanger;
 
class ExchangerTest extends Thread {
    private Exchanger<String> exchanger;
    private String string;
    private String threadName;
 
    public ExchangerTest(Exchanger<String> exchanger, String string,
            String threadName) {
        this.exchanger = exchanger;
        this.string = string;
        this.threadName = threadName;
    }
 
    public void run() {
        try {
            System.out.println(threadName + ": " + exchanger.exchange(string));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
 
public class Test {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerTest test1 = new ExchangerTest(exchanger, "string4",
                "thread-1");
        ExchangerTest test2 = new ExchangerTest(exchanger, "string2",
                "thread-2");
        ExchangerTest test3 = new ExchangerTest(exchanger, "string3",
                "thread-3");
        test1.start();
        test2.start();
        test3.start();
    }
}
