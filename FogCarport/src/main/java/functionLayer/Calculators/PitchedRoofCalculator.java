package functionLayer.Calculators;

import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.LineItem;

public class PitchedRoofCalculator implements CarportCalculator {

    private final int length;
    private final int width;
    private final int angle;
    private BOM bom = new BOM();

    public PitchedRoofCalculator(int length, int width, int angle) throws CarportException {
        this.length = length;
        this.width = width;
        this.angle = angle;
        bom = calculateBOM();
    }

    private BOM calculateBOM() throws CarportException {
        bom = new BOM();
        bom.setLength(length);
        bom.setWidth(width);
        bom.setAngle(angle);

        LineItem fasciaBoards = StorageFacade.getProduct(18);
        fasciaBoards.setQuantity(calcFasciaBoards());
        fasciaBoards.setUseInContext("Stern + vindskeder");
        bom.addToBOM(fasciaBoards);

        LineItem rafterSetAndPlates = StorageFacade.getProduct(4);
        rafterSetAndPlates.setQuantity(calcRafterSetAndPlates());
        rafterSetAndPlates.setUseInContext("Træ til remme og byg-selv spær");
        bom.addToBOM(rafterSetAndPlates);

        LineItem posts = StorageFacade.getProduct(5);
        posts.setQuantity(calcPosts(length));
        posts.setUseInContext("Nedgraves 90cm i jord");
        bom.addToBOM(posts);

        LineItem waterBoardsAndGablesCladding = StorageFacade.getProduct(6);
        waterBoardsAndGablesCladding.setQuantity(calcWaterBoardsAndGablesCladding());
        waterBoardsAndGablesCladding.setUseInContext("Vandbrædder + gavlbeklædning");
        bom.addToBOM(waterBoardsAndGablesCladding);

        LineItem molding = StorageFacade.getProduct(20); // molding = træliste
        molding.setQuantity(calcMolding(length));
        molding.setUseInContext("Monteres ovenpå tagfodslægte");
        bom.addToBOM(molding);

        LineItem roofLaths = StorageFacade.getProduct(3);
        roofLaths.setQuantity(calcRoofLaths(length, width, angle));
        roofLaths.setUseInContext("Monteres på spær, inkl. toplægte");
        bom.addToBOM(roofLaths);

        LineItem roofTiles = StorageFacade.getProduct(21);
        roofTiles.setQuantity(calcRoofTiles(length, width, angle));
        roofTiles.setUseInContext("Monteres på taglægter");
        bom.addToBOM(roofTiles);

        LineItem roofTopTiles = StorageFacade.getProduct(22);
        roofTopTiles.setQuantity(calcRoofTopTiles(length));
        roofTopTiles.setUseInContext("Monteres på toplægte");
        bom.addToBOM(roofTopTiles);

        LineItem topLathHolder = StorageFacade.getProduct(23);
        topLathHolder.setQuantity(calcTopLathHolder(length));
        topLathHolder.setUseInContext("Monteres på toppen af hvert spær");
        bom.addToBOM(topLathHolder);

        LineItem roofTopTileBrackets = StorageFacade.getProduct(24);
        roofTopTileBrackets.setQuantity(calcRoofTopTileBrackets(length));
        roofTopTileBrackets.setUseInContext("Til montering af rygsten");
        bom.addToBOM(roofTopTileBrackets);

        LineItem binders = StorageFacade.getProduct(25);
        binders.setQuantity(calcBinders(length, width));
        binders.setUseInContext("Til montering af tagsten");
        bom.addToBOM(binders);

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
        fasciaScrews.setUseInContext("Til montering af stern, vindskeder og vandbræt");
        bom.addToBOM(fasciaScrews);

        LineItem bracketScrews = StorageFacade.getProduct(13);
        bracketScrews.setQuantity(calcBracketScrews(length));
        bracketScrews.setUseInContext("Til montering af universalbeslag og toplægte");
        bom.addToBOM(bracketScrews);

        LineItem roofLathScrews = StorageFacade.getProduct(26);
        roofLathScrews.setQuantity(calcRoofLathScrews(length, width, angle));
        roofLathScrews.setUseInContext("Til taglægter");
        bom.addToBOM(roofLathScrews);

        LineItem bolts = StorageFacade.getProduct(14);
        bolts.setQuantity(calcBolts(length));
        bolts.setUseInContext("Til montering af rem på stolper");
        bom.addToBOM(bolts);

        LineItem squareBrackets = StorageFacade.getProduct(15);
        squareBrackets.setQuantity(calcSquareBrackets(length));
        squareBrackets.setUseInContext("Til montering af rem på stolper");
        bom.addToBOM(squareBrackets);

        return bom;
    }

    private double calcFasciaBoards() { // c = b/cos V, og vi skal bruge 4 brædder
        double calcAngle = Math.toRadians(angle);
        double fasciaBoards = 4 * ((width / 2) / Math.cos(calcAngle));
        fasciaBoards = fasciaBoards + 2 * length;
        return fasciaBoards / 100;
    }

    private double calcRafterSetAndPlates() {
        // først regner vi ud hvor mange spær
        int numberOfRafters = (length / 60);
        if (length % 60 == 0) {
            numberOfRafters++;
        } else {
            numberOfRafters += 2;
        }
        //så længden af træ til hver
        double calcAngle = Math.toRadians(angle);
        double lengthOfRafters = 2 * ((width / 2) / Math.cos(calcAngle)); // 2 * skrå tagside
        lengthOfRafters += width; // bredden skal med

        double gableHeight = (width / 2) * Math.tan(calcAngle); // og endelig højden
        lengthOfRafters += gableHeight;
        double totalWoodRafters = lengthOfRafters * numberOfRafters;
        // vi lægger remmene til og returnerer
        return (totalWoodRafters + 2 * length) / 100;

    }

    double calcWaterBoardsAndGablesCladding() {
        double calcAngle = Math.toRadians(angle);
        double waterBoards = 4 * ((width / 2) / Math.cos(calcAngle));
        double gableHeight = (width / 2) * Math.tan(calcAngle);
        double gablesCladding = ((((width / 2) * 100) / 750) * gableHeight) * 2;
        return (waterBoards + gablesCladding) / 100;
    }

    private double calcMolding(int length) {
        return (2 * length) / 100;
    }

    private double calcRoofLaths(int length, int width, int angle) { // c er trekantens hypotenuse
        double calcAngle = Math.toRadians(angle);
        int c = (int) ((width / 2) / Math.cos(calcAngle));
        int rows = c / 35;
        if (c % 35 > 3) {
            rows++;
        }
        double topLath = length; // toplægten, svarer til længden
        return (2 * rows * length + topLath) / 100;
    }

      int calcPosts(int length) {
        if (length <= 480) {
            return 4;
        } else {
            return 6;
        }
    }

    private int calcRoofTiles(int length, int width, int angle) { // 9 sten/m2. +10 er for buffer
        double calcAngle = Math.toRadians(angle);
        double widthForCalc = (int) ((width / 2) / Math.cos(calcAngle));
        double roofArea = (widthForCalc * length) / 10000;
        return 2 * (9 * (int) roofArea) + 10;
    }

    private int calcRoofTopTiles(int length) {
        return length / 30;
    }

    private int calcTopLathHolder(int length) { // samme som antal spær
        return length / 60 + 1;
    }

    private int calcRoofTopTileBrackets(int length) {
        return length / 30;
    }

    private int calcBinders(int length, int width) { // halvdelen af teglene + 30 for dem i siden
        int roofTiles = calcRoofTiles(length, width, angle);
        return roofTiles / 2 + 30;
    }

    private int calcUniBrackets(int length) {
        return length / 60 + 1;
    }

    private int calcFasciaScrews(int length, int width) {
        // først skal vi have 4 skruer pr spær:
        int numberOfRafters = (length / 60);
        if (length % 60 == 0) {
            numberOfRafters++;
        } else {
            numberOfRafters += 2;
        }
        int screws = 4 * numberOfRafters;

        //så 2 pr. taglægte (1 for vindskede og en for vandbræt)
        double calcAngle = Math.toRadians(angle);
        int c = (int) ((width / 2) / Math.cos(calcAngle));
        int rows = c / 35;
        if (c % 35 > 3) {
            rows++;
        }
        rows *= 2;
        return screws + 2 * rows + 30; // 30 for buffer
    }

    private int calcBracketScrews(int length) { // 9 pr. universalbeslag + 20 pr. toplægteholder ... 50 for buffer
        int numberOfScrews = ((((length / 60) + 1) * 2)) * 9 + (length / 60 + 1) * 20 + 50;
        return numberOfScrews;
    }

    private int calcRoofLathScrews(int length, int width, int angle) {
        double calcAngle = Math.toRadians(angle);
        int c = (int) ((width / 2) / Math.cos(calcAngle));
        int rows = c / 35;
        if (c % 35 > 3) {
            rows++;
        }
        int metersOfLaths = rows * length;
        return (metersOfLaths / 60) * 2;
    }

    private int calcBolts(int length) { // 2 stk. pr. stolpe
        return 2 * (2 * ((length / 300) + 1));
    }

    private int calcSquareBrackets(int length) {
        return 2 * (2 * ((length / 300) + 2));
    }

    @Override
    public BOM getBom() {
        return bom;
    }

}
