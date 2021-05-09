package my.caliappdisplay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference newsRef = db.collection("news");
    //private CollectionReference notebookRef = db.collection("notebook");
    private CollectionReference earthquakeRef = db.collection("earthquakes");
    private CollectionReference firesRef = db.collection("fires");
    private NewsAdapter newsadapter;
    private NoteAdapter noteadapter;
    private EarthquakeAdapter earthquakeadapter;
    private FiresAdapter firesadapter;
    boolean filterHidden = true;
    private Button filterButton;
    private LinearLayout filterView1;
    private Button newsButton, fireButton, earthquakeButton, allButton;
    private int white, darkGray, red;

    private ArrayList<String> selectedFilters = new ArrayList<String>();

    ConcatAdapter concatAdapter = new ConcatAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        setUpRecyclerView();
        hideFilter();
        initColors();
        lookSelected(allButton);
        //selectedFilters.add("all");
    }
    private void setUpRecyclerView() {
        Query query1 = newsRef;
        Query query3 = earthquakeRef;
        Query query4 = firesRef;

        FirestoreRecyclerOptions<News> options1 = new FirestoreRecyclerOptions.Builder<News>().setQuery(query1, News.class).build();
        FirestoreRecyclerOptions<Earthquake> options3 = new FirestoreRecyclerOptions.Builder<Earthquake>().setQuery(query3, Earthquake.class).build();
        FirestoreRecyclerOptions<Fires> options4 = new FirestoreRecyclerOptions.Builder<Fires>().setQuery(query4, Fires.class).build();

        newsadapter = new NewsAdapter(options1);
        earthquakeadapter = new EarthquakeAdapter(options3);
        firesadapter = new FiresAdapter(options4);

        ConcatAdapter concatenated = new ConcatAdapter(earthquakeadapter, firesadapter, newsadapter);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(concatenated);
    }
    @Override
    protected void onStart() {
        super.onStart();
        newsadapter.startListening();
        earthquakeadapter.startListening();
        firesadapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        newsadapter.stopListening();
        earthquakeadapter.stopListening();
        firesadapter.stopListening();
    }

    public void showFilterTapped(View view)
    {
        if(filterHidden == true)
        {
            filterHidden = false;
            showFilter();
        }
        else
        {
            filterHidden = true;
            hideFilter();
        }
    }

    private void initWidgets()
    {
        filterButton = (Button) findViewById(R.id.filterButton);
        filterView1 = (LinearLayout) findViewById(R.id.filterTabsLayout);

        newsButton = (Button) findViewById(R.id.newsFilter);
        fireButton = (Button) findViewById(R.id.fireFilter);
        earthquakeButton  = (Button) findViewById(R.id.earthquakeFilter);
        allButton  = (Button) findViewById(R.id.allFilter);

    }

    private void showFilter()
    {
        filterView1.setVisibility(View.VISIBLE);
        filterButton.setText("HIDE");
    }

    private void hideFilter()
    {
        filterView1.setVisibility(View.GONE);
        filterButton.setText("FILTER");
    }

    public void allFilterTapped(View view)
    {
        selectedFilters.clear();
        //selectedFilters.add("all");

        unSelectAllFilterButtons();
        lookSelected(allButton);
        concatAdapter = new ConcatAdapter();

        earthquakeadapter.setShowAllViews(true);
        newsadapter.setShowAllViews(true);
        firesadapter.setShowAllViews(true);
        RecyclerView recyclerViewAll = findViewById(R.id.recycler_view);
        ConcatAdapter concatAdapterAll = new ConcatAdapter(earthquakeadapter, firesadapter, newsadapter);
        recyclerViewAll.setAdapter(concatAdapterAll);
    }

    public void earthquakeFilterTapped(View view)
    {
        if (earthquakeButton.getCurrentTextColor() == red) {
            filterList("earthquake", "add");
            lookSelected(earthquakeButton);
            if (earthquakeButton.getCurrentTextColor() == white && fireButton.getCurrentTextColor() == white && newsButton.getCurrentTextColor() == white) {
                lookSelected(allButton);
                lookUnSelected(earthquakeButton);
                lookUnSelected(newsButton);
                lookUnSelected(fireButton);
            } else {
                lookUnSelected(allButton);
            }
        }else if (earthquakeButton.getCurrentTextColor() == white){
            filterList("earthquake", "remove");
            lookUnSelected(earthquakeButton);
            //if e n f red then all is selected
            if (earthquakeButton.getCurrentTextColor() == red && fireButton.getCurrentTextColor() == red && newsButton.getCurrentTextColor() == red){
                lookSelected(allButton);
            } else {
                lookUnSelected(allButton);
            }
        }
    }

    public void fireFilterTapped(View view)
    {
        if(fireButton.getCurrentTextColor() == red) {
            filterList("fire", "add");
            lookSelected(fireButton);
            if (earthquakeButton.getCurrentTextColor() == white && fireButton.getCurrentTextColor() == white && newsButton.getCurrentTextColor() == white) {
                lookSelected(allButton);
                lookUnSelected(earthquakeButton);
                lookUnSelected(newsButton);
                lookUnSelected(fireButton);


            } else {
                lookUnSelected(allButton);
            }
        } else if (fireButton.getCurrentTextColor() == white){
            filterList("fire", "remove");
            lookUnSelected(fireButton);
            if (earthquakeButton.getCurrentTextColor() == red && fireButton.getCurrentTextColor() == red && newsButton.getCurrentTextColor() == red){
                lookSelected(allButton);
            } else {
                lookUnSelected(allButton);
            }
        }
    }

    public void newsFilterTapped(View view)
    {
        if(newsButton.getCurrentTextColor() == red){
            filterList("news", "add");
            lookSelected(newsButton);
            if (earthquakeButton.getCurrentTextColor() == white && fireButton.getCurrentTextColor() == white && newsButton.getCurrentTextColor() == white) {
                lookSelected(allButton);
                lookUnSelected(earthquakeButton);
                lookUnSelected(newsButton);
                lookUnSelected(fireButton);

            } else {
                lookUnSelected(allButton);
            }
        }else if (newsButton.getCurrentTextColor() == white){
            filterList("news", "remove");
            lookUnSelected(newsButton);
            if (earthquakeButton.getCurrentTextColor() == red && fireButton.getCurrentTextColor() == red && newsButton.getCurrentTextColor() == red){
                lookSelected(allButton);
            } else {
                lookUnSelected(allButton);
            }
        }
    }

    private void unSelectAllFilterButtons()
    {
        lookUnSelected(allButton);
        lookUnSelected(earthquakeButton);
        lookUnSelected(fireButton);
        lookUnSelected(newsButton);

    }

    private void lookSelected(Button parsedButton)
    {
        parsedButton.setTextColor(white);
        parsedButton.setBackgroundColor(red);
    }

    private void lookUnSelected(Button parsedButton)
    {
        parsedButton.setTextColor(red);
        parsedButton.setBackgroundColor(darkGray);
    }

    private void initColors()
    {
        white = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        red = ContextCompat.getColor(getApplicationContext(), R.color.red);
        darkGray = ContextCompat.getColor(getApplicationContext(), R.color.darkerGray);
    }

    private void filterList(String status, String addOrRemove) {
        if (addOrRemove == "add") {
            if (status != null && !selectedFilters.contains(status))
                selectedFilters.add(status);
            for (String filter : selectedFilters) {
                if (filter == "earthquake") {
                    earthquakeadapter.setShowAllViews(true);
                    concatAdapter.addAdapter(earthquakeadapter);
                }
                if (filter == "fire") {
                    firesadapter.setShowAllViews(true);
                    concatAdapter.addAdapter(firesadapter);
                }
                if (filter == "news") {
                    newsadapter.setShowAllViews(true);
                    concatAdapter.addAdapter(newsadapter);
                }
            }
            if (selectedFilters.contains("earthquake") && selectedFilters.contains("fire") && selectedFilters.contains("news")){
                //selectedFilters.add("all");
                //unSelectAllFilterButtons();
                selectedFilters.clear();
                concatAdapter = new ConcatAdapter();

                earthquakeadapter.setShowAllViews(true);
                newsadapter.setShowAllViews(true);
                firesadapter.setShowAllViews(true);
                RecyclerView recyclerViewAll = findViewById(R.id.recycler_view);
                ConcatAdapter concatHere = new ConcatAdapter(earthquakeadapter, firesadapter, newsadapter);
                recyclerViewAll.setAdapter(concatHere);

            }else {
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setAdapter(concatAdapter);
            }
        } else if (addOrRemove == "remove") {
            if (status == "earthquake") {
                selectedFilters.remove("earthquake");
                earthquakeadapter.setShowAllViews(false);
                concatAdapter.removeAdapter(earthquakeadapter);
            }
            if (status == "fire") {
                selectedFilters.remove("fire");
                firesadapter.setShowAllViews(false);
                concatAdapter.removeAdapter(firesadapter);
            }
            if (status == "news") {
                selectedFilters.remove("news");
                newsadapter.setShowAllViews(false);
                concatAdapter.removeAdapter(newsadapter);

            }
            if (!selectedFilters.contains("news") && !selectedFilters.contains("earthquake") && !selectedFilters.contains("fire")){
                selectedFilters.clear();
                //selectedFilters.add("all");

                unSelectAllFilterButtons();
                concatAdapter = new ConcatAdapter();

                earthquakeadapter.setShowAllViews(true);
                newsadapter.setShowAllViews(true);
                firesadapter.setShowAllViews(true);
                RecyclerView recyclerViewAll = findViewById(R.id.recycler_view);
                ConcatAdapter concatHere = new ConcatAdapter(earthquakeadapter, firesadapter, newsadapter);
                recyclerViewAll.setAdapter(concatHere);
            } else {
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setAdapter(concatAdapter);
            }
        }

    }

}