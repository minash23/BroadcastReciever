package mina.app.broadcastreciever;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.LinkedList;
import java.util.Objects;

public class TickerListFragment extends Fragment {

    ListView listView;
    TickerViewModel model;
    SharedPreferences pref;

    public TickerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        model = new ViewModelProvider(getActivity()).get(TickerViewModel.class);
        model.getTickers().observe(getViewLifecycleOwner(), new Observer<LinkedList<Stock>>() {
            @Override
            public void onChanged(LinkedList<Stock> tickers) {
                ArrayAdapter<Stock> adapter =  new ArrayAdapter<Stock>(getActivity(), android.R.layout.simple_list_item_1, model.getTickers().getValue());
                listView.setAdapter(adapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                model.setClicked(i);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentRoot = inflater.inflate(R.layout.fragment_ticker_list, container, false);

        listView = fragmentRoot.findViewById(R.id.ticker_list);

        return fragmentRoot;
    }
}