package functionLayer;

public class DrawingMeasures {

    private int length;
    private int width;
    private int angle = 0;
    private int shedLength = 0;
    private int shedWidth = 0;
    private int height = 210;
    private double rafterGap;
    private int rafterQty;
    private int posts;
    private double gableHeight;

    public DrawingMeasures(int length, int width, int angle, int shedLength, int shedWidth) {
        this.length = length;
        this.width = width;
        this.angle = angle;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        rafterQty = calcRafterQty();
        rafterGap = calcRafterGap();
        posts = calcPosts();
        if (angle != 0) gableHeight = calcGableHeight(angle);
    }

    public double getRafterGap() {
        return rafterGap;
    }

    public void setRafterGap(double rafterGap) {
        this.rafterGap = rafterGap;
    }

    public int getRafterQty() {
        return rafterQty;
    }

    public void setRafterQty(int rafterQty) {
        this.rafterQty = rafterQty;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getGableHeight() {
        return gableHeight;
    }

    public void setGableHeight(double gableHeight) {
        this.gableHeight = gableHeight;
    }
    
    private double calcRafterGap() {
        return (length - 15) / (rafterQty - 1);
    }

    private int calcRafterQty() {
        int numberOfRafters = (length / 60);
        if (length % 60 == 0) {
            numberOfRafters++;
        } else {
            numberOfRafters += 2;
        }
        return numberOfRafters;
    }

    private int calcPosts() {
        
        if (length <= 480) {
            return 4;
        } else {
            return 6;
        }
    }

    private double calcGableHeight(int angle) {
        double calcAngle = Math.toRadians(angle);
        return (width / 2) * Math.tan(calcAngle);
    }

}
