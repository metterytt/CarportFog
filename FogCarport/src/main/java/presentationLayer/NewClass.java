///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package presentationLayer;
//
///**
// *
// * @author Rasmus
// */
//public class NewClass {
//
//    public static void main(String[] args) {
//        int numberOfRafters = (700 / 60);
//        int jk = 700 % 60;
//        System.out.println(jk);
//        if (700 % 60 == 0) {
//            numberOfRafters++;
//        }
//        else {
//            numberOfRafters += 2;
//        }
//        System.out.println(numberOfRafters);
//    }
//    
//    
//    <% if (angle == 0) {%>
//
//                    <svg width="1000" height="1000" viewbox="0 -100 <%= width * 3%> <%= length * 2%>"> 
//
//
//                    <%-- stolper --%>
//                    <%
//                        if (posts < 5) {
//                    %> 
//
//                    <rect x="50" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
//                    <rect x="<%= length - 40%>" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
//
//                    <%-- tekst til længde --%>
//                    <text x="<%=length / 2%>" y="<%=height - 230%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>
//
//                    <%-- rem til 4 stolper--%>
//                    <% if (length < 350) {%>
//                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" 
//                          transform="translate(0) rotate(1.7 30 40)"/>
//                    <% }
//                        if (length >= 350) {
//                    %>
//                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" 
//                          transform="translate(0) rotate(1.05 0 15)"/>
//
//                    <% }
//                        }
//                        if (posts > 4) {
//                    %>
//
//                    <rect x="50" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
//                    <rect x="<%= length / 2%>" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
//                    <rect x="<%= length - 40%>" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
//
//                    <%-- tekst til længde --%>
//                    <text x="<%=length / 2%>" y="<%=height - 230%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>
//
//                    <%-- rem til 6 stolper--%>
//                    <% if (length < 600) {%>
//                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1"
//                          transform="translate(0) rotate(1 100 50)"/>
//                    <% }
//                        if (length >= 600) {%>
//                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" transform="translate(0) rotate(0.5 250 50)"/>
//                    transform="translate(0) rotate(0.5 250 50)"/>
//                    <% }
//                        }%>
//
//                    <%-- stiplet linje til jorden --%>
//                    <line x1="0" y1="<%= height + 10%>" x2="<%= length * 1.30%>" y2="<%= height + 10%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>
//
//                    <%-- træ --%>
//                    <polygon points="<%= length + 70%>,15 <%= length + 20%>,170 <%= length + 120%>,170" fill="green" stroke="black" stroke-width="1" />
//                    <rect x="<%= length + 60%>" y="170" width="20" height="50" fill="saddlebrown" stroke="black"/>
//
//                    <%-- tekst for højde og hældning --%> 
//                    <text x="<%=length * 0.05%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde: <%=height%> </text>
//                    <text x="<%=length + 15%>" y="<%=height / 2 - 25%>" fill="black" text-anchor="middle" writing-mode="tb">Hældning på 9 cm. </text>
//
//                    <%-- tag og stern --%>
//                    <line x1="20" y1="10.5" x2="<%= length%>" y2="18" stroke="darkgrey" stroke-width="3"/>
//                    <line x1="20" y1="5" x2="<%= length%>" y2="13" stroke="black" stroke-width="8"/>
//
//                    <%-- streg for hældning --%>
//                    <line x1="20" y1="0" x2="<%= length%>" y2="0" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>
//                    <line x1="<%= length%>" y1="0" x2="<%= length%>" y2="<%= 15%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>
//
//                    <%-- pattern med striber til skur --%>
//                    <defs>
//                    <pattern id="patternSkur"
//                             width="15" height="10"
//                             patternUnits="userSpaceOnUse"
//                             patternTransform="rotate(0 0 0)">
//                        <line stroke="black" stroke-width="3px" y2="10"/>
//                    </pattern>
//                    </defs>
//
//                    <%-- checker om der er skur (fladt tag) --%>
//                    <% if (shedLength != 0) {%>
//
//                    <% if (length < 600) {%>
//                    <polygon points="<%=length - shedLength - 40%>,<%=24%> <%=length - 40%>,<%=29%> <%= length - 40%>,<%= height + 10%> <%= length - shedLength - 40%>,<%= height + 10%>" 
//                             fill="url(#patternSkur)" stroke="black"/>
//                    <% } %>
//                    <% if (length >= 600) {%>
//                    <polygon points="<%=length - shedLength - 40%>,<%=24%> <%=length - 40%>,<%=26%> <%= length - 40%>,<%= height + 10%> <%= length - shedLength - 40%>,<%= height + 10%>" 
//                             fill="url(#patternSkur)" stroke="black"/>
//                    <% }
//                        }%>
//
//                    </svg>
//
//                    <% } %>
//
//    
//    
//}
