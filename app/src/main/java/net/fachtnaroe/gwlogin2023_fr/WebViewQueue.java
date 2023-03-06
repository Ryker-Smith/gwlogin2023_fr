package net.fachtnaroe.gwlogin2023_fr;

import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;

import static net.fachtnaroe.gwlogin2023_fr.bits.dbg;

// Version/Date 2023-03-04-2055

public class WebViewQueue extends Object {
    /* suggestion: name your variable of this type as 'wvq' so
    that your code would read as, eq:
        wvq.toGame(cmd)
        wvq.fromGame()
     */
    private byte sequence_top=57, sequence_bot=48;
    public final static Integer queue_max=0;
    public static Integer default_timer=1000;
    public static boolean busy;
    private static String[] queue_out =new String[queue_max + 1];
    private static Integer head;
    private byte bop=sequence_bot;
    private static grassworldWebViewer theWebView;
    private HandlesEventDispatching containingForm;
    public static Clock ticker;

    public WebViewQueue(HandlesEventDispatching form, grassworldWebViewer wvcomponent){
        super();
        // constructor
        head=0;
        containingForm=form;
        dbg("DD "+form.toString());
        for (int i=0; i<queue_max;i++){
            queue_out[i]="";
        }
        theWebView=wvcomponent;
    }

    public boolean toGame(String cmd) {
        head++;
        dbg("   toGame      ["+containingForm.toString()+"]");
//        EventDispatcher.unregisterEventForDelegation( this, containingForm.toString(), "modifiedWebViewStringChange");
        String tmp=(char)bop+cmd;
        theWebView.WebViewString(tmp);
        if(bop>sequence_top){
            bop=sequence_bot;
        }
//        EventDispatcher.registerEventForDelegation( this, containingForm.toString(), "modifiedWebViewStringChange");
        return true;
    }

    public String fromGame(){
        dbg("   fromGame    ["+containingForm.toString()+"]");
        //EventDispatcher.unregisterEventForDelegation( this, containingForm.toString(), "fachtnaWebViewStringChange");
        String recvd=theWebView.WebViewString();
        //EventDispatcher.registerEventForDelegation( this, containingForm.toString(), "fachtnaWebViewStringChange");
        return recvd;
    }

    public Integer qSize(){

        return head;
    }

    public boolean qFull(){

        return false;
    }
}
