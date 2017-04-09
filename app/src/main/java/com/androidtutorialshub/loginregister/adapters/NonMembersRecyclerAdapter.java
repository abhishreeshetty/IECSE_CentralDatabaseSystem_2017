package com.androidtutorialshub.loginregister.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Member;
import com.androidtutorialshub.loginregister.model.NonMember;
import com.androidtutorialshub.loginregister.model.User;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class NonMembersRecyclerAdapter extends RecyclerView.Adapter<NonMembersRecyclerAdapter.NonMemberViewHolder> {

    private List<NonMember> listNonMembers;

    public NonMembersRecyclerAdapter(List<NonMember> listUsers) {
        this.listNonMembers = listNonMembers;
    }

    @Override
    public NonMemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nonmember_recycler, parent, false);

        return new NonMemberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NonMemberViewHolder holder, int position) {
        holder.textViewName.setText(listNonMembers.get(position).getName());
        holder.textViewEmail.setText(listNonMembers.get(position).getEmail());
        holder.textViewNMID.setText(listNonMembers.get(position).getNMId());
        holder.textViewPhone.setText(listNonMembers.get(position).getPhoneNo());
    }

    @Override
    public int getItemCount() {
        Log.v(MembersRecyclerAdapter.class.getSimpleName(),""+listNonMembers.size());
        return listNonMembers.size();
    }


    /**
     * ViewHolder class
     */
    public class NonMemberViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewNMID;
        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPhone;





        public NonMemberViewHolder(View view) {
            super(view);
            textViewNMID= (AppCompatTextView) view.findViewById(R.id.textViewNMID3);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName3);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail3);
            textViewPhone = (AppCompatTextView) view.findViewById(R.id.textViewMobile3);

        }
    }


}
