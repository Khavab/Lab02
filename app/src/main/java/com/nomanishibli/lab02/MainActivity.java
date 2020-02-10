package com.nomanishibli.lab02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean started = false;
    int count = 0;
    TextView counter;
    Button increase, clicker;
    EditText edit;
    final Handler h = new Handler();
    final Handler t = new Handler();
    ConstraintLayout bg;
    Button tryToPressMe;
    int r, g, b;
    int xPos, yPos;
    //Resources res = getResources();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = findViewById(R.id.textBox);
        bg = findViewById(R.id.background);
        increase = findViewById(R.id.increase);
    }


    public void changeColor(View view) {
        bg = findViewById(R.id.background);
        edit = findViewById((R.id.nameText));
        clicker = findViewById(R.id.clickButton);
        r = (int) (Math.random() * 256);
        g = (int) (Math.random() * 256);
        b = (int) (Math.random() * 256);
        bg.setBackgroundColor(Color.rgb(r, g, b));
        String temp = edit.getText().toString();
        clicker.setText(temp);

        Log.d("deb", "it worked i think");
    }

    public void increaser(View view) {
        increase = findViewById(R.id.increase);
        counter = findViewById(R.id.textBox);
        started = !started;

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(started) {
                    count += 1;
                    r = (int) (Math.random() * 256);
                    g = (int) (Math.random() * 256);
                    b = (int) (Math.random() * 256);
                    counter.setTextColor(Color.rgb(r, g, b));
                    counter.setText("Started: " + count);

                    h.postDelayed(this, 1000);
                }
                else
                    counter.setText(("Stopped: " + count));

            }
        }, 1000);
    }

    int c = 0;
    public void move(View view){
        Resources res = getResources();
        tryToPressMe = findViewById(R.id.tryToPressMe);
        xPos = (int)(Math.random() * (Resources.getSystem().getDisplayMetrics().widthPixels - tryToPressMe.getWidth()));
        yPos = (int)(Math.random() * (Resources.getSystem().getDisplayMetrics().heightPixels - tryToPressMe.getHeight() - 250));

        String[] ar = res.getStringArray(R.array.cycle);
        tryToPressMe.setText(ar[c]);
        c += 1;
        if(c > 2)
            c = 0;

        Log.d("move", "xPos: " + xPos + ", yPos: " + yPos);
        t.postDelayed(new Runnable() {
            public void run() {
                if(xPos != tryToPressMe.getX() && yPos != tryToPressMe.getY()){
                    if(xPos > tryToPressMe.getX())
                        tryToPressMe.setX(tryToPressMe.getX() + 1);
                    else if(xPos < tryToPressMe.getX())
                        tryToPressMe.setX(tryToPressMe.getX() - 1);
                    if(yPos > tryToPressMe.getY())
                        tryToPressMe.setY(tryToPressMe.getY() + 1);
                    else if(yPos < tryToPressMe.getY())
                        tryToPressMe.setY(tryToPressMe.getY() - 1);

                    //t.postDelayed(this, 100);
                    t.postDelayed(this, 1);
                }

            }
        }, 100);

    }


}
