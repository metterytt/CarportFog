/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculators;

import functionLayer.BOM;

/**
 *
 * @author mette
 */
public class FlatNoShedCalculator implements CarportCalculator {

    @Override
    public BOM calculateBOM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // ud fra 4 stolper hvis længde mindre end 481, ellers 6
    private int posts(int length) {
        if (length <= 480) {
            return 4;
        } else {
            return 6;
        }
    }
    
//    private int 

}
