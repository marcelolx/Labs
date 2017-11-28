package br.edu.unoesc.carcilosystem.maps;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class DirectionsXML {

	/**
	 * Retorna a dist�ncia obtida atrav�s do documento passado por par�metro.
	 * 
	 * @param document
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
    public static String GetDistancia(Document document) {
        List list = document
                .selectNodes("//DirectionsResponse/route/leg/distance/text");
 
        Element element = (Element) list.get(list.size() - 1);
 
        return element.getText();
    }
	
	/**
	 * Obt�m o tempo do percurso a partir do documento passado por par�metro.
	 * 
	 * @param document
	 * @return String
	 */	
	@SuppressWarnings("rawtypes")
    public static String GetTempoPercurso(Document document) {
        List list = document
                .selectNodes("//DirectionsResponse/route/leg/duration/text");
 
        Element element = (Element) list.get(list.size() - 1);
 
        return element.getText();
    }
}
