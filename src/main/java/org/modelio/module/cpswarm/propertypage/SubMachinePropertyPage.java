/**
 * Java Class : CommonPropertyPage.java
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
 * @package    com.modeliosoft.modelio.sysml.gui.propertypage
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.cpswarm.propertypage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.impl.CPSWarmModule;

/**
 * This class handles the properties common to all SysML stereotypes
 * @author ebrosse
 */
@objid ("e27d0de6-0123-4eba-99e4-9eb5748b219a")
public class SubMachinePropertyPage implements IPropertyContent {
    @objid ("c8dd60fe-5c2b-48aa-a2e4-86c79fea001a")
    private static Collection<StateMachine> _sms = null;

    /**
     * Constructor CommonPropertyPage
     * @author ebrosse
     */
    @objid ("fe54e7f5-3cc9-4b34-ac42-8662eb2c1022")
    public SubMachinePropertyPage() {
    }

    @objid ("d7a21735-2186-45e9-82cd-4e82f186e2a3")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        _sms = CPSWarmModule.getInstance().getModuleContext().getModelingSession().findByClass(StateMachine.class);  
        List<String> opNames = new ArrayList<>();
        for (StateMachine op : _sms) {
            opNames.add(op.getCompositionOwner().getName() + "::" + op.getName());
        }
        
        String value = "";
        if (element instanceof State) {
            State state = (State) element;
            StateMachine sm = state.getSubMachine();
            if (sm != null) {
                value = sm.getName();
            }
        }
        String[] result = (String[])opNames.toArray(new String[opNames.size()]);
        table.addProperty("SubMachine", value, result);
    }

    @objid ("2f8dd8ef-bd71-4a26-b28e-5fcfe9338cdc")
    @Override
    public int changeProperty(ModelElement element, int row, String value) {
        for (StateMachine op : _sms) {
            if (value.equals(op.getCompositionOwner().getName() + "::" + op.getName())) {
                ((State) element).setSubMachine(op);;
            }
        }
        return 1;
    }

}
