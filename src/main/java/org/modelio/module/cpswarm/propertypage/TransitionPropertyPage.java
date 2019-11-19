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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.module.cpswarm.impl.CPSWarmModule;

/**
 * This class handles the properties common to all SysML stereotypes
 * @author ebrosse
 */
@objid ("315fcc63-2310-4b95-95b4-6e5903239a35")
public class TransitionPropertyPage implements IPropertyContent {
    @objid ("93a5aa5a-3ec3-4152-a5ae-3c814a2a9355")
    private static Collection<Event> _events = null;

    @objid ("81bc7fbc-4134-4a74-9bde-df196d1a0c31")
    private static Collection<Signal> _signals = null;

    @objid ("494957b6-70ba-48db-8d0f-1ff6f7a1f678")
    private static Signal _sigModel = null;

    /**
     * Constructor TransitionPropertyPage
     * @author ebrosse
     */
    @objid ("69038a46-91cd-41f9-892b-a05ea9d62b02")
    public TransitionPropertyPage() {
    }

    @objid ("db7a137d-5f7f-4df9-8626-6dfe78dba76e")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        //Events
        _events = CPSWarmModule.getInstance().getModuleContext().getModelingSession().findByClass(Event.class);  
        List<String> opNames = new ArrayList<>();
        for (Event op : _events) {
            opNames.add(op.getCompositionOwner().getName() + "::" + op.getName());
        }
        
        String value = "";
        if (element instanceof Transition) {
            Transition tran = (Transition) element;
            Event process = tran.getTrigger();
            if (process != null) {
                value = process.getName();
            }
        }
        String[] result = (String[])opNames.toArray(new String[opNames.size()]);
        table.addProperty("Event", value, result);
        
        //Signals
        _signals = CPSWarmModule.getInstance().getModuleContext().getModelingSession().findByClass(Signal.class);  
        List<String> sigNames = new ArrayList<>();
        for (Signal sig : _signals) {
            sigNames.add(sig.getCompositionOwner().getName() + "::" + sig.getName());
        }
        
        String sigvalue = "";
        if (element instanceof Transition) {
            Transition tran = (Transition) element;
            Event process = tran.getTrigger();
            if (process != null) {
                _sigModel = process.getModel();
                if (_sigModel != null) {
                    sigvalue = _sigModel.getName();
                }
            }
        }
        String[] sigresult = (String[]) sigNames.toArray(new String[sigNames.size()]);
        table.addProperty("Signal", sigvalue, sigresult);
        
        //SignalProperties
        if (_sigModel != null) {
            for (Attribute att : _sigModel.getOwnedAttribute()) {
                table.addProperty(_sigModel.getName() + "." + att.getName(), att.getValue());
            }
        }
    }

    @objid ("a92a371c-b88f-4d37-9394-f0015f459edd")
    @Override
    public int changeProperty(ModelElement element, int row, String value) {
        if (row == 1) {
            for (Event op : _events) {
                if (value.equals(op.getCompositionOwner().getName() + "::" + op.getName())) {
                    ((Transition) element).setTrigger(op);
                    break;
                }
            }
        }else  if (row == 2) {
            for (Signal sig : _signals) {
                if (value.equals(sig.getCompositionOwner().getName() + "::" + sig.getName())) {
                    ((Transition) element).getTrigger().setModel(sig);
                    break;
                }
            }
        }else {
            row = row - 3;
            Attribute att = _sigModel.getOwnedAttribute().get(row);
            att.setValue(value);
        }
        return 2;
    }

}
