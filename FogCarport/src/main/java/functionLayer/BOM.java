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
    
    
    // overload konstruktøren med forskelligt antal variable afhængig af carporttype, eller lav flere BOM-klasser

    public BOM(int posts) {
        this.posts = posts;
    }

    public int getPosts() {
        return posts;
    }
    
    
}
