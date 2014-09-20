package com.grepper.model;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.util.HashMap;

/**
 * Created by mayank.gupta on 20/09/14.
 */
public class DbInitializer {

    private static GraphDatabaseService graphDb;
    private String DB_PATH = System.getProperty("user.dir") + File.separator + "graphDatabase";
    private enum CategoryNodes {
        MEN,
        WOMEN,
        KIDS,
        JACKETS,
        SAREES,
        SUITS,
        SUMMER_WEAR,
        WINTER_WEAR,
        TRENDING,
        TRADITIONAL,
        WESTERN,
        JEANS,
        SINGLE_PIECE,
        LINGERIE,
        MOBILES,
        CAMERAS,
        SHIRTS,
        GIFTS,
        ANNIVERSARY,
        BIRTHDAY,
        WEDDING,
        SPORTS,
        SHOES;
    }

    private enum PersonRelationsNodes{
        FRIEND,
        BROTHER,
        MOTHER,
        FATHER,
        WIFE,
        HUSBAND,
        SON,
        DAUGHTER,
        SISTER,
        GIRLFRIEND,
        BOYFRIEND,
        FIANCE;
    }

    private enum CultureNodes{
        NORTH_INDIAN,
        SOUTH_INDIAN,
        TAMIL,
        ANDHRA,
        KERALA,
        NORTH_EAST;
    }

    private DbInitializer(){
        deleteFileOrDirectory( new File(DB_PATH));
        System.out.println( "Starting database ..." );
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
        initializeCategories();
        initializeRelationships();
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

    private void initializeCategories(){
        /**
         * Populates the db with setup data: Categories, type of social relationships, types of cultures
         */

        Category category = new Category();
        HashMap<String, String> attr = new HashMap<String, String>();
        for(CategoryNodes cn : CategoryNodes.values()){
            attr.put("name",cn.toString());
            category.createNode(attr);
        }
    }

    private void initializeRelationships(){
        /**
         * Populates the db with setup data: Categories, type of social relationships, types of cultures
         */

        PersonRelations personRelations = new PersonRelations();
        HashMap<String, String> attr = new HashMap<String, String>();
        for(PersonRelationsNodes pr : PersonRelationsNodes.values()){
            attr.put("name", pr.toString());
            personRelations.createNode(attr);
        }
    }

    private void initializeCultures(){
        /**
         * Populates the db with setup data: Categories, type of social relationships, types of cultures
         */

        Culture culture = new Culture();
        HashMap<String, String> attr = new HashMap<String, String>();
        for(CultureNodes cn : CultureNodes.values()){
            attr.put("name",cn.toString());
            culture.createNode(attr);
        }
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
