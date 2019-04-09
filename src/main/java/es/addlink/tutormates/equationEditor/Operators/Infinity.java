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
 * File: Infinity.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Operators;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;

public class Infinity extends WithoutEntriesOperator {

private Label infinitySymbol;
	
	public Infinity(Manager manager, GridExpression cuadriculaPadre) throws ComponentEditorException {
		super(manager, cuadriculaPadre, "infinity", 169);
		// TODO Auto-generated constructor stub
		
		GridLayout grid = new GridLayout(1,true);
		grid.marginRight = -4;
		grid.marginLeft = -4;
		grid.marginBottom = 0;
		grid.marginTop = 2;
		
		super.getCmpTodo().setLayout(grid);
		this.infinitySymbol = new Label(super.getCmpTodo(), SWT.NONE);
		this.infinitySymbol.setBackground(this.getColorFondo());
		super.setSymbol(infinitySymbol);
		Image img = SWTResourceManager.getImage(GUIEditor.class, "images/operators/infinity.png");
		this.infinitySymbol.setImage(img);
		super.getCmpTodo().pack();
		super.getCmpTodo().setLocation(0, 1);
		setMenuEliminar();
		super.setAltura(0);
	}
	
	@Override
	public int getPosicionCentral() throws ComponentEditorException {
		// TODO Auto-generated method stub
		return 10;
	}
}
