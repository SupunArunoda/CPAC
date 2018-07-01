package org.eclipse.internal.cpacep.dialog;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.FrameworkUtil;

public class Report extends TitleAreaDialog {
    
    private Browser browser;
    private String report;
    
    public Report(Shell parentShell) {
	super(parentShell);

    }

    @Override
    public void create() {
	super.create();
	//setTitle(Messages.DialogViewForm_Report);
	//setMessage(Messages.DialogViewForm_ReportMessage, IMessageProvider.INFORMATION);
	setTitleImage(getImage(new Path("icons/cpac_logo.png")));

    }

    public Image getImage(Path path) {
	ImageDescriptor imageDescriptor = ImageDescriptor
		.createFromURL(FileLocator.find(FrameworkUtil.getBundle(this.getClass()), path, null));
	return imageDescriptor.createImage();
    }
    
    @Override
    protected Control createDialogArea(Composite parent) {
	Composite area = (Composite) super.createDialogArea(parent);
	Composite container = new Composite(area, SWT.NONE);
	container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	GridLayout layout = new GridLayout(1, false);
	container.setLayout(layout);

	browser= new Browser(container, SWT.NONE);
	browser.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
	loadReport();
	return area;
    }
    
    public void loadReport() {
	browser.setUrl(report);
    }
    
    public String getReport() {
	return report;
    }
    
    public void setReport(String report) {
	this.report=report;
    }
    @Override
    protected boolean isResizable() {
	return true;
    }

    @Override
    protected void okPressed() {
	super.okPressed();
    }
    
}
