package com.example.myapplication.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;


import com.example.myapplication.AppExecutors;
import com.example.myapplication.database.AppDatabase;
import com.example.myapplication.database.dao.SubjectDao;
import com.example.myapplication.database.entity.SubjectEntity;

import java.util.List;

public class SubjectRepository {

    private static SubjectRepository sInstance;
    private SubjectDao mSubjectDao;
    private LiveData<List<SubjectEntity>> mSubjects;

    private SubjectRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mSubjectDao = db.subjectDao();
        mSubjects = mSubjectDao.getAllSubject();

        // Other data sources
        /** Single source of truth
         * When we have another sources of data (persistent, model, web service, cache, etc...)
         * we should save them into room database. And we must read only from one source which is
         * our Room database with the help from LiveData.
         *
         * More info at (good examples fetching from API):
         * https://developer.android.com/jetpack/docs/guide#truth
         *
         * And, if we have two LiveData and we want to marge them we should use MediatorLiveData.
         */

    }
    public static SubjectRepository getInstance(Application application) {
        if (sInstance == null) {
            synchronized (SubjectRepository.class) {
                if (sInstance == null) {
                    sInstance = new SubjectRepository(application);
                }
            }
        }
        return sInstance;
    }


    public LiveData<List<SubjectEntity>> getAllSubjects() {
        return mSubjects;
    }

    public LiveData<SubjectEntity> getSubjectById(final int subjectId) {
        return mSubjectDao.getSubjectById(subjectId);
    }

    public void insert(final SubjectEntity subjectEntity) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mSubjectDao.insertSubject(subjectEntity);
            }
        });
    }

    public void update(final SubjectEntity subjectEntity) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mSubjectDao.updateSubject(subjectEntity);
            }
        });
    }

    public void delete(final SubjectEntity subjectEntity) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mSubjectDao.deleteSubject(subjectEntity);
            }
        });
    }

    public void deleteAll() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mSubjectDao.deleteAll();
            }
        });
    }
}
