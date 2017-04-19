import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19.04.2017.
 */
public class SAXReader {

    private File inputFile;
    private SAXBuilder saxBuilder;
    org.jdom2.Document document;

    private static List<Actor> saxActorsList;
    private static List<Relation> saxRelationsList;
    private static List<UseCase> saxUseCases;
    private Element mainElement;
    public SAXReader() throws JDOMException, IOException {
        init();
    }

    void init() throws JDOMException, IOException {
        inputFile = new File("src/file.xml");

        saxBuilder = new SAXBuilder();

        document = saxBuilder.build(inputFile);

        mainElement = document.getRootElement();
        saxActorsList = new ArrayList<Actor>();
    }

    void readXmlSAX() throws JDOMException, IOException {

        List<Element> children = mainElement.getChildren("packagedElement");
//        System.out.println(children.size());
        Element child;
        for (int i = 0; i < children.size(); i++ ){
            child = children.get(i);

            System.out.println(child.getAttribute("xmi:type"));
            String type = child.getAttributeValue("xmi:type");

            System.out.println("type : " + type );

            if ( type == "uml:Actor"){
                Actor a = new Actor(child.getAttribute("xmi:id").toString(), child.getAttribute("name").toString());
                saxActorsList.add(a);
            }
        }

        for (int j = 0; j < saxActorsList.size(); j++ ){
            System.out.println(saxActorsList.get(j));
        }
    }


}
