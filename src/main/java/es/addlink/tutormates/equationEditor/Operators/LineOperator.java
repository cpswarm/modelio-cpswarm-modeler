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
 * File: LineOperator.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;

/**
 * Se trata de una clase que contiene un label con una línea horizontal, con capacidad para alargarla o acortarla. Es útil para las raices cuadras o fracciones.
 * 
 * @author Ignacio Celaya Sesma
 */
public class LineOperator extends Composite {

	/**
	 * Fuente y tamaño por defecto para representar la línea horizontal.
	 */
	private final Font fuente = new Font(super.getDisplay(), "Courier New", 10, SWT.BOLD);

	/**
	 * Label donde se insertará la linea horizontal.
	 */
	private Label lblLinea;

	/**
	 * Número de caracteres que debe tener la línea horizontal.
	 */
	private int numCaracteres;

	/**
	 * Constructor
	 * 
	 * @param c Composite donde se encuentra alojado el objeto.
	 * @throws ComponentEditorException
	 */
	public LineOperator(Composite c) throws ComponentEditorException{
		super(c, SWT.CURSOR_HAND);
		try{
			this.lblLinea = new Label(this, SWT.NONE);
			this.lblLinea.setFont(this.fuente);
			this.numCaracteres = 0;
			aplicarLinea();
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el cálculo de la longitud de una línea superior según el número de carácteres que alberga.",ex);
		}
	}

	/**
	 * Aplica los cambios en la línea horizontal, una vez que se ha modificado el número de caracteres.
	 * 
	 * @throws ComponentEditorException
	 */
	private void aplicarLinea() throws ComponentEditorException {
		try{
			this.lblLinea.setText("");
			int ancho = 0;
			this.numCaracteres++;
	
			for (int i = 0; i < this.numCaracteres; i++) {
				this.lblLinea.setText(this.lblLinea.getText() + "_");
				ancho += 5;
			}
	
			if (this.numCaracteres == 1) {
				int iniChars = 2;
				setLongitudInicial(iniChars);
				ancho += (5 * iniChars);
			}
	
			this.lblLinea.setBackground(new Color(super.getDisplay(), 0, 0, 0));
			this.lblLinea.setSize(ancho, 1);

		}catch(Exception ex){
			throw new ComponentEditorException("Error en el ajuste de una línea superior.",ex);
		}
	}

	/**
	 * Método necesario para poder heredar una clase de Label.
	 */
	@Override
	protected void checkSubclass() {}

	/**
	 * Establece una longitud de caracteres inicial.
	 * 
	 * @throws ComponentEditorException
	 */
	public void setLongitudInicial(int num) throws ComponentEditorException{
		try{
			for (int i = 0; i < this.numCaracteres; i++)
				this.lblLinea.setText(this.lblLinea.getText() + "_");
		
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el cálculo de la longitud de una línea superior.",ex);
		}
	}

	/**
	 * Establece el número de caractéres para la línea horizontal.
	 * 
	 * @param longitudCmp Número de caractéres para la línea horizontal.
	 * @throws ComponentEditorException
	 */
	public void setNumCaracteres(int longitudCmp) throws ComponentEditorException {
		try{
			int numCar;
			numCar = (longitudCmp + 0) / 5;
	
			if (numCar >= 0) {
				this.numCaracteres = numCar;
				aplicarLinea();
			}
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el cálculo de la longitud de una línea superior según el número de carácteres que alberga.",ex);
		}
	}
}
