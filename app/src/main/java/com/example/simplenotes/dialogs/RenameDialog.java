package com.example.simplenotes.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.simplenotes.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class RenameDialog extends AppCompatDialogFragment {

    private EditText nameInput;
    private ExampleDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context context = requireContext();

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);

        View view = View.inflate(context, R.layout.dialog_rename, null);

        builder.setTitle("Rename");
        builder.setView(view);

        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = nameInput.getText().toString();
                listener.applyText(newName);
            }
        });

        nameInput = view.findViewById(R.id.editText_newName);

        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement Example Dialog Listener");
        }
    }

    public interface ExampleDialogListener {
        void applyText(String newName);
    }
}
