/*
The contents of this file are subject to the Mozilla Public License Version 1.1
(the "License"); you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.mozilla.org/MPL/

Software distributed under the License is distributed on an "AS IS" basis, WITHOUT
WARRANTY OF ANY KIND, either express or implied. See the License for the specific
language governing rights and limitations under the License.

The Original Code is "RemoveModelAction.java". Description:
"Action for removing this UI Wrapper and the model

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

import java.util.ArrayList;

import javax.swing.JOptionPane;

import ca.nengo.ui.lib.actions.UserCancelledException;
import ca.nengo.ui.lib.actions.StandardAction;
import ca.nengo.ui.lib.objects.models.ModelObject;
import ca.nengo.ui.lib.util.UIEnvironment;

/**
 * Action for removing this UI Wrapper and the model
 * 
 * @author Shu Wu
 */
public class RemoveModelAction extends StandardAction {

    private static final long serialVersionUID = 1L;
    ArrayList<ModelObject> modelsToRemove = null;

    /**
     * @param actionName TODO
     * @param modelToRemove TODO
     */
    public RemoveModelAction(String actionName, ArrayList<ModelObject> modelsToRemove) {
        super("Remove " + (modelsToRemove.size() == 1 ? modelsToRemove.get(0).getTypeName() : "selection"), actionName);
        this.modelsToRemove = modelsToRemove;
    }

    @Override
    protected void action() throws UserCancelledException {
        int response = JOptionPane.showConfirmDialog(UIEnvironment
                .getInstance(),
                "Once an object has been removed, it cannot be undone.",
                "Are you sure?", JOptionPane.YES_NO_OPTION);
        if (response == 0) {
        	for (ModelObject modelToRemove : modelsToRemove) {
        		modelToRemove.destroyModel();
        	}
        	modelsToRemove = null;
        } else {
            throw new UserCancelledException();
        }
    }

}