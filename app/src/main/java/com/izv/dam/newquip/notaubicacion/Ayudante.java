package com.izv.dam.newquip.notaubicacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class Ayudante extends OrmLiteSqliteOpenHelper {

    private Dao<Nota, Integer> simpleDao = null;
    private RuntimeExceptionDao<Nota, Integer> simpleRunTimeDao = null;


    public Ayudante(Context context) {
        super(context, "ormlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Nota.class);
        }catch (java.sql.SQLException e){
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<Nota, Integer> getDaoNota() throws java.sql.SQLException {
        if (simpleDao == null){
            simpleDao = getDao(Nota.class);
        }
        return simpleDao;
    }

    public RuntimeExceptionDao<Nota, Integer> getDataDaoNota(){
        if(simpleRunTimeDao == null){
            simpleRunTimeDao = getRuntimeExceptionDao(Nota.class);
        }
        return simpleRunTimeDao;
    }

    public void close(){
        super.close();
        simpleDao = null;
        simpleRunTimeDao = null;
    }
}

