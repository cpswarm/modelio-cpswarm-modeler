package es.addlink.tutormates.equationEditor.Utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.CoolBar;

/**
 * Utility class for managing OS resources associated with SWT controls such as
 * colors, fonts, images, etc.
 * 
 * !!! IMPORTANT !!! Application code must explicitly invoke the <code>dispose()</code>
 * method to release the operating system resources managed by cached objects
 * when those objects and OS resources are no longer needed (e.g. on
 * application shutdown)
 * 
 * This class may be freely distributed as part of any application or plugin.
 * <p>
 * Copyright (c) 2003 - 2005, Instantiations, Inc. <br>All Rights Reserved
 * 
 * @author scheglov_ke
 * @author Dan Rubel
 */
@objid ("717cb869-1b89-45e9-8236-8cad8766951f")
public class SWTResourceManager {
    @objid ("eec4bcc3-298e-4fd6-85d4-0ee17ea1be1f")
    private static final int MISSING_IMAGE_SIZE = 10;

    /**
     * Style constant for placing decorator image in top left corner of base image.
     */
    @objid ("9305044f-8553-4eb3-8be9-f6f428cd9c14")
    public static final int TOP_LEFT = 1;

    /**
     * Style constant for placing decorator image in top right corner of base image.
     */
    @objid ("7ab35d3d-3704-4f14-a8fa-52444b0ef53b")
    public static final int TOP_RIGHT = 2;

    /**
     * Style constant for placing decorator image in bottom left corner of base image.
     */
    @objid ("b2aea729-daa1-45c6-b27b-6147824a30c8")
    public static final int BOTTOM_LEFT = 3;

    /**
     * Style constant for placing decorator image in bottom right corner of base image.
     */
    @objid ("0d1fdbe5-d6e2-400a-bfe2-0369b6181ef5")
    public static final int BOTTOM_RIGHT = 4;

//////////////////////////////
// Color support
//////////////////////////////
    /**
     * Maps RGB values to colors
     */
    @objid ("3894d50f-f91e-4db3-abfb-e435f2fd5f51")
    private static HashMap<RGB, Color> m_ColorMap = new HashMap<RGB, Color>();

//////////////////////////////
// Image support
//////////////////////////////
    /**
     * Maps image names to images
     */
    @objid ("7e5440dd-d4c0-44c0-bb2f-ff4130ff3851")
    private static HashMap<String, Image> m_ClassImageMap = new HashMap<String, Image>();

    /**
     * Maps images to image decorators
     */
    @objid ("c0bfd03d-60c5-4de2-9e17-b7e8b470a57f")
    private static HashMap<Image, HashMap<Image,Image>> m_ImageToDecoratorMap = new HashMap<Image, HashMap<Image, Image>>();

//////////////////////////////
// Font support
//////////////////////////////
    /**
     * Maps font names to fonts
     */
    @objid ("97853b19-4aa4-45fc-9b01-0e2cc8ca9caf")
    private static HashMap<String, Font> m_FontMap = new HashMap<String, Font>();

    /**
     * Maps fonts to their bold versions
     */
    @objid ("19a7e462-b5d7-4786-b6e7-3a49f6d6960c")
    private static HashMap<Font, Font> m_FontToBoldFontMap = new HashMap<Font, Font>();

//////////////////////////////
// Cursor support
//////////////////////////////
    /**
     * Maps IDs to cursors
     */
    @objid ("b05d2c0a-3c15-4d65-a320-e691a5bc6ed1")
    private static HashMap<Integer, Cursor> m_IdToCursorMap = new HashMap<Integer, Cursor>();

    /**
     * Dispose of cached objects and their underlying OS resources. This should
     * only be called when the cached objects are no longer needed (e.g. on
     * application shutdown)
     */
    @objid ("6c26af84-5617-4aeb-acec-41236b9b70f0")
    public static void dispose() {
        disposeColors();
        disposeFonts();
        disposeImages();
        disposeCursors();
    }

    /**
     * Returns the system color matching the specific ID
     * @param systemColorID int The ID value for the color
     * @return Color The system color matching the specific ID
     */
    @objid ("781492f4-294a-454d-af5c-e146ddb0a4ca")
    public static Color getColor(int systemColorID) {
        Display display = Display.getCurrent();
        return display.getSystemColor(systemColorID);
    }

    /**
     * Returns a color given its red, green and blue component values
     * @param r int The red component of the color
     * @param g int The green component of the color
     * @param b int The blue component of the color
     * @return Color The color matching the given red, green and blue componet values
     */
    @objid ("d1cf08c0-4a6e-43e6-94a0-6a8f33c5743a")
    public static Color getColor(int r, int g, int b) {
        return getColor(new RGB(r, g, b));
    }

    /**
     * Returns a color given its RGB value
     * @param rgb RGB The RGB value of the color
     * @return Color The color matching the RGB value
     */
    @objid ("fdd75be9-6162-403f-8244-17a9f13e02c6")
    public static Color getColor(RGB rgb) {
        Color color = m_ColorMap.get(rgb);
        if (color == null) {
            Display display = Display.getCurrent();
            color = new Color(display, rgb);
            m_ColorMap.put(rgb, color);
        }
        return color;
    }

    /**
     * Dispose of all the cached colors
     */
    @objid ("843974d0-4914-4b0b-8da5-86282191a892")
    public static void disposeColors() {
        for (Iterator<Color> iter = m_ColorMap.values().iterator(); iter.hasNext();)
             iter.next().dispose();
        m_ColorMap.clear();
    }

    /**
     * Returns an image encoded by the specified input stream
     * @param is InputStream The input stream encoding the image data
     * @return Image The image encoded by the specified input stream
     */
    @objid ("ecf5c65c-58fa-40ea-a208-a5d6b17f76ae")
    protected static Image getImage(InputStream is) {
        Display display = Display.getCurrent();
        ImageData data = new ImageData(is);
        if (data.transparentPixel > 0)
            return new Image(display, data, data.getTransparencyMask());
        return new Image(display, data);
    }

    /**
     * Returns an image stored in the file at the specified path
     * @param path String The path to the image file
     * @return Image The image stored in the file at the specified path
     */
    @objid ("8ab71759-23c5-4810-8f32-9024267de1c1")
    public static Image getImage(String path) {
        return getImage("default", path); //$NON-NLS-1$
    }

    /**
     * Returns an image stored in the file at the specified path
     * @param section The section to which belongs specified image
     * @param path String The path to the image file
     * @return Image The image stored in the file at the specified path
     */
    @objid ("cb689680-0166-4a2d-9832-f16c34a3dc5a")
    public static Image getImage(String section, String path) {
        String key = section + '|' + SWTResourceManager.class.getName() + '|' + path;
        Image image = m_ClassImageMap.get(key);
        if (image == null) {
            try {
                FileInputStream fis = new FileInputStream(path);
                image = getImage(fis);
                m_ClassImageMap.put(key, image);
                fis.close();
            } catch (Exception e) {
                image = getMissingImage();
                m_ClassImageMap.put(key, image);
            }
        }
        return image;
    }

    /**
     * Returns an image stored in the file at the specified path relative to the specified class
     * @param clazz Class The class relative to which to find the image
     * @param path String The path to the image file
     * @return Image The image stored in the file at the specified path
     */
    @objid ("aad11fb2-5e0a-47e1-bc46-fd932e10d7ad")
    public static Image getImage(Class<?> clazz, String path) {
        String key = clazz.getName() + '|' + path;
        Image image = m_ClassImageMap.get(key);
        if (image == null) {
            try {
                if (path.length() > 0 && path.charAt(0) == '/') {
                    String newPath = path.substring(1, path.length());
                    image = getImage(new BufferedInputStream(clazz.getClassLoader().getResourceAsStream(newPath)));
                } else {
                    image = getImage(clazz.getResourceAsStream(path));
                }
                m_ClassImageMap.put(key, image);
            } catch (Exception e) {
                image = getMissingImage();
                m_ClassImageMap.put(key, image);
            }
        }
        return image;
    }

    @objid ("8069363e-91a1-467d-8616-3427d4fdb416")
    private static Image getMissingImage() {
        Image image = new Image(Display.getCurrent(), MISSING_IMAGE_SIZE, MISSING_IMAGE_SIZE);
        //
        GC gc = new GC(image);
        gc.setBackground(getColor(SWT.COLOR_RED));
        gc.fillRectangle(0, 0, MISSING_IMAGE_SIZE, MISSING_IMAGE_SIZE);
        gc.dispose();
        //
        return image;
    }

    /**
     * Dispose all of the cached images
     */
    @objid ("90457a48-bee0-4e0d-844f-230a56fcb798")
    public static void disposeImages() {
        for (Iterator<Image> I = m_ClassImageMap.values().iterator(); I.hasNext();)
             I.next().dispose();
        m_ClassImageMap.clear();
        //
        for (Iterator<HashMap<Image, Image>> I = m_ImageToDecoratorMap.values().iterator(); I.hasNext();) {
            HashMap<Image, Image> decoratedMap = I.next();
            for (Iterator<Image> J = decoratedMap.values().iterator(); J.hasNext();) {
                Image image = (Image) J.next();
                image.dispose();
            }
        }
    }

    /**
     * Dispose cached images in specified section
     * @param section the section do dispose
     */
    @objid ("c3ec417f-c91b-471c-83cd-d7c8d57374c7")
    public static void disposeImages(String section) {
        for (Iterator<String> I = m_ClassImageMap.keySet().iterator(); I.hasNext();) {
            String key = I.next();
            if (!key.startsWith(section + '|'))
                continue;
            Image image = m_ClassImageMap.get(key);
            image.dispose();
            I.remove();
        }
    }

    /**
     * Returns a font based on its name, height and style
     * @param name String The name of the font
     * @param height int The height of the font
     * @param style int The style of the font
     * @return Font The font matching the name, height and style
     */
    @objid ("b3f822b9-be19-497a-ac9f-18fe650d58c1")
    public static Font getFont(String name, int height, int style) {
        return getFont(name, height, style, false, false);
    }

    /**
     * Returns a font based on its name, height and style.
     * Windows-specific strikeout and underline flags are also supported.
     * @param name String The name of the font
     * @param size int The size of the font
     * @param style int The style of the font
     * @param strikeout boolean The strikeout flag (warning: Windows only)
     * @param underline boolean The underline flag (warning: Windows only)
     * @return Font The font matching the name, height, style, strikeout and underline
     */
    @objid ("83288ee6-f54e-4d42-bae2-73fdc0d0e233")
    public static Font getFont(String name, int size, int style, boolean strikeout, boolean underline) {
        String fontName = name + '|' + size + '|' + style + '|' + strikeout + '|' + underline;
        Font font = m_FontMap.get(fontName);
        if (font == null) {
            FontData fontData = new FontData(name, size, style);
            if (strikeout || underline) {
                try {
                    Class<?> logFontClass = Class.forName("org.eclipse.swt.internal.win32.LOGFONT"); //$NON-NLS-1$
                    Object logFont = FontData.class.getField("data").get(fontData); //$NON-NLS-1$
                    if (logFont != null && logFontClass != null) {
                        if (strikeout) {
                            logFontClass.getField("lfStrikeOut").set(logFont, new Byte((byte) 1)); //$NON-NLS-1$
                        }
                        if (underline) {
                            logFontClass.getField("lfUnderline").set(logFont, new Byte((byte) 1)); //$NON-NLS-1$
                        }
                    }
                } catch (Throwable e) {
                    System.err.println(
                        "Unable to set underline or strikeout" + " (probably on a non-Windows platform). " + e); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
            font = new Font(Display.getCurrent(), fontData);
            m_FontMap.put(fontName, font);
        }
        return font;
    }

    /**
     * Return a bold version of the give font
     * @param baseFont Font The font for whoch a bold version is desired
     * @return Font The bold version of the give font
     */
    @objid ("23941d46-9764-41b0-8974-8d23a3474a94")
    public static Font getBoldFont(Font baseFont) {
        Font font = m_FontToBoldFontMap.get(baseFont);
        if (font == null) {
            FontData fontDatas[] = baseFont.getFontData();
            FontData data = fontDatas[0];
            font = new Font(Display.getCurrent(), data.getName(), data.getHeight(), SWT.BOLD);
            m_FontToBoldFontMap.put(baseFont, font);
        }
        return font;
    }

    /**
     * Dispose all of the cached fonts
     */
    @objid ("123a7237-3702-403f-a480-cfb3fd0f29eb")
    public static void disposeFonts() {
        for (Iterator<Font> iter = m_FontMap.values().iterator(); iter.hasNext();)
             iter.next().dispose();
        m_FontMap.clear();
    }

//////////////////////////////
// CoolBar support
//////////////////////////////
    /**
     * Fix the layout of the specified CoolBar
     * @param bar CoolBar The CoolBar that shgoud be fixed
     */
    @objid ("5be6eab2-78a5-4823-9c0c-837e64205986")
    public static void fixCoolBarSize(CoolBar bar) {
        CoolItem[] items = bar.getItems();
        // ensure that each item has control (at least empty one)
        for (int i = 0; i < items.length; i++) {
            CoolItem item = items[i];
            if (item.getControl() == null)
                item.setControl(new Canvas(bar, SWT.NONE) {
                @Override
                public Point computeSize(int wHint, int hHint, boolean changed) {
                    return new Point(20, 20);
                }
            });
        }
        // compute size for each item
        for (int i = 0; i < items.length; i++) {
            CoolItem item = items[i];
            Control control = item.getControl();
            control.pack();
            Point size = control.getSize();
            item.setSize(item.computeSize(size.x, size.y));
        }
    }

    /**
     * Returns the system cursor matching the specific ID
     * @param id int The ID value for the cursor
     * @return Cursor The system cursor matching the specific ID
     */
    @objid ("e6a9dace-d09f-41e3-b02d-3aace3ad21b5")
    public static Cursor getCursor(int id) {
        Integer key = new Integer(id);
        Cursor cursor = m_IdToCursorMap.get(key);
        if (cursor == null) {
            cursor = new Cursor(Display.getDefault(), id);
            m_IdToCursorMap.put(key, cursor);
        }
        return cursor;
    }

    /**
     * Dispose all of the cached cursors
     */
    @objid ("87f6c1b3-7b3f-4fc1-b412-392ddd420be6")
    public static void disposeCursors() {
        for (Iterator<Cursor> iter = m_IdToCursorMap.values().iterator(); iter.hasNext();)
             iter.next().dispose();
        m_IdToCursorMap.clear();
    }

}
