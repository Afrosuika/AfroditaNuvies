package net.infobosccoma.projecte.afroditanuvies.model;

import android.content.Context;
import android.content.SharedPreferences;

public class ConjuntMidesPreferences {

	/** Nom de l'arxiu de preferencies */
    private static final String PREFS_NAME = "net.infobosccoma.projecte.afroditanuvies.model.ConjuntMidesPreferences";
 
    /** Preferencies compartides */
    private static SharedPreferences settings;
    
   /** Editor de les preferencies compartides */
   private static SharedPreferences.Editor editor;
 
   /** Constructor, obté el context */
   public ConjuntMidesPreferences(Context ctx){
        if(settings == null){
           settings = ctx.getSharedPreferences(PREFS_NAME,
                                               Context.MODE_PRIVATE );
        }
       /*
        * Get a SharedPreferences editor instance.
        * SharedPreferences ensures that updates are atomic
        * and non-concurrent
        */
        editor = settings.edit();    
   }
   
   /** The prefix for flattened user keys */
   public static final String KEY_PREFIX =
               "net.infobosccoma.projecte.afroditanuvies.model.KEY";
    
   /** Method to return a unique key for any field belonging to a given object
   * @param id of the object
   * @param fieldKey of a particular field belonging to that object
   * @return key String uniquely identifying the object's field
   */
   private String getFieldKey(String fieldKey) {
          return  KEY_PREFIX + "_" + fieldKey;
   }
   
   /** Camps genèrics */
   private static final String KEY_PIT = "net.infobosccoma.projecte.afroditanuvies.model.KEY_PIT";
   private static final String KEY_CINTURA = "net.infobosccoma.projecte.afroditanuvies.model.KEY_CINTURA";
   private static final String KEY_MALUC = "net.infobosccoma.projecte.afroditanuvies.model.KEY_MALUC";
   private static final String KEY_ALTURA = "net.infobosccoma.projecte.afroditanuvies.model.KEY_ALTURA";
   
   /** Store or Update */
   public void setUser(ConjuntMides conjuntMides){
       if(conjuntMides == null)
         return; // don't bother
        
       editor.putFloat(
                  getFieldKey(KEY_PIT),
                  conjuntMides.getMidaPit() );
       editor.putFloat(
                  getFieldKey(KEY_CINTURA),
                  conjuntMides.getMidaCintura() );
       editor.putFloat(
                  getFieldKey(KEY_MALUC),
                  conjuntMides.getMidaMaluc() );
       editor.putFloat(
    		   getFieldKey(KEY_ALTURA),
               conjuntMides.getMidaAltura() );
    
       editor.commit();
   }
   
   /** Retrieve */
   public ConjuntMides getConjuntMides(){
       float altura = settings.getFloat(
                     getFieldKey(KEY_ALTURA),
                     0); // default value
       float pit =  settings.getFloat(
                    getFieldKey(KEY_PIT),
                    0); // default value
       float cintura =  settings.getFloat(
                    getFieldKey(KEY_CINTURA),
                    0); // default value
       float maluc =  settings.getFloat(
               getFieldKey(KEY_MALUC),
               0); // default value
    
       return new ConjuntMides(pit, maluc, cintura, altura);
   }
    
   /** Delete */
   public void deleteUser(ConjuntMides conjuntMides){
      if(conjuntMides == null)
         return; // don't bother
    
      editor.remove( getFieldKey(KEY_PIT) );
      editor.remove( getFieldKey(KEY_CINTURA) );
      editor.remove( getFieldKey(KEY_MALUC) );
      editor.remove( getFieldKey(KEY_ALTURA) );
       
      editor.commit();
   }
   
}
