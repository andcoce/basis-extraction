package com.andcoce.estrazionebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andcoce.estrazionebase.bean.Matrix;

public class MainActivity extends AppCompatActivity {

    private TextView infoText;
    private Button infoBtn, genBtn;

    private Matrix matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrix = new Matrix();
        initUI();
    }

    private void printMatrix(){
        infoText.setText(matrix.toString());
        infoText.setTextSize(18);
        infoText.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
    }

    View.OnClickListener onGenerate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            matrix.init();
            printMatrix();
        }
    };

    View.OnClickListener onInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        }
    };

    private void initUI(){
        infoText = findViewById(R.id.infoView);
        infoBtn  = findViewById(R.id.infoBtn);
        genBtn   = findViewById(R.id.genBtn);

        infoBtn.setOnClickListener(onInfo);
        genBtn.setOnClickListener(onGenerate);
    }
}