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
 * File: RepeatingDecimal.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

/**
 * Clase capaz de representar números decimales, periódicos puros o periódicos mixtos.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
public class RepeatingDecimal extends MathEditor {

	/**
	 * Parte entera.
	 */
	private MathEditor firstChild = null;

	/**
	 * Parte decimal.
	 */
	private StringNumber secondChild = null;

	/**
	 * Parte periódica.
	 */
	private StringNumber thirdChild = null;

	/**
	 * Constructor
	 * 
	 * @param name Nombre del componente.
	 * @param id Identificador del componente.
	 * @param parent Padre del objeto MathEditor.
	 */
	public RepeatingDecimal(String name, int id, MathEditor parent) {
		super("especial", name, id, parent);
	}

	/**
	 * Devuelve la parte entera.
	 * 
	 * @return Parte entera.
	 */
	public MathEditor getFirstChild() {
		return firstChild;
	}

	/**
	 * Devuelve la parte decimal.
	 * 
	 * @return Parte decimal.
	 */
	public MathEditor getSecondChild() {
		return secondChild;
	}

	/**
	 * Devuelve la parte periódica.
	 * 
	 * @return La parte periódica.
	 */
	public MathEditor getThirdChild() {
		return thirdChild;
	}

	/**
	 * Establece la parte entera.
	 * 
	 * @param newFirst Parte entera.
	 */
	public void setFirstChild(MathEditor newFirst) {
		firstChild = newFirst;
	}

	/**
	 * Establece la parte decimal.
	 * 
	 * @param newSecond Parte decimal.
	 */
	public void setSecondChild(StringNumber newSecond) {
		secondChild = newSecond;
	}

	/**
	 * Establece la parte periódica.
	 * 
	 * @param newThird Parte periódica.
	 */
	public void setThirdChild(StringNumber newThird) {
		thirdChild = newThird;
	}

	/*
	 * (non-Javadoc)
	 * @see Tipos.MathEditor#toString()
	 */
	@Override
	public String toString() {
		String cad = "<periodico name=" + super.getName() + " id=" + super.getID() + ">\n";

		cad += "<firstChild>\n";
		if (this.firstChild != null) {
			cad += this.firstChild.toString();
		}
		cad += "</firstChild>\n";

		cad += "<secondChild>\n";
		if (this.secondChild != null) {
			cad += this.secondChild.toString();
		}
		cad += "</secondChild>\n";

		cad += "<thirdChild>\n";
		if (this.thirdChild != null) {
			cad += this.thirdChild.toString();
		}
		cad += "</thirdChild>\n";

		cad += "</periodico>";
		return cad;
	}
}
