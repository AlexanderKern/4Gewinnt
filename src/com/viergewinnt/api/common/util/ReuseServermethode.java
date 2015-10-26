package com.viergewinnt.api.common.util;



public class ReuseServermethode {

private static String methode;

private static String team;

private static String pfad;

private static String gegner;

private static String key;

private static String secret;



public static void setMethode(String methode) {

ReuseServermethode.methode = methode;

}



public static String getMethode() {

return methode;

}


public static void setTeam(String team) {

ReuseServermethode.team = team;

}



public static String getTeam() {

return team;

}


public static void setPfad(String pfad) {

ReuseServermethode.pfad = pfad;

}



public static String getPfad() {

return pfad;

}


public static void setGegner(String gegner){

ReuseServermethode.gegner = gegner;

}

public static String getGegner(){

return gegner;

}


public static void setKey(String key) {

ReuseServermethode.key = key;

}



public static String getKey() {

return key;

}


public static void setSecret(String secret) {

ReuseServermethode.secret = secret;

}



public static String getSecret() {

return secret;

}

}