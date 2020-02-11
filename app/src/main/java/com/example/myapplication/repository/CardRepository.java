package com.example.myapplication.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;


import com.example.myapplication.AppExecutors;
import com.example.myapplication.database.AppDatabase;
import com.example.myapplication.database.dao.CardDao;
import com.example.myapplication.database.entity.CardEntity;

public class CardRepository {
    private static CardRepository sInstance;
    private  CardDao mCardDao;

    private CardRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mCardDao = db.cardDao();
    }
    public static CardRepository getInstance(Application application) {
        if (sInstance == null) {
            synchronized (CardRepository.class) {
                if (sInstance == null) {
                    sInstance = new CardRepository(application);
                }
            }
        }
        return sInstance;
    }
    public LiveData<PagedList<CardEntity>> getAllCardsById(int id) {
        // Configuration for our Paging
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPrefetchDistance(10)
                .setPageSize(20)
                .build();

        LiveData<PagedList<CardEntity>> cardsById = (new LivePagedListBuilder(mCardDao.getCardsByParentId(id), pagedListConfig)).build();

        return cardsById;
    }
    public void insert(final CardEntity cardEntity) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mCardDao.insertCard(cardEntity);
            }
        });
    }

    public void update(final CardEntity cardEntity) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mCardDao.updateCard(cardEntity);
            }
        });
    }

    public void delete(final CardEntity cardEntity) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mCardDao.deleteCard(cardEntity);
            }
        });
    }

    public void deleteAll() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mCardDao.deleteAll();
            }
        });
    }

}
