import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Robot {
    private final boolean DBG = true;

    private enum Foot {
        LEFT,
        RIGHT
    }

    Lock stepLock = new ReentrantLock();
    volatile Foot lastStep = Foot.LEFT;

    private void makeStep(Foot foot) {
        System.out.println("Make step with " + foot + " foot");
        if (DBG) {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void tryMakeStep(Foot foot) {
        stepLock.lock();
        if (lastStep != foot) {
            makeStep(foot);
        }
        lastStep = foot;
        stepLock.unlock();
    }

    public void startWalking() {
        new Thread(() -> {
            while (true) {
                tryMakeStep(Foot.LEFT);
            }
        }).start();
        new Thread(() -> {
            while (true) {
                tryMakeStep(Foot.RIGHT);
            }
        }).start();
    }


    public static void main(String[] args) {
        new Robot().startWalking();
    }
}
