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
 * File: CentralManager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import org.eclipse.swt.widgets.Control;

import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Formats.FormatTextBox;
import es.addlink.tutormates.equationEditor.Operators.EulerNumber;
import es.addlink.tutormates.equationEditor.Operators.Exponent;
import es.addlink.tutormates.equationEditor.Operators.Fraction;
import es.addlink.tutormates.equationEditor.Operators.GeometricPoint;
import es.addlink.tutormates.equationEditor.Operators.GridExpression;
import es.addlink.tutormates.equationEditor.Operators.Infinity;
import es.addlink.tutormates.equationEditor.Operators.MixedRepeatingDecimal;
import es.addlink.tutormates.equationEditor.Operators.NthRoot;
import es.addlink.tutormates.equationEditor.Operators.Operator;
import es.addlink.tutormates.equationEditor.Operators.Pi;
import es.addlink.tutormates.equationEditor.Operators.PureRepeatingDecimal;
import es.addlink.tutormates.equationEditor.Operators.SimpleOperator;
import es.addlink.tutormates.equationEditor.Operators.SquareRoot;

/**
 * Esta clase es la encargada de controlar la insercción, colocación y eliminación de componentes. 
 * Es la intermediaria entre un componente y una cuadrícula.
 * 
 * @author Ignacio Celaya Sesma
 */
public class CentralManager {

	private Manager manager;
	
	public CentralManager(Manager manager){
		this.manager = manager;
	}
	
	/**
	 * Coloca el componente en la posición del cursor del Texto activo.
	 * 
	 * @param componente Componente que se desea colocar.
	 * @throws ComponentEditorException
	 */
	public void colocarComponente(Operator componente) throws ManagerEditorException{
		try{
			Boolean ultimaPosicion = false;
			GridExpression cuadricula = componente.getCuadriculaPadre();
			SimpleOperator textoActual = this.manager.getStateManager().getTextoActivo();
			int posicionCursor = this.manager.getStateManager().getPosicionCursor();
			cuadricula.aniadirColumna();
			Control[] children = cuadricula.getChildren();

			if (textoActual != null) {
	
				int posicionGrid = 0, i = 0;
	
				// ¿En qué columna del grid se encuentra el cursor?
				while ((i < children.length) && (posicionGrid == 0)) {
					if (textoActual == children[i])
						posicionGrid = i;
					i++;
				}
	
				// detecta las diferentes opciones para insertar un componente
				if (textoActual != null) {
	
					// primera posicion
					if (posicionCursor == 0) {
						componente.moveAbove(children[posicionGrid]);
						cuadricula.layout(new Control[] { componente });
					}
	
					// última posición
					else if (posicionCursor == textoActual.getNumCaracteres()) {
						if(posicionGrid != (cuadricula.getNumColumnas())-2){
							componente.moveAbove(children[posicionGrid + 1]);
							cuadricula.layout(new Control[] { componente });							
						}else
							ultimaPosicion = true;
					}
	
					// por el medio
					else {
						int posicionUltimoCaracter = textoActual.getNumCaracteres();
						String textoIzq = textoActual.getTexto().substring(0, posicionCursor);
						String textoDer = textoActual.getTexto().substring(posicionCursor, posicionUltimoCaracter);
	
						SimpleOperator tbeDer = new SimpleOperator(this.manager, cuadricula, FormatTextBox.TODO, "");
	
						children = cuadricula.getChildren();
	
						tbeDer.moveAbove(children[posicionGrid]);
						cuadricula.layout(new Control[] { tbeDer });
	
						children = cuadricula.getChildren();
	
						componente.moveAbove(children[posicionGrid + 1]);
						cuadricula.layout(new Control[] { componente });
	
						textoActual.setTexto(textoDer);
						tbeDer.setTexto(textoIzq);
	
						Operator comp = tbeDer.getCuadriculaPadre().getComponentePadre();
						if (comp != null) {
							tbeDer.setMenu(comp.getMenuEliminar());
							//tbeDer.addListener(SWT.MouseMove, comp.getListenerSeleccion());
							//tbeDer.addListener(SWT.MouseExit, comp.getListenerDeseleccion());
						}
	
						textoActual.pack();
						tbeDer.pack();
						this.manager.getStateManager().setTextoActivo(tbeDer);
					}
				}
			}
			else {
				/*
				 * Si no hay Texto activo, no se coloca el Componente. El siguiente código crea una columna al final de la Cuadricula y allí es donde se alojará el nuevo Componente: int numCols = children.length; cuadricula.moveAbove(children[numCols - 1]);
				 */
			}
			
			if(componente.getTipoComponente() == Category.WithoutEntriesType && ultimaPosicion){
				insertarTextoAlFinal(cuadricula);
			}
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error mientras se colocaba un componente en su posición correcta.",ex);
		}
	}

	/**
	 * Elimina el componente pasado por parámetro.
	 * 
	 * @param componente Componente que se quiere eliminar.
	 * @throws ComponentEditorException
	 */
	public void eliminarComponente(Operator componente) throws ManagerEditorException{
		try{
			GridExpression cuadricula = componente.getCuadriculaPadre();
	
			// ¿En qué columna se encuentra el componente a eliminar?
			Control[] children = cuadricula.getChildren();
			Operator c;
			int posicion = 0;
			boolean encontrado = false;
			while ((!encontrado) && (posicion < children.length)) {
				c = (Operator) children[posicion];
				if (c == componente)
					encontrado = true;
				else
					posicion++;
			}
			
			if(componente != null){
				componente.dispose();
				cuadricula.eliminarColumna();
			}
	
			if (cuadricula.getNumColumnas() == 0)
				insertarTextoInicial(cuadricula);
			else {
	
				if (posicion != 0) {
					children = cuadricula.getChildren();
					c = (Operator) children[posicion - 1];
	
					if (c.getNombre() == "texto") {
						SimpleOperator tbe = (SimpleOperator) c;
						int longTexto = tbe.getNumCaracteres();
						this.manager.getStateManager().setTextoActivo(tbe);
						tbe.setFoco();
						tbe.setPosicionCursor(longTexto);
						this.manager.getStateManager().setPosicionCursor(longTexto);
	
						// Si hay dos componentes de tipo Texto de forma consecutiva, éstos se unen en uno.
						if (children.length > posicion) {
							Operator c1 = (Operator) children[posicion - 1];
							Operator c2 = (Operator) children[posicion];
							if ((c1.getNombre() == "texto") && (c2.getNombre() == "texto")) {
								unirTextos((SimpleOperator) c1, (SimpleOperator) c2);
							}
						}
					}
				}
			}
			this.manager.getStateManager().setHaySeleccion(false);
			cuadricula.pack();
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error en la eliminación de un componente concreto.",ex);
		}
	}

	/**
	 * Elimina los componentes alojados en la cuadrícula que estén seleccionados.
	 * 
	 * @param cuadricula Cuadricula en la que se desea eliminar los componentes seleccionados.
	 * @throws ComponentEditorException
	 */
	public void eliminarSeleccion(GridExpression cuadricula)throws ManagerEditorException {
		try{
			int i = 0;
			Control[] children = cuadricula.getChildren();
			this.manager.getHistoryManager().setGuardar(false);
			/*
			 * Primero elimino los componentes seleccionados que sean de tipo texto.
			 * La razón es la siguiente:
			 * 
			 * En esta situación: [texto(1)][raiz][texto(2)][fracción][texto(3)]
			 * 
			 * Si eliminase [raiz], [texto(1)] y [texto(2)] se juntarian en un solo texto.
			 * Si primero se eliminan los textos, no sucederá lo anterior.
			 */
			
			while (i < children.length) {
				if (((Operator) children[i]).estaSeleccionado() && ((Operator) children[i]).getNombre() == "texto"){
					eliminarComponente((Operator) children[i]);
				}
				i++;
			}
			
			children = cuadricula.getChildren();
			i = 0;	
				
			while (i < children.length) {
				if (((Operator) children[i]).estaSeleccionado()){
					eliminarComponente((Operator) children[i]);
				}else
					((Operator) children[i]).eliminaComponentesInternosSeleccionados();
				
				i++;
			}
			
			if(cuadricula.getNumColumnas() == 0){
				insertarTextoInicial(cuadricula);
			}
			this.manager.getHistoryManager().guardarHistorial(true);
			this.manager.getHistoryManager().setGuardar(true);
			
			if(getCuadriculaPadre()!=null)
				setFocoUltimoComponente(getCuadriculaPadre());
			else
				setFocoUltimoComponente(cuadricula);
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error en la eliminación de componentes seleccionados.",ex);
		}
	}
	
	/**
	 * Devuelve la cuadrícula padre del Texto activo.
	 * @return La cuadrícula padre del Texto activo.
	 * @throws ComponentEditorException
	 */
	public GridExpression getCuadriculaPadre() throws ManagerEditorException{
		try{
			
			GridExpression cua = this.manager.getStateManager().getTextoActivo().getCuadriculaPadre();
			if(cua != null){
				while(cua != null && !cua.esCuadriculaPadre())
					cua = cua.getCuadriculaSuperior();
			}
			return cua;
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error en la obtención de la cuadrícula padre.",ex);
		}
	}

	/**
	 * Elimina los componentes de tipo Texto que se encuentren vacíos en esta cuadrícula.
	 * 
	 * @param cuadricula Cuadricula que se quiere dejar sin Textos vacíos.
	 * @throws ComponentEditorException
	 */
	public void eliminarTextosVacios(GridExpression cuadricula)throws ManagerEditorException {
		try{
			if(this.manager.getTutorMatesEditor().getCorrectExpresion()){
				Control[] children = cuadricula.getChildren();
				for (int i = 0; i < children.length; i++) {
					Operator comp = (Operator) children[i];
					if (comp.getTipoComponente().equals("texto")) {
						if (((SimpleOperator) comp).getTexto().length() == 0) {
							eliminarComponente(comp);
							this.manager.getStateManager().setTextoActivo(null);
							this.manager.getStateManager().setPosicionCursor(-1);
							if (i > 0)
								((Operator) children[i - 1]).setFocoFinal();
							else if (cuadricula.getNumColumnas() > i)
								((Operator) children[i + 1]).setFocoInicio();
						}
					}
					else
						comp.eliminaTextosVacios();
				}
			}
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error en la eliminación de Textos vacíos.",ex);
		}
	}

	/**
	 * Devuelve true si la cuadrícula no tiene columnas o tiene un solo Texto vacío.
	 * @return True si la cuadrícula no tiene columnas o tiene un solo Texto vacío.
	 */
	public boolean esCuadriculaVacia(GridExpression cuadricula){
		
		if(cuadricula.getNumColumnas() <= 0)
			return true;
		else{
			Operator c = (Operator)cuadricula.getChildren()[0];
			if(c.getNombre() == "texto"){
				SimpleOperator t = (SimpleOperator)c;
				if(t.getTexto().trim() == "" && cuadricula.getNumColumnas() == 1)
					return true;
				else
					return false;
					
			}else
				return false;
		}
	}
	
	public void insertarFuncion(String cadena){
		cadena = cadena.toLowerCase();
		
		try{
			
			if (this.manager.getStateManager().getTextoActivo() != null) {
	
				SimpleOperator textoActual = this.manager.getStateManager().getTextoActivo();

				int posicionCursor = this.manager.getStateManager().getPosicionCursor();
				int posicionUltimoCaracter = textoActual.getNumCaracteres();
				
				int entries = this.manager.getAllowedOperatorsAndFunctions().getEntriesFunction(cadena);
				String separators = "";
				for(int j=0;j<entries-1;j++)
					separators += ",";
				
				String textoIzq = textoActual.getTexto().substring(0, posicionCursor);
				String textoDer = textoActual.getTexto().substring(posicionCursor, posicionUltimoCaracter);
				String textoCompleto = textoIzq + cadena + "(" + separators + ")" + textoDer;
				textoActual.setTexto(textoCompleto);
				
				if (this.manager.getStateManager().getPosicionCursor() == -1) {
					this.manager.getStateManager().setPosicionCursor(posicionCursor + (cadena.toString().length()));
					textoActual.setPosicionCursor(this.manager.getStateManager().getPosicionCursor());
				}
				else {
					this.manager.getStateManager().setPosicionCursor(posicionCursor + 1 + cadena.toString().length());
					textoActual.setPosicionCursor(this.manager.getStateManager().getPosicionCursor());
				}

				textoActual.setFocus();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Inserta un carácter en el TextBox activo, respetando la posición del cursor.
	 * 
	 * @param cadena Carácter a insertar.
	 * @throws ComponentEditorException
	 */
	public void insertarCadena(String cadena) throws ManagerEditorException{
		cadena = cadena.toLowerCase();
		
		try{
			
			if (this.manager.getStateManager().getTextoActivo() != null) {
	
				SimpleOperator textoActual = this.manager.getStateManager().getTextoActivo();
				//FormatTextBox ftb = new FormatTextBox(this.manager);
				//if (ftb.esValido(cadena, textoActual.getFormato())) {
					int posicionCursor = this.manager.getStateManager().getPosicionCursor();
					int posicionUltimoCaracter = textoActual.getNumCaracteres();
					
					String textoIzq = textoActual.getTexto().substring(0, posicionCursor);
					String textoDer = textoActual.getTexto().substring(posicionCursor, posicionUltimoCaracter);
					String textoCompleto = textoIzq + cadena + textoDer;
					textoActual.setTexto(textoCompleto);
					
					if (this.manager.getStateManager().getPosicionCursor() == -1) {
						this.manager.getStateManager().setPosicionCursor(posicionCursor + (cadena.toString().length()));
						textoActual.setPosicionCursor(this.manager.getStateManager().getPosicionCursor());
					}
					else {
						this.manager.getStateManager().setPosicionCursor(posicionCursor + cadena.toString().length());
						textoActual.setPosicionCursor(this.manager.getStateManager().getPosicionCursor());
					}
	
					textoActual.setFocus();
				//}
			}
			
		}catch(Exception ex){
			throw new ManagerEditorException("Error en la insercción de una cadena en la posición correcta.",ex);
		}
	}

	/*
	 * posicionEnCuadricula contiene la posición del TextoActivo dentro de la cuadrícula donde
	 * va a ser insertado el nuevo componente, pero solo en caso de que el TextoActivo se 
	 * encuentre vacío. Si no está vacío, posicionEnCuadricula será igual a -1.
	 */
	
	/**
	 * posicionEnCuadricula contiene la posición del TextoActivo dentro de la cuadrícula donde
	 * va a ser insertado el nuevo componente, pero solo en caso de que el TextoActivo se 
	 * encuentre vacío. Si no está vacío, posicionEnCuadricula será igual a -1.
	 */
	public int getOperatorPosition(Operator ope, GridExpression grid){
		int i = 0;
		boolean encontrado=false;
		
		
		Control[] c = grid.getChildren();
		while(i<c.length && !encontrado){
			if(((Operator)c[i]) == ope)
				encontrado=true;
			else
				i++;
		}
		
		
		if(encontrado) return i;
		else return -1;
	}
	
	
	/**
	 * Es el encargado de insertar TODOS los componentes "no Texto".
	 * 
	 * @param nombreComponente Nombre del componente que se desea insertar.
	 * @throws ComponentEditorException
	 */
	public void insertarComponenteNoTexto(String nombreComponente)throws ManagerEditorException {
		try{
			
			if (permitirInserccionComponente()) {
				
				GridExpression cuadriculaDestino = this.manager.getStateManager().getTextoActivo().getCuadriculaPadre();
				
				/*
				 * posicionEnCuadricula contiene la posición del TextoActivo dentro de la cuadrícula donde
				 * va a ser insertado el nuevo componente, pero solo en caso de que el TextoActivo se 
				 * encuentre vacío. Si no está vacío, posicionEnCuadricula será igual a -1.
				 */
					if(this.manager.getStateManager().getTextoActivo().getNumCaracteres() == 0){
						Operator comp = this.manager.getStateManager().getTextoActivo();
						int i=0;
						boolean encontrado=false;
						Control[] c = cuadriculaDestino.getChildren();
						while(i<c.length && !encontrado){
							if(((Operator)c[i]) == comp)
								encontrado=true;
							else
								i++;
						}
					}
				/* ***************************************************************************************/

				if (nombreComponente.equals("squareRoot"))
					new SquareRoot(this.manager, cuadriculaDestino);
				else if (nombreComponente.equals("nthRoot"))
					new NthRoot(this.manager, cuadriculaDestino);
				else if (nombreComponente.equals("fraction"))
					new Fraction(this.manager, cuadriculaDestino);
				else if (nombreComponente.equals("mixedRepeatingDecimal"))
					new MixedRepeatingDecimal(this.manager, cuadriculaDestino);
				else if (nombreComponente.equals("pureRepeatingDecimal"))
					new PureRepeatingDecimal(this.manager, cuadriculaDestino);
				else if(nombreComponente.equals("exponent"))
					new Exponent(this.manager, cuadriculaDestino);
				else if(nombreComponente.equals("geometricPoint"))
					new GeometricPoint(this.manager, cuadriculaDestino);
				else if(nombreComponente.equals("pi")){
					Pi op = new Pi(this.manager, cuadriculaDestino);
					if(cuadriculaDestino.getChildren()[cuadriculaDestino.getChildren().length-1] == op)
						insertarTextoAlFinal(cuadriculaDestino);
				}
				else if(nombreComponente.equals("eulerNumber")){
					EulerNumber op = new EulerNumber(this.manager, cuadriculaDestino);
					if(cuadriculaDestino.getChildren()[cuadriculaDestino.getChildren().length-1] == op)
						insertarTextoAlFinal(cuadriculaDestino);
				}
				else if(nombreComponente.equals("infinity")){
					Infinity op = new Infinity(this.manager, cuadriculaDestino);
					if(cuadriculaDestino.getChildren()[cuadriculaDestino.getChildren().length-1] == op)
						insertarTextoAlFinal(cuadriculaDestino);
				}
				
				if(!cuadriculaDestino.isDisposed())
					cuadriculaDestino.pack();
			}
			if(nombreComponente.equals("ce"))
				vaciarCuadriculaPadre();
			
			if(this.manager.getStateManager().getTextoActivo() != null)
				this.manager.getStateManager().getTextoActivo().setFoco();
		
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error en la insercción de un componente diferente a Texto.",ex);
		}
	}

	/**
	 * Inserta un componente Texto al final del grid pasado por parámetro.
	 */
	public void insertarTextoAlFinal(GridExpression grid){
		int n = grid.getChildren().length-1;
		Control c[] = grid.getChildren();
		Operator ope = (Operator)c[n];
		if(ope.getTipoComponente() != Category.TextType){
			try {
				insertarTexto(grid,n+1);
			}
			catch (ManagerEditorException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	
		
	/**
	 * Inserta un componente Texto al final del grid pasado por parámetro.
	 */
	public void insertarTextoAlInicio(GridExpression grid){
		try {
			insertarTexto(grid,0);
		}
		catch (ManagerEditorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserta un componente de tipo Texto en el grid de esta cuadrícula.
	 * 
	 * @param posicion posición del grid donde se insertará el componente de tipo Texto. 0 <= posicion <= (longitud_grid-1).
	 * @throws ComponentEditorException
	 */
	public void insertarTexto(GridExpression cuadricula, int posicion)throws ManagerEditorException {
		try{
			SimpleOperator t = new SimpleOperator(this.manager, cuadricula, FormatTextBox.TODO, "");
			Control[] children = cuadricula.getChildren();
			if(posicion != (cuadricula.getNumColumnas() - 1)){
				t.moveAbove(children[posicion]);
				cuadricula.layout(new Control[] { t });
			}
			t.setAltura(cuadricula.getPosicionCentral());
			cuadricula.setPosicionCentral();
			cuadricula.pack();
			t.setFocus();
			this.manager.getHistoryManager().guardarHistorial(false);
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error en la insercción de Texto.",ex);
		}
	}

	/**
	 * Se encarga de insertar un Texto en la cuadrícula después de comprobar que la cuadrícula está vacía. Se encarga de insertar el Texto inicial que aparece al ejecutar la aplicación, o el Texto que aparece al borrar todo.
	 * 
	 * @param cuadricula Cuadricula donde se insertará el Texto.
	 * @throws ComponentEditorException
	 */
	public void insertarTextoInicial(GridExpression cuadricula)throws ManagerEditorException {
		try{
			cuadricula.vaciar();
			SimpleOperator t = new SimpleOperator(this.manager, cuadricula, FormatTextBox.TODO, "");
			cuadricula.pack();
			t.setFoco();
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error al insertar el Texto inicial.",ex);
		}
	}

	/**
	 * Devuelve true si existe un Texto activo y el Formato asignado a ese Texto activo, permite la insercción de componentes "no Texto", false en caso contrario.
	 * 
	 * @return True si está permitida la insercción de componentes no Texto, false en caso contrario.
	 * @throws ComponentEditorException
	 */
	private boolean permitirInserccionComponente() throws ManagerEditorException{
		try{
			boolean permitir = false;
	
			if (this.manager.getStateManager().getTextoActivo() != null && FormatTextBox.sePermitenComponentes(this.manager.getStateManager().getTextoActivo().getFormato()))
				permitir = true;
	
			return permitir;
			
		}catch(Exception ex){
			throw new ManagerEditorException("Error en la decisión de permitir una insercción de componente.",ex);
		}
	}

	/**
	 * Establece el foco en el último componente de la cuadrícula pasada por parámetro.
	 * 
	 * @param cuadricula Cuadricula que se desea establecer el foco en su último componente.
	 * @throws ComponentEditorException
	 */
	public void setFocoUltimoComponente(GridExpression cuadricula)throws ManagerEditorException{
		try{
			if(cuadricula != null){
				if (cuadricula.getChildren().length > 0) 
					((Operator) cuadricula.getChildren()[cuadricula.getNumColumnas()-1]).setFocoFinal();
			}
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error al colocar el foco en el último componente.",ex);
		}
	}
	
	/**
	 * Establece el foco en el primer componente de la cuadrícula pasada por parámetro.
	 * 
	 * @param cuadricula Cuadricula que se desea establecer el foco en su primer componente.
	 * @throws ComponentEditorException
	 */
	public void setFocoPrimerComponente(GridExpression cuadricula) throws ManagerEditorException{
		try{
			if (cuadricula.getChildren().length > 0) 
				((Operator) cuadricula.getChildren()[0]).setFocoInicio();
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error al colocar el foco en el primer componente.",ex);
		}
	}

	/**
	 * Cuando se elimina un componente, si la consecuencia son dos textos consecutivos, los une en uno.
	 * 
	 * @param textoLeft Texto izquierdo.
	 * @param textoRight Texto derecho.
	 * @throws ComponentEditorException
	 */
	protected void unirTextos(SimpleOperator textoLeft, SimpleOperator textoRight) throws ManagerEditorException{
		try{
			String tbeL = textoLeft.getTexto();
			String tbeR = textoRight.getTexto();
			int longTotalL = textoLeft.getNumCaracteres();
	
			textoRight.dispose();
	
			GridExpression parent = (GridExpression) textoLeft.getParent();
			parent.eliminarColumna();
	
			textoLeft.setTexto(tbeL + tbeR);
			textoLeft.setPosicionCursor(longTotalL);
			this.manager.getStateManager().setPosicionCursor(longTotalL);
			this.manager.getStateManager().setTextoActivo(textoLeft);
	
			textoLeft.pack();
			
		}catch(Exception ex){
			throw new ManagerEditorException("Error al unir dos Textos.",ex);
		}
	}
	
	protected void vaciarCuadricula(GridExpression cuadricula) throws ManagerEditorException{
		try{
			/* Localización de la cuadrícula padre */
				boolean encontrada = false;
				while(!encontrada){
					if(cuadricula.esCuadriculaPadre())
						encontrada=true;
					else
						cuadricula = cuadricula.getCuadriculaSuperior();
				}
			/* *************************************/
			
			if(encontrada){
				cuadricula.vaciar();
				insertarTextoInicial(cuadricula);
			}
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error al vaciar el contenido de una cuadrícula.",ex);
		}		
	}
	
	/**
	 * Elimina todos los componentes de la cuadrícula padre, dejando el número de columnas a cero.
	 * @throws ComponentEditorException
	 */
	protected void vaciarCuadriculaPadre() throws ManagerEditorException{
		try{
			if (this.manager.getHistoryManager().getGuardar())
				this.manager.getHistoryManager().guardarHistorial(true);		
			
			if(this.manager.getStateManager().getTextoActivo() != null){
				
				/* Localización de la cuadrícula padre */
					GridExpression cuaAux= this.manager.getStateManager().getTextoActivo().getCuadriculaPadre();
					boolean encontrada = false;
					while(!encontrada){
						if(cuaAux.esCuadriculaPadre())
							encontrada=true;
						else
							cuaAux = cuaAux.getCuadriculaSuperior();
					}
				/* *************************************/
				
				if(encontrada){
					cuaAux.vaciar();
					insertarTextoInicial(cuaAux);
				}
			}
			
		}catch(EditorException ex){
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error al vaciar el contenido de una cuadrícula.",ex);
		}
	}
}
