/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

/**
 *
 * @author pernillelorup
 */
public class RenderDrawings {

    public static String drawFromAbove(DrawingMeasures dm) {
        if (dm == null) {
            return "---Ingen tegning---";
        }
        int startingLength = dm.getLength()-10;
        StringBuilder sb = new StringBuilder();
        sb.append("<svg height=\"500\" width=\"500\" viewbox=\"0 0 ").append(dm.getWidth() + 150).append(" ").append(dm.getLength() + 60).append(">\"");
        // remme
        sb.append("<line x1=\"").append(dm.getWidth() + 15).append(" y1=\"0\" x2=\"").append(dm.getWidth()-15).append(" y2=\"").append(dm.getLength()).append(" stroke=\"black\" stroke-width=\"12\" stroke-opacity = \"0.5\"/>");
        sb.append("<line x1=\"15\" y1=\"0\" x2=\"15\" y2=\"").append(dm.getLength()).append(" stroke=\"black\" stroke-width=\"12\" stroke-opacity = \"0.5\"/>");
        // spær
        for (int i = 0; i < dm.getRafterQty(); i++) {
            sb.append("<line x1=\"5\" y1=\"").append(startingLength).append(" x2=\"").append(dm.getWidth()-5)" y2=\"<%= startingLength%>\" stroke-width=\"12\" stroke=\"darkgrey\"/>")
        }
    }
}
