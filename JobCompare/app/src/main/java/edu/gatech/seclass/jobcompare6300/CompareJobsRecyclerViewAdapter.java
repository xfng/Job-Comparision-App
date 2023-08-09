package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CompareJobsRecyclerViewAdapter extends RecyclerView.Adapter<CompareJobsRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<String[]> jobDetails;

    public CompareJobsRecyclerViewAdapter(Context context, ArrayList<String[]> jobDetails){
        this.context = context;
        this.jobDetails = jobDetails;
    }

    @NonNull
    @Override
    public CompareJobsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where we inflate the layout and give a look to our rows

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.job_display_row, parent, false);

        return new CompareJobsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompareJobsRecyclerViewAdapter.MyViewHolder holder, int position) {
        // Assign values to each of the rows as they come back ont he screen
        // Based on the position of the recycler view

        holder.tvIndex.setText(jobDetails.get(position)[0]);
        holder.tvJobTitle.setText(jobDetails.get(position)[1]);
        holder.tvCompany.setText(jobDetails.get(position)[2]);
        holder.tvScore.setText(jobDetails.get(position)[3]);
    }

    @Override
    public int getItemCount() {
        // The recycler just wants to know how many items you want displayed

        return jobDetails.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // Grab the views from oru recycler_view_row layout file
        // Kind of like we're doing in the onCreate method

        TextView tvIndex, tvCompany, tvJobTitle, tvScore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIndex = itemView.findViewById(R.id.JobIDTV);
            tvCompany = itemView.findViewById(R.id.CompanyTV);
            tvJobTitle = itemView.findViewById(R.id.JobTitleTV);
            tvScore = itemView.findViewById(R.id.ScoreTV);

        }
    }
}
