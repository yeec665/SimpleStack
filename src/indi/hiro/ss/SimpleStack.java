package indi.hiro.ss;

import java.util.EmptyStackException;
import java.util.Objects;

public class SimpleStack<T> implements Runnable {

    private final Object lock = new Object();

    private volatile T transfer;

    public SimpleStack() {
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                store();
            }
        } catch (InterruptedException ignored) {}
        throw new EmptyStackException();
    }

    private void store() throws InterruptedException {
        while (true) {
            lock.notify();
            lock.wait();
            if (transfer != null) {
                T item = transfer;
                store();
                transfer = item;
            } else {
                break;
            }
        }
    }

    private T interact(T t) {
        synchronized (lock) {
            transfer = t;
            lock.notify();
            try {
                lock.wait();
            } catch (InterruptedException ignored) {}
            return transfer;
        }
    }

    public void push(T t) {
        Objects.requireNonNull(t);
        interact(t);
    }

    public T pop() {
        return interact(null);
    }
}
