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
 * File: DropdownSelectionListener.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolItem;

public class DropdownSelectionListener extends SelectionAdapter {
	  private ToolItem dropdown;

	  private Menu menu;

	  public DropdownSelectionListener(ToolItem dropdown) {
	    this.dropdown = dropdown;
	    menu = new Menu(dropdown.getParent().getShell());
	  }

	  public void add(ToolItem item) {
	    MenuItem menuItem = new MenuItem(menu, SWT.NONE);
	    menuItem.setImage(item.getImage());
	    menuItem.setText("");
	    menuItem.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        MenuItem selected = (MenuItem) event.widget;
	        dropdown.setText(selected.getText());
	      }
	    });
	  }

	  public void widgetSelected(SelectionEvent event) {
	    if (event.detail == SWT.ARROW) {
	      ToolItem item = (ToolItem) event.widget;
	      Rectangle rect = item.getBounds();
	      Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
	      menu.setLocation(pt.x, pt.y + rect.height);
	      menu.setVisible(true);
	    } else {
	      //System.out.println(dropdown.getText() + " Pressed");
	    }
	  }
	}
