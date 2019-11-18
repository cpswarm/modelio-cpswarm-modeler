package es.addlink.tutormates.equationEditor.Role;

import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Esta clase contiene la configuración global que se debe cargar.
 * Dependiendo de si es un alumno o profesor, curso o tema, contendrá diferentes configuraciones para los siguientes aspectos:
 * - ToolBar de botones.
 * - Operadores, variables y funciones disponibles para el usuario.
 * 
 * Se puede ver el diagrama de clases en el package UML.
 */
@objid ("bc35a9b5-503f-4916-b945-d8766e5ac6ff")
public class Role {
    /**
     * Curso: 1ESO, 2ESO, etc.
     */
    @objid ("3d879e03-4430-41f9-8fcc-563a7b3583bf")
    private String course;

    /**
     * Perfil del usuario, es decir, si es un alumno o un profesor.
     */
    @objid ("e01eaf56-0a03-487d-b766-820a27db3943")
    private String profile;

    /**
     * Tema. Ejemplo de un tema: ENT_N1.
     */
    @objid ("4eff984c-d624-4b35-a64b-70c914e97ec5")
    private String unit;

    /**
     * Combinación de pestañas y botones en cada una que se cargará en la interfaz de usuario.
     */
    @objid ("77797abe-3cee-4a66-a301-7b81760f8628")
    private List<TabToolBar> listTabToolBar;

    /**
     * Listado de funciones (sin, cos, ...) que el editor reconoce para realizar las operaciones de exportación y/o importación.
     */
    @objid ("9495b6b9-0d9b-4f4f-8a74-e483839db76c")
    private List<BaseFunction> listAllowedFunction;

    /**
     * Listado de variables y números (enteros y reales) que el editor reconoce para realizar las operaciones de exportación y/o importación.
     */
    @objid ("02785ea0-5465-44b5-95ae-59f9debf7740")
    private List<BaseOperator> listAllowedNumbersVariables;

    /**
     * Listado de operadores que el editor reconoce para realizar las operaciones de exportación y/o importación.
     */
    @objid ("309d64a6-f879-43a7-ba92-ead786405c19")
    private List<BaseOperator> listAllowedOperator;

    /**
     * Constructor.
     * @param profile Perfil del usuario: alumno, profesor o vacío.
     * @param course Curso: 1ESO, 2ESO, etc o vacío.
     * @param unit Tema: ENT_N1, ... o vacío.
     * @param listAllowedOperator Listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
     * @param listAllowedFunction Listado de variables reconocibles para realizar las operaciones de exportación y/o importación.
     * @param listAllowedNumbersVariables Listado de funciones reconocibles para realizar las operaciones de exportación y/o importación.
     * @param listTabToolBar Combinación de pestañas y botones en cada una que se cargará en la interfaz de usuario.
     */
    @objid ("56293f64-6716-4880-ae03-541bc0d383a3")
    public Role(String profile, String course, String unit, List<BaseOperator> listAllowedOperator, List<BaseFunction> listAllowedFunction, List<BaseOperator> listAllowedNumbersVariables, List<TabToolBar> listTabToolBar) {
        super();
        this.profile = profile;
        this.course = course;
        this.unit = unit;
        this.listAllowedOperator = listAllowedOperator;
        this.listAllowedFunction = listAllowedFunction;
        this.listAllowedNumbersVariables = listAllowedNumbersVariables;
        this.listTabToolBar = listTabToolBar;
    }

    /**
     * Devuelve el curso que utiliza este rol.
     * @return Curso que utiliza este rol.
     */
    @objid ("b65239bd-d814-4c96-b9bf-72121e50ba47")
    public String getCourse() {
        return course;
    }

    /**
     * Devuelve el listado de funciones reconocibles para realizar las operaciones de exportación y/o importación.
     * @return Listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
     */
    @objid ("cf04b96a-1d9d-4c24-9a12-678fdf609baf")
    public List<BaseFunction> getListAllowedFunction() {
        return listAllowedFunction;
    }

    /**
     * Devuelve el listado de variables reconocibles para realizar las operaciones de exportación y/o importación.
     * @return Listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
     */
    @objid ("ec8451df-891c-48e1-9bc1-45c422ade35f")
    public List<BaseOperator> getListAllowedNumbersVariables() {
        return listAllowedNumbersVariables;
    }

    /**
     * Devuelve el listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
     * @return Listado de operadores reconocibles para realizar las operaciones de exportación y/o importación.
     */
    @objid ("d9c10713-8cfe-41e0-95a5-847ed57336ad")
    public List<BaseOperator> getListAllowedOperator() {
        return listAllowedOperator;
    }

    /**
     * Devuelve el listado de combinación de pestañas y botones en cada una que se cargará en la interfaz de usuario.
     * @return Listado de combinación de pestañas y botones en cada una que se cargará en la interfaz de usuario.
     */
    @objid ("1b2d48bc-2b11-46ff-bf92-41afdcd982cb")
    public List<TabToolBar> getListTabToolBar() {
        return listTabToolBar;
    }

    /**
     * Devuelve el perfil que utiliza este rol.
     * @return Perfil que utiliza este rol.
     */
    @objid ("0a22d77e-f732-43a3-965c-ff286a6f3852")
    public String getProfile() {
        return profile;
    }

    /**
     * Devuelve el tema que utiliza este rol.
     * @return Tema que utiliza este rol.
     */
    @objid ("301ef3b0-26f1-4775-beb7-3129089bac1f")
    public String getUnit() {
        return unit;
    }

    @objid ("857680b5-2001-47b4-b93f-426a37ca98f2")
    @Override
    public String toString() {
        String str="";
        
        str += " _____________________________________________________________________________\n|\n";
        str += "| ROL - " + this.profile + " ~ " + this.course + " ~ " + this.unit + "\n|";
        str += "\n|   ·Operators:\n|";
        str += "\n|   ·Functions:\n|";
        str += "\n|   ·Tab folders:\n|";
            Iterator<TabToolBar> ite = listTabToolBar.iterator();
            while(ite.hasNext()){
                TabToolBar t = (TabToolBar)ite.next();
                str += "       > " + t + "\n|";
            }
        
        str += "______________________________________________________________________________";
        return str;
    }

}
