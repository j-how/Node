package com.greendao.gen;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/**
 * Created by django on 2016/9/14.
 */
public class MyGenerator {
    public static void main(String[] args){
        int version = 1;
        String defaultPackage = "com.udacity.bean";
        String defaultPackageDao = "com.udacity.dao";
        Schema schema = new Schema(version,defaultPackage);
        schema.setDefaultJavaPackageDao(defaultPackageDao);
        addMovie(schema,"BeanMovie");
        String outDir = "../Udacity/app/src/main/java-gen";
        try {
            new DaoGenerator().generateAll(schema,outDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addMovie(Schema schema,String tableName) {
        Entity entity = schema.addEntity(tableName);
        entity.setTableName(tableName);
        entity.addIdProperty().autoincrement();
        entity.addStringProperty("poster_path");
        entity.addBooleanProperty("adult");
        entity.addStringProperty("overview");
        entity.addStringProperty("release_date");
        entity.addStringProperty("genre_ids");
        entity.addIntProperty("movie_id");
        entity.addStringProperty("original_title");
        entity.addStringProperty("original_language");
        entity.addStringProperty("title");
        entity.addStringProperty("backdrop_path");
        entity.addFloatProperty("popularity");
        entity.addIntProperty("vote_count");
        entity.addBooleanProperty("video");
        entity.addFloatProperty("vote_average");
        entity.addIntProperty("movie_type");
        entity.addBooleanProperty("favorite");
    }
}
