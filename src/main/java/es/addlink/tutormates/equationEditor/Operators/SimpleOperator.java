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
 * File: SimpleOperator.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Manager.SimpleOperatorManager;

/**
 * Componente que encapsula un sólo TextBox. Es el único componente capaz de albergar texto.
 * 
 * @author Ignacio Celaya Sesma
 */
public class SimpleOperator extends Operator {
	private Manager manager;
	/**
	 * True si se desea que un TextBox cambie de color cuando se encuentre vacío, false en caso contrario.
	 */
	private final boolean cambiaColorSiEstaVacio = true;

	/**
	 * Gestion de los listeners de un Texto y un TextBox
	 */
	private SimpleOperatorManager gestion;

	/**
	 * TextBox contenido en Texto, y en el que se guardará el texto introducido por teclado.
	 */
	private TextBox textbox;

	/**
	 * Constructor
	 * 
	 * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
	 * @param formato Tipo de texto o componentes que se podrán introducir en este Texto.
	 * @param textoInicial Texto con el que se cargará el componente Texto.
	 * @throws ComponentEditorException
	 */
	public SimpleOperator(Manager manager, GridExpression cuadriculaPadre, int formato, String textoInicial)throws ComponentEditorException {
		super(manager, cuadriculaPadre, false, "texto", "texto", -1);
		// TODO Auto-generated constructor stub

		try{
			this.manager = manager;
			this.textbox = new TextBox(super.getCmpTodo(), super.getColorFondo(), super.getColorFondoSeleccionado(), formato, textoInicial);
	
			this.manager.getStateManager().setTextoActivo(this);
			this.manager.getStateManager().setPosicionCursor(this.getPosicionCursor());
	
			this.gestion = new SimpleOperatorManager(this.manager, this);
			if (textoInicial != "")
				gestion.ajustar(true);
	
			if (cuadriculaPadre.esExponente())
				setExponente();
	
			if (this.getCambiaColorSiEstaVacio()) {
				if (this.getNumCaracteres() > 0)
					this.setColorLleno();
				else
					this.setColorVacio();
			}
	
			super.getCmpTodo().pack();
			super.setPosicionCentral(getPosicionCentral());
			listeners();
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de un Texto.",ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#cambioPosicionCentralEnCuadricula()
	 */
	@Override
	protected void cambioPosicionCentralEnCuadricula() {}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see Components.Componente#deseleccionar()
	 */
	@Override
	public void deseleccionar() throws ComponentEditorException{
		// TODO Auto-generated method stub
		super.deseleccionar();
		setBackground(super.getColorFondo());

		if (getNumCaracteres() > 0)
			this.setColorLleno();
		else if (this.cambiaColorSiEstaVacio)
			this.setColorVacio();
		else
			this.setColorLleno();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#eliminaComponentesInternosSeleccionados()
	 */
	@Override
	public void eliminaComponentesInternosSeleccionados(){}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#eliminaTextosVacios()
	 */
	@Override
	public void eliminaTextosVacios() {
	// TODO Auto-generated method stub
	}

	/**
	 * Devuelve true si el componente Texto se encuentra seleccionado, false en caso contrario.
	 * 
	 * @return True si el componente Texto se encuentra seleccionado, false en caso contrario.
	 */
	@Override
	public boolean estaSeleccionado() {
		return super.estaSeleccionado();
	}

	/**
	 * Devuelve true si el TextBox cambia su color de fondo cuando se encuentra vacío, false en caso contrario.
	 * 
	 * @return True si el TextBox cambia su color de fondo cuando se encuentra vacío, false en caso contrario.
	 */
	public boolean getCambiaColorSiEstaVacio() {
		return this.cambiaColorSiEstaVacio;
	}

	/**
	 * Devuelve una cadena que contiene los caracteres que se encuentran seleccionados.
	 * 
	 * @return Cadena que contiene los caracteres que se encuentran seleccionados.
	 */
	public String getCaracteresSeleccionados() {
			return this.textbox.getSelectionText();
	}

	/**
	 * Calcula un tamaño para el TextBox de acuerdo a los parámetros de entrada.
	 * 
	 * @param anchura Entero que contiene la anchura.
	 * @param altura Entero que contiene la altura.
	 * @return El Point con el nuevo tamaño.
	 */
	public Point getComputeSize(int anchura, int altura) {
			return this.textbox.computeSize(anchura, altura);
	}

	/**
	 * Devuelve el tipo de texto o componentes que se podrán introducir en este Texto.
	 * 
	 * @return El tipo de texto o componentes que se podrán introducir en este Texto.
	 */
	public int getFormato() {
			return this.textbox.getFormato();
	}

	/**
	 * Devuelve el GC del TextBox.
	 * 
	 * @return GC del TextBox.
	 */
	public GC getGC() {
			return new GC(this.textbox);
	}

	/**
	 * Devuelve la longitud del texto del TextBox.
	 * 
	 * @return La longitud del texto.
	 */
	public int getNumCaracteres() {
			return this.textbox.getCharCount();
	}

	/**
	 * Devuelve el número de caracteres seleccionados.
	 * 
	 * @return Número de caracteres seleccionados.
	 */
	public int getNumCaracteresSeleccionados() {
			return this.textbox.getSelectionCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Components.Componente#getPosicionCentral()
	 */
	@Override
	public int getPosicionCentral() {
		int totalAltura = super.getCmpTodo().getSize().y;
		return totalAltura / 2;
	}

	/**
	 * Devuelve la posición actual del cursor en el TextBox.
	 * 
	 * @return Posición actual del cursor en el TextBox.
	 */
	public int getPosicionCursor() {
			return this.textbox.getCaretPosition();
	}

	/**
	 * Devuelve el tamaño del TextBox.
	 * 
	 * @return Tamaño del TextBox.
	 */
	public Point getSizeTextBox() {
			return this.textbox.getSize();
	}

	/**
	 * Devuelve el TextBox contenido en el componente de tipo Texto.
	 * 
	 * @return El TextBox contenido en el componente de tipo Texto.
	 */
	public TextBox getTextBox() {
			return this.textbox;
	}

	/**
	 * Devuelve el texto que se encuentra en el TextBox.
	 * 
	 * @return El texto.
	 */
	public String getTexto() {
			return this.textbox.getText();
	}

	/**
	 * Método encargado de asignar listeners a Texto y TextBox.
	 */
	private void listeners() {

		// Controla el tamaño del componente Texto
		this.addControlListener(this.gestion.getListenerResized());

		// El TextBox recibe o pierde el foco
		this.textbox.addFocusListener(this.gestion.getListenerFocus());

		// Pulsación con cualquier botón del ratón dentro de un TextBox
		this.textbox.addListener(SWT.MouseDown, this.gestion.getListenerClick());

		// Pulsación con el teclado estando el foco en un TextBox
		this.textbox.addKeyListener(this.gestion.getListenerKey());
		
		// Este adaptador es manejado desde fuera del editor. Se puede utilizar para captar los "enters" desde fuera.
		this.textbox.addKeyListener(this.manager.getTutorMatesEditor().getKeyAdapter());
		//this.textbox.addKeyListener(TutorMatesEditorSingleton.getInstance().getKeyAdapter());

		// Verifica los caracteres antes de ser introducidos en el TextBox. Siguiento unas políticas, permitirá introducirlos o no.
		this.textbox.addVerifyListener(this.gestion.getListenerVerify());

		// El contenido del TextBox es modificado.
		this.textbox.addModifyListener(this.gestion.getListenerModify());

		// Controla los menús contextuales que se intentan ejecutar al pulsar con el botón derecho del ratón sobre el TextBox.
		//this.textbox.addMenuDetectListener(this.gestion.getListenerMenu());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Control#pack()
	 */
	@Override
	public void pack(){
		this.textbox.pack();
		super.getCmpTodo().pack();
		super.pack();
		this.getCuadriculaPadre().pack();
	}

	/**
	 * Realiza un pack a todos las partes de Texto menos al TextBox.
	 */
	public void packSinTextBox() {
		super.getCmpTodo().pack();
		super.pack();
		this.getCuadriculaPadre().pack();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Components.Componente#seleccionar()
	 */
	@Override
	public void seleccionar() throws ComponentEditorException {
		// TODO Auto-generated method stub
		super.seleccionar();
		
		setBackground(super.getColorFondoSeleccionado());
		this.textbox.setColorLlenoSeleccionado();
		if ((this.textbox.getCharCount() == 0) && (this.cambiaColorSiEstaVacio))
			this.textbox.setColorVacioSeleccionado();
	}

	/**
	 * Establece el color lleno al TextBox.
	 */
	public void setColorLleno() {
		this.textbox.setColorLleno();
	}

	/**
	 * Establece el color lleno al TextBox seleccionado.
	 */
	public void setColorLlenoSeleccionado() {
		this.textbox.setColorLlenoSeleccionado();
	}

	/**
	 * Establece el color vacío al TextBox.
	 */
	public void setColorVacio() {
		this.textbox.setColorVacio();
	}

	/**
	 * Establece el color vacío al TextBox seleccionado.
	 */
	public void setColorVacioSeleccionado() {
		this.textbox.setColorVacioSeleccionado();
	}

	/**
	 * Comunica al TextBox que forma parte de un exponente.
	 */
	public void setExponente() {
		this.textbox.setExponente();
	}

	/**
	 * Establece el foco en el TextBox.
	 */
	public void setFoco() {
		this.textbox.setFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#setFocoFinal()
	 */
	@Override
	public void setFocoFinal() throws ComponentEditorException{
		try{
			this.textbox.setFocus();
			this.textbox.setSelection(getNumCaracteres(), getNumCaracteres());
			this.manager.getStateManager().setTextoActivo(this);
			this.manager.getStateManager().setPosicionCursor(getNumCaracteres());
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de un periódico puro.",ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.addlink.tutormates.editor.Components.Componente#setFocoInicio()
	 */
	@Override
	public void setFocoInicio() throws ComponentEditorException{
		try{
			this.textbox.setFocus();
			this.textbox.setSelection(0, 0);
			this.manager.getStateManager().setTextoActivo(this);
			this.manager.getStateManager().setPosicionCursor(0);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción de un periódico puro.",ex);
		}
	}

	/**
	 * Establece el tipo de texto o componentes que se podrán introducir en este Texto.
	 * 
	 * @param formato Nuevo formato.
	 */
	public void setFormato(int formato) {
		this.textbox.setFormato(formato);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Components.Componente#setPosicionCentral()
	 */
	@Override
	public void setPosicionCentral(int posicion)throws ComponentEditorException {
		super.setPosicionCentral(posicion);
	}

	/**
	 * Establece la posición del cursor en el TextBox.
	 * 
	 * @param posicion Nueva posición del cursor en el TextBox.
	 */
	public void setPosicionCursor(int posicion) {
		this.textbox.setSelection(posicion);
	}

	/**
	 * Establece el tamaño al TextBox.
	 * 
	 * @param punto Nuevo tamaño que tendrá el TextBox.
	 */
	public void setSizeTextBox(Point punto) {
		this.textbox.setSize(punto);
	}

	/**
	 * Establece el texto en el TextBox.
	 * 
	 * @param string Nuevo texto que tendrá el TextBox.
	 */
	public void setTexto(String string) throws ComponentEditorException {
		try{
			this.textbox.setText(string);
			this.gestion.ajustar(true);
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Widget#toString()
	 */
	@Override
	public String toString() {
		return getTexto();
	}
}