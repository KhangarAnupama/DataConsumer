package com.anupama.ks.producer.service;
import redis.clients.jedis.Jedis; 

public class RedisJava { 
   public static void main(String[] args) { 
      Jedis jedis = new Jedis("localhost"); 
      System.out.println("Connection to server sucessfully"); 
      //check whether server is running or not 
      System.out.println("Server is running: "+jedis.ping()); 
      
      jedis.set("00:0a:95:9d:68:16", "(1,1)"); 
      // Get the stored data and print it 
      System.out.println("Stored string in redis:: "+ jedis.get("00:0a:95:9d:68:16")); 
   } 
}