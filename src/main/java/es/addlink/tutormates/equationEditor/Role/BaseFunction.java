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
 * File: BaseFunction.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Role;

import es.addlink.tutormates.equationEditor.Formats.MathML.MathML;

public class BaseFunction {

	private String name;
	private int entries;
	private BaseOperator allowedOperator;
	private MathML mathML;
	
	public BaseFunction(String name, int entries, BaseOperator allowedOperator, MathML mathML) {
		super();
		this.name = name;
		this.entries = entries;
		this.allowedOperator = allowedOperator;
		this.mathML = mathML;
	}
	
	public String getName() {
		return name;
	}
	public int getEntries() {
		return entries;
	}
	public BaseOperator getAllowedOperator() {
		return allowedOperator;
	}
	public MathML getMathML() {
		return mathML;
	}
	
	public String toString(){
		String output;
		output = name + "|" + entries + "|" + mathML + "|" + allowedOperator + "|";
		return output;
	}
	
}
