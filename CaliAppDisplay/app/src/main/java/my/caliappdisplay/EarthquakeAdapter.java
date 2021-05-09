package my.caliappdisplay;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class EarthquakeAdapter extends FirestoreRecyclerAdapter<Earthquake, EarthquakeAdapter.EarthquakeHolder> {
    public EarthquakeAdapter(@NonNull FirestoreRecyclerOptions<Earthquake> options) { super(options); }

    private boolean showAllViews = false;

    public void setShowAllViews(boolean show) {
        showAllViews = show;
        notifyItemRangeChanged(0, getItemCount());
    }

    @Override
    protected void onBindViewHolder(@NonNull EarthquakeHolder holder, int position, @NonNull Earthquake model) {
        holder.textViewmMagnitude.setText("Magnitude: " + String.valueOf(model.getMag()));
        holder.textViewLocation.setText("Location: " + model.getPlace());
    }



    @NonNull
    @Override
    public EarthquakeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eq_item, parent, false);
        return new EarthquakeHolder(v);
    }
    class EarthquakeHolder extends RecyclerView.ViewHolder {
        TextView textViewmMagnitude;
        TextView textViewLocation;

        public EarthquakeHolder(View itemView) {
            super(itemView);
            textViewmMagnitude = itemView.findViewById(R.id.text_view_magnitude);
            textViewLocation = itemView.findViewById(R.id.text_view_location);
        }
    }
}
