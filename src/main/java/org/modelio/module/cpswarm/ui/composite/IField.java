package org.modelio.module.cpswarm.ui.composite;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * Represents a field in an edition form.<br/>
 * A field is composed of:
 * <ul>
 * <li>a label - informative</li>
 * <li>a control - where edition takes place</li>
 * </ul>
 */
@objid ("21d99f61-21d8-4f92-9451-920ccf08bce9")
public interface IField {
    /**
     * Layouts the label, edition control and help button in their container ({@link IField#getComposite()})
     * 
     * This method can be redefined by subclasses who need to change the standard layout for label, control and help button.<br/>
     * When it is called default LayoutData have already been applied to the widgets so that this method can typically either modify
     * or replace the existing LayoutData of the widgets.
     * 
     * The container layout is a FormLayout, therefore widget LayoutData must be FormData.
     * 
     * Note the the helpText will always be layouted so that it appears under the control (same width and X position).
     */
    @objid ("37529bf9-74b9-4378-bfe6-8806781fe099")
    void layout(Label label, Control control);

    /**
     * Gets the top most container control of the field which must be a <code>Composite</code>.
     */
    @objid ("65ef0db2-f952-48d5-b40b-88278c161750")
    Composite getComposite();

    /**
     * Gets the control in charge of displaying/editing the field value.
     */
    @objid ("7feec682-ba73-45be-baac-5a26b0864c08")
    Control getControl();

    /**
     * Refresh the value displayed by the field. The field will typically query its IFiledData model and refresh its contents.
     */
    @objid ("d915f43e-b46c-4c81-8c93-2429f161d599")
    void refresh();

    @objid ("cf983ae2-fb03-487e-baff-2a2cf77d624f")
    void setEditable(boolean onoff);

    /**
     * Gets the IfieldData model of this field.
     */
    @objid ("1d5ccd94-97df-4b3b-bd41-259aa4c8172a")
    String getValue();

    @objid ("e31e8b2c-601b-4ed1-b853-12059487170d")
    void setValue(String data);

}
