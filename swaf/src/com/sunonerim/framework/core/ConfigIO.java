package com.sunonerim.framework.core;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ConfigIO {
	static	ConfigIO			TheConfigIO;		//
	static	String				ConfigFilePath;		
	
	
	final	static	public		String			cfCoreDao 			= "CoreDao";
	final	static	public		String			cfCommandHanlder	= "CommandHanlder";
	
	
	HashMap<String, String>		ConfigItems = new HashMap<String, String> ();
	
	static	public	ConfigIO	getConfig() {
		if ( TheConfigIO == null ) TheConfigIO = new ConfigIO(null);
		return TheConfigIO;
	}
	
	static	public	ConfigIO	getConfig(String conf_file) {
		if ( TheConfigIO == null ) TheConfigIO = new ConfigIO(conf_file);
		return TheConfigIO;
	}
	
	public	ConfigIO(String conf_file) {
		if( conf_file != null ) {
			if( !conf_file.equals( ConfigFilePath )){
				ConfigFilePath = conf_file;
				reload(ConfigFilePath);
			}
		}
	}	

	/**
	 * config 파일을 읽고 해당 사항을 ConfigItems map에 저장한다.
	 * 
	 * @param conf_file
	 */
	public	void	reload(String conf_file) {
		ConfigItems.clear();
		try {	
	         File inputFile = new File(conf_file); //);
	         DocumentBuilderFactory	dbFactory 	= DocumentBuilderFactory.newInstance();
	         DocumentBuilder		dBuilder 	= dbFactory.newDocumentBuilder();
	         Document 				doc 		= dBuilder.parse(inputFile);
	         
	         doc.getDocumentElement().normalize();
	         
	         NodeList nList = doc.getElementsByTagName("configuration");	         
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node 					nNode 		= nList.item(temp);	            
	            nNode.getChildNodes();
	            NodeList				config_nodes = nNode.getChildNodes();	            
	            findNode(config_nodes, config_nodes.getLength());
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
	
	public	void	findNode(NodeList node_list, int length){
		for ( int i=0; i<length; i++ ){
			Node node = node_list.item(i);
			if( node.getNodeType() == Node.ELEMENT_NODE ){
				Element element = (Element) node;
				if( element.hasChildNodes()){
					NodeList childNodeList = element.getElementsByTagName("*");
					int childLength = childNodeList.getLength();
					Node childNode = childNodeList.item(i);
					findNode( childNodeList, childLength);
				}
				System.out.println(node.getNodeName() + node.getTextContent());
				ConfigItems.put(node.getNodeName().trim(),  node.getTextContent().trim());
			}
		}
	}
	
	public HashMap<String, String> getConfigItems() {
		return ConfigItems;
	}

	public void setConfigItems(HashMap<String, String> configItems) {
		ConfigItems = configItems;
	}

	public	static String	getValue( String config_node){
		return TheConfigIO.getConfigItems().get(config_node);
	}
	
	
	
	
	
	
	public static void testConfig() {
		ConfigIO.getConfig("/Users/gubeobseo/Documents/workspace/m/WebContent/WEB-INF/swaf.conf.xml");
		System.out.println("cfCoreDao >> " + ConfigIO.getValue(ConfigIO.cfCoreDao) );
	}
	
	public static void main(String[] args) {
//		testCreateOrder();
		testConfig();
	}
}
