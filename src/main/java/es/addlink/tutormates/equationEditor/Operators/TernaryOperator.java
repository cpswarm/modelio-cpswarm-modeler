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
 * File: TernaryOperator.java
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
 * A este tipo solo pueden pertenecer componentes que admiten únicamente tres entradas. Ej: periódico mixto.
 * 
 * @author Ignacio Celaya Sesma
 */
public abstract class TernaryOperator extends Operator {
	private Manager manager;
	/**
	 * Primera cuadrícula del componente.
	 */
	private GridExpression cuadriculaEdicion1;

	/**
	 * Segunda cuadrícula del componente.
	 */
	private GridExpression cuadriculaEdicion2;

	/**
	 * Tercera cuadrícula del componente.
	 */
	private GridExpression cuadriculaEdicion3;
	
	/**
	 * Constructor.
	 * 
	 * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
	 * @param nombre Nombre del componente.
	 * @param id Identificador del componente.
	 * @throws ComponentEditorException
	 */
	public TernaryOperator(Manager manager, GridExpression cuadriculaPadre, String nombre, int id) throws ComponentEditorException {
		super(manager, cuadriculaPadre, true, Category.TernaryType, nombre, id);
		// TODO Auto-generated constructor stub
		
		this.manager = manager;
	}

	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#cambioPosicionCentralEnCuadricula()
	 */
	@Override
	protected abstract void cambioPosicionCentralEnCuadricula() throws ComponentEditorException;
	
	/**
	 * Crea la primera cuadrícula del componente.
	 * 
	 * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
	 * @throws ComponentEditorException
	 */
	protected void crearCuadriculaEdicion1(String textoInicial) throws ComponentEditorException{
		try{
			this.cuadriculaEdicion1 = new GridExpression(this.manager, super.getCmpTodo());
			this.cuadriculaEdicion1.setBackground(super.getColorFondo());
			SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion1, FormatTextBox.TODO, textoInicial);
	
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
			throw new ComponentEditorException("Error creando la cuadrícula 1 de un componente ternario.",ex);
		}
	}
	
	/**
	 * Crea la segunda cuadrícula del componente.
	 * 
	 * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
	 * @throws ComponentEditorException
	 */
	protected void crearCuadriculaEdicion2(String textoInicial) throws ComponentEditorException{
		try{
			this.cuadriculaEdicion2 = new GridExpression(this.manager, super.getCmpTodo());
			this.cuadriculaEdicion2.setBackground(super.getColorFondo());
			SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion2, FormatTextBox.TODO, textoInicial);
	
			this.cuadriculaEdicion2.setMenu(super.getMenuEliminar());
			t.setMenu(super.getMenuEliminar());
	
			/* Detecta si el componente forma parte de un exponente */
				if (super.getCuadriculaPadre().esExponente()) {
					this.cuadriculaEdicion2.setEsExponente(true);
					t.setExponente();
				}
			/* ***************************************************** */
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error creando la cuadrícula 2 de un componente ternario.",ex);
		}
	}

	/**
	 * Crea la tercera cuadrícula del componente.
	 * 
	 * @param textoInicial Cadena de texto con la que se quiere inicializar el Texto por defecto que se coloca al crear una nueva cuadrícula.
	 * @throws ComponentEditorException
	 */
	protected void crearCuadriculaEdicion3(String textoInicial)throws ComponentEditorException {
		try{
			this.cuadriculaEdicion3 = new GridExpression(this.manager, super.getCmpTodo());
			this.cuadriculaEdicion3.setBackground(super.getColorFondo());
			SimpleOperator t = new SimpleOperator(this.manager, this.cuadriculaEdicion3, FormatTextBox.TODO, textoInicial);
	
			this.cuadriculaEdicion3.setMenu(super.getMenuEliminar());
			t.setMenu(super.getMenuEliminar());
	
			/* Detecta si el componente forma parte de un exponente */
				if (super.getCuadriculaPadre().esExponente()) {
					this.cuadriculaEdicion3.setEsExponente(true);
					t.setExponente();
				}
			/* ***************************************************** */
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error creando la cuadrícula 3 de un componente ternario.",ex);
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
			this.cuadriculaEdicion2.deseleccionarTodo();
			this.cuadriculaEdicion3.deseleccionarTodo();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error deseleccionando un componente ternario",ex);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#eliminaComponentesInternosSeleccionados()
	 */
	@Override
	public void eliminaComponentesInternosSeleccionados() throws ComponentEditorException {
		// TODO Auto-generated method stub
		
		/*
		 * En este instante no existe ningún componente ternario que pueda albergar a otros componentes.
		 * Por eso se deja el método vacío.
		 */
	}

	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#eliminaTextosVacios()
	 */
	@Override
	public void eliminaTextosVacios() throws ComponentEditorException {
		// TODO Auto-generated method stub
		try{
			this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion1);
			this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion2);
			this.manager.getCentralManager().eliminarTextosVacios(this.cuadriculaEdicion3);
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error mientras se eliminaban Textos vacíos de un componente ternario.",ex);
		}
	}
	
	/**
	 * Devuelve la primera cuadricula padre del componente.
	 * 
	 * @return Primera cuadricula padre del componente.
	 */
	public GridExpression getCuadriculaEdicion1() {
		return this.cuadriculaEdicion1;
	}

	/**
	 * Devuelve la segunda cuadricula padre del componente.
	 * 
	 * @return Segunda cuadricula padre del componente.
	 */
	public GridExpression getCuadriculaEdicion2() {
		return this.cuadriculaEdicion2;
	}

	/**
	 * Devuelve la tercera cuadricula padre del componente.
	 * 
	 * @return Segunda cuadricula padre del componente.
	 */
	public GridExpression getCuadriculaEdicion3() {
		return this.cuadriculaEdicion3;
	}

	/*
	 * (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#getPosicionCentral()
	 */
	@Override
	public abstract int getPosicionCentral() throws ComponentEditorException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#seleccionar()
	 */
	@Override
	public void seleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		try{
			
			super.seleccionar();
			this.cuadriculaEdicion1.seleccionar(super.getColorFondoSeleccionado());
			this.cuadriculaEdicion2.seleccionar(super.getColorFondoSeleccionado());
			this.cuadriculaEdicion3.seleccionar(super.getColorFondoSeleccionado());
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error seleccionando un componente binario.",ex);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.addlink.tutormates.editor.Components.Componente#setFocoFinal()
	 */
	@Override
	public void setFocoFinal() throws ComponentEditorException {
		// TODO Auto-generated method stub
		try{
			Control[] con = getCuadriculaEdicion3().getChildren();
			Operator c = (Operator) con[con.length - 1];
			c.setFocoFinal();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error situando el cursor en la posición final de un componente ternario.",ex);
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
			throw new ComponentEditorException("Error situando el cursor en la primera posición de un componente ternario.",ex);
		}
	}

}
