package com.example.roomwordssample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    //to cacche the list of words
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application){
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    //Getter method completely hides the implementation from the UI
    LiveData<List<Word>> getAllWords(){return mAllWords;}

    //Wrapper method that calls the repo's insert method, to hide implementation ofinsert from UI
    public void insert(Word word){mRepository.insert(word); }

    public void deleteWord(Word word){mRepository.deleteWord(word);}

}
