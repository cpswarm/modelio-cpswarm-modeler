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
 * File: IntNumber.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

import java.math.BigInteger;

/**
 * Clase que representa a un número entero.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
public class IntNumber extends MathEditor {

	/**
	 * Número entero.
	 */
	private BigInteger number;

	/**
	 * Constructor
	 * 
	 * @param number Número entero.
	 * @param parent Padre del objeto MathEditor.
	 */
	public IntNumber(BigInteger number, MathEditor parent) {
		super("numero", "entero", -1, parent);
		this.number = number;
	}

	/**
	 * Devuelve el número entero.
	 * 
	 * @return El número entero.
	 */
	public BigInteger getNumber() {
		return number;
	}

	/**
	 * Establece el número entero.
	 * 
	 * @param newNumber Número entero.
	 */
	public void setNumber(BigInteger newNumber) {
		number = newNumber;
	}

	/* (non-Javadoc)
	 * @see Tipos.MathEditor#toString()
	 */
	@Override
	public String toString() {
		String cad = "<intNumber>";
		cad += this.number;
		cad += "</intNumber>";
		return cad;
	}

}
