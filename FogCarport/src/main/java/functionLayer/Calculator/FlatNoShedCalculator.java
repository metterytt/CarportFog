/*
understernbrædder = sub fascia boards // beam
oversternbrædder = fascia boards // beam
spær = rafter
stolper = posts
vandbræt
rem = plate
søm
tagplader
plastmo bundskruer
hulbånd
universalbeslag 190 mm
skruer til montering af stern og vandbræt
beslagskruer
bræddebolt
firkantskiver

 */
package functionLayer.Calculator;

import dbAccess.Mapper;
import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.entity.Product;

/**
 *
 * @author mette
 */
public class FlatNoShedCalculator implements CarportCalculator {
    
    private int length;
    private int width;
    private BOM bom = new BOM();
    
    public FlatNoShedCalculator(int length, int width) throws CarportException {
        this.length = length;
        this.width = width;
        bom = calculateBOMexp();
    }
    
    private BOM calculateBOMexp() throws CarportException {
        bom = new BOM();
        
        Product subFasciaBoards = Mapper.getProduct(1);
        subFasciaBoards.setQuantity(calcSubFasciaBoards(length, width));
        subFasciaBoards.setUseInContext("Understernbrædder");
        bom.addToBOM(subFasciaBoards);
        
        Product fasciaBoards = Mapper.getProduct(2);
        fasciaBoards.setQuantity(calcFasciaBoards(length, width));
        fasciaBoards.setUseInContext("Oversternbrædder");
        bom.addToBOM(fasciaBoards);
        
        Product plates = Mapper.getProduct(4); // samme træ til remme og spær
        plates.setQuantity(calcPlate(length));
        plates.setUseInContext("Remme");
        bom.addToBOM(plates);
        
        Product rafters = Mapper.getProduct(4); // samme træ til remme og spær
        rafters.setQuantity(calcRafters(length, width));
        rafters.setUseInContext("Spær, monteres på rem");
        bom.addToBOM(rafters);
        
        Product posts = Mapper.getProduct(5);
        posts.setQuantity(calcPosts(length));
        posts.setUseInContext("Nedgraves 90cm i jord");
        bom.addToBOM(posts);
        
        Product waterBoards = Mapper.getProduct(6);
        waterBoards.setQuantity(calcWaterBoards(length, width));
        waterBoards.setUseInContext("Vandbræt, monteres på stern");
        bom.addToBOM(waterBoards);

        // mangler tagplader
        
        Product roofScrews = Mapper.getProduct(8);
        roofScrews.setQuantity(calcRoofScrews(length, width));
        roofScrews.setUseInContext("Skruer til tagplader");
        bom.addToBOM(roofScrews);
        
        Product metalTape = Mapper.getProduct(9);
        metalTape.setQuantity(calcMetalTape(length, width));
        metalTape.setUseInContext("Til vindkryds på spær");
        bom.addToBOM(metalTape);
        
        Product uniBracketsRight = Mapper.getProduct(10);
        uniBracketsRight.setQuantity(calcUniBrackets(length));
        uniBracketsRight.setUseInContext("Til montering af spær på rem");
        bom.addToBOM(uniBracketsRight);
        
        Product uniBracketsLeft = Mapper.getProduct(11);
        uniBracketsLeft.setQuantity(calcUniBrackets(length));
        uniBracketsLeft.setUseInContext("Til montering af spær på rem");
        bom.addToBOM(uniBracketsLeft);
        
        Product fasciaScrews = Mapper.getProduct(12);
        fasciaScrews.setQuantity(calcFasciaScrews(length, width));
        fasciaScrews.setUseInContext("Til montering af stern og vandbræt");
        bom.addToBOM(fasciaScrews);
        
        Product bracketScrews = Mapper.getProduct(13);
        bracketScrews.setQuantity(calcBracketScrews(length));
        bracketScrews.setUseInContext("Til montering af universalbeslag og hulbånd");
        bom.addToBOM(bracketScrews);
        
        Product bolts = Mapper.getProduct(14);
        bolts.setQuantity(posts.getQuantity() * 3); // check dette regnestykke
        bolts.setUseInContext("Til montering af rem på stolper");
        bom.addToBOM(bolts);
        
        Product squareBrackets = Mapper.getProduct(15);
        squareBrackets.setQuantity(posts.getQuantity() * 3); // check dette regnestykke
        squareBrackets.setUseInContext("Til montering af rem på stolper");
        bom.addToBOM(squareBrackets);
        
        return bom;
    }
    
    // ud fra 4 stolper hvis længde mindre end 481, ellers 6
    private int calcPosts(int length) {
        if (length <= 480) {
            return 4;
        } else {
            return 6;
        }
    }
    
    private int calcSubFasciaBoards(int length, int width) {
        return 2 * length + 2 * width;
    }
    
    private int calcFasciaBoards(int length, int width) {
        return 2 * length + width;
    }
    
    private int calcPlate(int length) {
        return 2 * length;
    }
    
    private int calcRafters(int length, int width) { // check om dette regnestykke er rigtigt.
        return ((length / 60) + 2) * width;
    }
    
    private int calcWaterBoards(int length, int width) {
        return 2 * length + width;
    }
    
    private int calcRoof(int length, int width) { //under forudsætning af ca. 9 cm overlap
        int rows = length / 100;
        if (length % 100 != 0) {
            rows++;
        }
        return rows * width;
    }
    
    private int calcRoofScrews(int length, int width) { //12 skruer/m2, 50 for buffer
        return ((length * width * 12) / 10000) + 50;
    }
    
    private int calcMetalTape(int length, int width) {
        return 2 * (int) (Math.sqrt(length * length + width * width)) + 1;
    }
    
    private int calcUniBrackets(int length) {
        return (length / 60) + 2;
    }
    
    private int calcFasciaScrews(int length, int width) { // 2 stk. pr. 60 cm omkreds PLUS 4 stk. pr 60 cm (omkreds minus bredde)
        //(pga manglende overstern og vandbræt bagtil) ... 50 for buffer
        return 2 * ((2 * length + 2 * width) / 60) + (4 * (2 * length + width) / 60) + 50;
    }
    
    private int calcBracketScrews(int length) { // 20 pr. universalbeslag ... 50 for buffer
        return (((length / 60) + 2) * 2) * 20 + 50;
    }
    
    public BOM getBom() {
        return bom;
    }
    
}
