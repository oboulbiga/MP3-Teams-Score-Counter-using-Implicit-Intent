package com.example.teamsscorecounterusingexplicitintent3333;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_team1, button_team2;
    private TextView textView_team1;
    private TextView textView_team2;
    private int team1_int = 0, team2_int = 0;
    private String win_team;

    public static final String EXTRA_MESSAGE = "com.bryanmarchena.SCrecount.EXTRA_MESSAGE";
    public static final String EXTRA_INT1 = "com.bryanmarchena.SCrecount.EXTRA_INT1";
    public static final String EXTRA_INT2 = "com.bryanmarchena.SCrecount.EXTRA_INT2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_team1 = (Button)findViewById(R.id.button_one);
        button_team2 = (Button)findViewById(R.id.button_two);
        textView_team1 = (TextView)findViewById(R.id.team1_SCreDisplay);
        textView_team2 = (TextView)findViewById(R.id.team2_SCreDisplay);

        textView_team1.setText("SCre: " + String.valueOf(team1_int));
        textView_team2.setText("SCre: " + String.valueOf(team2_int));

        button_team1.setOnClickListener((View.OnClickListener)this);
        button_team2.setOnClickListener((View.OnClickListener)this);
    }

    public void onClick(View view){
        if(button_team1.equals(view)){
            team1_int += 1;
            textView_team1.setText("SCre: " + String.valueOf(team1_int));
            if(team1_int == 5){
                gameOver();
            }
        }
        else if(button_team2.equals(view)){
            team2_int += 1;
            textView_team2.setText("SCre: " + String.valueOf(team2_int));
            if(team2_int == 5){
                gameOver();
            }
        }

    }

    public void gameOver(){
        if (team1_int == Math.max(team1_int,team2_int)){
            win_team = "TEAM 1";
        }
        else{
            win_team = "TEAM 2";
        }

        Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra(EXTRA_MESSAGE, win_team);
        intent.putExtra(EXTRA_INT1,team1_int);
        intent.putExtra(EXTRA_INT2, team2_int);
        startActivity(intent);
    }


}



