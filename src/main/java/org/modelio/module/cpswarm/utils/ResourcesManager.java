/**
 * Java Class : ResourcesManager.java
 *
 * Description :
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *    KIND, either express or implied.  See the License for the
 *    specific language governing permissions and limitations
 *    under the License.
 *
 * @category   Util
 * @package    com.modeliosoft.modelio.sysml.utils
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.cpswarm.utils;

import java.io.File;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;
import org.modelio.api.module.IModule;
import org.modelio.module.cpswarm.impl.CPSWarmModule;

/**
 * This class handles the SysML resources i.e. images, styles, property names, etc.
 * @author ebrosse
 */
@objid ("1a83d8ad-09b2-46e8-9680-a8b4b5f5c4e9")
public class ResourcesManager {
    @objid ("069abdcb-6db8-4e5b-ae96-676dace86190")
    private static final String generatedFolderName = "generated";

    @objid ("d6315d19-ec09-4c49-a55f-a47bd45a4d5c")
    private static final String xmlFolderName = "xml";

    @objid ("923426e4-f122-4250-a96c-e8ca8fa34268")
    private IModule _mdac;

    @objid ("cd0655c9-4520-430e-92c0-7d8f8a70f15e")
    private static ResourcesManager instance = null;

    /**
     * Method ResourcesManager
     * @author ebrosse
     */
    @objid ("ac22a3f6-d64d-46b2-9e34-524a9a5c20ad")
    private ResourcesManager() {
    }

    /**
     * Method getInstance
     * @author ebrosse
     * @return the SysMLResourcesManager instance
     */
    @objid ("3c37ebd2-244f-44ce-84f7-59c612f9e085")
    public static ResourcesManager getInstance() {
        if(instance == null){
            instance =  new ResourcesManager();
        }
        return instance;
    }

    /**
     * This method sets the current module
     * @param module : the current module
     */
    @objid ("924252eb-48c5-423c-9716-04528be077a1")
    public void setJMDAC(IModule module) {
        this._mdac = module;
    }

    /**
     * Method getImage
     * @author ebrosse
     * @param imageName : the name of the image file
     * @return the complete path of the image file
     */
    @objid ("9101eda0-f371-4759-8e54-6d05bfd7e6ee")
    public String getImage(String imageName) {
        return CPSWarmModule.getInstance().getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator + "res" + File.separator + "icons" + File.separator + imageName;
    }

    @objid ("a1fe3d27-a4de-4cc4-9e11-33d8fd75865e")
    public String getRessource(String resName) {
        return CPSWarmModule.getInstance().getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator + "res" + File.separator + resName;
    }

    @objid ("00981700-4601-46fc-b678-efa2ece8c8eb")
    public String getPlugin(String pluginName) {
        return CPSWarmModule.getInstance().getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator + "res" + File.separator + "plugin" + File.separator + pluginName;
    }

    @objid ("778d4f4e-ae3f-41b7-9348-f1a85f575a46")
    public String getGeneratedPath() {
        return CPSWarmModule.getInstance().getModuleContext().getProjectStructure().getPath() + File.separator + generatedFolderName;
    }

    @objid ("ce32509a-2a87-4c72-b006-006c8840cb80")
    public Document getXMLDocument(String fileName) throws FileEditorException {
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(CPSWarmModule.getInstance().getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator + "res" + File.separator + xmlFolderName + File.separator + fileName);
            Document doc = builder.build(xmlFile);
            return doc;
        
        } catch(JDOMParseException e){
            throw new FileEditorException("Existen errores de sintaxis xml en el fichero '" + fileName +"'.",e);
        }catch (JDOMException e) {
            throw new FileEditorException("Se ha producido un error al cargar el fichero '" + fileName +"'.",e);
        }catch (IOException e) {
            throw new FileEditorException("Se ha producido un error al cargar el fichero '" + fileName +"'.",e);
        }catch (Exception e) {
            throw new FileEditorException("Es posible que el nombre del fichero xml '" + fileName + "' no sea el correcto.",e);
        }
    }

}
