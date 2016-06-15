package com.example.matt.movieWatchList.viewControllers.fragments.shows;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matt.movieWatchList.Models.POJO.Cast;
import com.example.matt.movieWatchList.Models.POJO.Credits;
import com.example.matt.movieWatchList.Models.POJO.Crew;
import com.example.matt.movieWatchList.Models.POJO.movies.Movie;
import com.example.matt.movieWatchList.Models.POJO.movies.MovieResult;
import com.example.matt.movieWatchList.Models.POJO.shows.TVShow;
import com.example.matt.movieWatchList.Models.Realm.JSONCast;
import com.example.matt.movieWatchList.Models.Realm.JSONShow;
import com.example.matt.movieWatchList.R;
import com.example.matt.movieWatchList.uitls.API.MovieAPI;
import com.example.matt.movieWatchList.uitls.API.TVShowAPI;
import com.example.matt.movieWatchList.uitls.PaletteTransformation;
import com.example.matt.movieWatchList.viewControllers.adapters.CastAdapter;
import com.example.matt.movieWatchList.viewControllers.adapters.CustomExpandableListAdapter;
import com.example.matt.movieWatchList.viewControllers.adapters.SearchAdapter;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Matt on 6/14/2016.
 */
public class TVShowOverviewFragment extends Fragment {
    //ThingsAdapter adapter;
    FragmentActivity listener;
    int showID;
    private JSONShow realmShow;
    private TVShow show;
    private RealmList<JSONCast> castList = new RealmList<>();
    private RecyclerView castRecyclerView;
    private CastAdapter castAdapter;

    private RealmList<JSONCast> crewList = new RealmList<>();
    private RecyclerView crewRecyclerView;
    private CastAdapter crewAdapter;

    private static final int NUMBER_OF_CREW_TO_DISPLAY = 3;

    @BindView(R.id.scroll_view)
    NestedScrollView scroll_view;

    @BindView(R.id.rating)
    RatingBar stars;

    @BindView(R.id.plot_title)
    TextView plotTitle;

    @BindView(R.id.cast_title)
    TextView castTitle;

    @BindView(R.id.crew_title)
    TextView crewTitle;

    @BindView(R.id.overview_title)
    TextView overviewTitle;

    @BindView(R.id.runtime)
    TextView runtime;

    @BindView(R.id.user_rating)
    TextView userRating;

    @BindView(R.id.more_info)
    LinearLayout layout;

    @BindView(R.id.expand_text_view)
    ExpandableTextView plot;


    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showID = getArguments().getInt("tvShowID",0);


        /*ArrayList<Thing> things = new ArrayList<Thing>();
        adapter = new ThingsAdapter(getActivity(), things);*/
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_test, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
    }

    // This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/tv/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TVShowAPI service = retrofit.create(TVShowAPI.class);
        Log.d("Intent", Integer.toString(showID));

        Call<TVShow> call = service.getTVShow(Integer.toString(showID));

        call.enqueue(new Callback<TVShow>() {
            @Override
            public void onResponse(retrofit.Response<TVShow> response, Retrofit retrofit) {
                Log.d("getMovie()", "Callback Success");
                show = response.body();
                //show.setBackdropPath("https://image.tmdb.org/t/p/w500//" + show.getBackdropPath());
                realmShow = show.convertToRealm();

                updateUI();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("getMovie()", "Callback Failure");
            }
        });
    }

    private void updateUI(){
        /*Picasso.with(getContext())
                .load(show.getBackdropPath())
                .fit().centerCrop()
                .transform(PaletteTransformation.instance())
                .into(backdrop, new PaletteTransformation.PaletteCallback(backdrop) {
                    @Override public void onSuccess(Palette palette) {
                        Bitmap bitmap = ((BitmapDrawable) backdrop.getDrawable()).getBitmap(); // Ew!

                        int defaultColor = 0x000000;
                        int vibrantColor = palette.getVibrantColor(defaultColor);

                        if (vibrantColor != 0){
                            plotTitle.setTextColor(vibrantColor);
                            castTitle.setTextColor(vibrantColor);
                            crewTitle.setTextColor(vibrantColor);
                            overviewTitle.setTextColor(vibrantColor);
                        } else {
                            Log.d("Palette", "Could not gather vibrant color");
                        }

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                        addByteArray(stream.toByteArray());
                    }

                    @Override
                    public void onError() {
                        //TODO
                    }
                });*/


        plot.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {

            }
        });

        plot.setText(show.getOverview());
        stars.setRating(show.getVoteAverage().floatValue());
        //runtime.setText(Integer.toString(show.getNumberOfSeasons()) + " seasons");
        userRating.setText(Double.toString(show.getVoteAverage())+ "/10");
        
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/tv/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TVShowAPI service = retrofit.create(TVShowAPI.class);
        Call<Credits> call = service.getCredits(Integer.toString(showID));

        call.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(retrofit.Response<Credits> response, Retrofit retrofit) {
                Log.d("GetCredits()", "Callback Success");
                List<Cast> cast = response.body().getCast();
                List<Crew> crew = response.body().getCrew();

                RealmList<JSONCast> realmCast = new RealmList<>();
                for( int i = 0; i <= 3; i++) {
                    realmCast.add(cast.get(i).convertToRealm());
                }

                RealmList<JSONCast> realmCrew = new RealmList<>();
                for( int i = 0; i <= 3; i++) {
                    realmCrew.add(crew.get(i).convertToRealm());
                }

                realmShow.setCrew(realmCrew);
                realmShow.setCast(realmCast);

                // Populate cast and crew recycler views
                castRecyclerView.setAdapter( new CastAdapter(realmShow.getCast(), getContext(), NUMBER_OF_CREW_TO_DISPLAY));
                crewRecyclerView.setAdapter( new CastAdapter(realmShow.getCrew(), getContext(), NUMBER_OF_CREW_TO_DISPLAY));
                castRecyclerView.setFocusable(false);
                crewRecyclerView.setFocusable(false);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("GetCredits()", "Callback Failure");
            }
        });*/
    }

    private void addByteArray(byte[] image) {
        realmShow.setBackdropBitmap(image);
    }

}

