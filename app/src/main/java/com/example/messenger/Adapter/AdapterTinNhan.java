package com.example.messenger.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.messenger.Model.TinNhan;
import com.example.messenger.R;

import java.util.List;

public class AdapterTinNhan  extends ArrayAdapter<TinNhan> {
    Context context;
    int resource;
    @NonNull List<TinNhan> objects;
    public AdapterTinNhan(@NonNull Context context, int resource, @NonNull List<TinNhan> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(this.resource,null);

        TextView _number=row.findViewById(R.id.txtphone);
        TextView _time=row.findViewById(R.id.txtime);
        TextView _body=row.findViewById(R.id.txtbody);

        _number.setText(this.objects.get(position).getNumber());
        _time.setText(this.objects.get(position).getTime());
        _body.setText(this.objects.get(position).getBody());
        return row;
    }
}
