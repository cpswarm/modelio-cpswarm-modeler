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
 * File: Operacion.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Formats;

/**
 * Clase que almacena los datos de una sola operación.
 * 
 * @author Ignacio Celaya Sesma
 */
public class Operacion {

	/**
	 * Identificador único.
	 */
	private int id;

	/**
	 * Código MathML asociado a la operación.
	 */
	private String mathML;

	/**
	 * Nombre de la operación.
	 */
	private String nombre;

	/**
	 * Símbolo con el que aparece en la expresión del editor.
	 */
	private String simbolo;

	/**
	 * Tipo de la operación.
	 */
	private String tipo;
	
	private Boolean sePermiteSeguidos;

	/**
	 * Constructor.
	 * 
	 * @param id Identificador único.
	 * @param simbolo Símbolo con el que aparece en la expresión del editor.
	 * @param nombre Nombre de la operación.
	 * @param mathML Código MathML asociado a la operación.
	 * @param tipo Tipo de la operación.
	 */
	public Operacion(int id, String simbolo, String nombre, String mathML, String tipo, Boolean sePermiteSeguidos) {
		this.id = id;
		this.simbolo = simbolo;
		this.nombre = nombre;
		this.mathML = mathML;
		this.tipo = tipo;
		this.sePermiteSeguidos = sePermiteSeguidos;
	}

	/**
	 * Devuelve el identificador de la operación.
	 * 
	 * @return El identificador de la operación.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Devuelve el código MathML asociado a la operación.
	 * 
	 * @return El código MathML asociado a la operación.
	 */
	public String getMathML() {
		return mathML;
	}

	/**
	 * Devuelve el nombre de la operación.
	 * 
	 * @return El nombre de la operación.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve el símbolo de la operación.
	 * 
	 * @return the simbolo
	 */
	public String getSimbolo() {
		return simbolo;
	}

	/**
	 * Devuelve el tipo de la operación.
	 * 
	 * @return El tipo de la operación.
	 */
	public String getTipo() {
		return tipo;
	}

	public Boolean getSePermiteSeguidos(){
		return this.sePermiteSeguidos;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String cad;
		cad = id + "|" + tipo + "|" + simbolo + "|" + nombre + "|" + mathML;
		return cad;
	}

}
