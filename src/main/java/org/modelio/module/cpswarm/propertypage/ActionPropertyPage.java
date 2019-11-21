/**
 * Java Class : ActionPropertyPage.java
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.module.cpswarm.impl.CPSWarmModule;

/**
 * This class handles the properties related to Action
 * @author ebrosse
 */
@objid ("9c1f12b4-52ec-4fb3-9686-b14c44178d90")
public class ActionPropertyPage implements IPropertyContent {
    @objid ("fdb1b733-0109-4dc1-892a-cdf5203d8e91")
    private static Collection<Operation> _operations = null;

    @objid ("0a9c5955-6a31-453b-a7b9-6b570922e98b")
    private static Operation _op = null;

    /**
     * Constructor CommonPropertyPage
     * @author ebrosse
     */
    @objid ("b7371215-9679-4d9c-be75-5751a5c4124d")
    public ActionPropertyPage() {
    }

    @objid ("ef61140f-c2e1-4f02-892b-3ac9e4dfbd3d")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        _operations = CPSWarmModule.getInstance().getModuleContext().getModelingSession().findByClass(Operation.class);  
        List<String> opNames = new ArrayList<>();
        for (Operation op : _operations) {
            opNames.add(op.getCompositionOwner().getName() + "::" + op.getName());
        }
        
        String value = "";
        if (element instanceof State) {
            State state = (State) element;
            InternalTransition internal = state.getInternal().get(0);
            _op = internal.getProcessed();
            if (_op != null) {
                value = _op.getName();
            }
        }
        String[] result = (String[])opNames.toArray(new String[opNames.size()]);
        table.addProperty("Function", value, result);
        
        //functionsProperties
        if (_op != null) {
            for (Parameter param : _op.getIO()) {
                table.addProperty(_op.getName() + "." + param.getName(), "");
            }
        }
    }

    @objid ("7d929c31-f91d-4bbe-a663-3601601e17d4")
    @Override
    public int changeProperty(ModelElement element, int row, String value) {
        for (Operation op : _operations) {
            if (value.equals(op.getCompositionOwner().getName() + "::" + op.getName())) {
                ((State) element).getInternal().get(0).setProcessed(op);
                break;
            }
        }
        return 1;
    }

}
