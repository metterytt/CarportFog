/*
ORDLISTE:
understernbrædder = sub fascia boards // beam
oversternbrædder = fascia boards // beam
rem = plate
spær = rafter
stolper = posts
vandbræt = waterboard
tagplader = roof...
plastmo bundskruer = roofscrews
hulbånd = metaltape
universalbeslag 190 mm = universalbrackets
skruer til montering af stern og vandbræt = fasciascrews
beslagskruer = bracketscrews
bræddebolt = bolts
firkantskiver = squarebrackets
 */
package functionLayer.Calculators;

import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.LineItem;

public class FlatRoofCalculator implements CarportCalculator {

    private final int length;
    private final int width;
    private BOM bom = new BOM();

    public FlatRoofCalculator(int length, int width) throws CarportException {
        this.length = length;
        this.width = width;
        bom = calculateBOM();
    }

    private BOM calculateBOM() throws CarportException {
        bom = new BOM();
        bom.setLength(length);
        bom.setWidth(width);

        LineItem subFasciaBoards = StorageFacade.getProduct(1);
        subFasciaBoards.setQuantity(calcSubFasciaBoards(length, width));
        subFasciaBoards.setUseInContext("Understernbrædder");
        bom.addToBOM(subFasciaBoards);

        LineItem fasciaBoards = StorageFacade.getProduct(2);
        fasciaBoards.setQuantity(calcFasciaBoards(length, width));
        fasciaBoards.setUseInContext("Oversternbrædder");
        bom.addToBOM(fasciaBoards);

        LineItem platesAndRafters = StorageFacade.getProduct(4); // samme træ til remme og spær
        platesAndRafters.setQuantity(calcPlatesAndRafters(length, width));
        platesAndRafters.setUseInContext("Remme og spær");
        bom.addToBOM(platesAndRafters);

        // disse to metoder bruges ikke siden at kunden ikke skulle have stykliste alligevel
//        LineItem plates = StorageFacade.getProduct(4); // samme træ til remme og spær
//        plates.setQuantity(calcPlate(length));
//        plates.setUseInContext("Remme");
//        bom.addToBOM(plates);
//        LineItem rafters = StorageFacade.getProduct(4); // samme træ til remme og spær
//        rafters.setQuantity(calcRafters(length, width));
//        rafters.setUseInContext("Spær, monteres på rem");
//        bom.addToBOM(rafters);
        LineItem posts = StorageFacade.getProduct(5);
        posts.setQuantity(calcPosts(length));
        posts.setUseInContext("Nedgraves 90cm i jord");
        bom.addToBOM(posts);

        LineItem waterBoards = StorageFacade.getProduct(6);
        waterBoards.setQuantity(calcWaterBoards(length, width));
        waterBoards.setUseInContext("Vandbræt, monteres på stern");
        bom.addToBOM(waterBoards);

        // mangler tagplader
        LineItem roofScrews = StorageFacade.getProduct(8);
        roofScrews.setQuantity(calcRoofScrews(length, width));
        roofScrews.setUseInContext("Skruer til tagplader");
        bom.addToBOM(roofScrews);

        LineItem metalTape = StorageFacade.getProduct(9);
        metalTape.setQuantity(calcMetalTape(length, width));
        metalTape.setUseInContext("Til vindkryds på spær");
        bom.addToBOM(metalTape);

        LineItem uniBracketsRight = StorageFacade.getProduct(10);
        uniBracketsRight.setQuantity(calcUniBrackets(length));
        uniBracketsRight.setUseInContext("Til montering af spær på rem");
        bom.addToBOM(uniBracketsRight);

        LineItem uniBracketsLeft = StorageFacade.getProduct(11);
        uniBracketsLeft.setQuantity(calcUniBrackets(length));
        uniBracketsLeft.setUseInContext("Til montering af spær på rem");
        bom.addToBOM(uniBracketsLeft);

        LineItem fasciaScrews = StorageFacade.getProduct(12);
        fasciaScrews.setQuantity(calcFasciaScrews(length, width));
        fasciaScrews.setUseInContext("Til montering af stern og vandbræt");
        bom.addToBOM(fasciaScrews);

        LineItem bracketScrews = StorageFacade.getProduct(13);
        bracketScrews.setQuantity(calcBracketScrews(length));
        bracketScrews.setUseInContext("Til montering af universalbeslag og hulbånd");
        bom.addToBOM(bracketScrews);

        //disse to metoder regner bare 3 * posts ud - uigennemskueligt hvad det korrekte regnestykke er
        LineItem bolts = StorageFacade.getProduct(14);
        bolts.setQuantity(posts.getQuantity() * 3);
        bolts.setUseInContext("Til montering af rem på stolper");
        bom.addToBOM(bolts);

        LineItem squareBrackets = StorageFacade.getProduct(15);
        squareBrackets.setQuantity(posts.getQuantity() * 3);
        squareBrackets.setUseInContext("Til montering af rem på stolper");
        bom.addToBOM(squareBrackets);

        return bom;
    }

    private int calcPosts(double length) {
       if (length <= 480) {
            return 4;
        } else {
            return 6;
        }
    }

    private double calcSubFasciaBoards(double length, int width) {
        return (2 * (double) length + 2 * (double) width) / 100; // returnerer antallet i meter
    }

    private double calcFasciaBoards(double length, int width) {
        return (2 * length + width) / 100; // returnerer antallet i meter
    }

    private double calcPlatesAndRafters(double length, int width) {
        int numberOfRafters = ((int) length / 60);
        if (length % 60 == 0) {
            numberOfRafters++;
        } else {
            numberOfRafters += 2;
        }
        double platesAndRafters = numberOfRafters * width;
        platesAndRafters = platesAndRafters + 2 * length;
        return platesAndRafters / 100;
    }

//    private double calcPlate(double length) {
//        return 2 * length;
//    }
//
//    private int calcRafters(double length) {
//        int numberOfRafters = ((int) length / 60);
//        if (length % 60 == 0) {
//            numberOfRafters++;
//        } else {
//            numberOfRafters += 2;
//        }
//        return numberOfRafters;
//    }
    private double calcWaterBoards(double length, int width) {
        return (2 * length + width) / 100; // returnerer antallet i meter
    }

    private double calcRoof(double length, int width) { //under forudsætning af ca. 9 cm overlap
        int rows = (int) length / 100;
        if (length % 100 != 0) {
            rows++;
        }
        return rows * width;
    }

    private int calcRoofScrews(double length, int width) { //12 skruer/m2, 50 for buffer
        return (int) ((length * width * 12) / 10000) + 50;
    }

    private int calcMetalTape(double length, int width) { // regner ud i meter, og runder op til nærmeste hele meter
        return (int) (2 * (Math.sqrt(length * length + width * width))) / 100 + 1;
    }

    private int calcUniBrackets(double length) {
        int uniBrackets = ((int) length / 60);
        if (length % 60 == 0) {
            uniBrackets++;
        } else {
            uniBrackets += 2;
        }
        return uniBrackets;
    }

    private int calcFasciaScrews(double length, int width) { // 2 stk. pr. 60 cm omkreds PLUS 4 stk. pr 60 cm (omkreds minus bredde)
        //(pga manglende overstern og vandbræt bagtil) ... 50 for buffer
        return (int) (2 * ((2 * length + 2 * width) / 60) + (4 * (2 * length + width) / 60) + 50);
    }

    private int calcBracketScrews(double length) { // 9 pr. universalbeslag + 2 * length/60 til hulbånd... 50 for buffer
        int uniBrackets = ((int) length / 60);
        if (length % 60 == 0) {
            uniBrackets++;
        } else {
            uniBrackets += 2;
        }
        return uniBrackets * 9 + 2 * (int) length / 60 + 50;
    }

    @Override
    public BOM getBom() {
        return bom;
    }

}
