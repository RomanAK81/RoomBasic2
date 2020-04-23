package com.example.roombasic;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;


    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);

    }

    LiveData<List<Word>> getAllWordsLiveData() {
        return wordRepository.getAllWordsLiveData();
    }

    void insertWords(Word... words) {
        wordRepository.insertWords(words);
    }

    void updateWords() {
        wordRepository.updateWords();
    }

    void deleteWord() {
        wordRepository.deleteWord();
    }

    void deleteAll() {
        wordRepository.deleteAll();
    }


}
