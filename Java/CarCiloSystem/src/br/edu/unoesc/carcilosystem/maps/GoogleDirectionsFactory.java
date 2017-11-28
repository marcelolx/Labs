package br.edu.unoesc.carcilosystem.maps;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class GoogleDirectionsFactory {

	private static Document document;
	
	public GoogleDirectionsFactory(){
		document = null; //Necessário? Métodos são estáticos
	}
	
	/**
	 * Retorna documento XML com informações sobre a rota.
	 * 
	 * @param AOrigem
	 * @param ADestino
	 */
	public static void BuscaRota(String AOrigem, String ADestino){
		URL url;
		try {
			url = new URL(
                    "http://maps.google.es/maps/api/directions/xml?origin="
                            + AOrigem + "&destination=" + ADestino
                            + "&sensor=false");
 
            document = getDocumento(url);         
            
            
		} catch (MalformedURLException | DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Retorna a instância do documento.
	 * 
	 * @return Document
	 */
	public static Document getDocumentInstance(){
		return document;
	}
		
	/**
	 * Faz a comunicação com a API Directions do Google, buscando a rota passada por parâmetro.
	 * 
	 * @param url
	 * @return
	 * @throws DocumentException
	 */
	private static Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }
	
	/**
	 * Monta uma URL para ser usada tanto na API Directions como no próprio jxBrowser para o GoogleMaps.
	 * 
	 * @param ANomeLocal
	 * @param ARua
	 * @param ABairro
	 * @param ACidade
	 * @param AEstado
	 * @return String
	 */
	public static String PreparaURL(String ANomeLocal, String ARua, String ABairro, String ACidade, String AEstado){
		String Url = "";
		
		if (!ANomeLocal.trim().isEmpty()){
			Url = Url + ANomeLocal.trim().replace(" ", "+");
		}
		
		if (!ARua.trim().isEmpty()){
			if (!Url.isEmpty()) {
				Url = Url + "+-+";
			}
			
			Url = Url + ARua.trim().replace(" ", "+") + ","; 
		}
		
		if (!ABairro.trim().isEmpty()) {
			if (!Url.isEmpty()) {
				Url = Url + "+-+";
			}
			
			Url = Url + ABairro.trim().replace(" ", "+") + ",";
		}
		
		if (!ACidade.trim().isEmpty()) {
			if (!Url.isEmpty()) {
				Url = Url + "+";
			}
			
			Url = Url + ACidade.trim().replace(" ", "+");
		}
		
		if (!AEstado.trim().isEmpty()) {
			if (!Url.isEmpty()) {
				Url = Url + "+-+";
			}
			
			Url = Url + AEstado.trim().replace(" ", "+");
		}
		
		return removerAcentos(Url);		
	}
	
	/**
	 * Remove todos os acentos de uma string.
	 * 
	 * @param str
	 * @return String
	 */
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	/**
	 * Gera URL para abrir o google maps com uma rota prédifina a partir dos parâmetros passados.
	 * 
	 * @param AOrigem
	 * @param ADestino
	 * @param ALatitudeOrigem
	 * @param ALongitudeOrigem
	 * @param ALatitudeDestino
	 * @param ALongitudeDestino
	 * @return String
	 */
	public static String GenerateURLMapsDir(String AOrigem, String ADestino, String ALatitudeOrigem, String ALongitudeOrigem, String ALatitudeDestino, String ALongitudeDestino){
		return "https://www.google.com.br/maps/dir/" + AOrigem + "/" + ADestino + "/@" + ALatitudeOrigem + "," + ALongitudeOrigem + 
				"z/data=!3m1!4b1!4m13!4m12!1m5!1m1!1s0x94fa5cee880f9245:0x214979ed93201221!2m2!1d" + ALongitudeDestino  + "!2d" + ALatitudeDestino;
	}
	
}
