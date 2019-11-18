package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * El único control que contiene texto, siempre encapsulado por el componente Texto.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("a0b57747-2429-4ea9-b6f4-6ef93ae59af2")
public class TextBox extends Text {
    /**
     * Tipo de texto o componentes que se podrán introducir en este TextBox.
     */
    @objid ("ac0dfa9e-f27d-4a7f-af77-ddb1d79854d9")
    private int formato;

    /**
     * Color que toma el background del TextBox cuando se encuentra lleno y sin seleccionar.
     */
    @objid ("7ae3fc70-d251-4e76-a2a1-865c7a6586a0")
    private Color colorLleno;

    /**
     * Color que toma el background del TextBox cuando se encuentra lleno y seleccionado.
     */
    @objid ("5271312c-bf92-4785-8e52-3955cec33458")
    private Color colorLlenoSeleccionado;

    /**
     * Color que toma el background del TextBox cuando se encuentra vacío y sin seleccionar.
     */
    @objid ("c86290a1-077c-4dec-9130-967938fa9563")
    private final Color colorVacio = new Color(super.getDisplay(), 210, 210, 210);

    /**
     * Color que toma el background del TextBox cuando se encuentra vacío y seleccionado.
     */
    @objid ("b840b3ca-1484-4da9-8606-63804c8b5da1")
    private final Color colorVacioSeleccionado = new Color(super.getDisplay(), 150, 150, 150);

    /**
     * Fuente y tamaño del TextBox por defecto.
     */
    @objid ("b684a1b0-9cf9-4874-949b-9b2854f8654e")
    private Font fontTexto;

    /**
     * Fuente y tamaño del TextBox cuando forma parte de un exponente.
     */
    @objid ("2c047008-3a27-47e5-9353-fa1b4c3a55f5")
    private Font fontTextoExp;

    /**
     * Constructor
     * @throws ComponentEditorException
     * @param parent Composite donde se aloja el TextBox (siempre es de tipo Texto).
     * @param colorLleno Color de background utilizado cuando el TextBox es no vacío.
     * @param colorLlenoSeleccionado Color de background utilizado cuando el TextBox está seleccionado y no vacío.
     * @param formato Tipo de texto o componentes que se podrán introducir en este TextBox.
     * @param textoInicial Cadena de texto con la que se inicializará el TextBox.
     */
    @objid ("e3660379-ae4b-4f2f-adc4-33bca83d0893")
    public TextBox(Composite parent, Color colorLleno, Color colorLlenoSeleccionado, int formato, String textoInicial) throws ComponentEditorException {
        super(parent, SWT.NONE);
        // TODO Auto-generated constructor stub
        
        try{
            this.fontTextoExp = new Font(super.getDisplay(), "Courier New", Manager.getFontSize() - 2, SWT.BOLD);
            this.fontTexto = new Font(super.getDisplay(), "Courier New", Manager.getFontSize(), SWT.BOLD);
            this.colorLleno = colorLleno;
            this.colorLlenoSeleccionado = colorLlenoSeleccionado;
            
            setFont(fontTexto);
            setText(textoInicial);
            setCursor(new Cursor(super.getDisplay(), SWT.CURSOR_IBEAM));
            
            int space = 0;
            if(Manager.isMac())
                space = 5;
            
            GC gc = new GC(this);
            FontMetrics fm = gc.getFontMetrics();
            int width = 1 * (fm.getAverageCharWidth() + space);
            int height = fm.getHeight() + space;
            gc.dispose();
            
            setSize(computeSize(width, height - 2));
            setSelection(1);
            setFocus();
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de un TextBox.",ex);
        }
    }

    /**
     * Método necesario para poder heredar una clase de Text.
     */
    @objid ("8c82c25c-6cb0-4597-b070-55ee25c4044a")
    @Override
    protected void checkSubclass() {
    }

    /**
     * Devuelve el tipo de texto o componentes que se podrán introducir en este TextBox.
     * @return El tipo de texto o componentes que se podrán introducir en este TextBox.
     */
    @objid ("020e3de0-d84a-4b79-b213-63cbc20bdf41")
    public int getFormato() {
        return formato;
    }

    /**
     * Establece el color de fondo del TextBox según lo acordado para los TextBoxes no vacíos.
     */
    @objid ("405858bf-17c6-41c1-baa1-ea038ada1a87")
    public void setColorLleno() {
        this.setBackground(colorLleno);
    }

    /**
     * Establece el color de fondo del TextBox según lo acordado para los TextBoxes no vacíos que se encuentren seleccionados.
     */
    @objid ("f0e3787c-e7b9-4ff1-81e6-644e9eeedf2f")
    public void setColorLlenoSeleccionado() {
        this.setBackground(colorLlenoSeleccionado);
    }

    /**
     * Establece el color de fondo del TextBox según lo acordado para los TextBoxes vacíos.
     */
    @objid ("9386da67-9430-4167-aba2-79940f8a2cdb")
    public void setColorVacio() {
        this.setBackground(colorVacio);
    }

    /**
     * Establece el color de fondo del TextBox según lo acordado para los TextBoxes vacíos que se encuentren seleccionados.
     */
    @objid ("8f5ff299-06b5-43ff-bce4-35c22474ecb2")
    public void setColorVacioSeleccionado() {
        this.setBackground(colorVacioSeleccionado);
    }

    /**
     * Modifica el tamaño de la fuente, ya que el TextBox pasa a formar parte de un exponente.
     */
    @objid ("3d797006-3e12-4074-9058-06c843a7e3f9")
    public void setExponente() {
        super.setFont(this.fontTextoExp);
    }

    /**
     * Establece el tipo de texto o componentes que se podrán introducir en este TextBox.
     * @param formato Nuevo formato.
     */
    @objid ("c397ca0a-b889-45fb-b1bb-87a365dfca30")
    public void setFormato(int formato) {
        this.formato = formato;
    }

}
