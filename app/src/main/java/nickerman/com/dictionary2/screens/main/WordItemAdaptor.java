package nickerman.com.dictionary2.screens.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nickerman.com.dictionary2.R;
import nickerman.com.dictionary2.WordItem;
import nickerman.com.dictionary2.room.entity.TranslateWord;


public class WordItemAdaptor extends RecyclerView.Adapter<WordItemAdaptor.ViewHolder> {

    private List<TranslateWord> listWords;
    private ClickCallback callabck;


    public WordItemAdaptor(List<TranslateWord> listWords, ClickCallback callback) {
        this.callabck = callback;
        this.listWords = new ArrayList<>();
        this.listWords = listWords;
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
            TranslateWord item = listWords.get(position);

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
        private AppCompatImageView deliteItem;
        private AppCompatImageView editItem;

        public ViewHolder(View itemView) {
            super(itemView);
            englishWord = itemView.findViewById(R.id.english_word);
            translateWord = itemView.findViewById(R.id.translate_word);
            editItem = itemView.findViewById(R.id.edit);
            deliteItem = itemView.findViewById(R.id.delete);
        }
    }
}
