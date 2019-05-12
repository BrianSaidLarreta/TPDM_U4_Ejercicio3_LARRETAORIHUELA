package com.example.tpdm_u4_ejercicio3_larretaorihuela;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bCiclo, bTimer, bHilo;
    CountDownTimer timer;
    Thread hilo;
    int contadorTimer, contadorHilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCiclo = findViewById(R.id.ciclo);
        bHilo = findViewById(R.id.hilo);
        bTimer = findViewById(R.id.timer);

        bCiclo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                for(int i = 1; i<= 100000;i++){
                    bCiclo.setText(""+i);
                }
            }

        });

        timer = new CountDownTimer(1000,200){
            @Override
            public void onTick(long millisUnitFinished){
                contadorTimer++;
                bTimer.setText(""+contadorTimer);
            }

            @Override
            public void onFinish(){
                start();
            }
        };

        bTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

        hilo = new Thread(){

            public void run() {
                while (true) {
                    contadorHilo++;

                    runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    bHilo.setText("" + contadorHilo);
                                }
                            }
                    );

                    try {
                        sleep(200);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };

        bHilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hilo.start();
            }
        });

    }
}
