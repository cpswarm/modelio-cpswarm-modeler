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
 * File: MathEditor2MathML.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Translation;

import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import es.addlink.tutormates.equationEditor.Exceptions.ExportTranslationEditorException;
import es.addlink.tutormates.equationEditor.Formats.Category;
import es.addlink.tutormates.equationEditor.Formats.MathML.MathMLAttribute;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Manager.PathManager;
import es.addlink.tutormates.equationEditor.MathEditor.Binary;
import es.addlink.tutormates.equationEditor.MathEditor.Function;
import es.addlink.tutormates.equationEditor.MathEditor.IncorrectExpression;
import es.addlink.tutormates.equationEditor.MathEditor.IntNumber;
import es.addlink.tutormates.equationEditor.MathEditor.MathEditor;
import es.addlink.tutormates.equationEditor.MathEditor.RealNumber;
import es.addlink.tutormates.equationEditor.MathEditor.RepeatingDecimal;
import es.addlink.tutormates.equationEditor.MathEditor.SequenceList;
import es.addlink.tutormates.equationEditor.MathEditor.StringNumber;
import es.addlink.tutormates.equationEditor.MathEditor.Text;
import es.addlink.tutormates.equationEditor.MathEditor.Unary;
import es.addlink.tutormates.equationEditor.MathEditor.Variable;
import es.addlink.tutormates.equationEditor.Role.BaseFunction;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;

/**
 * 
 * Esta clase se encarga de convertir un MathEditor en MathML, apoyándose de un fichero xml de configuración.
 * @author Ignacio Celaya
 *
 */
public class MathEditor2MathML {	
	
	/**
	 * Documento xml que contiene el nuevo MathML formado.
	 */
	private Document doc;
	
	/**
	 * 
	 */
	private Manager manager;
	
	/**
	 * Namespace.
	 */
	public static String MATHML_NS = "http://www.w3.org/1998/Math/MathML";
	
	/**
	 * v1: Expresiones con <mrow> después de <math>. En el resto de expresión están los mrows
	 */
	public static String madeIn = "equationEditor";
	
	/**
	 * v2: Expresiones sin <mrow> después de <math>. Productos invisibles agrupados con <mrow>.
	 */
	public static String madeIn_v2 = "equationEditor_v2";
	
	/**
	 * Namespace.
	 */
	static Namespace ns = Namespace.getNamespace(MATHML_NS);
	
	/**
	 * Constructor. Construye la estructura inicial "<math xmlns='namespace'><mrow>...</mrow></math>".
	 * @param mathEditor MathEditor que se desea convertir a MathML.
	 */
	public MathEditor2MathML(Manager manager, MathEditor mathEditor)  throws ExportTranslationEditorException{
		try {
			//System.out.println(mathEditor);
			this.manager = manager;
			this.doc = new Document();
			Element raiz = new Element("math",ns);
			if(this.manager.isTestVersion())
				raiz.setAttribute("madeIn", madeIn_v2);
			else
				raiz.setAttribute("madeIn", madeIn);
			
			doc.addContent(raiz);
			Element mrow = new Element("mrow",ns);
			if(this.manager.isTestVersion()){
				convert(mathEditor,raiz);
			}else{
				raiz.addContent(mrow);
				convert(mathEditor,mrow);
			}			
		}
		catch (ExportTranslationEditorException e) {
			throw new ExportTranslationEditorException("Error al convertir un MathEditor en MathML.",e);
		}
	}
	
	private void incluirIncorrectExpression(MathEditor incorrectExpression, Element root)throws ExportTranslationEditorException{
		try{
			IncorrectExpression ie = (IncorrectExpression)incorrectExpression;
			Iterator<MathEditor> ite = ie.getList().iterator();
			while(ite.hasNext()){
				MathEditor m = ite.next();
				if(m.getName().equalsIgnoreCase("text")){
					Text t = (Text)m;
					Element el = new Element("mtext",ns);
					el.setText(t.getText());
					root.addContent(el);
				}else
					convert(m,root);
			}
		}catch(Exception ex){
			//ex.printStackTrace();
			throw new ExportTranslationEditorException("Error al convertir un MathEditor en MathML.",ex);
		}
	}
	
	/**
	 * Mediante llamadas recursivas va recorriendo el mathEditor pasado por parámetro.
	 * @param mathEditor MathEditor que se desea convertir a MathML.
	 * @param root Elemento padre a partir del cual se construye el resto de xml.
	 */
	public void convert(MathEditor mathEditor, Element root) throws ExportTranslationEditorException{
		try{
			if(mathEditor != null){
				
				//Incorrect expression
				if (mathEditor.getClass().getName().equals(PathManager.getMathEditorClasses()+".IncorrectExpression"))
					incluirIncorrectExpression(mathEditor,root);
				
				//Lista
				if (mathEditor.getClass().getName().equals(PathManager.getMathEditorClasses()+".SequenceList")) {
					
					BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName("sequenceList").get(0);
					SequenceList sl = (SequenceList)mathEditor;
					int i=0;
					
					if(sl.getNumItems()>0){
						Element element = buildElement(ope,root,false);
						
						while(i<sl.getNumItems()){
							Element mrow = new Element("mrow",ns);
							element.addContent(mrow);
							convert(sl.getItem(i),mrow);
							i++;
						}
					}
				}
				
				//Int numbers
				else if (mathEditor.getClass().getName().equals(PathManager.getMathEditorClasses()+".IntNumber")) {
					IntNumber n = (IntNumber)mathEditor;
					BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getNumbersVariablesByName("intNumber").get(0);
					ope.setSymbolMathML(String.valueOf(n.getNumber()));
					buildElement(ope,root,true);
				}
				
				//Real numbers
				else if (mathEditor.getClass().getName().equals(PathManager.getMathEditorClasses()+".RealNumber")) {
					RealNumber n = (RealNumber)mathEditor;
					BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getNumbersVariablesByName("realNumber").get(0);
					ope.setSymbolMathML(String.valueOf(n.getNumber()));
					buildElement(ope,root,true);
				}
				
				//Variables
				else if (mathEditor.getClass().getName().equals(PathManager.getMathEditorClasses()+".Variable")) {
					Variable v = (Variable)mathEditor;
					if(v.getVar().length() > 0){
						BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getNumbersVariablesByName(v.getVar()).get(0);
						buildElement(ope,root,true);
					}else{
						throw new ExportTranslationEditorException(new Exception("Error al construir una función."));
					}
				}
				
				//Funciones
				else if (mathEditor.getClass().getName().equals(PathManager.getMathEditorClasses()+".Function")) {
					Function f = (Function)mathEditor;
					BaseFunction fun = this.manager.getAllowedOperatorsAndFunctions().getFunction(f.getName());
					
					if(fun.getMathML().getFunctionType()){
					
						buildFunction(fun,root,true);
						
						Element element = new Element("mfenced",ns);
						element.setAttribute("open","(");
						element.setAttribute("close",")");
						element.setAttribute("separators",",");
						
						root.addContent(element);
						
						int i=0;
						
						while(i<fun.getEntries()){
							Element mrow = new Element("mrow",ns);
							element.addContent(mrow);
							MathEditor m = f.getChild(i);
							if(m == null){
								//throw new ExportTranslationEditorException("Error al convertir un MathEditor en MathML. Conversión de una función.",null);
							}
							convert(m,mrow);
							i++;
						}
					}
					
					else{
						Element element = buildFunction(fun,root,false);
						
						int i=0;
						
						while(i<fun.getEntries()){
							Element mrow = new Element("mrow",ns);
							element.addContent(mrow);
							MathEditor m = f.getChild(i);
							
							if(m == null) throw new ExportTranslationEditorException("Error al convertir un MathEditor en MathML. Conversión de una función.",null);
							
							convert(m,mrow);
							i++;
						}
					}
				}
				
				//Unary elements
				else if(mathEditor.getClass().getName().equalsIgnoreCase(PathManager.getMathEditorClasses()+"." + Category.UnaryType)) {
					Unary una = (Unary)mathEditor;
					
					//Text operators
					if(this.manager.getAllowedOperatorsAndFunctions().existsOperatorByTypeAndName(Category.UnaryType, una.getName())){
						BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorById(una.getID());
						//System.out.println("id: " + una.getID());
						if(ope.isText()){
							
							//Los negativos se colocan delante del número: -7
							if(ope.getSymbolEditor().equals("neg")){
								buildElement(ope,root,true);
								convert(una.getChild(),root);

							//Los factoriales y porcentajes se colocan después del número: 4!, 5%.
							}else{
								convert(una.getChild(),root);
								buildElement(ope,root,true);
							}
						
						//No text operators
						}else{
							
							/**
							 * Para los operadores unarios que no son de tipo texto, siempre se crea un mrow.
							 */
							
							Element el = buildElement(ope,root,false);
							Element mrow = new Element("mrow",ns);
							el.addContent(mrow);
							convert(una.getChild(),mrow);
							
						}
					}
				}
				
				
				//Binary elements
				else if(mathEditor.getClass().getName().equalsIgnoreCase(PathManager.getMathEditorClasses() + "." + Category.BinaryType)) {
					Binary bin = (Binary)mathEditor;
					
					if(this.manager.getAllowedOperatorsAndFunctions().existsOperatorByTypeAndName(Category.BinaryType, bin.getName())){
						BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorById(bin.getID());
						
						//Text operators
						if(ope.isText()){
							
							//Cuando se detecta un producto invisible, se inserta dentro de un <mrow>, para que el mathml no se parta por la mitad en el xulrunner.
							if(ope.getName().equalsIgnoreCase("invisibleMultiplication") && this.manager.isTestVersion()){
								Element elRow = new Element("mrow",ns);
								convert(bin.getLeftChild(),elRow);
								buildElement(ope,elRow,true);
								convert(bin.getRightChild(),elRow);
								root.addContent(elRow);
							}else{
								convert(bin.getLeftChild(),root);
								buildElement(ope,root,true);
								convert(bin.getRightChild(),root);
							}
							
							
							
						
						//No text operators
						}else{
							
							/*
							 * Para los binarios que no son de tipo texto se crean mrows.
							 * Ej: 	<mfrac>
							 * 			<mrow><mn>1</mn></mrow>
							 * 			<mrow><mn>2</mn></mrow>
							 * 		</mfrac>
							 * 
							 * ---------------------
							 * 
							 * 1) Se crea el objeto binario
							 * 2) Se crea un mrow.
							 * 		2.1) Se crea el LeftChild dentro de ese mrow.
							 * 3) Se crea un mrow.
							 * 		3.1) Se crea el RightChild dentro de ese mrow.
							 */
							
							Element elOpe = buildElement(ope,root,false);
							
								
							Element mrow1 = new Element("mrow",ns);
							elOpe.addContent(mrow1);
							convert(bin.getLeftChild(),mrow1);
							
							Element mrow2 = new Element("mrow",ns);
							elOpe.addContent(mrow2);
							convert(bin.getRightChild(),mrow2);
							
						}
					}
				}
				
				//Repeating decimal
				else if(mathEditor.getClass().getName().equals(PathManager.getMathEditorClasses()+".RepeatingDecimal")) {
					RepeatingDecimal rep = (RepeatingDecimal) mathEditor;
					BaseOperator ope = this.manager.getAllowedOperatorsAndFunctions().getOperatorsByName(rep.getName()).get(0);
					
					String first;
					String second;
					String third;
					
					String oculto="";
					String ocultoValue="";
					String mnValue="";
					String code = "&#xAF;";
					try{
					if(rep.getSecondChild() == null){
						
						if(rep.getThirdChild().getName().equalsIgnoreCase("incorrectExpression")){
							IncorrectExpression ie = (IncorrectExpression)rep.getThirdChild();
							third = ((Text)ie.getList().get(0)).getText();
						}else
							third =  ((StringNumber)rep.getThirdChild()).getText();
						
						if(rep.getFirstChild().getName().equalsIgnoreCase("incorrectExpression")){
							IncorrectExpression ie = (IncorrectExpression)rep.getFirstChild();
							first = ((Text)ie.getList().get(0)).getText();
						}else
							first = String.valueOf(((IntNumber)rep.getFirstChild()).getNumber());
						
						
						oculto = first+"."+"dec"+third;
						ocultoValue = first+".";
						mnValue = third;
						
					}
					else{
						
						if(rep.getThirdChild().getName().equalsIgnoreCase("incorrectExpression")){
							IncorrectExpression ie = (IncorrectExpression)rep.getThirdChild();
							third = ((Text)ie.getList().get(0)).getText();
						}else
							third =  ((StringNumber)rep.getThirdChild()).getText();
						
						//System.out.println(rep.getThirdChild());
						
						if(rep.getSecondChild() != null && rep.getSecondChild().getName().equalsIgnoreCase("incorrectExpression")){
							IncorrectExpression ie = (IncorrectExpression)rep.getSecondChild();
							second = ((Text)ie.getList().get(0)).getText();
						}else
							second =  ((StringNumber)rep.getSecondChild()).getText();
						
						
						if(rep.getFirstChild().getName().equalsIgnoreCase("incorrectExpression")){
							IncorrectExpression ie = (IncorrectExpression)rep.getFirstChild();
							first = ((Text)ie.getList().get(0)).getText();
						}else
							first = String.valueOf(((IntNumber)rep.getFirstChild()).getNumber());
	
						oculto = first+"."+second+"dec"+third;
						ocultoValue = first+"."+second;
						mnValue = third;
					}
					
					Element el = buildElement(ope,root,false);
					el.setAttribute("oculto", oculto);
					el.setText(ocultoValue);
					
					Element mover = new Element("mover",ns);
					root.addContent(mover);
					
					Element mn = new Element("mn",ns);
					mover.addContent(mn);
					mn.setText(mnValue);
					
					Element mo = new Element("mo",ns);
					mover.addContent(mo);
					mo.setText(code);
					}catch(Exception ex){ex.printStackTrace();}
				}
			}
		}catch(Exception ex){
			//ex.printStackTrace();
			throw new ExportTranslationEditorException("Error al convertir un MathEditor en MathML.",ex);
		}
	}

	/**
	 * Método que es llamado por todas las estructuras anteriores excepto por el RepeatingDecimal.
	 * Es el responsable de añadir tanto el valor como los atributos a cada operador.
	 * @param ope Operación de tipo AllowedOperator que se desea añadir al xml.
	 * @param root Elemento padre a partir del cual se construye el resto de xml.
	 * @param withValue Si el operador lleva valor entre sus etiquetas es true, false en caso contrario.
	 * Por ejemplo, en el caso de una suma, e valor es true: <mo>+</mo>.
	 * En el caso de una fracción, el valor es false: <mfrac>...</mfrac>. 
	 * @return
	 */
	public Element buildElement(BaseOperator ope, Element root, boolean withValue){
		try{
			Element el = new Element(ope.getMathML().getMathMLLabel(),ns);
			if(ope.getMathML().hasAttributes()){
				for(int i=0;i<ope.getMathML().getAttributesList().size();i++){
					MathMLAttribute at = ope.getMathML().getAttribute(i);
					el.setAttribute(at.getAttribute(), at.getValue());
				}
			}
			if(withValue) el.setText(ope.getSymbolMathML());
			root.addContent(el);
			return el;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Element buildFunction(BaseFunction fun, Element root, boolean isFunction){
		Element el = new Element(fun.getMathML().getMathMLLabel(),ns);
		if(isFunction){
			el.setAttribute("type","function");
			el.setText(fun.getName());
		}
		
		root.addContent(el);
		
		return el;
	}

	/**
	 * Retorna en tipo cadena el contenido del Document.
	 * @return Devuelve en tipo cadena el contenido del Document.
	 */
	public String getMathMLString(){
		return Mathml2String(doc);
	}

	/**
	 * Devuelve el documento del MathML.
	 * @return Devuelve el documento del MathML.
	 */
	public Document getMathMLDocument(){
		return this.doc;
	}
	
	/**
	 * Este método convierte un Document de tipo mathml a String
	 * Este método convierte el xml en String.
	 * @param mathml árbol mathml que queremos convertir en cadena
	 * @return la cadena mathml
	 * @throws Exception
	 */
	public static String Mathml2String(Document mathml){ 
		//Jdom
		XMLOutputter out = new XMLOutputter();
		//Format format = Format.getPrettyFormat();
		Format format = Format.getCompactFormat();
		format.setOmitEncoding(true) ;
		format.setOmitDeclaration(true);
		format.setEncoding("US-ASCII");
		out.setFormat(format);
		String xml = (out.outputString(mathml));
		xml = xml.replaceAll("&amp;", "&");
		xml = xml.replaceAll("neg", "-");
		return (xml);
	}
}
