package com.example.simplenotes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenotes.MainActivity;
import com.example.simplenotes.NoteActivity;
import com.example.simplenotes.R;

import java.util.List;

/**
 * Adapter for displaying the names of notes
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<String> noteNames;

    /**
     * @param context   context of the class containing the view
     * @param noteNames list of note names
     */
    public NotesAdapter(Context context, List<String> noteNames) {
        this.context = context;
        this.noteNames = noteNames;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_notes, parent, false);
        return new MyViewHolder(view);
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

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tV_noteName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String selectedNote = MainActivity.noteNames.get(getAdapterPosition());
            Intent intent = new Intent(context, NoteActivity.class);
            intent.putExtra(MainActivity.getExtraTitle(), selectedNote);
            intent.putExtra(MainActivity.getExtraIndex(), position);
            context.startActivity(intent);
        }
    }
}
