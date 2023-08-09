package edu.gatech.seclass.jobcompare6300;



public class CurrentJobDetails {

    private JobDetails currentJobDetails;
    private DataHelper db;

    /**
     * CurrentJobDetails constructor.
     * This no-arg constructor assigns default values to the jobDetails object attributes and instantiates
     * a default JobDetails object
     */
    public CurrentJobDetails(DataHelper db) {
        this.db = db;
        JobDetails jobDetails = db.getCurrentJob();

        if (jobDetails != null) {
            this.currentJobDetails = jobDetails;
        }
    }

    /**
     * getCurrentJobDetails
     * @return jobDetails - The current job details object
     */
    public JobDetails getCurrentJobDetails() {
        return this.currentJobDetails;
    }

    /**
     * updateCurrentDetails
     * @param jobDetails - Takes a JobDetails object as an argument
     * @return void
     */
    public boolean updateCurrentDetails(JobDetails jobDetails){
        boolean successfullySaved = db.saveCurrentJob(jobDetails);

        if (successfullySaved) {
            this.currentJobDetails = jobDetails;
        }

        return successfullySaved;
    }
}
