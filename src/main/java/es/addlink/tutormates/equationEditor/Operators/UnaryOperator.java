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
 * File: UnaryOperator.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */
package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.widgets.Control;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Formats.FormatTextBox;
import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * A este tipo solo pueden pertenecer componentes que sólamente admiten una entrada. Ej: raíz cuadrada.
 * 
 * @author Ignacio Celaya Sesma
 */
public abstract class UnaryOperator extends Operator {
	private Manager manager;
	/**
	 * Cuadrícula del componente.
	 */
	private GridExpression cuadriculaEdicion1;
	
	/**
	 * Constructor.
	 * 
	 * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
	 * @param nombre Nombre del componente.
	 * @param id Identificador del componente.
	 * @throws ComponentEditorException
	 */
	public UnaryOperator(Manager manager, GridExpression cuadriculaPadre, String nombre, int id) throws ComponentEditorException{
		super(manager, cuadriculaPadre, true, Category.UnaryType, nombre, id);
		// TODO Auto-generated constructor stub
		
		this.manager = manager;
	}

	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#cambioPosicionCentralEnCuadricula()
	 */
	@Override
	protected abstract void cambioPosicionCentralEnCuadricula() throws ComponentEditorException;

	/**
	 * Crea la cuadrícula del componente.
	 * 
	 * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
	 * @throws ComponentEditorException
	 */
	protected void crearCuadriculaEdicion1(String textoInicial, boolean esExponente) throws ComponentEditorException{
		try{
			
			this.cuadriculaEdicion1 = new GridExpression(this.manager, super.getCmpTodo());
			this.cuadriculaEdicion1.setBackground(super.getColorFondo());
	
			SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion1, FormatTextBox.TODO, "");
			if(esExponente)
				t.setExponente();
			
			this.cuadriculaEdicion1.setMenu(super.getMenuEliminar());
			t.setMenu(super.getMenuEliminar());
	
			/* Detecta si el componente forma parte de un exponente */
				if (super.getCuadriculaPadre().esExponente()) {
					this.cuadriculaEdicion1.setEsExponente(true);
					t.setExponente();
				}
			/* ***************************************************** */
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la creación de la cuadrícula 1 en un componente unario.",ex);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#deseleccionar()
	 */
	@Override
	public void deseleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		try{
			
			super.deseleccionar();
			this.cuadriculaEdicion1.deseleccionarTodo();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la deselección de un componente unario.",ex);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#eliminaComponentesInternosSeleccionados()
	 */
	@Override
	public void eliminaComponentesInternosSeleccionados() throws ComponentEditorException {
		// TODO Auto-generated method stub
		try{
			this.manager.getCentralManager().eliminarSeleccion(this.getCuadriculaEdicion1());
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error eliminando los componentes internos seleccionados de un componente unario.",ex);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#eliminaTextosVacios()
	 */
	@Override
	public void eliminaTextosVacios() throws ComponentEditorException {
		// TODO Auto-generated method stub
		try{
			this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion1);
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error eliminando los Textox vacíos de un componente binario.",ex);
		}
	}

	/**
	 * Devuelve la cuadrícula que contiene el componente.
	 * 
	 * @return Cuadricula que contiene el componente.
	 */
	public GridExpression getCuadriculaEdicion1() {
		return this.cuadriculaEdicion1;
	}
	
	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
	 */
	@Override
	public abstract int getPosicionCentral() throws ComponentEditorException;

	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#setFocoFinal()
	 */
	@Override
	public void setFocoFinal() throws ComponentEditorException {
		// TODO Auto-generated method stub
		try{
			
			Control[] con = getCuadriculaEdicion1().getChildren();
			Operator c = (Operator) con[con.length - 1];
			c.setFocoFinal();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error situando el cursor en la posición final de un componente binario.",ex);
		}
	}

	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#setFocoInicio()
	 */
	@Override
	public void setFocoInicio() throws ComponentEditorException {
		// TODO Auto-generated method stub
		try{
			
			Control[] con = getCuadriculaEdicion1().getChildren();
			Operator c = (Operator) con[0];
			c.setFocoInicio();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error situando el cursor en la primera posición de un componente unario.",ex);
		}
	}

}
