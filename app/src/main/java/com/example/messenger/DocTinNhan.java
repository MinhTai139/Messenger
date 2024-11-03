package com.example.messenger;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.messenger.Adapter.AdapterTinNhan;
import com.example.messenger.Model.TinNhan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DocTinNhan extends AppCompatActivity {
    private static final int REQUEST_SMS_ASK_PERMISSIONS=1002;
    ListView lvdoctinnhan;
    ArrayList<TinNhan> dstinnhan;
    AdapterTinNhan adapterTinNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doc_tin_nhan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lvdoctinnhan=findViewById(R.id.lvdoctn);
        dstinnhan=new ArrayList<>();
        adapterTinNhan =new AdapterTinNhan(DocTinNhan.this,R.layout.item_tinnhan,dstinnhan);
        lvdoctinnhan.setAdapter(adapterTinNhan);

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        Uri uri=Uri.parse("content://sms/inbox");
        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
        dstinnhan.clear();
        while (cursor.moveToNext()){
            int indexphonenumber= cursor.getColumnIndex("address");
            int indextime= cursor.getColumnIndex("date");
            int indexbody= cursor.getColumnIndex("body");

            String phonenumber=cursor.getString(indexphonenumber);
            String time=cursor.getString(indextime);
            String body=cursor.getString(indexbody);

            dstinnhan.add(new TinNhan(phonenumber,sdf.format(Long.parseLong(time)),body));
            adapterTinNhan.notifyDataSetChanged();

        }
    }
}