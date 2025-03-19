package com.caparniyazi.ds.lambda;

public class TreeFunApp {
    public static void main(String[] args) {
        TreeFun<Integer> myTree = TreeFun.of(23, 5, 76, 10, 3, 45);
        System.out.println(myTree);
        myTree = myTree.remove(10);
        System.out.println(myTree);

        System.out.println(myTree.contains(45));
        System.out.println(myTree.max());
    }
}
