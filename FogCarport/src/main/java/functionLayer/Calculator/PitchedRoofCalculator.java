package functionLayer.Calculator;

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

        LineItem fasciaPitch = StorageFacade.getProduct(18);
        fasciaPitch.setQuantity(calcFasciaPitch(width, angle));
        fasciaPitch.setUseInContext("Vindskeder på rejsning");
        bom.addToBOM(fasciaPitch);

        LineItem fasciaSides = StorageFacade.getProduct(18);
        fasciaSides.setQuantity(calcFasciaSides(length));
        fasciaSides.setUseInContext("Sternbrædder til siderne");
        bom.addToBOM(fasciaSides);

        LineItem rafterSet = StorageFacade.getProduct(19); // prisen på disse er tvivlsom
        rafterSet.setQuantity(calcRafterSet(length));
        rafterSet.setUseInContext("Skal samles");
        bom.addToBOM(rafterSet);

        LineItem plates = StorageFacade.getProduct(4);
        plates.setQuantity(calcPlates(length));
        plates.setUseInContext("Remme i sider");
        bom.addToBOM(plates);

        LineItem posts = StorageFacade.getProduct(5);
        posts.setQuantity(calcPosts(length));
        posts.setUseInContext("Nedgraves 90cm i jord");
        bom.addToBOM(posts);

        LineItem waterBoards = StorageFacade.getProduct(6);
        waterBoards.setQuantity(calcWaterBoards(width, angle));
        waterBoards.setUseInContext("Vandbræt på vindskeder");
        bom.addToBOM(waterBoards);

        LineItem gablesCladding = StorageFacade.getProduct(6);
        gablesCladding.setQuantity(calcGablesCladding(width, angle));
        gablesCladding.setUseInContext("Beklædning af gavle 1 på 2");
        bom.addToBOM(gablesCladding);

        LineItem molding = StorageFacade.getProduct(20); // molding = træliste
        molding.setQuantity(calcMolding(length));
        molding.setUseInContext("Monteres ovenpå tagfodslægte");
        bom.addToBOM(molding);

        LineItem roofLaths = StorageFacade.getProduct(3);
        roofLaths.setQuantity(calcRoofLaths(length, width, angle));
        roofLaths.setUseInContext("Til montering på spær");
        bom.addToBOM(roofLaths);

        LineItem topLath = StorageFacade.getProduct(3);
        topLath.setQuantity(length); // altid 1 lægte tilsvarende længden
        topLath.setUseInContext("Toplægte til montering af rygsten");
        bom.addToBOM(topLath);

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

    @Override
    public BOM getBom() {
        return bom;
    }

    private int calcFasciaPitch(int width, int angle) { // c = b/cos V, og vi skal bruge 4 brædder
        double calcAngle = Math.toRadians(angle);
        return 4 * (int) ((width / 2) / Math.cos(calcAngle));
    }

    private int calcFasciaSides(int length) {
        return 2 * length;
    }

    private int calcRafterSet(int length) {
        return length / 60 + 1;
    }

    private int calcPlates(int length) {
        return 2 * length;
    }

    private int calcWaterBoards(int width, int angle) { // samme som vindskeder (fasciaPitch)
        double calcAngle = Math.toRadians(angle);
        return 4 * (int) ((width / 2) / Math.cos(calcAngle));
    }

    private int calcGablesCladding(int width, int angle) { // 7,5 pr. bræt pga 2 på 1
        double calcAngle = Math.toRadians(angle);
        double gableHeight = (width / 2) * Math.tan(calcAngle);
        return (int) (((((width / 2) * 100) / 750) * gableHeight) * 2);
    }

    private int calcMolding(int length) {
        return 2 * length;
    }

    private int calcRoofLaths(int length, int width, int angle) { // c er trekantens hypotenuse
        double calcAngle = Math.toRadians(angle);
        int c = (int) ((width / 2) / Math.cos(calcAngle));
        int rows = c / 35;
        if (c % 35 > 3) {
            rows++;
        }
        return rows * length;
    }

    private int calcPosts(int length) {
        return 2 * ((length / 300) + 2);
    }

    private int calcRoofTiles(int length, int width, int angle) { // 9 sten/m2. +10 er for buffer
        double calcAngle = Math.toRadians(angle);
        int widthForCalc = (int) ((width / 2) / Math.cos(calcAngle));
        int roofArea = widthForCalc * length;
        return (2 * (9 * roofArea) / 10000) + 10;
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

    private int calcBinders(int length, int width) {
        if (width * length <= 150000) {
            return 1;
        }
        if (width * length > 150000 && width * length <= 300000) {
            return 2;
        }
        if (width * length > 300000) {
            return 3;
        }
        return 4;
    }

    private int calcUniBrackets(int length) {
        return length / 60 + 1;
    }

    private int calcFasciaScrews(int length, int width) { // hvis carporten er større end eksemplet, bruges flere skruer
        if (length * width < 270000) {
            return 1;
        } else {
            return 2;
        }
    }

    private int calcBracketScrews(int length) { // 20 pr. universalbeslag + 20 pr. toplægteholder ... 50 for buffer
        int numberOfScrews = ((((length / 60) + 1) * 2)) * 20 + (length / 60 + 1) * 20 + 50;
        return numberOfScrews / 250 + 1;
    }

    private int calcRoofLathScrews(int length, int width, int angle) {
        double calcAngle = Math.toRadians(angle);
        int c = (int) ((width / 2) / Math.cos(calcAngle));
        int rows = c / 35;
        if (c % 35 > 3) {
            rows++;
        }
        int metersOfLaths = rows * length;
        return ((metersOfLaths / 60) * 2) / 100 + 1;
    }

    private int calcBolts(int length) {
        return 2 * (2 * ((length / 300) + 2));
    }

    private int calcSquareBrackets(int length) {
        return 2 * (2 * ((length / 300) + 2));
    }

}
