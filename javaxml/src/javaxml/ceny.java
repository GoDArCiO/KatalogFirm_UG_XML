package javaxml;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

//rejestr do tworzenia implementacji DOM
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

//Implementacja DOM Level 3 Load & Save
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSParser; // Do serializacji (zapisywania) dokumentow
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;
import org.w3c.dom.ls.LSOutput;

//Konfigurator i obsluga bledow
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;

//Do pracy z dokumentem
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class ceny {
	public static Document document;

	private static String getNodeValueByTagName(Node parentNode, String tagNameOfNode)
	{
	    String nodeValue = "";
	    if (((Element) parentNode).getElementsByTagName(tagNameOfNode).getLength() != 0)
	        if (((Element) ((Element) parentNode).getElementsByTagName(tagNameOfNode).item(0)).hasChildNodes())
	        {
	            nodeValue = ((Node) ((Element) ((Element) parentNode).getElementsByTagName(tagNameOfNode).item(0)).getChildNodes().item(0)).getNodeValue();
	        }
	    return nodeValue;
	}

	private static String getNodeAttributeByTagName(Node parentNode, String tagNameOfAttr)
	{
	    String nodeValue = "";

	    NamedNodeMap questNodeAttr = parentNode.getAttributes();

	    if (questNodeAttr.getLength() != 0)
	        nodeValue = questNodeAttr.getNamedItem(tagNameOfAttr).getTextContent();

	    return nodeValue;
	}
	
	static List<Firma> lista;
	static List<Wyswietlenie> lista2;
	
	public static void main(String[] args){
		pop(args);
		SwingScreen.main(args);
	}
	
	public static void pop(String[] args) {//-------------------------------------------------

		if (args.length == 0) {
			printUsage();
			System.exit(1);
		}

		try {
			/*
			 * ustawienie systemowej wlasnosci (moze byc dokonane w innym
			 * miejscu, pliku konfiguracyjnym w systemie itp.) konkretna
			 * implementacja DOM
			 */

			DOMImplementationRegistry registry = DOMImplementationRegistry
					.newInstance();

			// pozyskanie implementacji Load & Save DOM Level 3 z rejestru
			DOMImplementationLS impl = (DOMImplementationLS) registry
					.getDOMImplementation("LS");

			// stworzenie DOMBuilder
			LSParser builder = impl.createLSParser(
					DOMImplementationLS.MODE_SYNCHRONOUS, null);

			// pozyskanie konfiguratora - koniecznie zajrzec do dokumentacji co
			// mozna poustawiac
			DOMConfiguration config = builder.getDomConfig();

			// stworzenie DOMErrorHandler i zarejestrowanie w konfiguratorze
			DOMErrorHandler errorHandler = getErrorHandler();
			config.setParameter("error-handler", errorHandler);

			// set validation feature
			config.setParameter("validate", Boolean.TRUE);

			// set schema language
			config.setParameter("schema-type",
					"http://www.w3.org/2001/XMLSchema");

			// set schema location
			config.setParameter("schema-location", args[1]);

			System.out.println(args[0]+" validates against "+args[1]+" "+validateXMLSchema(args[1], args[0]));

			// sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
			document = builder.parseURI(args[0]);

			Node katalog = document.getFirstChild();
			Node firmy = katalog.getFirstChild();
			document.getDocumentElement().normalize();

	        System.out.println("Root element: " + document.getDocumentElement().getNodeName());

	        NodeList list = document.getElementsByTagName("firma");
	        List<Firma> listt = new ArrayList<>();
		      for (int temp = 0; temp < list.getLength(); temp++) {
		    	  Node node = list.item(temp);
		    	  String nip= getNodeAttributeByTagName(node, "nip");
		    	  String nazwa= getNodeValueByTagName(node, "nazwa");
		    	  String iban= getNodeValueByTagName(node, "iban");
		    	  String telefon= getNodeValueByTagName(node, "telefon");
		    	  String mail= getNodeValueByTagName(node, "mail");
		    	  String pesel= getNodeValueByTagName(node, "pesel");
		    	  String dowod_osobisty= getNodeValueByTagName(node, "dowod_osobisty");

		    	  Firma tempadres = new Firma(nip,nazwa,iban,telefon,mail,pesel,dowod_osobisty);
					listt.add(tempadres);
		      }

		      lista=listt;

		      
		      NodeList list2 = document.getElementsByTagName("wyswietlenie");
		      List<Wyswietlenie> listt2 = new ArrayList<>();
		      for (int temp = 0; temp < list2.getLength(); temp++) {
		    	  Node node = list2.item(temp);
		    	  String nip= getNodeAttributeByTagName(node, "nipFirmy");
		    	  String dStart= getNodeValueByTagName(node, "dataStart");
		    	  String dStop= getNodeValueByTagName(node, "dataStop");
		    	  String koszt= getNodeValueByTagName(node, "koszt");
		    	  
		    	  Wyswietlenie tempadres = new Wyswietlenie(nip,dStart,dStop,koszt);
		    	  listt2.add(tempadres);
		      }
		      
		      lista2=listt2;




			// pozyskanie serializatora
			LSSerializer domWriter = impl.createLSSerializer();
			// pobranie konfiguratora dla serializatora
			config = domWriter.getDomConfig();
			config.setParameter("xml-declaration", Boolean.TRUE);

			// pozyskanie i konfiguracja Wyjscia
			LSOutput dOut = impl.createLSOutput();

			dOut.setByteStream(new FileOutputStream(args[2]));
			System.out.println("zapisywanie " + args[2] + "...");
			System.out.println("Serializing document... ");
			//int min=Collections.min(listC); 
			//System.out.println("najnizsza cena "+min);
			domWriter.write(document, dOut);

			// Wyjscie na ekran
			// dOut.setByteStream(System.out);
			// domWriter.writeNode(System.out, document);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void add(String[] args,String xnip,String nazwa,String iban,String telefon,String mail,String pesel,String dowodosobisty) {//-------------------------------------------------

		try {
			/*
			 * ustawienie systemowej wlasnosci (moze byc dokonane w innym
			 * miejscu, pliku konfiguracyjnym w systemie itp.) konkretna
			 * implementacja DOM
			 */

			DOMImplementationRegistry registry = DOMImplementationRegistry
					.newInstance();

			// pozyskanie implementacji Load & Save DOM Level 3 z rejestru
			DOMImplementationLS impl = (DOMImplementationLS) registry
					.getDOMImplementation("LS");

			// stworzenie DOMBuilder
			LSParser builder = impl.createLSParser(
					DOMImplementationLS.MODE_SYNCHRONOUS, null);

			// pozyskanie konfiguratora - koniecznie zajrzec do dokumentacji co
			// mozna poustawiac
			DOMConfiguration config = builder.getDomConfig();

			// stworzenie DOMErrorHandler i zarejestrowanie w konfiguratorze
			DOMErrorHandler errorHandler = getErrorHandler();
			config.setParameter("error-handler", errorHandler);

			// set validation feature
			config.setParameter("validate", Boolean.TRUE);

			// set schema language
			config.setParameter("schema-type",
					"http://www.w3.org/2001/XMLSchema");

			// set schema location
			config.setParameter("schema-location", args[1]);

			System.out.println(args[0]+" validates against "+args[1]+" "+validateXMLSchema(args[1], args[0]));

			// sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
			document = builder.parseURI(args[0]);

			Node katalog = document.getFirstChild();
			Node firmy = katalog.getFirstChild();
			document.getDocumentElement().normalize();

	        System.out.println("Root element: " + document.getDocumentElement().getNodeName());

	        
	        Element x = document.createElement("firma");
	        Element nazw = document.createElement("nazwa");
	        Text tnazwa= document.createTextNode(""+nazwa);
	        nazw.appendChild(tnazwa);
	        Element iba = document.createElement("iban");
	        Text tiban= document.createTextNode(""+iban);
	        iba.appendChild(tiban);
	        Element tel = document.createElement("telefon");
	        Text ttel= document.createTextNode(""+telefon);
	        tel.appendChild(ttel);
	        Element mai = document.createElement("mail");
	        Text tmail= document.createTextNode(""+mail);
	        mai.appendChild(tmail);
	        Element pese = document.createElement("pesel");
	        Text tpesel= document.createTextNode(""+pesel);
	        pese.appendChild(tpesel);
	        Element dowod_osobist = document.createElement("dowod_osobisty");
	        Text tdowod_osobisty= document.createTextNode(""+dowodosobisty);
	        dowod_osobist.appendChild(tdowod_osobisty);
	        Element kontak = document.createElement("kontakt");
	        Element zglaszajac = document.createElement("zglaszajacy");
	        kontak.appendChild(tel);
	        kontak.appendChild(mai);
	        zglaszajac.appendChild(pese);
	        zglaszajac.appendChild(dowod_osobist);
	        
	        x.appendChild(nazw);
	        x.appendChild(iba);
	        x.appendChild(kontak);
	        x.appendChild(zglaszajac);
	        ((Element)x).setAttribute("nip",xnip);
	        
	        document.getElementsByTagName("firmy").item(0).appendChild(x);

// pozyskanie serializatora
LSSerializer domWriter = impl.createLSSerializer();
// pobranie konfiguratora dla serializatora
config = domWriter.getDomConfig();
config.setParameter("xml-declaration", Boolean.TRUE);

// pozyskanie i konfiguracja Wyjscia
LSOutput dOut = impl.createLSOutput();

dOut.setByteStream(new FileOutputStream(args[0]));
System.out.println("zapisywanie " + args[0] + "...");
System.out.println("Serializing document... ");
//int min=Collections.min(listC); 
//System.out.println("najnizsza cena "+min);
domWriter.write(document, dOut);

// Wyjscie na ekran
// dOut.setByteStream(System.out);
// domWriter.writeNode(System.out, document);

} catch (Exception ex) {
ex.printStackTrace();
}
		}

	public static void rm(String[] args,String xnip,Boolean firma) {//-------------------------------------------------

		try {
			/*
			 * ustawienie systemowej wlasnosci (moze byc dokonane w innym
			 * miejscu, pliku konfiguracyjnym w systemie itp.) konkretna
			 * implementacja DOM
			 */

			DOMImplementationRegistry registry = DOMImplementationRegistry
					.newInstance();

			// pozyskanie implementacji Load & Save DOM Level 3 z rejestru
			DOMImplementationLS impl = (DOMImplementationLS) registry
					.getDOMImplementation("LS");

			// stworzenie DOMBuilder
			LSParser builder = impl.createLSParser(
					DOMImplementationLS.MODE_SYNCHRONOUS, null);

			// pozyskanie konfiguratora - koniecznie zajrzec do dokumentacji co
			// mozna poustawiac
			DOMConfiguration config = builder.getDomConfig();

			// stworzenie DOMErrorHandler i zarejestrowanie w konfiguratorze
			DOMErrorHandler errorHandler = getErrorHandler();
			config.setParameter("error-handler", errorHandler);

			// set validation feature
			config.setParameter("validate", Boolean.TRUE);

			// set schema language
			config.setParameter("schema-type",
					"http://www.w3.org/2001/XMLSchema");

			// set schema location
			config.setParameter("schema-location", args[1]);

			System.out.println(args[0]+" validates against "+args[1]+" "+validateXMLSchema(args[1], args[0]));

			// sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
			document = builder.parseURI(args[0]);

			Node katalog = document.getFirstChild();
			Node firmy = katalog.getFirstChild();
			document.getDocumentElement().normalize();

	        System.out.println("Root element: " + document.getDocumentElement().getNodeName());
if(firma) {
	        NodeList list = document.getElementsByTagName("firma");
	        List<Firma> listt = new ArrayList<>();
		      for (int temp = 0; temp < list.getLength(); temp++) {
		    	  Node node = list.item(temp);
		    	  String nip= getNodeAttributeByTagName(node, "nip");
		    	  String nazwa= getNodeValueByTagName(node, "nazwa");
		    	  String iban= getNodeValueByTagName(node, "iban");
		    	  String telefon= getNodeValueByTagName(node, "telefon");
		    	  String mail= getNodeValueByTagName(node, "mail");
		    	  String pesel= getNodeValueByTagName(node, "pesel");
		    	  String dowod_osobisty= getNodeValueByTagName(node, "dowod_osobisty");

		    	  if(nip.equals(xnip)) {
		    		  Element element = (Element) node;
				        // remove the specific node
				        element.getParentNode().removeChild(element);
				        document.normalize();
		    	  }
		      }
}

if(!firma) {
		      NodeList list2 = document.getElementsByTagName("wyswietlenie");
		      List<Wyswietlenie> listt2 = new ArrayList<>();
		      for (int temp = 0; temp < list2.getLength(); temp++) {
		    	  Node node = list2.item(temp);
		    	  String nip= getNodeAttributeByTagName(node, "nipFirmy");
		    	  String dStart= getNodeValueByTagName(node, "dataStart");
		    	  String dStop= getNodeValueByTagName(node, "dataStop");
		    	  String koszt= getNodeValueByTagName(node, "koszt");
		    	  
		    	  if(nip.equals(xnip)) {
		    		  Element element = (Element) node;
				        // remove the specific node/nodes
				        element.getParentNode().removeChild(element);
				        document.normalize();
		    	  }
		      }
}

		      


			// pozyskanie serializatora
			LSSerializer domWriter = impl.createLSSerializer();
			// pobranie konfiguratora dla serializatora
			config = domWriter.getDomConfig();
			config.setParameter("xml-declaration", Boolean.TRUE);

			// pozyskanie i konfiguracja Wyjscia
			LSOutput dOut = impl.createLSOutput();

			dOut.setByteStream(new FileOutputStream(args[0]));
			System.out.println("zapisywanie " + args[0] + "...");
			System.out.println("Serializing document... ");
			//int min=Collections.min(listC); 
			//System.out.println("najnizsza cena "+min);
			domWriter.write(document, dOut);

			// Wyjscie na ekran
			// dOut.setByteStream(System.out);
			// domWriter.writeNode(System.out, document);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void printUsage() {

		System.err.println("usage: java Dom3Demo uri");
		System.err.println();
		System.err
				.println("NOTE: You can only validate DOM tree against XML Schemas.");

	}

	// obsluga bledow za pomoca anonimowej klasy wewnetrznej implementujacej
	// DOMErrorHandler
	// por. SAX ErrorHandler
	public static DOMErrorHandler getErrorHandler() {
		return new DOMErrorHandler() {
			public boolean handleError(DOMError error) {
				short severity = error.getSeverity();
				if (severity == error.SEVERITY_ERROR) {
					System.out.println("[dom3-error]: " + error.getMessage());
				}
				if (severity == error.SEVERITY_WARNING) {
					System.out.println("[dom3-warning]: " + error.getMessage());
				}
				if (severity == error.SEVERITY_FATAL_ERROR) {
					System.out.println("[dom3-fatal-error]: "
							+ error.getMessage());
				}
				return true;
			}
		};
	}
	
public static boolean validateXMLSchema(String xsdPath, String xmlPath){
        
        try {
            SchemaFactory factory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }

}
