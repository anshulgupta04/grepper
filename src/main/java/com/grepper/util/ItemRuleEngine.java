package com.grepper.util;

import com.grepper.model.DbInitializer;
import com.grepper.model.Relationships;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mayank.gupta on 20/09/14.
 */
public class ItemRuleEngine {


    public static Set<String> contextGenerator(String itemTitle, String itemDescription) {
        Set<String> categorySet = new HashSet<String>();
        // processing the title to find category relationships
        for (DbInitializer.CategoryNodes node : DbInitializer.CategoryNodes.values()) {
            if (itemTitle.toUpperCase().contains(node.toString())) {
                categorySet.add(node.toString());
            }
            //processing the description to find category relationships
            if (itemDescription.toUpperCase().contains(node.toString())){
                categorySet.add(node.toString());
            }
        }
        return categorySet;
    }
}
