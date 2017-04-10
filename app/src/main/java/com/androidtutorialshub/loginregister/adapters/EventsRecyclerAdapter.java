package com.androidtutorialshub.loginregister.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Event;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.EventViewHolder> {

    private List<Event> listEvents;

    public EventsRecyclerAdapter(List<Event> listEvents) {
        this.listEvents = listEvents;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_recycler, parent, false);

        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.textViewEID.setText(listEvents.get(position).getEventID());
        holder.textViewEName.setText(listEvents.get(position).getName());
        holder.textViewEVenue.setText(listEvents.get(position).getVenue());
        holder.textViewEType.setText(listEvents.get(position).getType());
    }

    @Override
    public int getItemCount() {
        Log.v(EventsRecyclerAdapter.class.getSimpleName(),""+listEvents.size());
        return listEvents.size();
    }


    /**
     * ViewHolder class
     */
    public class EventViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewEID;
        public AppCompatTextView textViewEName;
        public AppCompatTextView textViewEVenue;
        public AppCompatTextView textViewEType;


        public EventViewHolder(View view) {
            super(view);
            textViewEID = (AppCompatTextView) view.findViewById(R.id.textViewEID1);
            textViewEName = (AppCompatTextView) view.findViewById(R.id.textViewEName1);
            textViewEVenue = (AppCompatTextView) view.findViewById(R.id.textViewEVenue1);
            textViewEType = (AppCompatTextView) view.findViewById(R.id.textViewEType1);
        }
    }


}
