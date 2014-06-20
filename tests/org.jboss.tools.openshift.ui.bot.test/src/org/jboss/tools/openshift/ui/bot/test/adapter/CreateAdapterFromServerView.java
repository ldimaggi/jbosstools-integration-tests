package org.jboss.tools.openshift.ui.bot.test.adapter;

import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import org.jboss.reddeer.eclipse.wst.server.ui.view.ServersView;
import org.jboss.reddeer.swt.api.TreeItem;
import org.jboss.reddeer.swt.condition.ButtonWithTextIsActive;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.menu.ContextMenu;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.impl.tree.DefaultTree;
import org.jboss.reddeer.swt.wait.TimePeriod;
import org.jboss.reddeer.swt.wait.WaitUntil;
import org.jboss.tools.openshift.ui.bot.test.application.wizard.DeleteApplication;
import org.jboss.tools.openshift.ui.bot.test.application.wizard.NewApplicationTemplates;
import org.jboss.tools.openshift.ui.bot.util.OpenShiftLabel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Create adapter from Servers view. At first application is created 
 * without server adapter. After that OpenShift server adapter is created from
 * Servers view.
 * 
 * @author mlabuda@redhat.com
 *
 */
public class CreateAdapterFromServerView {

	private final String DIY_APP = "diyapp" + new Date().getTime();
	
	@Before
	public void createApplication() {
		new NewApplicationTemplates(false).createSimpleApplicationWithoutCartridges(
				OpenShiftLabel.AppType.DIY, DIY_APP, false, true, false);
	}
	
	@Test
	public void createAdapterFromServerView() {
		ServersView serverView = new ServersView();
		serverView.open();
		
		new ContextMenu("New", "Server").select();
		
		new DefaultShell("New Server").setFocus();
		List<TreeItem> items = new DefaultTree().getAllItems();
		TreeItem openShiftItem = null;
		for (TreeItem item: items) {
			String label = item.getText();
			if (label.equals("OpenShift Server")) {
				openShiftItem = item;
				break;
			}
		}
		openShiftItem.select();
		
		new WaitUntil(new ButtonWithTextIsActive(new PushButton(OpenShiftLabel.Button.NEXT)), TimePeriod.LONG);
		new PushButton(OpenShiftLabel.Button.NEXT).click();
		new WaitUntil(new ButtonWithTextIsActive(new PushButton(OpenShiftLabel.Button.FINISH)), TimePeriod.LONG);
		new PushButton(OpenShiftLabel.Button.FINISH).click();
		
		serverView.open();
		serverView.activate();
		
		List<TreeItem> servers = new DefaultTree().getItems();
		TreeItem server = null;
		
		for (TreeItem item: servers) {
			String serverName = item.getText().split(" ")[0];
			if (serverName.equals(DIY_APP)) {
				server = item;
				break;
			}
		}	
				
		assertFalse("Adapter was not created", server == null);
	}
	
	@After
	public void deleteApp() {
		new DeleteApplication(DIY_APP).perform();
	}
}