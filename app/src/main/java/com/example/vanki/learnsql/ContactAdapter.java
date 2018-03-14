package com.example.vanki.learnsql;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vanki on 3/14/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{
    private List<Contact> contacts;
    private Context mContext;
    private LayoutInflater mInflater;

    public ContactAdapter(List<Contact> contacts, Context context){
        this.mContext = context;
        this.contacts = contacts;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_contact,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Contact contact = contacts.get(position);
        holder.mTvName.setText(contact.getmName());

        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClick(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTvName;
        LinearLayout line;

        public MyViewHolder(View itemView){
            super(itemView);
            itemView.setClickable(true);
            mTvName = itemView.findViewById(R.id.tvName);
            line = itemView.findViewById(R.id.layout_line);
        }
    }

    public interface OnItemClickedListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickedListener onItemClickedListener;

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
