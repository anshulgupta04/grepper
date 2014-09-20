package com.grepper.util;

import com.grepper.model.DbInitializer;
import com.grepper.model.Relationships;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;



import java.awt.*;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by mayank.gupta on 20/09/14.
 */
public class ItemPopulator {
    private Label label = DynamicLabel.label("ITEM");
    Index<Node> name;
    private final String TITLE = "title";
    private final String DESCRIPTION = "description";

    public void createNode(HashMap<String,String> attributes){
        Node itemNode;
        GraphDatabaseService graphDb = DbInitializer.getDbInstance();
        Set<String> categorySet = ItemRuleEngine.contextGenerator(attributes.get(TITLE), attributes.get(DESCRIPTION));
        // Database operations go here
        itemNode = graphDb.createNode(label);
        for (String key : attributes.keySet()) {
            itemNode.setProperty(key, attributes.get(key));
        }
        for(String s : categorySet){
            IndexHits<Node> hit = name.get("name",s);
            Node category = hit.getSingle();
            itemNode.createRelationshipTo(category, Relationships.BELONGS_TO);

            if(s.equals(DbInitializer.CategoryNodes.LINGERIE.toString())){
                for(DbInitializer.PersonRelationsNodes node : DbInitializer.categoryToPersonRelationship.get(DbInitializer.CategoryNodes.LINGERIE))
                hit = name.get("name",node.toString());
                category = hit.getSingle();
                itemNode.createRelationshipTo(category, Relationships.BELONGS_TO);
            }
            if(s.equals(DbInitializer.CategoryNodes.SUMMER.toString())){
                for(DbInitializer.LocationNodes node : DbInitializer.categoryToLocationRelationship.get(DbInitializer.CategoryNodes.SUMMER))
                    hit = name.get("name",node.toString());
                category = hit.getSingle();
                itemNode.createRelationshipTo(category, Relationships.BELONGS_TO);
            }

            if(s.equals(DbInitializer.CategoryNodes.WINTER.toString())){
                for(DbInitializer.LocationNodes node : DbInitializer.categoryToLocationRelationship.get(DbInitializer.CategoryNodes.WINTER))
                    hit = name.get("name",node.toString());
                category = hit.getSingle();
                itemNode.createRelationshipTo(category, Relationships.BELONGS_TO);
            }
        }
    }

}
