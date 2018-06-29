
package org.eclipse.internal.cpacep.launcher;

import org.eclipse.debug.ui.*;

public class LaunchConfigurationTab extends AbstractLaunchConfigurationTabGroup {

  @Override
  public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
    ILaunchConfigurationTab[] tabs =
        new ILaunchConfigurationTab[] {new MainLaunchingTab(), new CommonTab()};
    setTabs(tabs);
  }
}
