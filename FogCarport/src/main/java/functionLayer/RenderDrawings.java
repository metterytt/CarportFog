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
        int startingLength = dm.getLength() - 10;
        StringBuilder sb = new StringBuilder();
        sb.append("<svg height=\"500\" width=\"500\" viewbox=\"0 0 ").append(dm.getWidth() + 150).append(" ").append(dm.getLength() + 60).append(">\"");
        // remme
        sb.append("<line x1=\"").append(dm.getWidth() + 15).append(" y1=\"0\" x2=\"").append(dm.getWidth() - 15).append(" y2=\"").append(dm.getLength()).append(" stroke=\"black\" stroke-width=\"12\" stroke-opacity = \"0.5\"/>");
        sb.append("<line x1=\"15\" y1=\"0\" x2=\"15\" y2=\"").append(dm.getLength()).append(" stroke=\"black\" stroke-width=\"12\" stroke-opacity = \"0.5\"/>");
        // spær
        for (int i = 0; i < dm.getRafterQty(); i++) {
            sb.append("<line x1=\"5\" y1=\"").append(startingLength).append(" x2=\"").append(dm.getWidth() - 5).append(" y2=\"").append(startingLength).append(" stroke-width=\"12\" stroke=\"darkgrey\"/>");
            startingLength -= dm.getRafterGap();
        }
        //stolper

        if (dm.getPosts() < 5) {
            sb.append("<rect x=\"15 - 4\" y=\"").append(dm.getLength() * 0.25).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\" />");
            sb.append("<rect x=\"15 - 4\" y=\"").append(dm.getLength() * 0.75).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\" />");
            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append(" y=\"").append(dm.getLength() * 0.25).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append(" y=\"").append(dm.getLength() * 0.75).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
        } else {
            sb.append("<rect x=\"15 - 4\" y=\"").append(dm.getLength() * 0.1).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"15 - 4\" y=\"").append(dm.getLength() * 0.9).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");

            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append(" y=\"").append(dm.getLength() * 0.1).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append(" y=\"").append(dm.getLength() * 0.9).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");

            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append(" y=\"").append(dm.getLength() / 2).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"15 - 4\" y=\"").append(dm.getLength() / 2).append(" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
        }
        sb.append("<text x=\"").append(dm.getWidth() / 2).append(" y=\"").append(dm.getLength() + 20).append(" fill=\"black\" text-anchor=\"middle\" >Bredde: ").append(dm.getWidth()).append(" </text>");
        sb.append("<text x=\"").append(dm.getWidth() + 20).append(" y=\"").append(dm.getLength() / 2).append(" fill=\"black\" text-anchor=\"middle\" writing-mode=\"tb\">Længde: ").append(dm.getLength()).append(" </text>");

        // stern foran
        sb.append("<line x1=\"0\" y1=\"0\" x2=\"").append(dm.getWidth()).append(" y2=\"0\" stroke-width=\"8\" stroke=\"black\"/>");

        //højre stern
        sb.append("<line x1=\"").append(dm.getWidth()).append(" y1=\"0\" x2=\"").append(dm.getWidth()).append(" y2=\"").append(dm.getLength()).append(" stroke-width=\"4\" stroke=\"black\"/>");
        sb.append("<line x1=\"").append(dm.getWidth() - 5).append(" y1=\"0\" x2=\"").append(dm.getWidth() - 5).append(" y2=\"").append(dm.getLength()).append(" stroke-width=\"4\" stroke=\"black\"/>");

        // stern bagved
        sb.append("<line x1=\"").append(dm.getWidth()).append(" y1=\"").append(dm.getLength()).append(" x2=\"0\" y2=\"").append(dm.getLength()).append(" stroke-width=\"4\" stroke=\"black\"/>");
        sb.append("<line x1=\"").append(dm.getWidth() - 5).append(" y1=\"").append(dm.getLength() - 5).append(" x2=\"5\" y2=\"").append(dm.getLength() - 5).append(" stroke-width=\"4\" stroke=\"black\"/>");

        //venstre stern
        sb.append("<line x1=\"0\" y1=\"").append(dm.getLength()).append(" x2=\"0\" y2=\"0\" stroke-width=\"4\" stroke=\"black\"/>");
        sb.append("<line x1=\"5\" y1=\"").append(dm.getLength()).append(" x2=\"5\" y2=\"0\" stroke-width=\"4\" stroke=\"black\"/>");

        //hulbånd
        sb.append("<line x1=\"").append(dm.getWidth() - dm.getWidth() * 0.9).append(" y1=\"").append(dm.getRafterGap() + 10).append(" x2=\"").append(dm.getWidth() * 0.9).append(" y2=\"").append((dm.getLength() - 10) - dm.getRafterGap()).append(" stroke=\"black\" stroke-dasharray=\"5 5\"/>");
        sb.append("<line x1=\"").append(dm.getWidth() - dm.getWidth() * 0.9).append(" y1=\"").append((dm.getLength() - 10) - dm.getRafterGap()).append(" x2=\"").append(dm.getWidth() * 0.9).append(" y2=\"").append(dm.getRafterGap() + 10).append(" stroke=\"black\" stroke-dasharray=\"5 5\"/>");

        //Skur til venstre
        if (dm.getShedLength() != 0) {
            sb.append("<rect x=\"11\" y=\"15\" height=\"").append(dm.getShedLength()).append(" width=\"").append(dm.getShedWidth()).append(" stroke=\"red\" stroke-width=\"2\" fill=\"none\" stroke-dasharray=\"10 10\"/>");
            //stolper til skur - venstre
            sb.append("<rect x=\"11\" y=\"15\" height=\"15\" width=\"15\" stroke=\"red\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"").append(dm.getShedWidth() - 4).append(" y=\"").append(dm.getShedLength()).append(" height=\"15\" width=\"15\" stroke=\"red\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"").append(dm.getShedWidth() - 4).append(" y=\"15\" height=\"15\" width=\"15\" stroke=\"red\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"11\" y=\"").append(dm.getShedLength()).append(" height=\"15\" width=\"15\" stroke=\"red\" stroke-width=\"3\" fill=\"none\"/>");

        }
        sb.append("</svg>");

        return sb.toString();
    }
}
