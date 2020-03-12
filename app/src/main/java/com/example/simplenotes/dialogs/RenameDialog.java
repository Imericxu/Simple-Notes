package com.example.simplenotes.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.simplenotes.MainActivity;
import com.example.simplenotes.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class RenameDialog extends AppCompatDialogFragment {

    private EditText nameInput;
    private ExampleDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Context context = requireContext();
        final MediaPlayer errorSound = MediaPlayer.create(context, R.raw.error_sound);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);

        View view = View.inflate(context, R.layout.dialog_rename, null);

        builder.setTitle("Rename");
        builder.setView(view);

        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("OK", null);

        nameInput = view.findViewById(R.id.editText_newName);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button posButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                posButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newName = nameInput.getText().toString();

                        boolean validName = true;

                        if (newName.length() != 0) {
                            for (String noteName : MainActivity.noteNames) {
                                if (newName.equals(noteName)) {
                                    validName = false;
                                    break;
                                }
                            }

                            if (validName) {
                                listener.applyText(newName);
                                dismiss();
                            }
                            else {
                                Toast.makeText(context, "Name taken", Toast.LENGTH_SHORT).show();
                                errorSound.seekTo(0);
                                errorSound.start();
                            }
                        }
                        else {
                            Toast.makeText(context, "Empty name!", Toast.LENGTH_SHORT).show();
                            errorSound.seekTo(0);
                            errorSound.start();
                        }
                    }
                });
            }
        });

        return alertDialog;
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
