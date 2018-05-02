/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

/**
 *
 * @author Rasmus
 */
public class NewClass {

    public static void main(String[] args) {
        int numberOfRafters = (700 / 60);
        int jk = 700 % 60;
        System.out.println(jk);
        if (700 % 60 == 0) {
            numberOfRafters++;
        }
        else {
            numberOfRafters += 2;
        }
        System.out.println(numberOfRafters);
    }

}
