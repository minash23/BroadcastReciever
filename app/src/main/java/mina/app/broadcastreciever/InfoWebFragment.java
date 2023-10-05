package mina.app.broadcastreciever;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;

public class InfoWebFragment extends Fragment {


    public static WebView webView;
    public static WebSettings websettings;
    TickerViewModel model;
    public InfoWebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_web, container, false);
        model = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);

        webView = view.findViewById(R.id.web_view);
        webView.loadUrl("https://seekingalpha.com");
        websettings = webView.getSettings();
        websettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        return view;
    }



    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);

        model.getClicked().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer clicked) {
                if (model.getClicked() != null) {
                    LinkedList<Stock> tickers = model.getTickers().getValue();
                    int pos = model.getClicked().getValue();
                    webView.loadUrl("https://seekingalpha.com/symbol/" + tickers.get(pos).getTicker());
                }
            }
        });


    }
}