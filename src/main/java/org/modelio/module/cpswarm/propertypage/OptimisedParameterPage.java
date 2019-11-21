/**
 * Java Class : OptimisedParameterPropertyPage.java
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
 * @category   PropertyDefinition page
 * @package    org.modelio.module.cpswarm.propertypage
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 **/
package org.modelio.module.cpswarm.propertypage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.api.CPSWarmTagTypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.module.cpswarm.utils.ModelUtils;

/**
 * This class handles the properties related to Optimised Parameter
 * @author ebrosse
 */
@objid ("559b27cd-a74b-4d27-8d46-cc3ae6072483")
public class OptimisedParameterPage implements IPropertyContent {
    /**
     * Constructor OptimisedParameterPage
     * @author ebrosse
     */
    @objid ("e403d007-f3bd-419a-aabe-abd78a5712e7")
    public OptimisedParameterPage() {
    }

    @objid ("56635226-ed1d-4f44-a31a-f11b7f6a6df7")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String value = ModelUtils.getTaggedValue(CPSWarmTagTypes.PROBLEM_NAME, element);    
        table.addProperty("Step", value);
        
        value = ModelUtils.getTaggedValue(CPSWarmTagTypes.PROBLEM_IMAGE, element);
        table.addProperty("Minimal Value", value);
           
        value = ModelUtils.getTaggedValue(CPSWarmTagTypes.PROBLEM_MAXSTEPS, element);
        table.addProperty("Maximal Value", value);
    }

    @objid ("7959d053-c2d8-495f-ae3c-897727a9f656")
    @Override
    public int changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.PROBLEM_NAME, value, element);
        }else if(row == 2){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.PROBLEM_IMAGE, value, element);
        }else if(row == 3){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.PROBLEM_MAXSTEPS, value, element);
        }
        return 3;
    }

}
