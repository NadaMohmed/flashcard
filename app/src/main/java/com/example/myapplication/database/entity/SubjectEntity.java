package com.example.myapplication.database.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


import java.util.Date;

@Entity(tableName = "subjects")
public class SubjectEntity

{
@PrimaryKey(autoGenerate = true)
@ColumnInfo( name = "subject_id")
    private int mId ;

    private String mTitle;
    private Date mDate;
    private Integer mColor;

    public  SubjectEntity (@NonNull int id , String title, Date date , Integer color)
    {
        mId=id ;
        mTitle=title ;
        mDate=date ;
        mColor=color ;

    }

    @Ignore
    public SubjectEntity(String title, Date date, Integer color) {
        mTitle = title;
        mDate = date;
        mColor = color;
    }

    public int getMId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getMTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getMDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Integer getMColor() {
        return mColor;
    }

    public void setmColor(Integer mColor) {
        this.mColor = mColor;
    }
}
