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
 * File: ActionToolBarDisplayManager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.jdom.Document;
import org.jdom.Element;

import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;
import es.addlink.tutormates.equationEditor.XMLFiles.LoadXML;

/**
 * Crea el ToolBar de acciones con sus ToolItems (deshacer,rehacer,eliminar,exportar,importar).
 * 
 * @author Ignacio Celaya Sesma
 */
public class ActionToolBarDisplayManager {

	/**
	 * 
	 */
	private GridLayout grid;
	
	/**
	 * 
	 */
	private ToolItem exportToolItem;
	
	/**
	 * 
	 */
	private ToolItem importToolItem;
	
	/**
	 * 
	 * @return
	 */
	public ToolItem getExportToolItem() {
		return exportToolItem;
	}
	
	/**
	 * 
	 * @return
	 */
	public ToolItem getImportToolItem() {
		return importToolItem;
	}	
	
	
	
	/**
	 * Composite donde está situado el ToolBar.
	 */
	private Composite parent;
	private Manager manager;
	/**
	 * Constructor.
	 */
	public ActionToolBarDisplayManager(Manager manager){
		this.manager = manager;
		grid = new GridLayout(6,false);
		grid.horizontalSpacing = 0;
		grid.verticalSpacing = 0;
		grid.numColumns = 1;
		grid.marginTop = 13;
		grid.marginLeft = -5;
		grid.marginRight = -5;
		grid.marginBottom = -5;
	}
	
	public void constructToolBar(Composite parent)throws ManagerEditorException{
		try {
			
			this.parent = parent;
			this.parent.setLayout(grid);
			
			
				
			/* Load file: toolBar.xml */
				Document toolbarDoc = LoadXML.getDocument(PathManager.getToolBarFileName(),PathManager.getToolbarClass());
				Element toolbarRoot = toolbarDoc.getRootElement();
		
			/* Get language */
				Document languageDoc = this.manager.getLanguageManager().getDocument();
				if(languageDoc != null){
					Element languageRoot = languageDoc.getRootElement();
					buildToolBars(toolbarRoot, languageRoot);
				}
			}
			catch (Exception e) {
				throw new ManagerEditorException("Ha ocurrido algún problema mientras se construía la ToolBar, y no ha podido ser cargada.",e);
			}
	}
	
	/**
	 * Crea un ToolBar por cada botón.
	 */
	public void buildToolBars(Element toolbarRoot, Element languageRoot) {
		
		Element elActions = toolbarRoot.getChild("lateralPalette");
		
		Element lanToolBars = languageRoot.getChild("toolBars");
		Element lanActions = lanToolBars.getChild("actions");
		
		//Element lanActions = lRoot.getChild("actions");
		
		ToolBar toolbar;
		
		Image img;

		// Deshacer - Undo
		toolbar = new ToolBar(parent, SWT.NONE);
		
		Element elUndo = elActions.getChild("undo");
		img = SWTResourceManager.getImage(GUIEditor.class, elUndo.getAttributeValue("icon"));
		toolbar.setToolTipText(lanActions.getChild("undo").getValue() + " (Ctrl+Z)");
		
		ToolItem toolItemUndo = new ToolItem(toolbar, SWT.NONE);
		toolItemUndo.setImage(img);
		toolItemUndo.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				try{
					manager.getActionManager().actionUndo();
				}catch(Exception ex){}
			}
		});
		toolItemUndo.setEnabled(false);
		
		// Rehacer - Redo
		toolbar = new ToolBar(parent, SWT.NONE);
		
		Element elRedo = elActions.getChild("redo");
		img = SWTResourceManager.getImage(GUIEditor.class, elRedo.getAttributeValue("icon"));
		toolbar.setToolTipText(lanActions.getChild("redo").getValue() + " (Ctrl+Y)");
		
		ToolItem toolItemRedo = new ToolItem(toolbar, SWT.NONE);
		toolItemRedo.setImage(img);
		toolItemRedo.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				try{
				manager.getActionManager().actionRedo();
				}catch(Exception ex){}
			}
		});
		toolbar.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1));
		toolItemRedo.setEnabled(false);
		
		// Eliminar
		toolbar = new ToolBar(parent, SWT.NONE);
		
		Element elRemove = elActions.getChild("remove");
		img = SWTResourceManager.getImage(GUIEditor.class, elRemove.getAttributeValue("icon"));
		toolbar.setToolTipText(lanActions.getChild("remove").getValue());
		
		ToolItem toolItemDelete = new ToolItem(toolbar, SWT.NONE);
		toolItemDelete.setImage(img);
		toolItemDelete.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				try{
					manager.getActionManager().actionDelete();
				}catch(Exception ex){}
			}
		});
		toolbar.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1));
		//toolItemDelete.setEnabled(false);
		
		
		// Separador
		Label lbl = new Label(parent,SWT.NONE);
		lbl.setFont(new Font(parent.getDisplay(), "Courier New", 4, SWT.NONE));
		
		// Exportar - Export
		toolbar = new ToolBar(parent, SWT.NONE);
		
		Element elExport = elActions.getChild("export");
		img = SWTResourceManager.getImage(GUIEditor.class, elExport.getAttributeValue("icon"));
		toolbar.setToolTipText(lanActions.getChild("export").getValue() + " (Enter)");
		
		this.exportToolItem = new ToolItem(toolbar, SWT.NONE);
		if(this.manager.getTutorMatesEditor().getCorrectExpresion())
			this.exportToolItem.setEnabled(false);
		this.exportToolItem.setImage(img);
		
		GridData gd = new GridData(SWT.CENTER, SWT.NONE, true, true, 1, 1);
		toolbar.setLayoutData(gd);
		
		// Importar - Import
		toolbar = new ToolBar(parent, SWT.NONE);
		Element elImport = elActions.getChild("import");
		img = SWTResourceManager.getImage(GUIEditor.class, elImport.getAttributeValue("icon"));
		toolbar.setToolTipText(lanActions.getChild("import").getValue());
		this.importToolItem = new ToolItem(toolbar, SWT.NONE);
		this.importToolItem.setImage(img);
		if(!this.manager.getTutorMatesEditor().getCorrectExpresion())
			this.importToolItem.setEnabled(false);
		
		this.manager.getActionManager().setItems(toolItemUndo, toolItemRedo, toolItemDelete, this.exportToolItem, this.importToolItem);
	}
}