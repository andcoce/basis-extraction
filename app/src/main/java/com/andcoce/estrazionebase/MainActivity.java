package com.andcoce.estrazionebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andcoce.estrazionebase.bean.Matrix;
import com.andcoce.estrazionebase.business.GaussianElimination;

public class MainActivity extends AppCompatActivity {

    private TextView infoText, moduleText;
    private EditText editModule;
    private Button infoBtn, genBtn, gaussBtn, resultsBtn, resetBtn;

    private Matrix matrix;
    private GaussianElimination gauss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    View.OnClickListener onGenerate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int module = Integer.parseInt(editModule.getText().toString());
            if(module > 0 && module < 10){
                matrix = new Matrix(module); //eventuale reset
                matrix.init();
                gauss = new GaussianElimination(matrix);

                infoText.setText(matrix.toVectorString());
                infoText.setTextSize(15);

                infoBtn.setText(R.string.info2);
                genBtn.setVisibility(View.GONE);
                gaussBtn.setVisibility(View.VISIBLE);

                moduleText.setVisibility(View.GONE);
                editModule.setVisibility(View.GONE);
            }else{
                Toast.makeText(MainActivity.this, R.string.alert, Toast.LENGTH_SHORT).show();
            }

        }
    };

    View.OnClickListener onGauss = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            infoBtn.setVisibility(View.GONE);
            gaussBtn.setVisibility(View.GONE);
            resetBtn.setVisibility(View.VISIBLE);
            resultsBtn.setVisibility(View.VISIBLE);

            infoText.setText(gauss.echelonForm());
        }
    };

    View.OnClickListener onReset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetBtn.setVisibility(View.GONE);
            resultsBtn.setVisibility(View.GONE);
            infoBtn.setVisibility(View.VISIBLE);
            infoBtn.setText(R.string.info);
            genBtn.setVisibility(View.VISIBLE);
            gauss = new GaussianElimination(matrix);

            moduleText.setVisibility(View.VISIBLE);
            editModule.setVisibility(View.VISIBLE);

            infoText.setText(R.string.help);
            infoText.setTextSize(20);
        }
    };

    View.OnClickListener onResults = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
            intent.putExtra("nVectors", matrix.getN_VECTORS());
            intent.putExtra("dim", matrix.getVECTOR_DIM());
            for(int i = 0; i < matrix.getN_VECTORS(); i++){
                for(int j = 0; j < matrix.getVECTOR_DIM(); j++){
                    intent.putExtra("mat" + i + j, matrix.getMatrix()[i][j]);
                }
            }
            startActivity(intent);
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
        infoText   = findViewById(R.id.infoView);
        moduleText = findViewById(R.id.moduleText);
        editModule = findViewById(R.id.editModule);
        infoBtn    = findViewById(R.id.infoBtn);
        genBtn     = findViewById(R.id.genBtn);
        gaussBtn   = findViewById(R.id.gaussBtn);
        resetBtn   = findViewById(R.id.resetBtn);
        resultsBtn = findViewById(R.id.resultsBtn);

        infoBtn.setOnClickListener(onInfo);
        genBtn.setOnClickListener(onGenerate);
        gaussBtn.setOnClickListener(onGauss);
        resetBtn.setOnClickListener(onReset);
        resultsBtn.setOnClickListener(onResults);
    }
}