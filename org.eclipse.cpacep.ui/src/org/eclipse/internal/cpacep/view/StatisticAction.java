package org.eclipse.internal.cpacep.view;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.internal.cpacep.dialog.Statistics;
import org.eclipse.jface.window.Window;

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
    CpacepView cpacepView = CpacepView.getViewInstance();
    Statistics dialog = new Statistics(cpacepView.getComposite().getShell());
    dialog.create();
    dialog.open();
    cpacepView.getCPACEPConncetor().getStatistics();
    // if (dialog.open() == Window.OK) {
    //
    // }

  }
}
