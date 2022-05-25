package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homework2.content.CityUtils;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mCityList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private CityListAdapter mAdapter;
    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        // Set the toolbar as the app bar.
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(getTitle());

        // Get the song list as a RecyclerView.
        System.out.println("DEBUG1"+findViewById(R.id.city_list).toString());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.city_list);
        System.out.println("DEBUG2:"+recyclerView.toString());
        recyclerView.setAdapter
                (new SimpleItemRecyclerViewAdapter(CityUtils.CITY_ITEMS));
        if (findViewById(R.id.city_detail_container) != null) {
            mTwoPane = true;
        }

//        mCityList.add("Aveiro");
//        mCityList.add("Porto");
//        mCityList.add("Leiria");
//        mCityList.add("Braga");
//        mCityList.add("Viana do Castelo");
//        mCityList.add("Santarem");
//        mCityList.add("Guarda");
//
//        // Get a handle to the RecyclerView.
//        mRecyclerView = findViewById(R.id.recyclerview);
//        // Create an adapter and supply the data to be displayed.
//        mAdapter = new CityListAdapter(this, mCityList);
//        // Connect the adapter with the RecyclerView.
//        mRecyclerView.setAdapter(mAdapter);
//        // Give the RecyclerView a default layout manager.
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter
            <SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<CityUtils.City> mValues;

        SimpleItemRecyclerViewAdapter(List<CityUtils.City> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.city_list_content, parent, false);
            return new ViewHolder(view);
        }

        /**
         * This method implements a listener with setOnClickListener().
         * When the user taps a song title, the code checks if mTwoPane
         * is true, and if so uses a fragment to show the song detail.
         * If mTwoPane is not true, it starts SongDetailActivity
         * using an intent with extra data about which song title was selected.
         *
         * @param holder   ViewHolder
         * @param position Position of the song in the array.
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(String.valueOf(position + 1));
            holder.mContentView.setText(mValues.get(position).city_name);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        int selectedSong = holder.getAdapterPosition();
                        CityDetailFragment fragment =
                                CityDetailFragment.newInstance(selectedSong);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.city_detail_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, CityDetailActivity.class);
                        intent.putExtra(CityUtils.CITY_ID_KEY,
                                holder.getAdapterPosition());
                        context.startActivity(intent);
                    }
                }
            });
        }

        /**
         * Get the count of song list items.
         * @return
         */
        @Override
        public int getItemCount() {
            return mValues.size();
        }

        /**
         * ViewHolder describes an item view and metadata about its place
         * within the RecyclerView.
         */
        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            CityUtils.City mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}