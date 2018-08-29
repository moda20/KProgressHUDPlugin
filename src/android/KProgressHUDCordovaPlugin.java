package cordova.plugin.kprogresshud;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Handler;
import com.kaopiz.kprogresshud.KProgressHUD;
/**
 * This class echoes a string called from JavaScript.
 */
public class KProgressHUDCordovaPlugin extends CordovaPlugin {

    private KProgressHUD hud;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("determinateHUD")) {
            String message = args.getString(0);
            String type = args.getString(1);
            int color = args.getInt(2);
            String details = args.getString(3);
            this.determinateHUD(message, type, color, details, callbackContext);
            return true;
        }else{
        if (action.equals("dismiss")) {
                this.dismiss(callbackContext);
                return true;
            }else{
                if (action.equals("setProgress")) {
                    int progress = args.getInt(0);
                    this.setProgress(progress,callbackContext);
                    return true;
                }else{
                   if (action.equals("launchAutomaticProgress")) {
                        int time = args.getInt(0);
                       this.launchAutomaticProgress(time,callbackContext);
                       return true;
                   }else{
                        if (action.equals("setLabel")) {
                            String label = args.getString(0);
                            this.setLabel(label,callbackContext);
                            return true;
                        }else{
                            if (action.equals("infiniteHUD")) {
                                String message = args.getString(0);
                                int speed = args.getInt(1);
                                int color = args.getInt(2);
                                String details = args.getString(3);
                                this.infiniteHUD(message, speed, color, details, callbackContext);
                                return true;
                            }else{
                                if (action.equals("setDetailsLabel")) {
                                    String label = args.getString(0);
                                    this.setDetailsLabel(label,callbackContext);
                                    return true;
                                }
                            }
                        }
                   }
                }
            }
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void infiniteHUD(String message, int speed, int color, String Details, CallbackContext callbackContext) {
        final CallbackContext CB = callbackContext;
        if (message != null && message.length() > 0  ) {
             hud = KProgressHUD.create(cordova.getActivity())

                .setLabel(message)
                .setCancellable(false)
                .setMaxProgress(100);
                if(color > 0){
                    hud.setBackgroundColor(color);
                }
                if(speed > 1){
                    hud.setAnimationSpeed(speed);
                }
                if(!Details.equals("")){
                                        hud.setDetailsLabel(Details);
                                    }
                hud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
                hud.show();
                 CB.success("shown");

        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void determinateHUD(String message, String type, int color, String Details, CallbackContext callbackContext) {
            final CallbackContext CB = callbackContext;
            if (message != null && message.length() > 0 && type != null && type.length() > 0  && Details != null) {
                 hud = KProgressHUD.create(cordova.getActivity())

                    .setLabel(message)
                    .setCancellable(false)
                    .setMaxProgress(100);
                    if(color > 0){
                        hud.setBackgroundColor(color);
                    }
                    if(!Details.equals("")){
                        hud.setDetailsLabel(Details);
                    }
                   switch(type){
                        case "annular":
                            hud.setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE);
                            hud.show();
                            CB.success("shown");
                            break;
                        case "bar":
                            hud.setStyle(KProgressHUD.Style.BAR_DETERMINATE);
                            hud.show();
                            CB.success("shown");
                            break;
                        case "pie":
                            hud.setStyle(KProgressHUD.Style.PIE_DETERMINATE);
                            hud.show();
                            CB.success("shown");
                            break;

                   }
            } else {
                callbackContext.error("Expected one non-empty string argument.");
            }
        }

    private void dismiss(CallbackContext callbackContext){
         hud.dismiss();
         callbackContext.success("dismissed");
    }

     private void setProgress(int progress ,CallbackContext callbackContext){
         if (progress > 0 ) {
                   hud.setProgress(progress);
                   callbackContext.success("true");
             } else {
                 callbackContext.error("Expected one non-empty string argument.");
             }

     }

     private void setLabel(String label ,CallbackContext callbackContext){
         if(label != null && label.length() > 0 ){
              hud.setLabel(label);
              callbackContext.success("true");
         }else{
                callbackContext.error("Expected one non-empty string argument.");
         }

      }

      private void setDetailsLabel(String label ,CallbackContext callbackContext){
               if(label != null && label.length() > 0 ){
                    hud.setDetailsLabel(label);
                    callbackContext.success("true");
               }else{
                      callbackContext.error("Expected one non-empty string argument.");
               }

            }

        private void launchAutomaticProgress(int time,CallbackContext callbackContext) {
         if ( time > 0 ) {
           final CallbackContext CB = callbackContext;
           hud.setProgress(0);
           hud.setMaxProgress(100);
           final Handler handler = new Handler();
           handler.postDelayed(new Runnable() {
               int currentProgress;
               @Override
               public void run() {
                   currentProgress += 1;
                   hud.setProgress(currentProgress);

                   if (currentProgress < 100) {
                       handler.postDelayed(this, time);
                   }else{
                       CB.success("Progress Over");
                   }
               }
           }, 100);
         } else {
             callbackContext.error("Expected one non-empty string argument.");
         }

        }
}
