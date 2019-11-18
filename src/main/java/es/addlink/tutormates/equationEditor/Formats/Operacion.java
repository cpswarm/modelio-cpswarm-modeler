package es.addlink.tutormates.equationEditor.Formats;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Clase que almacena los datos de una sola operación.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("26627ad9-8841-4c2a-a114-f7e5f0ba0e85")
public class Operacion {
    /**
     * Identificador único.
     */
    @objid ("dbd2396f-ff66-4764-875d-a90b17d171e2")
    private int id;

    /**
     * Código MathML asociado a la operación.
     */
    @objid ("d9d66b5d-188b-4362-afe4-0952f0d4f80d")
    private String mathML;

    /**
     * Nombre de la operación.
     */
    @objid ("f74e1d7e-7ee9-4566-b0f3-871c3d823589")
    private String nombre;

    /**
     * Símbolo con el que aparece en la expresión del editor.
     */
    @objid ("8b7e38ed-a35d-4255-bef4-a361d88976ec")
    private String simbolo;

    /**
     * Tipo de la operación.
     */
    @objid ("6bd28647-2a82-4f72-89b9-2cbd3cfc183b")
    private String tipo;

    @objid ("2d6f175d-a66f-4460-a99b-84c21ef1b5e9")
    private Boolean sePermiteSeguidos;

    /**
     * Constructor.
     * @param id Identificador único.
     * @param simbolo Símbolo con el que aparece en la expresión del editor.
     * @param nombre Nombre de la operación.
     * @param mathML Código MathML asociado a la operación.
     * @param tipo Tipo de la operación.
     */
    @objid ("f1e6a37c-4dfc-4931-89c9-e47cf5063976")
    public Operacion(int id, String simbolo, String nombre, String mathML, String tipo, Boolean sePermiteSeguidos) {
        this.id = id;
        this.simbolo = simbolo;
        this.nombre = nombre;
        this.mathML = mathML;
        this.tipo = tipo;
        this.sePermiteSeguidos = sePermiteSeguidos;
    }

    /**
     * Devuelve el identificador de la operación.
     * @return El identificador de la operación.
     */
    @objid ("304a7d5e-5c19-4e00-9e74-9a1f3cb3bddf")
    public int getId() {
        return id;
    }

    /**
     * Devuelve el código MathML asociado a la operación.
     * @return El código MathML asociado a la operación.
     */
    @objid ("ad96542c-4de6-4537-8dfe-5234e7a2ab04")
    public String getMathML() {
        return mathML;
    }

    /**
     * Devuelve el nombre de la operación.
     * @return El nombre de la operación.
     */
    @objid ("da8fdf67-7c1d-4c4d-b0a6-f46184cb9325")
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el símbolo de la operación.
     * @return the simbolo
     */
    @objid ("dba21b8f-d569-4304-a55d-3dac2703e9f9")
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Devuelve el tipo de la operación.
     * @return El tipo de la operación.
     */
    @objid ("b2ed7e1e-4d9e-40e2-b1ce-5ad11b1e9a50")
    public String getTipo() {
        return tipo;
    }

    @objid ("ac51528b-545b-4c95-bea2-2817ae02e1e6")
    public Boolean getSePermiteSeguidos() {
        return this.sePermiteSeguidos;
    }

/*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @objid ("de6dd1e9-2e1a-4b1f-8ba4-25c3490cd6d9")
    @Override
    public String toString() {
        String cad;
        cad = id + "|" + tipo + "|" + simbolo + "|" + nombre + "|" + mathML;
        return cad;
    }

}
