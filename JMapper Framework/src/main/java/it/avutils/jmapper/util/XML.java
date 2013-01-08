/**
 * Copyright (C) 2013 Alessandro Vurro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.avutils.jmapper.util;

import static it.avutils.jmapper.util.FilesManager.fullPathOf;
import static it.avutils.jmapper.util.FilesManager.readAtDevelopmentTime;
import static it.avutils.jmapper.util.FilesManager.readAtRuntime;
import it.avutils.jmapper.config.Error;
import it.avutils.jmapper.config.JmapperLog;
import it.avutils.jmapper.conversions.explicit.ConversionMethod;
import it.avutils.jmapper.exception.XmlConversionNameException;
import it.avutils.jmapper.exception.XmlConversionParameterException;
import it.avutils.jmapper.exception.XmlConversionTypeException;
import it.avutils.jmapper.xml.Attribute;
import it.avutils.jmapper.xml.XmlConverter;
import it.avutils.jmapper.xml.beans.XmlAttribute;
import it.avutils.jmapper.xml.beans.XmlClass;
import it.avutils.jmapper.xml.beans.XmlConversion;
import it.avutils.jmapper.xml.beans.XmlJmapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XML exposes utility methods for managing the XML file.
 * 
 * @author Alessandro Vurro
 *
 */
public final class XML {

	/**
	 * Xml file can be loaded in two ways: at runtime and development time.
	 * @param atRuntime true if xml file must be loaded at runtime, false at development time
	 * @param xmlPath path of xml file
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public XML(boolean atRuntime, String xmlPath) throws MalformedURLException, IOException{
		if(xmlPath != null){
			xmlJmapper = atRuntime?readAtRuntime(xmlPath):readAtDevelopmentTime(xmlPath);
			this.xmlPath = atRuntime?xmlPath:fullPathOf(xmlPath);
		}
		
		if(xmlJmapper == null)xmlJmapper = new XmlJmapper();
		if(xmlJmapper.classes == null)xmlJmapper.classes = new ArrayList<XmlClass>();
	}
	
	/**
	 * Default behavior
	 */
	public XML(){
		xmlJmapper = new XmlJmapper();
		xmlJmapper.classes = new ArrayList<XmlClass>();
	}
	
	/**  xml path */
	private String xmlPath;
	/**  xmlJmapper object */
	private XmlJmapper xmlJmapper;
	
	/**
	 * Returns the xml path of this object.
	 * @return xml path
	 */
	public String getXmlPath(){
		return xmlPath;
	}
	
	/**
	 * True if xml path has been defined, false otherwise.
	 * @return true if xml path has been defined, false otherwise
	 */
	public boolean exists(){
		return xmlPath != null;
	}
	
	/**
	 * Setting of xml path.
	 * @param aXmlPath xml path
	 * @return this instance of XML
	 */
	public XML setXmlPath(String aXmlPath){
		xmlPath = aXmlPath;
		return this;
	}
	
	/** @return a Map with class name as key and a list of Attributes as value */
	public Map<String, List<Attribute>> attributesLoad(){
		Map<String, List<Attribute>> map = new HashMap<String, List<Attribute>>();
		
		try{	// if xml mapping file isn't empty
				if(xmlJmapper.classes != null && xmlJmapper.classes.size() > 0)
					for (XmlClass xmlClass : xmlJmapper.classes) {
					   List<Attribute> attributes = new ArrayList<Attribute>();
					   if(xmlClass.attributes != null && xmlClass.attributes.size()>0)
						 for (XmlAttribute xmlAttribute : xmlClass.attributes)
							 attributes.add(XmlConverter.toAttribute(xmlAttribute));
													
					   map.put(xmlClass.name, attributes);
					}
		}catch (Exception e) {JmapperLog.ERROR(e);}
		return map;
	}
	
	/** @return a Map with class name as key and a list of Conversions as value */
	public Map<String, List<ConversionMethod>> conversionsLoad(){
		Map<String, List<ConversionMethod>> map = new HashMap<String, List<ConversionMethod>>();
		
		try{	// if xml mapping file isn't empty
				if(xmlJmapper.classes != null && xmlJmapper.classes.size() > 0)
					for (XmlClass xmlClass : xmlJmapper.classes) {
					   List<ConversionMethod> conversions = new ArrayList<ConversionMethod>();
					   if(xmlClass.conversions != null && xmlClass.conversions.size()>0)
						 for (XmlConversion xmlConversion : xmlClass.conversions)
							 try{	 conversions.add(XmlConverter.toConversionMethod(xmlConversion));
							 }catch (XmlConversionNameException e) {
								 Error.xmlConversionNameUndefined(this.xmlPath,xmlClass.name);
							 }catch (XmlConversionTypeException e) {
								 Error.xmlConversionTypeIncorrect(xmlConversion.name,this.xmlPath,xmlClass.name,xmlConversion.type);
							 }catch (XmlConversionParameterException e) {
								 Error.improperUseOfTheParameter(xmlConversion.name,this.xmlPath,xmlClass.name);
							 }
					   // enrich map only if for this class there are conversions
					   if(!conversions.isEmpty())
						   map.put(xmlClass.name, conversions);
					}
		}catch (Exception e) {JmapperLog.ERROR(e);}
		return map;
	}
	
	/**
	 * Returns a list with the classes names presents in xml mapping file.
	 * @return a list with the classes names presents in xml mapping file
	 */
	public List<String> getClasses(){
		List<String> classes = new ArrayList<String>();
		for (XmlClass xmlClass : xmlJmapper.classes) 
			classes.add(xmlClass.name);
		return classes;
	}
	
	/**
	 * Writes the xml mapping file starting from xmlJmapper object.
	 * @return this instance of XML
	 */
	public XML write(){
		try { FilesManager.write(xmlJmapper, xmlPath);
		} catch (IOException e) {JmapperLog.ERROR(e);}
		return this;
	}
	
	/**
	 * This method adds to the xml configuration file, the class given in input.
	 * @param aClass add This Class to Xml Configuration file
	 * @return this instance
	 */
	public XML addAnnotatedClass(Class<?> aClass){
		checksClassAbsence(aClass);
		addClass(aClass);
		return this;
	}
	
	/**
	 * This method adds Class and attributes to xmlJmapper object.<br>
	 * It's mandatory define at least one attribute.
	 * @param aClass Class to adds
	 * @param attributes attributes of Class
	 * @return this instance
	 */
	public XML addClass(Class<?> aClass,Attribute[] attributes){
		
		checksClassAbsence(aClass);
		
		XmlClass xmlClass = new XmlClass();
		xmlClass.name = aClass.getName();
		xmlClass.attributes = new ArrayList<XmlAttribute>();
		xmlJmapper.classes.add(xmlClass);
		
		addAttributes(aClass, attributes);
		
		return this;
	}
	
	/**
	 * This method remove a specific Class from Xml Configuration File
	 * @param aClass
	 * @return this instance of XML
	 */
	public XML deleteClass(Class<?> aClass){
		boolean isRemoved = xmlJmapper.classes.remove(new XmlClass(aClass.getName()));
		if(!isRemoved)Error.xmlClassInexistent(this.xmlPath,aClass);
		return this;
	}
	
	/**
	 * This method adds the attributes to an existing Class.
	 * @param aClass
	 * @param attributes
	 * @return this instance of XML
	 */
	public XML addAttributes(Class<?> aClass,Attribute[] attributes){
		checksAttributesExistence(aClass,attributes);
		
		for (Attribute attribute : attributes) {
			if(attributeExists(aClass,attribute)) Error.xmlAttributeExistent(this.xmlPath,attribute, aClass);
			findXmlClass(aClass).attributes.add(XmlConverter.toXmlAttribute(attribute));
		}
		return this;
	}
	
	/**
	 * This method deletes the attributes to an existing Class.
	 * @param aClass
	 * @param attributes
	 * @return this instance of XML
	 */
	public XML deleteAttributes(Class<?> aClass,String[] attributes){
		checksAttributesExistence(aClass,attributes);
		
		if(findXmlClass(aClass).attributes == null || findXmlClass(aClass).attributes.size()<=1)
			Error.xmlWrongMethod(aClass);
		
		for (String attributeName : attributes) {
			XmlAttribute attribute = null;
			for (XmlAttribute xmlAttribute : findXmlClass(aClass).attributes)
				if(xmlAttribute.name.equals(attributeName))
					attribute = xmlAttribute;
			
			if(attribute == null) Error.xmlAttributeInexistent(this.xmlPath,attributeName,aClass);
				
			findXmlClass(aClass).attributes.remove(attribute);
		}
		return this;
	}
	/**
	 * Adds an annotated Class to xmlJmapper structure.
	 * @param aClass Class to convert to XmlClass
	 * @return this instance of XML
	 */
	private XML addClass(Class<?> aClass){
		xmlJmapper.classes.add(XmlConverter.toXmlClass(aClass));
		return this;
	}
	
	/**
	 * Verifies that the attributes exist in aClass.
	 * @param aClass Class to check
	 * @param attributes attributes to check
	 * @return this instance of XML
	 */
	private XML checksAttributesExistence(Class<?> aClass,Attribute[] attributes){
		String[] attributesNames = new String[attributes.length];
		for (int i = attributes.length; i --> 0;) 
			attributesNames[i] = attributes[i].getName();	
		
		checksAttributesExistence(aClass,attributesNames);
		return this;
	}
	/**
	 * Verifies that the attributes exist in aClass.
	 * @param aClass Class to check
	 * @param attributes list of the names attributes to check.
	 * @return this instance of XML
	 */
	private XML checksAttributesExistence(Class<?> aClass,String[] attributes){
		if(!classExists(aClass)) Error.xmlClassInexistent(this.xmlPath,aClass);
		for (String attributeName : attributes) 
			try{ 				 aClass.getDeclaredField(attributeName);		  }
			catch (Exception e){ Error.attributeInexistent(attributeName, aClass);}
		return this;
	}
	/**
	 * Throws an exception if the class exist.
	 * @param aClass a Class to check
	 * @return this instance of XML
	 */
	private XML checksClassAbsence(Class<?> aClass){
		if(classExists(aClass))Error.xmlClassExistent(this.xmlPath,aClass);
		return this;
	}
	/**
	 * verifies that this class exist in Xml Configuration File.
	 * 
	 * @param aClass Class to verify
	 * @return this instance
	 */
	private boolean classExists(Class<?> aClass) {
		if(xmlJmapper.classes == null)return false;
		return findXmlClass(aClass) !=null?true:false;
	}
	
	/**
	 * This method returns true if the attribute exist in the Class given in input, returns false otherwise.
	 * @param aClass Class of the Attribute
	 * @param attribute Attribute to check
	 * @return true if attribute exist, false otherwise
	 */
	public boolean attributeExists(Class<?> aClass,Attribute attribute){
		if(!classExists(aClass))return false;
		for (XmlAttribute xmlAttribute : findXmlClass(aClass).attributes) 
			if(xmlAttribute.name.equals(attribute.getName()))return true;
		
		return false;
	}
	
	/**
	 * Finds the Class, given in input, in the xml mapping file and returns a XmlClass instance if exist, null otherwise.
	 * @param aClass Class to finds
	 * @return a XmlClass if aClass exist in xml mapping file, null otherwise
	 */
	private XmlClass findXmlClass(Class<?> aClass){
		for (XmlClass xmlClass : xmlJmapper.classes) 
			if(xmlClass.name.equals(aClass.getName())) return xmlClass;
		return null;
	}
	
}