package org.modelio.module.cpswarm.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("07c85920-753c-4c84-9b3b-a4cb4446a080")
public class I18nMessageService {
    @objid ("43e9576a-2840-42a8-8f64-d7c2de5752bd")
    private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle ("org.modelio.module.cpswarm.i18n.messages");

    @objid ("2327a526-1796-45b1-81d3-0d0e2f24e15b")
    private I18nMessageService() {
    }

    @objid ("d3b6765f-5ec0-47b4-add1-31abfbda0d4b")
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString (key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    @objid ("8bb1245f-19fd-4754-87ea-27abe9a30a62")
    public static String getString(String key, Object... params) {
        try {
            return MessageFormat.format (RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

}
