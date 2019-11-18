package es.addlink.tutormates.equationEditor.Manager;

import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ImportTranslationEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.MathEditor.MathEditor;
import es.addlink.tutormates.equationEditor.Operators.GridExpression;
import es.addlink.tutormates.equationEditor.Translation.ExportMathEditor;
import es.addlink.tutormates.equationEditor.Translation.ImportMathEditor;
import es.addlink.tutormates.equationEditor.Translation.MathEditor2MathML;
import es.addlink.tutormates.equationEditor.Translation.MathML2MathEditor;
import es.addlink.tutormates.equationEditor.Utils.CustomToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolItem;
import org.jdom.Document;
import org.jdom.Element;

/**
 * Gestiona el comportamiento de los ToolItems del ToolBar de acciones (deshacer,rehacer,eliminar,exportar,importar)
 * 
 * @author Nuria García, Ignacio Celaya Sesma
 */
@objid ("da65e959-74fc-4a54-abc2-416dcd953c06")
public class ActionManager {
    @objid ("8353bb8a-ddb8-4c52-ba29-f4792386d582")
    private static String incorrectExportExpressionTitle;

    @objid ("ae5cd818-66e8-446e-93c3-d1c597c53ea0")
    private static String incorrectExportExpressionMessage;

    @objid ("fab9c660-c0f0-499b-bd76-64bcbb3f39b2")
    private static String emptyExportExpressionTitle;

    @objid ("a0f5e809-7134-42dd-adb9-b4a41b75c31c")
    private static String emptyExportExpressionMessage;

    @objid ("eccbf7bd-1240-43e8-b1c4-2a474735091d")
    private static String incorrectImportExpressionTitle;

    @objid ("0f8db126-09b2-4bf0-b958-07e197574afb")
    private static String incorrectImportExpressionMessage;

    @objid ("34c44303-9d79-4fb0-b162-c2f43d9c4550")
    private static String emptyImportExpressionTitle;

    @objid ("0d322c27-97e6-4ca8-ac13-4c05dea84cbf")
    private static String emptyImportExpressionMessage;

    /**
     * Mensaje erróneo mostrado por consola cuando el editor recibe un objeto nulo.
     */
    @objid ("8986b090-2edb-46b8-9960-b673d490b1df")
    private final String msgReceiveError = "# Editor [receive]: Null.";

    /**
     * Mensaje satisfactorio mostrado por consola cuando el editor recibe un objeto MathML diferente a nulo.
     */
    @objid ("c318a834-f810-4c67-b636-14d8df2b9046")
    private final String msgReceiveOK = "# Editor [receive]: ";

    /**
     * Mensaje satisfactorio mostrado por consola cuando el editor recibe un objeto MathML diferente a nulo.
     */
    @objid ("73f297bd-aef4-4e4b-ab23-5ef5d95c3976")
    private final String msgMathMLTest = "# Editor [mathml test]: ";

    /**
     * Mensaje erróneo mostrado por consola cuando el editor no envía nada por haber detectado una expresión no válida.
     */
    @objid ("40ca4c87-62e6-4efa-a366-90addd5f9b5a")
    private final String msgSendError = "# Editor [send]: Expresión no válida. No envía nada.";

    /**
     * Mensaje satisfactorio mostrado por consola cuando el editor envía un objeto MathML diferente a nulo.
     */
    @objid ("1424163d-1fd9-43bf-931a-46abeb0d9db0")
    private final String msgSendOK = "# Editor [send]: ";

    /**
     * Si es true, cada vez que el editor envíe una expresión válida se eliminará el contenido, false para el caso contrario.
     */
    @objid ("394340da-e511-4619-bd4a-e7fdf94a1ee1")
    private final boolean removeExpression = true;

    /**
     * Cuadrícula padre del editor.
     */
    @objid ("0aec4d78-98d3-419e-a0e0-470504996606")
    private GridExpression cuadriculaPadre;

    @objid ("be3d5db3-53a3-4916-bebd-01f3c5ceaf45")
    private Manager manager;

    @objid ("2618ceaf-95cc-448e-9044-822378da9e27")
    private Composite cmp;

    @objid ("6dd4f0f8-187a-45fd-bb83-01fe348e738e")
    private Composite cmpInferior;

    /**
     * ToolItem del ToolBar de acciones que al pulsar se encarga de deshacer una acción.
     */
    @objid ("02763999-99b5-4566-bc4f-5cb5663c8cd8")
    private ToolItem toolItemUndo;

//private ToolItem toolItemDelete;
    /**
     * ToolItem del panel de acciones que al pulsar se encarga de eliminar la expresión seleccionada.
     * ToolItem del panel de acciones que al pulsar se encarga de rehacer una acción.
     */
    @objid ("5592ac47-789e-4c3c-9fe6-e05a1bc70230")
    private ToolItem toolItemRedo;

    @objid ("30dc2b2f-47d0-4183-9af8-e04343d92cdf")
    public void setCmp(Composite cmp, Composite cmpInferior) {
        this.cmpInferior = cmpInferior;
        this.cmp = cmp;
    }

    /**
     * Constructor que carga las imágenes en los correspondientes atributos
     */
    @objid ("26050c64-b63d-4003-8154-3d21a2878903")
    @SuppressWarnings("unchecked")
    public ActionManager(Manager manager) {
        this.manager = manager;
        
        try{
            
            /*Language - incorrect expression**********************************************/
                Document languageDoc = this.manager.getLanguageManager().getDocument();
                if(languageDoc != null){
                    Element languageRoot = languageDoc.getRootElement();
                    List<String> errorsLanguage = languageRoot.getChildren("errors");
                    
                    Iterator ite = errorsLanguage.iterator();
                    if(ite.hasNext()){
                        Element el = (Element)ite.next();
                        List<String> guiErrorsLanguage = el.getChildren();
                        Iterator ite2 = guiErrorsLanguage.iterator();
                        
                        while(ite2.hasNext()){
                            Element el2 = (Element)ite2.next();
            
                            if(el2.getName().equals("gui")){
                                Element inc = el2.getChild("incorrectExportExpression");
                                incorrectExportExpressionTitle = inc.getChildText("title");
                                incorrectExportExpressionMessage = inc.getChildText("message");
                                
                                Element inc4 = el2.getChild("emptyExportExpression");
                                emptyExportExpressionTitle = inc4.getChildText("title");
                                emptyExportExpressionMessage = inc4.getChildText("message");
                                
                                Element inc2 = el2.getChild("incorrectImportExpression");
                                incorrectImportExpressionTitle = inc2.getChildText("title");
                                incorrectImportExpressionMessage = inc2.getChildText("message");
                                
                                Element inc3 = el2.getChild("emptyImportExpression");
                                emptyImportExpressionTitle = inc3.getChildText("title");
                                emptyImportExpressionMessage = inc3.getChildText("message");
                            }
                        }
                        
                    }
                }
        /* *****************************************************************************/
            
            
        }catch(Exception ex){
            //ex.printStackTrace();
        }
        /* *************************************************************************************************************************/
    }

    /**
     * Elimina los componentes seleccionados
     */
    @objid ("2b95a6ab-ad75-4788-b63b-8f728dc9944b")
    public void actionDelete() throws EditorException {
        if(!this.manager.getCentralManager().esCuadriculaVacia(this.cuadriculaPadre)){
            if(!this.cuadriculaPadre.haySeleccion())
                this.manager.getCentralManager().vaciarCuadriculaPadre();
            else
                this.manager.getCentralManager().eliminarSeleccion(this.manager.getCentralManager().getCuadriculaPadre());
            //this.setEstadoBtnEliminar(false);
        }
    }

    @objid ("000c5371-9b89-4bcf-bcd1-05a7f161b834")
    public boolean isMathMLCorrect(String mathml) {
        try {
            System.out.println(this.msgMathMLTest + mathml.trim());
            MathML2MathEditor mathML2MathEditor = new MathML2MathEditor(mathml,this.manager);
            MathEditor mo = mathML2MathEditor.getMathEditor();
            return MathEditor.isCorrect(mo);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return false;
        }
    }

    @objid ("7e49d2ab-9a02-43f9-87b9-43770db93413")
    private String exportCorrectExpressionInMathML(MathEditor me) throws ManagerEditorException {
        try{
                MathEditor2MathML mml = new MathEditor2MathML(this.manager,me);
                String mathML = mml.getMathMLString();
                mathML = mathML.replace("~", "").trim();
                System.out.println(this.msgSendOK + mathML);
                
                if (this.removeExpression)
                    this.manager.getCentralManager().vaciarCuadriculaPadre();
                
                return mathML;
        }catch(EditorException ex){
            //ex.printStackTrace();
            //ex.showExtendedError();
            this.manager.getCentralManager().vaciarCuadricula(this.cuadriculaPadre);
            return null;
        }catch(Exception ex){
            
            //ex.printStackTrace();
            //ExportTranslationEditorException e =  new ExportTranslationEditorException("Error desconocido al exportar MathML.",ex);
            //e.showExtendedError();
            return null;
        }
    }

    /**
     * Envía o exporta una expresión en formato MathML.
     */
    @objid ("fd4f984b-8ba4-4799-a19b-230fc0e5a9af")
    public String exportMathML() throws ManagerEditorException {
        try{    
            
            if(!this.manager.getCentralManager().esCuadriculaVacia(this.cuadriculaPadre)){
                
                ExportMathEditor out = new ExportMathEditor(this.manager);
                MathEditor me = out.getMathEditorCargar(this.cuadriculaPadre);
                
                if(!this.manager.getTutorMatesEditor().getCorrectExpresion()){
                    return exportCorrectExpressionInMathML(me);
                }else{
                    
                    if (me == null){
                        
                        /* Se muestra el ToolTip con el error */
                            CustomToolTip tip = new CustomToolTip(this.cmp.getShell(), SWT.BALLOON | SWT.ICON_ERROR, 3000);
                            tip.setText(incorrectExportExpressionTitle);
                            tip.setMessage(incorrectExportExpressionMessage);
                            Point p2 = this.cmp.toDisplay(this.cmpInferior.getLocation());
                            p2.x = p2.x + 30;
                            p2.y = p2.y + 40;
                            tip.setLocation(p2);
                            tip.setVisible(true);
                            tip.activeAutoHide();
                        /* ************************************/
                        
                        System.out.println(this.msgSendError);
                    }else
                        return exportCorrectExpressionInMathML(me);
                }
                this.manager.getCentralManager().insertarTextoInicial(this.cuadriculaPadre);
                if (this.manager.getStateManager().getTextoActivo() != null)
                    this.manager.getStateManager().getTextoActivo().setFoco();
            }else{
                
                /* Se muestra el ToolTip con el error */
                    CustomToolTip tip = new CustomToolTip(this.cmp.getShell(), SWT.BALLOON | SWT.ICON_ERROR, 3000);
                    tip.setText(emptyExportExpressionTitle);
                    tip.setMessage(emptyExportExpressionMessage);
                    Point p2 = this.cmp.toDisplay(this.cmpInferior.getLocation());
                    p2.x = p2.x + 30;
                    p2.y = p2.y + 40;
                    tip.setLocation(p2);
                    tip.setVisible(true);
                    tip.activeAutoHide();
                /* ************************************/
                    
            }
            
        }catch(EditorException ex){
            //ex.printStackTrace();
            //ex.showExtendedError();
            //this.manager.getCentralManager().vaciarCuadricula(this.cuadriculaPadre);
            return null;
        }catch(Exception ex){
            
            //ex.printStackTrace();
            //ExportTranslationEditorException e =  new ExportTranslationEditorException("Error desconocido al exportar MathML.",ex);
            //e.showExtendedError();
            return null;
        }
        return null;
    }

    @objid ("3a4c5ddf-a71e-45e9-86cc-c5d3392e7ee7")
    public void importMathML(String mathml) throws ImportTranslationEditorException {
        try{
            //Poner el panel de edición invisible sirve para que durante el proceso de creación de la expresión, el usuario no vea como se va formando.
            setInvisibleParent();
            //mathml = "<math><mrow><mrow><mfenced open=\"\" close=\"\" separators=\";\"><mrow><mi><mn>5</mn></mi></mrow><mrow><msqrt><mrow><mn>4</mn></mrow></msqrt></mrow></mfenced></mrow></mrow></math>";
            if(isMathMLCorrect(mathml)){
                //System.out.println("RECIBIDO: " + mathml);
                this.manager.getHistoryManager().setGuardar(false);
                
                String mathmlModificado = mathml;
                
                if (mathml != null) {    
                    
                    MathML2MathEditor mathML2MathEditor = new MathML2MathEditor(mathmlModificado,this.manager);
                    MathEditor mo = mathML2MathEditor.getMathEditor();
                    ImportMathEditor i = new ImportMathEditor(this.manager);
                    
                    if(mo != null){
                        
                        System.out.println(this.msgReceiveOK + mathml);
                        this.cuadriculaPadre.vaciar();
                        i.loadMathEditor(mo, this.cuadriculaPadre);
                        this.manager.getCentralManager().setFocoUltimoComponente(this.cuadriculaPadre);
                        this.manager.getHistoryManager().guardarHistorial(false);
                    }
                }
                else
                    System.out.println(this.msgReceiveError);
        
                this.manager.getHistoryManager().setGuardar(true);
            }else{
                String title = "";
                String body = "";
                if(mathml == null || mathml.trim().equalsIgnoreCase("")){
                    title = emptyImportExpressionTitle;
                    body = emptyImportExpressionMessage;
                }else{
                    title = incorrectImportExpressionTitle;
                    body = incorrectImportExpressionMessage;
                }
                
                /* Se muestra el ToolTip con el error */
                    CustomToolTip tip = new CustomToolTip(this.cmp.getShell(), SWT.BALLOON | SWT.ICON_ERROR, 3000);
                    tip.setText(title);
                    tip.setMessage(body);
                    Point p2 = this.cmp.toDisplay(this.cmpInferior.getLocation());
                    p2.x = p2.x + 30;
                    p2.y = p2.y + 40;
                    tip.setLocation(p2);
                    tip.setVisible(true);
                    tip.activeAutoHide();
                /* ************************************/
            }
            setVisibleParent();
        }catch(EditorException ex){
            //ex.printStackTrace();
            try{
                vaciarCuadriculaPadre();
                this.manager.getCentralManager().insertarTextoInicial(this.cuadriculaPadre);
                ex.showExtendedError();
                //ex.printStackTrace();
            }catch(EditorException ex2){
                ex.printStackTrace();
                //throw new ImportTranslationEditorException(ex2);
            }
                    
        }catch(Exception ex){
            ex.printStackTrace();
            throw new ImportTranslationEditorException("Error desconocido al importar MathML.",ex);
        }
    }

    /**
     * Importa una expresión.
     * @throws ManagerEditorException
     * @param mathml MathML en formato String que se desea importar en el editor.
     */
    @objid ("983827e9-eab8-46bb-8529-b920875ab089")
    public void actionImportMathML(String mathml) throws ManagerEditorException {
        try {
            importMathML(mathml);
            
        }catch(EditorException ex){
            //ex.showExtendedError();            
        }catch(Exception ex){
            //ImportTranslationEditorException e = new ImportTranslationEditorException("Error desconocido al importar MathML.",ex);
            //e.showExtendedError();
        }
    }

    /**
     * Rehace una acción
     */
    @objid ("bf0789ed-7c79-4670-805e-b7d98c10c866")
    public void actionRedo() throws ManagerEditorException {
        try{
            setInvisibleParent();
            this.manager.getHistoryManager().setGuardar(false);
            MathEditor mo = this.manager.getHistoryManager().avanzar();
            ImportMathEditor i = new ImportMathEditor(this.manager);
            this.cuadriculaPadre.vaciar();
            if (mo != null){
                i.loadMathEditor(mo, this.cuadriculaPadre);
                this.manager.getCentralManager().setFocoUltimoComponente(this.cuadriculaPadre);
            }else {
                i.insertarTexto("", this.cuadriculaPadre);
                this.cuadriculaPadre.pack();
            }
            cambiarEstado();
            this.manager.getHistoryManager().setGuardar(true);
            setVisibleParent();
        }catch(EditorException ex){
            try{
                vaciarCuadriculaPadre();
            }catch(EditorException ex2){throw new ManagerEditorException(ex2);}
            
            throw new ManagerEditorException(ex);
        }catch(Exception ex){
            throw new ManagerEditorException("Error desconocido al rehacer una acción.",ex);
        }
    }

    @objid ("83bcb609-7487-406d-bf36-ee56a9d685b8")
    private void setInvisibleParent() {
        try {
            this.manager.getCentralManager().getCuadriculaPadre().getParent().setVisible(false);
        }
        catch (ManagerEditorException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
    }

    @objid ("db9505ef-3a47-4abc-b18f-130414bdb363")
    private void setVisibleParent() {
        try {
            this.manager.getCentralManager().getCuadriculaPadre().getParent().setVisible(true);
            this.manager.getStateManager().getTextoActivo().setFoco();
        }
        catch (ManagerEditorException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
    }

    /**
     * Deshace una acción
     */
    @objid ("765b7b11-bbdb-49b0-a58e-0a110979f794")
    public void actionUndo() throws ManagerEditorException {
        try{
            setInvisibleParent();
            this.manager.getHistoryManager().setGuardar(false);
            MathEditor mo = this.manager.getHistoryManager().retroceder();
            ImportMathEditor i = new ImportMathEditor(this.manager);
            this.cuadriculaPadre.vaciar();
            if (mo != null){
                i.loadMathEditor(mo, this.cuadriculaPadre);
                this.manager.getCentralManager().setFocoUltimoComponente(this.cuadriculaPadre);
            }else {
                i.insertarTexto("", this.cuadriculaPadre);
                this.cuadriculaPadre.pack();
            }
            cambiarEstado();
            this.manager.getHistoryManager().setGuardar(true);
            setVisibleParent();
        }catch(EditorException ex){
            try{
                vaciarCuadriculaPadre();
            }catch(EditorException ex2){throw new ManagerEditorException(ex2);}
            
            throw new ManagerEditorException(ex);
        }catch(Exception ex){
            throw new ManagerEditorException("Error desconocido al deshacer una acción.",ex);
        }
    }

    @objid ("b08e779b-f1eb-4175-91d5-b854d00899de")
    private void vaciarCuadriculaPadre() throws ManagerEditorException {
        try{
            this.cuadriculaPadre.vaciar();
            this.manager.getCentralManager().insertarTextoInicial(this.cuadriculaPadre);
            
        }catch(EditorException ex){            
            throw new ManagerEditorException(ex);
        }catch(Exception ex){
            throw new ManagerEditorException("Error al vaciar la cuadrícula padre.",ex);
        }
    }

    /**
     * Activa o desactiva los botones deshacer/rehacer según su estado
     */
    @objid ("dc12cb6f-a90b-4987-a12c-ab9e37f774c3")
    public void cambiarEstado() throws ManagerEditorException {
        //try{
            this.toolItemUndo.setEnabled(this.manager.getHistoryManager().getUndo());
            this.toolItemRedo.setEnabled(this.manager.getHistoryManager().getRedo());
        //}catch(EditorException ex){
            //throw new ManagerEditorException(ex);
        //}
    }

    /**
     * Establece la cuadrícula sobre la que se quiere realizar las acciones.
     * @param cuadriculaPadre Cuadrícula sobre la que se quiere realizar las acciones.
     */
    @objid ("31b68ee9-b439-4e6e-9f4a-5cc27a20ea9c")
    public void setCuadriculaPadre(GridExpression cuadriculaPadre) {
        this.cuadriculaPadre = cuadriculaPadre;
    }

    /**
     * Habilita o deshabilita el botón de eliminar
     * @param habilitado True si existe algún componente seleccionado, false en caso contrario
     */
    @objid ("ebf2e863-ae43-4c11-8811-805bebbbe3e0")
    public void setEstadoBtnEliminar(boolean habilitado) {
        //this.toolItemDelete.setEnabled(habilitado);
    }

    /**
     * Obtiene los ToolItems de los botones de acción (deshacer,rehacer,eliminar,cargar y editar) para gestionar sus Listeners.
     * @param toolItemUndo ToolItem del ToolBar de acciones que al pulsar se encarga de deshacer una acción.
     * @param toolItemRedo ToolItem del ToolBar de acciones que al pulsar se encarga de rehacer una acción.
     * @param toolItemDelete ToolItem del panel de acciones que al pulsar se encarga de eliminar la expresión seleccionada.
     * @param toolItemExport ToolItem del panel de acciones que al pulsar se encarga de exportar la expresión existente en el editor.
     * @param toolItemImport ToolItem del panel de acciones que al pulsar se encarga de importar una expresión.
     */
    @objid ("e3888f0a-a566-46d0-8b83-0ca821ff9f80")
    public void setItems(ToolItem toolItemUndo, ToolItem toolItemRedo, ToolItem toolItemDelete, ToolItem toolItemExport, ToolItem toolItemImport) {
        this.toolItemUndo = toolItemUndo;
        this.toolItemRedo = toolItemRedo;
        //this.toolItemDelete = toolItemDelete;
    }

}
