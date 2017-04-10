package com.androidtutorialshub.loginregister.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Registration;
import com.androidtutorialshub.loginregister.model.Event;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class RegistrationsRecyclerAdapter extends RecyclerView.Adapter<RegistrationsRecyclerAdapter.RegistrationViewHolder> {

    private List<Registration> listRegistrations;

    public RegistrationsRecyclerAdapter(List<Registration> listRegistrations) {
        this.listRegistrations = listRegistrations;
    }

    @Override
    public RegistrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_registration_recycler, parent, false);

        return new RegistrationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RegistrationViewHolder holder, int position) {
        holder.textViewEID.setText(listRegistrations.get(position).getEventID());
        holder.textViewMID.setText(listRegistrations.get(position).getMemID());
        holder.textViewNID.setText(listRegistrations.get(position).getNmID());
    }

    @Override
    public int getItemCount() {
        Log.v(RegistrationsRecyclerAdapter.class.getSimpleName(),""+listRegistrations.size());
        return listRegistrations.size();
    }


    /**
     * ViewHolder class
     */
    public class RegistrationViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewEID;
        public AppCompatTextView textViewMID;
        public AppCompatTextView textViewNID;


        public RegistrationViewHolder(View view) {
            super(view);
            textViewEID = (AppCompatTextView) view.findViewById(R.id.textViewEID5);
            textViewMID = (AppCompatTextView) view.findViewById(R.id.textViewMID5);
            textViewNID = (AppCompatTextView) view.findViewById(R.id.textViewNID5);
        }
    }


}
