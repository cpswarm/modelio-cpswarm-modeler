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
package org.modelio.module.cpswarm.type.Abstraction;

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
@objid ("bbfb20d2-9c95-4824-81b3-9e507b786a29")
public class AbstractionImporter {
    @objid ("d2998aee-34ca-4cf4-ab16-fb981c0e3459")
    private IModelingSession modelingSession = CPSWarmModule.getInstance().getModuleContext().getModelingSession();

    @objid ("f3b5ab6b-79c3-4790-80c8-c6cba4b921c0")
    private IUmlModel factory = this.modelingSession.getModel();

    @objid ("5e193094-dbb8-4b27-b0e7-82ca4232bf66")
    private Interface inputInt = null;

    @objid ("ce06252a-a4b8-4eca-bf43-6e9d18ca0dc0")
    private Interface outputInt = null;

    @objid ("6bc799e9-820f-4cc6-bd9b-ea55c35ce6c2")
    private Class fmi = null;

    /**
     * Method ModelDescription
     * @author ebrosse
     */
    @objid ("0bbbcf09-f90b-48a2-9a59-817bee540b80")
    public AbstractionImporter() {
    }

    @objid ("f9629bda-c60c-4611-ae6e-f8c82a3582f3")
    public void importing(NameSpace owner, File file) {
        Abstraction md = null;
        
        try {
            md = loadAbstractionDescription(file);
        
            try{
        
                importAbstractionDescription(owner, md);
        
        
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

    @objid ("1f9dc848-aa9f-4086-b7c3-19c89c9e5bb2")
    private Abstraction loadAbstractionDescription(File file) {
        Abstraction md = null;
        ObjectMapper mapper = new ObjectMapper();
        
        //JSON file to Java object
        try {
            md = mapper.readValue(file, Abstraction.class);
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

    @objid ("3dfc241c-e1ef-4906-b370-9a12c76ef750")
    private void importAbstractionDescription(NameSpace owner, Abstraction md) throws ExtensionNotFoundException {
        Class sensor = this.factory.createClass(md.getSensorsActuators().get(0).getName(), owner);
        
        for (Function function : md.getFunctions()) {
            Operation op = this.factory.createOperation(function.getName(), sensor);
        
        
            for (Input input : function.getApi().getInputs()) {
                Parameter param = this.factory.createParameter();
                param.setComposed(op);
                param.setParameterPassing(PassingMode.IN);
        
                DataType type = this.factory.createDataType(input.getMsgDef().getClass_(), (NameSpace) sensor.getOwner());
                param.setType(type);
        
            }
        
        
            for (Output output : function.getApi().getOutputs()) {
                Parameter param = this.factory.createParameter();
                param.setComposed(op);
                param.setParameterPassing(PassingMode.OUT);
        
                DataType type = this.factory.createDataType(output.getMsgDef().getClass_(), (NameSpace) sensor.getOwner());
                param.setType(type);
        
            }
        
        
        }
    }

}
