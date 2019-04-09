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
 * File: Exponent.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Control;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * Clase encargada de añadir un exponente.
 * 
 * @author Ignacio Celaya Sesma
 */
public class Exponent extends UnaryOperator {
	/**
	 * Constructor
	 * 
	 * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
	 * @throws ComponentEditorException
	 */
	public Exponent(Manager manager, GridExpression cuadriculaPadre)throws ComponentEditorException {
		super(manager, cuadriculaPadre, "exponent", 52);
		super.setTipoComponente(Category.UnaryComplexType);
		
		try{
			super.getCmpTodo().setLayout(new FormLayout());
	
			crearCuadriculaEdicion1("",true);
	
			setBackground(super.getColorFondo());
	
			super.getCuadriculaEdicion1().addControlListener(new ControlAdapter() {
				@Override
				public void controlResized(ControlEvent e) {
					try{
						ajustar();					
					}catch(Exception ex){}
				}
			});
	
			setMenuEliminar();
			super.setPosicionCentral(getPosicionCentral());
			ajustar();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de un exponente.",ex);
		}
	}
	
	/**
	 * Modifica el tamaño del componente. Ajusta el tamaño del símbolo y la longitud de la línea superior.
	 * 
	 * @throws ComponentEditorException
	 */
	private void ajustar() throws ComponentEditorException{
		try{
			
			super.getCuadriculaEdicion1().pack();
			super.getCmpTodo().pack();
			super.getCuadriculaPadre().pack();
			super.setPosicionCentral(getPosicionCentral());
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el ajuste de un exponente.",ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.CUnario#cambioPosicionCentralEnCuadricula()
	 */
	@Override
	protected void cambioPosicionCentralEnCuadricula() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Components.CUnario#deseleccionar()
	 */
	@Override
	public void deseleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.deseleccionar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Components.Componente#getPosicionCentral()
	 */
	@Override
	public int getPosicionCentral() throws ComponentEditorException{
		try{
			if (super.getCuadriculaEdicion1() != null) {
				Control[] children = super.getCuadriculaEdicion1().getCuadriculaSuperior().getChildren();
				int i = 0;
				boolean encontrado = false;
	
				while (i < children.length && !encontrado) {
					if (((Operator) children[i]) == this)
						encontrado = true;
					else
						i++;
				}
	
				int devuelta = 0;
	
				if (i > 0) {
					Operator compAnterior = (Operator) children[i - 1];
					devuelta = this.getCmpTodo().getSize().y + (compAnterior.getPosicionCentral() - compAnterior.getLocation().y) -8;
				}
	
				return devuelta;
			}
			else {
				return 0;
			}
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el cálculo de la posición central de un exponente.",ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Components.CUnario#seleccionar()
	 */
	@Override
	public void seleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.seleccionar();
		super.getCuadriculaEdicion1().seleccionar(super.getColorFondoSeleccionado());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Components.Componente#setAltura()
	 */
	@Override
	public void setAltura(int altura) throws ComponentEditorException {
		try{
			if (altura != super.getCmpTodo().getLocation().y) {
				super.getCmpTodo().setLocation(0, altura);
			}
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el cálculo de la posición central de un exponente.",ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Components.Componente#setMenuEliminar()
	 */
	@Override
	public void setMenuEliminar() throws ComponentEditorException{
		super.setMenuEliminar();
	}
	
}
