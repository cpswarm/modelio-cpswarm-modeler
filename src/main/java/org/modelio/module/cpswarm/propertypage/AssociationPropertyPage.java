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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.AssociationEnd;

/**
 * This class handles the properties common to all SysML stereotypes
 * @author ebrosse
 */
@objid ("0b840cfb-9b4e-46ed-b684-74dfd62c7ceb")
public class AssociationPropertyPage implements IPropertyContent {
    /**
     * Constructor CommonPropertyPage
     * @author ebrosse
     */
    @objid ("c9a0a5cd-7252-4bd1-b0a6-abce80ba61d6")
    public AssociationPropertyPage() {
    }

    @objid ("19ec8c08-a242-4ee1-9f22-13a29630f589")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        AssociationEnd end = (AssociationEnd) element;
        if (end.getAggregation().equals(AggregationKind.KINDISAGGREGATION))
            end = end.getOpposite();
            
        table.addProperty("Number", end.getMultiplicityMax());
    }

    @objid ("d5a73b75-a4a1-433b-84b8-5a9e4d753f2c")
    @Override
    public int changeProperty(ModelElement element, int row, String value) {
        AssociationEnd end = (AssociationEnd) element;
        if (end.getAggregation().equals(AggregationKind.KINDISAGGREGATION))
            end = end.getOpposite();
        end.setMultiplicityMax(value);
        end.setMultiplicityMin(value);
        return 1;
    }

}
