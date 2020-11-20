import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VendingMachineTest {

    private VendingMachine vendingMachine;
    private Product product1;
    private Product product2;
    private Product product3;
    private Coin coin1;
    private Coin coin2;
    private Coin coin3;

    @Before
    public void before(){
        coin1 = Coin.ONEPENCE;
        coin2 = Coin.TWOPENCE;
        coin3 = Coin.ONEPOUND;
        product1 = new Product("Coke",1.00);
        product2 = new Product("Crisps",0.5);
        product3 = new Product("Sweets",0.65);
        vendingMachine = new VendingMachine();
        vendingMachine.addProduct("A1",product1);
        vendingMachine.addProduct("B2",product2);
    }

    @Test
    public void canAddProductToMachine(){
        vendingMachine.addProduct("C3",product3);
        assertEquals(3,vendingMachine.getInventory().size());
    }

    @Test
    public void canAddMultipleProductsToACode(){
        vendingMachine.addProduct("A1",product1);
        assertEquals(2,vendingMachine.getInventory().get("A1").size());
    }

    @Test
    public void canInsertValidCoin(){
        vendingMachine.takeCoin(coin3);
        assertEquals(1,vendingMachine.getCoinInsert().getInsertedCoins().size());
    }

    @Test
    public void cannotInsertInvalidCoin(){
        vendingMachine.takeCoin(coin1);
        vendingMachine.takeCoin(coin2);
        assertEquals(0,vendingMachine.getCoinInsert().getInsertedCoins().size());
        assertEquals(2,vendingMachine.getCoinReturn().size());
    }

    @Test
    public void canGetProductPrice(){
        assertEquals(1.00,vendingMachine.getProductPrice("A1"),0.01);
    }

    @Test
    public void canRemoveProduct(){
        assertEquals(product1,vendingMachine.removeProduct("A1"));
    }

    @Test
    public void cantRemoveEmptyProduct() {
        vendingMachine.removeProduct("A1");
        assertNull(vendingMachine.removeProduct("A1"));
    }

    @Test
    public void canDispenseProduct(){
        vendingMachine.dispenseProduct(product1);
        assertEquals(1,vendingMachine.getDrawer().size());
    }

    @Test
    public void canDisplayRemainder(){
        vendingMachine.displayCoinsToAdd(1.50);
        assertEquals("Please insert £1.5 to buy", vendingMachine.getDisplay());
    }

    @Test
    public void canBuyProductWithEnoughMoney(){
        ArrayList<Coin> coinArray = new ArrayList<Coin>();
        coinArray.add(coin3);
        vendingMachine.buyProduct("A1",coinArray);
        assertEquals(1,vendingMachine.getDrawer().size());
        assertEquals(0, vendingMachine.getCoinInsert().getInsertedCoins().size());
        assertEquals(1,vendingMachine.getCoinStorage().size());
        assertEquals(0,vendingMachine.getInventory().get("A1").size());
    }


}
