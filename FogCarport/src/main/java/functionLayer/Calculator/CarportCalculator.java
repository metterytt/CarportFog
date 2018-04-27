/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer.Calculator;

import functionLayer.BOM;
import functionLayer.BOMexp;

/**
 *
 * @author mette
 */
public interface CarportCalculator {
    
    public BOM calculateBOM();

    public BOMexp getBom();
    
}
