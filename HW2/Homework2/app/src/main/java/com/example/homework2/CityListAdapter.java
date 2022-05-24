package com.example.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder>{
    private final LinkedList<String> mCityList;
    private LayoutInflater mInflater;

    public CityListAdapter(Context context, LinkedList<String> cityList) {
        mInflater = LayoutInflater.from(context);
        this.mCityList = cityList;
    }

    @NonNull
    @Override
    public CityListAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.citylist_item, parent, false);
        return new CityViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CityListAdapter.CityViewHolder holder, int position) {
        String mCurrent = mCityList.get(position);
        holder.cityItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        public final TextView cityItemView;
        final CityListAdapter mAdapter;

        public CityViewHolder(View itemView, CityListAdapter adapter) {
            super(itemView);
            cityItemView = itemView.findViewById(R.id.city);
            this.mAdapter = adapter;
        }


    }
}
