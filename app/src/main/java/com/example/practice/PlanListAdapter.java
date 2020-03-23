package com.example.practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.MyViewHolder>{
    private List<PlanData> mDataset;
    public static View.OnClickListener onClickListener;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView plan_title;
        public TextView plan_author;
        public View rootView;

        public MyViewHolder(View v) {
            super(v);
            plan_title = v.findViewById(R.id.plan_title);
            plan_author = v.findViewById(R.id.plan_author);
            rootView = v;
            v.setClickable(true);
            v.setEnabled(true);
            v.setOnClickListener(onClickListener);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PlanListAdapter(List<PlanData> planData, View.OnClickListener onClick) {
        mDataset = planData;
        onClickListener = onClick;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PlanListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_plan_list, parent, false);

        PlanListAdapter.MyViewHolder vh = new PlanListAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PlanListAdapter.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        PlanData plan = mDataset.get(position);

        holder.plan_title.setText(plan.getTitle());
        holder.plan_author.setText(plan.getAuthor());
        holder.rootView.setTag(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset == null ? 0: mDataset.size();
    }

    public void addPlan(PlanData planData){
        mDataset.add(planData);
        notifyItemInserted(mDataset.size() -1);
    }

    public PlanData getPlan(int positon){
        return mDataset != null ? mDataset.get(positon) : null ;
    }

}
