package br.edu.unoesc.carcilosystem.maps;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class DirectionsXML {

	@SuppressWarnings("rawtypes")
    public static String GetDistancia(Document document) {
        List list = document
                .selectNodes("//DirectionsResponse/route/leg/distance/text");
 
        Element element = (Element) list.get(list.size() - 1);
 
        return element.getText();
    }
	
	@SuppressWarnings("rawtypes")
    public static String GetTempoPercurso(Document document) {
        List list = document
                .selectNodes("//DirectionsResponse/route/leg/duration/text");
 
        Element element = (Element) list.get(list.size() - 1);
 
        return element.getText();
    }
}
