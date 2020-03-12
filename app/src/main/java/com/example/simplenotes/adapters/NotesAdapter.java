package com.example.simplenotes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenotes.MainActivity;
import com.example.simplenotes.NoteActivity;
import com.example.simplenotes.R;

import java.io.File;
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

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        private TextView title;
        private View itemView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tV_noteName);
            this.itemView = itemView;

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String selectedNote = MainActivity.noteNames.get(position);
            Intent intent = new Intent(context, NoteActivity.class);
            intent.putExtra(MainActivity.getExtraTitle(), selectedNote);
            intent.putExtra(MainActivity.getExtraIndex(), position);
            context.startActivity(intent);
        }
//
//        @Override
//        public boolean onLongClick(View v) {
//            itemView.setBackgroundColor(Color.GREEN);
//
//            final int position = getAdapterPosition();
//            String selectedNote = MainActivity.noteNames.get(position);
//            final File file = new File(context.getFilesDir(), selectedNote);
//
//            PopupMenu popupMenu = new PopupMenu(context, itemView);
//            popupMenu.inflate(R.menu.list_notes_context);
//            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    boolean deleted = file.delete();
//                    MainActivity.noteNames.remove(position);
//                    return true;
//                }
//            });
//            popupMenu.show();
//            return false;
//        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem delete = menu.add(Menu.NONE, 1, 1, "Delete");
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position = getAdapterPosition();
            String selectedNote = MainActivity.noteNames.get(position);
            File file = new File(context.getFilesDir(), selectedNote);
            boolean deleted = file.delete();
            MainActivity.noteNames.remove(position);
            notifyItemRemoved(position);
            return false;
        }
    }
}
