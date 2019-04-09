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
 * File: GeometricPoint.java
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
 * Clase encargada de añadir una coordenada para su representación en un gráfico.
 * 
 * @author Ignacio Celaya Sesma
 */
public class GeometricPoint extends BinaryOperator {
	/**
	 * Label que contiene el paréntesis izquierdo.
	 */
	private Label lblParentesis1;

	/**
	 * Label que contiene el paréntesis derecho.
	 */
	private Label lblParentesis2;

	/**
	 * Label en el que se muestra el separador de la coordenada.
	 */
	private Label lblSeparador;

	/**
	 * Posición central en la que se encuentra el componente.
	 */
	private int posicionCentral;

	/**
	 * Separador de la coordenada.
	 */
	private final String separador = ";";

	/**
	 * Constructor.
	 * 
	 * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
	 * @throws ComponentEditorException
	 */
	public GeometricPoint(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException{
		super(manager, cuadriculaPadre, "geometricPoint", 56);
		// TODO Auto-generated constructor stub

		try{
			this.setBackground(super.getColorFondo());
			super.getCmpTodo().setLayout(new FormLayout());
	
			super.crearCuadriculaEdicion2("",false);
			super.crearCuadriculaEdicion1("",false);
	
			this.lblParentesis1 = new Label(super.getCmpTodo(), SWT.NONE);
			this.lblParentesis2 = new Label(super.getCmpTodo(), SWT.NONE);
			this.lblSeparador = new Label(super.getCmpTodo(), SWT.NONE);
	
			this.lblParentesis1.setFont(new Font(this.getDisplay(), "Courier New", 15, SWT.BOLD));
			this.lblParentesis2.setFont(new Font(this.getDisplay(), "Courier New", 15, SWT.BOLD));
			this.lblSeparador.setFont(new Font(this.getDisplay(), "Courier New", 12, SWT.BOLD));
	
			this.lblParentesis1.setBackground(super.getColorFondo());
			this.lblParentesis2.setBackground(super.getColorFondo());
			this.lblSeparador.setBackground(super.getColorFondo());
	
			this.lblParentesis1.setText("(");
			this.lblParentesis2.setText(")");
			this.lblSeparador.setText(separador);
	
			/* Colocación de las partes */
	
				// Colocación del paréntesis izquierdo
				FormData fd = new FormData();
				fd.left = new FormAttachment(0, 0);
				this.lblParentesis1.setLayoutData(fd);
		
				// Colocación de la cuadrícula izquierda
				fd = new FormData();
				fd.left = new FormAttachment(this.lblParentesis1, 0);
				super.getCuadriculaEdicion1().setLayoutData(fd);
		
				// Colocación del separador
				fd = new FormData();
				fd.left = new FormAttachment(super.getCuadriculaEdicion1(), 0);
				this.lblSeparador.setLayoutData(fd);
		
				// Colocación de la cuadrícula derecha
				fd = new FormData();
				fd.left = new FormAttachment(this.lblSeparador, 1);
				super.getCuadriculaEdicion2().setLayoutData(fd);
		
				// Colocación del paréntesis derecho
				fd = new FormData();
				fd.left = new FormAttachment(super.getCuadriculaEdicion2(), -1);
				this.lblParentesis2.setLayoutData(fd);
	
			/* ************************************************************************* */
	
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
	
			setMenuEliminar();
			super.setPosicionCentral(getPosicionCentral());
			ajustar();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de una coordenada.",ex);
		}
	}

	/**
	 * Modifica el tamaño del componente y la altura de su contenido a medida que la expresión interior va cambiando.
	 * 
	 * @throws ComponentEditorException
	 */
	private void ajustar() throws ComponentEditorException {
		try{
			if (super.getCuadriculaEdicion1() != null && this.lblSeparador != null) {
	
				int diferencia = -1;
	
				if (super.getCuadriculaEdicion1().getPosicionCentral() > super.getCuadriculaEdicion2().getPosicionCentral()) {
					this.posicionCentral = super.getCuadriculaEdicion1().getPosicionCentral();
					diferencia = super.getCuadriculaEdicion1().getPosicionCentral() - (super.getCuadriculaEdicion2().getSize().y - (super.getCuadriculaEdicion2().getSize().y - super.getCuadriculaEdicion2().getPosicionCentral()));
					if (diferencia != super.getCuadriculaEdicion2().getLocation().y) {
						super.getCmpTodo().pack();
						super.getCuadriculaEdicion2().setLocation(super.getCuadriculaEdicion2().getLocation().x, diferencia);
					}
					else {
						super.getCmpTodo().pack();
						super.getCuadriculaEdicion2().setLocation(super.getCuadriculaEdicion2().getLocation().x, diferencia);
						super.getCuadriculaEdicion1().pack();
						super.getCuadriculaEdicion2().pack();
					}
				}
	
				else if (super.getCuadriculaEdicion2().getPosicionCentral() > super.getCuadriculaEdicion1().getPosicionCentral()) {
	
					this.posicionCentral = super.getCuadriculaEdicion2().getPosicionCentral();
					diferencia = super.getCuadriculaEdicion2().getPosicionCentral() - (super.getCuadriculaEdicion1().getSize().y - (super.getCuadriculaEdicion1().getSize().y - super.getCuadriculaEdicion1().getPosicionCentral()));
					if (diferencia != super.getCuadriculaEdicion1().getLocation().y) {
						super.getCmpTodo().pack();
						super.getCuadriculaEdicion1().setLocation(super.getCuadriculaEdicion1().getLocation().x, diferencia);
					}
					else {
						super.getCmpTodo().pack();
						super.getCuadriculaEdicion1().setLocation(super.getCuadriculaEdicion1().getLocation().x, diferencia);
						super.getCuadriculaEdicion1().pack();
						super.getCuadriculaEdicion2().pack();
					}
				}
	
				else {
					this.posicionCentral = super.getCuadriculaEdicion2().getPosicionCentral();
					diferencia = super.getCuadriculaEdicion2().getPosicionCentral() - (super.getCuadriculaEdicion1().getSize().y - (super.getCuadriculaEdicion1().getSize().y - super.getCuadriculaEdicion1().getPosicionCentral()));
					if (diferencia != super.getCuadriculaEdicion1().getLocation().y) {
						super.getCmpTodo().pack();
						super.getCuadriculaEdicion1().setLocation(super.getCuadriculaEdicion1().getLocation().x, diferencia);
						super.getCuadriculaEdicion2().setLocation(super.getCuadriculaEdicion2().getLocation().x, diferencia);
					}
					else {
						super.getCmpTodo().pack();
						super.getCuadriculaEdicion1().setLocation(super.getCuadriculaEdicion1().getLocation().x, diferencia);
						super.getCuadriculaEdicion2().setLocation(super.getCuadriculaEdicion2().getLocation().x, diferencia);
						super.getCuadriculaEdicion1().pack();
						super.getCuadriculaEdicion2().pack();
					}
					this.posicionCentral = super.getCuadriculaEdicion1().getSize().y / 2 + super.getCuadriculaEdicion1().getLocation().y;
				}
	
				super.getCuadriculaPadre().pack();
				super.setPosicionCentral(getPosicionCentral());
	
				this.lblSeparador.setLocation(this.lblSeparador.getLocation().x, this.posicionCentral - (this.lblSeparador.getSize().y / 2));
				this.lblParentesis1.setLocation(this.lblParentesis1.getLocation().x, this.posicionCentral - (this.lblParentesis1.getSize().y / 2));
				this.lblParentesis2.setLocation(this.lblParentesis2.getLocation().x, this.posicionCentral - (this.lblParentesis2.getSize().y / 2));
			}
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el ajuste de una coordenada.",ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.CBinario#cambioPosicionCentralEnCuadricula()
	 */
	@Override
	protected void cambioPosicionCentralEnCuadricula() throws ComponentEditorException{
		ajustar();
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
		this.lblSeparador.setBackground(super.getColorFondo());
		this.lblParentesis1.setBackground(super.getColorFondo());
		this.lblParentesis2.setBackground(super.getColorFondo());
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
			return this.getCmpTodo().getSize().y / 2;
		}catch(Exception ex){
			throw new ComponentEditorException("Error en el cáculo de la posición central de una coordenada.",ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.CBinario#seleccionar(boolean)
	 */
	@Override
	public void seleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.seleccionar();
		this.lblSeparador.setBackground(super.getColorFondoSeleccionado());
		this.lblParentesis1.setBackground(super.getColorFondoSeleccionado());
		this.lblParentesis2.setBackground(super.getColorFondoSeleccionado());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#setMenuEliminar()
	 */
	@Override
	protected void setMenuEliminar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.setMenuEliminar();
		this.lblParentesis1.setMenu(super.getMenuEliminar());
		this.lblParentesis2.setMenu(super.getMenuEliminar());
		this.lblSeparador.setMenu(super.getMenuEliminar());
	}

}
