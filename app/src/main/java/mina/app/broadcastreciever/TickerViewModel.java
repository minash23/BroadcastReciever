package mina.app.broadcastreciever;


import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.LinkedList;

public class TickerViewModel extends ViewModel {

    MutableLiveData<LinkedList<Stock>> tickers;
    private MutableLiveData<Stock> tickerData;
    MutableLiveData<Integer> clicked;

    public LiveData<LinkedList<Stock>> getTickers() {
        if (tickers == null) {
            tickers = new MutableLiveData<>();
            loadTickers();
        }
        return tickers;
    }

    private void loadTickers() {
        LinkedList<Stock> lTickers = new LinkedList<Stock>();
        lTickers.add(new Stock("BAC"));
        lTickers.add(new Stock("AAPL"));
        lTickers.add(new Stock("DIS"));

        tickers.setValue(lTickers);
    }

    public void addTickerData(String name) {

        if (tickerData == null) {
            tickerData = new MutableLiveData<>();
            loadTickers();
        }

        Stock t = new Stock(name);
        LinkedList<Stock> list = tickers.getValue();
        this.tickerData.setValue(t);
        if (list.size() == 5) {
            list.set(4,t);
        } else {
            list.add(t);
        }

        tickers.setValue(list);
        setClicked(tickers.getValue().size()-1);
    }


    public MutableLiveData<Integer> getClicked() {
        if (clicked == null) {
            clicked = new MutableLiveData<>();
        }
        return  clicked;
    }

    public void setClicked(int pos) {
        if (clicked == null) {
            clicked = new MutableLiveData<>();
        }
        clicked.setValue(pos);

    }
}

