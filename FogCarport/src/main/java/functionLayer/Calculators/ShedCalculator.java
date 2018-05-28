package functionLayer.Calculators;

import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.LineItem;

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
        lath.setUseInContext("Skur: Til Z på bagside af dør");
        bom.addToBOM(lath);

        LineItem reglar = StorageFacade.getProduct(17);
        reglar.setQuantity(calcReglar());
        reglar.setUseInContext("Skur: Løsholter til skur");
        bom.addToBOM(reglar);

        LineItem cladding = StorageFacade.getProduct(6);
        cladding.setQuantity(calcCladding());
        cladding.setUseInContext("Skur: Til beklædning af skur 1 på 2");
        bom.addToBOM(cladding);

        LineItem posts = StorageFacade.getProduct(5);
        posts.setQuantity(3); // altid 3 ekstra stolper når skur
        posts.setUseInContext("Skur: Ekstra stolper til skur");
        bom.addToBOM(posts);

        LineItem shedScrews = StorageFacade.getProduct(27);
        shedScrews.setQuantity(calcShedScrews());
        shedScrews.setUseInContext("Skur: Til montering af yderste beklædning");
        bom.addToBOM(shedScrews);

        LineItem smallShedScrews = StorageFacade.getProduct(28);
        smallShedScrews.setQuantity(calcSmallShedScrews());
        smallShedScrews.setUseInContext("Skur: Til montering af inderste beklædning");
        bom.addToBOM(smallShedScrews);

        LineItem doorKnob = StorageFacade.getProduct(29);
        doorKnob.setQuantity(1); // altid 1
        doorKnob.setUseInContext("Skur: Til lås på dør i skur");
        bom.addToBOM(doorKnob);

        LineItem tHinge = StorageFacade.getProduct(30);
        tHinge.setQuantity(2); // altid 2
        tHinge.setUseInContext("Skur: Til skurdør");
        bom.addToBOM(tHinge);

        LineItem angleBrackets = StorageFacade.getProduct(31);
        angleBrackets.setQuantity(20); // altid 20
        angleBrackets.setUseInContext("Skur: Til montering af løsholter i skur");
        bom.addToBOM(angleBrackets);

        return bom;
    }

    private double calcReglar() {
        return (double) (4 * shedLength + 4 * shedWidth) / 100;
    }

    private double calcCladding() { // pga overlap fylder hvert bræt 7,5 cm
        return (((((shedLength * 100) / 750) + ((shedWidth * 100) / 750)) * 210) * 2) / 100; //210 er standard højden.
    }

    private int calcShedScrews() { // 3 skruer pr. bræt, halvdelen af brædderne
        int numberOfBoards = (((shedLength * 100) / 750) + ((shedWidth * 100) / 750)) * 2;
        return numberOfBoards * 3;
    }

    private int calcSmallShedScrews() { // 6 skruer pr. bræt, halvdelen af brædderne
        int numberOfBoards = (((shedLength * 100) / 750) + ((shedWidth * 100) / 750)) * 2;
        return numberOfBoards * 6;
    }

    @Override
    public BOM getBom() {
        return bom;
    }

}
