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
 * File: SequenceList.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

import java.util.List;
import java.util.Vector;

public class SequenceList extends MathEditor {

	private List<MathEditor> list;
	private String separator;
	
	public SequenceList(MathEditor parent, String separator) {
		super("list", "sequenceList", -1, parent);
		// TODO Auto-generated constructor stub
		
		this.list = new Vector<MathEditor>();
		this.separator = separator;
	}

	@Override
	public String toString() {
		String cad = "<lista name=" + super.getName() + " id=" + super.getID() + ">";
		int i=0;
		
		while(i<this.list.size()){
			cad += "\n<child>\n";
			cad += this.list.get(i) + "\n";
			i++;
			cad += "</child>";
		}

		cad += "\n</lista>\n";
		return cad;
	}
	
	public int getNumItems(){
		return this.list.size();
	}
	
	public MathEditor getItem(int i){
		if(i < getNumItems()) return this.list.get(i);
		else return null;
	}
	
	public void addMathEditor(MathEditor mathEditor){
		this.list.add(mathEditor);
	}

	public String getSeparator() {
		return separator;
	}
	
}
