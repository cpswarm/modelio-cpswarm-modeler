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
 * File: Variable.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

/**
 * Clase capaz de representar una variable.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
public class Variable extends MathEditor {

	/**
	 * Variable.
	 */
	private String var;
	
	private Boolean isText;

	public Boolean getIsText() {
		return isText;
	}

	/**
	 * Constructor.
	 * 
	 * @param var Variable.
	 * @param parent Padre del objeto MathEditor.
	 */
	public Variable(String var, Boolean isText, MathEditor parent) {
		super("numero", "variable", 0, parent);
		this.var = var;
		this.isText = isText;
	}

	/**
	 * Devuelve la variable.
	 * 
	 * @return La variable.
	 */
	public String getVar() {
		return var;
	}

	/**
	 * Establece una nueva variable.
	 * 
	 * @param newvar Nueva variable.
	 */
	public void setVar(String newvar) {
		var = newvar;
	}

	/*
	 * (non-Javadoc)
	 * @see Tipos.MathEditor#toString()
	 */
	@Override
	public String toString() {
		return "<Variable>" + var + "</Variable>\n";
	}
}
