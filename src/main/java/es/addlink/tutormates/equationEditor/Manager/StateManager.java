package es.addlink.tutormates.equationEditor.Manager;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Operators.SimpleOperator;

/**
 * Almacena la información sobre la situación del foco en los objetos de tipo Texto.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("e43926ec-bfd4-47f8-8f97-9e09da1df13a")
public class StateManager {
    /**
     * Indica si algún componente se encuentra seleccionado. Por defecto el valor es false.
     */
    @objid ("dac91882-471f-4f37-87eb-c48c1f9a2a93")
    private boolean haySeleccion = false;

    /**
     * Posición del cursor dentro del Texto activo.
     */
    @objid ("0041fd79-5480-4765-9ab3-50d4869d3ae3")
    private int posicionCursor;

    /**
     * Texto que contiene el foco.
     */
    @objid ("435845d2-874c-4d37-91aa-c972ec70b214")
    private SimpleOperator textoActivo;

    @objid ("f9c0e1bb-fda3-4252-9a51-c06eb62d9eec")
    private SimpleOperator textoActivoAnterior;

    @objid ("2ef3ee14-96d4-4604-9485-51be64f44334")
    public StateManager() {
    }

    /**
     * Devuelve true, si algún componente se encuentra seleccionado, false en caso contrario.
     * @return True, si algún componente se encuentra seleccionado, false en caso contrario.
     */
    @objid ("9807669f-a8de-4bd2-9403-267197d12549")
    public boolean getHaySeleccion() {
        return haySeleccion;
    }

    /**
     * Devuelve la posición del cursor dentro del Texto activo.
     * @return posición del cursor dentro del Texto activo.
     */
    @objid ("5e5d1634-5cfa-4968-939f-9bfaf490fe19")
    public int getPosicionCursor() {
        return posicionCursor;
    }

    /**
     * Devuelve el Texto que contiene el foco.
     * @return Texto que contiene el foco.
     */
    @objid ("6f1887b3-08f8-45db-b0fc-139158d65a18")
    public SimpleOperator getTextoActivo() {
        return textoActivo;
    }

    @objid ("c20ff374-f91d-47d5-a618-4db75fa87d7c")
    public SimpleOperator getTextoActivoAnterior() {
        return textoActivoAnterior;
    }

    /**
     * Establece un valor en función de si se ha seleccionado algún componente o no.
     * @param sel valor en función de si se ha seleccionado algún componente o no.
     */
    @objid ("cb1ab5c5-bffe-455f-9373-6256fdf23aa9")
    public void setHaySeleccion(boolean sel) {
        haySeleccion = sel;
    }

    /**
     * Establece la posición del cursor dentro del Texto activo.
     * @param posicionCursor posición del cursor dentro del Texto activo.
     */
    @objid ("0eae6c49-b8a0-4772-8620-748e550dd85e")
    public void setPosicionCursor(int posicionCursor) {
        this.posicionCursor = posicionCursor;
    }

    /**
     * Establece el Texto que contiene el foco.
     * @param t Texto que contiene el foco.
     */
    @objid ("2f5a6116-7c60-4c9e-9f6a-ee14b432e96a")
    public void setTextoActivo(SimpleOperator t) {
        textoActivoAnterior = getTextoActivo();
        textoActivo = t;
    }

}
