package com.service.cars;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.io.InputStream;

import java.util.Properties;

 

public class PropReader {

	private Properties prop = null;

    public PropReader(){
    	//Default
    }

    public PropReader(String name){

         

        InputStream is = null;

        try {

            this.prop = new Properties();

            is = this.getClass().getResourceAsStream(name+".properties");

            prop.load(is);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

     

    public String getPropertyValue(String key){
    	
        return this.prop.getProperty(key);

    }

    public String getPropertyValue(String name, String key){
    	PropReader mProp = new PropReader(name);
        return mProp.prop.getProperty(key);

    }


    public static void main(String a[]){

         

    	PropReader mpc = new PropReader("sample2");

        System.out.println("db.host: "+mpc.getPropertyValue("db.host"));

        System.out.println("db.user: "+mpc.getPropertyValue("db.user"));

        System.out.println("db.password: "+mpc.getPropertyValue("db.password"));

    }

}

