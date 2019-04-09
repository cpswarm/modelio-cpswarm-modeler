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
 * File: HistoryManager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com, Nuria García - nuria.garcia87@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import java.util.ArrayList;
import java.util.List;

import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.MathEditor.MathEditor;
import es.addlink.tutormates.equationEditor.Operators.GridExpression;
import es.addlink.tutormates.equationEditor.Translation.ExportMathEditor;

/**
 * Clase que almacena las modificaciones producidas durante la edición de las expresiones, para dotar al editor de las funciones Deshacer y Rehacer.
 * 
 * @author Nuria García, Ignacio Celaya Sesma
 */
public class HistoryManager {

	private final int max = 50;

	/**
	 * Capa sobre la que actúa el guardado del historial.
	 */
	private GridExpression cap;

	/**
	 * Determina si hay que guardar estado o no.
	 */
	private boolean guardar;

	/**
	 * Lista que guarda los cambios en el editor.
	 */
	private List<MathEditor> listaHistorial;

	/**
	 * Para saber si deben estar activados los botones deshacer/rehacer.
	 */
	private boolean on_redo;

	/**
	 * Para saber si deben estar activados los botones deshacer/rehacer.
	 */
	private boolean on_undo;

	/**
	 * Entero que señalará en la lista anterior la posición del estado actual.
	 */
	private int posHistorial;
private Manager manager;
	/**
	 * Constructor - privado (singleton)
	 * @throws ManagerEditorException
	 */
	public HistoryManager(Manager manager){
		try{
			this.manager = manager;
			this.listaHistorial = new ArrayList<MathEditor>();
			this.posHistorial = -1;
			this.cap = null;
			
			if(this.manager.getTutorMatesEditor().getCorrectExpresion())
				this.guardar = true;
			else this.guardar = false;
			this.on_undo = false;
			this.on_redo = false;
			
		}catch(Exception ex){
			//ex.printStackTrace();
		}
	}

	/**
	 * Rehace acciones en el editor.
	 * 
	 * @return MathEditor con el nuevo objeto a insertar.
	 * @throws ManagerEditorException
	 */
	public MathEditor avanzar() throws ManagerEditorException {
		try{
			if (posHistorial < listaHistorial.size() - 1) { // Si se puede rehacer
				posHistorial++; // Incrementamos la posición en la lista
				on_undo = true;
				if (posHistorial == listaHistorial.size() - 1) { // Si alcanzamos el límite
					on_redo = false; // Desactivamos el botón rehacer
				}
				return listaHistorial.get(posHistorial); // Cogemos el MathEditor de la lista
			}
			return listaHistorial.get(listaHistorial.size() - 1);
			
		}catch(Exception ex){
			throw new ManagerEditorException("Error al avanzar una acción en la lista del historial.",ex);
		}
	}

	/**
	 * Elimina el historial almacenado.
	 */
	public void cleanHistory() {
		listaHistorial.clear();
	}

	/**
	 * Devuelve true si está activado el guardado automático, false en caso contrario.
	 * 
	 * @return True si está activado el guardado automático, false en caso contrario.
	 */
	public boolean getGuardar() {
		return guardar;
	}

	/**
	 * Devuelve true si el botón de rehacer debe estar activado, false en caso contrario.
	 * 
	 * @return True si el botón de rehacer debe estar activado, false en caso contrario.
	 */
	public boolean getRedo() {
		if(this.manager.getTutorMatesEditor().getCorrectExpresion())
			return on_redo;
		else
			return false;
	}

	/**
	 * Devuelve true si el botón de deshacer debe estar activado, false en caso contrario.
	 * 
	 * @return True si el botón de deshacer debe estar activado, false en caso contrario.
	 */
	public boolean getUndo() {
		if(this.manager.getTutorMatesEditor().getCorrectExpresion())
			return on_undo;
		else
			return false;
	}

	/**
	 * Almacena el historial de cambios llevados a cabo en el editor.
	 * 
	 * @throws ManagerEditorException
	 */
	public void guardarHistorial(boolean borrado) {
		try {			

			if (posHistorial == -1) {
				cleanHistory();
			}
			for (int i = posHistorial + 1; i < listaHistorial.size(); i++) {
				listaHistorial.remove(i);
			}
			if (listaHistorial.size() == max) {
				listaHistorial.remove(0);
			}
			
			//si borrado es true, guardamos un null en Historial.
			if(borrado){
				
				boolean eq = false;
				MathEditor mo = null;
				if (!listaHistorial.isEmpty()) {
					if(listaHistorial.get(listaHistorial.size() - 1) == null)
						eq = true;
				}
				if (!eq) {
					listaHistorial.add(mo); // Almacenamos el MathEditor en la lista
					posHistorial = listaHistorial.size() - 1; // Actualizamos el valor del atributo "posHistorial"
				}
			}else{
				// Creamos el MathEditor
				MathEditor mo;
				ExportMathEditor out = new ExportMathEditor(this.manager);
				mo = out.getMathEditorCargarHistorial(cap);
				
				boolean eq = false;
				if (!listaHistorial.isEmpty()) {
					eq = MathEditor.equals(mo, listaHistorial.get(listaHistorial.size() - 1));
					
				}
				
				if(mo != null){
					if(this.manager.getTutorMatesEditor().getCorrectExpresion())
						this.manager.getActionsToolBar().getExportToolItem().setEnabled(true);
				}else{
					if(this.manager.getTutorMatesEditor().getCorrectExpresion())
						this.manager.getActionsToolBar().getExportToolItem().setEnabled(false);
				}
				
				if (mo != null && !eq) {
					listaHistorial.add(mo); // Almacenamos el MathEditor en la lista
					posHistorial = listaHistorial.size() - 1; // Actualizamos el valor del atributo "posHistorial"
				}
			}
			
		}catch(EditorException ex){
			//ex.showExtendedError();
		}catch(Exception ex){
			//ExportTranslationEditorException e =  new ExportTranslationEditorException("Error desconocido al guardar el historial.",ex);
			//e.showExtendedError();
		}
		
		on_undo = true;
		on_redo = false;
		
	}

	/**
	 * Deshace acciones en el editor.
	 * 
	 * @return MathEditor con el nuevo objeto a insertar.
	 * @throws ManagerEditorException
	 */
	public MathEditor retroceder() throws ManagerEditorException{
		try{
			if (posHistorial > -1) { // En caso de que se pueda deshacer. (Valores inferiores a cero: Editor en blanco)
				posHistorial--; // Decrementamos la posición en la lista
				on_redo = true;
				if (posHistorial == -1) {
					on_undo = false;
				}
				else {
					return listaHistorial.get(posHistorial); // Cogemos el MathEditor de la lista
				}
			}
			return null;
			
		}catch(Exception ex){
			throw new ManagerEditorException("Error al avanzar una acción en la lista del historial.",ex);
		}
	}

	/**
	 * Establece la cuadrícula sobre la que se quiere llevar a cabo las acciones.
	 * 
	 * @param c Cuadrícula sobre la que se quiere llevar a cabo las acciones.
	 */
	public void setCuadricula(GridExpression c) {
		cap = c;
	}

	/**
	 * Activa o desactiva el guardado automático.
	 * 
	 * @param g True para activar el guardado automático, false en caso contrario.
	 */
	public void setGuardar(boolean g) {
		guardar = g;
	}

}
