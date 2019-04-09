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
 * File: ButtonToolBar.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * Clase encargada de construir un ToolBar con un sólo ToolItem para representar un sólo botón.
 * 
 * @author Ignacio Celaya Sesma
 */
public class ButtonToolBar {
	
	/**
	 * Constructor.
	 * 
	 * @param parent Composite donde está situado el ToolBar.
	 * @param image Imagen de fondo del ToolItem.
	 * @param toolTipText ToolTipText que se muestra al establecer el ratón sobre el ToolItem.
	 * @param style Estilo.
	 * @param isString Si se trata de insertar una cadena (la del parámetro loadParameter) entonces es true, false en caso contrario.
	 * @param loadParameter Cadena o nombre del componente que se va a insertar.
	 */
	public ButtonToolBar(final Manager manager, Composite parent, Image image, String toolTipText, int style, final Boolean isString, final String loadParameter, Boolean enabled) {
		ToolBar toolbar = new ToolBar(parent, SWT.NONE);
		ToolItem item = new ToolItem(toolbar, style);
		if(image != null)
			item.setImage(image);
		
		item.setToolTipText(toolTipText);
		
		//if(!isString)
			//System.out.println("-------\nImagen: " + image + "\nToolTip: " + toolTipText + "\nEstilo: " + style + "\nEs cadena: " + isString + "\nParametro: " + loadParameter);
		
		item.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				try{
					if (isString)
						manager.getCentralManager().insertarCadena(loadParameter);
					else{
						manager.getCentralManager().insertarComponenteNoTexto(loadParameter);
					}
				}catch(Exception ex){}
			}
		});
		item.setEnabled(enabled);
	}

}
