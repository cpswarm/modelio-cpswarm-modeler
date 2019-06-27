package org.modelio.module.cpswarm.customizer;

import java.util.List;
import java.util.Map;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.IDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.module.IModule;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.cpswarm.utils.ICPSwarmDiagramCustomizerPredefinedField;
import org.modelio.module.sysml.utils.IDiagramCustomizerPredefinedField;

/**
 * This class handles the palette configuration of Swarm definition diagram
 * @author ebrosse
 */

public class SwarmDiagramCustomizer extends CPSwarmDiagramCustomizer implements IDiagramCustomizer {
    
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
        IDiagramService toolRegistry = CPSWarmModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
        
        final PaletteDrawer commonGroup = new PaletteDrawer("Default", null);
        commonGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        commonGroup.add(new SelectionToolEntry());
        commonGroup.add(new MarqueeToolEntry());
        paletteRoot.add(commonGroup);
        
        paletteRoot.add(this.createSwarmGroup(toolRegistry));
        paletteRoot.add(this.createDefaultFreeDrawingGroup(toolRegistry));
    }

  
//    private PaletteEntry createInstanceGroup(final IDiagramService toolRegistry) {
//        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Instance"), null);
//        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.QuantityKind));
//        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Unit));
//        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Instance));
//        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.AttributeLink));
//        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DelegateLink));
//        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InstanceLink));
//        
//        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
//        return group;
//    }

   
    private PaletteEntry createSwarmGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer("Swarm", null);
       
        group.add(toolRegistry.getRegisteredTool(ICPSwarmDiagramCustomizerPredefinedField.SWARMMEMBER));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Component));
        group.add(toolRegistry.getRegisteredTool(ICPSwarmDiagramCustomizerPredefinedField.CPSWARMCOMPOSITION));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Attribute));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Operation));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Port));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Signal));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Datatype));
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Interface));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InterfaceRealization));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Enumeration));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.EnumerationLiteral));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }


    @Override
    public boolean keepBasePalette() {
        return false;
    }


    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }


    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
