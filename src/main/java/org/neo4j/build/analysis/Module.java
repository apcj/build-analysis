package org.neo4j.build.analysis;

public class Module
{
    private String groupId;
    private String artifactId;

    public Module( String groupId, String artifactId )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
    }

    public String toTeamCityLogFormat()
    {
        return groupId + ":" + artifactId;
    }
}
