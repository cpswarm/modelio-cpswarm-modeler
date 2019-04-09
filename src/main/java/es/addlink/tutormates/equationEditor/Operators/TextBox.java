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
 * File: TextBox.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * El único control que contiene texto, siempre encapsulado por el componente Texto.
 * 
 * @author Ignacio Celaya Sesma
 */
public class TextBox extends Text {

	/**
	 * Color que toma el background del TextBox cuando se encuentra lleno y sin seleccionar.
	 */
	private Color colorLleno;

	/**
	 * Color que toma el background del TextBox cuando se encuentra lleno y seleccionado.
	 */
	private Color colorLlenoSeleccionado;

	/**
	 * Color que toma el background del TextBox cuando se encuentra vacío y sin seleccionar.
	 */
	private final Color colorVacio = new Color(super.getDisplay(), 210, 210, 210);

	/**
	 * Color que toma el background del TextBox cuando se encuentra vacío y seleccionado.
	 */
	private final Color colorVacioSeleccionado = new Color(super.getDisplay(), 150, 150, 150);

	/**
	 * Fuente y tamaño del TextBox por defecto.
	 */
	private Font fontTexto;

	/**
	 * Fuente y tamaño del TextBox cuando forma parte de un exponente.
	 */
	private Font fontTextoExp;

	/**
	 * Tipo de texto o componentes que se podrán introducir en este TextBox.
	 */
	private int formato;

	/**
	 * Constructor
	 * 
	 * @param parent Composite donde se aloja el TextBox (siempre es de tipo Texto).
	 * @param colorLleno Color de background utilizado cuando el TextBox es no vacío.
	 * @param colorLlenoSeleccionado Color de background utilizado cuando el TextBox está seleccionado y no vacío.
	 * @param formato Tipo de texto o componentes que se podrán introducir en este TextBox.
	 * @param textoInicial Cadena de texto con la que se inicializará el TextBox.
	 * @throws ComponentEditorException
	 */
	public TextBox(Composite parent, Color colorLleno, Color colorLlenoSeleccionado, int formato, String textoInicial) throws ComponentEditorException{
		super(parent, SWT.NONE);
		// TODO Auto-generated constructor stub

		try{
			this.fontTextoExp = new Font(super.getDisplay(), "Courier New", Manager.getFontSize() - 2, SWT.BOLD);
			this.fontTexto = new Font(super.getDisplay(), "Courier New", Manager.getFontSize(), SWT.BOLD);
			this.colorLleno = colorLleno;
			this.colorLlenoSeleccionado = colorLlenoSeleccionado;
	
			setFont(fontTexto);
			setText(textoInicial);
			setCursor(new Cursor(super.getDisplay(), SWT.CURSOR_IBEAM));
	
			int space = 0;
			if(Manager.isMac())
				space = 5;
			
			GC gc = new GC(this);
			FontMetrics fm = gc.getFontMetrics();
			int width = 1 * (fm.getAverageCharWidth() + space);
			int height = fm.getHeight() + space;
			gc.dispose();
	
			setSize(computeSize(width, height - 2));
			setSelection(1);
			setFocus();

		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de un TextBox.",ex);
		}
	}

	/**
	 * Método necesario para poder heredar una clase de Text.
	 */
	@Override
	protected void checkSubclass() {}

	/**
	 * Devuelve el tipo de texto o componentes que se podrán introducir en este TextBox.
	 * 
	 * @return El tipo de texto o componentes que se podrán introducir en este TextBox.
	 */
	public int getFormato() {
		return formato;
	}

	/**
	 * Establece el color de fondo del TextBox según lo acordado para los TextBoxes no vacíos.
	 */
	public void setColorLleno() {
		this.setBackground(colorLleno);
	}

	/**
	 * Establece el color de fondo del TextBox según lo acordado para los TextBoxes no vacíos que se encuentren seleccionados.
	 */
	public void setColorLlenoSeleccionado() {
		this.setBackground(colorLlenoSeleccionado);
	}

	/**
	 * Establece el color de fondo del TextBox según lo acordado para los TextBoxes vacíos.
	 */
	public void setColorVacio() {
		this.setBackground(colorVacio);
	}

	/**
	 * Establece el color de fondo del TextBox según lo acordado para los TextBoxes vacíos que se encuentren seleccionados.
	 */
	public void setColorVacioSeleccionado() {
		this.setBackground(colorVacioSeleccionado);
	}

	/**
	 * Modifica el tamaño de la fuente, ya que el TextBox pasa a formar parte de un exponente.
	 */
	public void setExponente() {
		super.setFont(this.fontTextoExp);
	}

	/**
	 * Establece el tipo de texto o componentes que se podrán introducir en este TextBox.
	 * 
	 * @param formato Nuevo formato.
	 */
	public void setFormato(int formato) {
		this.formato = formato;
	}
}
