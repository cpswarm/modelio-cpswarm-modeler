package org.modelio.module.cpswarm.generator;

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

public class SCXMLGeneration extends Generator implements IGenerator {

    private StateMachine sm = null;

    public SCXMLGeneration(StateMachine sm) {    
        this.sm = sm;
    }

    @Override
    public StringBuffer generate() {
        headerSCXML();
        stateSCXML();
        footerSCXML();
        return this.content;
    }

    private void stateSCXML() {
        for (StateVertex state : this.sm.getTop().getSub()){
            if (state instanceof State){
                generateState((State) state);
            }
        }
    }

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

    private void footerSCXML() {
        decreaseIndent();
        addLine("</scxml>");
    }

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

    private String getInitialName() {
        for (StateVertex state : this.sm.getTop().getSub()){
            if (state instanceof InitialPseudoState){
                return state.getOutGoing().get(0).getTarget().getName();
            }
        }
        return "";
    }



}
