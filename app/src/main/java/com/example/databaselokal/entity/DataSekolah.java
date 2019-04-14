package com.example.databaselokal.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Sekolah_db")
public class DataSekolah {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id")
    private int id;

    @ColumnInfo(name = "Nama Sekolah")
    private String name;

    @ColumnInfo(name = "Jumlah Siswa")
    private String siswa;

    @ColumnInfo(name = "Jumlah Guru")
    private String guru;

    @ColumnInfo(name = "Alamat Sekolah")
    private String alamat;

    @NonNull

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiswa() {
        return siswa;
    }

    public void setSiswa(String siswa) {
        this.siswa = siswa;
    }

    public String getGuru() {
        return guru;
    }

    public void setGuru(String guru) {
        this.guru = guru;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
