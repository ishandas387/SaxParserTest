package com.sax.test;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class DriverClass {

	public static void main(String[] args) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			SaxHandler saxHandler = new SaxHandler();
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(saxHandler);
			InputSource input = new InputSource("C://saxparser//Student.xml");
			xmlReader.parse(input);
			
			//saxParser.parse("Student.xml", saxHandler);

			List<Student> students = saxHandler.getStudents();
			for (Student student : students) {
				System.out.println("Student Id = " + student.getId());
				System.out.println("Student Name = " + student.getName());
				System.out.println("Is student graduated? " + student.isGraduated());
			}
			System.out.println("prep for real thing");
			SaxHandler2 saxHandler2 = new SaxHandler2();
			xmlReader.setContentHandler(saxHandler2);
			InputSource input2 = new InputSource("Root2.xml");
			xmlReader.parse(input2);
			List<CustomObject> listOfChildrenFromRoot = saxHandler2.getListOfChildrenFromRoot();
			CustomTreeNode<CustomObject> primaryTree = saxHandler2.getTree();
			listOfChildrenFromRoot.forEach(a -> System.out.println(a.getName()+"  "+a.getFile()) );
			//listOfChildrenFromRoot.forEach(pc -);
			CustomObject customObject = listOfChildrenFromRoot.get(0);
			xmlReader.parse(new InputSource(customObject.getFile()));
			List<CustomObject> listOfChildrenFromRoot2 = saxHandler2.getListOfChildrenFromRoot();
			listOfChildrenFromRoot2.forEach(a -> System.out.println("**"+a.getName()+"  "+a.getFile()) );
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
