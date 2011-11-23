package org.jboss.tools.portlet.ui.bot.test.matcher.factory;

import java.util.Arrays;
import java.util.List;

import org.jboss.tools.portlet.ui.bot.test.entity.FacetDefinition;
import org.jboss.tools.portlet.ui.bot.test.entity.WorkspaceFile;
import org.jboss.tools.portlet.ui.bot.test.entity.XMLNode;
import org.jboss.tools.portlet.ui.bot.test.matcher.SWTMatcher;
import org.jboss.tools.portlet.ui.bot.test.matcher.workspace.ExistingProjectMatcher;
import org.jboss.tools.portlet.ui.bot.test.matcher.workspace.ProjectFacetsMatcher;
import org.jboss.tools.portlet.ui.bot.test.matcher.workspace.file.ExistingFileMatcher;
import org.jboss.tools.portlet.ui.bot.test.matcher.workspace.file.xml.XMLFileNodeContentMatcher;

/**
 * Factory for workspace specific matchers (projects, files..) 
 * 
 * @author Lucia Jelinkova
 *
 */
public class WorkspaceMatchersFactory {

	private WorkspaceMatchersFactory(){
		// not to be instantiated
	}
	
	public static SWTMatcher<String> isExistingProject(){
		return new ExistingProjectMatcher();
	}
	
	public static SWTMatcher<WorkspaceFile> exists(){
		return new ExistingFileMatcher();
	}
	
	public static SWTMatcher<String> hasFacets(FacetDefinition... facets){
		return new ProjectFacetsMatcher(facets);
	}
	
	public static SWTMatcher<String> hasFacets(List<FacetDefinition> facets){
		return new ProjectFacetsMatcher(facets.toArray(new FacetDefinition[facets.size()]));
	}
	
	public static SWTMatcher<WorkspaceFile> containsNodes(XMLNode... nodes){
		return new XMLFileNodeContentMatcher(Arrays.asList(nodes));
	}
}
