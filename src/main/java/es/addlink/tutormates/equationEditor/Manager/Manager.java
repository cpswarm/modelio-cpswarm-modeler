package es.addlink.tutormates.equationEditor.Manager;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Connections.TutorMatesEditor;
import es.addlink.tutormates.equationEditor.Role.AllowedOperatorsAndFunctions;
import org.eclipse.swt.widgets.TabFolder;

/**
 * Clase que engloba toda la configuración del editor.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("6d339318-d29e-4f5c-8d89-df44ef1b8ddd")
public class Manager {
    /**
     * Tamaño de la fuente de los TextBox cargada finalmente.
     */
    @objid ("6cd000ea-ee93-49ab-9110-91380e022d4d")
    private static int fontSize;

    /**
     * Tamaño de la fuente de los TextBox para Mac.
     */
    @objid ("5b6f08dd-dd7d-4ddb-b034-9d81f96bd355")
    private static final int fontSizeMac = 14;

    /**
     * Tamaño de la fuente de los TextBox para Windows.
     */
    @objid ("c6cfdcef-39be-44e6-b3d7-6bdd8795bc88")
    private static final int fontSizeWinLin = 12;

    @objid ("f956ea39-4d60-4d93-85db-a897e3feb227")
    private Boolean isTestVersion;

    /**
     * Contiene los listados de operadores, variables y funciones disponibles para un rol en concreto.
     */
    @objid ("518fb17b-1408-466b-bc81-a6064df07250")
    private AllowedOperatorsAndFunctions allowedOperators;

    /**
     * Rol cargado.
     */
    @objid ("8efe7738-d43c-4f40-8a6e-3872006ca2af")
    private RoleManager roleManager;

    /**
     * Guarda el TextBox que tiene el foco y la posición del cursor.
     */
    @objid ("444c72b7-6ced-4a73-a3b8-7240bbeab373")
    private StateManager state;

    /**
     * Guarda la configuración de las acciones (deshacer, rehacer, exportar e importar).
     */
    @objid ("40e26b56-4303-46e9-adde-a67d73bd273c")
    private ActionManager action;

    /**
     * Realiza varias acciones sobre la zona de edición afectando a una expresión al completo.
     */
    @objid ("301cdfcf-ee21-49f0-a39e-bab6209ca03b")
    private CentralManager central;

    /**
     * Única clase de visibilidad pública hacia el exterior del editor.
     */
    @objid ("2072e454-1706-4b19-946f-701b69b4cf15")
    private TutorMatesEditor editor;

    /**
     * Guarda el historial para deshacer o rehacer una expresión.
     */
    @objid ("6c02aad2-da85-4dd3-9ff5-f53663c86678")
    private HistoryManager history;

    /**
     * Configuración del lenguaje.
     */
    @objid ("9cf77c08-194c-4eff-83de-e2caab370db4")
    private LanguageManager language;

    /**
     * Encargado de construir el ToolBar de los botones de acción.
     */
    @objid ("6d8c5325-a89a-4c3a-9151-ddf2d19e01b6")
    private ActionToolBarDisplayManager toolBar;

    @objid ("14717e01-594b-4a0b-8bcf-fc06d64fd04c")
    public static int getFontSize() {
        return fontSize;
    }

    @objid ("a7dde61c-4776-431b-878e-b84e15d44260")
    public static boolean isMac() {
        return System.getProperty("os.name").toLowerCase().indexOf("mac")>-1;
    }

    @objid ("e9192af2-1c1d-4271-8973-a62bc0669e9f")
    public Boolean isTestVersion() {
        return isTestVersion;
    }

    /**
     * Constructor.
     * @param editor Instancia de la única clase de visibilidad pública hacia el exterior del editor.
     * @param lang Dos primeros caracteres del idioma: "es", "en", etc.
     */
    @objid ("cc61e858-3d90-4b02-b42f-2b6f137170d4")
    public Manager(TutorMatesEditor editor, String lang, Boolean isTestVersion) {
        this.editor = editor;
        this.isTestVersion = isTestVersion;
        
        if(isMac()){
            fontSize = fontSizeMac;
        }else{
            fontSize = fontSizeWinLin;
        }
        
        this.language = new LanguageManager(lang);
        this.state = new StateManager();
        this.toolBar = new ActionToolBarDisplayManager(this);
        this.history = new HistoryManager(this);
        this.action = new ActionManager(this);
        this.central = new CentralManager(this);
    }

    @objid ("99f770e4-8e13-48e4-8e2d-b80ee1c58431")
    public void buildMainToolBar(TabFolder tab) {
        ToolBarDisplayManager tb = new ToolBarDisplayManager(this,tab,this.getRoleManager().getProfile(),this.getRoleManager().getCourse(),this.getRoleManager().getUnit());
        tb.buildMainToolBar();
    }

    /**
     * Construye el rol a partir de los parámetros pasados.
     * @param profile Perfil del usuario: profesor o alumno.
     * @param course Curso: 1ESO, 2ESO, ...
     * @param unit Tema: ENT_N1, ...
     */
    @objid ("d863ff2a-ef17-4589-aae4-62759f3a0058")
    public void buildRole(String profile, String course, String unit) {
        this.roleManager = new RoleManager(this,profile,course,unit);
        this.allowedOperators = new AllowedOperatorsAndFunctions();
        this.allowedOperators.setOperatorsList(this.roleManager.getRole().getListAllowedOperator());
        this.allowedOperators.setNumbersVariablesList(this.roleManager.getRole().getListAllowedNumbersVariables());
        this.allowedOperators.setFunctionsList(this.roleManager.getRole().getListAllowedFunction());
    }

    @objid ("e76eaddf-0740-45eb-b9cc-9fdbdb6d0709")
    public ActionManager getActionManager() {
        return this.action;
    }

    @objid ("ab1f42a5-3c3f-463c-aadd-2bced166429d")
    public ActionToolBarDisplayManager getActionsToolBar() {
        return this.toolBar;
    }

    @objid ("a352048f-7458-4ab4-8680-1f8b59519880")
    public AllowedOperatorsAndFunctions getAllowedOperatorsAndFunctions() {
        return allowedOperators;
    }

    @objid ("3b888a39-3d96-4504-b88c-177c9098965b")
    public CentralManager getCentralManager() {
        return this.central;
    }

    @objid ("39010456-c566-41f4-80b2-bf7864a55ecb")
    public HistoryManager getHistoryManager() {
        return this.history;
    }

    @objid ("c1f0904d-e65d-4b08-b7cd-4e1a968c540e")
    public LanguageManager getLanguageManager() {
        return this.language;
    }

    @objid ("aafcf716-6c6a-4d01-a2fc-f826165d6d9b")
    public RoleManager getRoleManager() {
        return roleManager;
    }

    @objid ("8f879bc3-a4cf-4ec8-a06d-7293e64adfe6")
    public StateManager getStateManager() {
        return this.state;
    }

    @objid ("222432a5-a195-4c7e-b9cc-6b8304c405c4")
    public TutorMatesEditor getTutorMatesEditor() {
        return this.editor;
    }

}
