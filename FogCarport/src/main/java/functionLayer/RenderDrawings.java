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
        sb.append("<svg height=\"500\" width=\"500\" viewbox=\"0 0 ").append(dm.getWidth() + 150).append(" ").append(dm.getLength() + 60).append("\">");
        // remme

        sb.append("<line x1=\"").append(dm.getWidth() - 15).append("\" y1=\"0\" x2=\"").append(dm.getWidth() - 15).append("\" y2=\"").append(dm.getLength()).append("\" stroke=\"black\" stroke-width=\"12\" stroke-opacity = \"0.5\"/>");
        sb.append("<line x1=\"15\" y1=\"0\" x2=\"15\" y2=\"").append(dm.getLength()).append("\" stroke=\"black\" stroke-width=\"12\" stroke-opacity = \"0.5\"/>");
        // spær
        for (int i = 0; i < dm.getRafterQty(); i++) {
            sb.append("<line x1=\"5\" y1=\"").append(startingLength).append("\" x2=\"").append(dm.getWidth() - 5).append("\" y2=\"").append(startingLength).append("\" stroke-width=\"12\" stroke=\"darkgrey\"/>");
            startingLength -= dm.getRafterGap();
        }
        //stolper

        if (dm.getPosts() < 5) {
            sb.append("<rect x=\"11 \" y=\"").append(dm.getLength() * 0.25).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\" />");
            sb.append("<rect x=\"11\" y=\"").append(dm.getLength() * 0.75).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\" />");
            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append("\" y=\"").append(dm.getLength() * 0.25).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append("\" y=\"").append(dm.getLength() * 0.75).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
        } else {
            sb.append("<rect x=\"11\" y=\"").append(dm.getLength() * 0.1).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"11\" y=\"").append(dm.getLength() * 0.9).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");

            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append("\" y=\"").append(dm.getLength() * 0.1).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append("\" y=\"").append(dm.getLength() * 0.9).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");

            sb.append("<rect x=\"").append(dm.getWidth() - 15 - 11).append("\" y=\"").append(dm.getLength() / 2).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"11\" y=\"").append(dm.getLength() / 2).append("\" height=\"15\" width=\"15\" stroke=\"black\" stroke-width=\"3\" fill=\"none\"/>");
        }
        sb.append("<text x=\"").append(dm.getWidth() / 2).append("\" y=\"").append(dm.getLength() + 20).append("\" fill=\"black\" text-anchor=\"middle\" >Bredde: ").append(dm.getWidth()).append(" </text>");
        sb.append("<text x=\"").append(dm.getWidth() + 20).append("\" y=\"").append(dm.getLength() / 2).append("\" fill=\"black\" text-anchor=\"middle\" writing-mode=\"tb\">Længde: ").append(dm.getLength()).append(" </text>");

        // stern foran
        sb.append("<line x1=\"0\" y1=\"0\" x2=\"").append(dm.getWidth()).append("\" y2=\"0\" stroke-width=\"8\" stroke=\"black\"/>");

        //højre stern
        sb.append("<line x1=\"").append(dm.getWidth()).append("\" y1=\"0\" x2=\"").append(dm.getWidth()).append("\" y2=\"").append(dm.getLength()).append("\" stroke-width=\"4\" stroke=\"black\"/>");
        sb.append("<line x1=\"").append(dm.getWidth() - 5).append("\" y1=\"0\" x2=\"").append(dm.getWidth() - 5).append("\" y2=\"").append(dm.getLength()).append("\" stroke-width=\"4\" stroke=\"black\"/>");

        // stern bagved
        sb.append("<line x1=\"").append(dm.getWidth()).append("\" y1=\"").append(dm.getLength()).append("\" x2=\"0\" y2=\"").append(dm.getLength()).append("\" stroke-width=\"4\" stroke=\"black\"/>");
        sb.append("<line x1=\"").append(dm.getWidth() - 5).append("\" y1=\"").append(dm.getLength() - 5).append("\" x2=\"5\" y2=\"").append(dm.getLength() - 5).append("\" stroke-width=\"4\" stroke=\"black\"/>");

        //venstre stern
        sb.append("<line x1=\"0\" y1=\"").append(dm.getLength()).append("\" x2=\"0\" y2=\"0\" stroke-width=\"4\" stroke=\"black\"/>");
        sb.append("<line x1=\"5\" y1=\"").append(dm.getLength()).append("\" x2=\"5\" y2=\"0\" stroke-width=\"4\" stroke=\"black\"/>");

        //hulbånd
        sb.append("<line x1=\"").append(dm.getWidth() - dm.getWidth() * 0.9).append("\" y1=\"").append(dm.getRafterGap() + 10).append("\" x2=\"").append(dm.getWidth() * 0.9).append("\" y2=\"").append((dm.getLength() - 10) - dm.getRafterGap()).append("\" stroke=\"black\" stroke-dasharray=\"5 5\"/>");
        sb.append("<line x1=\"").append(dm.getWidth() - dm.getWidth() * 0.9).append("\" y1=\"").append((dm.getLength() - 10) - dm.getRafterGap()).append("\" x2=\"").append(dm.getWidth() * 0.9).append("\" y2=\"").append(dm.getRafterGap() + 10).append("\" stroke=\"black\" stroke-dasharray=\"5 5\"/>");

        //Skur til venstre
        if (dm.getShedLength() != 0) {
            sb.append("<rect x=\"11\" y=\"15\" height=\"").append(dm.getShedLength()).append("\" width=\"").append(dm.getShedWidth()).append("\" stroke=\"red\" stroke-width=\"2\" fill=\"none\" stroke-dasharray=\"10 10\"/>");
            //stolper til skur - venstre
            sb.append("<rect x=\"11\" y=\"15\" height=\"15\" width=\"15\" stroke=\"red\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"").append(dm.getShedWidth() - 4).append("\" y=\"").append(dm.getShedLength()).append("\" height=\"15\" width=\"15\" stroke=\"red\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"").append(dm.getShedWidth() - 4).append("\" y=\"15\" height=\"15\" width=\"15\" stroke=\"red\" stroke-width=\"3\" fill=\"none\"/>");
            sb.append("<rect x=\"11\" y=\"").append(dm.getShedLength()).append("\" height=\"15\" width=\"15\" stroke=\"red\" stroke-width=\"3\" fill=\"none\"/>");

        }
        sb.append("</svg>");
        return sb.toString();

    }

    public static String drawFromSide(DrawingMeasures dm) {
        if (dm == null) {
            return "---Ingen tegning---";
        }
        StringBuilder sb = new StringBuilder();
        // hvis fladt tag
        if (dm.getAngle() == 0) {
            sb.append("<svg width=\"1000\" height=\"1000\" viewbox=\"0 -100 ")
                    .append(dm.getWidth() * 3).append(" ").append(dm.getLength() * 2).append("\">");
            // hvis få stolper
            if (dm.getPosts() < 5) {
                sb.append("<rect x=\"50\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
                sb.append("<rect x=\"").append(dm.getLength() - 40).append("\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
                // tekst til længde
                sb.append("<text x=\"").append(dm.getLength() / 2).append("\" y=\"").append(dm.getHeight() - 230).append("\" fill=\"black\" text-anchor=\"middle\">Længde: ").append(dm.getLength()).append(" </text>");
                // hældning ift længde
                if (dm.getLength() < 350) {
                    sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append("\" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" transform=\"translate(0) rotate(1.7 30 40)\"/>");

                } else {
                    sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append("\" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" transform=\"translate(0) rotate(1.05 0 15)\"/>");
                }
                // hvis mange stolper
            } else {
                sb.append("<rect x=\"50\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // denne linie er kopi
                sb.append("<rect x=\"").append(dm.getLength() / 2).append("\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
                sb.append("<rect x=\"").append(dm.getLength() - 40).append("\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // denne linie er kopi

                // tekst til længde
                sb.append("<text x=\"").append(dm.getLength() / 2).append("\" y=\"").append(dm.getHeight() - 230).append("\" fill=\"black\" text-anchor=\"middle\">Længde: ").append(dm.getLength()).append(" </text>");
                // rem til 6 stolper
                if (dm.getLength() < 600) {
                    sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append("\" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" transform=\"translate(0) rotate(1 100 50)\"/>");
                } else {
                    sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append("\" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" transform=\"translate(0) rotate(0.5 250 50)\"/>");
                }
            }
            // stiplet linie til jorden
            sb.append("<line x1=\"0\" y1=\"").append(dm.getHeight() + 10).append("\" x2=\"").append(dm.getLength() * 1.30).append("\" y2=\"").append(dm.getHeight() + 10).append("\" stroke=\"black\" stroke-width=\"2\" stroke-dasharray=\"5 5\"/>");
            // træ
            sb.append("<polygon points=\"").append(dm.getLength() + 70).append(",15 ").append(dm.getLength() + 20).append(",170 ").append(dm.getLength() + 120).append(",170\" fill=\"green\" stroke=\"black\" stroke-width=\"1\" />");
            sb.append("<rect x=\"").append(dm.getLength() + 60).append("\" y=\"170\" width=\"20\" height=\"50\" fill=\"saddlebrown\" stroke=\"black\"/>");
            // tekst for højde og hældning
            sb.append("<text x=\"").append(dm.getLength() * 0.05).append("\" y=\"").append(dm.getHeight() / 2).append("\" fill=\"black\" text-anchor=\"middle\" writing-mode=\"tb\">Højde: ").append(dm.getHeight()).append(" </text>");
            sb.append("<text x=\"").append(dm.getLength() + 15).append("\" y=\"").append(dm.getHeight() / 2 - 25).append("\" fill=\"black\" text-anchor=\"middle\" writing-mode=\"tb\">Hældning på 9 cm. </text>");
            // tag og stern
            sb.append("<line x1=\"20\" y1=\"10.5\" x2=\"").append(dm.getLength()).append("\" y2=\"18\" stroke=\"darkgrey\" stroke-width=\"3\"/>");
            sb.append("<line x1=\"20\" y1=\"5\" x2=\"").append(dm.getLength()).append("\" y2=\"13\" stroke=\"black\" stroke-width=\"8\"/>");
            // streg for hældning
            sb.append("<line x1=\"20\" y1=\"0\" x2=\"").append(dm.getLength()).append("\" y2=\"0\" stroke=\"black\" stroke-width=\"2\" stroke-dasharray=\"5 5\"/>");
            sb.append("<line x1=\"").append(dm.getLength()).append("\" y1=\"0\" x2=\"").append(dm.getLength()).append("\" y2=\"<%= 15%>\" stroke=\"black\" stroke-width=\"2\" stroke-dasharray=\"5 5\"/>");
            // pattern med striber til skur
            sb.append("<defs><pattern id=\"patternSkur\" width=\"15\" height=\"10\"patternUnits=\"userSpaceOnUse\"patternTransform=\"rotate(0 0 0)\"><line stroke=\"black\" stroke-width=\"3px\" y2=\"10\"/></pattern></defs>");

            if (dm.getShedLength() != 0) {
                if (dm.getLength() < 600) {
                    sb.append("<polygon points=\"").append(dm.getLength() - dm.getShedLength() - 40).append(",24 ").append(dm.getLength() - 40).append(",29 ").append(dm.getLength() - 40).append(",").append(dm.getHeight() + 10).append(" ").append(dm.getLength() - dm.getShedLength() - 40).append(",").append(dm.getHeight() + 10).append("\"");
                } else {
                    sb.append("<polygon points=\"").append(dm.getLength() - dm.getShedLength() - 40).append(",24 ").append(dm.getLength() - 40).append(",26 ").append(dm.getLength() - 40).append(",").append(dm.getHeight() + 10).append(" ").append(dm.getLength() - dm.getShedLength() - 40).append(",").append(dm.getHeight() + 10).append("\"");
                }
                sb.append("fill=\"url(#patternSkur)\" stroke=\"black\"/>");
            }
            sb.append("</svg>");
        }

        if (dm.getAngle() > 0) {
            sb.append("<svg width=\"1000\" height=\"1000\" viewbox=\"0 -100 ")
                    .append(dm.getWidth() * 3).append(" ").append(dm.getLength() * 2).append("\">"); // denne linie er dobbelt
            if (dm.getPosts() < 5) {
                sb.append("<rect x=\"50\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // denne linie er dobbelt
                sb.append("<rect x=\"").append(dm.getLength() - 40).append("\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // samme
                // tekst til længde
                if (dm.getShedLength() == 0) {
                    sb.append("<text x=\"").append(dm.getLength() / 2).append("\" y=\"").append(dm.getHeight() - 160).append("\" fill=\"black\" text-anchor=\"middle\">Længde: ").append(dm.getLength()).append(" </text>");
                } else {
                    sb.append("<text x=\"").append(dm.getLength() * 0.3).append("\" y=\"").append(dm.getHeight() - 160).append("\" fill=\"black\" text-anchor=\"middle\">Længde: ").append(dm.getLength()).append(" </text>");
                }
                // rem til 4 stolper
                sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append("\" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
            } else {
                sb.append("<rect x=\"50\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // denne linie er dobbelt
                sb.append("<rect x=\"").append(dm.getLength() / 2).append("\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // samme
                sb.append("<rect x=\"").append(dm.getLength() - 40).append("\" y=\"10\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // samme
                // tekst til længde
                sb.append("<text x=\"").append(dm.getLength() * 0.3).append("\" y=\"").append(dm.getHeight() - 160).append("\" fill=\"black\" text-anchor=\"middle\">Længde: ").append(dm.getLength()).append(" </text>");
                // rem
                sb.append("<rect x=\"20\" y=\"12\" width=\"").append(dm.getLength() - 20).append("\" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // samme
            }
            // stiplet linie til jorden
            sb.append("<line x1=\"0\" y1=\"").append(dm.getHeight() + 10).append("\" x2=\"").append(dm.getLength() * 1.30).append("\" y2=\"").append(dm.getHeight() + 10).append("\" stroke=\"black\" stroke-width=\"2\" stroke-dasharray=\"5 5\"/>"); // samme
            // træ
            sb.append("<polygon points=\"").append(dm.getLength() + 70).append(",15 ").append(dm.getLength() + 20).append(",170 ").append(dm.getLength() + 120).append(",170\" fill=\"green\" stroke=\"black\" stroke-width=\"1\" />"); // samme
            sb.append("<rect x=\"").append(dm.getLength() + 60).append("\" y=\"170\" width=\"20\" height=\"50\" fill=\"saddlebrown\" stroke=\"black\"/>"); // samme
            // tekst for højde
            sb.append("<text x=\"").append(dm.getLength() * 0.05).append("\" y=\"").append(dm.getHeight() / 2).append("\" fill=\"black\" text-anchor=\"middle\" writing-mode=\"tb\">Højde: ").append(dm.getHeight()).append(" </text>"); // samme
            // tag og stern
            sb.append("<line x1=\"20\" y1=\"10.5\" x2=\"").append(dm.getLength()).append("\" y2=\"10.5\" stroke=\"darkgrey\" stroke-width=\"3\"/>");
            sb.append("<line x1=\"20\" y1=\"5\" x2=\"").append(dm.getLength()).append("\" y2=\"5\" stroke=\"black\" stroke-width=\"8\"/>");
            // taget med rejsning
            if (dm.getPosts() < 5) {
                // spær i toppen - check om minusangivelserne er ok
                sb.append("<rect x=\"30\" y=\"-70\" width=\"").append(dm.getLength() - 42).append("\" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" />");
                sb.append("<rect x=\"20\" y=\"-75\" width=\"10\" height=\"76\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
                if (dm.getLength() < 350) {
                    sb.append("<rect x=\"").append(dm.getLength() - 13).append("\" y=\"-75\" width=\"10\" height=\"76\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
                } else {
                    sb.append("<rect x=\"").append(dm.getLength() - 11).append("\" y=\"-75\" width=\"10\" height=\"76\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
                }
            } else {
                // spær i toppen
                sb.append("<rect x=\"30\" y=\"-70\" width=\"").append(dm.getLength() - 42).append("\" height=\"10\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\" />"); // samme
                sb.append("<rect x=\"20\" y=\"-75\" width=\"10\" height=\"76\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // samme
                sb.append("<rect x=\"").append(dm.getLength() - 11).append("\" y=\"-75\" width=\"10\" height=\"76\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>"); // samme
            }
            // pattern med striber til tag
            sb.append("<defs><pattern id=\"pattern\"width=\"15\" height=\"10\" patternUnits=\"userSpaceOnUse\"patternTransform=\"rotate(0 0 0)\">");
            sb.append("<line stroke=\"darkgrey\" stroke-width=\"3px\" y2=\"10\"/></pattern></defs>");
            // lodrette striber på tag
            sb.append("<rect x=\"31\" y=\"-60 \"width=\"").append(dm.getLength() - 42).append("\" height=\"61\"");
            sb.append("fill=\"url(#pattern)\"stroke=\"none\"stroke-width=\"2px\" />");
            // pattern med striber til skur
            sb.append("<defs><pattern id=\"patternSkur\" width=\"15\" height=\"10\"patternUnits=\"userSpaceOnUse\"patternTransform=\"rotate(0 0 0)\"><line stroke=\"black\" stroke-width=\"3px\" y2=\"10\"/></pattern></defs>");
            if (dm.getShedLength() != 0) {
                sb.append("<polygon points=\"").append(dm.getLength() - dm.getShedLength() - 40).append(",22 ").append(dm.getLength() - 40).append(",22 ").append(dm.getLength() - 40).append(",").append(dm.getHeight() + 10).append(" ").append(dm.getLength() - dm.getShedLength() - 40).append(",").append(dm.getHeight() + 10).append("\"");
                sb.append("fill=\"url(#patternSkur)\" stroke=\"black\"/>");
            }
            sb.append("</svg>");
        }

        return sb.toString();
    }

    public static String drawFromFront(DrawingMeasures dm) {
        if (dm == null || dm.getAngle() == 0) {
            return "---Ingen tegning---";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<svg width=\"1000\" height=\"1000\" viewbox=\"-200 -200 ").append(dm.getWidth() * 3).append(" ").append(dm.getLength() * 2).append("\">");
        //stolper
        sb.append("<rect x=\"30\" y=\"0\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
        sb.append("<rect x=\"").append(dm.getWidth() + 10).append("\" y=\"0\" width=\"10\" height=\"").append(dm.getHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");
        //over- og understern
        sb.append("<rect x=\"15\" y=\"-10\" width=\"").append(dm.getWidth() + 20).append("\" height=\"10\" fill=\"snow\" stroke=\"black\"/>");

        //gavl
        sb.append("<rect x=\"").append(dm.getWidth() / 2 + 20).append("\" y=\"").append(-10 - dm.getGableHeight()).append("\" width=\"10\" height=\"").append(dm.getGableHeight()).append("\" fill=\"snow\" stroke=\"black\" stroke-width=\"1\"/>");

        //sb.append("<polygon points=\"").append(dm.getWidth()/2 + 25).append(",").append(-13 - dm.getGableHeight()).append(" ").append(dm.getWidth()/2 + 25).append(",").append(-13 - dm.getGableHeight()).append(" \" 15,-5 15,-15\" fill=\"snow\" stroke=\"black\"/>");
        sb.append("<polygon points=\"").append(dm.getWidth() / 2 + 25).append(",").append(-13 - dm.getGableHeight()).append(" ").append(dm.getWidth() / 2 + 25).append(",").append(-3 - dm.getGableHeight()).append(" ").append(" 15,-5 15,-15\" fill=\"snow\" stroke=\"black\"/>");
        sb.append("<polygon points=\"").append(dm.getWidth() / 2 + 25).append(",").append(-13 - dm.getGableHeight()).append(" ").append(dm.getWidth() / 2 + 25).append(",").append(-3 - dm.getGableHeight()).append(" ").append(dm.getWidth() + 35).append(",").append("-5 ").append(dm.getWidth() + 35).append(",").append("-15\" fill=\"snow\" stroke=\"black\"/>");

        //streg for højde - med tag
        sb.append("<line x1=\"").append(dm.getWidth() + 50).append("\" y1=\"").append(-20 - dm.getGableHeight()).append("\" x2=\"").append(dm.getWidth() + 50).append("\" y2=\"").append(dm.getHeight()).append("\" stroke=\"black\" />");
        sb.append("<text x=\"").append(dm.getWidth() + 60).append("\" y=\"").append(dm.getHeight() / 2).append("\" fill=\"black\" text-anchor=\"middle\" writing-mode=\"tb\">Højde med tag: ").append(dm.getHeight() + (int) dm.getGableHeight()).append("\" </text>");
        //streg for højde - uden tag
        sb.append("<line x1=\"").append(dm.getWidth() + 80).append("\" y1=\"-15\" x2=\"").append(dm.getWidth() + 80).append("\" y2=\"").append(dm.getHeight()).append("\" stroke=\"black\" />");
        sb.append("<text x=\"").append(dm.getWidth() + 90).append("\" y=\"").append(dm.getHeight() / 2).append("\" fill=\"black\" text-anchor=\"middle\" writing-mode=\"tb\">Højde uden tag: ").append(dm.getHeight()).append("\" </text>");
        //streg langs jorden
        sb.append("<line x1=\"0\" y1=\"").append(dm.getHeight()).append("\" x2=\"").append(dm.getWidth() + 150).append("\" y2=\"").append(dm.getHeight()).append("\" stroke=\"black\"/>");
        sb.append("</svg>");

        return sb.toString();
    }
}
