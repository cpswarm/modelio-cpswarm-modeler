/**
 * Java Class : AddBlockDiagramExplorerCommand.java
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
 * @category   Command explorer
 * @package    com.modeliosoft.modelio.sysml.gui.explorer
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.cpswarm.command.explorer.diagram.wizard;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.contributor.AbstractWizardContributor;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.IDiagramWizardContributor;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.cpswarm.i18n.I18nMessageService;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.utils.CPSwarmFactory;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.utils.SysMLResourcesManager;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the creation of SysML block diagram
 * @author ebrosse
 */
@objid ("c7251f6e-c13c-426f-b02c-b2727e582637")
public class EnvironmentDefinitionDiagramWizard extends AbstractWizardContributor implements IDiagramWizardContributor {
    @objid ("6aec608a-d7f2-4fe0-98f2-7ae5574796fd")
    @Override
    public AbstractDiagram actionPerformed(ModelElement element, String diagramName, String description) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        AbstractDiagram diagram = null;
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Environment Definition"))){
        
        
            diagram = CPSwarmFactory.createEnvironmentDefinitionDiagram(element, diagramName, description);
        
            if (diagram != null) {
                IDiagramService ds = CPSWarmModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
                IDiagramHandle handler = ds.getDiagramHandle(diagram);
                IDiagramDG dg = handler.getDiagramNode();
        
                for (IStyleHandle style : ds.listStyles()){
                    if (style.getName().equals("cpswarm")){
                        dg.setStyle(style);
                        break;
                    }
                }
        
                handler.save();
                handler.close();
        
                CPSWarmModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
            }
        
            transaction.commit ();
        }
        return diagram;
    }

    @objid ("3357c877-ef43-44d0-bddf-2af7f7b434e5")
    @Override
    public String getLabel() {
        return I18nMessageService.getString ("Ui.Command.BlockDiagramExplorerCommand.Label");
    }

    @objid ("55c5fc1e-34c0-48dd-955a-57753c410805")
    @Override
    public String getDetails() {
        return I18nMessageService.getString ("Ui.Command.BlockDiagramExplorerCommand.Details");
    }

    @objid ("81e05c1b-aec7-4d94-b296-e604fe5b1b37")
    @Override
    public String getHelpUrl() {
        return null;
    }

    @objid ("cdc83d9e-ef81-422f-bc88-1f68b3a71145")
    @Override
    public Image getIcon() {
        return new Image(Display.getDefault(),SysMLResourcesManager.getInstance().getImage("blockdiagram.png"));
    }

    @objid ("a3aa79ef-f3f3-49d5-8fe6-fc2531c1bcbe")
    @Override
    public String getInformation() {
        return I18nMessageService.getString ("Ui.Command.BlockDiagramExplorerCommand.Information");
    }

    @objid ("6c38d83d-8a89-4823-a8fb-23bb432f3491")
    @Override
    public boolean accept(MObject selectedElt) {
        return ((selectedElt != null) &&
                (((selectedElt instanceof Package) && !(selectedElt instanceof Profile)
                        || ((selectedElt instanceof Class) && (((Class)selectedElt).isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.BLOCK) )))
                        && selectedElt.getStatus().isModifiable()));
    }

    @objid ("28596af9-350b-4a27-b09f-615fe281619c")
    @Override
    public ElementDescriptor getCreatedElementType() {
        // TODO Auto-generated method stub
        return null;
    }

}
