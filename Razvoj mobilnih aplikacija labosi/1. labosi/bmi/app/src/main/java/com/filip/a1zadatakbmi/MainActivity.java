package com.filip.a1zadatakbmi;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText kilos;
    EditText height;
    TextView bmi;
    Button cal;
    ImageView image;
    TextView bmiMessage;
    public static float bmiValue= 0;
    public static float he = 0;     //(visina)
    public static float we = 0;        //(masa)
    public static final String message = "Krivi unos težine ili mase";
    public static boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kilos = (EditText) findViewById(R.id.kilogramsID);
        height = (EditText) findViewById(R.id.heghtID);
        cal = (Button) findViewById(R.id.calculateButtonID);
        bmi = (TextView) findViewById(R.id.bmiID);
        image = (ImageView) findViewById(R.id.imageID);
        bmiMessage = (TextView) findViewById(R.id.bmiMessageID);

        calculate();

    }

    public void calculate(){
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kilos.getText().toString().length() > 0 || height.getText().toString().length() > 0){
                    bmi.setText("");
                    bmiMessage.setText("");
                }

                he = Float.parseFloat(height.getText().toString());
                we = Float.parseFloat(kilos.getText().toString());

                if((he > 2.5 || he < 0) || (we > 300 || we < 0)){
                    flag = true;
                    restriction(flag);
                }
                else{
                    bmiValue = we / (he * he);

                    bmi.setText(Float.toString(bmiValue));

                    if(bmiValue < 18.5){
                        image.setImageResource(R.drawable.underweight);
                        bmiMessage.setText("You're underweight. Contact your doctor to increase your body weight.");
                    }
                    else if(bmiValue >= 18.5 && bmiValue <= 24.9){
                        image.setImageResource(R.drawable.normalweight);
                        bmiMessage.setText("You have normal body weight. Eat healthy and exercise to maintain it.");
                    }
                    else if(bmiValue >= 25 && bmiValue <= 29.9){
                        image.setImageResource(R.drawable.overweight);
                        bmiMessage.setText("You have excessive body weight. Contact your doctor to reduce it.");
                    }
                    else if(bmiValue >= 30 && bmiValue <= 34.9){
                        image.setImageResource(R.drawable.obese);
                        bmiMessage.setText("You're overweight! Contact your doctor to reduce body weight and your risk of developing related diseases.");
                    }
                    else if(bmiValue > 34.9){
                        image.setImageResource(R.drawable.extremlyobese);
                        bmiMessage.setText("You're extremely overweight! You are extremely exposed to the risk of developing the disease! Contact your doctor to reduce your body weight.");
                    }
                }
            }
        });

    }

    public void restriction(boolean flag){
        if(flag != false){
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

}
