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
 * File: StringNumber.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

/**
 * Clase que representa texto, útil para los periódicos cuando hay que representar cantidades con ceros a la izquierda.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
public class StringNumber extends MathEditor {

	/**
	 * Texto
	 */
	private String text;

	/**
	 * Constructor
	 * 
	 * @param text Texto.
	 * @param parent Padre del objeto MathEditor.
	 */
	public StringNumber(String text, MathEditor parent) {
		super("", "", 0, parent);
		this.text = text;
	}

	/**
	 * 
	 * @return Texto
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @param newText Nuevo texto.
	 */
	public void setText(String newText) {
		text = newText;
	}

	/*
	 * (non-Javadoc)
	 * @see Tipos.MathEditor#toString()
	 */
	@Override
	public String toString() {
		String cad = "<Texto>";
		cad += this.text;
		cad += "</Texto>\n";
		return cad;
	}
}
