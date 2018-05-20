package org.eclipse.internal.cpacep.launcher;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class MainLaunchingTab extends AbstractLaunchConfigurationTab {

    private Text executableText;
    private Button executableButton;
    private Text sourceText;
    private Button sourceFileButton;
    private Combo specificationCombo;
    private Combo configurationCombo;
    private Text commandLineText;

    ModifyListener modifyListener = new ModifyListener() {
	@Override
	public void modifyText(ModifyEvent e) {
	    scheduleUpdateJob();
	}
    };

    @Override
    public boolean canSave() {
	return true;
    }

    @Override
    public boolean isValid(ILaunchConfiguration launchConfig) {
	setMessage(null);
	setErrorMessage(null);
	return true;
    }

    @Override
    public void createControl(Composite parent) {
	// TODO Auto-generated method stub
	updateLaunchConfigurationDialog();
	Font font = parent.getFont();
	Composite comp = new Composite(parent, SWT.NONE);
	setControl(comp);
	GridLayout topLayout = new GridLayout();
	topLayout.verticalSpacing = 7;
	topLayout.horizontalSpacing = 7;
	topLayout.numColumns = 3;
	comp.setLayout(topLayout);
	comp.setFont(font);

	createVerticalSpacer(comp, 3);

	new Label(comp, SWT.NONE).setText(Messages.MainLaunchingTab_labelCPAChecker);
	executableText = new Text(comp, SWT.SINGLE | SWT.BORDER);
	GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
	gridData.horizontalSpan = 2;
	executableText.setLayoutData(gridData);
	executableText.addModifyListener(modifyListener);

	Composite tempBrowseLayout = new Composite(comp, SWT.NONE);
	gridData = new GridData(GridData.FILL_HORIZONTAL);
	gridData.horizontalSpan = 3;
	tempBrowseLayout.setLayoutData(gridData);

	GridLayout browseButtonLayout = new GridLayout(4, false);
	browseButtonLayout.marginHeight = browseButtonLayout.marginWidth = 0;
	tempBrowseLayout.setLayout(browseButtonLayout);

	Label tempLbl = new Label(tempBrowseLayout, SWT.NONE);
	tempLbl.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, false));

	executableButton = createPushButton(tempBrowseLayout, Messages.MainLaunchingTab_labelBrowse, null);
	executableButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		CPACExecutableButtonSelected();
	    }
	});

	new Label(comp, SWT.NONE).setText(Messages.MainLaunchingTab_labelFile);
	sourceText = new Text(comp, SWT.SINGLE | SWT.BORDER);
	gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
	gridData.horizontalSpan = 2;
	sourceText.setLayoutData(gridData);
	sourceText.setFont(font);
	sourceText.addModifyListener(modifyListener);

	Composite tempButtonLayout = new Composite(comp, SWT.NONE);
	gridData = new GridData(GridData.FILL_HORIZONTAL);
	gridData.horizontalSpan = 3;
	tempButtonLayout.setLayoutData(gridData);

	GridLayout buttonLayout = new GridLayout(4, false);
	buttonLayout.marginHeight = buttonLayout.marginWidth = 0;
	tempButtonLayout.setLayout(buttonLayout);

	Label tempLbl2 = new Label(tempButtonLayout, SWT.NONE);
	tempLbl2.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, false));

	sourceFileButton = createPushButton(tempButtonLayout, Messages.MainLaunchingTab_sourcesBrowse, null);
	sourceFileButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		sourcesButtonSelected();
	    }
	});
	new Label(comp, SWT.NONE).setText(Messages.MainLaunchingTab_labelProgramArgs);
	String dummySpecification[] = { "ErrorLabel", "JavaAssertion", "TerminatingFunctions" };
	String dummyConfiguration[] = { "defuse", "policy-intervals", "invariantGeneration" };
	specificationCombo = SWTFactory.createCombo(comp, SWT.DROP_DOWN, 1, dummySpecification);
	configurationCombo = SWTFactory.createCombo(comp, SWT.DROP_DOWN, 1, dummyConfiguration);
	specificationCombo.setText(Messages.MainLaunchingTab_specificationCombo);
	configurationCombo.setText(Messages.MainLaunchingTab_configurationCombo);
	addAutoCompleteFeature(specificationCombo);
	addAutoCompleteFeature(configurationCombo);

	new Label(comp, SWT.NONE).setText(Messages.MainLaunchingTab_labelCommandLineArgs);
	commandLineText = new Text(comp, SWT.SINGLE | SWT.BORDER);
	gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
	gridData.horizontalSpan = 2;
	commandLineText.setLayoutData(gridData);
	commandLineText.addModifyListener(modifyListener);

    }

    @Override
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
	// TODO Auto-generated method stub
	System.out.println("Inside set defaults");

    }

    @Override
    public void initializeFrom(ILaunchConfiguration configuration) {
	// TODO Auto-generated method stub
	System.out.println("Inside initializing form");

    }

    @Override
    public void performApply(ILaunchConfigurationWorkingCopy configuration) {
	// TODO Auto-generated method stub
	System.out.println("Inside perform apply");

    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return Messages.MainLaunchingTab_name;
    }

    private void CPACExecutableButtonSelected() {
	FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
	dialog.setText(Messages.MainLaunchingTab_dialogCPA);
	dialog.setFilterNames(new String[] { "cpa*" }); //$NON-NLS-1$
	String file = dialog.open();
	if (file != null) {
	    executableText.setText(file);
	}
    }

    void sourcesButtonSelected() {
	FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
	dialog.setText(Messages.MainLaunchingTab_dialogSourcesMessage);
	dialog.setFilterNames(new String[] { "*.c" }); //$NON-NLS-1$
	String file = dialog.open();
	if (file != null) {
	    sourceText.setText(file);
	}

    }

    /**
     * 
     * @param combo
     *            Function to auto complete the combo box
     */
    public static void addAutoCompleteFeature(Combo combo) {
	// Add a key listener
	combo.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent keyEvent) {
		Combo cmb = ((Combo) keyEvent.getSource());
		setClosestMatch(cmb);
	    }

	    // Move the highlight back by one character for backspace
	    public void keyPressed(KeyEvent keyEvent) {
		if (keyEvent.keyCode == SWT.BS) {
		    Combo cmb = ((Combo) keyEvent.getSource());
		    Point pt = cmb.getSelection();
		    cmb.setSelection(new Point(Math.max(0, pt.x - 1), pt.y));
		}
	    }

	    private void setClosestMatch(Combo combo) {
		String str = combo.getText();
		String[] cItems = combo.getItems();
		// Find Item in Combo Items. If full match returns index
		int index = -1;
		for (int i = 0; i < cItems.length; i++) {
		    if (cItems[i].toLowerCase().startsWith(str.toLowerCase())) {
			index = i;
			break;
		    }
		}

		if (index != -1) {
		    Point pt = combo.getSelection();
		    combo.select(index);
		    combo.setText(cItems[index]);
		    combo.setSelection(new Point(pt.x, cItems[index].length()));
		} else {
		    combo.setText("");
		}
	    }
	});
    }

}
