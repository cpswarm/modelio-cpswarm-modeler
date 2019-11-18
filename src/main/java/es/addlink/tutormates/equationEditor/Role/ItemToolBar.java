package es.addlink.tutormates.equationEditor.Role;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;

/**
 * Clase que representa a un botón de la paleta de operadores.
 * Siempre va dentro de la clase TabToolBar.
 */
@objid ("86ccf022-06cb-4e0b-98c7-5115216de012")
public class ItemToolBar {
    /**
     * Indica si el botón se encuentra habilitado o deshabilitado.
     */
    @objid ("367ba889-601a-4518-85cd-e5c828101ddc")
    private Boolean enabled;

    /**
     * True si va a implicar una inserción de texto, por ejemplo: "+", "4", "a", false si no implica a texto, por ejemplo: raíz cuadrada, fracción.
     */
    @objid ("de14f026-e816-425d-b4bd-49a6032d57b1")
    private Boolean isText;

    /**
     * Nombre identificativo.
     */
    @objid ("072a9c41-6030-44fc-9ca5-18f57485440c")
    private String name;

    /**
     * Operador que se introduce en el editor, si es de tipo texto, se introducirá ese mismo, si no, será una referencia a un operador complejo.
     */
    @objid ("c12c0e92-39da-41fe-b5d6-7fb60257f108")
    private String operator;

    /**
     * Combinación de teclas para simular su pulsación. Este campo solo sirve para el toolTip, la configuración real se realiza en la clase SimpleOperatorManager.java.
     */
    @objid ("32c607bf-2ff2-46ef-9125-8a62cff5897a")
    private String shortcut;

    /**
     * Tooltip que se muestra. Ya se encuentra en el idioma correcto.
     */
    @objid ("c874c4ed-33ec-40c0-975d-e29548d02754")
    private String tooltip;

    /**
     * Indica si el botón se muestra o no en la paleta de operadores.
     */
    @objid ("aeadb1e6-5f03-4b8b-a8fd-2174990a3648")
    private Boolean visible;

    /**
     * Icono del botón.
     */
    @objid ("4bdfad08-f795-4373-93c4-83ab7e462f67")
    private Image icon;

    /**
     * Constructor.
     * @param icon Icono
     * @param isText Es un operador simple o complejo.
     * @param operator Cadena que se inserta si es de tipo texto o referencia en caso de ser operador complejo.
     * @param name Nombre identificativo.
     * @param shortcut Combinación de teclas que se muestra en el tooltip. No participa en la configuración de los listeners.
     * @param tooltip Tooltip en el idioma correcto.
     * @param visible Indica si el botón se muestra o no en la paleta de operadores.
     * @param enabled Indica si el botón se encuentra habilitado o deshabilitado.
     */
    @objid ("313b79b5-52d8-43ab-a311-e26c1767a882")
    public ItemToolBar(Image icon, Boolean isText, String operator, String name, String shortcut, String tooltip, Boolean visible, Boolean enabled) {
        super();
        this.icon = icon;
        this.isText = isText;
        this.operator = operator;
        this.name = name;
        this.shortcut = shortcut;
        this.tooltip = tooltip;
        this.visible = visible;
        this.enabled = enabled;
    }

    /**
     * Devuelve el icono del botón.
     * @return Icono del botón.
     */
    @objid ("245a1d7d-3283-4021-a17c-0cae9147677d")
    public Image getIcon() {
        return icon;
    }

    /**
     * Devuelve el nombre identificativo.
     * @return Nombre identificativo.
     */
    @objid ("6c8c4633-8e41-4b03-8669-2be34516d026")
    public String getName() {
        return name;
    }

    /**
     * Devuelve el operador que se introduce en el editor, si es de tipo texto, se introducirá ese mismo, si no, será una referencia a un operador complejo.
     * @return Operador que se introduce en el editor, si es de tipo texto, se introducirá ese mismo, si no, será una referencia a un operador complejo.
     */
    @objid ("6051a863-4356-4335-b9ad-b4406207d47f")
    public String getOperator() {
        return operator;
    }

    /**
     * Devuelve la combinación de teclas que se muestra en el tooltip. No participa en la configuración de los listeners.
     * @return Combinación de teclas que se muestra en el tooltip. No participa en la configuración de los listeners.
     */
    @objid ("d958ef0e-2071-4f2b-b1d3-f00c175f7b4d")
    public String getShortcut() {
        return shortcut;
    }

    /**
     * Devuelve el tooltip en el idioma correcto.
     * @return Tooltip en el idioma correcto.
     */
    @objid ("082fd85d-f007-475b-ae77-e5487a8afbec")
    public String getTooltip() {
        return tooltip;
    }

    /**
     * Devuelve true si el botón se encuentra habilitado, false en caso contrario.
     * @return True si el botón se encuentra habilitado, false en caso contrario.
     */
    @objid ("4f1ffc80-6d5b-4173-bb10-6edb9153c3c2")
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Devuelve true si es un operador simple, false si es complejo.
     * @return True si es un operador simple, false si es complejo.
     */
    @objid ("246c4ab4-eed1-4c3e-bb7b-db919ade53f4")
    public Boolean isText() {
        return isText;
    }

    /**
     * Devuelve true si el botón se muestra en la paleta de operadores o false en caso contrario.
     * @return True si el botón se muestra en la paleta de operadores o false en caso contrario.
     */
    @objid ("83ea1ca9-3adc-4f8c-accf-e2382307abfa")
    public Boolean isVisible() {
        return visible;
    }

    @objid ("f618e0a6-b093-4ec3-a184-672dd3d39d39")
    @Override
    public String toString() {
        return this.name;
    }

}
