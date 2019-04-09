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
 * File: GUIMathMLTest.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;

/**
 * Crea el GUI para la versión de generar y cargar MathML, ejecutada desde Equation_Editor_SWT_MathMLTest.java.
 * 
 * @author Ignacio Celaya Sesma
 */
public class GUIMathMLTest {
	
	private Composite parent;
	private Manager manager;
	private Text txtIn;
	private Text txtOut;
	private Button btnImport;
	
	protected GUIMathMLTest(Composite parent, Manager manager){
		this.parent = parent;
		this.manager = manager;
	}
	
	protected void createGUI(){
		
		this.manager.getActionsToolBar().getImportToolItem().setEnabled(false);
		
		Composite c = new Composite(this.parent,SWT.NONE);
		
		GridLayout grid = new GridLayout(3,false);
		grid.marginLeft = 0;
		grid.marginBottom = 0;
		grid.marginTop = 5;
		grid.marginRight = 0;
		
		
		c.setLayout(grid);
		c.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));

		//OUT - verde
		Label lblOut = new Label(c,SWT.NONE);
		lblOut.setText("MathML de\nsalida:");
		
		this.txtOut = new Text(c,SWT.BORDER);
		this.txtOut.setFont(new Font(this.parent.getDisplay(), "Courier New", 11, SWT.BOLD));
		this.txtOut.setForeground(new Color(this.parent.getDisplay(),4,108,2));
		this.txtOut.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		this.txtOut.setEditable(false);
		
		Button btnOut = new Button(c,SWT.NONE);
		Image imgCopy = SWTResourceManager.getImage(GUIEditor.class, "images/copy-to-clipboard.png");
		btnOut.setImage(imgCopy);
		btnOut.setToolTipText("Copiar MathML");
		
		btnOut.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				try{
				org.eclipse.swt.dnd.Clipboard clipboard = new org.eclipse.swt.dnd.Clipboard(parent.getDisplay());
		        String plainText = txtOut.getText();
		        
		        TextTransfer textTransfer = TextTransfer.getInstance();
		        
		        if(!plainText.trim().equalsIgnoreCase(""))
		        	clipboard.setContents(new String[]{plainText}, new Transfer[]{textTransfer});
		        else
		        	clipboard.setContents(new String[]{" "}, new Transfer[]{textTransfer});
		        
		        clipboard.dispose();
				}catch(Exception ex){ex.printStackTrace();}
			}
		});
		
		
		//IN azul
		Label lblIn = new Label(c,SWT.NONE);
		lblIn.setText("Comprobar\nMathML:");
		
		this.txtIn = new Text(c,SWT.BORDER);
		txtIn.setFont(new Font(this.parent.getDisplay(), "Courier New", 11, SWT.BOLD));
		txtIn.setForeground(new Color(this.parent.getDisplay(),16,37,169));
		txtIn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		btnImport = new Button(c,SWT.NONE);
		Image imgIn = SWTResourceManager.getImage(GUIEditor.class, "images/ok.png");
		btnImport.setImage(imgIn);
		btnImport.setToolTipText("Importar expresión");
		
		//Limpiar
		new Label(c,SWT.NONE);
		new Label(c,SWT.NONE);
		/*Label lblAviso = new Label(c,SWT.NONE);
		lblAviso.setFont(new Font(this.parent.getDisplay(), "Arial", 8, SWT.NONE));
		lblAviso.setForeground(new Color(this.parent.getDisplay(),200,0,0));
		lblAviso.setText("No se podrá COMPROBAR expresiones que contengan algún\nelemento de estos:\n\n* Subíndices: sub, <msub>");*/
		Button btnLimpiar = new Button(c,SWT.NONE);
		
		Image imgClean = SWTResourceManager.getImage(GUIEditor.class, "images/clean.png");
		btnLimpiar.setImage(imgClean);
		
		btnLimpiar.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				try{
					txtOut.setText("");
					txtIn.setText("");
				}catch(Exception ex){ex.printStackTrace();}
			}
		});
		c.pack();
		
	}

	public Button getBtnImport() {
		return btnImport;
	}
	
	public void setTextOut(String str){
		this.txtOut.setText(str);
	}
	
	public void setTextIn(String str){
		this.txtIn.setText(str);
	}
	
	public String getTextImput(){
		return this.txtIn.getText();
	}
	
}
