package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

public class ComparisonSettings {
    private int AYS;
    private int AYB;
    private int CSO;
    private int LT;
    private int HBP;
    private int WF;
    private DataHelper db;

    /**
     * Default no-arg constructor for ComparisonSettings
     * This constructor is called when the ComparisonSettingsForm is created
     * Uses an equal-weight value for all comparators as the default
     */
    public ComparisonSettings(Context context){
        db = new DataHelper(context);
        ComparisonSettings settings = db.getComparisonSettings();

        if (settings == null) {
            AYS = 1;
            AYB = 1;
            CSO = 1;
            LT = 1;
            HBP = 1;
            WF = 1;
        } else {
            this.AYS = settings.getAYS();
            this.AYB = settings.getAYB();
            this.CSO = settings.getCSO();
            this.LT = settings.getLT();
            this.HBP = settings.getHBP();
            this.WF = settings.getWF();
        }
    }

    /**
     * Constructor for ComparisonSettings with arguments
     * Used as part of the database call to return a ComparisonSettings object
     * @param salary salary weight
     * @param bonus bonus weight
     * @param stockOptions stock options int weight
     * @param leave leave days weight
     * @param homeBuyingProgram home buying program fund weight
     * @param wellnessFund wellness fund weight
     */
    public ComparisonSettings(int salary, int bonus, int stockOptions, int leave, int homeBuyingProgram, int wellnessFund) {
        this.AYS = salary;
        this.AYB = bonus;
        this.CSO = stockOptions;
        this.LT = leave;
        this.HBP = homeBuyingProgram;
        this.WF = wellnessFund;
    }

    /**
     * UpdateComparisonSettings
     * Takes parameters for the comparison settings and stores them both in the
     * ComparisonSettings object and updates the Database
     *
     * @param salary salary int weight
     * @param bonus bonus int weight
     * @param stockOptions stock options int weight
     * @param leave leave days weight
     * @param homeBuyingProgram home buying program fund weight
     * @param wellnessFund wellness fund weight
     * @return boolean - True if successful, false if not
     */
    public boolean updateComparisonSettings(int salary, int bonus, int stockOptions, int leave,
                                            int homeBuyingProgram, int wellnessFund) {
        boolean success = db.saveComparisonSettings(this);

        if (success){
            this.AYS = salary;
            this.AYB = bonus;
            this.CSO = stockOptions;
            this.LT = leave;
            this.HBP = homeBuyingProgram;
            this.WF = wellnessFund;

            db.saveComparisonSettings(this);
        }

        return success;
    }

    /**
     * getTotalWeight
     * returns the sum of the ComparisonSettings
     * @return int - sum of ComparisonSettings
     */
    public int getTotalWeight(){
        int totalWeight = this.AYS + this.AYB + this.LT + this.CSO + this.HBP + this.WF;

        if (totalWeight == 0){
            return 1;
        }
        return totalWeight;
    }

    //Getters and Setters

    public int getAYS() {
        return AYS;
    }

    public void setAYS(int AYS) {
        this.AYS = AYS;
    }

    public int getAYB() {
        return AYB;
    }

    public void setAYB(int AYB) {
        this.AYB = AYB;
    }

    public int getLT() {
        return LT;
    }

    public void setLT(int LT) {
        this.LT = LT;
    }

    public int getCSO() {
        return CSO;
    }

    public void setCSO(int CSO) {
        this.CSO = CSO;
    }

    public int getHBP() {
        return HBP;
    }

    public void setHBP(int HBP) {
        this.HBP = HBP;
    }

    public int getWF() {
        return WF;
    }

    public void setWF(int WF) {
        this.WF = WF;
    }
}
