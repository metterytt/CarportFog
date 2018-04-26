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

import functionLayer.BOM;

/**
 *
 * @author mette
 */
public class FlatNoShedCalculator implements CarportCalculator {

    private int length;
    private int width;

    public FlatNoShedCalculator(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public BOM calculateBOM() {
        int posts = calcPosts(length);
        int subFasciaBoards = calcSubFasciaBoards(length, width);
        int fasciaBoards = calcFasciaBoards(length, width);
        int plates = calcPlate(length);
        int rafters = calcRafters(length, width);
        int waterBoards = calcWaterBoards(length, width); // check denne oversættelse
        int roof = calcRoof(length, width);
        int roofScrews = calcRoofScrews(length, width);
        int metalTape = calcMetalTape(length, width);
        int uniBrackets = calcUniBrackets(length);
        int fasciaScrews = calcFasciaScrews(length, width);
        int bracketScrews = calcBracketScrews(length);
        BOM bom = new BOM(posts, subFasciaBoards, fasciaBoards, plates, rafters, waterBoards, roof, roofScrews, metalTape, uniBrackets, fasciaScrews, bracketScrews);
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

}
