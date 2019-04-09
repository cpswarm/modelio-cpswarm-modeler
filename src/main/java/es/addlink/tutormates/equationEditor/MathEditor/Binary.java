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
 * File: Binary.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

/**
 * Clase que representa objetos con dos entradas. Ej: suma, resta, fracción, etc.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
public class Binary extends MathEditor {

	/**
	 * Parte izquierda del objeto binario.
	 */
	private MathEditor leftChild = null;

	/**
	 * Parte derecha del objeto binario.
	 */
	private MathEditor rightChild = null;

	/**
	 * Constructor
	 * 
	 * @param name Nombre del componente.
	 * @param id Identificador del componente.
	 * @param parent Padre del objeto MathEditor.
	 */
	public Binary(String name, int id, MathEditor parent) {
		super("binario", name, id, parent);
	}

	/**
	 * Devuelve la parte izquierda del objeto binario.
	 * 
	 * @return Parte izquierda del objeto binario.
	 */
	public MathEditor getLeftChild() {
		return leftChild;
	}

	/**
	 * Devuelve la parte derecha del objeto binario.
	 * 
	 * @return Parte derecha del objeto binario.
	 */
	public MathEditor getRightChild() {
		return rightChild;
	}

	/**
	 * Establece la parte izquierda del objeto binario.
	 * 
	 * @param newLeft
	 */
	public void setLeftChild(MathEditor newLeft) {
		leftChild = newLeft;
	}

	/**
	 * Establece la parte derecha del objeto binario.
	 * 
	 * @param newRight
	 */
	public void setRightChild(MathEditor newRight) {
		rightChild = newRight;
	}

	/* (non-Javadoc)
	 * @see Tipos.MathEditor#toString()
	 */
	@Override
	public String toString() {
		String cad = "<binario name=" + super.getName() + " id=" + super.getID() + ">\n";

		cad += "<leftChild>\n";
		if (this.leftChild != null) {
			cad += this.leftChild.toString();
		}
		cad += "</leftChild>\n";

		cad += "<rightChild>\n";
		if (this.rightChild != null) {
			cad += this.rightChild.toString();
		}
		cad += "</rightChild>\n";

		cad += "</binario>";
		return cad;
	}

}
