package my.caliappdisplay;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends FirestoreRecyclerAdapter<News, NewsAdapter.NewsHolder> {
    public NewsAdapter(@NonNull FirestoreRecyclerOptions<News> options) {
        super(options);
    }

    private boolean showAllViews = false;

    public void setShowAllViews(boolean show) {
        showAllViews = show;
        notifyItemRangeChanged(0, getItemCount());
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsHolder holder, int position, @NonNull News model) {
        holder.textViewTitle.setText("Article Title: " + model.getTitle());
        holder.textViewDescription.setText("Description: " + model.getDescription());
        holder.textViewAuthor.setText("Author: " + model.getAuthor());
        holder.textViewURL.setText("URL: " + model.geturl());
        Picasso.get().load(model.getUrlToImage()).fit().centerInside().into(holder.imageViewurlToImage);
        //Picasso.with(mContext).load(model.getUrlToImage()).fit().centerInside().into(holder.imageViewurlToImage);
    }
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsHolder(v);
    }
    class NewsHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewAuthor;
        TextView textViewURL;
        ImageView imageViewurlToImage;

        public NewsHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewAuthor = itemView.findViewById(R.id.text_view_author);
            textViewURL = itemView.findViewById(R.id.text_view_url);
            imageViewurlToImage = itemView.findViewById(R.id.image_view_urlToImage);
        }
    }
}

