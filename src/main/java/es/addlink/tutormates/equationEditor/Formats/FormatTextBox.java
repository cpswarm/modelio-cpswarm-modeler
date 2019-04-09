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
 * File: FormatTextBox.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Formats;

import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * La utilidad de esta clase no es otra más que dotar a los objetos de tipo Texto de un formato determinado e impedir que se introduzcan carácteres o componentes no deseados.
 * 
 * @author Ignacio Celaya Sesma
 */
public class FormatTextBox {
	private Manager manager;
	/**
	 * Indica que se permite la insercción de números, puntos y comas.
	 */
	public final static int NUM_DEC = 2;

	/**
	 * Indica que se permite la insercción de un símbolo negativo al comienzo y números.
	 */
	public final static int NUM_NEG = 4;
	
	/**
	 * Indica que solo se permite la insercción únicamente de números.
	 */
	public final static int NUMERICO = 1;

	/**
	 * Indica que se permite la insercción de cualquier número, variable, paréntesis, punto, coma, operador, operador especial y componente.
	 */
	public final static int TODO = 0;

	/**
	 * Indica que se permite la insercción de variables.
	 */
	public final static int VARIABLE = 3;

	public FormatTextBox(Manager manager){
		this.manager = manager;
	}
	
	/**
	 * Devuelve true si el carácter se puede insertar de acuerdo a las políticas del formato, false en caso contrario.
	 * 
	 * @param cad Carácter que se desea insertar.
	 * @param formato Formato del Texto activo.
	 * @return True si el carácter se puede insertar de acuerdo a las políticas del formato, false en caso contrario.
	 */
	public boolean esValido(String cad, int formato) {
		boolean valido = false;

		/* Verificación del formato "Todo" */
			if (formato == FormatTextBox.TODO) {
	
				// Número
				try {
					Integer.parseInt(cad.toString());
					valido = true;
				}
				catch (NumberFormatException e) {
					valido = false;
				}
	
				// punto, coma, paréntesis
				if (!valido) {
					if (cad.equals(",") || cad.equals("."))
						valido = true;
				}
	
				// variable
				if(this.manager.getAllowedOperatorsAndFunctions().existsVariable(cad))
					valido = true;
	
				// operador
				if (this.manager.getAllowedOperatorsAndFunctions().existsOperator(cad)){
					valido = true;
				}
			}
			/* ********************************************** */
	
			/* Verificación del formato "Numerico" */
			else if (formato == FormatTextBox.NUMERICO) {
				try {
					Integer.parseInt(cad);
					valido = true;
				}
				catch (NumberFormatException e) {
					valido = false;
				}
			}
		/* ********************************************** */

		/* Verificación del formato "Num_Dec" */
			else if (formato == FormatTextBox.NUM_DEC) {
				try {
					Integer.parseInt(cad);
					valido = true;
				}
				catch (NumberFormatException e) {
					valido = false;
				}
	
				if (!valido) {
					if (cad.equals(",") || cad.equals("."))
						valido = true;
				}
			}
		/* ********************************************** */

		/* Verificación del formato "Variable" */
			else if (formato == FormatTextBox.VARIABLE) {
				if (this.manager.getAllowedOperatorsAndFunctions().existsVariable(cad))
					valido = true;
			}
		/* ********************************************** */
			
		/* Verificación del formato "Num_Dec" */
			else if (formato == FormatTextBox.NUM_NEG) {
				
				try {
					Integer.parseInt(cad);
					valido = true;
				}
				catch (NumberFormatException e) {
					valido = false;
				}

				if(cad.substring(0, 1).equals("-") && this.manager.getStateManager().getPosicionCursor()==0){
					if(this.manager.getStateManager().getTextoActivo().getNumCaracteres()>0){
						 if(!this.manager.getStateManager().getTextoActivo().getTexto().substring(0, 1).equals("-"))
						 valido = true;
					}else
						valido = true;
				}

				if (cad.equals(",") || cad.equals("."))
					valido = false;
			}
		/* ********************************************** */

		return valido;
	}

	/**
	 * Devuelve true si el formato permite insertar componentes, false en caso contrario.
	 * 
	 * @param formato Formato del Texto activo.
	 * @return True si el formato permite insertar componentes, false en caso contrario.
	 */
	public static boolean sePermitenComponentes(int formato) {
		if (formato == FormatTextBox.TODO)
			return true;
		else
			return false;
	}

}
