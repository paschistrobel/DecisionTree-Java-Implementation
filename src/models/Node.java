package models;
import models.Conditions.Condition;

import java.util.List;

public class Node {
    /* Name of the node
    --> if not a leaf node than it's label is the name of the attribute
    --> else its the result/ class value (in our case survived/ not survived)
    */
    private String label;
    // boolean value if node is a leaf node or not
    private boolean isLeaf;
    // List with all successors of the node
    //private List<Node>
    private List<Condition> conditions;

}
