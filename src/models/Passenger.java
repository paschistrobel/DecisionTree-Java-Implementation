package models;

public class Passenger {
    /**unique Passenger ID*/
    private int passengerID;

    /**
     0 = not survived
     1 = survived
    */
    private int survived;

    /**
     Price Class, categories:
     1 = 1
     2 = 2
     3 = 3
    */
    private int pClass;

    /**
    Title (extracted from name Attribute!):
     0 = Mr
     1 = Master
     2 = Mrs, Mme
     3 = Miss, Mlle, Ms
     4 = Other (Lady, Countess, Capt, Col, Don, Dr, Major, Rev, Sir, Jonkheer, Dona)
    */
    private int title;

    /**
     0 = male
     1 = female
    */
    private int sex;

    /** age organized in age groups
     0 = 0-5 years (inclusive)
     1 = 6-17 years (inclusive)
     2 = 18-29 years (inclusive)
     3 = 30-49 years (inclusive)
     4 = 50-64 years (inclusive)
     5 = >=65 years
    */
    private int ageGroup;

    /**
     Number of siblings/ spouses on board
     0 = no siblings/ spouses
     1 = 1 sibling/ spouse
     2 = >1 siblings/ spouses
    */
    private int sibSp;

    /**
     Number of parents/ children on board
     0 = no parent/ child
     1 = 1 parent/ child
     2 = >1 parents/ children
    */
    private int parch;

    /**
     Number of siblings/ spouses on board
     0 = no siblings/ spouses
     1 = 1 sibling/ spouse
     2 = >1 sibling/ spouse
    */
    private int fare;

    /**
     Embarked (= Zustiegs-ort)
     0 = C
     1 = Q
     2 = S
     --> missing values are assigned with most frequent value
    */
    private int embarked;

    /**
    Constructor
    */
    public Passenger(String id, String survived, String pClass, String name, String sex, String age, String sibSp, String parch, String fare, String embarked){
        this.passengerID = Integer.valueOf(id);
        this.survived = survived_2_category(survived);
        this.pClass = pClass_2_category(pClass);
        this.title = name_2_category(name);
        this.sex = sex_2_category(sex);
        this.ageGroup = age_2_category(age);
        this.sibSp = sibSp_2_category(sibSp);
        this.parch = parch_2_category(parch);
        this.fare = fare_2_category(fare);
        this.embarked = embarked_2_category(embarked);
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
    public int getPassengerID() {
        return passengerID;
    }

    public int getSurvived() {
        return survived;
    }

    public int getpClass() {
        return pClass;
    }

    public int getTitle() {
        return title;
    }

    public int getSex() {
        return sex;
    }

    public int getSibSp() {
        return sibSp;
    }

    public int getParch() {
        return parch;
    }

    public int getFare() {
        return fare;
    }

    public int getEmbarked() {
        return embarked;
    }
}
