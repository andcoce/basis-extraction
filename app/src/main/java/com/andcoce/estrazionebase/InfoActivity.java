package com.andcoce.estrazionebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private Button backBtn;
    private TextView infoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        infoView = findViewById(R.id.infoView);
        infoView.setMovementMethod(new ScrollingMovementMethod()); //enables scrolling

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(onBack);
    }

    View.OnClickListener onBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}