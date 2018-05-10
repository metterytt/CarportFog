// Lægte = lath
// Beklædning = cladding
package functionLayer.Calculators;

import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.LineItem;

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
        bom.setShedLength(shedLength);
        bom.setShedWidth(shedWidth);

        LineItem lath = StorageFacade.getProduct(16);
        lath.setQuantity(4.20); // altid samme mængde, til Z på dør
        lath.setUseInContext("Til Z på bagside af dør");
        bom.addToBOM(lath);

        LineItem reglar = StorageFacade.getProduct(17);
        reglar.setQuantity(calcReglar(shedLength, shedWidth));
        reglar.setUseInContext("Løsholter til skur");
        bom.addToBOM(reglar);

        LineItem cladding = StorageFacade.getProduct(6);
        cladding.setQuantity(calcCladding(shedLength, shedWidth));
        cladding.setUseInContext("Til beklædning af skur 1 på 2");
        bom.addToBOM(cladding);

        LineItem shedScrews = StorageFacade.getProduct(27);
        shedScrews.setQuantity(calcShedScrews(shedLength, shedWidth));
        shedScrews.setUseInContext("Til montering af yderste beklædning");
        bom.addToBOM(shedScrews);

        LineItem smallShedScrews = StorageFacade.getProduct(28);
        smallShedScrews.setQuantity(calcSmallShedScrews(shedLength, shedWidth));
        smallShedScrews.setUseInContext("Til montering af inderste beklædning");
        bom.addToBOM(smallShedScrews);

        LineItem doorKnob = StorageFacade.getProduct(29);
        doorKnob.setQuantity(1); // altid 1
        doorKnob.setUseInContext("Til lås på dør i skur");
        bom.addToBOM(doorKnob);

        LineItem tHinge = StorageFacade.getProduct(30);
        tHinge.setQuantity(2); // altid 2
        tHinge.setUseInContext("Til skurdør");
        bom.addToBOM(tHinge);

        LineItem angleBrackets = StorageFacade.getProduct(31);
        angleBrackets.setQuantity(20); // altid 20
        angleBrackets.setUseInContext("Til montering af løsholter i skur");
        bom.addToBOM(angleBrackets);

        return bom;
    }

    private double calcReglar(int shedLength, int shedWidth) {
        return (4 * shedLength + 4 * shedWidth) / 100;
    }

    private double calcCladding(int shedLength, int shedWidth) { // pga overlap fylder hvert bræt 7,5 cm
        return (((((shedLength * 100) / 750) + ((shedWidth * 100) / 750)) * 210) * 2) / 100; //210 er standard højden.
    }

    private int calcShedScrews(int shedLength, int shedWidth) { // 3 skruer pr. bræt, halvdelen af brædderne
        int numberOfBoards = (((shedLength * 100) / 750) + ((shedWidth * 100) / 750)) * 2;
        return numberOfBoards * 3;

    }

    private int calcSmallShedScrews(int shedLength, int shedWidth) { // 6 skruer pr. bræt, halvdelen af brædderne
        int numberOfBoards = (((shedLength * 100) / 750) + ((shedWidth * 100) / 750)) * 2;
        return numberOfBoards * 6;
    }

    @Override
    public BOM getBom() {
        return bom;
    }

}
