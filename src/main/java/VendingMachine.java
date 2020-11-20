import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine {

    private HashMap<String,ArrayList<Product>> inventory;
    private CoinInsert coinInsert;
    private CoinFloat coinFloat;
    private ArrayList<Coin> coinReturn;
    private ArrayList<Coin> coinStorage;
    private ArrayList<Product> drawer;
    private String display;

    public VendingMachine() {
        this.inventory = new HashMap<String, ArrayList<Product>>();
        this.coinInsert = new CoinInsert();
        this.coinFloat = new CoinFloat();
        this.coinReturn = new ArrayList<Coin>();
        this.coinStorage = new ArrayList<Coin>();
        this.drawer = new ArrayList<Product>();
        this.display = "";
    }

    public String getDisplay() {
        return display;
    }

    public HashMap<String, ArrayList<Product>> getInventory() {
        return inventory;
    }

    public void addProduct(String code, Product product) {
        if (inventory.containsKey(code)) {
            inventory.get(code).add(product);
        } else {
            ArrayList<Product> newArray = new ArrayList<Product>();
            newArray.add(product);
            inventory.put(code,newArray);
        }
    }

    public Product removeProduct(String code){
        if (inventory.get(code).size() > 0) {
           return inventory.get(code).remove(0);
        }
        return null;
    }

    public double getProductPrice(String code){
        return inventory.get(code).get(0).getPrice();
    }

    public CoinInsert getCoinInsert() {
        return coinInsert;
    }

    public CoinFloat getCoinFloat() {
        return coinFloat;
    }

    public ArrayList<Coin> getCoinReturn() {
        return coinReturn;
    }

    public ArrayList<Coin> getCoinStorage() {
        return coinStorage;
    }

    public ArrayList<Product> getDrawer() {
        return drawer;
    }

    public void takeCoin(Coin coin){
        if (coin.getValue() >= 0.05) {
            coinInsert.addValidCoin(coin);
        } else {
            this.coinReturn.add(coin);
        }
    }

    public void dispenseProduct(Product product) {
        this.drawer.add(product);
    }


    public void displayCoinsToAdd(double amount){
        this.display = "Please insert £" + amount + " to buy";
    }


    public void buyProduct(String code, ArrayList<Coin> coinArray) {
        for (Coin coin : coinArray) {
            takeCoin(coin);
        }

        if(coinInsert.calculateTotal() >= getProductPrice(code)) {

            dispenseProduct(removeProduct(code));
            coinStorage.addAll(coinInsert.getInsertedCoins());
            coinInsert.clearCoins();
        } else {
            double amountToAdd = getProductPrice(code) - coinInsert.calculateTotal();
            displayCoinsToAdd(amountToAdd);
        }

    }


}
