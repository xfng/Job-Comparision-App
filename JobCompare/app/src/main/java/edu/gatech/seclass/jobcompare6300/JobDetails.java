package edu.gatech.seclass.jobcompare6300;

import kotlinx.coroutines.Job;

public class JobDetails implements Comparable<JobDetails> {

    private String title;
    private String company;
    private String city;
    private String state;
    private int yearlySalary;
    private int yearlyBonus;
    private int leaveTime;
    private int numStocks;
    private int homeBuyingFund;
    private int wellnessFund;
    private double rating;
    private int ranking = 9999;


    public JobDetails(String title, String company, String city,String state,  int yearlySalary,
                      int yearlyBonus,  int leaveTime, int numStocks,int homeBuyingFund, int wellnessFund) {
        this.title = title;
        this.company = company;
        this.city = city;
        this.state = state;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.leaveTime = leaveTime;
        this.numStocks = numStocks;
        this.homeBuyingFund = homeBuyingFund;
        this.wellnessFund = wellnessFund;
    }

    public double calculateRating(ComparisonSettings compSettings){
        if (compSettings == null) {
            compSettings = new ComparisonSettings(1,1,1,1,1,1);
        }

        double totalWeight = (double) compSettings.getTotalWeight();
        double salWeight = compSettings.getAYS() / totalWeight;
        double bonusWeight = compSettings.getAYB() / totalWeight;
        double stockWeight = compSettings.getCSO() / totalWeight;
        double leaveWeight = compSettings.getLT() / totalWeight;
        double homeWeight = compSettings.getHBP() / totalWeight;
        double wellWeight = compSettings.getWF() / totalWeight;

        double score = salWeight * this.yearlySalary + bonusWeight * this.yearlyBonus + stockWeight * this.numStocks +
                leaveWeight * this.leaveTime + homeWeight * this.homeBuyingFund + wellWeight * this.wellnessFund;

        this.rating = Math.round(score * 10.0)/10.0;
        return rating;
    }

    @Override
    public String toString() {
        return "JobDetails{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", yearlySalary=" + yearlySalary +
                ", yearlyBonus=" + yearlyBonus +
                ", leaveTime=" + leaveTime +
                ", numStocks=" + numStocks +
                ", homeBuyingFund=" + homeBuyingFund +
                ", wellnessFund=" + wellnessFund +
                '}';
    }

    @Override
    public int compareTo(JobDetails compareJob) {
        double compareRating = compareJob.getRating();
        if((this.rating - compareRating) < 0){
            return 1;
        }else if (this.rating == compareRating){
            return 0;
        }else{
            return -1;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(int yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public int getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(int yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getNumStocks() {
        return numStocks;
    }

    public void setNumStocks(int numStocks) {
        this.numStocks = numStocks;
    }

    public int getHomeBuyingFund() {
        return homeBuyingFund;
    }

    public void setHomeBuyingFund(int homeBuyingFund) {
        this.homeBuyingFund = homeBuyingFund;
    }

    public int getWellnessFund() {
        return wellnessFund;
    }

    public void setWellnessFund(int wellnessFund) {
        this.wellnessFund = wellnessFund;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = Math.round(rating*10.0)/10.0;
    }

    public int getRanking(){
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}


