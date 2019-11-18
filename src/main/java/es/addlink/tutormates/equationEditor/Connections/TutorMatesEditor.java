package es.addlink.tutormates.equationEditor.Connections;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolItem;

/**
 * Connects the equation editor with another java application.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("37d2ae4d-01c9-46a4-a9f3-3a4a67b3cdd7")
public class TutorMatesEditor {
    /**
     * Requires a correct expression for exporting.
     */
    @objid ("f015253b-4b4e-4781-a402-172ce9b08d5a")
    private Boolean correctExpresion;

    /**
     * GUI's equation editor.
     */
    @objid ("ebd33f05-4f52-491b-87e7-67d11568884f")
    private GUIEditor gui;

    /**
     * Contains equation editor's global configuration.
     */
    @objid ("b15d31e2-c8e7-4d4b-a340-5f886404a256")
    private Manager manager;

    /**
     * Captures enter key.
     */
    @objid ("d63cfd39-b102-4e14-a3d8-7daca0490c7c")
    private KeyAdapter kAdapter;

    /**
     * Constructor.
     * @param lang Two first chars of the language. Example: "es" -> Spanish, "en" -> English, ...
     */
    @objid ("785e070c-7d35-4399-a075-af32e50cf9b7")
    public TutorMatesEditor(String lang) {
        this.correctExpresion = true;
        this.manager = new Manager(this,lang,false);
    }

    @objid ("e36dc0c1-3934-40e0-bbbf-2114118287f4")
    public TutorMatesEditor(String lang, Boolean correctExpression, Boolean isTestVersion) {
        this.correctExpresion = correctExpression;
        this.manager = new Manager(this,lang,isTestVersion);
    }

    @objid ("f57df109-b544-43ec-87ca-203c6b047139")
    public TutorMatesEditor(String lang, Boolean correctExpression, String profile, String course, String unit) {
        this.correctExpresion = correctExpression;
        this.manager = new Manager(this,lang,false);
        this.manager.buildRole(profile, course, unit);
    }

    /**
     * Construye la parte gráfica.
     * @param lang Lenguaje con el que se quiere inicializar el editor (en,es,ca,eu,ga).
     * @param parent Composite donde se quiere alojar el editor.
     */
    @objid ("8575ba64-08c9-426e-b571-4d351c41d4df")
    public void buildGUI(Composite parent, KeyAdapter kAdapter) {
        this.manager.buildRole("all","","");
        this.kAdapter = kAdapter;
        this.gui = new GUIEditor(this.manager,parent);
        this.gui.createGUI(false);
    }

    /**
     * Insert the first text box at the beginning.
     */
    @objid ("f4874c47-3e2b-45fd-a5c3-c741631d2544")
    public void insertFirstText() {
        this.gui.insertFirstText();
    }

    /**
     * Inserts the equation editor into the Composite. It will load the role configuration.
     * @param parent
     * @param kAdapter
     * @param profile
     * @param course
     * @param unit
     */
    @objid ("c3c1315c-9b60-4d7e-b1f2-75e0f7af3f4b")
    public void buildGUI(Composite parent, KeyAdapter kAdapter, String profile, String course, String unit) {
        this.manager.buildRole(profile,course,unit);
        this.kAdapter = kAdapter;
        this.gui = new GUIEditor(this.manager,parent);
        this.gui.createGUI(false);
    }

    /**
     * Construye la parte gráfica, añadiendo una parte para obtener y comprobar el mathml de una expresión.
     * @param lang Lenguaje con el que se quiere inicializar el editor (en,es,ca,eu,ga).
     * @param parent Composite donde se quiere alojar el editor.
     */
    @objid ("b81f51d3-4506-4d09-b81a-2fee4f79d498")
    public void buildGUIWithMathMLTesting(Composite parent, KeyAdapter kAdapter) {
        this.manager.buildRole("all","","");
        this.kAdapter = kAdapter;
        this.gui = new GUIEditor(this.manager,parent);
        this.gui.createGUI(true);
    }

    /**
     * Clases utilizada para las pruebas de código mathml.
     */
    @objid ("9cd908eb-3369-4436-b22f-535c734022de")
    public void exportMathMLInText() {
        try {
            String mathml = this.manager.getActionManager().exportMathML();
            if(mathml != null){
                this.gui.getGUIMathMLTest().setTextOut(mathml);
                this.gui.getGUIMathMLTest().setTextIn(mathml);
            } 
        }
        catch (ManagerEditorException e) {
            //e.showExtendedError();
        }
    }

    /**
     * Devuelve el ToolItem del botón exportar para que desde el exterior se capture el clic.
     * @return ToolItem del botón exportar para que desde el exterior se capture el clic.
     */
    @objid ("437d5f70-add5-4075-b9e5-e293a111f9eb")
    public ToolItem getExportToolItem() {
        return this.manager.getActionsToolBar().getExportToolItem();
    }

    @objid ("13486852-c549-4a30-a50e-1c7c4c37a703")
    public Button getImportButton_Test() {
        return this.gui.getGUIMathMLTest().getBtnImport();
    }

    /**
     * Devuelve el ToolItem del botón importar para que desde el exterior se capture el clic.
     * @return ToolItem del botón importar para que desde el exterior se capture el clic.
     */
    @objid ("beb3ab14-ed78-469a-a0b3-cd9f3eb6da61")
    public ToolItem getImportToolItem() {
        return this.manager.getActionsToolBar().getImportToolItem();
    }

    /**
     * Devuelve el KeyAdapter definido fuera del editor, cuya misión es capturar las pulsaciones de "enter".
     * @return
     */
    @objid ("21852d6a-f7da-4b54-a7ad-247c82de5a38")
    public KeyAdapter getKeyAdapter() {
        return this.kAdapter;
    }

    /**
     * Devuelve el manager del editor.
     * @return Manager del editor.
     */
    @objid ("92998fec-9cb8-4428-b540-dfed805c0d63")
    public Manager getManager() {
        return this.manager;
    }

    /**
     * Devuelve el MathML de la expresión actual que hay en el editor.
     * @return MathML de la expresión actual que hay en el editor.
     */
    @objid ("a5d30489-9e81-44ec-93c5-7335c712cffd")
    public String getMathML() {
        try {
            return this.manager.getActionManager().exportMathML();
        }
        catch (ManagerEditorException e) {
            e.showExtendedError();
            return null;
        }
    }

    /**
     * Devuelve true si el editor solo exporta expresiones correctas, false en caso contrario.
     * @return True si el editor solo exporta expresiones correctas, false en caso contrario.
     */
    @objid ("c1959194-fa56-4a7e-8050-c39c46aa593b")
    public Boolean getCorrectExpresion() {
        return correctExpresion;
    }

    /**
     * Devuelve el título para la ventana donde se aloje el editor.
     * @return
     */
    @objid ("7a8ce21a-4f87-4677-a7bf-c850bc3c0c01")
    public String getTitle() {
        return this.manager.getLanguageManager().getTitle();
    }

    @objid ("ba4355c2-c97e-4270-9c74-0dca4ba4a0a3")
    public void importMathMLInText() {
        try {
            manager.getActionManager().actionImportMathML(this.gui.getGUIMathMLTest().getTextImput());
        }
        catch (ManagerEditorException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
    }

    @objid ("b90fafe8-5d8c-4417-bb99-f40207dd50d2")
    public boolean isMathMLCorrect(String mathml) {
        return this.manager.getActionManager().isMathMLCorrect(mathml);
    }

    /**
     * Establece el foco en el editor. Se suele llamar a este método al inicio.
     */
    @objid ("6e9c9370-13be-421b-be04-a8083f964023")
    public void setFocus() {
        this.gui.setFocus();
    }

    /**
     * Carga la expresión pasada por parámetro en el editor.
     * @param mathml MathML que se desea cargar en el editor.
     */
    @objid ("57aac100-6c52-42bb-972a-9e22e88500c5")
    public void setMathML(String mathml) {
        try {
            this.manager.getActionManager().actionImportMathML(mathml);
        }
        catch (ManagerEditorException e) {
            e.showExtendedError();
        }
    }

}
