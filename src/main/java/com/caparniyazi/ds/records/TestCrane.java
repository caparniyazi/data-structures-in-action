package com.caparniyazi.ds.records;

public class TestCrane {

    public static void main(String[] args) {
        var mommy = new Crane(4, "Cammy");
        System.out.println(mommy.numberEggs());
        System.out.println(mommy.name());
        System.out.println(mommy);

        var friend = new Crane(mommy.numberEggs(), "Ayşe");
        System.out.println(friend);
    }
}
