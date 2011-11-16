package org.neo4j.build.analysis;

public class MavenStepTiming
{
    private String pluginArtifactId;
    private String goal;
    private String executionId;
    private int duration;

    public MavenStepTiming( String pluginArtifactId, String goal, String executionId, int duration )
    {
        this.pluginArtifactId = pluginArtifactId;
        this.goal = goal;
        this.executionId = executionId;
        this.duration = duration;
    }

    public String toTeamCityLogFormat()
    {
        return shorten(pluginArtifactId) + ":" + goal + " {execution: " + executionId + "}";
    }

    private String shorten( String name )
    {
        return name.replaceAll( "maven-", "" ).replaceAll( "-plugin", "" );
    }

    public int getDuration()
    {
        return duration;
    }
}
