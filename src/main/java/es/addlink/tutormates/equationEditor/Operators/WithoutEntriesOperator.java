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
 * File: WithoutEntriesOperator.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.widgets.Label;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Manager.Manager;

public class WithoutEntriesOperator extends Operator {
	
	private Manager manager;
	private Label symbol;
	
	public WithoutEntriesOperator(Manager manager, GridExpression cuadriculaPadre, String nombre, int id) throws ComponentEditorException {
		super(manager, cuadriculaPadre, true, Category.WithoutEntriesType, nombre, id);
		// TODO Auto-generated constructor stub
		this.manager = manager;
		this.setFocusNextOperator();
		this.manager.getHistoryManager().guardarHistorial(false);
	}

	protected void setSymbol(Label lbl){
		this.symbol = lbl;
	}
	
	@Override
	protected void cambioPosicionCentralEnCuadricula() throws ComponentEditorException {
	// TODO Auto-generated method stub

	}

	@Override
	public void eliminaComponentesInternosSeleccionados() throws ComponentEditorException {
	// TODO Auto-generated method stub

	}

	@Override
	public void eliminaTextosVacios() throws ComponentEditorException {
	// TODO Auto-generated method stub

	}

	@Override
	public int getPosicionCentral() throws ComponentEditorException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFocoFinal() throws ComponentEditorException {
	// TODO Auto-generated method stub
		int pos = this.manager.getCentralManager().getOperatorPosition(this, super.getCuadriculaPadre());
		if(pos == super.getCuadriculaPadre().getNumColumnas()-1)
			this.manager.getCentralManager().insertarTextoAlFinal(super.getCuadriculaPadre());
		else
			try {
				this.manager.getCentralManager().insertarTexto(super.getCuadriculaPadre(), pos+1);
			}
			catch (ManagerEditorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void setFocoInicio() throws ComponentEditorException {
	// TODO Auto-generated method stub
		int pos = this.manager.getCentralManager().getOperatorPosition(this, super.getCuadriculaPadre());
		if(pos == 0)
			this.manager.getCentralManager().insertarTextoAlInicio(super.getCuadriculaPadre());
		else
			try {
				this.manager.getCentralManager().insertarTexto(super.getCuadriculaPadre(), pos-1);
			}
			catch (ManagerEditorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void setFocusNextOperator(){
		try {
			int pos = this.manager.getCentralManager().getOperatorPosition(this, super.getCuadriculaPadre());
			if(pos < super.getCuadriculaPadre().getChildren().length - 1){
				Operator ope = (Operator)super.getCuadriculaPadre().getChildren()[pos+1];
				ope.setFocoInicio();
			}else
				this.manager.getCentralManager().insertarTextoAlFinal(super.getCuadriculaPadre());
		}
		catch (ComponentEditorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setFocusPreviousOperator(){
		try {
			int pos = this.manager.getCentralManager().getOperatorPosition(this, super.getCuadriculaPadre());
			if(pos > 0){
				Operator ope = (Operator)super.getCuadriculaPadre().getChildren()[pos-1];
				ope.setFocoFinal();
			}else
				this.manager.getCentralManager().insertarTextoAlInicio(super.getCuadriculaPadre());
		}
		catch (ComponentEditorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Manager getManager(){
		return this.manager;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#seleccionar()
	 */
	@Override
	public void seleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.seleccionar();
		this.symbol.setMenu(super.getMenuEliminar());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.CBinario#deseleccionar()
	 */
	@Override
	public void deseleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.deseleccionar();
		this.symbol.setBackground(getColorFondo());
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
		
	}

}
