package com.example.databaselokal.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.databaselokal.R;
import com.example.databaselokal.entity.DataSekolah;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataSekolah> list;
    MainContract.view view;

    public MainAdapter (Context context, List<DataSekolah> list, MainContract.view view) {
        this.view = view;
        this.context = context;
        this.list = list;
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView tvNama, tvAlamat, tvGuru, tvSiswa, id;
        CardView cvItem;
        public viewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_item_id);
            tvNama = itemView.findViewById(R.id.tv_item_nama);
            tvSiswa = itemView.findViewById(R.id.tv_item_siswa);
            tvGuru = itemView.findViewById(R.id.tv_item_guru);
            tvAlamat = itemView.findViewById(R.id.tv_item_alamat);
            cvItem = itemView.findViewById(R.id.cv_data);
        }

    }
    @NonNull
    @Override
    public MainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.viewHolder holder, int position) {
        final DataSekolah item = list.get(position);
        holder.tvAlamat.setText(String.valueOf(item.getAlamat()));
        holder.tvGuru.setText(item.getGuru());
        holder.tvNama.setText(item.getName());
        holder.id.setText(String.valueOf(item.getId()));
        holder.tvSiswa.setText(String.valueOf(item.getSiswa()));

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.EditData(item);
            }
        });
        holder.cvItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                view.DeleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
