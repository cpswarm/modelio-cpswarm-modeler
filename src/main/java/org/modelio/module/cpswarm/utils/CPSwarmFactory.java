package org.modelio.module.cpswarm.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.cpswarm.api.CPSWarmStereotypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.module.cpswarm.i18n.I18nMessageService;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("3c680331-e84f-4121-8525-67a9d69628e0")
public class CPSwarmFactory {
    @objid ("d9e2afda-73da-49b7-88b9-bd06c402f053")
    private static final String MODELERMODULE_NAME = "ModelerModule";

    @objid ("495c7577-f018-42f5-9542-bff71469c8d3")
    private static final String DESCRIPTION = "description";

    @objid ("126f05c2-e6b4-49a9-9ef7-3ebee990bf5e")
    private static final IModelingSession _session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();

    @objid ("262fa5a2-74fe-49bf-9335-6843761781fe")
    private static final IUmlModel _model = _session.getModel();

    @objid ("eab17815-d3a0-4e0b-bde1-8aade4fe20d1")
    public static StaticDiagram createEnvironmentDefinitionDiagram(ModelElement owner) {
        try{
            StaticDiagram diagram = _model.createStaticDiagram("", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.ENVIRONMENT_DEFINITION);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, I18nMessageService.getString("Ui.Name.EnvironmentDefinition", owner.getName()));       
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("a809cdc1-d368-493d-8390-b5e817d8715b")
    public static StaticDiagram createEnvironmentDefinitionDiagram(ModelElement owner, String name, String description) {
        try{
            StaticDiagram diagram = _model.createStaticDiagram("", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.ENVIRONMENT_DEFINITION);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, name);
            _session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram,description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("43504223-85b6-4780-98cd-6eac232b9c15")
    public static StaticDiagram createFitnessDefinitionDiagram(ModelElement owner, String name, String description) {
        try{
            StaticDiagram diagram = _model.createStaticDiagram("", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.FITNESS_DEFINITION);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, I18nMessageService.getString("Ui.Name.FitnessDefinition", owner.getName()));
            _session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram,description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("6b8c5ac6-baef-4041-adbc-83cfdda25d2e")
    public static StaticDiagram createProblemStatementDiagram(ModelElement element, String name, String description) {
        try{
            StaticDiagram diagram = _model.createStaticDiagram("", element, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.PROBLEM_STATEMENT);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, I18nMessageService.getString("Ui.Name.ProblemStatement", element.getName()));
            _session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram,description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("7a14cfbe-4ddc-40e9-8721-0fb880c89c79")
    public static ClassDiagram createSwarmMemberArchitectureDiagram(ModelElement owner, String name, String description) {
        try{
            Stereotype ster = _session.getMetamodelExtensions().getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARMMEMBER_ARCHITECTURE, 
                    CPSWarmModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ClassDiagram.class));
            
            ClassDiagram diagram = _model.createClassDiagram(name, owner, ster);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, name);
            _session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram,description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("ff59620e-ee2d-472f-bd13-85edb401a080")
    public static ClassDiagram createSwarmDefinitionDiagram(ModelElement owner, String name, String description) {
        try{
            Stereotype ster = _session.getMetamodelExtensions().getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARM_DEFINITION, 
                    CPSWarmModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ClassDiagram.class));
            
            ClassDiagram diagram = _model.createClassDiagram(name, owner, ster);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, I18nMessageService.getString("Ui.Name.SwarmDefinition", owner.getName()));
            _session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram, description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("91de45be-2440-476c-995f-25303b6f5f9c")
    public static ClassDiagram createSwarmMemberArchitectureDiagram(ModelElement owner, String name) {
        try{
            Stereotype ster = _session.getMetamodelExtensions().getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARMMEMBER_ARCHITECTURE, 
                    CPSWarmModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ClassDiagram.class));
            
            ClassDiagram diagram = _model.createClassDiagram(name, owner, ster);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, name);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("b58ea52f-a649-4c2f-aaae-ba20e2a44e4b")
    public static ClassDiagram createSwarmDefinitionDiagram(ModelElement owner, String name) {
        try{
            Stereotype ster = _session.getMetamodelExtensions().getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARM_DEFINITION, 
                    CPSWarmModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ClassDiagram.class));
            
            ClassDiagram diagram = _model.createClassDiagram(name, owner, ster);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, "Swarm Definition");
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("7ca3983d-471e-4cde-a57a-34958d0ad27a")
    public static StateMachineDiagram createBehaviorDefinitionDiagram(StateMachine owner, String description) {
        try{
            Stereotype ster = _session.getMetamodelExtensions().getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.BEHAVIOR_DEFINITION, 
                    CPSWarmModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(StateMachineDiagram.class));
            StateMachineDiagram diagram = _model.createStateMachineDiagram("Behavior", owner, ster);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, owner.getName());
            _session.getModel().createNote(MODELERMODULE_NAME, DESCRIPTION, diagram, description);
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("9705a3cb-1c83-4ecf-8155-13e26db97c43")
    public static StateMachineDiagram createBehaviorDefinitionDiagram(StateMachine owner) {
        try{
            Stereotype ster = _session.getMetamodelExtensions().getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.BEHAVIOR_DEFINITION, 
                    CPSWarmModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(StateMachineDiagram.class));
            StateMachineDiagram diagram = _model.createStateMachineDiagram("Behavior", owner, ster);
            _session.getModel().getDefaultNameService().setDefaultName(diagram, owner.getName());
            return diagram;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("54da9271-c2ba-4941-9768-e6b3fd760e1c")
    public static StateMachineDiagram createBehavior(NameSpace owner) {
        try{
            StateMachine sm = _model.createStateMachine();
            _session.getModel().getDefaultNameService().setDefaultName(sm, "Behavior");
            sm.setOwner(owner);
            return createBehaviorDefinitionDiagram(sm);
        }catch(Exception e){
            return null;
        }
    }

    @objid ("45e5e990-c20a-4f51-85e6-683dc3134d4c")
    public static StateMachineDiagram createBehavior(Operation owner) {
        try{
            StateMachine sm = _model.createStateMachine();
            _session.getModel().getDefaultNameService().setDefaultName(sm, "Behavior");
            sm.setOwnerOperation(owner);
            return createBehaviorDefinitionDiagram(sm);
        }catch(Exception e){
            return null;
        }
    }

    @objid ("b98f2ad0-56c9-44cc-8260-fc395457d36d")
    public static State createAction(Region owner) {
        try{
            State state = _model.createState();
            _session.getModel().getDefaultNameService().setDefaultName(state, "Action");
            state.setParent(owner);
            
            InternalTransition it = _model.createInternalTransition();
            it.setSComposed(state);
            it.setReceivedEvents("Do");
            return state;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("e7cfa828-6cb1-412f-af20-6108c60d55f8")
    public static State createAction(StateMachine owner) {
        try{
            return createAction(owner.getTop());
        }catch(Exception e){
            return null;
        }
    }

    @objid ("90bd3002-2894-4570-b95a-5115e94cc37e")
    public static Event createEvent(Transition tran) {
        try{
            Event event = _model.createEvent();
            _session.getModel().getDefaultNameService().setDefaultName(event, "Event");
            
            StateVertex source = tran.getSource();
            Region reg = source.getParent();
            event.setComposed(reg.getRepresented());
            tran.setTrigger(event);
        
            return event;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("3d9e5823-f375-4436-a08d-5368696e672c")
    public static MObject createSubStateMachine(Region owner) {
        try{
            State state = _model.createState();
            _session.getModel().getDefaultNameService().setDefaultName(state, "subState");
            state.setParent(owner);
            
            return state;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("a5157dc0-b534-4406-816a-78b91fb3eade")
    public static MObject createSubStateMachine(StateMachine owner) {
        try{
           return createSubStateMachine(owner.getTop());
        }catch(Exception e){
            return null;
        }
    }

    @objid ("f711bc7c-1df7-4dc6-901e-b5155b7e1706")
    public static Class createSwarm(Package owner) {
        try{
            Class swarm = _model.createClass("Swarm", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARM);
            _session.getModel().getDefaultNameService().setDefaultName(swarm, "Swarm");          
            return swarm;
        }catch(Exception e){
            return null;
        }
    }

    @objid ("140611af-84a3-4c6f-9afe-7ee405897d9f")
    public static Class createSwarmMember(Package owner) {
        try{
            Class swarm = _model.createClass("SwarmMember", owner, ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARM_MEMBER);
            _session.getModel().getDefaultNameService().setDefaultName(swarm, "SwarmMember");          
            return swarm;
        }catch(Exception e){
            return null;
        }
    }

}
