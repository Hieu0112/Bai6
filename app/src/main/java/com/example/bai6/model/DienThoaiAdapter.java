package com.example.bai6.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai6.R;

import java.util.ArrayList;
import java.util.List;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.DienThoaiViewHolder>{
    private Context context;
    private List<DienThoai>listBackup;
    private List<DienThoai>mList;
    private DTItemListener dtItemListener;
    public DienThoaiAdapter(Context context) {
        this.context = context;
        this.mList = new ArrayList<>();
        listBackup = new ArrayList<>();
    }
    public List<DienThoai> getBackup(){
        return listBackup;
    }
    public void filterList(List<DienThoai>filterList){
        mList=filterList;
        notifyDataSetChanged();
    }
    public void setClickListener(DTItemListener dtItemListener) {
        this.dtItemListener = dtItemListener;
    }

    @NonNull
    @Override
    public DienThoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new DienThoaiViewHolder(view);
    }

    public DienThoai getItem(int position){
        return mList.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull DienThoaiViewHolder holder, int position) {
        DienThoai dt=mList.get(position);
        if(dt==null){
            return;
        }
        holder.img.setImageResource(dt.getImg());
        holder.tvName.setText(dt.getTen());
        holder.tvxuatxu.setText(dt.getXuatsu());
        holder.tvPrice.setText(dt.getGia()+"");
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Thông báo Xác nhận xóa");
                builder.setMessage("bạn có muốn xóa bài "+dt.getTen()+" này không ?");
                builder.setIcon(R.drawable.img);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listBackup.remove(position);
                        mList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

    }
    public void add(DienThoai dt){
        mList.add(dt);
        listBackup.add(dt);
        notifyDataSetChanged();
    }
    public void update(int position,DienThoai dt){
        mList.set(position,dt);
        listBackup.set(position,dt);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    public class DienThoaiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName,tvxuatxu,tvPrice;
        private Button btRemove;

        public DienThoaiViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            tvName=view.findViewById(R.id.txtName);
            tvxuatxu=view.findViewById(R.id.txtxuatxu);
            tvPrice=view.findViewById(R.id.txtPrice);
            btRemove=view.findViewById(R.id.btRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(dtItemListener!=null){
                dtItemListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
    public interface DTItemListener{
        void onItemClick(View view,int position);
    }
}
