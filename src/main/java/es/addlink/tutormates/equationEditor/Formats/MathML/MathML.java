/* ===========================================================
 * TutorMates: Tool for Mathematics Teaching and Learning.
 * ===========================================================
 *
 * (C) Copyright 2011, by Addlink Research.
 *
 * Project Info: 	http://www.tutormates.com
 * Contact: 		info@tutormates.es
 * 
 * TutorMates Equation Editor is free software: you can redistribute it 
 * and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * TutorMates Equation Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with TutorMates Equation Editor.  
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * ---------------
 * File: MathML.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Formats.MathML;

import java.util.List;

/**
 * Almacena las características MathML de cada operador.
 * 
 * @author Ignacio Celaya Sesma
 */
public class MathML {
	
	private List<MathMLAttribute> attributesList;
	private String mathMLLabel;
	private Boolean functionType;
	

	private Boolean withinLabel;

	public MathML(List<MathMLAttribute> attributesList, String mathMLLabel, Boolean functionType, Boolean withinLabel) {
		this.attributesList = attributesList;
		this.mathMLLabel = mathMLLabel;
		this.functionType = functionType;
		this.withinLabel = withinLabel;
	}
	
	public Boolean getFunctionType() {
		return functionType;
	}
	
	public Boolean getWithinLabel() {
		return withinLabel;
	}
	
	public String getMathMLLabel() {
		return mathMLLabel;
	}

	public void setMathMLLabel(String mathMLLabel) {
		this.mathMLLabel = mathMLLabel;
	}
	
	public List<MathMLAttribute> getAttributesList() {
		return attributesList;
	}
	
	public MathMLAttribute getAttribute(int i){
		return this.attributesList.get(i);
	}
	
	public String toString(){
		return this.mathMLLabel + " : " + this.functionType + " : " + this.withinLabel + " : " + this.attributesList;
	}
	
	public Boolean hasAttributes(){
		if(this.attributesList != null)
			return this.attributesList.size() > 0;
		else
			return false;	
	}
	
}
