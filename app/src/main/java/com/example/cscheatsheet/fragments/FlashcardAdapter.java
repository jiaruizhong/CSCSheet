package com.example.cscheatsheet.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cscheatsheet.R;
import com.example.cscheatsheet.fragments.flashcards.Flashcard;

import java.util.List;


public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.ViewHolder> {
    private ResultInterface resultInterface;
    private List<Flashcard> flashcards;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public FlashcardAdapter(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    public ResultInterface getResultInterface() {
        return resultInterface;
    }

    public void setResultInterface(ResultInterface resultInterface) {
        this.resultInterface = resultInterface;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Flashcard flashcard = flashcards.get(position);
        holder.question.setText(flashcard.getQuestion());
        holder.answer.setText(flashcard.getAnswer());


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resultInterface != null) {
                    resultInterface.click(flashcard);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return flashcards.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        TextView answer;
        Button button;
        public ViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.tvQuest);
            answer = (TextView) view.findViewById(R.id.tvDescription);
            button=view.findViewById(R.id.button);
        }

    }
}
