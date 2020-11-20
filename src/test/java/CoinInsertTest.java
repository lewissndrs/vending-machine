import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinInsertTest {

    private CoinInsert coinInsert;
    private Coin coin1;
    private Coin coin2;

    @Before
    public void before(){
        coin1 = Coin.TWENTYPENCE;
        coin2 = Coin.ONEPOUND;
        coinInsert = new CoinInsert();
    }

    @Test
    public void canAddCoin(){
        coinInsert.addValidCoin(coin2);
        assertEquals(1,coinInsert.getInsertedCoins().size());
    }

    @Test
    public void canGetTotalValue(){
        coinInsert.addValidCoin(coin2);
        coinInsert.addValidCoin(coin1);
        assertEquals(1.2,coinInsert.calculateTotal(),0.01);
    }
    
    @Test
    public void canClearCoins(){
        coinInsert.addValidCoin(coin1);
        coinInsert.clearCoins();
        assertEquals(0,coinInsert.getInsertedCoins().size());
    }
}
