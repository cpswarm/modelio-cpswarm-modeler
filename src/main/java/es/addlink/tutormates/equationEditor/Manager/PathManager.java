package es.addlink.tutormates.equationEditor.Manager;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Role.Role;
import es.addlink.tutormates.equationEditor.XMLFiles.LoadXML;

/**
 * Clase que agrupa todas las rutas de ficheros y paquetes.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("a7b7eac7-8310-4efd-9a2f-3cfe30db2bc6")
public class PathManager {
    @objid ("0e07081f-52e9-4439-85a9-cdbe8844c223")
    private static final String commonPath = "es.addlink.tutormates.equationEditor";

    @objid ("f2ada404-6908-40c7-aeb2-5069973b42ab")
    private static final String commonPathWithSlash = "/es/addlink/tutormates/equationEditor";

    @objid ("9ff455ba-227b-4339-b1b8-ce282325d3bc")
    private static final String MathEditorClasses = commonPath + ".MathEditor";

    @objid ("87e7ff20-6533-4c3b-8b0a-065d8995c9ed")
    private static final String AllowedOperators_fileName = "allowedOperators.xml";

    @objid ("ef6f84bd-e15d-42fb-b91c-59ade5007452")
    private static final String ToolBar_fileName = "toolBarBase.xml";

    @objid ("43c62e34-30b7-4caa-bd78-6cfb6c1e613f")
    private static final String Language_fileName = "";

    @objid ("e45cacc1-4e25-418f-b5d2-59b6bb68baff")
    private static final Class<LoadXML> AllowedOperators_class = LoadXML.class;

    @objid ("db96ca7d-ef9b-4658-b804-eb90b714a67a")
    private static final Class<Role> ToolBar_class = Role.class;

//private final static Class<LanguagePackage> Language_class = LanguagePackage.class;
    @objid ("c851d5dc-076c-44c8-af61-6085a6d1adeb")
    public static Class<LoadXML> getAllowedoperatorsClass() {
        return AllowedOperators_class;
    }

    @objid ("5c5ebc9b-8b81-473d-86b4-63aa258da2aa")
    public static Class<Role> getToolbarClass() {
        return ToolBar_class;
    }

/*public static Class<LanguagePackage> getLanguageClass() {
        return Language_class;
    }*/
    @objid ("126eb07f-05ad-4332-b21c-139c0dd8781a")
    public static String getCommonPath() {
        return commonPath;
    }

    @objid ("7ef0c913-4fc7-4f39-88d4-5efe394ec40a")
    public static String getAllowedOperatorsFileName() {
        return AllowedOperators_fileName;
    }

    @objid ("bb974bfa-ef8d-4e32-83c2-746bedad181b")
    public static String getMathEditorClasses() {
        return MathEditorClasses;
    }

    @objid ("fbdf3d4b-b640-4801-b0a7-c6671af32dda")
    public static String getCommonPathWithSlash() {
        return commonPathWithSlash;
    }

    @objid ("902d0243-ffd6-4f17-a8b0-ad6c23848e37")
    public static String getToolBarFileName() {
        return ToolBar_fileName;
    }

    @objid ("400b8ec6-23bb-44d1-98b9-7d407ab25e21")
    public static String getLanguageFileName() {
        return Language_fileName;
    }

}
