package com.andcoce.estrazionebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    private Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

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