package com.caparniyazi.ds.lambda;

import lombok.Setter;

// The Invoker
@Setter
public class AcAutomationRemote {
    Command command;

    public void buttonPressed() {
        command.execute();
    }
}
