package org.eclipse.internal.cpacep.launcher;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.cpacep.util.CPACEPConnector;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class LaunchConfigurationDelegate extends org.eclipse.debug.core.model.LaunchConfigurationDelegate {

    public void launch(final ILaunchConfiguration configuration, String mode, final ILaunch launch,
	    IProgressMonitor monitor) {

	Display.getDefault().asyncExec(new Runnable() {
	    @Override
	    public void run() {
		IWorkbenchPage page = null;
		//CpacepView view = null;
//		try {

//		    view = (CpacepView) page.findView(CpacepView.ID);
//		    if (view == null) {
//			view = (CpacepView) page.showView(CpacepView.ID, null, IWorkbenchPage.VIEW_ACTIVATE);
//		    }
//		    if(configuration instanceof ILaunchConfigurationWorkingCopy) {
//			ILaunchConfigurationWorkingCopy workConfig=(ILaunchConfigurationWorkingCopy)configuration;
			CPACEPConnector.create(configuration);
//		    }
//		    CPACEPConnector.create(configuration);
//
//		} catch (Exception e) {
//		    System.out.println("Error initializing the CPACEP");
//		}
	    }
	});
    }
}
