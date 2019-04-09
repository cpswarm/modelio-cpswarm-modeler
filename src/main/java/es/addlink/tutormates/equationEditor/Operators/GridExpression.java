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
 * File: GridExpression.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * Una cuadrícula únicamente sirve para albergar componentes. Una cuadrícula puede o no, estar dentro de un componente para albergar a otros.
 * 
 * @author Ignacio Celaya Sesma
 */
public class GridExpression extends Composite {
	private Manager manager;
	/**
	 * Composite donde se aloja la cuadrícula. El composite siempre será cmpTodo.
	 */
	private Composite compositePadre;

	/**
	 * Si se trata de la primera cuadrícula (la del nivel superior), este atributo es true, false en caso contrario.
	 */
	private boolean esCuadriculaPadre;

	/**
	 * Si contiene una expresión que actúa como exponente, este atributo es true, false en caso contrario.
	 */
	private boolean esExponente;

	/**
	 * Tabla de la cuadrícula.
	 */
	private GridLayout grid;

	/**
	 * Posición central para todos los componentes de la cuadrícula.
	 */
	private int posicionCentral;

	/**
	 * Constructor
	 * 
	 * @param parent composite donde se aloja la cuadrícula.
	 * @throws ComponentEditorException
	 */
	public GridExpression(Manager manager, Composite parent) throws ComponentEditorException{
		super(parent, SWT.NONE);
		// TODO Auto-generated constructor stub

		try{
			this.manager = manager;
			this.compositePadre = parent;
			this.esCuadriculaPadre = false;
			this.esExponente = false;
	
			this.grid = new GridLayout();
			this.grid.numColumns = 0;
			this.grid.makeColumnsEqualWidth = false;
			this.grid.horizontalSpacing = 0;
			this.grid.verticalSpacing = 0;
			this.grid.marginHeight = 0;
			this.grid.marginWidth = 0;
			this.grid.marginBottom = 0;
	
			this.posicionCentral = this.getSize().y / 2;
	
			setLayout(this.grid);

		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una cuadrícula.",ex);
		}
	}

	/**
	 * Añade una columna al grid asociado a la cuadrícula.
	 */
	public void aniadirColumna() {
		this.grid.numColumns = this.grid.numColumns + 1;
	}

	/**
	 * Elimina la selección de todos los componentes alojados en la cuadrícula.
	 * 
	 * @throws ComponentEditorException
	 */
	public void deseleccionarTodo() throws ComponentEditorException{
		try{
			this.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
			Control[] c = this.getChildren();
			for (int i = 0; i < c.length; i++)
				((Operator) c[i]).deseleccionar();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
		}
	}

	/**
	 * Elimina una columna del grid asociado a la cuadrícula.
	 */
	public void eliminarColumna() {
		this.grid.numColumns = this.grid.numColumns - 1;
	}

	/**
	 * Devuelve true si se trata de la cuadrícula padre, false en caso contrario.
	 * 
	 * @return true si se trata de la cuadrícula padre, false en caso contrario.
	 */
	public boolean esCuadriculaPadre() {
		return esCuadriculaPadre;
	}

	/**
	 * Devuelve true si se trata de un exponente, false en caso contrario.
	 * 
	 * @return true si se trata de un exponente, false en caso contrario.
	 */
	protected boolean esExponente() {
		return esExponente;
	}
	
	/**
	 * Devuelve el componente al que pertenece la cuadrícula. No confundir con el padre de la cuadrícula.
	 * 
	 * @return Componente al que pertenece la cuadrícula. No confundir con el padre de la cuadrícula.
	 * @throws ComponentEditorException
	 */
	public Operator getComponentePadre() throws ComponentEditorException{
		try{
			/*
			 * Si la cuadrícula es la padre de todas, el padre de ésta no es de tipo Componente. 
			 * Si se intenta hacer un cast(Componente), saltaría una excepción de tipo ClassCastExcepcion.
			 */
			if (this.esCuadriculaPadre)
				return null;
			else
				return (Operator) this.compositePadre.getParent();
		
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
		}
	}

	/**
	 * Devuelve la siguiente cuadrícula superior a la actual.
	 * 
	 * @return Siguiente cuadrícula superior a la actual.
	 * @throws ComponentEditorException
	 */
	public GridExpression getCuadriculaSuperior() throws ComponentEditorException{
		try{
			
			if(!this.isDisposed() && !this.esCuadriculaPadre){
				return (GridExpression) this.getParent().getParent().getParent();
			}else{
				if(this.isDisposed())
					return null;
				else
					return this;
			}

		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
		}
	}

	/**
	 * Devuelve, una vez calculado, la altura del componente cuya posición central es más baja.
	 * 
	 * @return Altura del componente cuya posición central es más baja.
	 * @throws ComponentEditorException
	 */
	private int getMayorPosicionCentral() throws ComponentEditorException{
		try{
			int max = 0;
	
			Control[] c = this.getChildren();
			Operator comp;
			for (int i = 0; i < c.length; i++) {
				comp = (Operator) c[i];
				if (comp.getPosicionCentral() > max)
					max = comp.getPosicionCentral();
			}
			return max;
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
		}
	}
	
	/**
	 * Devuelve el número de columnas que tiene actualmente el grid de la cuadrícula.
	 * 
	 * @return el número de columnas que tiene actualmente el grid de la cuadrícula.
	 */
	public int getNumColumnas() {
		return this.grid.numColumns;
	}

	/**
	 * Devuelve la posición central de la cuadrícula en el momento de la petición.
	 * 
	 * @return Posición central de la cuadrícula en el momento de la petición.
	 */
	public int getPosicionCentral() {
		return this.posicionCentral;
	}

	/**
	 * Devuelve true si algún componente alojado en la cuadrícula se encuentra seleccionado, false en caso contrario.
	 * 
	 * @return True si algún componente alojado en la cudrícula se encuentra seleccionado, false en caso contrario.
	 * @throws ComponentEditorException
	 */
	public boolean haySeleccion() throws ComponentEditorException{
		try{
			
			boolean haySeleccion = false;
			int i = 0;
			Control[] children = this.getChildren();
	
			while ((i < children.length) && (!haySeleccion)) {
				if (((Operator) children[i]).estaSeleccionado())
					haySeleccion = true;
				i++;
			}
	
			return haySeleccion;

		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
		}
	}

	/**
	 * Selecciona todos los componentes de la cuadrícula.
	 * 
	 * @param colorSeleccion Color de la selección.
	 * @throws ComponentEditorException
	 */
	protected void seleccionar(Color colorSeleccion) throws ComponentEditorException{
		try{
			
			this.setBackground(colorSeleccion);
			Control[] c = this.getChildren();
			for (int i = 0; i < c.length; i++)
				((Operator) c[i]).seleccionar();
		
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
		}
	}

	public void pack(){
		super.pack();
		if(this.esCuadriculaPadre())
			this.getParent().pack();
	}
	
	/**
	 * Modifica el valor del atributo.
	 * 
	 * @param esCuadriculaPadre si es la cuadrícula padre true, false en caso contrario.
	 */
	public void setEsCuadriculaPadre(boolean esCuadriculaPadre) {
		this.esCuadriculaPadre = esCuadriculaPadre;
	}

	/**
	 * Modifica el valor del atributo.
	 * 
	 * @param esExponente Si es un exponente true, false en caso contrario.
	 */
	protected void setEsExponente(boolean esExponente) {
		this.esExponente = esExponente;
	}

	/**
	 * Establece la posición central de la cuadrícula.
	 * 
	 * @throws ComponentEditorException
	 */
	public void setPosicionCentral() throws ComponentEditorException{

		try{
			
			this.posicionCentral = getMayorPosicionCentral();
			
			Control[] c = this.getChildren();
			
			Operator comp=null;
			int alt;
	
			for (int i = 0; i < c.length; i++) {
				comp = (Operator) c[i];
				alt = this.posicionCentral - comp.getPosicionCentral();
				comp.setAltura(alt);
			}
			if(comp.getComponentePadre() != null)
				comp.getComponentePadre().cambioPosicionCentralEnCuadricula();
		
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
		}
	}

	/**
	 * Elimina todos los componentes que contiene la cuadrícula y el número de columnas se establece a cero.
	 * 
	 * @throws ComponentEditorException
	 */
	public void vaciar() throws ComponentEditorException{
		try{
			
			Control[] c = this.getChildren();
			for (int i = 0; i < c.length; i++)
				c[i].dispose();
	
			this.grid.numColumns = 0;
	
			this.manager.getStateManager().setTextoActivo(null);
			this.manager.getStateManager().setPosicionCursor(-1);
			this.manager.getStateManager().setHaySeleccion(false);
			this.posicionCentral = 0;
			this.pack();
			
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
		}
	}
}
