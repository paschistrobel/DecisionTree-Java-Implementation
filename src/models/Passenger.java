package models;

import java.util.HashMap;
import java.util.Map;

public class Passenger {

    /**unique Passenger ID*/
    private int id;
    /**Map with all attributes*/
    private Map<String, Integer> attributes;

    /**
    Constructor
    */
    public Passenger(String id, String survived, String pClass, String name, String sex, String age, String sibSp, String parch, String fare, String embarked){
        this.id = Integer.parseInt(id);
        attributes = new HashMap<>();
        /**
         0 = not survived
         1 = survived
         */
        attributes.put(Attribute.SURVIVED, survived_2_category(survived));
        /**
         Price Class, categories:
         1 = 1
         2 = 2
         3 = 3
         */
        attributes.put(Attribute.PCLASS, pClass_2_category(pClass));
        /**
         Title (extracted from name models.Attribute!):
         0 = Mr
         1 = Master
         2 = Mrs, Mme
         3 = Miss, Mlle, Ms
         4 = Other (Lady, Countess, Capt, Col, Don, Dr, Major, Rev, Sir, Jonkheer, Dona)
         */
        attributes.put(Attribute.TITLE, name_2_category(name));
        /**
         0 = male
         1 = female
         */
        attributes.put(Attribute.SEX, sex_2_category(sex));
        /** age organized in age groups
         0 = 0-5 years (inclusive)
         1 = 6-17 years (inclusive)
         2 = 18-29 years (inclusive)
         3 = 30-49 years (inclusive)
         4 = 50-64 years (inclusive)
         5 = >=65 years
         */
        attributes.put(Attribute.AGEGROUP, age_2_category(age));
        /**
         Number of siblings/ spouses on board
         0 = no siblings/ spouses
         1 = 1 sibling/ spouse
         2 = >1 siblings/ spouses
         */
        attributes.put(Attribute.SIBSP, sibSp_2_category(sibSp));
        /**
         Number of parents/ children on board
         0 = no parent/ child
         1 = 1 parent/ child
         2 = >1 parents/ children
         */
        attributes.put(Attribute.PARCH, parch_2_category(parch));
        /**
         Number of siblings/ spouses on board
         0 = no siblings/ spouses
         1 = 1 sibling/ spouse
         2 = >1 sibling/ spouse
         */
        attributes.put(Attribute.FARE, fare_2_category(fare));
        /**
         Embarked (= Zustiegs-ort)
         0 = C
         1 = Q
         2 = S
         --> missing values are assigned with most frequent value
         */
        attributes.put(Attribute.EMBARKED, embarked_2_category(embarked));
    }

    /**
    Conversion methods
    */
    private int survived_2_category(String survived){
        return 0;
    }
    private int pClass_2_category(String pClass){
        return 0;
    }
    private int name_2_category(String name){
        return 0;
    }
    private int sex_2_category(String sex){
        return 0;
    }
    private int age_2_category(String age){
        return 0;
    }
    private int sibSp_2_category(String sibSp){
        return 0;
    }
    private int parch_2_category(String parch){
        return 0;
    }
    private int fare_2_category(String fare){
        return 0;
    }
    private int embarked_2_category(String embarked){
        return 0;
    }


    /**
    Getter methods
     */
    public int getAttributeValue(String attribute){
        return attributes.get(attribute);
    }
    public int getID() {
        return this.id;
    }
    public int getSurvived() {
        return attributes.get(Attribute.SURVIVED);
    }
    public int getpClass() {
        return attributes.get(Attribute.PCLASS);
    }
    public int getTitle() {
        return attributes.get(Attribute.TITLE);
    }
    public int getSex() {
        return attributes.get(Attribute.SEX);
    }
    public int getSibSp() {
        return attributes.get(Attribute.SIBSP);
    }
    public int getParch() {
        return attributes.get(Attribute.PARCH);
    }
    public int getFare() {
        return attributes.get(Attribute.FARE);
    }
    public int getEmbarked() {
        return attributes.get(Attribute.EMBARKED);
    }
}
