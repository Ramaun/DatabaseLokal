package com.example.databaselokal.view;

import android.view.View;

import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.DataSekolah;

import java.util.List;

public interface MainContract {
    interface view extends View.OnClickListener {
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataSekolah> list);
        void EditData(DataSekolah item);
        void DeleteData(DataSekolah item);
    }

    interface presenter {
        void insertData(String name, String siswa, String guru, String alamat, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String name, String siswa, String guru, String alamat, int id, AppDatabase database);
        void deleteData(DataSekolah dataSekolah, AppDatabase database);
    }

}
