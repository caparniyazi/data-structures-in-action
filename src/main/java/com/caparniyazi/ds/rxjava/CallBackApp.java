package com.caparniyazi.ds.rxjava;

public class CallBackApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread is running");
        Runnable r = () -> new CallBackApp().runningAsync(new Callback() {
            @Override
            public void pushData(String data) {
                System.out.println("Callback data: " + data);
            }

            @Override
            public void pushComplete() {
                System.out.println("Callback completed");
            }

            @Override
            public void pushError(Exception ex) {
                System.out.println("Callback error: " + ex);
            }
        });

        Thread t = new Thread(r);
        t.start();
        Thread.sleep(2000);
        System.out.println("Main thread is completed");
    }

    public void runningAsync(Callback callback) {
        System.out.println("I'm running in a separate thread");
        sleep(1000);
        callback.pushData("Data1");
        callback.pushData("Data2");
        callback.pushData("Data3");
        callback.pushError(new RuntimeException("Error"));
        callback.pushComplete();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
