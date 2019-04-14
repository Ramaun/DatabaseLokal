package com.example.databaselokal;

import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.DataSekolah;
import com.example.databaselokal.view.MainAdapter;
import com.example.databaselokal.view.MainContract;
import com.example.databaselokal.view.MainData;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.view {
    private DataSekolah dataSekolah;
    private AppDatabase appDatabase;
    private MainData data;
    private MainAdapter adapter;

    private Button btnInput, btnEdit, btnDelete, btnReset;
    private TextView tvNama, tvSiswa, tvGuru, tvAlamat;
    private RecyclerView rvData;

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.iniDb(getApplicationContext());

        btnInput = findViewById(R.id.btn_input);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);
        btnReset = findViewById(R.id.btn_reset);
        tvNama = findViewById(R.id.et_nama);
        tvSiswa = findViewById(R.id.et_siswa);
        tvGuru = findViewById(R.id.et_guru);
        tvAlamat = findViewById(R.id.et_alamat);

        rvData = findViewById(R.id.rv_database);

        rvData.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        data = new MainData(this);

        data.readData(appDatabase);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvNama.getText().toString().equals("") || tvSiswa.getText().toString().equals("") ||
                        tvGuru.getText().toString().equals("") || tvAlamat.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Kolom Kosong!!",Toast.LENGTH_LONG).show();
                } else {
                    data.insertData(tvNama.getText().toString(), tvSiswa.getText().toString(),
                            tvGuru.getText().toString(), tvAlamat.getText().toString(), appDatabase);
                    successAdd();
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvNama.getText().toString().equals("") || tvSiswa.getText().toString().equals("") ||
                        tvGuru.getText().toString().equals("") || tvAlamat.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Kolom Kosong!!",Toast.LENGTH_LONG).show();
                } else {
                    data.editData(tvNama.getText().toString(), tvSiswa.getText().toString(),
                            tvGuru.getText().toString(), tvAlamat.getText().toString(), id, appDatabase);
                    successAdd();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.deleteData(dataSekolah, appDatabase);
                successDelete();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Added", Toast.LENGTH_LONG).show();
        data.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show();
        data.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        tvNama.setText("");
        tvSiswa.setText("");
        tvGuru.setText("");
        tvAlamat.setText("");
        btnReset.setText("Reset");
    }

    @Override
    public void getData(List<DataSekolah> list) {
        adapter = new MainAdapter(this, list, this);
        rvData.setAdapter(adapter);
    }

    @Override
    public void EditData(DataSekolah item) {
        tvNama.setText(item.getName());
        tvSiswa.setText(item.getSiswa());
        tvGuru.setText(item.getGuru());
        tvAlamat.setText(item.getAlamat());
        id = item.getId();
        btnEdit.setText("Edit");
    }

    public void DeleteData(final DataSekolah item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        data.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public void onClick(View v) {
        resetForm();
    }
}