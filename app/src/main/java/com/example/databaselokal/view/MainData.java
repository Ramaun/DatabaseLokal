package com.example.databaselokal.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.DataSekolah;

import java.util.List;

public class MainData implements MainContract.presenter {
    private MainContract.view view;

    public MainData(MainContract.view view) {this.view = view;}


    @Override
    public void insertData(String name, String siswa, String guru,
                           String alamat, final AppDatabase database) {
        final DataSekolah dataSekolah = new DataSekolah();
        dataSekolah.setName(name);
        dataSekolah.setSiswa(siswa);
        dataSekolah.setGuru(guru);
        dataSekolah.setAlamat(alamat);
        new InsertData(database, dataSekolah).execute();
    }

    class InsertData extends AsyncTask<Void, Void, Long>{
        private AppDatabase database;
        private DataSekolah dataSekolah;

        public InsertData(AppDatabase database, DataSekolah dataSekolah){
            this.database = database;
            this.dataSekolah = dataSekolah;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return database.dao().insertData(dataSekolah);
        }

        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            view.successAdd();
        }
    }

    @Override
    public void readData(AppDatabase database) {
        List list;
        list = database.dao().getData();
        view.getData(list);
    }

    @Override
    public void editData(String name, String siswa, String guru, String alamat, int id, AppDatabase database) {
        DataSekolah dataSekolah = new DataSekolah();
        dataSekolah.setName(name);
        dataSekolah.setSiswa(siswa);
        dataSekolah.setGuru(guru);
        dataSekolah.setAlamat(alamat);
        dataSekolah.setId(id);
        new EditData(database, dataSekolah).execute();
    }

    class EditData extends AsyncTask<Void, Void, Integer>{
        private AppDatabase database;
        private DataSekolah dataSekolah;

        public EditData(AppDatabase database, DataSekolah dataSekolah){
            this.database = database;
            this.dataSekolah = dataSekolah;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.dao().updateData(dataSekolah);
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute : " + integer);
            view.successAdd();
        }
    }

    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        private DataSekolah dataSekolah;

        public DeleteData(AppDatabase database, DataSekolah dataSekolah){
            this.database = database;
            this.dataSekolah = dataSekolah;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataSekolah);
            return null;
        }

        protected void  onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void deleteData(DataSekolah dataSekolah, AppDatabase database) {
        new DeleteData(database, dataSekolah).execute();
    }

}