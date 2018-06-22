package org.eclipse.internal.cpacep.view;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class CpacepView extends ViewPart {
    public static final String ID = "org.eclipse.cpacep.ui.view.properties"; //$NON-NLS-1$

    private Text text;

    private Composite parent;
    private ViewToolBar toolbar;
    private StatusBar statusBar;

    public void createPartControl(Composite p) {
	this.parent = p;
	GridLayout gridLayout = new GridLayout();
	gridLayout.marginWidth = 4;
	gridLayout.marginHeight = 4;
	p.setLayout(gridLayout);
	statusBar = new StatusBar(p);
	statusBar.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

	toolbar = new ViewToolBar(getViewSite().getActionBars().getToolBarManager());

	text = new Text(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
	
	text.setSize(200,200);
	GridData gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
	gridData.horizontalSpan = 2;
	text.setLayoutData(gridData);
	
	OutputStream out = new OutputStream() {
	    @Override
	    public void write(int b) throws IOException {
		if (text.isDisposed())
		    return;
		text.append(String.valueOf((char) b));
	    }
	};
	final PrintStream oldOut = System.out;
	System.setOut(new PrintStream(out));
	text.addDisposeListener(new DisposeListener() {
	    public void widgetDisposed(DisposeEvent e) {
		System.setOut(oldOut);
	    }
	});
    }

    // public void createPartControl(Composite parent) {
    // text = new Text(parent, SWT.READ_ONLY | SWT.MULTI);
    // OutputStream out = new OutputStream() {
    // @Override
    // public void write(int b) throws IOException {
    // if (text.isDisposed())
    // return;
    // text.append(String.valueOf((char) b));
    // }
    // };
    // final PrintStream oldOut = System.out;
    // System.setOut(new PrintStream(out));
    // text.addDisposeListener(new DisposeListener() {
    // public void widgetDisposed(DisposeEvent e) {
    // System.setOut(oldOut);
    // }
    // });
    // }

    public void setFocus() {
	
    }

    public void reset() {
	text.setText("");
    }
}