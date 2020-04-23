package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button insert, update, clear, delete;
    TextView textView;
    //    LiveData<List<Word>> allWordsLive;
    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(WordViewModel.class);


        textView = findViewById(R.id.textView);

        wordViewModel.getAllWordsLiveData().observe(this, words -> {
            StringBuilder text = new StringBuilder();
            for (Word word : words) {
                text.append(word.getId()).append(":").append(word.getWord()).append(":").append(word.getChineseMeaning()).append("\n");
            }
            textView.setText(text.toString());
        });


        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        clear = findViewById(R.id.clear);
        delete = findViewById(R.id.delete);

        insert.setOnClickListener(v -> {

//            wordDao.add(new Word("Hello","你好"));
            Word word = new Word("Hello", "你好");

            wordViewModel.insertWords(word);
        });
        clear.setOnClickListener(v -> {
//            wordDao.deleteAll();
//            new DeleteAllAsyncTask(wordDao).execute();
            wordViewModel.deleteAll();
        });
        delete.setOnClickListener(v -> {
//            List<Word> list = allWordsLive.getValue();
//
//            System.out.println(list);
//            assert list != null;
//            if (!list.isEmpty()){
//                new DeleteAsyncTask(wordDao).execute(list.get(list.size()-1));
//                wordDao.delete();
            wordViewModel.deleteWord();

        });
        update.setOnClickListener(v -> {
//            List<Word> list = allWordsLive.getValue();
//            assert list != null;
//            if (!list.isEmpty()){
//                Word word = list.get(0);
//                word.setWord("Modified hello");
//                word.setChineseMeaning("修改后的hello");
//                new UpdateAsyncTask(wordDao).execute(word);
//                wordDao.update(word);
            wordViewModel.updateWords();
//            }
        });

    }


}
