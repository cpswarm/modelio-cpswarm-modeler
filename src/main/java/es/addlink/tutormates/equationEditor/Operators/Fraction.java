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
 * File: Fraction.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * Es la clase encargada de construir una fracción.
 * 
 * @author Ignacio Celaya Sesma
 */
public class Fraction extends BinaryOperator {
	/**
	 * Linea central de la fracción
	 */
	private LineOperator lineaCentral;

	/**
	 * Constructor
	 * 
	 * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
	 * @throws ComponentEditorException
	 */
	public Fraction(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException{
		super(manager, cuadriculaPadre, "fraction", 51);
		// TODO Auto-generated constructor stub
		
		try{
			GridLayout grid = new GridLayout();
			grid.numColumns = 1;
			grid.makeColumnsEqualWidth = false;
			grid.horizontalSpacing = 0;
			grid.verticalSpacing = 1;
			grid.marginHeight = 0;
			grid.marginWidth = 0;
			grid.marginBottom = 0;
			grid.marginTop = 0;
	
			super.getCmpTodo().setLayout(grid);
	
			super.crearCuadriculaEdicion1("",false);
			this.lineaCentral = new LineOperator(super.getCmpTodo());
			super.crearCuadriculaEdicion2("",false);
	
			GridExpression c1 = super.getCuadriculaEdicion1();
			GridExpression c2 = super.getCuadriculaEdicion2();
	
			c1.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1));
			c2.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1));
			
			super.getCmpTodo().pack();
			
			setMenuEliminar();
			listeners();
	
			super.setFocoInicio();
			super.setPosicionCentral(getPosicionCentral());
	
			if (super.getComponentePadre() != null)
				super.getComponentePadre().pack();
			
		}catch(EditorException ex){
			System.out.println("Error");
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una fracción.",ex);
		}
	}

	/**
	 * Mantiene en el centro del componente a ambas cuadrículas, y modifica la longitud de la línea central en función de la anchura global del componente.
	 * 
	 * @throws ComponentEditorException
	 */
	private void ajustar() throws ComponentEditorException{
		try{
			int anchoCuadriculaEdicion1 = super.getCuadriculaEdicion1().getSize().x;
			int anchoCuadriculaEdicion2 = super.getCuadriculaEdicion2().getSize().x;
	
			if (anchoCuadriculaEdicion1 > anchoCuadriculaEdicion2)
				lineaCentral.setNumCaracteres(super.getCuadriculaEdicion1().getSize().x + 1);
			else if (anchoCuadriculaEdicion2 > anchoCuadriculaEdicion1)
				lineaCentral.setNumCaracteres(super.getCuadriculaEdicion2().getSize().x + 1);
			else
				lineaCentral.setNumCaracteres(super.getCuadriculaEdicion2().getSize().x + 1);
	
			int anchoCmpTodo = super.getCmpTodo().getSize().x;
			int posicionCua1 = (anchoCmpTodo - anchoCuadriculaEdicion1) / 2;
			int posicionCua2 = (anchoCmpTodo - anchoCuadriculaEdicion2) / 2;
	
			super.getCuadriculaEdicion1().setLocation(posicionCua1, super.getCuadriculaEdicion1().getLocation().y);
			super.getCuadriculaEdicion2().setLocation(posicionCua2, super.getCuadriculaEdicion2().getLocation().y);
	
			super.getCmpTodo().pack();
			super.getCuadriculaPadre().pack();
			super.setPosicionCentral(getPosicionCentral());
	
			if (this.getComponentePadre() != null)
				this.getComponentePadre().cambioPosicionCentralEnCuadricula();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el ajuste de una fracción.",ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.CBinario#cambioPosicionCentralEnCuadricula()
	 */
	@Override
	protected void cambioPosicionCentralEnCuadricula() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.CBinario#deseleccionar()
	 */
	@Override
	public void deseleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.deseleccionar();
		this.lineaCentral.setBackground(super.getColorFondo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
	 */
	@Override
	public int getPosicionCentral() throws ComponentEditorException{
		// TODO Auto-generated method stub
		
		try{
			
			if (this.lineaCentral != null)
				return this.lineaCentral.getLocation().y;
			else
				return 0;
			
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el ajuste de una fracción.",ex);
		}
	}

	/**
	 * Enlaza listeners a ambas cuadrículas para realizar ajustes cuando éstas cambian de tamaño.
	 */
	private void listeners() {
		super.getCuadriculaEdicion1().addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				try{
					ajustar();
				}catch(Exception ex){}
			}
		});

		super.getCuadriculaEdicion2().addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				try{
					ajustar();
				}catch(Exception ex){}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.CBinario#seleccionar()
	 */
	@Override
	public void seleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.seleccionar();
		this.lineaCentral.setBackground(super.getColorFondoSeleccionado());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.CBinario#setMenuEliminar()
	 */
	@Override
	protected void setMenuEliminar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.setMenuEliminar();
		this.lineaCentral.setMenu(super.getMenuEliminar());
	}
}
