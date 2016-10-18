package org.jboss.ide.eclipse.as.ui.bot.test.eap5;

import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerReqType;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement.JBossServer;
import org.jboss.ide.eclipse.as.ui.bot.test.template.HotDeployJSPFileTemplate;
import org.jboss.reddeer.requirements.server.ServerReqState;

@JBossServer(state=ServerReqState.RUNNING, type=ServerReqType.EAP5x,cleanup=false)
public class HotDeployJSPFileEAP5Server extends HotDeployJSPFileTemplate {

}
