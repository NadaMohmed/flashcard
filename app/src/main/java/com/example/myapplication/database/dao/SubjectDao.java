package com.example.myapplication.database.dao;



import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.myapplication.database.entity.SubjectEntity;

import java.util.List;

@Dao
public interface SubjectDao

{
    @Query("SELECT * FROM subjects")
    LiveData<List<SubjectEntity>> getAllSubject();

    @Query("select * from subjects where subject_id = :id")
   LiveData <SubjectEntity> getSubjectById (int id ) ;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubject(SubjectEntity subjectEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSubject(SubjectEntity subjectEntity);

    @Delete
    void deleteSubject(SubjectEntity subjectEntity);

    @Query("DELETE FROM subjects")
    void deleteAll();
}
