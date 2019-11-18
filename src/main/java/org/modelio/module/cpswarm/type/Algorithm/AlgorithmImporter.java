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
package org.modelio.module.cpswarm.type.Algorithm;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.module.cpswarm.impl.CPSWarmModule;

/**
 * This class handles ModelDescription import
 * @author ebrosse
 */
@objid ("65a70ff7-ea09-4a5e-ba59-6301de5a46c6")
public class AlgorithmImporter {
    @objid ("543e519e-3731-4b69-8189-e07eeef09ad1")
    private IModelingSession modelingSession = CPSWarmModule.getInstance().getModuleContext().getModelingSession();

    @objid ("101d152a-3a70-4503-8b13-3c25cd328e20")
    private IUmlModel factory = this.modelingSession.getModel();

    @objid ("850ea514-ee7a-4a6c-991d-8ddec052a4f7")
    private Class fmi = null;

    @objid ("c421309d-0b6e-41f9-ae3d-6be803fa3420")
    private Interface inputInt = null;

    @objid ("4c5f7f6b-9fa7-4c0a-920e-646897cd8baf")
    private Interface outputInt = null;

    /**
     * Method ModelDescription
     * @author ebrosse
     */
    @objid ("04f62269-0a88-4890-a888-d846a3755342")
    public AlgorithmImporter() {
    }

    @objid ("126f00c7-fcaa-4b40-adb3-2472c0f00321")
    public void importing(Component owner, File file) {
        Algorithm md = null;
        
        try {
            md = loadAlgorithmDescription(file);
        
            try{
        
                importAlgorithmDescription(owner, md);
        
        
            } catch (ExtensionNotFoundException e) {
                CPSWarmModule.logService.error(e);
            }
        
            //            ModelStructure ms = md.getModelStructure();
            //            if (ms != null){
            //                Fmi2VariableDependency vd = ms.getOutputs();
            //                List<Unknown> unknows = vd.getUnknown();
            //                for (Unknown unknow : unknows){
            //                    List<Fmi2ScalarVariable> scvs = md.getModelVariables().getScalarVariable();
            //                    try{
            //                        ModelElement destination = this.svs.get(scvs.get(((int) unknow.getIndex()) - 1 ));
            //                        IUmlModel model = INTOCPSModule.getInstance().getModuleContext().getModelingSession().getModel();
            //
            //                        for (long input : unknow.getDependencies() ){
            //                            ModelElement source = this.svs.get(scvs.get(((int)input) - 1 ));
            //                            try {
            //                                model.createDependency(source, destination, IINTOCPSPeerModule.MODULE_NAME, INTOCPSStereotypes.DEPENDS);
            //                            } catch (ExtensionNotFoundException e) {
            //                                CPSWarmModule.logService.error(e);
            //                            }
            //                        }
            //                    }catch (IndexOutOfBoundsException e){
            //                        CPSWarmModule.logService.error(e);
            //                    }
            //                }
            //            }
        } catch (Exception e) {
            CPSWarmModule.logService.error(e);
        }
    }

    @objid ("d634936b-e066-407d-8864-9c50746c1b07")
    private Algorithm loadAlgorithmDescription(File file) {
        Algorithm md = null;
        ObjectMapper mapper = new ObjectMapper();
        
        //JSON file to Java object
               try {
        md = mapper.readValue(file, Algorithm.class);
            } catch (JsonParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
            } catch (JsonMappingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
            } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
            }
        //        JAXBContext jaxbContext = JAXBContext.newInstance(Algorithm.class);
        //        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        //        md = (Algorithm) jaxbUnmarshaller.unmarshal(file);
        return md;
    }

    @objid ("d358d542-6b6f-4417-b36e-ad7c595515e1")
    private void importAlgorithmDescription(Component owner, Algorithm md) throws ExtensionNotFoundException {
        for (Function function : md.getFunctions()) {
            Operation op = this.factory.createOperation(function.getName(), owner);
            
            
            for (Input input : function.getApi().getInputs()) {
                Parameter param = this.factory.createParameter();
                param.setComposed(op);
                param.setParameterPassing(PassingMode.IN);
                
                DataType type = this.factory.createDataType(input.getMsgDef().getClass_(), (NameSpace) owner.getOwner());
                param.setType(type);
                
            }
            
            
            for (Output output : function.getApi().getOutputs()) {
                Parameter param = this.factory.createParameter();
                param.setComposed(op);
                param.setParameterPassing(PassingMode.OUT);
                
                DataType type = this.factory.createDataType(output.getMsgDef().getClass_(), (NameSpace) owner.getOwner());
                param.setType(type);
                
            }
            
         
        //                Parameter param = this.factory.createParameter();
        //                param.setReturned(op);
        //                param.setName(function.getApi().get
        //                
        //                this.factory.createDataType(output.getMsgDef().getClass().getName(), (NameSpace) owner.getOwner());
                
                }
    }

}
