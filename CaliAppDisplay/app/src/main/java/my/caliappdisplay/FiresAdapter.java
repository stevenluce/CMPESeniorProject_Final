package my.caliappdisplay;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FiresAdapter extends FirestoreRecyclerAdapter<Fires, FiresAdapter.FiresHolder> {
    public FiresAdapter(@NonNull FirestoreRecyclerOptions<Fires> options) {
        super(options);
    }

    private boolean showAllViews = false;

    public void setShowAllViews(boolean show) {
        showAllViews = show;
        notifyItemRangeChanged(0, getItemCount());
    }

    @Override
    protected void onBindViewHolder(@NonNull FiresHolder holder, int position, @NonNull Fires model) {
        holder.textViewName.setText(model.getName());
        holder.textViewLocation.setText("Location: " + model.getLocation());
        holder.textViewAdminUnit.setText("Assigned Unit: " + model.getAdminUnit());
    }
    @NonNull
    @Override
    public FiresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fire_item, parent, false);
        return new FiresHolder(v);
    }
    class FiresHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewLocation;
        TextView textViewAdminUnit;
        //TextView textViewPriority;
        public FiresHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewLocation = itemView.findViewById(R.id.text_view_location);
            textViewAdminUnit = itemView.findViewById(R.id.text_view_adminunit);
        }
    }
}
