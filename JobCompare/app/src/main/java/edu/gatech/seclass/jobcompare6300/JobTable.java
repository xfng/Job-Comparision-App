package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JobTable {

    private JobDetails job1;
    private JobDetails job2;

    public JobTable(JobDetails job1, JobDetails job2){
        this.job1 = job1;
        this.job2 = job2;
    }
    public void setJob1(JobDetails job1) {
            this.job1 = job1;
    }

    public void setJob2(JobDetails job2) {
            this.job2 = job2;
    }

    public JobDetails getJob1() {
        return job1;
    }

    public JobDetails getJob2() {
        return job2;
    }
}