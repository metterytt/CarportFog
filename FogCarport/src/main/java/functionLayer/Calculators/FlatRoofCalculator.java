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
import java.util.List;

public class FlatRoofCalculator implements CarportCalculator {

    private final int length;
    private final int width;
    private final int angle = 0;
    private int shedLength;
    private int shedWidth;
    private BOM bom = new BOM();

    public FlatRoofCalculator(int length, int width, int shedLength, int shedWidth) throws CarportException {
        this.length = length;
        this.width = width;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        bom = calculateBOM();
    }

    private BOM calculateBOM() throws CarportException {
        bom = new BOM();
        bom.setLength(length);
        bom.setWidth(width);

        LineItem subFasciaBoards = StorageFacade.getProduct(1);
        subFasciaBoards.setQuantity(calcSubFasciaBoards());
        subFasciaBoards.setUseInContext("Understernbrædder");
        bom.addToBOM(subFasciaBoards);

        LineItem fasciaBoards = StorageFacade.getProduct(2);
        fasciaBoards.setQuantity(calcFasciaBoards());
        fasciaBoards.setUseInContext("Oversternbrædder");
        bom.addToBOM(fasciaBoards);

        LineItem platesAndRafters = StorageFacade.getProduct(4); // same wood for rafters and plates
        platesAndRafters.setQuantity(calcPlatesAndRafters());
        platesAndRafters.setUseInContext("Remme og spær");
        bom.addToBOM(platesAndRafters);

        LineItem posts = StorageFacade.getProduct(5);
        posts.setQuantity(calcPosts());
        posts.setUseInContext("Nedgraves 90cm i jord");
        bom.addToBOM(posts);

        LineItem waterBoards = StorageFacade.getProduct(6);
        waterBoards.setQuantity(calcWaterBoards());
        waterBoards.setUseInContext("Vandbræt, monteres på stern");
        bom.addToBOM(waterBoards);

        LineItem roof = StorageFacade.getProduct(7);
        roof.setQuantity(calcRoof());
        roof.setUseInContext("Tagplader");
        bom.addToBOM(roof);

        LineItem roofScrews = StorageFacade.getProduct(8);
        roofScrews.setQuantity(calcRoofScrews());
        roofScrews.setUseInContext("Skruer til tagplader");
        bom.addToBOM(roofScrews);

        LineItem metalTape = StorageFacade.getProduct(9);
        metalTape.setQuantity(calcMetalTape());
        metalTape.setUseInContext("Til vindkryds på spær");
        bom.addToBOM(metalTape);

        LineItem uniBracketsRight = StorageFacade.getProduct(10);
        uniBracketsRight.setQuantity(calcUniBrackets());
        uniBracketsRight.setUseInContext("Til montering af spær på rem");
        bom.addToBOM(uniBracketsRight);

        LineItem uniBracketsLeft = StorageFacade.getProduct(11);
        uniBracketsLeft.setQuantity(calcUniBrackets());
        uniBracketsLeft.setUseInContext("Til montering af spær på rem");
        bom.addToBOM(uniBracketsLeft);

        LineItem fasciaScrews = StorageFacade.getProduct(12);
        fasciaScrews.setQuantity(calcFasciaScrews());
        fasciaScrews.setUseInContext("Til montering af stern og vandbræt");
        bom.addToBOM(fasciaScrews);

        LineItem bracketScrews = StorageFacade.getProduct(13);
        bracketScrews.setQuantity(calcBracketScrews());
        bracketScrews.setUseInContext("Til montering af universalbeslag og hulbånd");
        bom.addToBOM(bracketScrews);

        LineItem bolts = StorageFacade.getProduct(14);
        bolts.setQuantity(posts.getQuantity() * 3);
        bolts.setUseInContext("Til montering af rem på stolper");
        bom.addToBOM(bolts);

        LineItem squareBrackets = StorageFacade.getProduct(15);
        squareBrackets.setQuantity(posts.getQuantity() * 3);
        squareBrackets.setUseInContext("Til montering af rem på stolper");
        bom.addToBOM(squareBrackets);

        if (shedLength != 0) {
            CarportCalculator shedCalculator = new ShedCalculator(shedLength, shedWidth);
            BOM shedBom = shedCalculator.getBom();
            List<LineItem> shedList = shedBom.getListOfProducts();
            for (LineItem li : shedList) {
                bom.addToBOM(li);
            }
        }

        return bom;
    }

    private int calcPosts() {
        if (length <= 480) {
            return 4;
        } else {
            return 6;
        }
    }

    private double calcSubFasciaBoards() {
        return (2 * length + 2 * width) / 100; // returns the number in meters
    }

    private double calcFasciaBoards() {
        return (2 * length + width) / 100; // returns the number in meters
    }

    private double calcPlatesAndRafters() {
        int numberOfRafters = (length / 60);
        if (length % 60 == 0) {
            numberOfRafters++;
        } else {
            numberOfRafters += 2;
        }
        double platesAndRafters = numberOfRafters * width;
        platesAndRafters = platesAndRafters + 2 * length;
        return platesAndRafters / 100;
    }

    private double calcWaterBoards() {
        return (double) (2 * length + width) / 100; // returns the number in meters
    }

    private double calcRoof() { // returns in m2
        return (double) (length * width) / 10000;
    }

    private int calcRoofScrews() { //12 screws/m2, 50 for buffer
        return (int) ((length * width * 12) / 10000) + 50;
    }

    private int calcMetalTape() { // calculates in meters, and rounds up
        return (int) (2 * (Math.sqrt(length * length + width * width))) / 100 + 1;
    }

    private int calcUniBrackets() {
        int uniBrackets = ((int) length / 60);
        if (length % 60 == 0) {
            uniBrackets++;
        } else {
            uniBrackets += 2;
        }
        return uniBrackets;
    }

    private int calcFasciaScrews() { // 2 pr. 60 cm perimeter PLUS 4 pr 60 cm (perimeter minus width)
        //(due to missing fasciaboard and waterboard at the back) ... 50 for buffer
        return (int) (2 * ((2 * length + 2 * width) / 60) + (4 * (2 * length + width) / 60) + 50);
    }

    private int calcBracketScrews() { // 9 pr. universalbracket + 2 * length/60 for metaltape... 50 for buffer
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
