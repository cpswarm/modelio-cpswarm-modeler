package org.modelio.module.cpswarm.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.parameter.IParameterEditionModel;
import org.modelio.api.module.parameter.impl.ParameterGroupModel;
import org.modelio.api.module.parameter.impl.ParameterModel;
import org.modelio.api.module.parameter.impl.ParametersEditionModel;
import org.modelio.api.module.parameter.impl.StringParameterModel;
import org.modelio.module.cpswarm.api.CPSWarmParameters;
import org.modelio.module.cpswarm.i18n.I18nMessageService;

/**
 * Implementation of the IModule interface.
 * <br>All Modelio java modules should inherit from this class.
 */
@objid ("fd6fe968-c247-4132-bd8a-651dae66dc05")
public class CPSWarmModule extends AbstractJavaModule {
    @objid ("9e102e96-6755-4f39-bac0-194a364c6cb9")
    private CPSWarmPeerModule peerModule = null;

    @objid ("c368b95e-5f79-416e-be55-61f42c469e52")
    private CPSWarmSession session = null;

    @objid ("f21764ca-178c-4fc3-9f84-b0c2539a3f0f")
    private static CPSWarmModule instance = null;

    @objid ("722b1be0-353b-4647-af44-d5174d11e7f3")
    public static CPSWarmModule getInstance() {
        return instance;
    }

    @objid ("4b5e47b7-5f85-4b80-aa96-70796188372b")
    @Override
    public CPSWarmPeerModule getPeerModule() {
        return this.peerModule;
    }

    /**
     * Return the lifecycle handler attached to the current module.
     * <p>
     * <p>
     * This handler is used to manage the module lifecycle by declaring the
     * desired implementation on start, select... methods.
     */
    @objid ("d5390aaa-dcb5-4c56-8471-085677e8d1e5")
    @Override
    public IModuleLifeCycleHandler getLifeCycleHandler() {
        return this.session;
    }

    /**
     * Method automatically called just after the creation of the module.
     * <p>
     * <p>
     * The module is automatically instanciated at the beginning of the MDA
     * lifecycle and constructor implementation is not accessible to the module
     * developer.
     * <p>
     * <p>
     * The <code>init</code> method allows the developer to execute the desired initialization code at this step. For
     * example, this is the perfect place to register any IViewpoint this module provides.
     * @see org.modelio.api.module.AbstractJavaModule#init()
     */
    @objid ("f505de58-733c-434b-b254-eecc6a340c8b")
    @Override
    public void init() {
        // Add the module initialization code
        super.init();
    }

    /**
     * Method automatically called just before the disposal of the module.
     * <p>
     * <p>
     * 
     * 
     * The <code>uninit</code> method allows the developer to execute the desired un-initialization code at this step.
     * For example, if IViewpoints have been registered in the {@link #init()} method, this method is the perfect place
     * to remove them.
     * <p>
     * <p>
     * 
     * This method should never be called by the developer because it is already invoked by the tool.
     * @see org.modelio.api.module.AbstractJavaModule#uninit()
     */
    @objid ("fb8b3114-94ab-441e-b01a-8b2a932f8cd8")
    @Override
    public void uninit() {
        // Add the module un-initialization code
        super.uninit();
    }

    /**
     * Builds a new module.
     * <p>
     * <p>
     * This constructor must not be called by the user. It is automatically
     * invoked by Modelio when the module is installed, selected or started.
     * @param moduleContext context of the module, needed to access Modelio features.
     */
    @objid ("091b4405-6aa8-494c-bed2-41533730455e")
    public CPSWarmModule(IModuleContext moduleContext) {
        super(moduleContext);
        this.session = new CPSWarmSession(this);
        this.peerModule = new CPSWarmPeerModule(this, moduleContext.getPeerConfiguration());
        this.peerModule.init();
        instance = this;
    }

    /**
     * @see org.modelio.api.module.AbstractJavaModule#getParametersEditionModel()
     */
    @objid ("18ca4187-82cc-4e03-a2ce-3c8a15fbcbbf")
    @Override
    public IParameterEditionModel getParametersEditionModel() {
        if (this.parameterEditionModel == null) {
            IModuleUserConfiguration configuration = this.getModuleContext().getConfiguration();
            ParametersEditionModel parameters = new ParametersEditionModel(this);
            this.parameterEditionModel = parameters;
            ParameterModel parameter;
        
            ParameterGroupModel Group1 = new ParameterGroupModel("Group1", "ROS");
            parameters.addGroup(Group1);
            parameter = new StringParameterModel(configuration, CPSWarmParameters.WORKSPACEPATH, I18nMessageService.getString ("Ui.Parameter.ROSWorkspace.Label"), I18nMessageService.getString ("Ui.Parameter.ROSWorkspace.Description"),"");
            Group1.addParameter(parameter);
        
            parameter = new StringParameterModel(configuration, CPSWarmParameters.ROSPATH, I18nMessageService.getString ("Ui.Parameter.ROSPath.Label"), I18nMessageService.getString ("Ui.Parameter.ROSPath.Description"),"");
            Group1.addParameter(parameter);
            
            ParameterGroupModel Group2 = new ParameterGroupModel("Group2", "OptimizationTool");
            parameters.addGroup(Group2);
            parameter = new StringParameterModel(configuration, CPSWarmParameters.FREVOPATH, I18nMessageService.getString ("Ui.Parameter.FrevoPath.Label"), I18nMessageService.getString ("Ui.Parameter.FrevoPath.Description"),"");
            Group2.addParameter(parameter);
        
        }
        return this.parameterEditionModel;
    }

    @objid ("c34659be-780a-4243-8b7a-51982e21014b")
    @Override
    public String getModuleImagePath() {
        return "/res/icons/cpswarm.png";
    }

}
