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
 * File: TabToolBar.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Role;

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.graphics.Image;

public class TabToolBar {

	private List<ItemToolBar> listItemToolBar;
	private String name;
	private String text;
	private Image icon;
	private int columns;
	private Boolean visible;
	private Boolean enabled;

	public TabToolBar(List<ItemToolBar> listItemToolBar, String name, String text, Image icon, int columns, Boolean show, Boolean enabled) {
		super();
		this.listItemToolBar = listItemToolBar;
		this.name = name;
		this.text = text;
		this.icon = icon;
		this.columns = columns;
		this.visible = show;
		this.enabled = enabled;
	}

	public String getText() {
		return text;
	}
	
	public List<ItemToolBar> getListItemToolBar() {
		return listItemToolBar;
	}

	public String getName() {
		return name;
	}

	public Image getIcon() {
		return icon;
	}

	public int getColumns() {
		return columns;
	}

	public Boolean getShow() {
		return visible;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		String str="";
		
		str += name;
		str += " ~ " + text;
		str += " ~ " + columns;
		str += " ~ " + visible;
		str += " ~ " + enabled;
		str += " ~ " + icon;
		
		if(this.listItemToolBar != null){
			str += " ~ botones: ";
			Iterator<ItemToolBar> ite = this.listItemToolBar.iterator();
			while(ite.hasNext()){
				ItemToolBar item = (ItemToolBar) ite.next();
				str += item + ", ";
			}
		}
		
		return str;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	// TODO Auto-generated method stub

	}

}
