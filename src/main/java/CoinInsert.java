import java.lang.reflect.Array;
import java.util.ArrayList;

public class CoinInsert {

    private ArrayList<Coin> insertedCoins;

    public CoinInsert() {
        this.insertedCoins = new ArrayList<Coin>();
    }

    public ArrayList<Coin> getInsertedCoins() {
        return insertedCoins;
    }

    public void addValidCoin(Coin coin) {
            this.insertedCoins.add(coin);
    }

    public void clearCoins(){
        this.insertedCoins.clear();
    }

    public double calculateTotal(){
        double total = 0;
        for (Coin coin : insertedCoins) {
            total += coin.getValue();
        }
        return total;
    }
}
