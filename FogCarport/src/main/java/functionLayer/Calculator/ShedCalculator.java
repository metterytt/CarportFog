package functionLayer.Calculator;

import functionLayer.BOM;

/**
 *
 * @author pernillelorup
 */
public class ShedCalculator implements CarportCalculator {

    
    private int shedLength;
    private int shedWidth;

    public ShedCalculator(int shedLength, int shedWidth) {
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }
    
    
    @Override
    public BOM calculateBOM() {
        int lath = 420;
        int reglar = calcReglar(shedLength, shedWidth);
        int cladding = calcCladding(shedLength, shedWidth);
        
        BOM bom = new BOM(lath, reglar, cladding);
        return bom;
    }

    private int calcReglar(int shedLength, int shedWidth) {
        return 4 * shedLength + 4 * shedWidth;
    }

    private int calcCladding(int shedLength, int shedWidth) {
        return ((((shedLength * 100) / 750) + ((shedWidth * 100) / 750)) * 210) * 2; //210 er standard højden.
    }
    
}
