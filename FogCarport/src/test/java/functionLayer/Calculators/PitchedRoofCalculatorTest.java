/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer.Calculators;

import functionLayer.CarportException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mette
 */
public class PitchedRoofCalculatorTest {
    
    public PitchedRoofCalculatorTest() {
    }

    @Test
    public void testGetBom() throws CarportException {
        PitchedRoofCalculator cp = new PitchedRoofCalculator(270, 270, 10);
        cp.calcWaterBoardsAndGablesCladding();
    }
    
}
