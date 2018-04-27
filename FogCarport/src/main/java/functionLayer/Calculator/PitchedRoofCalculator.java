/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer.Calculator;

import functionLayer.BOM;
import functionLayer.CarportException;

/**
 *
 * @author mette
 */
public class PitchedRoofCalculator implements CarportCalculator {

    private int length;
    private int width;
    private BOM bom = new BOM();

    public PitchedRoofCalculator(int length, int width) throws CarportException {
        this.length = length;
        this.width = width;
        bom = calculateBOM();
    }

    private BOM calculateBOM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BOM getBom() {
        return bom;
    }

}
