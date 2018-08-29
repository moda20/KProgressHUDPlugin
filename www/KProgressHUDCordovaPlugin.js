var exec = require('cordova/exec');

/*
exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'KProgressHUDCordovaPlugin', 'coolMethod', arg0);
};
*/

/**
 *
 * @param argObject
 *
 *
 * arguments ={
 *  type:'' , // 'timed' | 'infinite'
 *  label:'', // a string for label
 *  details :'', // a string to show as details under the label
 *  color : '', // int : background color of the loader MUST BE AN ARGB COLOR
 *  if you don't specify color it will not take any effect. if you do, you must specify a valid ARGB color ot the plugin will crash
 *  visual:'', // only for 'timed' : 'bar' | 'annular' | 'pie'
 *  progress:{
 *   auto : '', // only for 'timed' : true | false
 *   time: '', // only for 'timed' and auto 'true' : Is a number, the more you put, the more it takes time to load : 50 => about 3 seconds
 *  },
 *  speed : '' // only for 'infinite' int 1 for normal 2 for double the speed
 *  }
 *
 *
 * @param success
 * @param error
 */

exports.showLoader = function (argObject, success, error) {
    console.log(argObject);
    switch (argObject.type) {
        case 'infinite' : {
            let arg0 = [argObject.label, argObject.speed || 1, argObject.color || 0, argObject.details];
            exec(success, error, 'KProgressHUDCordovaPlugin', 'infiniteHUD', arg0);
            break;
        }
        case 'timed': {
            let arg0 = [argObject.label, argObject.visual, argObject.color || 0, argObject.details];
            exec(() => {
                if (argObject.progress.auto == true) {
                    this.launchAutomaticProgress([argObject.progress.time || 50], success, error)
                } else {
                    success();
                }
            }, error, 'KProgressHUDCordovaPlugin', 'determinateHUD', arg0);
            break;
        }
    }


};
/**
 *
 * @param arg0 ['']  must be an empty string for it to work. Works for all types of HUD
 * @param success
 * @param error
 */
exports.dismiss = function (arg0, success, error) {
    exec(success, error, 'KProgressHUDCordovaPlugin', 'dismiss', arg0);
};
/**
 *
 * @param arg0 [ integer : progress  ] Must be between 0 and 100 or it will crash the plugin
 * Only works for timed HUD. Do not Abuse or it will crash the plugin
 * @param success
 * @param error
 */
exports.setProgress = function (arg0, success, error) {
    exec(success, error, 'KProgressHUDCordovaPlugin', 'setProgress', arg0);
};
/**
 *
 * @param arg0 [integer : time ]  Only works for timed HUD. Do not Abuse or it will crash the plugin. Will instantly reset
 * Current progress to Zero then apply its effect.
 * @param success
 * @param error
 */
exports.launchAutomaticProgress = function (arg0, success, error) {
    exec(success, error, 'KProgressHUDCordovaPlugin', 'launchAutomaticProgress', arg0);
};
/**
 *
 * @param arg0  ['String :label'] Works for all types of HUD
 * @param success
 * @param error
 */
exports.setLabel = function (arg0, success, error) {
    exec(success, error, 'KProgressHUDCordovaPlugin', 'setLabel', arg0);
};
/**
 *
 * @param arg0  ['String : detailsLabel'] Works for all types of HUD
 * @param success
 * @param error
 */
exports.setDetailsLabel = function (arg0, success, error) {
    exec(success, error, 'KProgressHUDCordovaPlugin', 'setDetailsLabel', arg0);
};
