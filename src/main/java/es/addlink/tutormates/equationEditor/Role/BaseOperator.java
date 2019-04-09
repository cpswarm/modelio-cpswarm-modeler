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
 * File: BaseOperator.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Role;

import es.addlink.tutormates.equationEditor.Formats.MathML.MathML;

public class BaseOperator {

	private int id;
	private Boolean isText;
	private String name;
	private MathML mathML;
	private String symbolEditor;
	private String symbolMathML;
	private String type;
	private String position;
	private int priority;

	public BaseOperator(int id, Boolean isText, String name, MathML mathML, String symbolEditor, String symbolMathML, String type, String position, int priority) {
		super();
		this.id = id;
		this.isText = isText;
		this.name = name;
		this.mathML = mathML;
		this.symbolEditor = symbolEditor;
		this.symbolMathML = symbolMathML;
		this.type = type;
		this.position = position;
		this.priority = priority;
	}
	
	public void setSymbolMathML(String symbolMathML) {
		this.symbolMathML = symbolMathML;
	}
	
	public String getType() {
		return type;
	}

	public String getPosition() {
		return position;
	}

	public int getPriority() {
		return priority;
	}
	
	public int getId() {
		return id;
	}
	public Boolean isText() {
		return isText;
	}
	public String getName() {
		return name;
	}
	public MathML getMathML() {
		return mathML;
	}
	public String getSymbolEditor() {
		return symbolEditor;
	}
	public String getSymbolMathML() {
		return symbolMathML;
	}
	
	public String toString(){
		String output;
		output = id + "|" + type + "|" + name + "|" + symbolEditor + "|" + mathML + "|" + isText + "|";
		return output;
	}
	
}
