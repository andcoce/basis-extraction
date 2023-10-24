package com.andcoce.estrazionebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private Button backBtn;
    private TextView infoText;
    private TextView[][] table;

    private double matrix[][];
    private int nVectors, dim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        nVectors = getIntent().getIntExtra("nVectors", 0);
        dim      = getIntent().getIntExtra("dim", 0);
        matrix = new double[nVectors][dim];
        retrieveMatrix();

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(onBack);
        fillTable();

        findPivotCells();
    }

    private void fillTable(){
        table = new TextView[nVectors][dim];
        for(int col = 0; col < nVectors; col++) {
            for (int row = 0; row < dim; row++) {
                int resourceId = this.getResources().getIdentifier("mat" + row + col, "id", getPackageName());
                table[col][row] = findViewById(resourceId);
                table[col][row].setText(Double.toString(matrix[col][row]));
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private void findPivotCells(){
        for(int i = 0; i < nVectors; i++){
            for(int j = 0; j < dim; j++){
                if(previousAreZero(i, j) && matrix[i][j] != 0 && matrix[i+1][j] != 0){
                    table[i][j].setBackgroundResource(R.color.red);
                }
            }
        }
    }

    private boolean previousAreZero(int col, int row){
        boolean prev = true;
        for(int i = col - 1; i >= 0; i--){
            if(matrix[i][row] != 0){
                prev = false;
            }
        }
        return prev;
    }

    private void retrieveMatrix(){
        for(int i = 0; i < nVectors; i++){
            for(int j = 0; j < dim; j++){
                matrix[i][j] = getIntent().getDoubleExtra("mat" + i + j, 0);
            }
        }
    }

    View.OnClickListener onBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}