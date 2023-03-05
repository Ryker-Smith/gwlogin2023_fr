package net.fachtnaroe.gwlogin2023_fr;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.VerticalArrangement;

import com.google.appinventor.components.runtime.util.Ev3Constants;

import static net.fachtnaroe.gwlogin2023_fr.bits.dbg;

public class GameScreen extends Form implements HandlesEventDispatching {

    private
    VerticalArrangement mainArrangement;
    HorizontalArrangement topMenu, botButtons;
    Label lblTitleAtTop;
    Button btl, btr;
    Button butLeft, butRight, butUp, butDown;
    grassworldWebViewer wvGame;
    StatusBarTools statusBar;
    String token;
    Notifier announce;
//    WebViewQueue wvq;

    protected void $define() {

        this.Sizing("Responsive");
        mainArrangement=new VerticalArrangement(this);
        mainArrangement.WidthPercent(100);
        mainArrangement.Height(LENGTH_FILL_PARENT);
        mainArrangement.BackgroundColor(colors.MAIN_BACKGROUND);
        mainArrangement.AlignHorizontal(Component.ALIGNMENT_CENTER);
        statusBar=new StatusBarTools(mainArrangement);
        statusBar.BGTransparentColor("#00000000");
        statusBar.BackgroundColor("#00000000");
        token=this.startupValue;
        token=token.replace("\"","");// apparently quotes are part of what's passed!
        topMenu=new HorizontalArrangement(mainArrangement);
        btl=new Button(topMenu);
        lblTitleAtTop=new Label(topMenu);
        btr=new Button(topMenu);

        topMenu.WidthPercent(100);
        topMenu.HeightPercent(5);
        btl.WidthPercent(5);
        lblTitleAtTop.WidthPercent(85);
        btr.WidthPercent(10);

        btl.Height(Component.LENGTH_FILL_PARENT);
        btl.BackgroundColor(colors.MAIN_BACKGROUND);
        btl.Text("");
        btl.Image("BabyBurger01.png");
        btr.BackgroundColor(colors.RED);
        btr.Text("R");
        btr.Height(Component.LENGTH_FILL_PARENT);

        announce=new Notifier(this);
        lblTitleAtTop.TextColor(colors.HEADING_TEXT);
        lblTitleAtTop.Text("grassworld.fachtnaroe.net");
        lblTitleAtTop.FontTypeface(Ev3Constants.FontType.LARGE_FONT);
        lblTitleAtTop.FontTypeface(Component.TYPEFACE_MONOSPACE);
        lblTitleAtTop.TextAlignment(Component.ALIGNMENT_CENTER);
        wvGame=new grassworldWebViewer(mainArrangement);
        wvGame.HeightPercent(90);
        wvGame.WidthPercent(100);
        wvGame.HomeUrl(ApplicationSettings.URL_MAIN);
        wvGame.GoHome();

        botButtons=new HorizontalArrangement(mainArrangement);

        butLeft=new Button(botButtons);
        butLeft.WidthPercent(25);
        butLeft.Height(LENGTH_FILL_PARENT);
        butLeft.Text("\u25C4");//◄
        butDown=new Button(botButtons);
        butDown.WidthPercent(25);
        butDown.Height(LENGTH_FILL_PARENT);
        butDown.Text("\u25BC");  //▼
        butUp=new Button(botButtons);
        butUp.WidthPercent(25);
        butUp.Height(LENGTH_FILL_PARENT);
        butUp.Text("\u25B2"); //▲
        butRight=new Button(botButtons);
        butRight.WidthPercent(25);
        butRight.Height(LENGTH_FILL_PARENT);
        butRight.Text("\u25BA"); //►
        wvGame.ClearCaches();
//        wvq = new WebViewQueue(this, wvGame);

        EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "WebViewStringChange");
        EventDispatcher.registerEventForDelegation(this, formName, "thingUpdate");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

        dbg("dispatchEvent: [" + formName + "] [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed")) {
            wvGame.GoBack();
            return true;
        }
        else if (eventName.equals("thingUpdate")) {
            String s=wvGame.get_thingUpdates();
            dbg("Updates: "+s);
            return true;
        }
        else if (eventName.equals("wvq_fromGame")) {
            String s=wvGame.fromGame();
            dbg("From game: "+s);
            return true;
        }
        else if (eventName.equals("WebViewStringChange")) {
              String msg = wvGame.WebViewString();
              dbg("WVS: "+msg);
              return true;
        }
        else if (eventName.equals("Click")) {
            if (component.equals(btl)) {
//                System.err.print("You pressed a button");
                switchFormWithStartValue("AccountAdminScreen",token);
                // invert te timer status
                return true;
            }
            else if (component.equals(btr)) {
                dbg("You pressed a button");
                wvGame.GoHome();
                // invert te timer status
                return true;
            }
            else if (component.equals(butLeft)) {
                dbg("key_left");
                wvGame.toGame("__key_left");
                return true;
            }
            else if (component.equals(butDown)) {
                dbg("key_down");
                wvGame.toGame("__key_down");
                return true;
            }
            else if (component.equals(butUp)) {
                dbg("key_up");
                wvGame.toGame("__key_up");
                return true;
            }
            else if (component.equals(butRight)) {
                dbg("key_right");
                wvGame.toGame("__key_right");
                return true;
            }
        }
        else if (eventName.equals("Timer")) {
            return true;
        }
        // true means event has been handled by us (ie recognised)
        return false;
    }

}