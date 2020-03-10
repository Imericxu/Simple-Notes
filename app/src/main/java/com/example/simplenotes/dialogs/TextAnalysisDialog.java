package com.example.simplenotes.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.simplenotes.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class TextAnalysisDialog extends AppCompatDialogFragment {

    private String words;
    private String sentences;

    public TextAnalysisDialog(int words, int sentences) {
        this.words = "" + words;
        this.sentences = "" + sentences;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context context = requireContext();

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        View view = View.inflate(context, R.layout.dialog_analyze, null);
        builder.setTitle("Text Analysis");
        builder.setView(view);
        builder.setNeutralButton("OK", null);

        TextView wordCount = view.findViewById(R.id.tV_wordNumber);
        TextView sentenceCount = view.findViewById(R.id.tV_sentenceNumber);

        wordCount.setText(words);
        sentenceCount.setText(sentences);

        return builder.create();
    }
}
