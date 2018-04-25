/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer.Calculator;

import functionLayer.BOM;

/**
 *
 * @author mette
 */
public class FlatNoShedCalculator implements CarportCalculator {
    
    private int length;
    private int width;

    public FlatNoShedCalculator(int length, int width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public BOM calculateBOM() {
        BOM bom = null;
        int posts = calcPosts(length);
        int subFasciaBoards = calcSubFasciaBoards(length, width);
        bom = new BOM(posts);
        return bom;
    }
    
    // ud fra 4 stolper hvis længde mindre end 481, ellers 6
    private int calcPosts(int length) {
        if (length <= 480) {
            return 4;
        } else {
            return 6;
        }
    }
    
    private int calcSubFasciaBoards(int length, int width){
        return 1; //dummy!
        
    }

}
