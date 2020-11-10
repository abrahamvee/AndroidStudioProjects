package com.example.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public void deleteWord(Word word){
        new deleteWordAsyncTask(mWordDao).execute(word);
    }
    private static class deleteWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        deleteWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    //gets a handle to db and init variables
    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    //wrapper method
    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert (Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }


}
