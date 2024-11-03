package com.example.messenger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SMS_ASK_PERMISSIONS=1002;
    Button btndoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addControl();
        addEvent();
    }

    private void addEvent(){
        btndoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyManHinhTinNhan();
            }
        });
    }

    private void addControl(){
        btndoc=findViewById(R.id.button_read);
    }

    private void XuLyManHinhTinNhan(){
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{""+
            "android.permission.READ_SMS"},REQUEST_SMS_ASK_PERMISSIONS);
        }else{
            Intent intent=new Intent(MainActivity.this,DocTinNhan.class);
            intent.setClassName("com.example.messenger","com.example.messenger.DocTinNhan");
            startActivity(intent);
        }
    }
}