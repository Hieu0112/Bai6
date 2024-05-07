package com.example.bai6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bai6.model.DienThoai;
import com.example.bai6.model.DienThoaiAdapter;
import com.example.bai6.model.SpinnerAdp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DienThoaiAdapter.DTItemListener,
        SearchView.OnQueryTextListener{
    private Spinner sp;
    private RecyclerView recyclerView;
    private DienThoaiAdapter adapter;
    private EditText eName,eDesc,ePrice;
    private Button btAdd,btUpdate;
    private SearchView searchView;
    private int pcurr;
    private int[]imgs={R.drawable.img,R.drawable.img_2,R.drawable.img_1,R.drawable.img_3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new DienThoaiAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DienThoai dt=new DienThoai();
                String i=sp.getSelectedItem().toString();
                String name=eName.getText().toString();
                String desc=eDesc.getText().toString();
                String p=ePrice.getText().toString();
                int img=R.drawable.img;
                double price=0;
                try {
                    img=imgs[Integer.parseInt(i)];
                    price=Double.parseDouble(p);
                    dt.setImg(img);
                    dt.setTen(name);
                    dt.setXuatsu(desc);
                    dt.setGia(price);
                    adapter.add(dt);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap lai",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DienThoai cat=new DienThoai();
                String i=sp.getSelectedItem().toString();
                String name=eName.getText().toString();
                String desc=eDesc.getText().toString();
                String p=ePrice.getText().toString();
                int img=R.drawable.img;
                double price=0;
                try {
                    img=imgs[Integer.parseInt(i)];
                    price=Double.parseDouble(p);
                    cat.setImg(img);
                    cat.setTen(name);
                    cat.setXuatsu(desc);
                    cat.setGia(price);
                    adapter.update(pcurr,cat);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Sua thong tin",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        sp=findViewById(R.id.img);
        SpinnerAdp adapter = new SpinnerAdp(this);
        sp.setAdapter(adapter);
        recyclerView=findViewById(R.id.recycleView);
        eName=findViewById(R.id.name);
        eDesc=findViewById(R.id.describe);
        ePrice=findViewById(R.id.price);
        btAdd=findViewById(R.id.btAdd);
        btUpdate=findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
        searchView=findViewById(R.id.search);
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurr=position;
        DienThoai dienThoai=adapter.getItem(position);
        int img=dienThoai.getImg();
        int p=0;
        for (int i = 0; i < imgs.length; i++) {
            if(img==imgs[i]){
                p=i;
                break;
            }
        }
        sp.setSelection(p);
        eName.setText(dienThoai.getTen());
        eDesc.setText(dienThoai.getXuatsu());
        ePrice.setText(dienThoai.getGia()+"");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }
    private void filter(String s){
        List<DienThoai> filterlist=new ArrayList<>();
        for (DienThoai i:adapter.getBackup()) {
            if(i.getTen().toLowerCase().contains(s.toLowerCase())){
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this,"No Data found",Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.filterList(filterlist);
        }
    }
}