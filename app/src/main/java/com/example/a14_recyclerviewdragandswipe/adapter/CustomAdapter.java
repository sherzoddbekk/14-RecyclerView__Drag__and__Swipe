package com.example.a14_recyclerviewdragandswipe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a14_recyclerviewdragandswipe.MainActivity;
import com.example.a14_recyclerviewdragandswipe.R;
import com.example.a14_recyclerviewdragandswipe.helper.ItemTouchHelperAdapter;
import com.example.a14_recyclerviewdragandswipe.model.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {
    List<Member> members;
    MainActivity activity;

    public CustomAdapter(List<Member> members, MainActivity activity) {
        this.members = members;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_custom_view,parent,false);
        return new CustomHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Member member = members.get(position);
        if (holder instanceof CustomHolderView){


            TextView firstName = ((CustomHolderView) holder).first_name;
            TextView lastName =      ((CustomHolderView) holder).last_name;

            firstName.setText(member.getFirstName());
            lastName.setText(member.getLastName());


        }
    }



    @Override
    public int getItemCount() {
        return members.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

        if (fromPosition < toPosition){
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(members,i,  i+1);
            }
        }else {
            for (int i = fromPosition; i > toPosition ; i--) {
               Collections.swap(members,i,i-1);
            }
        }
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        members.remove(position);
        notifyItemRemoved(position);
    }

    private class CustomHolderView extends RecyclerView.ViewHolder{

        View view;
        TextView first_name, last_name;
        public CustomHolderView(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            first_name = itemView.findViewById(R.id.firstName);
            last_name = itemView.findViewById(R.id.lastName);

        }
    }
}
