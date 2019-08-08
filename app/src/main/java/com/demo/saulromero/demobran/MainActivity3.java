package com.demo.saulromero.demobran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button myButton = new Button(this);
        myButton.setText("Push Me");

        ConstraintLayout ll = (ConstraintLayout) findViewById(R.id.main);
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(myButton, lp);

         myButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 new Timer().scheduleAtFixedRate(new TimerTask(){
                     @Override
                     public void run(){

                         runOnUiThread(new Runnable()
                         {
                             @Override
                             public void run()
                             {
                                 Toast.makeText(getApplicationContext(),"Termino el tiempo",Toast.LENGTH_SHORT).show();
                             }
                         });


                     }
                 },0,10000);



             }
         });





    }
}
