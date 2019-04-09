package org.modelio.module.cpswarm;

import es.addlink.tutormates.editor.Display.Main;

public class test {
    
    
    public static void main(String[] args) {
            
        
//        Display display = new Display();
//        
//        Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
//        
//        GridLayout layout = new GridLayout(2, false);
//        shell.setLayout(layout);
//        
//        Label lbl1 = new Label(shell, SWT.NONE);
//        GridData gd1 = new GridData(SWT.FILL, SWT.FILL, true, true);
//        lbl1.setLayoutData(gd1);
//        
//        Color col1 = new Color(display, 250, 155, 100);
//        lbl1.setBackground(col1);
//        col1.dispose();        
//        
//        Label lbl2 = new Label(shell, SWT.NONE);
//        GridData gd2 = new GridData(SWT.FILL, SWT.FILL, true, true);
//        gd2.heightHint = 100;
//        lbl2.setLayoutData(gd2);      
//        
//        Color col2 = new Color(display, 10, 155, 100);
//        lbl2.setBackground(col2);
//        col2.dispose();        
//        
//        Label lbl3 = new Label(shell, SWT.NONE);
//        GridData gd3 = new GridData(SWT.FILL, SWT.FILL, true, true);
//        gd3.widthHint = 300;
//        gd3.heightHint = 100;
//        gd3.horizontalSpan = 2;
//        lbl3.setLayoutData(gd3);           
//        
//        Color col3 = new Color(display, 100, 205, 200);
//        lbl3.setBackground(col3);
//        col3.dispose();        
//
//        shell.setText("Grid");
//        shell.pack();
//        shell.open();
//        
//        while (!shell.isDisposed()) {
//            if (!display.readAndDispatch())
//                display.sleep();
//        }
        
        Main main = new Main();
        main.run();
    }
    
    public double FitnessFunction(Point2D sm, Point2D e1, Point2D e2, Point2D e3) {
        
        double d1 = dist (sm, e1);
        double d2 = dist (sm, e2);
        double d3 = dist (sm, e3);
        
        return  Math.min( Math.min(d1, d2), d3);
    }
    
    public double dist(Point2D p1, Point2D p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2)  + Math.pow(p1.y - p2.y, 2));
    }
    

}
