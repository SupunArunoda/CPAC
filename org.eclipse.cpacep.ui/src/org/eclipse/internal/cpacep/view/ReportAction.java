package org.eclipse.internal.cpacep.view;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.internal.cpacep.dialog.Report;

public class ReportAction extends ActionEvent {
    public ReportAction(ViewToolBar toolbar) {
	super(toolbar);
    }

    @Override
    public IPath getImagePath() {
	return new Path("icons/result_table.gif"); //$NON-NLS-1$
    }

    @Override
    public IPath getDisabledImagePath() {
	return new Path("icons/result_table.gif"); //$NON-NLS-1$
    }

    @Override
    public String getText() {
	return Messages.PropertiesViewForm_actionResult;
    }

    @Override
    public void updateEnablementState() {

    }

    @Override
    public void run() {
	CpacepView cpacepView = CpacepView.getViewInstance();
	String report = null;
	report = cpacepView.getCPACEPConncetor().readReport();
	Report reportDialog = new Report(cpacepView.getComposite().getShell());
	reportDialog.setReport(report);
	reportDialog.create();
	reportDialog.open();
    }

}
