package mina.app.broadcastreciever;

public class Stock {
    String ticker;
    String URL;
    public Stock(){

    }

    public Stock(String ticker){
        this.ticker = ticker;
    }

    public String getURL() {
        return URL;
    }

    public void setURL() {
        this.URL = "https://seekingalpha.com/symbol/" + ticker;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "This Stock is: " + ticker;
    }
}
