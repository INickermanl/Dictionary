package nickerman.com.dictionary2.screens.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nickerman.com.dictionary2.R;
import nickerman.com.dictionary2.WordItem;


public class WordItemAdaptor extends RecyclerView.Adapter<WordItemAdaptor.ViewHolder> {

    private ArrayList<WordItem> listWords = new ArrayList<>();
    private ClickCallback callabck;


    public WordItemAdaptor(ArrayList<WordItem> listWord, ClickCallback callback) {
        this.listWords = listWord;
        this.callabck = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (listWords.size() < 0) {
        } else {
            WordItem item = listWords.get(position);

            holder.numberWord.setText(item.getNumber() + 1 + " ");
            holder.englishWord.setText(item.getEnglishWord() + " ");
            holder.translateWord.setText(item.getTranslateWord() + " ");
            holder.editItem.setOnClickListener(v -> callabck.editItem(position));
            holder.deliteItem.setOnClickListener(v -> callabck.deleteItem(position));
        }

    }

    @Override
    public int getItemCount() {
        return listWords.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView englishWord;
        private TextView translateWord;
        private TextView numberWord;
        private AppCompatImageView deliteItem;
        private AppCompatImageView editItem;

        public ViewHolder(View itemView) {
            super(itemView);
            englishWord = itemView.findViewById(R.id.english_words);
            translateWord = itemView.findViewById(R.id.translate_words);
            numberWord = itemView.findViewById(R.id.number_of_word);
            editItem = itemView.findViewById(R.id.edit);
            deliteItem = itemView.findViewById(R.id.delete);
        }
    }
}
