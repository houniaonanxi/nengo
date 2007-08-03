package ca.shu.ui.lib.util;

import ca.shu.ui.lib.actions.ReversableActionManager;
import ca.shu.ui.lib.world.impl.GFrame;

public class UIEnvironment {
	static GFrame uiInstance;

	/**
	 * 
	 * @return UI Instance
	 */
	public static GFrame getInstance() {
		return uiInstance;
	}

	/**
	 * 
	 * @param instance
	 *            UI Instance
	 */
	public static void setInstance(GFrame instance) {
		uiInstance = instance;
	}

	public static ReversableActionManager getActionManager() {
		return getInstance().getActionManager();
	}

}
