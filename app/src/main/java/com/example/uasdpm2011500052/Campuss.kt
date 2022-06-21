package com.example.uasdpm2011500052

import android.content.*
import android.database.Cursor
import android.database.sqlite.*

class Campuss(context: Context): SQLiteOpenHelper(context, "campuss", null, 1){
    var NIDN = ""
    var NMDOSEN = ""
    var JABATAN = ""
    var GOLONGANPANGKAT = ""
    var PENDIDIKAN = ""
    var KEAHLIAN = ""
    var PROGRAMSTUDI = ""

    private val tabel = "lecturer"

    override fun onCreate(db: SQLiteDatabase?) {
        val sql
                = """create table $tabel(
            NIDN char(10) primary key,
            nama_dosen varchar(50) not null,
            Jabatan varchar(15) not null,
            golongan_pangkat varchar(30) not null,
            Pendidikan char(2) not null,
            Keahlian varchar(30) not null,
            Program_Studi varchar(50) not null
            )
        """.trimIndent()
        db?.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql
                = "drop table if exists $tabel"
        db?.execSQL(sql)
    }

    fun simpan(): Boolean{
        val db = writableDatabase
        val cv = ContentValues()
        with(cv){
            put("NIDN", NIDN)
            put("nama_dosen", NMDOSEN)
            put("jabatan", JABATAN)
            put("golongan_pangkat", GOLONGANPANGKAT)
            put("pendidikan", PENDIDIKAN)
            put("keahlian", KEAHLIAN)
            put("program_studi", PROGRAMSTUDI)
        }
        val cmd = db.insert(tabel, null, cv)
        db.close()
        return cmd != -1L
    }

    fun ubah(kode: String): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        with(cv){
            put("nama_dosen", NMDOSEN)
            put("jabatan", JABATAN)
            put("golongan_pangkat", GOLONGANPANGKAT)
            put("pendidikan", PENDIDIKAN)
            put("keahlian", KEAHLIAN)
            put("program_studi", PROGRAMSTUDI)

        }
        val cmd = db.update(tabel, cv, "NIDN = ?", arrayOf(kode))
        db.close()
        return cmd != -1
    }

    fun hapus(kode: String): Boolean {
        val db = writableDatabase
        val cmd = db.delete(tabel, "NIDN = ?", arrayOf(kode))
        return cmd != -1
    }

    fun tampil(): Cursor{
        val db = writableDatabase
        val reader = db.rawQuery("select * from $tabel", null)
        return reader
    }
}