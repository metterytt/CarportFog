package functionLayer.Calculators;

import functionLayer.CarportException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PitchedRoofCalculatorTest {

    private PitchedRoofCalculator PRC;

    @Before
    public void setUp() throws CarportException {
        PRC = new PitchedRoofCalculator(780, 330, 20, 210, 270);
    }

    @Test
    public void testCalcFasciaBoards() {
        double expected = 22.62;
        double actual = PRC.getBom().getListOfProducts().get(0).getQuantity();
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testCalcRafterSetAndPlates() throws CarportException {
        double expected = 119.37;
        double actual = PRC.getBom().getListOfProducts().get(1).getQuantity();
        assertEquals(expected, actual, 0.01);

        PRC = new PitchedRoofCalculator(750, 330, 20, 210, 270);
        expected = 118.77;
        actual = PRC.getBom().getListOfProducts().get(1).getQuantity();
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testCalcWaterBoardsAndGablesCladding() {
        double expected = 33.45;
        double actual = PRC.getBom().getListOfProducts().get(3).getQuantity();
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testCalcPosts() throws CarportException {
        int expected = 6;
        double actual = PRC.getBom().getListOfProducts().get(2).getQuantity();
        assertEquals(expected, actual, 0.0);

        PRC = new PitchedRoofCalculator(480, 330, 20, 210, 270);
        expected = 4;
        actual = PRC.getBom().getListOfProducts().get(2).getQuantity();
        assertEquals(expected, actual, 0.0);
    }
}
