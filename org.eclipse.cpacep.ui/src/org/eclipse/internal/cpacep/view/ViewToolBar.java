package org.eclipse.internal.cpacep.view;

import org.eclipse.core.runtime.jobs.*;
import org.eclipse.jface.action.*;
public class ViewToolBar {
	IToolBarManager toolBarManager;


	public ViewToolBar(IToolBarManager toolBarManager) {
		this.toolBarManager = toolBarManager;
		toolBarManager.add(new RunAction(this));
		toolBarManager.add(new StopAction(this));
	}

	void refresh() {
		IContributionItem[] items = toolBarManager.getItems();
		for (int i = 0; i < items.length; i++) {
			if (items[i] instanceof ActionContributionItem) {
				
			}
		}
	}
}
