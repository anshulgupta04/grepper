package com.grepper.model;


import clover.it.unimi.dsi.fastutil.Hash;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mayank.gupta on 20/09/14.
 */
public class DbInitializer {


    private static GraphDatabaseService graphDb;
    private String DB_PATH = System.getProperty("user.dir") + File.separator + "graphDatabase";

    public static HashMap<CategoryNodes, List<CategoryNodes>> categoryToCategoryRelationship = new HashMap<CategoryNodes, List<CategoryNodes>>();
    public static HashMap<CategoryNodes, List<PersonRelationsNodes>> categoryToPersonRelationship = new HashMap<CategoryNodes, List<PersonRelationsNodes>>();
    public static HashMap<CategoryNodes, List<LocationNodes>> categoryToLocationRelationship = new HashMap<CategoryNodes, List<LocationNodes>>();

    public enum CategoryNodes {
        MEN,
        WOMEN,
        KID,
        JACKET,
        SAREE,
        SUIT,
        SUMMER,
        WINTER,
        TRENDING,
        TRADITIONAL,
        WESTERN,
        JEANS,
        LINGERIE,
        MOBILE,
        CAMERA,
        SHIRT,
        GIFT,
        ANNIVERSARY,
        BIRTHDAY,
        WEDDING,
        SPORT,
        SHOE,
        COMIC;
    }

    public enum PersonRelationsNodes{
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

    public enum CultureNodes{
        NORTH_INDIAN,
        SOUTH_INDIAN,
        TAMIL,
        ANDHRA,
        KERALA,
        NORTH_EAST;
    }
    public enum LocationNodes{
        CHENNAI,
        KASHMIR,
        BANGALORE;

    }
    private DbInitializer(){
        deleteFileOrDirectory( new File(DB_PATH));
        System.out.println( "Starting database ..." );
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
        //if(G) -- NEED TO HAVE CHECK TO SEE IF DATA IS ALREADY POPULATED, IF SO DO NOT CALL INITIALIZE METHODS
        initializeCategories();
        initializeRelationships();
        initializeCultures();
        initializeLocations();
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

    private void initializeLocations(){
        /**
         * Populates the db with setup data: Categories, type of social relationships, types of cultures
         */

        Location location = new Location();
        HashMap<String, String> attr = new HashMap<String, String>();
        for(LocationNodes ln : LocationNodes.values()){
            attr.put("name",ln.toString());
            location.createNode(attr);
        }
    }

    public void initializeRelaionships(){
        List<CategoryNodes> listCategory = new ArrayList<CategoryNodes>();
        listCategory.add(CategoryNodes.JACKET);
        listCategory.add(CategoryNodes.JEANS);
        listCategory.add(CategoryNodes.SHIRT);
        listCategory.add(CategoryNodes.SHOE);

        List<CategoryNodes> listCategoryWomen = new ArrayList<CategoryNodes>();
        listCategoryWomen.add(CategoryNodes.SAREE);
        listCategoryWomen.add(CategoryNodes.SUIT);
        listCategoryWomen.add(CategoryNodes.JEANS);
        listCategoryWomen.add(CategoryNodes.LINGERIE);
        listCategoryWomen.add(CategoryNodes.SHOE);

        List<CategoryNodes> listCategoryGift = new ArrayList<CategoryNodes>();
        listCategoryGift.add(CategoryNodes.SAREE);
        listCategoryGift.add(CategoryNodes.CAMERA);
        listCategoryGift.add(CategoryNodes.MOBILE);
        listCategoryGift.add(CategoryNodes.LINGERIE);
        listCategoryGift.add(CategoryNodes.SHOE);
        listCategoryGift.add(CategoryNodes.ANNIVERSARY);
        listCategoryGift.add(CategoryNodes.WEDDING);
        listCategoryGift.add(CategoryNodes.BIRTHDAY);


        categoryToCategoryRelationship.put(CategoryNodes.MEN, listCategory);
        categoryToCategoryRelationship.put(CategoryNodes.WOMEN, listCategoryWomen);
        categoryToCategoryRelationship.put(CategoryNodes.GIFT, listCategoryGift);

        List<PersonRelationsNodes> listPersonRelations = new ArrayList<PersonRelationsNodes>();
        listPersonRelations.add(PersonRelationsNodes.GIRLFRIEND);
        listPersonRelations.add(PersonRelationsNodes.WIFE);
        listPersonRelations.add(PersonRelationsNodes.FIANCE);

        categoryToPersonRelationship.put(CategoryNodes.LINGERIE, listPersonRelations);
        

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
