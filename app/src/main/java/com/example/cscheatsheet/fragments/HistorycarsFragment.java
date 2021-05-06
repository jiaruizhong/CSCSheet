package com.example.cscheatsheet.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cscheatsheet.R;
import com.example.cscheatsheet.fragments.flashcards.EditCardActivity;
import com.example.cscheatsheet.fragments.flashcards.Flashcard;
import com.example.cscheatsheet.fragments.flashcards.FlashcardDatabase;

import java.util.List;

import static com.parse.Parse.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistorycarsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistorycarsFragment extends Fragment {


    FlashcardDatabase flashcardDatabase;

    List<Flashcard> allFlashcards;
    private RecyclerView recyclerView;
    private FlashcardAdapter adapter;
    private TextView update;
    public HistorycarsFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment HistorycarsFragment.
     */
    public static HistorycarsFragment newInstance() {
        HistorycarsFragment fragment = new HistorycarsFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historycars, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flashcardDatabase =  FlashcardDatabase.getInstance();
        allFlashcards = flashcardDatabase.getAllCards();
        recyclerView=view.findViewById(R.id.mrecycle);
        adapter=new FlashcardAdapter(allFlashcards);
        adapter.setResultInterface(new ResultInterface() {
            @Override
            public void click(Flashcard flashcard) {
                Bundle bundle = new Bundle();
                //        this.question = question;
                //        this.answer = answer;
                bundle.putString("id",flashcard.getUuid());
                bundle.putString("question",flashcard.getQuestion());
                bundle.putString("answer",flashcard.getAnswer());

                Intent intent = new Intent(getActivity(), EditCardActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        update=view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }

    public void update(){
        List<Flashcard> allCards = flashcardDatabase.getAllCards();
        allFlashcards.clear();
        allFlashcards.addAll(allCards);
        adapter.notifyDataSetChanged();
    }

}
