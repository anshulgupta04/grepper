package com.grepper.model;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.tooling.GlobalGraphOperations;

import java.util.HashMap;

/**
 * Created by mayank.gupta on 20/09/14.
 */
public abstract class BaseNode {

    public GraphDatabaseService graphDb = DbInitializer.getDbInstance();
    private IndexManager index = graphDb.index();
    private Index<Node> name = index.forNodes("name");

    public Label getLabel(){
        return DynamicLabel.label("BaseLabel");
    }

    public void createNode(HashMap<String,String> attributes){
        Node categoryNode;
        GraphDatabaseService graphDb = DbInitializer.getDbInstance();
        // Database operations go here
        categoryNode = graphDb.createNode(getLabel());
        for (String key : attributes.keySet()) {
            categoryNode.setProperty(key, attributes.get(key));
            name.add(categoryNode,"name",categoryNode.getProperty("name"));

        }
    }
    public void printAllLabelNodes(){
         ResourceIterator<Node> allLabelNodes =  GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(getLabel()).iterator();
         while(allLabelNodes.hasNext()){
             System.out.println(allLabelNodes.next().toString());
        }
    }

}
