package org.eclipse.internal.cpacep.view;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class RunAction extends ActionEvent {

	public RunAction(ViewToolBar toolbar) {
		super(toolbar);
	}

	@Override
	public IPath getImagePath() {
		return new Path("icons/run_enabled.gif"); //$NON-NLS-1$
	}

	@Override
	public IPath getDisabledImagePath() {
		return new Path("icons/run_disabled.gif"); //$NON-NLS-1$
	}

	@Override
	public String getText() {
		return Messages.PropertiesView_actionRun;
	}

	@Override
	public void updateEnablementState() {
		
	}

	@Override
	public void run() {
		
	}
}
