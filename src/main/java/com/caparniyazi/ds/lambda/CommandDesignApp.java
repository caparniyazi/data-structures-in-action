package com.caparniyazi.ds.lambda;

public class CommandDesignApp {
    public static void main(String[] args) {
        AC ac1 = new AC();
        AC ac2 = new AC();

        AcAutomationRemote remote = new AcAutomationRemote();
        remote.setCommand(ac2::turnOn);
        remote.buttonPressed();
    }
}
