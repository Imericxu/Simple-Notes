package com.example.simplenotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenotes.R;

import java.util.List;

/**
 * Adapter for displaying the names of notes
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<String> noteNames;
    private NoteClickListener noteClickListener;

    /**
     * @param context           context of the class containing the view
     * @param noteNames         list of note names
     * @param noteClickListener listener used by the <code>RecyclerView</code>
     */
    NotesAdapter(Context context, List<String> noteNames, NoteClickListener noteClickListener) {
        this.context = context;
        this.noteNames = noteNames;
        this.noteClickListener = noteClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notes_list_item, parent, false);
        return new MyViewHolder(view, noteClickListener);
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

    public interface NoteClickListener {

        void onNoteClicked(int position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private NoteClickListener noteClickListener;

        MyViewHolder(@NonNull View itemView, NoteClickListener noteClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.tV_noteName);

            this.noteClickListener = noteClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            noteClickListener.onNoteClicked(getAdapterPosition());
        }
    }
}
