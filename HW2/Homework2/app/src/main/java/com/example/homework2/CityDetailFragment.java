package com.example.homework2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homework2.content.CityUtils;

public class CityDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public CityUtils.City mCity;

    public CityDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CityDetailFragment newInstance(int selectedCity) {
        CityDetailFragment fragment = new CityDetailFragment();
        Bundle args = new Bundle();
        args.putInt(CityUtils.CITY_ID_KEY, selectedCity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(CityUtils.CITY_ID_KEY)) {
            // Load the content specified by the fragment arguments.
            mCity = CityUtils.CITY_ITEMS.get(getArguments()
                    .getInt(CityUtils.CITY_ID_KEY));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.city_detail, container, false);
        if (mCity != null) {
            ((TextView) rootView.findViewById(R.id.city_detail))
                    .setText(mCity.details);
        }
        return rootView;
    }
}