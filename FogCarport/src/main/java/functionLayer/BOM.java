/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

/**
 *
 * @author mette
 */
public class BOM {

    // skal have mange variable....
    private int posts;
    private int subFasciaBoards;
    private int fasciaBoards;
    private int plates;
    private int rafters;
    private int waterBoards;
    private int roof;
    private int roofScrews;
    private int metalTape;
    private int uniBrackets;
    private int fasciaScrews;
    private int bracketScrews;// screws for brackets and metalTape = 20x 2x number of rafters
    // bolts = number of posts DETTE MÅ VI CHECKE - det giver ikke mening med antallet i eksempelstyklisten
    // squarepieces = 2x number of posts

    // overload konstruktøren med forskelligt antal variable afhængig af carporttype, eller lav flere BOM-klasser
    public BOM(int posts, int subFasciaBoards, int fasciaBoards, int plates, int rafters,
            int waterBoards, int roof, int roofScrews, int metalTape, int uniBrackets, int fasciaScrews, int bracketScrews) {
        this.posts = posts;
        this.subFasciaBoards = subFasciaBoards;
        this.fasciaBoards = fasciaBoards;
        this.plates = plates;
        this.rafters = rafters;
        this.waterBoards = waterBoards;
        this.roof = roof;
        this.roofScrews = roofScrews;
        this.metalTape = metalTape;
        this.uniBrackets = uniBrackets;
        this.fasciaScrews = fasciaScrews;
        this.bracketScrews = bracketScrews;
    }
    
    public BOM() {
        
    }

    public int getPosts() {
        return posts;
    }

    public int getSubFasciaBoards() {
        return subFasciaBoards;
    }

    public int getFasciaBoards() {
        return fasciaBoards;
    }

    public int getPlates() {
        return plates;
    }

    public int getRafters() {
        return rafters;
    }

    public int getWaterBoards() {
        return waterBoards;
    }

    public int getRoof() {
        return roof;
    }

    public int getRoofScrews() {
        return roofScrews;
    }

    public int getMetalTape() {
        return metalTape;
    }

    public int getUniBrackets() {
        return uniBrackets;
    }
    
    public int getFasciaScrews() {
        return fasciaScrews;
    }

    public int getBracketScrews() {
        return bracketScrews;
    }

    

}
