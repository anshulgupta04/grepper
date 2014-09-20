package com.grepper.model;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

/**
 * Created by mayank.gupta on 20/09/14.
 */
public class DbInitializer {

    private static GraphDatabaseService graphDb;
    private String DB_PATH = System.getProperty("user.dir") + File.separator + "graphDatabase";




    private DbInitializer(){
        deleteFileOrDirectory( new File(DB_PATH));
        System.out.println( "Starting database ..." );
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
    }

    public static GraphDatabaseService getDbInstance(){
        if(graphDb == null){
           new DbInitializer();
        }
        return graphDb;
    }

    public void graphDbShutDown(final GraphDatabaseService graphDb){
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    }

    private void initializeSetupNodes(){

    }
    private static void deleteFileOrDirectory( File file )
    {
        if ( file.exists() )
        {
            if ( file.isDirectory() )
            {
                for ( File child : file.listFiles() )
                {
                    deleteFileOrDirectory( child );
                }
            }
            file.delete();
        }
    }

}
