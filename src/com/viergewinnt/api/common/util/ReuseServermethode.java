package com.viergewinnt.api.common.util;

/**
 * Die Klasse ReuseServermethode speichert die Attribute, welche Serverkommunikation relevant sind 
 * und ermoeglich einen Zugriff auf diese Attribute waehrend der Programmlaufzeit
 * @author Alexander Kern
 *
 */

public class ReuseServermethode {

private static String methode;

private static String team;

private static String pfad;

private static String gegner;

private static String key;

private static String secret;

private static boolean teamfarbe;

private static boolean gegnerfarbe;

public static void setMethode(String methode) {

ReuseServermethode.methode = methode;

}

/**
 * 
 * @return
 */
public static String getMethode() {

return methode;

}

/**
 * Setzt den Namen des Teams
 * @param team Name des Teams
 */
public static void setTeam(String team) {

ReuseServermethode.team = team;

}


/**
 * Gibt den Name des Teams zurueck
 * @return Name des Teams
 */
public static String getTeam() {

return team;

}

/**
 * 
 * @param pfad
 */
public static void setPfad(String pfad) {

ReuseServermethode.pfad = pfad;

}

/**
 * 
 * @return
 */
public static String getPfad() {

return pfad;

}

/**
 * Setzt den Namen des Gegner
 * @param gegner Name des Gegners
 */
public static void setGegner(String gegner){

ReuseServermethode.gegner = gegner;

}

/**
 * Gibt den Name des Gegners  zurueck
 * @param Name des Gegners
 */
public static String getGegner(){

return gegner;

}

/**
 * 
 * @param key
 */
public static void setKey(String key) {

ReuseServermethode.key = key;

}


/**
 * 
 * @return
 */
public static String getKey() {

return key;

}

/**
 * 
 * @param secret
 */
public static void setSecret(String secret) {

ReuseServermethode.secret = secret;

}


/**
 * 
 * @return
 */
public static String getSecret() {

return secret;

}

/**
 * Setzt die Steinfarbe der kuenstlichen Intelligenz Claire
 * @param teamfarbe Steinfarbe 
 */
public static void setTeamfarbe(boolean teamfarbe) {

ReuseServermethode.teamfarbe = teamfarbe;

}

/**
 * Gibt die Steinfarbe der kuenstlichen Intelligenz Claire zurueck
 * @return Steinfarbe
 */
public static boolean getTeamfarbe() {

return teamfarbe;

}

/**
 * Setzt die gegnerische Steinfarbe
 * @param gegnerfarbe Steinfarbe des Gegners
 */
public static void setGegnerfarbe(boolean gegnerfarbe) {

ReuseServermethode.gegnerfarbe = gegnerfarbe;

}


/**
 * Gibt die Steinfrabe des Gegners zurueck
 * @return  Steinfrabe des Gegners
 */
public static boolean getGegnerfarbe() {

return gegnerfarbe;

}

}