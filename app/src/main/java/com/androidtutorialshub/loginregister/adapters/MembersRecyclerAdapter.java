package com.androidtutorialshub.loginregister.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Member;
import com.androidtutorialshub.loginregister.model.User;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class MembersRecyclerAdapter extends RecyclerView.Adapter<MembersRecyclerAdapter.MemberViewHolder> {

    private List<Member> listMembers;

    public MembersRecyclerAdapter(List<Member> listUsers) {
        this.listMembers = listMembers;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_member_recycler, parent, false);

        return new MemberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        holder.textViewName.setText(listMembers.get(position).getName());
        holder.textViewEmail.setText(listMembers.get(position).getEmail());
        holder.textViewMemID.setText(listMembers.get(position).getMemId());
        holder.textViewAccessLevel.setText(listMembers.get(position).getAccessLevel());
        holder.textViewPhone.setText(listMembers.get(position).getPhoneNo());
    }

    @Override
    public int getItemCount() {
        Log.v(MembersRecyclerAdapter.class.getSimpleName(),""+listMembers.size());
        return listMembers.size();
    }


    /**
     * ViewHolder class
     */
    public class MemberViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewMemID;
        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPhone;
        public AppCompatTextView textViewAccessLevel;




        public MemberViewHolder(View view) {
            super(view);
            textViewMemID= (AppCompatTextView) view.findViewById(R.id.textViewMemID2);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName2);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail2);
            textViewPhone = (AppCompatTextView) view.findViewById(R.id.textViewMobile2);
            textViewAccessLevel = (AppCompatTextView) view.findViewById(R.id.textViewAccessLevel2);
        }
    }


}
