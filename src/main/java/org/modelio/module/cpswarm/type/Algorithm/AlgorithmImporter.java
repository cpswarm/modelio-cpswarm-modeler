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
 *
 */
public class AlgorithmImporter {

    private IModelingSession modelingSession = CPSWarmModule.getInstance().getModuleContext().getModelingSession();

    private IUmlModel factory = this.modelingSession.getModel();


    private Class fmi = null;

    private Interface inputInt = null;

    private Interface outputInt = null;


    /**
     * Method ModelDescription
     * @author ebrosse
     */

    public AlgorithmImporter() {
    }


    public void importing(Component owner, File file){

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

    private void importAlgorithmDescription(Component owner, Algorithm md)
            throws ExtensionNotFoundException {

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
