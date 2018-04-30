package functionLayer.Calculator;

import dbAccess.Mapper;
import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.entity.LineItem;

public class PitchedRoofCalculator implements CarportCalculator {

    private int length;
    private int width;
    private int angle;
    private BOM bom = new BOM();

    public PitchedRoofCalculator(int length, int width, int angle) throws CarportException {
        this.length = length;
        this.width = width;
        this.angle = angle;
        bom = calculateBOM();
    }

    private BOM calculateBOM() throws CarportException {
        bom = new BOM();

        LineItem fasciaPitch = Mapper.getProduct(18);
        fasciaPitch.setQuantity(calcFasciaPitch(width, angle));
        fasciaPitch.setUseInContext("Vindskeder på rejsning");
        bom.addToBOM(fasciaPitch);

        LineItem fasciaSides = Mapper.getProduct(18);
        fasciaSides.setQuantity(calcFasciaSides(length));
        fasciaSides.setUseInContext("Sternbrædder til siderne");
        bom.addToBOM(fasciaSides);

        LineItem rafterSet = Mapper.getProduct(19); // prisen på disse er tvivlsom
        rafterSet.setQuantity(calcRafterSet(length));
        rafterSet.setUseInContext("Skal samles");
        bom.addToBOM(rafterSet);

        LineItem plates = Mapper.getProduct(4);
        plates.setQuantity(calcPlates(length));
        plates.setUseInContext("Remme i sider");
        bom.addToBOM(plates);

        LineItem posts = Mapper.getProduct(5);
        posts.setQuantity(calcPosts(length));
        posts.setUseInContext("Nedgraves 90cm i jord");
        bom.addToBOM(posts);

        LineItem waterBoards = Mapper.getProduct(6);
        waterBoards.setQuantity(calcWaterBoards(width, angle));
        waterBoards.setUseInContext("Vandbræt på vindskeder");
        bom.addToBOM(waterBoards);

        LineItem gablesCladding = Mapper.getProduct(6);
        gablesCladding.setQuantity(calcGablesCladding(width, angle));
        gablesCladding.setUseInContext("Beklædning af gavle 1 på 2");
        bom.addToBOM(gablesCladding);

        LineItem molding = Mapper.getProduct(20); // molding = træliste
        molding.setQuantity(calcMolding(length));
        molding.setUseInContext("Monteres ovenpå tagfodslægte");
        bom.addToBOM(molding);

        LineItem roofLaths = Mapper.getProduct(3);
        roofLaths.setQuantity(calcRoofLaths(length, width, angle));
        roofLaths.setUseInContext("Til montering på spær");
        bom.addToBOM(roofLaths);

        LineItem topLath = Mapper.getProduct(3);
        topLath.setQuantity(length); // altid 1 lægte tilsvarende længden
        topLath.setUseInContext("Toplægte til montering af rygsten");
        bom.addToBOM(topLath);

        // skal til tagstenene
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
}
