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
 * File: RealNumber.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

import java.math.BigDecimal;

/**
 * Clase que representa a un número de tipo real.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
public class RealNumber extends MathEditor {

	/**
	 * Número de tipo real.
	 */
	private BigDecimal number;

	/**
	 * Constructor
	 * 
	 * @param number Número de tipo real.
	 * @param parent Padre del objeto MathEditor.
	 */
	public RealNumber(BigDecimal number, MathEditor parent) {
		super("numero", "real", -1, parent);
		this.number = number;
	}

	/**
	 * Devuelve un número de tipo real.
	 * 
	 * @return Número de tipo real.
	 */
	public BigDecimal getNumber() {
		return number;
	}

	/**
	 * Establece el número de tipo real.
	 * 
	 * @param newNumber Número de tipo real.
	 */
	public void setNumber(BigDecimal newNumber) {
		number = newNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see Tipos.MathEditor#toString()
	 */
	@Override
	public String toString() {
		String cad = "<realNumber>";
		cad += this.number;
		cad += "</realNumber>";
		return cad;
	}

}
