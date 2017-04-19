import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.jdom2.*;
import java.io.*;
import java.util.*;

public class Main {

    private static List<Actor> actorsList;
    private static List<Relation> relationsList;
    private static List<UseCase> useCases;

    public static void main(String[] args) throws JDOMException, IOException {
        actorsList = new ArrayList<Actor>();
        relationsList = new ArrayList<Relation>();
        useCases = new ArrayList<UseCase>();

        //readXmlDOM();

        SAXReader saxReader = new SAXReader();
        saxReader.readXmlSAX();

//        System.out.println("Actors:");
//        for ( int i = 0; i < actorsList.size(); i++){
//            System.out.println(actorsList.get(i).getName());
//        }
//        System.out.println("----------------------");
//
//
//        System.out.println("Use Cases:");
//        for ( int i = 0; i < useCases.size(); i++){
//            System.out.println(useCases.get(i).getName());
//        }
//
//        System.out.println("-----------------------");
//        System.out.println("Relations:");
//        for ( int i = 0; i < relationsList.size(); i++){
//            System.out.println(relationsList.get(i).getStartComponent() + relationsList.get(i).getType()+ "s --> " + relationsList.get(i).getEndComponent() );
//        }


    }



    static void readXmlDOM(){
        String currentElement;
        try {
            File fXmlFile = new File("src/file.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("packagedElement");

            Node nNode;
            NodeList childNodes;
            Actor a;
            UseCase usecase;
            Relation relation;
            for (int i = 0; i < nList.getLength(); i++) {
                nNode = nList.item(i);
                childNodes = nNode.getChildNodes();
                String children = nNode.toString();
                System.out.println(children+ " :) ");
                if (childNodes.getLength() != 0 ){

                    Element el = (Element)childNodes.item(1);

                    System.out.print(el.getAttribute( "xmi:type") + "!!!!!");
                }


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    currentElement = eElement.getAttribute("xmi:type");

                    //Is Actor
                    if (currentElement.equals("uml:Actor")) {
                        a = new Actor(eElement.getAttribute("xmi:id"), eElement.getAttribute("name"));
                        actorsList.add(a);
                    }
                    // Is UseCase
                    else if (currentElement.equals("uml:UseCase")) {
                        usecase = new UseCase(eElement.getAttribute("xmi:id"), eElement.getAttribute("name"));
                        useCases.add(usecase);



                        if (childNodes.getLength() != 0) {
                            for (int j = 1; j <= childNodes.getLength(); j++) {
                                Element el = (Element)childNodes.item(j);
                                if ( el.getAttribute("xmi:type").equals("uml:Include")){
                                    relationsList.add(new Relation(el.getAttribute("xmi:id"), "include", usecase.getId(), el.getAttribute("addition")));
                                } else if ( el.getAttribute("xmi:type").equals("uml:Extend")){
                                    relationsList.add(new Relation(el.getAttribute("xmi:id"), "extend", usecase.getId(), el.getAttribute("extendedCase")));
                                }
                            }
                        }
                    }
                    // Is Association
                    else if (currentElement.equals("uml:Association")) {
                        Element end1 = (Element)childNodes.item(1);
                        String end1_id = end1.getAttribute("type");
                        Element end2 = (Element)childNodes.item(2);
                        String end2_id = end2.getAttribute("type");

                        relation = new Relation(eElement.getAttribute("xmi:id"), "association", end1_id, end2_id);
                        relationsList.add(relation);
                    }

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
