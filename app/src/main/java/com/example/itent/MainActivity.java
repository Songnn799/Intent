package com.example.itent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    private Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent in1= new Intent(Intent.ACTION_VIEW);
                EditText ed =findViewById(R.id.editText);
                final String net= ed.getText().toString();
                if (isValidUrl(net)){
                    in1.setData(Uri.parse(net));
                }
                else{
                    Toast.makeText(MainActivity.this,"请输入正确网址",Toast.LENGTH_SHORT).show();
                }
                startActivity(in1);
            }
        });

    }
    public static boolean isValidUrl(String urlString){
        URI uri = null;
        try {
            uri = new URI(urlString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }

        if(uri.getHost() == null){
            return false;
        }
        if(uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https")){
            return true;
        }
        return false;
    }


}