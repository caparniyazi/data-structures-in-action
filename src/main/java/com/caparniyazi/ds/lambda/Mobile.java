package com.caparniyazi.ds.lambda;

import lombok.Getter;

// An immutable class.
@Getter
public final class Mobile {
    // Data fields
    final int ram, storage;
    final int battery;
    final int camera;
    final String processor;
    final double screenSize;

    public Mobile(int ram, int storage, int battery, int camera, String processor, double screenSize) {
        this.ram = ram;
        this.storage = storage;
        this.battery = battery;
        this.camera = camera;
        this.processor = processor;
        this.screenSize = screenSize;
    }

    public Mobile(MobileBuilder mobileBuilder) {
        this.ram = mobileBuilder.ram;
        this.storage = mobileBuilder.storage;
        this.battery = mobileBuilder.battery;
        this.camera = mobileBuilder.camera;
        this.processor = mobileBuilder.processor;
        this.screenSize = mobileBuilder.screenSize;
    }

    @Override
    public String toString() {
        return "Mobile [ram=" + ram + ", storage=" + storage + ", battery=" + battery + ", camera=" + camera +
                ", processor=" + processor + ", screenSize=" + screenSize + "]";
    }
}
