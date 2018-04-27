// Lægte = lath
// Beklædning = cladding
package functionLayer.Calculator;

import dbAccess.Mapper;
import functionLayer.BOM;
import functionLayer.BOMexp;
import functionLayer.CarportException;
import functionLayer.entity.Product;

/**
 *
 * @author pernillelorup
 */
public class ShedCalculator implements CarportCalculator {

    private int shedLength;
    private int shedWidth;
    private BOMexp bom = new BOMexp();

    public ShedCalculator(int shedLength, int shedWidth) throws CarportException {
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        bom = calculateBOMexp();
    }

    private BOMexp calculateBOMexp() throws CarportException {
        bom = new BOMexp();

        Product lath = Mapper.getProduct(16);
        lath.setQuantity(420);
        lath.setUseInContext("Til Z på bagside af dør");
        bom.addToBOM(lath);

        Product reglar = Mapper.getProduct(17);
        reglar.setQuantity(calcReglar(shedLength, shedWidth));
        reglar.setUseInContext("Løsholter til skur");
        bom.addToBOM(reglar);

        Product cladding = Mapper.getProduct(6);
        cladding.setQuantity(calcCladding(shedLength, shedWidth));
        cladding.setUseInContext("Til beklædning af skur 1 på 2");
        bom.addToBOM(cladding);

        return bom;
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

    @Override
    public BOMexp getBom() {
        return bom;
    }

}
