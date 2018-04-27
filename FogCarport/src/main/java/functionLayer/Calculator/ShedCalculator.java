// Lægte = lath
// Beklædning = cladding
package functionLayer.Calculator;

import dbAccess.Mapper;
import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.entity.Product;

/**
 *
 * @author pernillelorup
 */
public class ShedCalculator implements CarportCalculator {

    private int shedLength;
    private int shedWidth;
    private BOM bom = new BOM();

    public ShedCalculator(int shedLength, int shedWidth) throws CarportException {
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        bom = calculateBOM();
    }

    private BOM calculateBOM() throws CarportException {
        bom = new BOM();

        Product lath = Mapper.getProduct(16);
        lath.setQuantity(420); // altid samme mængde, til Z på dør
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

    private int calcReglar(int shedLength, int shedWidth) {
        return 4 * shedLength + 4 * shedWidth;
    }

    private int calcCladding(int shedLength, int shedWidth) { // pga overlap fylder hvert bræt 7,5 cm
        return ((((shedLength * 100) / 750) + ((shedWidth * 100) / 750)) * 210) * 2; //210 er standard højden.
    }

    @Override
    public BOM getBom() {
        return bom;
    }

}
