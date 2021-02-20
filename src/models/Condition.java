package models;

import models.Node;

public class Condition {

    // successor = Nachfolger
    private Node successor;
    private int compareValue;

    public Condition(int value){
        this.compareValue = value;
    }

    private boolean check(int value){
        return compareValue == value;
    }

    public void setSuccessor(Node successor){
        this.successor = successor;
    }
}
