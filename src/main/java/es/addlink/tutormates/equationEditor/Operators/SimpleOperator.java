package es.addlink.tutormates.equationEditor.Operators;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.ComponentEditorException;
import es.addlink.tutormates.equationEditor.Exceptions.EditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Manager.SimpleOperatorManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

/**
 * Componente que encapsula un sólo TextBox. Es el único componente capaz de albergar texto.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("b9915dab-2010-4f07-b64f-902dfdde53fd")
public class SimpleOperator extends Operator {
    /**
     * True si se desea que un TextBox cambie de color cuando se encuentre vacío, false en caso contrario.
     */
    @objid ("f156fd50-f8b8-4ca9-a24b-feb973f61d78")
    private final boolean cambiaColorSiEstaVacio = true;

    /**
     * TextBox contenido en Texto, y en el que se guardará el texto introducido por teclado.
     */
    @objid ("3a4fa563-8a16-4d28-88d8-ed46585e8ab8")
    private TextBox textbox;

    @objid ("124d1e26-f4bc-47b4-ac3f-b029c68ca63a")
    private Manager manager;

    /**
     * Gestion de los listeners de un Texto y un TextBox
     */
    @objid ("050c3167-cfbb-4b9c-bf0a-757faa0291c0")
    private SimpleOperatorManager gestion;

    /**
     * Constructor
     * @throws ComponentEditorException
     * @param cuadriculaPadre Cuadrícula donde se aloja el componente.
     * @param formato Tipo de texto o componentes que se podrán introducir en este Texto.
     * @param textoInicial Texto con el que se cargará el componente Texto.
     */
    @objid ("7b2d664c-0536-4e58-835b-aacb858b3028")
    public SimpleOperator(Manager manager, GridExpression cuadriculaPadre, int formato, String textoInicial) throws ComponentEditorException {
        super(manager, cuadriculaPadre, false, "texto", "texto", -1);
        // TODO Auto-generated constructor stub
        
        try{
            this.manager = manager;
            this.textbox = new TextBox(super.getCmpTodo(), super.getColorFondo(), super.getColorFondoSeleccionado(), formato, textoInicial);
            
            this.manager.getStateManager().setTextoActivo(this);
            this.manager.getStateManager().setPosicionCursor(this.getPosicionCursor());
            
            this.gestion = new SimpleOperatorManager(this.manager, this);
            if (textoInicial != "")
                gestion.ajustar(true);
            
            if (cuadriculaPadre.esExponente())
                setExponente();
            
            if (this.getCambiaColorSiEstaVacio()) {
                if (this.getNumCaracteres() > 0)
                    this.setColorLleno();
                else
                    this.setColorVacio();
            }
            
            super.getCmpTodo().pack();
            super.setPosicionCentral(getPosicionCentral());
            listeners();
            
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de un Texto.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#cambioPosicionCentralEnCuadricula()
     */
    @objid ("cd757f35-d24d-48b0-9df7-f5c4a2620ba9")
    @Override
    protected void cambioPosicionCentralEnCuadricula() {
    }

/*
     * (non-Javadoc)
     * 
     * @see Components.Componente#deseleccionar()
     */
    @objid ("23b7a8b3-e0c3-443e-a003-837eccbe6a61")
    @Override
    public void deseleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.deseleccionar();
        setBackground(super.getColorFondo());
        
        if (getNumCaracteres() > 0)
            this.setColorLleno();
        else if (this.cambiaColorSiEstaVacio)
            this.setColorVacio();
        else
            this.setColorLleno();
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#eliminaComponentesInternosSeleccionados()
     */
    @objid ("35dd8fb6-eb02-41de-8dd0-f8ca73a2cb67")
    @Override
    public void eliminaComponentesInternosSeleccionados() {
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#eliminaTextosVacios()
     */
    @objid ("bf5e15f0-137d-4d87-8684-803fe06fe902")
    @Override
    public void eliminaTextosVacios() {
        // TODO Auto-generated method stub
    }

    /**
     * Devuelve true si el componente Texto se encuentra seleccionado, false en caso contrario.
     * @return True si el componente Texto se encuentra seleccionado, false en caso contrario.
     */
    @objid ("00e92948-aaa9-4604-994e-6303185e26fa")
    @Override
    public boolean estaSeleccionado() {
        return super.estaSeleccionado();
    }

    /**
     * Devuelve true si el TextBox cambia su color de fondo cuando se encuentra vacío, false en caso contrario.
     * @return True si el TextBox cambia su color de fondo cuando se encuentra vacío, false en caso contrario.
     */
    @objid ("168fbaf8-dbd4-44bd-af8f-1bb3d60935fd")
    public boolean getCambiaColorSiEstaVacio() {
        return this.cambiaColorSiEstaVacio;
    }

    /**
     * Devuelve una cadena que contiene los caracteres que se encuentran seleccionados.
     * @return Cadena que contiene los caracteres que se encuentran seleccionados.
     */
    @objid ("d0272beb-9b35-434c-a425-1cbbc9b1aa65")
    public String getCaracteresSeleccionados() {
        return this.textbox.getSelectionText();
    }

    /**
     * Calcula un tamaño para el TextBox de acuerdo a los parámetros de entrada.
     * @param anchura Entero que contiene la anchura.
     * @param altura Entero que contiene la altura.
     * @return El Point con el nuevo tamaño.
     */
    @objid ("51651559-6e87-4088-a2b4-9df6263848a7")
    public Point getComputeSize(int anchura, int altura) {
        return this.textbox.computeSize(anchura, altura);
    }

    /**
     * Devuelve el tipo de texto o componentes que se podrán introducir en este Texto.
     * @return El tipo de texto o componentes que se podrán introducir en este Texto.
     */
    @objid ("c688ebc7-858e-45a9-bfbf-d5d96f6655a9")
    public int getFormato() {
        return this.textbox.getFormato();
    }

    /**
     * Devuelve el GC del TextBox.
     * @return GC del TextBox.
     */
    @objid ("1c1b203b-d8d3-47d1-9a76-4aae5a182fab")
    public GC getGC() {
        return new GC(this.textbox);
    }

    /**
     * Devuelve la longitud del texto del TextBox.
     * @return La longitud del texto.
     */
    @objid ("11c5a64e-d699-4e19-9166-b5a1c7e8d484")
    public int getNumCaracteres() {
        return this.textbox.getCharCount();
    }

    /**
     * Devuelve el número de caracteres seleccionados.
     * @return Número de caracteres seleccionados.
     */
    @objid ("f11773c3-91d6-432c-a698-a99fc00669e7")
    public int getNumCaracteresSeleccionados() {
        return this.textbox.getSelectionCount();
    }

/*
     * (non-Javadoc)
     * 
     * @see Components.Componente#getPosicionCentral()
     */
    @objid ("09085b99-8a47-4c1e-9b06-4562e98cfc81")
    @Override
    public int getPosicionCentral() {
        int totalAltura = super.getCmpTodo().getSize().y;
        return totalAltura / 2;
    }

    /**
     * Devuelve la posición actual del cursor en el TextBox.
     * @return Posición actual del cursor en el TextBox.
     */
    @objid ("310dd982-78ca-4e5b-8f8c-95f9913c114f")
    public int getPosicionCursor() {
        return this.textbox.getCaretPosition();
    }

    /**
     * Devuelve el tamaño del TextBox.
     * @return Tamaño del TextBox.
     */
    @objid ("c9876786-1207-47a5-b583-5579b5eff571")
    public Point getSizeTextBox() {
        return this.textbox.getSize();
    }

    /**
     * Devuelve el TextBox contenido en el componente de tipo Texto.
     * @return El TextBox contenido en el componente de tipo Texto.
     */
    @objid ("521f4264-af6f-4e62-983c-d7b5d259b318")
    public TextBox getTextBox() {
        return this.textbox;
    }

    /**
     * Devuelve el texto que se encuentra en el TextBox.
     * @return El texto.
     */
    @objid ("60fb10b7-2da0-4219-b274-1f737a8c390c")
    public String getTexto() {
        return this.textbox.getText();
    }

    /**
     * Método encargado de asignar listeners a Texto y TextBox.
     */
    @objid ("053dcd4d-6da4-4929-b9e4-6d906a15a69c")
    private void listeners() {
        // Controla el tamaño del componente Texto
        this.addControlListener(this.gestion.getListenerResized());
        
        // El TextBox recibe o pierde el foco
        this.textbox.addFocusListener(this.gestion.getListenerFocus());
        
        // Pulsación con cualquier botón del ratón dentro de un TextBox
        this.textbox.addListener(SWT.MouseDown, this.gestion.getListenerClick());
        
        // Pulsación con el teclado estando el foco en un TextBox
        this.textbox.addKeyListener(this.gestion.getListenerKey());
        
        // Este adaptador es manejado desde fuera del editor. Se puede utilizar para captar los "enters" desde fuera.
        this.textbox.addKeyListener(this.manager.getTutorMatesEditor().getKeyAdapter());
        //this.textbox.addKeyListener(TutorMatesEditorSingleton.getInstance().getKeyAdapter());
        
        // Verifica los caracteres antes de ser introducidos en el TextBox. Siguiento unas políticas, permitirá introducirlos o no.
        this.textbox.addVerifyListener(this.gestion.getListenerVerify());
        
        // El contenido del TextBox es modificado.
        this.textbox.addModifyListener(this.gestion.getListenerModify());
        
        // Controla los menús contextuales que se intentan ejecutar al pulsar con el botón derecho del ratón sobre el TextBox.
        //this.textbox.addMenuDetectListener(this.gestion.getListenerMenu());
    }

/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Control#pack()
     */
    @objid ("3f8a91fc-fb1c-4f02-900e-39777f9adcb9")
    @Override
    public void pack() {
        this.textbox.pack();
        super.getCmpTodo().pack();
        super.pack();
        this.getCuadriculaPadre().pack();
    }

    /**
     * Realiza un pack a todos las partes de Texto menos al TextBox.
     */
    @objid ("e7150951-50e9-4c92-b8c4-e32655140720")
    public void packSinTextBox() {
        super.getCmpTodo().pack();
        super.pack();
        this.getCuadriculaPadre().pack();
    }

/*
     * (non-Javadoc)
     * 
     * @see Components.Componente#seleccionar()
     */
    @objid ("586c7207-50d9-453f-98cf-bbd23fe049fd")
    @Override
    public void seleccionar() throws ComponentEditorException {
        // TODO Auto-generated method stub
        super.seleccionar();
        
        setBackground(super.getColorFondoSeleccionado());
        this.textbox.setColorLlenoSeleccionado();
        if ((this.textbox.getCharCount() == 0) && (this.cambiaColorSiEstaVacio))
            this.textbox.setColorVacioSeleccionado();
    }

    /**
     * Establece el color lleno al TextBox.
     */
    @objid ("e5e52873-ac49-438c-bc76-c1c8ed22e6c5")
    public void setColorLleno() {
        this.textbox.setColorLleno();
    }

    /**
     * Establece el color lleno al TextBox seleccionado.
     */
    @objid ("f14fa27d-78a1-44be-93a7-48687ddae240")
    public void setColorLlenoSeleccionado() {
        this.textbox.setColorLlenoSeleccionado();
    }

    /**
     * Establece el color vacío al TextBox.
     */
    @objid ("fcb6c219-4ab8-4814-9b73-c677b44fd565")
    public void setColorVacio() {
        this.textbox.setColorVacio();
    }

    /**
     * Establece el color vacío al TextBox seleccionado.
     */
    @objid ("4ee70fc6-3160-4752-bab9-c680a571318a")
    public void setColorVacioSeleccionado() {
        this.textbox.setColorVacioSeleccionado();
    }

    /**
     * Comunica al TextBox que forma parte de un exponente.
     */
    @objid ("a8d90c1a-a51b-4122-a44b-969955a620cf")
    public void setExponente() {
        this.textbox.setExponente();
    }

    /**
     * Establece el foco en el TextBox.
     */
    @objid ("a376493c-f165-4b10-9fda-876ae6487492")
    public void setFoco() {
        this.textbox.setFocus();
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#setFocoFinal()
     */
    @objid ("99873021-f97a-426c-a11c-0f8921d2a6ec")
    @Override
    public void setFocoFinal() throws ComponentEditorException {
        try{
            this.textbox.setFocus();
            this.textbox.setSelection(getNumCaracteres(), getNumCaracteres());
            this.manager.getStateManager().setTextoActivo(this);
            this.manager.getStateManager().setPosicionCursor(getNumCaracteres());
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de un periódico puro.",ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see es.addlink.tutormates.editor.Components.Componente#setFocoInicio()
     */
    @objid ("f7ace8eb-bba6-4f41-82c2-5c29180cfb24")
    @Override
    public void setFocoInicio() throws ComponentEditorException {
        try{
            this.textbox.setFocus();
            this.textbox.setSelection(0, 0);
            this.manager.getStateManager().setTextoActivo(this);
            this.manager.getStateManager().setPosicionCursor(0);
        }catch(Exception ex){
            throw new ComponentEditorException("Error en la construcción de un periódico puro.",ex);
        }
    }

    /**
     * Establece el tipo de texto o componentes que se podrán introducir en este Texto.
     * @param formato Nuevo formato.
     */
    @objid ("f080c9cf-5bb9-413f-a739-9f4d56d675bd")
    public void setFormato(int formato) {
        this.textbox.setFormato(formato);
    }

/*
     * (non-Javadoc)
     * 
     * @see Components.Componente#setPosicionCentral()
     */
    @objid ("e6fe4d6a-87a2-4bce-9840-9406fd9f7645")
    @Override
    public void setPosicionCentral(int posicion) throws ComponentEditorException {
        super.setPosicionCentral(posicion);
    }

    /**
     * Establece la posición del cursor en el TextBox.
     * @param posicion Nueva posición del cursor en el TextBox.
     */
    @objid ("cf73b7f6-865d-4712-891f-dcabe6806ee3")
    public void setPosicionCursor(int posicion) {
        this.textbox.setSelection(posicion);
    }

    /**
     * Establece el tamaño al TextBox.
     * @param punto Nuevo tamaño que tendrá el TextBox.
     */
    @objid ("f5539d89-ac4c-4c27-939a-aef26cd1e299")
    public void setSizeTextBox(Point punto) {
        this.textbox.setSize(punto);
    }

    /**
     * Establece el texto en el TextBox.
     * @param string Nuevo texto que tendrá el TextBox.
     */
    @objid ("5ac63140-f4e1-45f6-bdb5-1b2cc7867115")
    public void setTexto(String string) throws ComponentEditorException {
        try{
            this.textbox.setText(string);
            this.gestion.ajustar(true);
        }catch(EditorException ex){
            throw new ComponentEditorException(ex);
        }
    }

/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Widget#toString()
     */
    @objid ("60ae69c5-bc0b-4a73-af5f-bbb78eff593e")
    @Override
    public String toString() {
        return getTexto();
    }

}
