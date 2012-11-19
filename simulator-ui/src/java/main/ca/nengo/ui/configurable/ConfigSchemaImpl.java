/*
The contents of this file are subject to the Mozilla Public License Version 1.1
(the "License"); you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.mozilla.org/MPL/

Software distributed under the License is distributed on an "AS IS" basis, WITHOUT
WARRANTY OF ANY KIND, either express or implied. See the License for the specific
language governing rights and limitations under the License.

The Original Code is "ConfigSchemaImpl.java". Description:
"Default implementation of a IConfigSchema

  @author Shu Wu"

The Initial Developer of the Original Code is Bryan Tripp & Centre for Theoretical Neuroscience, University of Waterloo. Copyright (C) 2006-2008. All Rights Reserved.

Alternatively, the contents of this file may be used under the terms of the GNU
Public License license (the GPL License), in which case the provisions of GPL
License are applicable  instead of those above. If you wish to allow use of your
version of this file only under the terms of the GPL License and not to allow
others to use your version of this file under the MPL, indicate your decision
by deleting the provisions above and replace  them with the notice and other
provisions required by the GPL License.  If you do not delete the provisions above,
a recipient may use your version of this file under either the MPL or the GPL License.
 */

package ca.nengo.ui.configurable;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of a IConfigSchema
 * 
 * @author Shu Wu
 */
public class ConfigSchemaImpl implements ConfigSchema {
    private List<Property> advancedProperties;
    private List<Property> properties;

    /**
     * Default constructor, no property descriptors
     */
    public ConfigSchemaImpl() {
        this(new Property[] {}, new Property[] {});
    }

    /**
     * @param property A single property
     */
    public ConfigSchemaImpl(Property property) {
        this(new Property[] { property }, new Property[] {});
    }

    /**
     * @param properties An array of properties
     */
    public ConfigSchemaImpl(Property[] properties) {
        this(properties, new Property[] {});
    }

    /**
     * @param properties Properties always shown
     * @param advancedProperties Properties only shown in advanced mode
     */
    public ConfigSchemaImpl(Property[] properties,
            Property[] advancedProperties) {
        this.properties = new ArrayList<Property>(properties.length);
        for (Property property : properties) {
            this.properties.add(property);
        }

        this.advancedProperties = new ArrayList<Property>(properties.length);
        for (Property property : advancedProperties) {
            this.advancedProperties.add(property);
        }
    }

    /**
     * @param prop Property to add
     * @param position Location to insert into the property list
     */
    public void addProperty(Property prop, int position) {
        properties.add(position, prop);
    }

    public List<Property> getAdvancedProperties() {
        return advancedProperties;
    }

    public List<Property> getProperties() {
        return properties;
    }
}
