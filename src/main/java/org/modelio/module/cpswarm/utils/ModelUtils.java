package org.modelio.module.cpswarm.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handle a set of utility methods for Modelio model manipulation
 * @author ebrosse
 */
@objid ("5855fce8-9795-40f8-927d-4d8fefceded3")
public class ModelUtils {
    @objid ("08d3e755-5c18-4e5d-be2b-7d70ebcac93d")
    private static String namespacingSeparator = "::";

    /**
     * return the ModelElement target of the FIRST dependency stereotyped stereotypeName  owned by source element
     * @param source : the ModelElement which is the source of the dependency
     * @param stereotypeName : the stereotype name applicable on Dependency Metaclass
     * @return the ModelElement target of the first dependency
     */
    @objid ("50592fea-05e0-4c0c-a045-16e588becc0a")
    public static ModelElement getTarget(ModelElement source, String stereotypeName) {
        for(Dependency dp: source.getDependsOnDependency()){
            if(dp.isStereotyped(ICPSWarmPeerModule.MODULE_NAME,  stereotypeName)){
                ModelElement element = dp.getDependsOn();
                if(element != null){
                    return element;
                }       
            }
        }
        return null;
    }

    /**
     * This method returns the list of Classifier associated (sharing an Association) to the given Association
     * @param center : the central association
     * @return the list of associated Association
     */
    @objid ("2c78105e-5dcc-4a94-b961-c29e71a4e341")
    public static List<Classifier> getAssociatedClassifier(final Classifier center) {
        List<Classifier> result = new ArrayList<Classifier>();
        
        for (AssociationEnd feature : center.getOwnedEnd()){
            AssociationEnd opposite = feature.getOpposite();
            Classifier owner = opposite.getOwner();
            if (!result.contains(owner))
                result.add(owner);
        }
        return result;
    }

    /**
     * This method test if the Analyst module is already deployed
     * @return true if the Analyst module is deployed
     */
    @objid ("e0511acc-2a34-4f62-9c84-079210755a79")
    public static boolean isRequirementDeployed() {
        return true;
    }

    /**
     * Allows the tagged value getting
     * @param tagtypeName : tagged value name
     * @param element : owner element
     * @return the tagged value values in a string form
     */
    @objid ("8c279abb-09c3-4263-9934-d649ac41d04b")
    public static String getTaggedValue(final String tagtypeName, final ModelElement element) {
        for (TaggedValue tag : element.getTag()) {
            TagType type = tag.getDefinition();
            String tagname = type.getName();
            if (tagname.equals(tagtypeName)) {
                if (!tag.getActual().isEmpty()) {
                    String result = "";
                    for (TagParameter tp: tag.getActual()) {
                        if (tag.getDefinition().getParamNumber().equals("1")) {
        
                            result = tp.getValue();
                        }
                        else {
                            result = result + tp.getValue() + " ";
                        }
                    }
                    return result; 
                }
            }
        }
        return "";
    }

    /**
     * Allows the tagged value values parsing to string tab
     * @param value : the tagged value
     * @return a string tab with many string values
     */
    @objid ("9ee07478-e4c2-466f-8265-7f3c6428b885")
    public static String[] parseValuesToStringTab(final String value) {
        ArrayList<String> dynamicList = new ArrayList<String>();
        String current = "";
        boolean hasManyElts = false;
        
        for (int i=0; i < value.length(); i++) {
            if (value.charAt(i) != ' ') {
                current = current + value.charAt(i);
        
                if ((i == value.length()-1) && (hasManyElts)) {
                    dynamicList.add(current);
                }
            }
        
            if ((value.charAt(i) == ' ')&&(!current.equals(""))) {
                dynamicList.add(current);
                current = "";
                hasManyElts = true;
            }
            else if (current.length() == value.length()) {
                dynamicList.add(current);
                current = "";
            }
        }
        
        String[] result = dynamicList.toArray(new String[dynamicList.size()]);
        return result;
    }

    /**
     * Allows the tagged value adding.
     * @param element : owner element
     * @param tagtypeName : tagged value name
     * @param value : value to add
     */
    @objid ("f13232aa-0596-4eb3-b523-afcf3beacc41")
    public static void addStringValue(final ModelElement element, final String tagtypeName, final String value) {
        // DON'T place Transition HERE
        boolean exist = false;
        List<TaggedValue> tagElements = element.getTag();
        TaggedValue tvFound = null;
        TagType type = null;
        
        // existing verification
        if (!tagElements.isEmpty()) {
            for (TaggedValue tag : tagElements) {
        
                type = tag.getDefinition();
                String tagname = type.getName();
        
                if (tagname.equals(tagtypeName)) {
                    exist = true;
                    tvFound = tag;
                }
            }
        }
        
        // if the tagged value doesn't exist yet, we create this
        if (!exist) {
            
                TaggedValue v = CPSWarmModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(type, element);
                if (!v.getDefinition().getParamNumber().equals("0")) {
                    setTaggedValue(element, tagtypeName, value);
                }
            
        }
        // if the tagged value already exists
        else {
            if ((tvFound!= null) && (tvFound.getDefinition().getParamNumber().equals("0"))) {
                tvFound.delete();
            }
            else {
                setTaggedValue(element, tagtypeName, value);
            }
        }
    }

    /**
     * Allows the tagged value setting
     * @param elt : owner element
     * @param tagtypeName : name of the tagtype
     * @param value : taggeValue value
     */
    @objid ("46410fc4-66cc-4915-96e8-1cd2e86aaf9f")
    public static void setTaggedValue(final ModelElement elt, final String tagtypeName, final String value) {
        // if the element has tagged values
        if (!elt.getTag().isEmpty()) {
            // for each tagged values
            for (TaggedValue tag : elt.getTag()) {
                //good tagged value getting
                if (tag.getDefinition().getName().equals(tagtypeName)) {
                    // if the parameter number is one
                    if (tag.getDefinition().getParamNumber().equals("1")) {
                        if(tag.getActual().size() != 0) {
                            tag.getActual().get(0).setValue(value);
                        }
                        else {
                            tag.getActual().add(CPSWarmModule.getInstance().getModuleContext().getModelingSession().getModel().createTagParameter(value, tag));
                        }
                    }
                    //if the parameter number is multiple
                    else if (tag.getDefinition().getParamNumber().equals("*")) {
        
                        // string transformation to string tab
                        String[] tabValues = parseValuesToStringTab(value);
        
                        // array list for the news tag parameters
                        ArrayList<TagParameter> listTagParam = new ArrayList<TagParameter>();
        
                        // old tag parameters deleting
                        for (TagParameter tp:tag.getActual()) {
                            tag.getActual().remove(tp);
                            tp.delete();
                        }
        
                        // new list creating
                        for (String s:tabValues) {
                            listTagParam.add(CPSWarmModule.getInstance().getModuleContext().getModelingSession().getModel().createTagParameter(s+" ", tag));
                        }
        
                        // new tag parameters adding
                        for (TagParameter tpl:listTagParam) {
                            tag.getActual().add(tpl);
                        }
                    }
                    else {
                        // else, the parameter number is another number, 2, 3 4 etc.
                        try {
                            int nbParam = Integer.parseInt(tag.getDefinition().getParamNumber());
        
                            // string transformation to string tab
                            String[] tabValues = parseValuesToStringTab(value);
        
                            /* if (tabValues.length > nbParam) {
                                //JOptionPane.showMessageDialog(null,  "You have too many parameter numbers, only "+tag.getDefinition().getParamNumber()+" has been set.", "Too many parameter numbers", JOptionPane.ERROR_MESSAGE);
                                //MessageDialog.openError(null, "Too many parameter numbers", "You have too many parameter numbers, only "+tag.getDefinition().getParamNumber()+" has been set.");
                                //MARTEFrame.marteShowMessageDialog(null, "Too many parameter numbers", "You have too many parameter numbers, only "+tag.getDefinition().getParamNumber()+" has been set.", JOptionPane.ERROR_MESSAGE);
                                MARTEFrame f = new MARTEFrame("You have to enter integers.");
                                f.show();
        
                            }*/
                            // array list for the news tag parameters
                            ArrayList<TagParameter> listTagParam = new ArrayList<TagParameter>();
        
                            // old tag parameters deleting
                            for (TagParameter tp:tag.getActual()) {
                                tag.getActual().remove(tp);
                                tp.delete();
                            }
        
                            // new list creating
                            for (int i = 0; i <tabValues.length;i++) {
                                if (i<nbParam) {
                                    listTagParam.add(CPSWarmModule.getInstance().getModuleContext().getModelingSession().getModel().createTagParameter(tabValues[i]+" ", tag));
                                }
                            }
        
                            // new tag parameters adding
                            for (TagParameter tpl:listTagParam) {
                                tag.getActual().add(tpl);
                            }
        
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Internal ERROR :\nThe parameter number is not a number.", "-- Internal Error --", JOptionPane.ERROR_MESSAGE);
                        }
        
                    }
                }
            }
        }
        else {
            
        }
    }

    /**
     * Allows a string tab creating. The string tab element has this form : parentName::elementName
     * @param listElement : the element list
     * @return a string tab
     */
    @objid ("b2373f7e-c6cb-43ee-8969-354271d51ac8")
    public static String[] createListString(final List<ModelElement> listElement) {
        List<String> listEltName = new ArrayList<String>();
        
        listEltName.add("");
        
        for (ModelElement elt: listElement) {
            listEltName.add(getCPSwarmName(elt));
        }
        
        Collections.sort(listEltName);
        String[] result = listEltName.toArray(new String[listEltName.size()]);
        return result;
    }

    /**
     * Returns the "CPSwarm" name
     * @param elt : the element
     * @return String : the marte name of the element.
     */
    @objid ("1bf0e048-da11-49ea-8659-d5cfa088ca39")
    public static String getCPSwarmName(final ModelElement elt) {
        String result = elt.getName();
        MObject owner = elt.getCompositionOwner();
        if (owner instanceof ModelElement){
            result = ((ModelElement) owner).getName() + namespacingSeparator + result;
        }
        return result;
    }

    /**
     * This method test if the SysML module is already deployed
     * @return true if the SysML module is deployed
     */
    @objid ("d14ab131-fdeb-4a7d-878b-b1e2124d3e66")
    public static boolean isSysMLDeployed() {
        return true;
    }

    /**
     * This method test if the MARTE module is already deployed
     * @return true if the MARTE module is deployed
     */
    @objid ("ae28d354-c74d-4379-9729-6fcbd287981e")
    public static boolean isMARTEDeployed() {
        return true;
    }

}
