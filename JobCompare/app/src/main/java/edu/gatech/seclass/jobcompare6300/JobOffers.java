package edu.gatech.seclass.jobcompare6300;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;


import java.util.ArrayList;
import java.util.Collections;

public class JobOffers {

    private ArrayList<JobDetails> jobOffers;


    private DataHelper db;

    /**
     * No arg constructor for the JobOffers object
     */
    public JobOffers(DataHelper db){
        this.db = db;
        jobOffers =  db.getJobOffersList();
    }

    /**
     * getJobOffers
     * @return the job offers list
     */
    public ArrayList<JobDetails> getJobOffers() {
        return this.jobOffers;
    }

    /**
     * addJobOffer
     *
     * Public method to add a new JobDetail to the JobOffers ArrayList
     * @param jobDetail New job details being added
     * @return true if added, false if any error occurs
     */
    public boolean addJobOffer(JobDetails jobDetail){
        boolean success = false;
        if (!jobOffers.contains(jobDetail)){
            success = db.saveJobOffers(jobDetail);
            if(success) {
                jobOffers.add(jobDetail);
            }
        }
        return success;
    }

    /**
     * removeJobOffer
     *
     * Public method to remove a JobDetail from the JobOffers ArrayList
     * @param jobOffer Job offer to remove
     * @return true if removed, false if the JobDetail is not in the ArrayList
     */
    public boolean removeJobOffer(JobDetails jobOffer){
        if(jobOffers.contains(jobOffer)){
            jobOffers.remove(jobOffer);
            return true;
        }
        return false;
    }

    public ArrayList<JobDetails> getRankedList(Context context) {

        ArrayList<JobDetails> jobDetailsArrayList = new ArrayList<JobDetails>();
        jobDetailsArrayList.addAll(db.getJobOffersList());
        ComparisonSettings compSettings = new ComparisonSettings(context);
        CurrentJobDetails currentJobDetails = new CurrentJobDetails(db);
        JobDetails currentJob = currentJobDetails.getCurrentJobDetails();

        // TODO: Delete the dummy data after other functionality added
        // Adding dummy JobDetails to the list for testing
//        jobDetailsArrayList.add(new JobDetails("Engineer", "Google", "CA", "San Francisco", 1, 2, 3, 4, 5, 6));
//        jobDetailsArrayList.add(new JobDetails("DevOps", "Apple", "TX", "Austin", 10, 11, 12, 13, 14, 15));
//        jobDetailsArrayList.add(new JobDetails("Title", "company", "State", "City", 100000, 50000, 1000, 35, 25000, 10000));
//        jobDetailsArrayList.add(new JobDetails("Engineer", "Google", "CA", "San Francisco", 1, 2, 3, 4, 5, 6));
//        jobDetailsArrayList.add(new JobDetails("DevOps", "Apple", "TX", "Austin", 10, 11, 12, 13, 14, 15));
//        jobDetailsArrayList.add(new JobDetails("Title", "company", "State", "City", 100000, 50000, 1000, 35, 25000, 10000));
//        jobDetailsArrayList.add(new JobDetails("Engineer", "Google", "CA", "San Francisco", 1, 2, 3, 4, 5, 6));
//        jobDetailsArrayList.add(new JobDetails("DevOps", "Apple", "TX", "Austin", 10, 11, 12, 13, 14, 15));
//        jobDetailsArrayList.add(new JobDetails("Title", "company", "State", "City", 100000, 50000, 1000, 35, 25000, 10000));
//        jobDetailsArrayList.add(new JobDetails("Engineer", "Google", "CA", "San Francisco", 1, 2, 3, 4, 5, 6));
//        jobDetailsArrayList.add(new JobDetails("DevOps", "Apple", "TX", "Austin", 10, 11, 12, 13, 14, 15));
//        jobDetailsArrayList.add(new JobDetails("Title", "company", "State", "City", 100000, 50000, 1000, 35, 25000, 10000));
//        jobDetailsArrayList.add(new JobDetails("Engineer", "Google", "CA", "San Francisco", 1, 2, 3, 4, 5, 6));
//        jobDetailsArrayList.add(new JobDetails("DevOps", "Apple", "TX", "Austin", 10, 11, 12, 13, 14, 15));
//        jobDetailsArrayList.add(new JobDetails("Title", "company", "State", "City", 100000, 50000, 1000, 35, 25000, 10000));
//        jobDetailsArrayList.add(new JobDetails("Engineer", "Google", "CA", "San Francisco", 1, 2, 3, 4, 5, 6));
//        jobDetailsArrayList.add(new JobDetails("DevOps", "Apple", "TX", "Austin", 10, 11, 12, 13, 14, 15));
//        jobDetailsArrayList.add(new JobDetails("Title", "company", "State", "City", 100000, 50000, 1000, 35, 25000, 10000));

        // Build a new arraylist for sorting
        ArrayList<JobDetails> rankedJobList = new ArrayList<JobDetails>(jobDetailsArrayList);
        if (currentJob != null) {
            rankedJobList.add(currentJob);
        }

        // re-calculate all job Ratings
        for (JobDetails job : rankedJobList) {
            double rating = job.calculateRating(compSettings);

        }

        // Sort using the compareTo method of JobDetails
        Collections.sort(rankedJobList);

        return rankedJobList;
    }

    public ArrayList<String[]> getJobDetailArray(ArrayList<JobDetails> rankedJobList, Context context){
        // Cycle through the rankedJobList to get the relevant data
        // Add data to an Array List to pass to the RecyclerView
        ArrayList<String[]> detailArray = new ArrayList<String[]>();
        int pos = 0;

        JobDetails currentJob = new CurrentJobDetails(db).getCurrentJobDetails();
        ComparisonSettings cs = new ComparisonSettings(context);

        if (currentJob != null) {
            currentJob.calculateRating(cs);
        }

        for (JobDetails job : rankedJobList){

            // Extract necessary info for building the rankedJobsList
            int position = pos;
            String company = job.getCompany();
            String title = job.getTitle();
            String isCurrent;

            // Logic to check against the CurrentJob
            // Adding more more parameters may result in a more accurate and secure validation
            if(currentJob != null && job.getCompany().equals(currentJob.getCompany()) && job.getTitle().equals(currentJob.getTitle()) &&
                job.getRating() == currentJob.getRating() && job.getYearlySalary() == currentJob.getYearlySalary()){
                isCurrent = "Yes";
            }else{
                isCurrent = "No";
            }

            // Store the Job's ranking in the Job Object for reference on the compare 2 Jobs screen
            job.setRanking(position);
            pos++;

            // Instantiate a new String Array, add relevant components to STR array
            String[] str = new String[4];
            str[0] = Integer.toString(position);
            str[1] = title;
            str[2] = company;
            str[3] = isCurrent;
            detailArray.add(str);
        }

        return detailArray;

    }

}