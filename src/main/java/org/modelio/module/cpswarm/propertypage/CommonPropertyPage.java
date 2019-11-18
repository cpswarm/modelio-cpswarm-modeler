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

/**
 * This class handles the properties common to all SysML stereotypes
 * @author ebrosse
 */
@objid ("b89e974f-2f99-4976-ab04-821880929018")
public class CommonPropertyPage implements IPropertyContent {
    /**
     * Constructor CommonPropertyPage
     * @author ebrosse
     */
    @objid ("03e65688-d8d2-4083-af54-72b23ecefc65")
    public CommonPropertyPage() {
    }

    @objid ("9ed30f39-05bc-4ffb-b4df-579d0860be61")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
    }

    @objid ("b09406cd-9c57-4c3d-8ec7-b94947b1255c")
    @Override
    public int changeProperty(ModelElement element, int row, String value) {
        return 0;
    }

}
