package com.example.a17010407.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText weight;
    EditText tallness;
    Button calculate;
    Button reset;
    TextView datentime;
    TextView bmi;
    TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weight);
        tallness = findViewById(R.id.height);
        calculate = findViewById(R.id.calculate);
        reset = findViewById(R.id.reset);
        datentime = findViewById(R.id.date);
        bmi = findViewById(R.id.bmi);
        status = findViewById(R.id.fattyness);

        calculate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String weight1 = weight.getText().toString();
                        String height1 = tallness.getText().toString();

                        double weight2 = Double.parseDouble(weight1);
                        double height2 = Double.parseDouble(height1);
                        String bmi1 = Double.toString((weight2/(height2 * height2)));
                        double bmil11 = ((weight2 / (height2 * height2)));




                        Calendar now = Calendar.getInstance();
                        String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                                (now.get(Calendar.MONTH) + 1) + "/" +
                                now.get(Calendar.YEAR) + " " +
                                now.get(Calendar.HOUR_OF_DAY) + ":" +
                                now.get(Calendar.MINUTE);

                        datentime.setText(datetime);
                        bmi.setText(bmi1);

                        if (bmil11 >= 30) {
                            status.setText("You are obese");
                        }else if (bmil11 >= 25) {
                            status.setText("You are overweight");
                        }else if (bmil11 >=18.5) {
                            status.setText("Your BMI is normal");
                        }else{
                            status.setText("You are underweight");
                        }

                    }
                }
        );

        reset.setOnClickListener(
                new View.OnClickListener()

                {
                    @Override
                    public void onClick (View v){

                        weight.setText("");
                        tallness.setText("");

                    }
                }
        );
}

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String bmi3 = prefs.getString("bmi","Default BMI");
        String date3 = prefs.getString("date","Date Time");

        bmi.setText(bmi3);
        datentime.setText(date3);


    }


    @Override
    protected void onPause() {
        super.onPause();

        String bmi2 = bmi.getText().toString();
        String datetime2 = datentime.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("bmi",bmi2);
        prefEdit.putString("date",datetime2);
        prefEdit.commit();

    }



}
