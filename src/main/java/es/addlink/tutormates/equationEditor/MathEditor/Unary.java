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
 * File: Unary.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

/**
 * Clase que representa objetos con una entrada. Ej: negativo, raíz, factorial, etc.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
public class Unary extends MathEditor {

	/**
	 * Contenido del objeto Unario.
	 */
	private MathEditor child = null;

	/**
	 * Constructor
	 * 
	 * @param name Nombre del componente.
	 * @param id Identificador del componente.
	 * @param parent Padre del objeto MathEditor.
	 */
	public Unary(String name, int id, MathEditor parent) {
		super("unario", name, id, parent);
	}

	/**
	 * Devuelve el contenido del objeto Unario.
	 * 
	 * @return El contenido del objeto Unario.
	 */
	public MathEditor getChild() {
		return child;
	}

	/**
	 * Establece el contenido del objeto Unario.
	 * 
	 * @param newChild
	 */
	public void setChild(MathEditor newChild) {
		child = newChild;
	}

	/* (non-Javadoc)
	 * @see Tipos.MathEditor#toString()
	 */
	@Override
	public String toString() {
		String cad = "<unario name=" + super.getName() + " id=" + super.getID() + ">\n<child>\n";
		if (this.child != null)
			cad += this.child.toString();
		cad += "</child>\n</unario>\n";
		return cad;
	}

}
