package com.androidtutorialshub.loginregister.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Attendance;
import com.androidtutorialshub.loginregister.model.Event;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class AttendancesRecyclerAdapter extends RecyclerView.Adapter<AttendancesRecyclerAdapter.AttendanceViewHolder> {

    private List<Attendance> listAttendances;

    public AttendancesRecyclerAdapter(List<Attendance> listAttendances) {
        this.listAttendances = listAttendances;
    }

    @Override
    public AttendanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attendance_recycler, parent, false);

        return new AttendanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AttendanceViewHolder holder, int position) {
        holder.textViewEID.setText(listAttendances.get(position).getEventID());
        holder.textViewMID.setText(listAttendances.get(position).getMemID());
        holder.textViewNID.setText(listAttendances.get(position).getNmID());
    }

    @Override
    public int getItemCount() {
        Log.v(AttendancesRecyclerAdapter.class.getSimpleName(),""+listAttendances.size());
        return listAttendances.size();
    }


    /**
     * ViewHolder class
     */
    public class AttendanceViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewEID;
        public AppCompatTextView textViewMID;
        public AppCompatTextView textViewNID;


        public AttendanceViewHolder(View view) {
            super(view);
            textViewEID = (AppCompatTextView) view.findViewById(R.id.textViewEID6);
            textViewMID = (AppCompatTextView) view.findViewById(R.id.textViewMID6);
            textViewNID = (AppCompatTextView) view.findViewById(R.id.textViewNID6);
        }
    }


}
