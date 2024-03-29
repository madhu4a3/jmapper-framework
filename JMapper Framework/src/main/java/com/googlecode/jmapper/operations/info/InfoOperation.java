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

package com.googlecode.jmapper.operations.info;

import com.googlecode.jmapper.enums.ChooseConfig;
import com.googlecode.jmapper.enums.ConversionType;
import com.googlecode.jmapper.enums.OperationType;

/**
 * InfoOperation represents the operation to be performed, taking with him such information as:<br>
 * the type of operation, the conversion type and configuration to be analyzed.
 * @author Alessandro Vurro
 *
 */
public class InfoOperation {

	private OperationType instructionType;
	private ConversionType conversionType;
	private ChooseConfig configChosen;
	
	public InfoOperation setConfigChosen(ChooseConfig configChosen) {
		this.configChosen = configChosen;
		return this;
	}
	public InfoOperation setInstructionType(OperationType instructionType) {
		this.instructionType = instructionType;
		return this;
	}
	public InfoOperation setConversionType(ConversionType conversionType) {
		this.conversionType = conversionType;
		return this;
	}
	
	public ChooseConfig getConfigChosen() {			return configChosen;	}
	public OperationType getInstructionType() {	return instructionType;	}
	public ConversionType getConversionType() {	return conversionType;	}
	
	public InfoOperation() {}
	
}
