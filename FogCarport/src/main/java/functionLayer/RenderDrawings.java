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

//    public static String drawFromAbove(DrawingMeasures dm) {
//        if (dm == null) {
//            return "---Ingen tegning---";
//        }
//        int startingLength = dm.getLength()-10;
//        StringBuilder sb = new StringBuilder();
//        sb.append("<svg height=\"500\" width=\"500\" viewbox=\"0 0 ").append(dm.getWidth() + 150).append(" ").append(dm.getLength() + 60).append(">\"");
//        // remme
//        sb.append("<line x1=\"").append(dm.getWidth() + 15).append(" y1=\"0\" x2=\"").append(dm.getWidth()-15).append(" y2=\"").append(dm.getLength()).append(" stroke=\"black\" stroke-width=\"12\" stroke-opacity = \"0.5\"/>");
//        sb.append("<line x1=\"15\" y1=\"0\" x2=\"15\" y2=\"").append(dm.getLength()).append(" stroke=\"black\" stroke-width=\"12\" stroke-opacity = \"0.5\"/>");
//        // spær
//        for (int i = 0; i < dm.getRafterQty(); i++) {
//            sb.append("<line x1=\"5\" y1=\"").append(startingLength).append(" x2=\"").append(dm.getWidth()-5)" y2=\"<%= startingLength%>\" stroke-width=\"12\" stroke=\"darkgrey\"/>")
//        }
//    }
    public static String drawFromSide(DrawingMeasures dm) {
        if (dm == null) {
            return "---Ingen tegning---";
        }
        StringBuilder sb = new StringBuilder();
        // hvis fladt tag
        if (dm.getAngle() == 0) {
            sb.append("<svg width=\"1000\" height=\"1000\" viewbox=\"0 -100 ")
                    .append(dm.getWidth() * 3).append(" ").append(dm.getLength() * 2).append(">");
            // hvis få stolper
            if (dm.getPosts() < 5) {
                sb.append("<rect x=\"50\" y=\"<%=10%>\" width=\"10\" height=\"").append(dm.getHeight()).append(" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
                sb.append("<rect x=\"").append(dm.getLength() - 40).append(" y=\"<%=10%>\" width=\"10\" height=\"").append(dm.getHeight()).append(" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
                // tekst til længde
                sb.append("<text x=\"").append(dm.getLength() / 2).append(" y=\"").append(dm.getHeight() - 230).append(" fill=\"black\" text-anchor=\"middle\">Længde: ").append(dm.getLength()).append(" </text>");
                // hældning ift længde
                if (dm.getLength() < 350){
                    sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append(" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" transform=\"translate(0) rotate(1.7 30 40)\"/>");
                } else {
                    sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append(" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" transform=\"translate(0) rotate(1.05 0 15)\"/>");
                }
            // hvis mange stolper
            } else { 
                sb.append("<rect x=\"50\" y=\"<%=10%>\" width=\"10\" height=\"").append(dm.getHeight()).append(" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // denne linie er kopi
                sb.append("<rect x=\"").append(dm.getLength() / 2).append(" y=\"<%=10%>\" width=\"10\" height=\"").append(dm.getHeight()).append(" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
                sb.append("<rect x=\"").append(dm.getLength() - 40).append(" y=\"<%=10%>\" width=\"10\" height=\"").append(dm.getHeight()).append(" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // denne linie er kopi
                // tekst til længde
                sb.append("<text x=\"").append(dm.getLength() / 2).append(" y=\"").append(dm.getHeight() - 230).append(" fill=\"black\" text-anchor=\"middle\">Længde: ").append(dm.getLength()).append(" </text>");
                if (dm.getLength() < 600) {
                    sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append(" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" transform=\"translate(0) rotate(1 100 50)\"/>");
                } else {
                    sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append(" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" transform=\"translate(0) rotate(0.5 250 50)\"/>");
                }
            }

        }

        return sb.toString();
    }
}
