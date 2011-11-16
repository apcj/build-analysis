package org.neo4j.build.analysis;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JenkinsBuildXmlParser
{
    public ModuleMavenStepTimings parse( File file ) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException
    {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse( file );
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node mainArtifactNode = (Node) xPath.evaluate( "/hudson.maven.MavenBuild/actions/hudson.maven.reporters.MavenArtifactRecord/mainArtifact", document, XPathConstants.NODE );

        String groupId = xPath.evaluate( "groupId", mainArtifactNode );
        String artifactId = xPath.evaluate( "artifactId", mainArtifactNode );

        ModuleMavenStepTimings timings = new ModuleMavenStepTimings( new Module( groupId, artifactId ) );

        NodeList mojoNodes = (NodeList) xPath.evaluate( "//hudson.maven.ExecutedMojo", document, XPathConstants.NODESET );

        for ( int i = 0; i < mojoNodes.getLength(); i++ )
        {
            Node mojoNode = mojoNodes.item( i );
            String pluginArtifactId = xPath.evaluate( "artifactId", mojoNode );
            String goal = xPath.evaluate( "goal", mojoNode );
            String executionId = xPath.evaluate( "executionId", mojoNode );
            int duration = Integer.parseInt( xPath.evaluate( "duration", mojoNode ) );

            timings.add( new MavenStepTiming( pluginArtifactId, goal, executionId, duration ) );
        }

        return timings;
    }

}
