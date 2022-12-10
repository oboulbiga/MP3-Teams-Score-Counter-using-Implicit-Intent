package com.example.teamsscorecounterusingexplicitintent3333;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ShareActivity extends AppCompatActivity {


        public static final int RQST_PHONE_CALL= 1;
        public static final int RQST_PHONE_MSG= 2;

        private EditText etPnNum;
        private Button btMkCall, btSdMsG, btSearch;
        private String cLNum, sdNum, wNTeam;
        private int SCre;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_share);

                etPnNum = (EditText)findViewById(R.id.editText_getPnNum);
                btMkCall = (Button)findViewById(R.id.button_makeCall);
                btSdMsG = (Button)findViewById(R.id.button_sendMsg);
                btSearch = (Button)findViewById(R.id.button_searchBB);

                Intent shareIntent = getIntent();
                SCre = shareIntent.getIntExtra(com.example.teamsscorecounterusingexplicitintent3333.WinnerActivity.EXTRA_SCRE, 0);
                wNTeam = shareIntent.getStringExtra(com.example.teamsscorecounterusingexplicitintent3333.WinnerActivity.EXTRA_WINNER);

                btMkCall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                callFriend();
                        }
                });


                btSdMsG.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                                msgFriend();
                        }
                });

                btSearch.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                                searchBB();
                        }
                });



        }


        private void callFriend() {
                cLNum = "tel:" + etPnNum.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(cLNum));

                if (callIntent.resolveActivity(getPackageManager()) != null);{
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(this, new String[]{
                                        Manifest.permission.CALL_PHONE}, RQST_PHONE_CALL);
                        }

                        else{
                                startActivity(callIntent);
                        }
                }
        }

        private void msgFriend() {
                sdNum = "sms:" + etPnNum.getText().toString();

                String msg = wNTeam + " won at SCreCount by: " + SCre;

                Intent msgIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(sdNum));
                msgIntent.putExtra("sms_body", msg);
                startActivity(msgIntent);

                if (msgIntent.resolveActivity(getPackageManager()) != null);{
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(this, new String[]{
                                        Manifest.permission.SEND_SMS},  RQST_PHONE_MSG);
                        }

                        else{
                                startActivity(msgIntent);
                        }
                }

        }

        private void searchBB() {
                Uri search = Uri.parse("geo:0,0?q=basketball near me");

                Intent searchIntent = new Intent(Intent.ACTION_VIEW, search);
                startActivity(searchIntent);
        }


}
