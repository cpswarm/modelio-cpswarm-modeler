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
 * File: StateManager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import es.addlink.tutormates.equationEditor.Operators.SimpleOperator;

/**
 * Almacena la información sobre la situación del foco en los objetos de tipo Texto.
 * 
 * @author Ignacio Celaya Sesma
 */
public class StateManager {

	public StateManager(){}
	
	/**
	 * Indica si algún componente se encuentra seleccionado. Por defecto el valor es false.
	 */
	private boolean haySeleccion = false;

	/**
	 * Posición del cursor dentro del Texto activo.
	 */
	private int posicionCursor;

	/**
	 * Texto que contiene el foco.
	 */
	private SimpleOperator textoActivo;
	
	private SimpleOperator textoActivoAnterior;

	/**
	 * Devuelve true, si algún componente se encuentra seleccionado, false en caso contrario.
	 * 
	 * @return True, si algún componente se encuentra seleccionado, false en caso contrario.
	 */
	public boolean getHaySeleccion() {
		return haySeleccion;
	}

	/**
	 * Devuelve la posición del cursor dentro del Texto activo.
	 * 
	 * @return posición del cursor dentro del Texto activo.
	 */
	public int getPosicionCursor() {
		return posicionCursor;
	}

	/**
	 * Devuelve el Texto que contiene el foco.
	 * 
	 * @return Texto que contiene el foco.
	 */
	public SimpleOperator getTextoActivo() {
		return textoActivo;
	}
	
	public SimpleOperator getTextoActivoAnterior() {
		return textoActivoAnterior;
	}

	/**
	 * Establece un valor en función de si se ha seleccionado algún componente o no.
	 * 
	 * @param sel valor en función de si se ha seleccionado algún componente o no.
	 */
	public void setHaySeleccion(boolean sel) {
		haySeleccion = sel;
	}

	/**
	 * Establece la posición del cursor dentro del Texto activo.
	 * 
	 * @param posicionCursor posición del cursor dentro del Texto activo.
	 */
	public void setPosicionCursor(int posicionCursor) {
		this.posicionCursor = posicionCursor;
	}

	/**
	 * Establece el Texto que contiene el foco.
	 * 
	 * @param t Texto que contiene el foco.
	 */
	public void setTextoActivo(SimpleOperator t) {
		textoActivoAnterior = getTextoActivo();
		textoActivo = t;
	}
}
