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
 * File: Role.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Role;

import java.util.Iterator;
import java.util.List;

/**
 * Esta clase contiene la configuración global que se debe cargar.
 * Dependiendo de si es un alumno o profesor, curso o tema, contendrá diferentes configuraciones para los siguientes aspectos:
 * - ToolBar de botones.
 * - Operadores, variables y funciones disponibles para el usuario.
 * 
 * Se puede ver el diagrama de clases en el package UML.
 */
public class Role {

	/**
	 * Curso: 1ESO, 2ESO, etc.
	 */
	private String course;
	
	/**
	 * Listado de funciones (sin, cos, ...) que el editor reconoce para realizar las operaciones de exportación y/o importación.
	 */
	private List<BaseFunction> listAllowedFunction;
	
	/**
	 * Listado de variables y números (enteros y reales) que el editor reconoce para realizar las operaciones de exportación y/o importación.
	 */
	private List<BaseOperator> listAllowedNumbersVariables;
	
	/**
	 * Listado de operadores que el editor reconoce para realizar las operaciones de exportación y/o importación.
	 */
	private List<BaseOperator> listAllowedOperator;
	
	/**
	 * Combinación de pestañas y botones en cada una que se cargará en la interfaz de usuario.
	 */
	private List<TabToolBar> listTabToolBar;

	/**
	 * Perfil del usuario, es decir, si es un alumno o un profesor.
	 */
	private String profile;
	
	/**
	 * Tema. Ejemplo de un tema: ENT_N1.
	 */
	private String unit;

	/**
	 * Constructor.
	 * @param profile Perfil del usuario: alumno, profesor o vacío.
	 * @param course Curso: 1ESO, 2ESO, etc o vacío.
	 * @param unit Tema: ENT_N1, ... o vacío.
	 * @param listAllowedOperator Listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
	 * @param listAllowedFunction Listado de variables reconocibles para realizar las operaciones de exportación y/o importación.
	 * @param listAllowedNumbersVariables Listado de funciones reconocibles para realizar las operaciones de exportación y/o importación.
	 * @param listTabToolBar Combinación de pestañas y botones en cada una que se cargará en la interfaz de usuario.
	 */
	public Role(String profile, String course, String unit, List<BaseOperator> listAllowedOperator, List<BaseFunction> listAllowedFunction, List<BaseOperator> listAllowedNumbersVariables, List<TabToolBar> listTabToolBar) {
		super();
		this.profile = profile;
		this.course = course;
		this.unit = unit;
		this.listAllowedOperator = listAllowedOperator;
		this.listAllowedFunction = listAllowedFunction;
		this.listAllowedNumbersVariables = listAllowedNumbersVariables;
		this.listTabToolBar = listTabToolBar;
	}

	/**
	 * Devuelve el curso que utiliza este rol.
	 * @return Curso que utiliza este rol.
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * Devuelve el listado de funciones reconocibles para realizar las operaciones de exportación y/o importación.
	 * @return Listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
	 */
	public List<BaseFunction> getListAllowedFunction() {
		return listAllowedFunction;
	}

	/**
	 * Devuelve el listado de variables reconocibles para realizar las operaciones de exportación y/o importación.
	 * @return Listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
	 */
	public List<BaseOperator> getListAllowedNumbersVariables() {
		return listAllowedNumbersVariables;
	}

	/**
	 * Devuelve el listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
	 * @return Listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
	 */
	public List<BaseOperator> getListAllowedOperator() {
		return listAllowedOperator;
	}

	/**
	 * Devuelve el listado de combinación de pestañas y botones en cada una que se cargará en la interfaz de usuario.
	 * @return Listado de combinación de pestañas y botones en cada una que se cargará en la interfaz de usuario.
	 */
	public List<TabToolBar> getListTabToolBar() {
		return listTabToolBar;
	}

	/**
	 * Devuelve el perfil que utiliza este rol.
	 * @return Perfil que utiliza este rol.
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * Devuelve el tema que utiliza este rol.
	 * @return Tema que utiliza este rol.
	 */
	public String getUnit() {
		return unit;
	}
	
	@Override
	public String toString() {
		String str="";
		
		str += " _____________________________________________________________________________\n|\n";
		str += "| ROL - " + this.profile + " ~ " + this.course + " ~ " + this.unit + "\n|";
		str += "\n|   ·Operators:\n|";
		str += "\n|   ·Functions:\n|";
		str += "\n|   ·Tab folders:\n|";
			Iterator<TabToolBar> ite = listTabToolBar.iterator();
			while(ite.hasNext()){
				TabToolBar t = (TabToolBar)ite.next();
				str += "       > " + t + "\n|";
			}
		
		str += "______________________________________________________________________________";
		return str;	
	}
}
