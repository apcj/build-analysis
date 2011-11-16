package org.neo4j.build.analysis;

import java.io.File;

public class AnalyseFiles
{
    public static void main( String[] args ) throws Exception
    {
        File[] files = new File( "data" ).listFiles();
        JenkinsBuildXmlParser parser = new JenkinsBuildXmlParser();
        for ( File file : files )
        {
            ModuleMavenStepTimings timings = parser.parse( file );
            System.out.print( timings.toTeamCityLogFormat() );
        }
    }

}
