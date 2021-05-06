package com.example.cscheatsheet.fragments.flashcards;

import android.content.Context;

import java.util.List;

import androidx.room.Room;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cscheatsheet.ParseApplication;

public class FlashcardDatabase {
    private final AppDatabase db;

    //change provide single
    private static class FlashcardDatabaseHolder {
        private final static FlashcardDatabase instance = new FlashcardDatabase(ParseApplication.getInstances());
    }
    public static FlashcardDatabase getInstance() {
        return FlashcardDatabaseHolder.instance;
    }
    private FlashcardDatabase(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "flashcard-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public List<Flashcard> getAllCards() {
        return db.flashcardDao().getAll();
    }
    public Flashcard getfromId(String uuid){
        Flashcard flashcard = db.flashcardDao().querybyuuid(uuid);
        return flashcard;
    }

    public void insertCard(Flashcard flashcard) {
        db.flashcardDao().insertAll(flashcard);
    }

    public void deleteCard(String flashcardQuestion) {
        List<Flashcard> allCards = db.flashcardDao().getAll();
        for (Flashcard f : allCards) {
            if (f.getQuestion().equals(flashcardQuestion)) {
                db.flashcardDao().delete(f);
            }
        }
    }

    public void updateCard(Flashcard flashcard) {
        db.flashcardDao().update(flashcard);
    }


}
