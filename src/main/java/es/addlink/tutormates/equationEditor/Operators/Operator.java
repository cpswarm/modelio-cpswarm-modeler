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
 * File: Operator.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;

/**
 * Clase abstracta que representa un sólo componente. Ejemplo de componente: Raiz, Fracción, Texto, etc.
 * 
 * @author Ignacio Celaya Sesma
 */
public abstract class Operator extends Composite {
	private Manager manager;
	/**
	 * Composite alojado dentro del componente y que contiene todas sus partes. Puede vascular verticalmente por el componente, para colocar sus partes a la altura correcta.
	 */
	private Composite cmpTodo;

	/**
	 * Color utilizado para el background de los componentes cuando no se encuentran seleccionados.
	 */
	private final Color colorFondo = super.getDisplay().getSystemColor(SWT.COLOR_WHITE);

	/**
	 * Color gris, utilizado para el background de los componentes cuando se encuentran seleccionados.
	 */
	private final Color colorFondoSeleccionado = new Color(super.getDisplay(), 200, 200, 200);

	/**
	 * Cuadrícula donde se aloja el componente.
	 */
	private GridExpression grid;

	/**
	 * True si el componente está seleccionado, false en caso contrario.
	 */
	private boolean estaSeleccionado;

	/**
	 * Identificador del componente.
	 */
	private int id;

	/**
	 * Nombre del componente.
	 */
	private String nombre;

	/**
	 * Posición central (vertical) del componente.
	 */
	private int posicionCentral;

	/**
	 * Nombre del tipo heredado del componente.
	 */
	private String tipoComponente;

	public void setTipoComponente(String tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	/**
	 * Constructor
	 * 
	 * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
	 * @param posicionarComponente Debe ser true si se quiere que el componente se inserte dependiendo de la posición del cursor, false en caso contrario.
	 * @param tipoComponente Nombre del tipo heredado del componente.
	 * @param nombre Nombre del componente.
	 * @param id Identificador del componente.
	 * @throws ComponentEditorException
	 */
	public Operator(Manager manager, GridExpression cuadriculaPadre, boolean posicionarComponente, String tipoComponente, String nombre, int id) throws ComponentEditorException {
		super(cuadriculaPadre, SWT.NONE);
		// TODO Auto-generated constructor stub
		
		try{
			this.manager = manager;		
			this.grid = cuadriculaPadre;
			this.tipoComponente = tipoComponente;
			this.nombre = nombre;
			this.id = id;
			this.estaSeleccionado = false;
	
			this.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1));
	
			this.cmpTodo = new Composite(this, SWT.NONE);
			this.cmpTodo.setCursor(new Cursor(super.getDisplay(), SWT.CURSOR_HAND));
			this.cmpTodo.setBackground(this.colorFondo);
			
			//SelectionManager sc = new SelectionManager(cmpTodo,super.getDisplay(),true);
			//sc.select();
			
			this.setBackground(this.colorFondo);
			
			
			
			if (posicionarComponente)
				this.manager.getCentralManager().colocarComponente(this);
			else
				this.grid.aniadirColumna();
	
			controlTextosVacios();
			
			
			
			
		}catch(EditorException ex){
			throw new ComponentEditorException(ex);
		}catch(Exception ex){
			throw new ComponentEditorException("Error en la construcción genérica del componente." + this.nombre,ex); //$NON-NLS-1$
		}
	}
	
	/**
	 * Método que se ejecuta cada vez que hay un cambio en la posición central de la cuadrícula.
	 * 
	 * @throws ComponentEditorException
	 */
	protected abstract void cambioPosicionCentralEnCuadricula() throws ComponentEditorException;

	/**
	 * Si en la cuadrícula donde se inserta el nuevo componente existe algún Texto vacío, éste se elimina de acuerdo a unas políticas.
	 * 
	 * @throws ComponentEditorException
	 */
	private void controlTextosVacios() throws ComponentEditorException {
		
		try{
		
			/* primero se controla si el textbox anterior está vacío y es posible eliminarlo */
				if ((this.manager.getStateManager().getTextoActivo() != null) && (!this.manager.getStateManager().getTextoActivo().isDisposed())) {
					SimpleOperator textoAnterior = this.manager.getStateManager().getTextoActivo();
					GridExpression cAnterior = textoAnterior.getCuadriculaPadre();
					if ((textoAnterior != null) && (textoAnterior.getNumCaracteres() == 0)) {
						if (cAnterior.getNumColumnas() > 1)
							this.manager.getCentralManager().eliminarComponente(textoAnterior);
					}
				}
			/* **************************************************************************** */

			if (getNombre() != "texto") {
	
				GridExpression cua = (GridExpression) getParent();
	
				if (cua.getNumColumnas() > 0) {
	
					Control[] children = cua.getChildren();
					int i = 0, numCar = 0;
					boolean salida = false;
	
					while ((!salida) && (i < children.length)) {
						Operator comp = (Operator) children[i];
						if (comp.getNombre() == "texto") {
							numCar = ((SimpleOperator) comp).getNumCaracteres();
							if (numCar == 0) {
								this.manager.getCentralManager().eliminarComponente(comp);
								salida = true;
							}
						}
						i++;
					}
				}
			}
		}catch(Exception ex){
			throw new ComponentEditorException("Error eliminando Textos vacíos.",ex); //$NON-NLS-1$
		}
	}

	/**
	 * Elimina la selección del componente.
	 * 
	 * @throws ComponentEditorException
	 */
	public void deseleccionar() throws ComponentEditorException{
		try{
			this.estaSeleccionado = false;
			this.manager.getActionManager().setEstadoBtnEliminar(false);
			this.setBackground(this.getColorFondo());
			this.cmpTodo.setBackground(this.getColorFondo());
		}catch(Exception ex){
			throw new ComponentEditorException("Error deseleccionando un componente.",ex); //$NON-NLS-1$
		}
	}

	/**
	 * Elimina los componentes que se encuentran seleccionados dentro de éste componente.
	 * 
	 * @throws ComponentEditorException
	 */
	public abstract void eliminaComponentesInternosSeleccionados() throws ComponentEditorException;

	/**
	 * Elimina todos los Textos vacíos que haya dentro del componente.
	 * 
	 * @throws ComponentEditorException
	 */
	public abstract void eliminaTextosVacios() throws ComponentEditorException;

	/**
	 * Devuelve true si el componente se encuentra seleccionado, false en caso contrario.
	 * 
	 * @return True si el componente se encuentra seleccionado, false en caso contrario.
	 */
	public boolean estaSeleccionado() {
		return this.estaSeleccionado;
	}

	/**
	 * Devuelve el composite que contiene las partes internas del componente.
	 * 
	 * @return Composite que contiene las partes internas del componente.
	 */
	protected Composite getCmpTodo() {
		return this.cmpTodo;
	}

	/**
	 * Devuelve el color de fondo por defecto cuando no existe selección.
	 * 
	 * @return Color blanco.
	 */
	protected Color getColorFondo() {
		return colorFondo;
	}

	/**
	 * Devuelve el color de selección para el fondo.
	 * 
	 * @return Color de selección.
	 */
	protected Color getColorFondoSeleccionado() {
		return colorFondoSeleccionado;
	}

	/**
	 * Devuelve el componente padre de éste componente.
	 * 
	 * @return El componente padre de éste componente.
	 * @throws ComponentEditorException
	 */
	public Operator getComponentePadre() throws ComponentEditorException{
		try{
			
			if (!this.getCuadriculaPadre().esCuadriculaPadre())
				return this.getCuadriculaPadre().getComponentePadre();
			return null;
			
		}catch(Exception ex){
			throw new ComponentEditorException("Error al obtener el componente padre de un componente.",ex); //$NON-NLS-1$
		}
	}

	/**
	 * Devuelve la cuadrícula en la que se aloja el componente.
	 * 
	 * @return Cuadrícula en la que se aloja el componente.
	 */
	public GridExpression getCuadriculaPadre() {
		return this.grid;
	}

	/**
	 * Devuelve el identificador del componente.
	 * 
	 * @return Identificador del componente.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Devuelve el listener para deseleccionar el componente.
	 * 
	 * @return Listener para deseleccionar el componente.
	 * @throws ComponentEditorException
	 */
	public Listener getListenerDeseleccion() throws ComponentEditorException{
		try{
			
			Listener mouseListener = new Listener() {
				public void handleEvent(Event e) {
					try{
						cmpTodo.setBackground(getColorFondo());
						Operator.this.deseleccionar();
					}catch(Exception ex){}
				}
			};
			return mouseListener;
			
		}catch(Exception ex){
			throw new ComponentEditorException(ex);
		}
	}

	/**
	 * Devuelve el listener para seleccionar el componente.
	 * 
	 * @return Listener para seleccionar el componente.
	 * @throws ComponentEditorException
	 */
	public Listener getListenerSeleccion() {
		Listener mouseListener = new Listener() {
			public void handleEvent(Event e) {
				if (!estaSeleccionado) {
					cmpTodo.setBackground(getColorFondoSeleccionado());
				}
			}
		};
		return mouseListener;
	}

	/**
	 * Devuelve el menú contextual de eliminación de componente.
	 * 
	 * @return Menú contextual de eliminación de componente.
	 * @throws ComponentEditorException
	 */
	public Menu getMenuEliminar() throws ComponentEditorException{
		try{
			Image img = SWTResourceManager.getImage(GUIEditor.class, "images/icons/eliminar.png"); //$NON-NLS-1$
			Menu menu = new Menu(this.grid);
			MenuItem item = new MenuItem(menu, SWT.PUSH);
			item.setText("Eliminar");
			item.setImage(img);
	
			SelectionListener mouseListener = new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent event) {}
	
				public void widgetSelected(SelectionEvent event) {
					try{
						manager.getCentralManager().eliminarComponente(Operator.this);
					}catch(Exception ex){}
				}
			};
	
			item.addSelectionListener(mouseListener);
	
			return menu;
		}catch(Exception ex){
			throw new ComponentEditorException("Error creando el menú contextual de eliminación de componentes.",ex); //$NON-NLS-1$
		}
	}

	/**
	 * Devuelve el nombre del componente.
	 * 
	 * @return Nombre del tipo heredado del componente.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Devuelve la posición central del componente.
	 * 
	 * @return Posición central del componente.
	 * @throws ComponentEditorException
	 */
	public abstract int getPosicionCentral()throws ComponentEditorException;

	/**
	 * Devuelve el nombre del tipo heredado del componente.
	 * 
	 * @return Nombre del tipo heredado del componente.
	 */
	public String getTipoComponente() {
		return this.tipoComponente;
	}

	/**
	 * Selecciona el componente.
	 * 
	 * @throws ComponentEditorException
	 */
	public void seleccionar() throws ComponentEditorException{
		try{
			this.estaSeleccionado = true;
			this.manager.getActionManager().setEstadoBtnEliminar(true);
			this.cmpTodo.setBackground(this.getColorFondoSeleccionado());
			this.setBackground(this.getColorFondoSeleccionado());
		}catch(Exception ex){
			throw new ComponentEditorException("Error seleccionando un componente.",ex); //$NON-NLS-1$
		}
	}
	
	/**
	 * Pack del operador.
	 */
	@Override
	public void pack(){
		super.pack();
		
		if(this.getCuadriculaPadre().esCuadriculaPadre()){
			this.getCuadriculaPadre().getParent().pack();
		}
	}
	
	/**
	 * Establece la localización del componente, dependiendo del parámetro de entrada.
	 * 
	 * @param altura Entero que indica la altura a la que se debe colocar el componente.
	 * @throws ComponentEditorException
	 */
	public void setAltura(int altura) throws ComponentEditorException{
		try{
			this.cmpTodo.setLocation(0, altura);
		}catch(Exception ex){
			throw new ComponentEditorException("Error aplicando una altura de " +altura+ " px a un componente.",ex); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * Establece el foco en el componente y el cursor al final del mismo.
	 * 
	 * @throws ComponentEditorException
	 */
	public abstract void setFocoFinal() throws ComponentEditorException;

	/**
	 * Establece el foco en el componente y el cursor al principio del mismo.
	 */
	public abstract void setFocoInicio() throws ComponentEditorException;

	/**
	 * Establece el menú de "eliminar componente" en el composite cmpTodo.
	 * 
	 * @throws ComponentEditorException
	 */
	protected void setMenuEliminar() throws ComponentEditorException{
		this.setMenu(this.getMenuEliminar());
		this.cmpTodo.setMenu(this.getMenuEliminar());
	}

	/**
	 * Establece la posición central del componente.
	 * 
	 * @param posicion Posición central del componente.
	 * @throws ComponentEditorException
	 */
	public void setPosicionCentral(int posicion)  throws ComponentEditorException{
		try{
			this.posicionCentral = posicion;
			if (this.posicionCentral != this.getCuadriculaPadre().getPosicionCentral()) {
				this.getCuadriculaPadre().setPosicionCentral();
			}
		}catch(Exception ex){
			 throw new ComponentEditorException("Error aplicando una posición central a un componente.",ex); //$NON-NLS-1$
		}
	}

}
