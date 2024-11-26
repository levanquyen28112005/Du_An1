package category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien.R;

import java.util.List;

import book.BookAdapter;

public class CatemoryAdapter extends RecyclerView.Adapter<CatemoryAdapter.CatemoryViewHolder> {

    private Context context;
    private List<category> mcategory;

    public CatemoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<category> list) {
        this.mcategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatemoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent, false);
        return new CatemoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatemoryViewHolder holder, int position) {
        category category = mcategory.get(position);
        if (mcategory != null){
            return;
        }

        holder.tvNameCatemory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);

        BookAdapter bookAdapter = new BookAdapter();
        bookAdapter.setData(category.getBooks());
        holder.recyclerView.setAdapter(bookAdapter);
    }

    @Override
    public int getItemCount() {
        if (mcategory != null){
            return mcategory.size();
        }
        return 0;
    }

    public class CatemoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameCatemory;
        private RecyclerView recyclerView;

        public CatemoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCatemory = itemView.findViewById(R.id.text_Title);
            recyclerView = itemView.findViewById(R.id.rev_book);
        }
    }
}
