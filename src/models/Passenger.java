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
        this.passengerID = Integer.parseInt(id); // parseInt statt valueOf, weil valueOf ein Integer Objekt zurückgibt
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
        return Integer.parseInt(survived);
    }

    private int pClass_2_category(String pClass){
        return Integer.parseInt(pClass);
    }

    private int name_2_category(String name){
        if (name.contains("Mr.")) return 0;
        if (name.contains("Master.")) return 1;
        if (name.contains("Mrs.") || name.contains("Mme.")) return 2;
        if (name.contains("Miss.") || name.contains("Mlle.") || name.contains("Ms.")) return 3;
        else return 4;
    }

    private int sex_2_category(String sex){
        if (sex.equals("male")) {
            return 0;
        }
        /*if (sex.equals("female")) {
            return 1;
        }*/
        else return 1;
    }

    private int age_2_category(String age){
        int ageNumber;
        if (age != null) {
             ageNumber = Integer.parseInt(age);
        } else {
            return 6;
        }

        if (ageNumber <= 5) return 0;
        if (ageNumber <= 17) return 1;
        if (ageNumber <= 29) return 2;
        if (ageNumber <= 49) return 3;
        if (ageNumber <= 64) return 4;
        if (ageNumber > 65) return 5;
        return 6;
    }

    private int sibSp_2_category(String sibSp){
        int number = Integer.parseInt(sibSp);
        if (number > 1) return 2;
        return number;
    }

    private int parch_2_category(String parch){
        int number = Integer.parseInt(parch);
        if (number > 1) return 2;
        return number;
    }

    private int fare_2_category(String fare){
        double number = Double.parseDouble(fare);
        if (number <= 7.91) return 0;
        if (number <= 14.454) return 1;
        if (number <= 31) return 2;
        else return 3;
    }

    private int embarked_2_category(String embarked){
        switch (embarked) {
            case "C": return 0;
            case "Q": return 1;
            case "S": return 2;
            default: return 2;
        }
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
