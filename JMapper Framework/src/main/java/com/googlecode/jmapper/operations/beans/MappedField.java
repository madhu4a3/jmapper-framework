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
package com.googlecode.jmapper.operations.beans;

import java.lang.reflect.Field;

import com.googlecode.jmapper.config.Constants;
import com.googlecode.jmapper.enums.ConfigurationType;

/**
 * Wrapper used for a custom definition of "get" and "set" methods for a specific Field.
 * 
 * @author Alessandro Vurro
 *
 */
public class MappedField {

	private final Field field;
	private String getMethod;
	private String setMethod;
	private ConfigurationType configurationType;
	
	public MappedField(Field field) {
		super();
		this.field = field;
		getMethod = Constants.DEFAULT_ACCESSOR_VALUE;
		setMethod = Constants.DEFAULT_ACCESSOR_VALUE;
	}
	
	public Field getValue(){
		return field;
	}
	
	public String getName(){
		return field.getName();
	}
	
	public Class<?> getType(){
		return field.getType();
	}
	
	public String getMethod(){
		return getMethod;
	}
	
	public void getMethod(String getMethod){
		this.getMethod = getMethod;
	}
	
	public String setMethod(){
		return setMethod;
	}
	
	public void setMethod(String setMethod){
		this.setMethod = setMethod;
	}

	public ConfigurationType getConfigurationType() {
		return configurationType;
	}

	public void setConfigurationType(ConfigurationType configurationType) {
		this.configurationType = configurationType;
	}
	
	
}

