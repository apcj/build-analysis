package org.neo4j.build.analysis;

import java.util.ArrayList;
import java.util.List;

public class ModuleMavenStepTimings
{
    private Module module;
    private List<MavenStepTiming> timings = new ArrayList<MavenStepTiming>();

    public ModuleMavenStepTimings( Module module )
    {
        this.module = module;
    }

    public void add( MavenStepTiming mavenStepTiming )
    {
        timings.add( mavenStepTiming );
    }

    public String toTeamCityLogFormat()
    {
        StringBuilder builder = new StringBuilder();
        for ( MavenStepTiming timing : timings )
        {
            builder.append( "[" ).append( module.toTeamCityLogFormat() )
                    .append( "] [" ).append( timing.toTeamCityLogFormat() )
                    .append( "]\t" ).append( timing.getDuration() / (double) 1000 )
                    .append( "\n" );
        }
        return builder.toString();
    }
}
