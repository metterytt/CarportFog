package functionLayer.Calculators;

import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.LineItem;
import java.util.List;

public class PitchedRoofCalculator implements CarportCalculator {

    private final int length;
    private final int width;
    private final int angle;
    private int shedLength;
    private int shedWidth;
    private BOM bom = new BOM();

    public PitchedRoofCalculator(int length, int width, int angle, int shedLength, int shedWidth) throws CarportException {
        this.length = length;
        this.width = width;
        this.angle = angle;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        bom = calculateBOM();
    }

    private BOM calculateBOM() throws CarportException {
        bom = new BOM();
        bom.setLength(length);
        bom.setWidth(width);
        bom.setAngle(angle);
        bom.setShedLength(shedLength);
        bom.setShedWidth(shedWidth);

        LineItem fasciaBoards = StorageFacade.getProduct(18);
        fasciaBoards.setQuantity(calcFasciaBoards());
        fasciaBoards.setUseInContext("Stern + vindskeder");
        bom.addToBOM(fasciaBoards);

        LineItem rafterSetAndPlates = StorageFacade.getProduct(4);
        rafterSetAndPlates.setQuantity(calcRafterSetAndPlates());
        rafterSetAndPlates.setUseInContext("Træ til remme og byg-selv spær");
        bom.addToBOM(rafterSetAndPlates);

        LineItem posts = StorageFacade.getProduct(5);
        posts.setQuantity(calcPosts());
        posts.setUseInContext("Nedgraves 90cm i jord");
        bom.addToBOM(posts);

        LineItem waterBoardsAndGablesCladding = StorageFacade.getProduct(6);
        waterBoardsAndGablesCladding.setQuantity(calcWaterBoardsAndGablesCladding());
        waterBoardsAndGablesCladding.setUseInContext("Vandbrædder + gavlbeklædning");
        bom.addToBOM(waterBoardsAndGablesCladding);

        LineItem molding = StorageFacade.getProduct(20); // molding = træliste
        molding.setQuantity(calcMolding());
        molding.setUseInContext("Monteres ovenpå tagfodslægte");
        bom.addToBOM(molding);

        LineItem roofLaths = StorageFacade.getProduct(3);
        roofLaths.setQuantity(calcRoofLaths());
        roofLaths.setUseInContext("Monteres på spær, inkl. toplægte");
        bom.addToBOM(roofLaths);

        LineItem roofTiles = StorageFacade.getProduct(21);
        roofTiles.setQuantity(calcRoofTiles());
        roofTiles.setUseInContext("Monteres på taglægter");
        bom.addToBOM(roofTiles);

        LineItem roofTopTiles = StorageFacade.getProduct(22);
        roofTopTiles.setQuantity(calcRoofTopTiles());
        roofTopTiles.setUseInContext("Monteres på toplægte");
        bom.addToBOM(roofTopTiles);

        LineItem topLathHolder = StorageFacade.getProduct(23);
        topLathHolder.setQuantity(calcTopLathHolder());
        topLathHolder.setUseInContext("Monteres på toppen af hvert spær");
        bom.addToBOM(topLathHolder);

        LineItem roofTopTileBrackets = StorageFacade.getProduct(24);
        roofTopTileBrackets.setQuantity(calcRoofTopTileBrackets());
        roofTopTileBrackets.setUseInContext("Til montering af rygsten");
        bom.addToBOM(roofTopTileBrackets);

        LineItem binders = StorageFacade.getProduct(25);
        binders.setQuantity(calcBinders());
        binders.setUseInContext("Til montering af tagsten");
        bom.addToBOM(binders);

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
        fasciaScrews.setUseInContext("Til montering af stern, vindskeder og vandbræt");
        bom.addToBOM(fasciaScrews);

        LineItem bracketScrews = StorageFacade.getProduct(13);
        bracketScrews.setQuantity(calcBracketScrews());
        bracketScrews.setUseInContext("Til montering af universalbeslag og toplægte");
        bom.addToBOM(bracketScrews);

        LineItem roofLathScrews = StorageFacade.getProduct(26);
        roofLathScrews.setQuantity(calcRoofLathScrews());
        roofLathScrews.setUseInContext("Til taglægter");
        bom.addToBOM(roofLathScrews);

        LineItem bolts = StorageFacade.getProduct(14);
        bolts.setQuantity(calcBolts());
        bolts.setUseInContext("Til montering af rem på stolper");
        bom.addToBOM(bolts);

        LineItem squareBrackets = StorageFacade.getProduct(15);
        squareBrackets.setQuantity(calcSquareBrackets());
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

     double calcFasciaBoards() { // c = b/cos V, and we need 4 boards
        double calcAngle = Math.toRadians(angle);
        double fasciaBoards = 4 * ((width / 2) / Math.cos(calcAngle));
        fasciaBoards = fasciaBoards + 2 * length;
        return fasciaBoards / 100;
    }

     double calcRafterSetAndPlates() {
        // first we calculate how many rafters
        int numberOfRafters = (length / 60);
        if (length % 60 == 0) {
            numberOfRafters++;
        } else {
            numberOfRafters += 2;
        }
        // then the length of wood for each
        double calcAngle = Math.toRadians(angle);
        double lengthOfRafters = 2 * ((width / 2) / Math.cos(calcAngle)); // 2 * tilted roofside
        lengthOfRafters += width; // adding width

        double gableHeight = (width / 2) * Math.tan(calcAngle); // finally the height
        lengthOfRafters += gableHeight;
        double totalWoodRafters = lengthOfRafters * numberOfRafters;
        // we add the the plates and return
        return (totalWoodRafters + 2 * length) / 100;

    }

    double calcWaterBoardsAndGablesCladding() {
        double calcAngle = Math.toRadians(angle);
        double waterBoards = 4 * ((width / 2) / Math.cos(calcAngle));
        double gableHeight = (width / 2) * Math.tan(calcAngle);
        double gablesCladding = ((((width / 2) * 100) / 750) * gableHeight) * 2;
        return (waterBoards + gablesCladding) / 100;
    }

    private double calcMolding() {
        return (double) (2 * length) / 100;
    }

    private double calcRoofLaths() { // c is the triangle's hypotenuse
        double calcAngle = Math.toRadians(angle);
        int c = (int) ((width / 2) / Math.cos(calcAngle));
        int rows = c / 35;
        if (c % 35 > 3) {
            rows++;
        }
        double topLath = length; // toplath, same as length
        return (2 * rows * length + topLath) / 100;
    }

    int calcPosts() {
        if (length <= 480) {
            return 4;
        } else {
            return 6;
        }
    }

    private int calcRoofTiles() { // 9 tiles/m2. +10 for buffer
        double calcAngle = Math.toRadians(angle);
        double widthForCalc = (int) ((width / 2) / Math.cos(calcAngle));
        double roofArea = (widthForCalc * length) / 10000;
        return 2 * (9 * (int) roofArea) + 10;
    }

    private int calcRoofTopTiles() {
        return length / 30;
    }

    private int calcTopLathHolder() { // same as number of rafters
        return length / 60 + 1;
    }

    private int calcRoofTopTileBrackets() {
        return length / 30;
    }

    private int calcBinders() { // half of the tiles + 30 for the ones on the side
        int roofTiles = calcRoofTiles();
        return roofTiles / 2 + 30;
    }

    private int calcUniBrackets() {
        return length / 60 + 1;
    }

    private int calcFasciaScrews() {
        // first we need 4 screws pr. rafter:
        int numberOfRafters = (length / 60);
        if (length % 60 == 0) {
            numberOfRafters++;
        } else {
            numberOfRafters += 2;
        }
        int screws = 4 * numberOfRafters;

        // the 2 pr. rooflath (1 for fascia and 1 for waterboard)
        double calcAngle = Math.toRadians(angle);
        int c = (int) ((width / 2) / Math.cos(calcAngle));
        int rows = c / 35;
        if (c % 35 > 3) {
            rows++;
        }
        rows *= 2;
        return screws + 2 * rows + 30; // 30 for buffer
    }

    private int calcBracketScrews() { // 9 pr. universalbracket + 20 pr. toplathholder ... 50 for buffer
        int numberOfScrews = ((((length / 60) + 1) * 2)) * 9 + (length / 60 + 1) * 20 + 50;
        return numberOfScrews;
    }

    private int calcRoofLathScrews() {
        double calcAngle = Math.toRadians(angle);
        int c = (int) ((width / 2) / Math.cos(calcAngle));
        int rows = c / 35;
        if (c % 35 > 3) {
            rows++;
        }
        int metersOfLaths = rows * length;
        return (metersOfLaths / 60) * 2;
    }

    private int calcBolts() { // 2 pr. post
        return 2 * (2 * ((length / 300) + 1));
    }

    private int calcSquareBrackets() {
        return 2 * (2 * ((length / 300) + 2));
    }

    @Override
    public BOM getBom() {
        return bom;
    }

}
