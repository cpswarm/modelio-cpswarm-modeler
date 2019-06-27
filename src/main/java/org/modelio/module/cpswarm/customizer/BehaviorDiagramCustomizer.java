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
import org.modelio.module.cpswarm.utils.IDiagramCustomizerPredefinedField;

/**
 * This class handles the palette configuration of Swarm member diagram
 * @author ebrosse
 */

public class BehaviorDiagramCustomizer extends CPSwarmDiagramCustomizer implements IDiagramCustomizer {

    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
        IDiagramService toolRegistry = CPSWarmModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
        
        final PaletteDrawer commonGroup = new PaletteDrawer("Default", null);
        commonGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        commonGroup.add(new SelectionToolEntry());
        commonGroup.add(new MarqueeToolEntry());
        paletteRoot.add(commonGroup);
        
        paletteRoot.add(this.createStateGroup(toolRegistry));
        paletteRoot.add(this.createDefaultFreeDrawingGroup(toolRegistry));
    }


    private PaletteEntry createStateGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer("State", null);
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);

//        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.State));
        group.add(toolRegistry.getRegisteredTool(ICPSwarmDiagramCustomizerPredefinedField.ACTION));
        group.add(toolRegistry.getRegisteredTool(ICPSwarmDiagramCustomizerPredefinedField.SUBMACHINE));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Transition));
        group.add(toolRegistry.getRegisteredTool(ICPSwarmDiagramCustomizerPredefinedField.CPSEVENT));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Initiale));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Final));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Choice));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Fork));
       
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
