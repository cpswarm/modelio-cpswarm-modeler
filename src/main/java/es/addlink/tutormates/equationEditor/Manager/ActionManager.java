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
 * File: ActionManager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolItem;
import org.jdom.Document;
import org.jdom.Element;

import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ImportTranslationEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.MathEditor.MathEditor;
import es.addlink.tutormates.equationEditor.Operators.GridExpression;
import es.addlink.tutormates.equationEditor.Translation.ExportMathEditor;
import es.addlink.tutormates.equationEditor.Translation.ImportMathEditor;
import es.addlink.tutormates.equationEditor.Translation.MathEditor2MathML;
import es.addlink.tutormates.equationEditor.Translation.MathML2MathEditor;
import es.addlink.tutormates.equationEditor.Utils.CustomToolTip;

/**
 * Gestiona el comportamiento de los ToolItems del ToolBar de acciones (deshacer,rehacer,eliminar,exportar,importar)
 * 
 * @author Nuria García, Ignacio Celaya Sesma
 */
public class ActionManager {

	public void setCmp(Composite cmp, Composite cmpInferior){
		this.cmpInferior = cmpInferior;
		this.cmp = cmp;
	}
	
	private static String incorrectExportExpressionTitle;
	private static String incorrectExportExpressionMessage;
	private static String emptyExportExpressionTitle;
	private static String emptyExportExpressionMessage;
	private static String incorrectImportExpressionTitle;
	private static String incorrectImportExpressionMessage;
	private static String emptyImportExpressionTitle;
	private static String emptyImportExpressionMessage;
	private Composite cmp;
	private Composite cmpInferior;
	/**
	 * Cuadrícula padre del editor.
	 */
	private GridExpression cuadriculaPadre;
	
	/**
	 * ToolItem del ToolBar de acciones que al pulsar se encarga de deshacer una acción.
	 */
	private ToolItem toolItemUndo;

	/**
	 * ToolItem del panel de acciones que al pulsar se encarga de eliminar la expresión seleccionada.
	 */
	//private ToolItem toolItemDelete;

	/**
	 * ToolItem del panel de acciones que al pulsar se encarga de rehacer una acción.
	 */
	private ToolItem toolItemRedo;

	/**
	 * Mensaje erróneo mostrado por consola cuando el editor recibe un objeto nulo.
	 */
	private final String msgReceiveError = "# Editor [receive]: Null.";

	/**
	 * Mensaje satisfactorio mostrado por consola cuando el editor recibe un objeto MathML diferente a nulo.
	 */
	private final String msgReceiveOK = "# Editor [receive]: ";
	
	/**
	 * Mensaje satisfactorio mostrado por consola cuando el editor recibe un objeto MathML diferente a nulo.
	 */
	private final String msgMathMLTest = "# Editor [mathml test]: ";

	/**
	 * Mensaje erróneo mostrado por consola cuando el editor no envía nada por haber detectado una expresión no válida.
	 */
	private final String msgSendError = "# Editor [send]: Expresión no válida. No envía nada.";

	/**
	 * Mensaje satisfactorio mostrado por consola cuando el editor envía un objeto MathML diferente a nulo.
	 */
	private final String msgSendOK = "# Editor [send]: ";

	/**
	 * Si es true, cada vez que el editor envíe una expresión válida se eliminará el contenido, false para el caso contrario.
	 */
	private final boolean removeExpression = true;
	
	private Manager manager;

	/**
	 * Constructor que carga las imágenes en los correspondientes atributos
	 */
	@SuppressWarnings("unchecked")
	public ActionManager(Manager manager) {
		this.manager = manager;
		
		try{
			
			/*Language - incorrect expression**********************************************/
				Document languageDoc = this.manager.getLanguageManager().getDocument();
				if(languageDoc != null){
					Element languageRoot = languageDoc.getRootElement();
					List<String> errorsLanguage = languageRoot.getChildren("errors");
					
					Iterator ite = errorsLanguage.iterator();
					if(ite.hasNext()){
						Element el = (Element)ite.next();
						List<String> guiErrorsLanguage = el.getChildren();
						Iterator ite2 = guiErrorsLanguage.iterator();
						
						while(ite2.hasNext()){
							Element el2 = (Element)ite2.next();
	
							if(el2.getName().equals("gui")){
								Element inc = el2.getChild("incorrectExportExpression");
								incorrectExportExpressionTitle = inc.getChildText("title");
								incorrectExportExpressionMessage = inc.getChildText("message");
								
								Element inc4 = el2.getChild("emptyExportExpression");
								emptyExportExpressionTitle = inc4.getChildText("title");
								emptyExportExpressionMessage = inc4.getChildText("message");
								
								Element inc2 = el2.getChild("incorrectImportExpression");
								incorrectImportExpressionTitle = inc2.getChildText("title");
								incorrectImportExpressionMessage = inc2.getChildText("message");
								
								Element inc3 = el2.getChild("emptyImportExpression");
								emptyImportExpressionTitle = inc3.getChildText("title");
								emptyImportExpressionMessage = inc3.getChildText("message");
							}
						}
						
					}
				}
		/* *****************************************************************************/
			
			
		}catch(Exception ex){
			//ex.printStackTrace();
		}
		/* *************************************************************************************************************************/
	}

	/**
	 * Elimina los componentes seleccionados
	 */
	public void actionDelete()throws EditorException{
		if(!this.manager.getCentralManager().esCuadriculaVacia(this.cuadriculaPadre)){
			if(!this.cuadriculaPadre.haySeleccion())
				this.manager.getCentralManager().vaciarCuadriculaPadre();
			else
				this.manager.getCentralManager().eliminarSeleccion(this.manager.getCentralManager().getCuadriculaPadre());
			//this.setEstadoBtnEliminar(false);
		}
	}
	
	public boolean isMathMLCorrect(String mathml){
		try {
			System.out.println(this.msgMathMLTest + mathml.trim());
			MathML2MathEditor mathML2MathEditor = new MathML2MathEditor(mathml,this.manager);
			MathEditor mo = mathML2MathEditor.getMathEditor();
			return MathEditor.isCorrect(mo);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
	}
	
	private String exportCorrectExpressionInMathML(MathEditor me)throws ManagerEditorException{
		try{
				MathEditor2MathML mml = new MathEditor2MathML(this.manager,me);
				String mathML = mml.getMathMLString();
				mathML = mathML.replace("~", "").trim();
				System.out.println(this.msgSendOK + mathML);
				
				if (this.removeExpression)
					this.manager.getCentralManager().vaciarCuadriculaPadre();
				
				return mathML;
		}catch(EditorException ex){
			//ex.printStackTrace();
			//ex.showExtendedError();
			this.manager.getCentralManager().vaciarCuadricula(this.cuadriculaPadre);
			return null;
		}catch(Exception ex){
			
			//ex.printStackTrace();
			//ExportTranslationEditorException e =  new ExportTranslationEditorException("Error desconocido al exportar MathML.",ex);
			//e.showExtendedError();
			return null;
		}
	}
	
	/**
	 * Envía o exporta una expresión en formato MathML.
	 */
	public String exportMathML()throws ManagerEditorException {
		try{	
			
			if(!this.manager.getCentralManager().esCuadriculaVacia(this.cuadriculaPadre)){
				
				ExportMathEditor out = new ExportMathEditor(this.manager);
				MathEditor me = out.getMathEditorCargar(this.cuadriculaPadre);
				
				if(!this.manager.getTutorMatesEditor().getCorrectExpresion()){
					return exportCorrectExpressionInMathML(me);
				}else{
					
					if (me == null){
						
						/* Se muestra el ToolTip con el error */
							CustomToolTip tip = new CustomToolTip(this.cmp.getShell(), SWT.BALLOON | SWT.ICON_ERROR, 3000);
							tip.setText(incorrectExportExpressionTitle);
							tip.setMessage(incorrectExportExpressionMessage);
							Point p2 = this.cmp.toDisplay(this.cmpInferior.getLocation());
							p2.x = p2.x + 30;
							p2.y = p2.y + 40;
							tip.setLocation(p2);
							tip.setVisible(true);
							tip.activeAutoHide();
						/* ************************************/
						
						System.out.println(this.msgSendError);
					}else
						return exportCorrectExpressionInMathML(me);
				}
				this.manager.getCentralManager().insertarTextoInicial(this.cuadriculaPadre);
				if (this.manager.getStateManager().getTextoActivo() != null)
					this.manager.getStateManager().getTextoActivo().setFoco();
			}else{
				
				/* Se muestra el ToolTip con el error */
					CustomToolTip tip = new CustomToolTip(this.cmp.getShell(), SWT.BALLOON | SWT.ICON_ERROR, 3000);
					tip.setText(emptyExportExpressionTitle);
					tip.setMessage(emptyExportExpressionMessage);
					Point p2 = this.cmp.toDisplay(this.cmpInferior.getLocation());
					p2.x = p2.x + 30;
					p2.y = p2.y + 40;
					tip.setLocation(p2);
					tip.setVisible(true);
					tip.activeAutoHide();
				/* ************************************/
					
			}
			
		}catch(EditorException ex){
			//ex.printStackTrace();
			//ex.showExtendedError();
			//this.manager.getCentralManager().vaciarCuadricula(this.cuadriculaPadre);
			return null;
		}catch(Exception ex){
			
			//ex.printStackTrace();
			//ExportTranslationEditorException e =  new ExportTranslationEditorException("Error desconocido al exportar MathML.",ex);
			//e.showExtendedError();
			return null;
		}
		return null;
	}
	
	public void importMathML(String mathml) throws ImportTranslationEditorException{
		try{
			//Poner el panel de edición invisible sirve para que durante el proceso de creación de la expresión, el usuario no vea como se va formando.
			setInvisibleParent();
			//mathml = "<math><mrow><mrow><mfenced open=\"\" close=\"\" separators=\";\"><mrow><mi><mn>5</mn></mi></mrow><mrow><msqrt><mrow><mn>4</mn></mrow></msqrt></mrow></mfenced></mrow></mrow></math>";
			if(isMathMLCorrect(mathml)){
				//System.out.println("RECIBIDO: " + mathml);
				this.manager.getHistoryManager().setGuardar(false);
				
				String mathmlModificado = mathml;
				
				if (mathml != null) {	
					
					MathML2MathEditor mathML2MathEditor = new MathML2MathEditor(mathmlModificado,this.manager);
					MathEditor mo = mathML2MathEditor.getMathEditor();
					ImportMathEditor i = new ImportMathEditor(this.manager);
					
					if(mo != null){
						
						System.out.println(this.msgReceiveOK + mathml);
						this.cuadriculaPadre.vaciar();
						i.loadMathEditor(mo, this.cuadriculaPadre);
						this.manager.getCentralManager().setFocoUltimoComponente(this.cuadriculaPadre);
						this.manager.getHistoryManager().guardarHistorial(false);
					}
				}
				else
					System.out.println(this.msgReceiveError);
		
				this.manager.getHistoryManager().setGuardar(true);
			}else{
				String title = "";
				String body = "";
				if(mathml == null || mathml.trim().equalsIgnoreCase("")){
					title = emptyImportExpressionTitle;
					body = emptyImportExpressionMessage;
				}else{
					title = incorrectImportExpressionTitle;
					body = incorrectImportExpressionMessage;
				}
				
				/* Se muestra el ToolTip con el error */
					CustomToolTip tip = new CustomToolTip(this.cmp.getShell(), SWT.BALLOON | SWT.ICON_ERROR, 3000);
					tip.setText(title);
					tip.setMessage(body);
					Point p2 = this.cmp.toDisplay(this.cmpInferior.getLocation());
					p2.x = p2.x + 30;
					p2.y = p2.y + 40;
					tip.setLocation(p2);
					tip.setVisible(true);
					tip.activeAutoHide();
				/* ************************************/
			}
			setVisibleParent();
		}catch(EditorException ex){
			//ex.printStackTrace();
			try{
				vaciarCuadriculaPadre();
				this.manager.getCentralManager().insertarTextoInicial(this.cuadriculaPadre);
				ex.showExtendedError();
				//ex.printStackTrace();
			}catch(EditorException ex2){
				ex.printStackTrace();
				//throw new ImportTranslationEditorException(ex2);
			}
					
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ImportTranslationEditorException("Error desconocido al importar MathML.",ex);
		}
	}
	
	/**
	 * Importa una expresión.
	 * @param mathml MathML en formato String que se desea importar en el editor.
	 * @throws ManagerEditorException
	 */
	public void actionImportMathML(String mathml) throws ManagerEditorException{
		try {
			importMathML(mathml);
			
		}catch(EditorException ex){
			//ex.showExtendedError();			
		}catch(Exception ex){
			//ImportTranslationEditorException e = new ImportTranslationEditorException("Error desconocido al importar MathML.",ex);
			//e.showExtendedError();
		}
	}

	/**
	 * Rehace una acción
	 */
	public void actionRedo()throws ManagerEditorException {
		try{
			setInvisibleParent();
			this.manager.getHistoryManager().setGuardar(false);
			MathEditor mo = this.manager.getHistoryManager().avanzar();
			ImportMathEditor i = new ImportMathEditor(this.manager);
			this.cuadriculaPadre.vaciar();
			if (mo != null){
				i.loadMathEditor(mo, this.cuadriculaPadre);
				this.manager.getCentralManager().setFocoUltimoComponente(this.cuadriculaPadre);
			}else {
				i.insertarTexto("", this.cuadriculaPadre);
				this.cuadriculaPadre.pack();
			}
			cambiarEstado();
			this.manager.getHistoryManager().setGuardar(true);
			setVisibleParent();
		}catch(EditorException ex){
			try{
				vaciarCuadriculaPadre();
			}catch(EditorException ex2){throw new ManagerEditorException(ex2);}
			
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error desconocido al rehacer una acción.",ex);
		}
	}

	private void setInvisibleParent(){
		try {
			this.manager.getCentralManager().getCuadriculaPadre().getParent().setVisible(false);
		}
		catch (ManagerEditorException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	private void setVisibleParent(){
		try {
			this.manager.getCentralManager().getCuadriculaPadre().getParent().setVisible(true);
			this.manager.getStateManager().getTextoActivo().setFoco();
		}
		catch (ManagerEditorException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	/**
	 * Deshace una acción
	 */
	public void actionUndo() throws ManagerEditorException{
		try{
			setInvisibleParent();
			this.manager.getHistoryManager().setGuardar(false);
			MathEditor mo = this.manager.getHistoryManager().retroceder();
			ImportMathEditor i = new ImportMathEditor(this.manager);
			this.cuadriculaPadre.vaciar();
			if (mo != null){
				i.loadMathEditor(mo, this.cuadriculaPadre);
				this.manager.getCentralManager().setFocoUltimoComponente(this.cuadriculaPadre);
			}else {
				i.insertarTexto("", this.cuadriculaPadre);
				this.cuadriculaPadre.pack();
			}
			cambiarEstado();
			this.manager.getHistoryManager().setGuardar(true);
			setVisibleParent();
		}catch(EditorException ex){
			try{
				vaciarCuadriculaPadre();
			}catch(EditorException ex2){throw new ManagerEditorException(ex2);}
			
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error desconocido al deshacer una acción.",ex);
		}
	}

	private void vaciarCuadriculaPadre()throws ManagerEditorException{
		try{
			this.cuadriculaPadre.vaciar();
			this.manager.getCentralManager().insertarTextoInicial(this.cuadriculaPadre);
			
		}catch(EditorException ex){			
			throw new ManagerEditorException(ex);
		}catch(Exception ex){
			throw new ManagerEditorException("Error al vaciar la cuadrícula padre.",ex);
		}
	}
	
	/**
	 * Activa o desactiva los botones deshacer/rehacer según su estado
	 */
	public void cambiarEstado() throws ManagerEditorException{
		//try{
			this.toolItemUndo.setEnabled(this.manager.getHistoryManager().getUndo());
			this.toolItemRedo.setEnabled(this.manager.getHistoryManager().getRedo());
		//}catch(EditorException ex){
			//throw new ManagerEditorException(ex);
		//}
	}

	/**
	 * Establece la cuadrícula sobre la que se quiere realizar las acciones.
	 * 
	 * @param cuadriculaPadre Cuadrícula sobre la que se quiere realizar las acciones.
	 */
	public void setCuadriculaPadre(GridExpression cuadriculaPadre) {
		this.cuadriculaPadre = cuadriculaPadre;
	}

	/**
	 * Habilita o deshabilita el botón de eliminar
	 * 
	 * @param habilitado True si existe algún componente seleccionado, false en caso contrario
	 */
	public void setEstadoBtnEliminar(boolean habilitado) {
		//this.toolItemDelete.setEnabled(habilitado);
	}

	/**
	 * Obtiene los ToolItems de los botones de acción (deshacer,rehacer,eliminar,cargar y editar) para gestionar sus Listeners.
	 * 
	 * @param toolItemUndo ToolItem del ToolBar de acciones que al pulsar se encarga de deshacer una acción.
	 * @param toolItemRedo ToolItem del ToolBar de acciones que al pulsar se encarga de rehacer una acción.
	 * @param toolItemDelete ToolItem del panel de acciones que al pulsar se encarga de eliminar la expresión seleccionada.
	 * @param toolItemExport ToolItem del panel de acciones que al pulsar se encarga de exportar la expresión existente en el editor.
	 * @param toolItemImport ToolItem del panel de acciones que al pulsar se encarga de importar una expresión.
	 */
	public void setItems(ToolItem toolItemUndo, ToolItem toolItemRedo, ToolItem toolItemDelete, ToolItem toolItemExport, ToolItem toolItemImport) {
		this.toolItemUndo = toolItemUndo;
		this.toolItemRedo = toolItemRedo;
		//this.toolItemDelete = toolItemDelete;
	}
}
