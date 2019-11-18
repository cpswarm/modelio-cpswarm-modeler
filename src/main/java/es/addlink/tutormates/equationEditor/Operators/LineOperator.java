package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Se trata de una clase que contiene un label con una línea horizontal, con capacidad para alargarla o acortarla. Es útil para las raices cuadras o fracciones.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("50dfc585-2bd5-46db-9ec9-893ab023d8d3")
public class LineOperator extends Composite {
    /**
     * Número de caracteres que debe tener la línea horizontal.
     */
    @objid ("4fb1ed0d-25c5-4e18-a995-12cfe12207d1")
    private int numCaracteres;

    /**
     * Fuente y tamaño por defecto para representar la línea horizontal.
     */
    @objid ("80551873-8f28-44aa-a271-6297808f30a7")
    private final Font fuente = new Font(super.getDisplay(), "Courier New", 10, SWT.BOLD);

    /**
     * Label donde se insertará la linea horizontal.
     */
    @objid ("0826c5b3-ac16-4b77-b2b1-cc080bd89ed4")
    private Label lblLinea;

    /**
     * Constructor
     * @throws ComponentEditorException
     * @param c Composite donde se encuentra alojado el objeto.
     */
    @objid ("25785ad9-118d-4664-a056-766f613305ae")
    public LineOperator(Composite c) throws ComponentEditorException {
        super(c, SWT.CURSOR_HAND);
        try{
            this.lblLinea = new Label(this, SWT.NONE);
            this.lblLinea.setFont(this.fuente);
            this.numCaracteres = 0;
            aplicarLinea();
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el cálculo de la longitud de una línea superior según el número de carácteres que alberga.",ex);
        }
    }

    /**
     * Aplica los cambios en la línea horizontal, una vez que se ha modificado el número de caracteres.
     * @throws ComponentEditorException
     */
    @objid ("0d92dbf9-aac9-43b1-a10d-462a6045662b")
    private void aplicarLinea() throws ComponentEditorException {
        try{
            this.lblLinea.setText("");
            int ancho = 0;
            this.numCaracteres++;
            
            for (int i = 0; i < this.numCaracteres; i++) {
                this.lblLinea.setText(this.lblLinea.getText() + "_");
                ancho += 5;
            }
            
            if (this.numCaracteres == 1) {
                int iniChars = 2;
                setLongitudInicial(iniChars);
                ancho += (5 * iniChars);
            }
            
            this.lblLinea.setBackground(new Color(super.getDisplay(), 0, 0, 0));
            this.lblLinea.setSize(ancho, 1);
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el ajuste de una línea superior.",ex);
        }
    }

    /**
     * Método necesario para poder heredar una clase de Label.
     */
    @objid ("ca35a464-2bce-4ba6-ba68-b8c0ac51b9cc")
    @Override
    protected void checkSubclass() {
    }

    /**
     * Establece una longitud de caracteres inicial.
     * @throws ComponentEditorException
     */
    @objid ("e7a32544-8dda-42f3-83d0-ec78f2454ff1")
    public void setLongitudInicial(int num) throws ComponentEditorException {
        try{
            for (int i = 0; i < this.numCaracteres; i++)
                this.lblLinea.setText(this.lblLinea.getText() + "_");
        
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el cálculo de la longitud de una línea superior.",ex);
        }
    }

    /**
     * Establece el número de caractéres para la línea horizontal.
     * @throws ComponentEditorException
     * @param longitudCmp Número de caractéres para la línea horizontal.
     */
    @objid ("7a25621c-c669-4499-bbee-5e208a820c6b")
    public void setNumCaracteres(int longitudCmp) throws ComponentEditorException {
        try{
            int numCar;
            numCar = (longitudCmp + 0) / 5;
            
            if (numCar >= 0) {
                this.numCaracteres = numCar;
                aplicarLinea();
            }
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en el cálculo de la longitud de una línea superior según el número de carácteres que alberga.",ex);
        }
    }

}
