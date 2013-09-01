package com.hunterdavis.easycatwhistlehd;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.hunterdavis.easycatwhistlehd.R;

public class EasyCatWhistle extends Activity {

    private static float currentFrequency = 26000;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
		// listener for frequency button
		OnClickListener frequencyListner = new OnClickListener() {
			public void onClick(View v) {
				playFrequency(v.getContext(), currentFrequency);

			}
		};

        ImageView catview = (ImageView)findViewById(R.id.imageView);
        catview.setOnClickListener(frequencyListner);

        TextView instview = (TextView)findViewById(R.id.textView1);
        instview.setOnClickListener(frequencyListner);


        SeekBar seek=(SeekBar) findViewById(R.id.seekBar);
        seek.setMax(10);
        seek.setProgress(5);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // 26000 - 64000 = 38000 spread / 10 = 26000 + 380- * position
                currentFrequency = 26000 + (380 * progress);
                setCatPhoto(seekBar.getContext(), progress);

            }
        });
		
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adView.loadAd(new AdRequest());
        
    }

    public void setCatPhoto(Context context, int number) {
        ImageView catview = (ImageView)findViewById(R.id.imageView);

        switch (number)
        {
            case 1:
                catview.setImageResource(R.drawable.one);
                break;
            case 2:
                catview.setImageResource(R.drawable.two);
                break;
            case 3:
                catview.setImageResource(R.drawable.three);
                break;
            case 4:
                catview.setImageResource(R.drawable.four);
                break;
            case 5:
                catview.setImageResource(R.drawable.five);
                break;
            case 6:
                catview.setImageResource(R.drawable.six);
                break;
            case 7:
                catview.setImageResource(R.drawable.seven);
                break;
            case 8:
                catview.setImageResource(R.drawable.eight);
                break;
            case 9:
                catview.setImageResource(R.drawable.nine);
                break;
            case 10:
            case 0:
                catview.setImageResource(R.drawable.ten);
                break;
            default:
                catview.setImageResource(R.drawable.one);
                break;
        }
    }
    
	public void playFrequency(Context context, float frequency) {

		// final float frequency2 = 440;
		float increment = (float) (2 * Math.PI) * frequency / 44100; // angular
																		// increment
																		// for
																		// each
																		// sample
		float angle = 0;
		AndroidAudioDevice device = new AndroidAudioDevice();
		float samples[] = new float[1024];

		for (int j = 0; j < 60; j++) {
			for (int i = 0; i < samples.length; i++) {
				samples[i] = (float) Math.sin(angle);
				angle += increment;
			}

			device.writeSamples(samples);

		}
	}
    
    
    
}