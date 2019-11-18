package es.addlink.tutormates.equationEditor.Manager;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.MathEditor.MathEditor;
import es.addlink.tutormates.equationEditor.Operators.GridExpression;
import es.addlink.tutormates.equationEditor.Translation.ExportMathEditor;

/**
 * Clase que almacena las modificaciones producidas durante la edición de las expresiones, para dotar al editor de las funciones Deshacer y Rehacer.
 * 
 * @author Nuria García, Ignacio Celaya Sesma
 */
@objid ("daadb4c0-4827-4d9c-8683-4bb7d726c7c5")
public class HistoryManager {
    @objid ("59b5b947-cedc-4111-b031-4313c29eb4f7")
    private final int max = 50;

    /**
     * Determina si hay que guardar estado o no.
     */
    @objid ("d7773c72-eada-46c0-8de8-94ffa62d413a")
    private boolean guardar;

    /**
     * Para saber si deben estar activados los botones deshacer/rehacer.
     */
    @objid ("d949eb01-b42d-4585-b91b-9ee4de43b6aa")
    private boolean on_redo;

    /**
     * Para saber si deben estar activados los botones deshacer/rehacer.
     */
    @objid ("0f54b789-94c1-423d-b5e5-bdc27229c15d")
    private boolean on_undo;

    /**
     * Entero que señalará en la lista anterior la posición del estado actual.
     */
    @objid ("30777cbe-58e3-4475-bf8b-7deff3553443")
    private int posHistorial;

    /**
     * Capa sobre la que actúa el guardado del historial.
     */
    @objid ("fe61835a-d6e8-4140-858a-ae565cc07c65")
    private GridExpression cap;

    /**
     * Lista que guarda los cambios en el editor.
     */
    @objid ("c6a8dcca-eaef-4d5a-b759-1f0eb8ce4bf5")
    private List<MathEditor> listaHistorial;

    @objid ("921e60c5-014b-4b1d-81ca-2b8f37652d7b")
    private Manager manager;

    /**
     * Constructor - privado (singleton)
     * @throws ManagerEditorException
     */
    @objid ("1c492aaa-1e14-4faf-9ae2-6ea06428f544")
    public HistoryManager(Manager manager) {
        try{
            this.manager = manager;
            this.listaHistorial = new ArrayList<MathEditor>();
            this.posHistorial = -1;
            this.cap = null;
            
            if(this.manager.getTutorMatesEditor().getCorrectExpresion())
                this.guardar = true;
            else this.guardar = false;
            this.on_undo = false;
            this.on_redo = false;
            
        }catch(Exception ex){
            //ex.printStackTrace();
        }
    }

    /**
     * Rehace acciones en el editor.
     * @throws ManagerEditorException
     * @return MathEditor con el nuevo objeto a insertar.
     */
    @objid ("775be1ee-0c7c-4508-ad4d-640a0d4e1c1a")
    public MathEditor avanzar() throws ManagerEditorException {
        try{
            if (posHistorial < listaHistorial.size() - 1) { // Si se puede rehacer
                posHistorial++; // Incrementamos la posición en la lista
                on_undo = true;
                if (posHistorial == listaHistorial.size() - 1) { // Si alcanzamos el límite
                    on_redo = false; // Desactivamos el botón rehacer
                }
                return listaHistorial.get(posHistorial); // Cogemos el MathEditor de la lista
            }
            return listaHistorial.get(listaHistorial.size() - 1);
            
        }catch(Exception ex){
            throw new ManagerEditorException("Error al avanzar una acción en la lista del historial.",ex);
        }
    }

    /**
     * Elimina el historial almacenado.
     */
    @objid ("40e2b7a0-fcd2-4960-9576-f8143f66addd")
    public void cleanHistory() {
        listaHistorial.clear();
    }

    /**
     * Devuelve true si está activado el guardado automático, false en caso contrario.
     * @return True si está activado el guardado automático, false en caso contrario.
     */
    @objid ("f4ebfa3e-7167-42f5-8e63-ce1ab0d22b89")
    public boolean getGuardar() {
        return guardar;
    }

    /**
     * Devuelve true si el botón de rehacer debe estar activado, false en caso contrario.
     * @return True si el botón de rehacer debe estar activado, false en caso contrario.
     */
    @objid ("96f5fc07-5212-43a6-9fd3-46458bf3bab3")
    public boolean getRedo() {
        if(this.manager.getTutorMatesEditor().getCorrectExpresion())
            return on_redo;
        else
            return false;
    }

    /**
     * Devuelve true si el botón de deshacer debe estar activado, false en caso contrario.
     * @return True si el botón de deshacer debe estar activado, false en caso contrario.
     */
    @objid ("cd06da0a-a2b6-4ef1-a8f3-b12246f951aa")
    public boolean getUndo() {
        if(this.manager.getTutorMatesEditor().getCorrectExpresion())
            return on_undo;
        else
            return false;
    }

    /**
     * Almacena el historial de cambios llevados a cabo en el editor.
     * @throws ManagerEditorException
     */
    @objid ("c8415b6d-e937-4a82-9583-31201d21a7c4")
    public void guardarHistorial(boolean borrado) {
        try {            
        
            if (posHistorial == -1) {
                cleanHistory();
            }
            for (int i = posHistorial + 1; i < listaHistorial.size(); i++) {
                listaHistorial.remove(i);
            }
            if (listaHistorial.size() == max) {
                listaHistorial.remove(0);
            }
            
            //si borrado es true, guardamos un null en Historial.
            if(borrado){
                
                boolean eq = false;
                MathEditor mo = null;
                if (!listaHistorial.isEmpty()) {
                    if(listaHistorial.get(listaHistorial.size() - 1) == null)
                        eq = true;
                }
                if (!eq) {
                    listaHistorial.add(mo); // Almacenamos el MathEditor en la lista
                    posHistorial = listaHistorial.size() - 1; // Actualizamos el valor del atributo "posHistorial"
                }
            }else{
                // Creamos el MathEditor
                MathEditor mo;
                ExportMathEditor out = new ExportMathEditor(this.manager);
                mo = out.getMathEditorCargarHistorial(cap);
                
                boolean eq = false;
                if (!listaHistorial.isEmpty()) {
                    eq = MathEditor.equals(mo, listaHistorial.get(listaHistorial.size() - 1));
                    
                }
                
                if(mo != null){
                    if(this.manager.getTutorMatesEditor().getCorrectExpresion())
                        this.manager.getActionsToolBar().getExportToolItem().setEnabled(true);
                }else{
                    if(this.manager.getTutorMatesEditor().getCorrectExpresion())
                        this.manager.getActionsToolBar().getExportToolItem().setEnabled(false);
                }
                
                if (mo != null && !eq) {
                    listaHistorial.add(mo); // Almacenamos el MathEditor en la lista
                    posHistorial = listaHistorial.size() - 1; // Actualizamos el valor del atributo "posHistorial"
                }
            }
            
        }catch(EditorException ex){
            //ex.showExtendedError();
        }catch(Exception ex){
            //ExportTranslationEditorException e =  new ExportTranslationEditorException("Error desconocido al guardar el historial.",ex);
            //e.showExtendedError();
        }
        
        on_undo = true;
        on_redo = false;
    }

    /**
     * Deshace acciones en el editor.
     * @throws ManagerEditorException
     * @return MathEditor con el nuevo objeto a insertar.
     */
    @objid ("6329c61d-b44c-45f0-a044-7e65eaaa5081")
    public MathEditor retroceder() throws ManagerEditorException {
        try{
            if (posHistorial > -1) { // En caso de que se pueda deshacer. (Valores inferiores a cero: Editor en blanco)
                posHistorial--; // Decrementamos la posición en la lista
                on_redo = true;
                if (posHistorial == -1) {
                    on_undo = false;
                }
                else {
                    return listaHistorial.get(posHistorial); // Cogemos el MathEditor de la lista
                }
            }
            return null;
            
        }catch(Exception ex){
            throw new ManagerEditorException("Error al avanzar una acción en la lista del historial.",ex);
        }
    }

    /**
     * Establece la cuadrícula sobre la que se quiere llevar a cabo las acciones.
     * @param c Cuadrícula sobre la que se quiere llevar a cabo las acciones.
     */
    @objid ("5ded834a-abd4-4435-94f0-43259e73cc51")
    public void setCuadricula(GridExpression c) {
        cap = c;
    }

    /**
     * Activa o desactiva el guardado automático.
     * @param g True para activar el guardado automático, false en caso contrario.
     */
    @objid ("c3b142bd-859c-401e-b462-142916179f48")
    public void setGuardar(boolean g) {
        guardar = g;
    }

}
