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
 * File: Function.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

import java.util.List;
import java.util.Vector;

public class Function extends MathEditor {
	
	private List<MathEditor> childList = null;
	
	public Function(String name, int id, MathEditor parent) {
		super("functions", name, id, parent);
		this.childList = new Vector<MathEditor>();
		// TODO Auto-generated constructor stub
	}
	
	public int numChilds(){
		return this.childList.size();
	}
	
	public MathEditor getChild(int n){
		try{
			return this.childList.get(n);
		}catch(Exception e){
			return null;
		}
	}
	
	public List<MathEditor> getChildList(){
		return this.childList;
	}
	
	public void addChild(MathEditor me){
		this.childList.add(me);
	}

	public String toString() {
		String cad = "<function name=" + super.getName() + " id=" + super.getID() + ">";
		int i=0;
		
		while(i<this.childList.size()){
			cad += "\n<child>\n";
			cad += this.childList.get(i) + "\n";
			i++;
			cad += "</child>";
		}

		cad += "\n</function>\n";
		return cad;
	}
	
}
