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
 * File: ItemToolBar.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Role;

import org.eclipse.swt.graphics.Image;

/**
 * Clase que representa a un botón de la paleta de operadores.
 * Siempre va dentro de la clase TabToolBar.
 */
public class ItemToolBar {

	/**
	 * Indica si el botón se encuentra habilitado o deshabilitado.
	 */
	private Boolean enabled;
	
	/**
	 * Icono del botón.
	 */
	private Image icon;
	
	/**
	 * True si va a implicar una inserción de texto, por ejemplo: "+", "4", "a", false si no implica a texto, por ejemplo: raíz cuadrada, fracción.
	 */
	private Boolean isText;
	
	/**
	 * Nombre identificativo.
	 */
	private String name;
	
	/**
	 * Operador que se introduce en el editor, si es de tipo texto, se introducirá ese mismo, si no, será una referencia a un operador complejo.
	 */
	private String operator;
	
	/**
	 * Combinación de teclas para simular su pulsación. Este campo solo sirve para el toolTip, la configuración real se realiza en la clase SimpleOperatorManager.java.
	 */
	private String shortcut;
	
	/**
	 * Tooltip que se muestra. Ya se encuentra en el idioma correcto.
	 */
	private String tooltip;
	
	/**
	 * Indica si el botón se muestra o no en la paleta de operadores. 
	 */
	private Boolean visible;
	
	/**
	 * Constructor.
	 * @param icon Icono
	 * @param isText Es un operador simple o complejo.
	 * @param operator Cadena que se inserta si es de tipo texto o referencia en caso de ser operador complejo.
	 * @param name Nombre identificativo.
	 * @param shortcut Combinación de teclas que se muestra en el tooltip. No participa en la configuración de los listeners.
	 * @param tooltip Tooltip en el idioma correcto.
	 * @param visible Indica si el botón se muestra o no en la paleta de operadores.
	 * @param enabled Indica si el botón se encuentra habilitado o deshabilitado.
	 */
	public ItemToolBar(Image icon, Boolean isText, String operator, String name, String shortcut, String tooltip, Boolean visible, Boolean enabled) {
		super();
		this.icon = icon;
		this.isText = isText;
		this.operator = operator;
		this.name = name;
		this.shortcut = shortcut;
		this.tooltip = tooltip;
		this.visible = visible;
		this.enabled = enabled;
	}

	/**
	 * Devuelve el icono del botón.
	 * @return Icono del botón.
	 */
	public Image getIcon() {
		return icon;
	}

	/**
	 * Devuelve el nombre identificativo.
	 * @return Nombre identificativo.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Devuelve el operador que se introduce en el editor, si es de tipo texto, se introducirá ese mismo, si no, será una referencia a un operador complejo.
	 * @return Operador que se introduce en el editor, si es de tipo texto, se introducirá ese mismo, si no, será una referencia a un operador complejo.
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * Devuelve la combinación de teclas que se muestra en el tooltip. No participa en la configuración de los listeners.
	 * @return Combinación de teclas que se muestra en el tooltip. No participa en la configuración de los listeners.
	 */
	public String getShortcut() {
		return shortcut;
	}
	
	/**
	 * Devuelve el tooltip en el idioma correcto.
	 * @return Tooltip en el idioma correcto.
	 */
	public String getTooltip() {
		return tooltip;
	}
	
	/**
	 * Devuelve true si el botón se encuentra habilitado, false en caso contrario.
	 * @return True si el botón se encuentra habilitado, false en caso contrario.
	 */
	public Boolean isEnabled() {
		return enabled;
	}

	/**
	 * Devuelve true si es un operador simple, false si es complejo.
	 * @return True si es un operador simple, false si es complejo.
	 */
	public Boolean isText() {
		return isText;
	}

	/**
	 * Devuelve true si el botón se muestra en la paleta de operadores o false en caso contrario.
	 * @return True si el botón se muestra en la paleta de operadores o false en caso contrario.
	 */
	public Boolean isVisible() {
		return visible;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
