package com.example.teamsscorecounterusingexplicitintent3333;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WinnerActivity extends AppCompatActivity {

    private TextView textView_win;
    private Button button_sTShare;
    private String WNGTeam;
    private int SCre_Spread;
    private Intent shareActivity;

    public static final String EXTRA_WINNER = "com.example.teamsscorecounterusingexplicitintent3333.EXTRA_WINNER";
    public static final String EXTRA_SCRE = "com.example.teamsscorecounterusingexplicitintent3333.EXTRA_SCRE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        textView_win = (TextView)findViewById(R.id.winner_text);
        button_sTShare = (Button)findViewById(R.id.button_shareAct);
        shareActivity = new Intent(this, ShareActivity.class);

        button_sTShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareActivity.putExtra(EXTRA_WINNER, WNGTeam);
                shareActivity.putExtra(EXTRA_SCRE, SCre_Spread);

                startActivity(shareActivity);
            }
        });

        Intent intent = getIntent();
        int team1_int = intent.getIntExtra(MainActivity.EXTRA_INT1, 0);
        int team2_int = intent.getIntExtra(MainActivity.EXTRA_INT2, 0);
        WNGTeam = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        SCre_Spread = (Math.max(team1_int,team2_int) - Math.min(team1_int,team2_int));

        textView_win.setText(WNGTeam + " won by: " + SCre_Spread);


    }

}

