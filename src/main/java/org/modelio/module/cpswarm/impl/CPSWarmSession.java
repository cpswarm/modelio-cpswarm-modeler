package org.modelio.module.cpswarm.impl;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.modelio.api.modelio.mc.IModelComponentDescriptor;
import org.modelio.api.modelio.mc.IModelComponentService;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.lifecycle.DefaultModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.module.cpswarm.api.CPSWarmParameters;
import org.modelio.vbasic.version.Version;

/**
 * @author ebrosse
 */
@objid ("5aed94de-25ef-4a13-bbc0-c3b74a5a8693")
public class CPSWarmSession extends DefaultModuleLifeCycleHandler implements IModuleLifeCycleHandler {
    /**
     * Constructor with the CPSwarm Mdac
     * @param module : the CPSwarm Mdac module
     */
    @objid ("2dc93f13-f9a2-4841-90ed-1fb34e84ae30")
    public CPSWarmSession(IModule module) {
        super(module);
    }

    @objid ("7db92d81-619b-42b6-8795-288284f58986")
    @Override
    public boolean start() throws ModuleException {
        installRamc();
        installStyles();
        //        initParameter();
        return super.start();
    }

    @objid ("4ad4cdd9-517e-40da-a553-1519091427b7")
    @Override
    public void stop() throws ModuleException {
        super.stop();
    }

    @objid ("ac8a80d6-0829-4046-a977-8ea35c5443c7")
   
    private void installStyles() {
        Path mdaPath = this.module.getModuleContext().getConfiguration().getModuleResourcesPath();
        CPSWarmModule.getInstance().getModuleContext().getModelioServices().getDiagramService().registerStyle("cpswarm", "default", 
                new File(mdaPath + File.separator  + "res" + File.separator + "style" + File.separator + "cpswarm.style"));
        CPSWarmModule.getInstance().getModuleContext().getModelioServices().getDiagramService().registerStyle("cpswarminternal", "default", 
                new File(mdaPath + File.separator  + "res" + File.separator + "style" + File.separator + "cpswarminternal.style"));
    }

    @objid ("7a857d3d-eece-450f-b793-212686521db8")
    @Override
    public boolean select() throws ModuleException {
        return super.select();
    }

    @objid ("f3413fbe-9483-44e0-a779-21bc3b840c60")
    @Override
    public void upgrade(Version oldVersion, Map<String, String> oldParameters) throws ModuleException {
        super.upgrade (oldVersion, oldParameters);
    }

    @objid ("05960367-b61d-4ce8-bf03-8da5e2331668")
    @Override
    public void unselect() {
    }

    @objid ("4a6680ae-f8c6-4b58-b041-cba177c503c9")
    private void initParameter() {
        IModuleUserConfiguration configuration = this.module.getModuleContext().getConfiguration();
        if (configuration.getParameterValue (CPSWarmParameters.WORKSPACEPATH).isEmpty ()) {
            configuration.setParameterValue (CPSWarmParameters.WORKSPACEPATH, "");
        }
        
        if (configuration.getParameterValue (CPSWarmParameters.WORKSPACEPATH).isEmpty ()) {
            configuration.setParameterValue (CPSWarmParameters.WORKSPACEPATH, "/opt/ros/lunar");
        }
    }

    @objid ("2ca4f6fa-0d25-4751-a9ba-f4d71455b127")
    private void installRamc() {
        Path mdaplugsPath = this.module.getModuleContext().getConfiguration().getModuleResourcesPath();
        
        final IModelComponentService modelComponentService =  CPSWarmModule.getInstance().getModuleContext().getModelioServices().getModelComponentService();
        for (IModelComponentDescriptor mc : modelComponentService.getModelComponents()) {
            if (mc.getName().equals("CPSWarmLibrary")) {
                if (new Version(mc.getVersion()).isOlderThan(new Version("3.7.00"))) {
                    modelComponentService.deployModelComponent(new File(mdaplugsPath.resolve("res" + File.separator + "ramc" + File.separator + "CPSWarmLibrary.ramc").toString()), new NullProgressMonitor());
                } else {
                    // Ramc already deployed...
                    return;
                }
            }
        }
        
        // No ramc found, deploy it
        modelComponentService.deployModelComponent(new File(mdaplugsPath.resolve("res" + File.separator + "ramc" + File.separator + "CPSWarmLibrary.ramc").toString()), new NullProgressMonitor());
    }

}
