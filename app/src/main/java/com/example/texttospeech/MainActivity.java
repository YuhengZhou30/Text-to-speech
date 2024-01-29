package com.example.texttospeech;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Locale;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextToSpeech t1;
    EditText ed1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.button);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    int result = t1.setLanguage(Locale.CHINA);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Handle error: language not supported
                        Toast.makeText(getApplicationContext(), "Language not supported", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle error: Text-to-Speech initialization failed
                    Toast.makeText(getApplicationContext(), "Text-to-Speech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Reemplaza el código dentro del método onClick con esto:

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = ed1.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    // Utilizar la versión actualizada de speak para API 21 en adelante
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    // Utilizar la versión anterior de speak para versiones anteriores a API 21
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}