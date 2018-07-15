package com.aa.xeb.yakshop.util;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aa.xeb.yakshop.model.Yak;

public class XmlIpReader {

	private File xmlInput;
    private ArrayList<Yak> yakList;
    public XmlIpReader(File xmlInput){
        this.xmlInput = xmlInput;

    }
    public ArrayList<Yak> read(){
        yakList = new ArrayList<Yak>();

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlInput);

            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("labyak");


            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String name =  eElement.getAttribute("name");
                    float age =  Float.parseFloat(eElement.getAttribute("age"));
                    String sex = eElement.getAttribute("sex");
                    Yak.Sex yakSex = (sex.equalsIgnoreCase("f")) ? Yak.Sex.F : Yak.Sex.M;
                    yakList.add(new Yak(temp,name,age,yakSex));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return yakList;
    }
}
