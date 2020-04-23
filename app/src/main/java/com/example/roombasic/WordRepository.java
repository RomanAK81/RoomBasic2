package com.example.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>> allWordsLiveData;
    private WordDao wordDao;

    WordRepository(Context context) {
        WordDatabase wordDatabase = WordDatabase.getInstance(context);
        wordDao = wordDatabase.getWordDao();
        allWordsLiveData = wordDao.getAllWordsLiveData();
    }

    LiveData<List<Word>> getAllWordsLiveData() {
        return allWordsLiveData;
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.add(words);
            return null;
        }
    }

    void insertWords(Word... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updateWords() {
        List<Word> lists = allWordsLiveData.getValue();
        System.out.println(lists);
        if (lists != null && !lists.isEmpty()) {
            Word word = lists.get(0);
            word.setWord("Modified hello");
            word.setChineseMeaning("修改后的hello");
            new UpdateAsyncTask(wordDao).execute(word);
        }
    }

    void deleteWord() {
        List<Word> lists = allWordsLiveData.getValue();
        System.out.println(lists);
        if (lists != null && !lists.isEmpty()) {
            new DeleteAsyncTask(wordDao).execute(lists.get(0));
        }
    }

    void deleteAll() {
        new DeleteAllAsyncTask(wordDao).execute();
    }


    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.update(words);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.delete(words);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao wordDao;

        DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAll();
            return null;
        }
    }

}
