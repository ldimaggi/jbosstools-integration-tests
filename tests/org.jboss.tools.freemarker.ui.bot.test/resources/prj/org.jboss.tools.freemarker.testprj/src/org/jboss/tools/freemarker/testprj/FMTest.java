/*******************************************************************************
 * Copyright (c) 2016 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.freemarker.testprj;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FMTest {
	public static void main(String[] args) throws IOException {
		FMTest test = new FMTest();
		test.perform();
	}

	public void perform() {
		// Add the values in the datamodel
		Map<String,Object> datamodel = new HashMap<String,Object>();
		datamodel.put("pet", "Bunny");
		datamodel.put("number", new Integer(6));
		try {
			freemarkerDo(datamodel, "pet.ftl");
		}

		catch (Exception e) {
			System.out.println("Exception");
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void freemarkerDo(Map<String, Object> datamodel, String template) throws Exception {
		Configuration cfg = new Configuration(Configuration.getVersion());
		cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir") + "/resource"));
		Template tpl = cfg.getTemplate(template);
		OutputStreamWriter output = new OutputStreamWriter(System.out);
		tpl.process(datamodel, output);
	}
}
