package com.caparniyazi.ds.rxjava;

public class CallBackApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread is running");
        Runnable r = () -> new CallBackApp().runningAsync(() -> System.out.println("Callback called!"));

        Thread t = new Thread(r);
        t.start();
        Thread.sleep(2000);
        System.out.println("Main thread is completed");
    }

    public void runningAsync(Callback callback) {
        System.out.println("I'm running in a separate thread");
        sleep(1000);
        callback.call();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
