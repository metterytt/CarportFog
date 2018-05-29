/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer.entity;

/**
 * This entity is used to handle all calculations that has been made on the site, to then be inserted into the database.
 * @author Snøvsen
 */
public class CustomerCalculation {

    private final int calcID;
    private final int length;
    private final int width;
    private final int angle;
    private final int shedLength;
    private final int shedWidth;

    public CustomerCalculation(int calcID, int length, int width, int angle, int shedLength, int shedWidth) {
        this.calcID = calcID;
        this.length = length;
        this.width = width;
        this.angle = angle;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }

    public int getCalcID() {
        return calcID;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getAngle() {
        return angle;
    }

    public int getShedLength() {
        return shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

}
