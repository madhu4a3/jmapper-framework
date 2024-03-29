/**
 * Copyright (C) 2012 - 2015 Alessandro Vurro.
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
package com.googlecode.jmapper.operations.recursive;

import java.util.Set;

import com.googlecode.jmapper.enums.ChooseConfig;
import com.googlecode.jmapper.generation.beans.Method;
import com.googlecode.jmapper.operations.complex.AComplexOperation;
import com.googlecode.jmapper.xml.XML;

/**
 * This abstract class represents a recursive operation.
 * 
 * @author Alessandro Vurro
 *
 */
public abstract class ARecursiveOperation extends AComplexOperation{

	/** methods to generate */
	protected Set<Method> methodsToGenerate;
	/** newInstance indicates the destination creation */
	protected final boolean newInstance = true;
	/** enrichment indicates the destination enrichment */
	protected final boolean enrichment = false;
	/** configuration chosen */
	protected ChooseConfig configChosen = null;
	/** xml object */
	protected XML xml;
	
	/**
	 * ChooseConfig setting.
	 * @param configChosen configuration chosen
	 * @return this instance of ARecursiveOperation
	 */
	public ARecursiveOperation setConfigChosen(ChooseConfig configChosen) {
		this.configChosen = configChosen;
		return this;
	}
	
	/**
	 * Xml setting.
	 * @param aXml xml object
	 * @return this instance of ARecursiveOperation
	 */
	public ARecursiveOperation setXml(XML aXml){
		xml = aXml;
		return this;
	}
	
	/**
	 * @return the set destination path
	 */
	protected String setDName(){
		return s(setDestination());
	}
	
	/**
	 * @return the get destination path
	 */
	protected String getDName(){
		return s(getDestination());
	}
	
	/**
	 * @return the get source path
	 */
	protected String getSName(){
		return s(getSource());
	}

	/**
	 * This set is used to add all explicit conversions which must be generated.
	 * @param methodsToGenerate methods to generate
	 * @return this instance of ARecursiveOperation
	 */
	public ARecursiveOperation setDynamicMethodsToWrite(Set<Method> methodsToGenerate) {
		this.methodsToGenerate = methodsToGenerate;
		return this;
	}
}