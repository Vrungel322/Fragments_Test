package com.nanddgroup.hw_tutorial12;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MIN_L_SIZE = 255;
    private static final int OFFSET = 50;
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private static final String LEFT = "LEFT";
    private static final String RIGHT = "RIGHT";
    private Button bUp;
    private Button bDown;
    private Button bLeft;
    private Button bRight;
    private RelativeLayout rlRed;
    private RelativeLayout rlGreen;
    private RelativeLayout rlBlue;
    private RelativeLayout RLayout;
    private int totalHeight;
    private int totalWight;
    private Point point;
    private Canvas canvas;
    private boolean forFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(MainActivity.this, "onCreate", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        forFocus = false;
    }

    private void init() {
        forFocus = true;
        bUp = (Button) findViewById(R.id.bUp);
        bDown = (Button) findViewById(R.id.bDown);
        bLeft = (Button) findViewById(R.id.bLeft);
        bRight = (Button) findViewById(R.id.bRight);
        bUp.setOnClickListener(this);
        bDown.setOnClickListener(this);
        bLeft.setOnClickListener(this);
        bRight.setOnClickListener(this);

        rlRed = (RelativeLayout) findViewById(R.id.rlRed);
        rlRed.getLayoutParams().height = totalHeight / 2;
        rlRed.getLayoutParams().width = totalWight / 2;
        rlRed.setBackgroundColor(Color.rgb(255, 0, 0));
        rlRed.setX(totalWight / 2);
        rlRed.setY(0);

        rlGreen = (RelativeLayout) findViewById(R.id.rlGreen);
        rlGreen.getLayoutParams().height = totalHeight / 2;
        rlGreen.getLayoutParams().width = totalWight / 2;
        rlGreen.setBackgroundColor(Color.rgb(0, 255, 0));
        rlGreen.setX(0);
        rlGreen.setY(0);

        rlBlue = (RelativeLayout) findViewById(R.id.rlBlue);
        rlBlue.getLayoutParams().height = totalHeight / 2;
        rlBlue.getLayoutParams().width = MIN_L_SIZE * 4;
        rlBlue.setBackgroundColor(Color.rgb(0, 0, 255));
        rlBlue.setX(0);
        rlBlue.setY(totalHeight / 2);

        canvas = new Canvas();
        point = new Point(this, totalWight / 2, rlBlue.getY());
        RLayout.addView(point);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bUp:{
                offset(UP);
                point.setY(point.getY() - OFFSET);
                checkPositionOfPoint();
            }
            break;

            case R.id.bDown:{
                offset(DOWN);
                point.setY(point.getY() + OFFSET);
                checkPositionOfPoint();
            }
            break;

            case R.id.bLeft:{
                offset(LEFT);
                point.setX(point.getX() - OFFSET);
                checkPositionOfPoint();
            }
            break;

            case R.id.bRight:{
                offset(RIGHT);
                point.setX(point.getX() + OFFSET);
                checkPositionOfPoint();
            }
            break;
        }

    }

    private void checkPositionOfPoint() {
        if (point.getX() <= -totalWight / 2){
            Toast.makeText(MainActivity.this, "X "+ point.getX(), Toast.LENGTH_SHORT).show();
            bLeft.setEnabled(false);
        }else {bLeft.setEnabled(true);}

        if (point.getX() >= totalWight / 2){
            Toast.makeText(MainActivity.this, "X "+ point.getX(), Toast.LENGTH_SHORT).show();
            bRight.setEnabled(false);
        }else {bRight.setEnabled(true);}

        if (point.getY() >= -100 + totalHeight / 2){
            Toast.makeText(MainActivity.this, "Y "+ point.getY(), Toast.LENGTH_SHORT).show();
            bDown.setEnabled(false);
        }else {bDown.setEnabled(true);}

        if (point.getY() <= -((totalHeight / 2)) + 50){
            Toast.makeText(MainActivity.this, "Y "+ point.getY(), Toast.LENGTH_SHORT).show();
            bUp.setEnabled(false);
        }else {bUp.setEnabled(true);}
    }

    private void offset(String tag) {
        switch (tag) {
            case UP:{
                changeParams(rlGreen, UP);
                changeParams(rlRed, UP);
                rlBlue.setY(rlBlue.getY() - OFFSET);
                changeParams(rlBlue, UP);
            }break;

            case DOWN:{
                changeParams(rlGreen, DOWN);
                changeParams(rlRed, DOWN);
                rlBlue.setY(rlBlue.getY() + OFFSET);
                changeParams(rlBlue, DOWN);
            }break;

            case LEFT:{
                changeParams(rlGreen, LEFT);
                rlRed.setX(rlRed.getX() - OFFSET);
                increaseField(rlRed);
            }break;

            case RIGHT:{
                changeParams(rlRed, RIGHT);
                rlRed.setX(rlRed.getX() + OFFSET);
                increaseField(rlGreen);
            }break;

        }
    }

    private void increaseField(RelativeLayout rl) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(rl.getLayoutParams().width + OFFSET,
            rl.getLayoutParams().height);
        rl.setLayoutParams(params);
    }

    private void changeParams(RelativeLayout rl, String way) {
        switch (way){
            case UP:{
                RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(rl.getLayoutParams().width,
                       totalHeight + OFFSET);
                rl.setLayoutParams(parms);
            }break;

            case DOWN:{
                RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(rl.getLayoutParams().width,
                    totalHeight - OFFSET );
                rl.setLayoutParams(parms);
            }break;

            case LEFT:{
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(rl.getLayoutParams().width - OFFSET,
                        rl.getLayoutParams().height);
                rl.setLayoutParams(params);
            }break;

            case RIGHT:{
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(rl.getLayoutParams().width - OFFSET,
                        rl.getLayoutParams().height);
                rl.setLayoutParams(params);
            }break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Toast.makeText(MainActivity.this, "WindowFocusChanged", Toast.LENGTH_SHORT).show();
        RLayout = (RelativeLayout) findViewById(R.id.rlRoot);
        totalHeight = RLayout.getHeight();
        totalWight = RLayout.getWidth();
        Toast.makeText(MainActivity.this, "" + totalWight + " ," + totalHeight, Toast.LENGTH_SHORT).show();
        if (forFocus == false) {
            init();
        }
    }
}
