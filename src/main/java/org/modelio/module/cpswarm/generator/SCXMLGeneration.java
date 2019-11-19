package org.modelio.module.cpswarm.generator;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.stateMachineModel.FinalState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.cpswarm.api.CPSWarmNoteTypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;

@objid ("fdb1f4b4-5988-41a6-bd42-b8398c300696")
public class SCXMLGeneration extends Generator implements IGenerator {
    @objid ("860872d3-82f4-4618-b7a0-1636b3f3ad40")
    private StateMachine sm = null;

    @objid ("9ba83793-0016-4f2d-b0ca-187e9703774a")
    public SCXMLGeneration(StateMachine sm) {
        this.sm = sm;
    }

    @objid ("678f5491-5100-42e1-b979-ce56f2faf588")
    @Override
    public StringBuffer generate() {
        headerSCXML();
        stateSCXML();
        footerSCXML();
        return this.content;
    }

    @objid ("b4cd6636-162d-4ac5-85c5-fff514553fbb")
    private void stateSCXML() {
        for (StateVertex state : this.sm.getTop().getSub()){
            if (state instanceof State){
                generateState((State) state);
            }
        }
    }

    @objid ("619e8116-a4f9-44c3-8318-5a3e4a61bdde")
    private void generateState(State state) {
        addLine("<state id= \"" + state.getName() + "\">");
        increaseIndent();
        
        if ((state.getInternal().size() > 0) && (state.getInternal().get(0).getProcessed() != null)){
            generateCode(state.getInternal().get(0).getProcessed());
        }else {
            generateCode(state);
        }
        
        addEmptyLine();
        
        for(Transition trans : state.getOutGoing()){
            if ((trans.getTarget() instanceof State) && !(trans.getTarget() instanceof FinalState)) 
                generateTransition(trans);
        }
        decreaseIndent();
        addLine("<state>");  
        addEmptyLine();
    }

    @objid ("5951da7b-21d9-40a9-8e33-cad1965d2f2f")
    private void generateCode(State state) {
        String note = state.getNoteContent(ICPSWarmPeerModule.MODULE_NAME, CPSWarmNoteTypes.MONITOR);  
        if ((note != null) && (!(note.equals(""))))
            generateMonitor(note);
        
        note = state.getNoteContent(ICPSWarmPeerModule.MODULE_NAME, CPSWarmNoteTypes.ACTION);  
        if ((note != null) && (!(note.equals(""))))
            generateAction(note);
        
        
        note = state.getNoteContent(ICPSWarmPeerModule.MODULE_NAME, CPSWarmNoteTypes.SERVICE);  
        if ((note != null) && (!(note.equals(""))))
            generateService(note);
    }

    @objid ("30dc672c-e3e6-4812-9df7-83fbb44eb98e")
    private void generateAction(String action) {
        addLine("<datamodel>");
        String msg = "<data id=\"invoke\"><rosaction>";
        msg+= action;
        msg+= "</rosaction>";
        addLine(msg);
        addLine("</data>");
        addLine("</datamodel>");
        addLine("<invoke type=\"ROS_ACTION\" />");
    }

    @objid ("61cb727c-20c2-4f4b-824f-d437b55f82c7")
    private void generateMonitor(String monitor) {
        addLine("<datamodel>");
        String msg = "<data id=\"invoke\"><rosmonitor>";
        msg+= monitor;
        msg+= "</rosmonitor>";
        addLine(msg);
        addLine("</data>");
        addLine("</datamodel>");
        addLine("<invoke type=\"ROS_MONITOR\" />");
    }

    @objid ("e19d9119-7fff-4543-95ea-acbdd8c1bb08")
    private void generateService(String service) {
        addLine("<datamodel>");
        String msg = "<data id=\"invoke\"><rosservice>";
        msg+= service;
        msg+= "</rosservice>";
        addLine(msg);
        addLine("</data>");
        addLine("</datamodel>");
        addLine("<invoke type=\"ROS_SERVICE\" />");
    }

    @objid ("79d2a510-9ad7-44f7-b6a8-c4d5158a1af1")
    private void generateTransition(Transition trans) {
        String eventName = "";
        Event trigger = trans.getTrigger();
        if (trigger == null) {
            eventName = trans.getEffect();
        }else {
            eventName = trigger.getName().toLowerCase();
        }
        
        String targetName = "";
        StateVertex target = trans.getTarget();
        if (target != null) {
            targetName = target.getName();
        }
        
        addLine(" <transition event=\"" + eventName + "\" target=\"" + targetName +"\"/>");
    }

    @objid ("486e1a25-bac0-4638-b0c5-0771e9fe1987")
    private void generateCode(Operation processed) {
        String note = processed.getNoteContent(ICPSWarmPeerModule.MODULE_NAME, CPSWarmNoteTypes.MONITOR);  
        if ((note != null) && (!(note.equals(""))))
            generateMonitor(note);
        
        note = processed.getNoteContent(ICPSWarmPeerModule.MODULE_NAME, CPSWarmNoteTypes.ACTION);  
        if ((note != null) && (!(note.equals(""))))
            generateAction(note);
        
        
        note = processed.getNoteContent(ICPSWarmPeerModule.MODULE_NAME, CPSWarmNoteTypes.SERVICE);  
        if ((note != null) && (!(note.equals(""))))
            generateService(note);
    }

    @objid ("601712da-159b-435c-9163-fc33bd5ea2eb")
    private void footerSCXML() {
        decreaseIndent();
        addLine("</scxml>");
    }

    @objid ("9df11abf-d830-42fa-8727-df00321008f7")
    private void headerSCXML() {
        String initName= getInitialName();
        addLine("<scxml xmlns=\"http://www.w3.org/2005/07/scxml\" " 
                + "xmlns:cpswarm=\"http://my.custom-actions.domain/cpswarm/CUSTOM\" "
                + "version=\"1.0\" "
                + "initial=\"" + initName + "\" "
                + "name=\"" + this.sm.getName() + "\"");
        addEmptyLine();
        increaseIndent();
    }

    @objid ("81109f6d-3330-4260-bca7-610ffefc05af")
    private String getInitialName() {
        for (StateVertex state : this.sm.getTop().getSub()){
            if (state instanceof InitialPseudoState){
                return state.getOutGoing().get(0).getTarget().getName();
            }
        }
        return "";
    }

}
