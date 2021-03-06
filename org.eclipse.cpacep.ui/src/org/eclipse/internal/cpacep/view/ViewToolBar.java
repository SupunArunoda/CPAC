package org.eclipse.internal.cpacep.view;

import org.eclipse.core.runtime.jobs.*;
import org.eclipse.cpacep.util.CPACEPConnector;
import org.eclipse.jface.action.*;

public class ViewToolBar {
    private IToolBarManager toolBarManager;
    private RunAction runAction;
    private StopAction stopAction;
    private StatisticAction statisticAction;
    private ReportAction resultTableAction;

    public ViewToolBar(IToolBarManager toolBarManager) {
	this.toolBarManager = toolBarManager;
	runAction = new RunAction(this);
	stopAction = new StopAction(this);
	statisticAction = new StatisticAction(this);
	resultTableAction = new ReportAction(this);
	enableActions(false);
	toolBarManager.add(runAction);
	toolBarManager.add(stopAction);
	toolBarManager.add(statisticAction);
	toolBarManager.add(resultTableAction);

    }

    public void enableActions(boolean enable) {
	if (enable) {
	    runAction.setEnabled(true);
	    stopAction.setEnabled(true);
	    statisticAction.setEnabled(true);
	    resultTableAction.setEnabled(true);
	    runAction.setImageDescriptor(runAction.getImageDescriptor());
	    stopAction.setImageDescriptor(stopAction.getImageDescriptor());
	} else {
	    runAction.setEnabled(false);
	    stopAction.setEnabled(false);
	    statisticAction.setEnabled(false);
	    resultTableAction.setEnabled(false);
	    runAction.setDisabledImageDescriptor(runAction.getDisabledImageDescriptor());
	    stopAction.setDisabledImageDescriptor(stopAction.getDisabledImageDescriptor());
	}
    }

    void refresh() {
	IContributionItem[] items = toolBarManager.getItems();
	for (int i = 0; i < items.length; i++) {
	    if (items[i] instanceof ActionContributionItem) {

	    }
	}
    }
}
