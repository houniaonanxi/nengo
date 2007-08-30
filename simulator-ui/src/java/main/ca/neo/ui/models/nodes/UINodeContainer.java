package ca.neo.ui.models.nodes;

import java.lang.ref.WeakReference;

import ca.neo.model.Node;
import ca.neo.ui.actions.SaveNodeContainerAction;
import ca.neo.ui.models.UINeoNode;
import ca.neo.ui.models.tooltips.PropertyPart;
import ca.neo.ui.models.tooltips.TooltipBuilder;
import ca.neo.ui.models.viewers.NodeViewer;
import ca.shu.ui.lib.actions.StandardAction;
import ca.shu.ui.lib.exceptions.ActionException;
import ca.shu.ui.lib.objects.Window;
import ca.shu.ui.lib.objects.Window.WindowState;
import ca.shu.ui.lib.util.PopupMenuBuilder;

/**
 * UI Wrapper for Node Containers such as Ensembles and Networks.
 * 
 */
/**
 * @author Shu
 * 
 */
public abstract class UINodeContainer extends UINeoNode {

	private static final long serialVersionUID = 1L;

	/**
	 * Weak reference to the viewer window
	 */
	private WeakReference<Window> viewerWindowRef = new WeakReference<Window>(
			null);

	public UINodeContainer() {
		super();
	}

	public UINodeContainer(Node model) {
		super(model);
	}

	@Override
	protected PopupMenuBuilder constructMenu() {
		PopupMenuBuilder menu = super.constructMenu();

		menu.addSection("File");
		menu.addAction(new SaveNodeContainerAction(
				"Save " + this.getTypeName(), this));

		menu.addSection("Container");
		if (viewerWindowRef.get() == null
				|| viewerWindowRef.get().isDestroyed()
				|| (viewerWindowRef.get().getWindowState() == Window.WindowState.MINIMIZED)) {

			menu.addAction(new StandardAction("Open viewer") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void action() throws ActionException {
					openViewer();
				}
			});

		} else {
			menu.addAction(new StandardAction("Close viewer") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void action() throws ActionException {
					closeViewer();
				}
			});

		}

		return menu;

	}

	@Override
	protected TooltipBuilder constructTooltips() {
		TooltipBuilder tooltips = super.constructTooltips();

		tooltips.addPart(new PropertyPart("# Nodes", "" + getNodesCount()));
		return tooltips;
	}

	/**
	 * Creates a new Viewer
	 * 
	 * @return Viewer created
	 */
	protected abstract NodeViewer createViewerInstance();

	/**
	 * @return Viewer Window
	 */
	protected Window getViewerWindow() {

		if (viewerWindowRef.get() == null
				|| viewerWindowRef.get().isDestroyed()) {
			Window viewerWindow = new Window(this, createViewerInstance());

			viewerWindowRef = new WeakReference<Window>(viewerWindow);

		}

		return viewerWindowRef.get();

	}

	@Override
	protected void prepareForDestroy() {
		closeViewer();
		super.prepareForDestroy();
	}

	/**
	 * Closes the viewer Window
	 */
	public void closeViewer() {
		if (viewerWindowRef.get() != null)
			viewerWindowRef.get().destroy();

	}

	@Override
	public void doubleClicked() {
		openViewer();
	}

	/**
	 * @return The file name for saving this node container
	 */
	public abstract String getFileName();

	/**
	 * @return Number of nodes contained by the Model
	 */
	public abstract int getNodesCount();

	/**
	 * @return Container Viewer
	 */
	public NodeViewer getViewer() {
		if (viewerWindowRef.get() != null) {
			return (NodeViewer) viewerWindowRef.get().getWindowContent();
		}
		return null;
	}

	/**
	 * Opens the Container Viewer
	 * 
	 * @return the Container viewer
	 */
	public NodeViewer openViewer() {
		Window viewerWindow = getViewerWindow();

		if (viewerWindow.getWindowState() == WindowState.MINIMIZED) {
			viewerWindow.restoreWindow();
		}

		return (NodeViewer) viewerWindow.getWindowContent();

	}

	/**
	 * Saves the configuration of this node container
	 */
	public abstract void saveContainerConfig();

	/**
	 * @param fileName
	 *            New file Name
	 */
	public abstract void setFileName(String fileName);

}
