/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer.Calculator;

import dbAccess.Mapper;
import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.entity.LineItem;

/**
 *
 * @author mette
 */
public class PitchedRoofCalculator implements CarportCalculator {

    private int length;
    private int width;
    private int angle;
    private BOM bom = new BOM();

    public PitchedRoofCalculator(int length, int width, int angle) throws CarportException {
        this.length = length;
        this.width = width;
        this.angle = angle;
        bom = calculateBOM();
    }

    private BOM calculateBOM() throws CarportException {
        bom = new BOM();
        
        LineItem fasciaPitch = Mapper.getProduct(18);
        fasciaPitch.setQuantity(calcFasciaPitch(width, angle));
        
        return bom;
    }

    @Override
    public BOM getBom() {
        return bom;
    }

    private int calcFasciaPitch(int width, int angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
