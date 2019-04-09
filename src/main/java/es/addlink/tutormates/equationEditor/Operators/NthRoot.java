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
 * File: NthRoot.java
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
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * Es la clase encargada de construir una raíz cuadrada.
 * 
 * @author Ignacio Celaya Sesma
 */
public class NthRoot extends BinaryOperator {
	/**
	 * Linea superior de la raíz.
	 */
	private LineOperator lineaSuperior;

	/**
	 * Símbolo de la raíz.
	 */
	private Label simboloRaiz;

	/**
	 * Constructor.
	 * 
	 * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
	 * @throws ComponentEditorException
	 */
	public NthRoot(Manager manager, GridExpression cuadriculaPadre)throws ComponentEditorException {
		super(manager, cuadriculaPadre, "nthRoot", 57);
		// TODO Auto-generated constructor stub

		try{
			super.getCmpTodo().setLayout(new FormLayout());
			super.crearCuadriculaEdicion1("",true);
			super.crearCuadriculaEdicion2("",false);
			this.lineaSuperior = new LineOperator(super.getCmpTodo());
			this.simboloRaiz = new Label(super.getCmpTodo(), SWT.NONE);
			this.simboloRaiz.setBackground(super.getColorFondo());
			this.simboloRaiz.setText("\u221A");
	
			/* Composición de las partes del componente Raiz */
	
			
			FormData fd;
			fd = new FormData();
			fd.top = new FormAttachment(0,0);
			super.getCuadriculaEdicion1().setEsExponente(true);
			super.getCuadriculaEdicion1().setLayoutData(fd);
				
		
				int i1 = 1;
				int i2 = -2;
				int i3 = 0;
				int i4 = 2;
				int i5 = -10;
				int i6 = -7;
				
				if(Manager.isMac()){
					i1 = 1;
					i2 = -2;
					i3 = 0;
					i4 = 0;
					i5 = -8;
					i6 = -5;
				}
				
				// Colocación del símbolo
				fd = new FormData();
				fd.left = new FormAttachment(super.getCuadriculaEdicion1(), i5);
				fd.top = new FormAttachment(super.getCuadriculaEdicion1(), i6);
				this.simboloRaiz.setLayoutData(fd);
				
				// Colocación de la línea superior
				fd = new FormData();
				fd.bottom = new FormAttachment(this.simboloRaiz, i1);
				fd.left = new FormAttachment(this.simboloRaiz, i2);
				this.lineaSuperior.setLayoutData(fd);
		
				// Colocación de la cuadrícula con el Texto en la primera columna
				fd = new FormData();
				fd.left = new FormAttachment(this.simboloRaiz, i3);
				fd.top = new FormAttachment(this.lineaSuperior, i4);
				super.getCuadriculaEdicion2().setLayoutData(fd);
					
			/* ************************************************************************* */
			
			super.getCmpTodo().pack();
				
			setMenuEliminar();		
			listeners();
			
			super.setFocoInicio();
	
			super.getCuadriculaPadre().pack();
			super.setPosicionCentral(getPosicionCentral());
			
			ajustar();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una raíz.",ex);
		}
	}

	/**
	 * Modifica el tamaño del componente. Ajusta el tamaño del símbolo y la longitud de la línea superior.
	 * 
	 * @throws ComponentEditorException
	 */
	private void ajustar() throws ComponentEditorException{
		try{
			if (super.getCuadriculaEdicion2() != null && this.simboloRaiz != null) {
	
				super.getCuadriculaEdicion1().pack();
				super.getCuadriculaEdicion2().pack();
	
				// Cálculo del alto y ancho de la cuadrícula
				int tamY = super.getCuadriculaEdicion2().getSize().y;
				int tamX = super.getCuadriculaEdicion2().getSize().x;
				lineaSuperior.setNumCaracteres(tamX);
	
				int tam = (((tamY + 12) * 16) / 23) - 4;
				simboloRaiz.setFont(new Font(super.getCuadriculaPadre().getDisplay(), "Courier New", tam, SWT.BOLD));
				simboloRaiz.setSize(simboloRaiz.getSize().x, 1);
	
				super.getCmpTodo().pack();
				super.getCuadriculaPadre().pack();
				super.setPosicionCentral(getPosicionCentral());
			}
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el ajuste de una raíz.",ex);
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
	 * @see es.addlink.tutormates.editor.Components.CUnario#deseleccionar()
	 */
	@Override
	public void deseleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.deseleccionar();
		this.lineaSuperior.setBackground(super.getColorFondo());
		this.simboloRaiz.setBackground(super.getColorFondo());
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
			
			int inc = 0;
			
			if(Manager.isMac())
				inc = 1;
			
			return this.getCmpTodo().getSize().y / 2 - calculoPosicionCentral()-8 + inc;
		
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una raíz.",ex);
		}
	}

	private int calculoPosicionCentral(){
		try{
			Double d = super.getCuadriculaEdicion1().getSize().y*(-0.5)-4;
			int i = (int) Math.round(d);
			//System.out.println("tamaño: " + super.getCuadriculaEdicion1().getSize().y + "; double: " + d);
			
		return i;
		}catch(Exception ex){
			//ex.printStackTrace();
			return -11;
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
	 * @see es.addlink.tutormates.editor.Components.CUnario#seleccionar(boolean)
	 */
	@Override
	public void seleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.seleccionar();
		this.simboloRaiz.setBackground(super.getColorFondoSeleccionado());
		this.lineaSuperior.setBackground(super.getColorFondoSeleccionado());
		super.getCuadriculaEdicion1().seleccionar(super.getColorFondoSeleccionado());
		super.getCuadriculaEdicion2().seleccionar(super.getColorFondoSeleccionado());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.CUnario#setMenuEliminar()
	 */
	@Override
	protected void setMenuEliminar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.setMenuEliminar();
		this.simboloRaiz.setMenu(super.getMenuEliminar());
		this.lineaSuperior.setMenu(super.getMenuEliminar());
	}

}
