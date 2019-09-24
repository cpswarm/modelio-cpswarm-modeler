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
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;
import org.modelio.api.module.IModule;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;


/**
 * This class handles the SysML resources i.e. images, styles, property names, etc.
 * @author ebrosse
 *
 */
public class ResourcesManager {

    private static final String generatedFolderName = "generated";

    private static final String xmlFolderName = "xml";

    private static ResourcesManager instance = null;

    private IModule _mdac;

    /**
     * Method ResourcesManager
     * @author ebrosse
     */

    private ResourcesManager() {
    }

    /**
     * Method getInstance
     * @author ebrosse
     * @return the SysMLResourcesManager instance
     */

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
    public void setJMDAC(IModule module) {
        this._mdac = module;
    }

    /**
     * Method getImage
     * @author ebrosse
     * @param imageName : the name of the image file
     * @return the complete path of the image file
     */
    public String getImage(String imageName) {
        return CPSWarmModule.getInstance().getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator + "res" + File.separator + "icons" + File.separator + imageName;
    }

    public String getRessource(String resName) {
        return CPSWarmModule.getInstance().getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator + "res" + File.separator + resName;
    }

    public String getPlugin(String pluginName) {
        return CPSWarmModule.getInstance().getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator + "res" + File.separator + "plugin" + File.separator + pluginName;
    }


    public String getGeneratedPath(){
        return CPSWarmModule.getInstance().getModuleContext().getProjectStructure().getPath() + File.separator + generatedFolderName;
    }



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
