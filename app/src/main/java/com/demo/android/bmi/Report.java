package com.demo.android.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Report extends AppCompatActivity {

    TextView result, suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        findViews();
        calcBMI();
    }

    void findViews() {
        result = (TextView) findViewById(R.id.result);
        suggest = (TextView) findViewById(R.id.suggest);
    }

    void calcBMI(){

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String height = bundle.getString("KEY_HEIGHT");
        String weight = bundle.getString("KEY_WEIGHT");
        double h = Double.parseDouble(height) / 100;
        double w = Double.parseDouble(weight);
        double BMI = w / (h * h);

        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");

        result.setText("你的BMI值：\n" + df.format(BMI));

        if (BMI > 25)
            suggest.setText(R.string.advice_heavy);
        else if (BMI < 20)
            suggest.setText(R.string.advice_light);
        else
            suggest.setText(R.string.advice_arerage);
    }
}
