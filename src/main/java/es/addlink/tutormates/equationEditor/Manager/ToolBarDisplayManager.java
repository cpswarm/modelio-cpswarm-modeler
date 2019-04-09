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
 * File: ToolBarDisplayManager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import es.addlink.tutormates.equationEditor.Display.ButtonToolBar;
import es.addlink.tutormates.equationEditor.Role.ItemToolBar;
import es.addlink.tutormates.equationEditor.Role.TabToolBar;

public class ToolBarDisplayManager {

	private Manager manager;
	private TabFolder tabFolder;
	
	public ToolBarDisplayManager(Manager manager, TabFolder tabFolder, String profile, String course, String unit){
		this.manager = manager;
		this.tabFolder = tabFolder;
	}
	
	public void buildMainToolBar(){
		TabItem tabItem = null;			
		
		Iterator<TabToolBar> ite = this.manager.getRoleManager().getRole().getListTabToolBar().iterator();
		while(ite.hasNext()){
			
			TabToolBar tab = (TabToolBar) ite.next();
			if(tab.getShow()){
				tabItem = new TabItem(this.tabFolder,SWT.NONE);
				tabItem.setText(tab.getText());
				tabItem.setImage(tab.getIcon());
				
				Composite cmp = new Composite(this.tabFolder,SWT.NONE);
				tabItem.setControl(cmp);
				GridLayout grid = new GridLayout();
				grid.horizontalSpacing = 0;
				grid.verticalSpacing = 0;
				grid.numColumns = tab.getColumns();
				grid.marginTop = 5;
				grid.marginLeft = 5;
				grid.marginBottom = 5;
				cmp.setLayout(grid);
				
				Iterator<ItemToolBar> it = tab.getListItemToolBar().iterator();
				while(it.hasNext()){
					ItemToolBar button = (ItemToolBar) it.next();
					int style = SWT.NONE;
					if(button.getName().equalsIgnoreCase("separator"))
						style = SWT.SEPARATOR;
					
					Boolean enabled;
					if(tab.getEnabled() == true)
						enabled = button.isEnabled();
					else
						enabled = false;
					
					new ButtonToolBar(this.manager,cmp,button.getIcon(),button.getTooltip(),style,button.isText(),button.getOperator(),enabled);
					
				}
			}
		}
		
	}
}
