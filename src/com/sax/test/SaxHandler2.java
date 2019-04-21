package com.sax.test;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler2 extends DefaultHandler{

	 private List<CustomObject> listOfChildrenFromRoot = null;
	    private CustomObject root = null;
	    private CustomObject child=null;
	    private String elementValue;
	    private CustomTreeNode<CustomObject> tree= null;
	    
	    @Override
	    public void startDocument() throws SAXException {
	    	listOfChildrenFromRoot = new ArrayList<CustomObject>();
	    }
	    
	    @Override
	    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	        if (qName.equalsIgnoreCase("mainbooktitle")) {
	             root = new CustomObject();
	            
	            if(attributes.getLength() > 0)
	            {
	                String name = attributes.getValue("id");
	                root.setName(name);
	                if(name != null && name.contains("PC")){
	                	root.setType("PC");
	                }
	            }
	        }
	        if (qName.equalsIgnoreCase("map")) {
	             root = new CustomObject();
	            
	            if(attributes.getLength() > 0)
	            {
	                String name = attributes.getValue("id");
	                root.setName(name);
	                if(name != null && name.contains("PC")){
	                	root.setType("PC");
	                }
	            }
	        }
	        if (qName.equalsIgnoreCase("chapter")) {
	        	child = new CustomObject();
	        	  if(attributes.getLength() > 0)
		            {
		                String name = attributes.getValue("navtitle");
		                String file = attributes.getValue("href");
		                child.setName(name);
		                child.setFile(file);
		                if(file.contains("PC")){
		                	child.setType("PC");
		                }else{
		                	child.setType("PB");
		                }
		            }
	        	
	        }
	        
	        if (qName.equalsIgnoreCase("topicref")) {
	        	child = new CustomObject();
	        	  if(attributes.getLength() > 0)
		            {
		                String name = attributes.getValue("navtitle");
		                String file = attributes.getValue("href");
		                child.setName(name);
		                child.setFile(file);
		                if(file.contains("PC")){
		                	child.setType("PC");
		                }else{
		                	child.setType("PB");
		                }
		            }
	        	
	        }
	    }
	    
	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException {
	       
	        if (qName.equalsIgnoreCase("mainbooktitle")) {
	            root.setName(elementValue);
	        }
	        if (qName.equalsIgnoreCase("chapter") ||  qName.equalsIgnoreCase("topicref")) {
	        	listOfChildrenFromRoot.add(child);
	        }
	        
	    }
	    
	    @Override
	    public void characters(char[] ch, int start, int length) throws SAXException {
	        elementValue = new String(ch, start, length);
	    }
	 
	    public List<CustomObject> getStudents() {
	        return listOfChildrenFromRoot;
	    }

		@Override
		public void endDocument() throws SAXException {
			super.endDocument();
			tree = new CustomTreeNode<CustomObject>(root);
			if(!listOfChildrenFromRoot.isEmpty()){
				listOfChildrenFromRoot.forEach(node->addToParent(node,tree));
			}
		}

		private void addToParent(CustomObject node, CustomTreeNode<CustomObject> tree2) {
			tree2.addChild(node);
		}

		public List<CustomObject> getListOfChildrenFromRoot() {
			return listOfChildrenFromRoot;
		}

		public void setListOfChildrenFromRoot(List<CustomObject> listOfChildrenFromRoot) {
			this.listOfChildrenFromRoot = listOfChildrenFromRoot;
		}

		public CustomObject getRoot() {
			return root;
		}

		public void setRoot(CustomObject root) {
			this.root = root;
		}

		public CustomObject getChild() {
			return child;
		}

		public void setChild(CustomObject child) {
			this.child = child;
		}

		public CustomTreeNode<CustomObject> getTree() {
			return tree;
		}

		public void setTree(CustomTreeNode<CustomObject> tree) {
			this.tree = tree;
		}
	
}

