package models.Conditions;

import models.Node;

public abstract class Condition {
    Node successor;

    public Condition(Node successor){
        this.successor = successor;
    }

    private void test(String attribute){
        if (attribute.equals("")) {

        }
    }
}
