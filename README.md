# Cordova KProgressHUDPlugin

**Based On :** 
KProgressHUD : https://github.com/Kaopiz/KProgressHUD

![](https://raw.githubusercontent.com/Kaopiz/KProgressHUD/master/demo/screenshots/screencast.gif)

**Cordova plugin : KProgressHUD** 


Platforms available : **android**


***install :***

1 ) run `cordova plugin add https://github.com/moda20/KProgressHUDPlugin.git`

2 ) use directly from the `window` available `cordova` object.

# Usage

***Example :***

```
 let args ={
            type:'infinite',
            label:'Loading',
            details:'Please Wait ...',
            /*color:0,*/
            visual:'pie',
            progress:{
                auto:true,
                time:250,
            },
        }

        window.cordova.plugins.KProgressHUDCordovaPlugin.showLoader(args,
            function (res) {
                console.log(res);
            },
            function (e) {
                console.log(e);
                alert("Errored")
            });
    }
    
    
```
***Docs :***
```

Note : The plugin only takes consideration of one Loader at a time. Creating and showing 
an other Loader while the first one is still visible may crash the plugin and/or the 
app.

- KProgressHUDCordovaPlugin.showLoader(Args,SuccessCallback,ErrorCallback)
    Creates and show the loader automatically.
    Args : {
       type:'' ,    // 'timed' | 'infinite'
       label:'',    // a string for label
       details :'', // a string to show as details under the label
       color : '',  // int : background color of the loader MUST BE AN ARGB COLOR
                    // if you don't specify color it will not have any effect. if you do, you must specify a valid ARGB color ot the plugin will crash
       visual:'',   // only for 'timed' : 'bar' | 'annular' | 'pie'
       progress:{
        auto : '',  // only for 'timed' : true | false
        time: '',   // only for 'timed' and auto 'true' : Is a number, the more you put, the more it takes time to load : 50 => about 3 seconds
                    // defautls to 50.
       },
       speed : ''   // only for 'infinite', int 1 for normal 2 for double the speed. defaults to 1.
    }
    
    SuccessCallback and ErrorCallback are regular callbacks, see the example above.
    

- KProgressHUDCordovaPlugin.dismiss(Args,SuccessCallback,ErrorCallback)
    dismisses the visible loader.
    Args : ['']  must be an empty string for it to work. Works for all types of HUD
    
    SuccessCallback and ErrorCallback are regular callbacks, see the example above.
    
    
- KProgressHUDCordovaPlugin.setProgress(Args,SuccessCallback,ErrorCallback)
    Sets progress manually for 'timed' loader type.

    Args : [ integer : progress  ] Must be between 0 and 100 or it will crash the plugin
            Only works for timed HUD. Do not Abuse or it will crash the plugin
    
    SuccessCallback and ErrorCallback are regular callbacks, see the example above.    

- KProgressHUDCordovaPlugin.launchAutomaticProgress(Args,SuccessCallback,ErrorCallback)
    starts the automatic Progress visualization on the 'timed' type of loaders.
    It will be called internally when specifying 'progress.auto' to true when creating the loader.
    
    Args : [Time integer]  Only works for timed HUD. Do not Abuse or it will crash the plugin. Will instantly reset 
            Current progress to Zero then apply its effect.
            
    SuccessCallback and ErrorCallback are regular callbacks, see the example above.
    
- KProgressHUDCordovaPlugin.setLabel(Args,SuccessCallback,ErrorCallback)
    dynamically sets the label for the visible loader.
    
    Args : ['String : Label'] Works for all types of HUD
    
    SuccessCallback and ErrorCallback are regular callbacks, see the example above.
    
     
- KProgressHUDCordovaPlugin.setDetailsLabel(Args,SuccessCallback,ErrorCallback)
    dynamically sets the details label for the visible loader.
    
    Args : ['String : detailsLabel'] Works for all types of HUD
    
    SuccessCallback and ErrorCallback are regular callbacks, see the example above.
            
```
```
Plugin.xml :

You may have to change the com.android.support:appcompat-v7:27.1.1 to a certain version.
Note that this version needs to be unique with all components in your app, so make sure you have it fixed.

```


# ChangeLog

- V 0.1.0 : 
    - Initial Commit and publish.
    
    
# Licence 

MIT
