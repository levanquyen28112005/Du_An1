package book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private List<Book> mBook;

    public void setData(List<Book> list) {
        this.mBook = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_dmsp, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = mBook.get(position);
        if (book == null) {
            return;
        }

        holder.img.setImageResource(book.getResourceID());
        holder.title.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        if (mBook != null) {
            return mBook.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView title;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_book);
            title = itemView.findViewById(R.id.title);
        }
    }
}
