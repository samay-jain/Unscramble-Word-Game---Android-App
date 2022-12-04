package com.example.unscrambleword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogBox extends Dialog
{
    private final String message;
    private final PlayGame play;

    public DialogBox(@NonNull Context context, String message, PlayGame play) {
        super(context);
        this.message=message;
        this.play=play;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialog_box);
        final TextView showwinner = findViewById(R.id.showwinner);
        final Button playagain = findViewById(R.id.PlayAgain);
        showwinner.setText(message);
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}