package org.eclipse.internal.cpacep.view;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class StatisticAction extends ActionEvent {
    public StatisticAction(ViewToolBar toolbar) {
	super(toolbar);
    }

    @Override
    public IPath getImagePath() {
	return new Path("icons/statistics.gif"); //$NON-NLS-1$
    }

    @Override
    public IPath getDisabledImagePath() {
	return new Path("icons/statistics.gif"); //$NON-NLS-1$
    }

    @Override
    public String getText() {
	return Messages.PropertiesViewForm_actionStatistics;
    }

    @Override
    public void updateEnablementState() {

    }

    @Override
    public void run() {

    }
}
