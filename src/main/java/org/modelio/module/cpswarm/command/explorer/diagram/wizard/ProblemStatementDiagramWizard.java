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
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLResourcesManager;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the creation of Problem defintion diagram
 * @author ebrosse
 */
@objid ("bd84d10b-0936-4673-82df-1b93e8f0a2bd")
public class ProblemStatementDiagramWizard extends AbstractWizardContributor implements IDiagramWizardContributor {
    @objid ("e6b37f5b-1e9b-4c95-a298-b84717c0b044")
    @Override
    public AbstractDiagram actionPerformed(ModelElement element, String diagramName, String description) {
        IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
        AbstractDiagram diagram = null;
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create","Problem Statement"))){
        
        
            diagram = CPSwarmFactory.createProblemStatementDiagram(element, diagramName, description);
        
            if (diagram != null) {
                IDiagramService ds = SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
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

    @objid ("097eb18f-67b6-4026-80bc-cd35952728b3")
    @Override
    public String getLabel() {
        return I18nMessageService.getString ("Ui.Command.ProblemStatementDiagramExplorerCommand.Label");
    }

    @objid ("34e88998-5fb9-443a-8907-c55cc15d99e7")
    @Override
    public String getDetails() {
        return I18nMessageService.getString ("Ui.Command.ProblemStatementDiagramExplorerCommand.Details");
    }

    @objid ("8b792cae-5409-4663-98a1-9e11a69e4c8b")
    @Override
    public String getHelpUrl() {
        return null;
    }

    @objid ("2ab11dde-5a66-4220-88ea-9516ec1885b5")
    @Override
    public Image getIcon() {
        return new Image(Display.getDefault(),SysMLResourcesManager.getInstance().getImage("problemstatement.png"));
    }

    @objid ("6ec64654-b9dc-4e1c-a8cc-0bc81dcdb55e")
    @Override
    public String getInformation() {
        return I18nMessageService.getString ("Ui.Command.ProblemStatementDiagramExplorerCommand.Information");
    }

    @objid ("ce26a124-52c5-4b7d-bc8b-20f06dcc27f1")
    @Override
    public boolean accept(MObject selectedElt) {
        return ((selectedElt != null) &&
                                        (((selectedElt instanceof Package) && !(selectedElt instanceof Profile)
                                                || ((selectedElt instanceof Class) && (((Class)selectedElt).isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.BLOCK) )))
                                                && selectedElt.getStatus().isModifiable()));
    }

    @objid ("d3761d3f-83f4-4658-a63e-cd1da737e81c")
    @Override
    public ElementDescriptor getCreatedElementType() {
        // TODO Auto-generated method stub
        return null;
    }

}
