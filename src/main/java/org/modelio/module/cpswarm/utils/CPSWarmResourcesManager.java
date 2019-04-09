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
 * @package    com.modeliosoft.modelio.sysml.utils
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    1.2.18
 **/
package org.modelio.module.cpswarm.utils;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.module.cpswarm.i18n.I18nMessageService;
import org.modelio.module.cpswarm.impl.CPSWarmModule;

@objid ("208c34e7-d8ea-4355-8331-402094a6760f")
public class CPSWarmResourcesManager {
    @objid ("43842e37-57bc-43c3-ab0b-28825245655a")
    private static String getModulePath() {
        return  CPSWarmModule.getInstance().getModuleContext().getConfiguration().getModuleResourcesPath().toString() + File.separator + "res";
    }

    /**
     * Operation getStyleget
     * @author ebrosse
     * @param style name
     * @return
     */
    @objid ("a2a925e9-526d-47f8-a221-9fe33f114c27")
    public static String getStyle(String style) {
        return getModulePath() + File.separator + "style" + File.separator + style;
    }

    /**
     * Operation getPropertyName
     * @author ebrosse
     * @param string @return
     */
    @objid ("7cb2be0f-930d-487f-b4c1-1e28aae15283")
    public static String getPropertyName(String string) {
        return I18nMessageService.getString("Ui.Property." + string + ".Name");
    }

    /**
     * This method return the tooltip of the desired command
     * @author ebrosse
     * @param string : name of the stereotype
     * @return the associated tooltip
     */
    @objid ("9085c271-69d6-4497-b55d-8d8f53fd33a6")
    public static String getTooltip(String string) {
        return I18nMessageService.getString("Ui.Command.Diagram." + string + ".Tooltip" );
    }

    /**
     * This method return the label of the desired command
     * @author ebrosse
     * @param string : name of the stereotype
     * @return the associated label
     */
    @objid ("985a1692-6a11-4799-a89b-81117fd68057")
    public static String getLabel(String string) {
        return I18nMessageService.getString("Ui.Command.Diagram." + string + ".Label" );
    }

    /**
     * Operation getPattern
     * @author ebrosse
     * @param pattern name
     * @return
     */
    @objid ("39118261-b790-442d-aa34-d3e5f37132b8")
    public static String getPattern(String pattern) {
        return getModulePath() + File.separator + "pattern" + File.separator + pattern;
    }

}
