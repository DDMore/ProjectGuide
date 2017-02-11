package dnyaneshwar.fireapp;

import android.app.Application;
import com.firebase.client.Firebase;
/**
 * Created by Dnyaneshwar on 12/14/2016.
 */
public class FireApp extends Application {
    @Override
   public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this );
    }
}
