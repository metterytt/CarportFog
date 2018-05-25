/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer.Calculators;

import functionLayer.CarportException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rasmus
 */
public class PitchedRoofCalculatorTest {
    
    private PitchedRoofCalculator PRC;
    
    @Before
    public void setUp() throws CarportException {
        PRC = new PitchedRoofCalculator(780, 330, 20, 210, 270);
    }

    @Test
    public void testCalcFasciaBoards() {
    }

    @Test
    public void testCalcRafterSetAndPlates() {
    }

    @Test
    public void testCalcWaterBoardsAndGablesCladding() {
    }

    @Test
    public void testCalcPosts() {
    }
}
