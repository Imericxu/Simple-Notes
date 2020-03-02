package com.example.simplenotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<String> noteNames;

    private OnNoteListener mOnNoteListener;

    MyAdapter(Context context, List<String> noteNames, OnNoteListener mOnNoteListener) {
        this.context = context;
        this.noteNames = noteNames;
        this.mOnNoteListener = mOnNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notes_list_item, parent, false);
        return new MyViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String noteTitle = noteNames.get(position);
        holder.title.setText(noteTitle);
    }

    @Override
    public int getItemCount() {
        return noteNames.size();
    }

    public interface OnNoteListener {

        void onNoteClicked(int position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private OnNoteListener onNoteListener;

        MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.tV_noteName);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClicked(getAdapterPosition());
        }
    }
}
