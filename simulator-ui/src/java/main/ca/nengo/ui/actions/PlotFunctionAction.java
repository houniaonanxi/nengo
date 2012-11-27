/*
The contents of this file are subject to the Mozilla Public License Version 1.1
(the "License"); you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.mozilla.org/MPL/

Software distributed under the License is distributed on an "AS IS" basis, WITHOUT
WARRANTY OF ANY KIND, either express or implied. See the License for the specific
language governing rights and limitations under the License.

The Original Code is "PlotFunctionAction.java". Description:
"Plots a function node, which can contain multiple functions

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

package ca.nengo.ui.actions;

import java.util.Map;

import javax.swing.JDialog;

import ca.nengo.math.Function;
import ca.nengo.ui.configurable.ConfigException;
import ca.nengo.ui.configurable.Property;
import ca.nengo.ui.configurable.descriptors.PFloat;
import ca.nengo.ui.configurable.managers.ConfigManager;
import ca.nengo.ui.configurable.managers.ConfigManager.ConfigMode;
import ca.nengo.ui.lib.actions.ActionException;
import ca.nengo.ui.lib.actions.StandardAction;
import ca.nengo.ui.lib.util.UIEnvironment;
import ca.nengo.ui.util.DialogPlotter;

/**
 * Plots a function node, which can contain multiple functions
 * 
 * @author Shu Wu
 */
public class PlotFunctionAction extends StandardAction {
    private static final long serialVersionUID = 1L;

    static final Property pEnd = new PFloat("End");
    static final Property pIncrement = new PFloat("Increment");
    static final Property pStart = new PFloat("Start");

    static final Property[] propD = { pStart, pIncrement, pEnd };
    private Function function;

    private String plotName;

    private JDialog dialogParent;

    /**
     * @param plotName TODO
     * @param function TODO
     * @param dialogParent TODO
     */
    public PlotFunctionAction(String plotName, Function function, JDialog dialogParent) {
        super("Plot function");
        this.plotName = plotName;
        this.function = function;
        this.dialogParent = dialogParent;
        pStart.setDescription("Time (in seconds) to start the graph (usually 0)");
        pIncrement.setDescription("Resolution (in seconds) of the graph (usually 0.001)");
        pEnd.setDescription("Time (in seconds) of the end of the graph");
    }

    @Override
    protected void action() throws ActionException {

        try {
        	Map<Property, Object> properties = ConfigManager.configure(propD,
                    "Function Node plotter", UIEnvironment.getInstance(),
                    ConfigMode.TEMPLATE_NOT_CHOOSABLE);
            String title = plotName + " - Function Plot";

            float start = (Float) properties.get(pStart);
            float end = (Float) properties.get(pEnd);
            float increment = (Float) properties.get(pIncrement);

            if (increment == 0) {
                throw new ActionException("Please use a non-zero increment");
            }

            DialogPlotter plotter = new DialogPlotter(dialogParent);

            try {
                plotter.doPlot(function, start, increment, end, title + " ("
                        + function.getClass().getSimpleName() + ")");
            } catch (Exception e) {
                throw new ConfigException(e.getMessage());
            }

        } catch (ConfigException e) {
            e.defaultHandleBehavior();
        }

    }

}