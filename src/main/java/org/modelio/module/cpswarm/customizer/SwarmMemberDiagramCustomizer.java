package org.modelio.module.cpswarm.customizer;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.IDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.module.IModule;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.utils.IDiagramCustomizerPredefinedField;
import org.modelio.module.sysml.utils.ISysMLCustomizerPredefinedField;

/**
 * This class handles the palette configuration of Swarm member diagram
 * @author ebrosse
 */
@objid ("713de936-bdb3-4f4d-834a-7559442ad7e0")
public class SwarmMemberDiagramCustomizer extends CPSwarmDiagramCustomizer implements IDiagramCustomizer {
    @objid ("b3628ca8-f777-4cc3-80ff-e0d9e4bbeeec")
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
        IDiagramService toolRegistry = CPSWarmModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
        
        final PaletteDrawer commonGroup = new PaletteDrawer("Default", null);
        commonGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        commonGroup.add(new SelectionToolEntry());
        commonGroup.add(new MarqueeToolEntry());
        paletteRoot.add(commonGroup);
        
        paletteRoot.add(this.createInstanceGroup(toolRegistry));
        paletteRoot.add(this.createDefaultFreeDrawingGroup(toolRegistry));
    }

    @objid ("67eee4ba-3a17-4db9-acda-f6728f0f54bc")
    private PaletteEntry createInstanceGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Instance"), null);
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        //        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ConnectorProperty));
        //        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ConstraintProperty));
        //        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ParticipantPropertyBindableInstance));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Instance));
        //        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.DistributedProperty));
        //        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.FlowProperty));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.UMLAttribute));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.UMLOperation));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Port));
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DelegateLink));
        //        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InstanceLink));
        return group;
    }

    @objid ("2040b7cc-ae32-4c7a-9198-502adbd0542b")
    @Override
    public boolean keepBasePalette() {
        return false;
    }

    @objid ("9c6ad9cd-c3e5-4397-bddd-50481c8a82d0")
    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }

    @objid ("675acfef-d479-49c2-ba4e-cca3c79145f6")
    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
