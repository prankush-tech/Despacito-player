package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private Button play;
    private Button pause;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        seekBar=findViewById(R.id.seekBar);

//       meadiaplayer using source from file
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.jbdespacito);

        seekBar.setMax(mediaPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,200 );
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(fromUser)
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //using remote source
//        mediaPlayer = new MediaPlayer();
//        try {
//            mediaPlayer.setDataSource("https://www.in2unemusic.com/featuredartists/despacito/luis_fonsi_daddy_yankee_feat_bieber_despacito_remix.mp3");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//       mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//           @Override
//           public void onPrepared(MediaPlayer mp)
//           {
//               Toast.makeText(MainActivity.this, "READY TO PLAY", Toast.LENGTH_SHORT).show();
//               mp.start();
//               seekBar.setMax(mediaPlayer.getDuration());
//               seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                   @Override
//                   public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
//                   {
//                       if(fromUser)
//                       {
//                           mediaPlayer.seekTo(progress);
//                       }
//
//                   }
//
//                   @Override
//                   public void onStartTrackingTouch(SeekBar seekBar)
//                   {
//
//                   }
//
//                   @Override
//                   public void onStopTrackingTouch(SeekBar seekBar)
//                   {
//
//                   }
//               });
//           }
//       });
//        mediaPlayer.prepareAsync();
        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mediaPlayer.start();;
            }
        });
        pause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mediaPlayer.pause();;
            }
        });

    }
}