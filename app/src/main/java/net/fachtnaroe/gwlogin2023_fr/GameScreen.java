package net.fachtnaroe.gwlogin2023_fr;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.WebViewer;
import com.google.appinventor.components.runtime.util.Ev3Constants;

public class GameScreen extends Form implements HandlesEventDispatching {

    private
    VerticalArrangement mainArrangement;
    HorizontalArrangement topMenu;
    Label lblTitleAtTop;
    Button btl, btr;
    WebViewer wvGame;
    StatusBarTools statusBar;
    String token;
    Notifier announce;

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
        lblTitleAtTop.WidthPercent(90);
        btr.WidthPercent(5);

        btl.Height(Component.LENGTH_FILL_PARENT);
        btl.BackgroundColor(colors.MAIN_BACKGROUND);
        btl.Text("");
        btl.Image("BabyBurger01.png");
        btr.BackgroundColor(colors.MAIN_BACKGROUND);
        btr.Text("R");
        btr.Height(Component.LENGTH_FILL_PARENT);

        announce=new Notifier(this);
        lblTitleAtTop.TextColor(colors.HEADING_TEXT);
        lblTitleAtTop.Text("grassworld.fachtnaroe.net");
        lblTitleAtTop.FontTypeface(Ev3Constants.FontType.LARGE_FONT);
        lblTitleAtTop.FontTypeface(Component.TYPEFACE_MONOSPACE);
        lblTitleAtTop.TextAlignment(Component.ALIGNMENT_CENTER);
        wvGame=new WebViewer(mainArrangement);
        wvGame.HeightPercent(90);
        wvGame.WidthPercent(100);
        wvGame.HomeUrl(ApplicationSettings.URL_MAIN);
        wvGame.GoHome();

        EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
        EventDispatcher.registerEventForDelegation(this, formName, "Click");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

        System.err.print("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed")) {
            wvGame.GoBack();
            return true;
        }
        else if (eventName.equals("Click")) {
            if (component.equals(btl)) {
                System.err.print("You pressed a button");
                switchFormWithStartValue("AccountAdminScreen",token);
                // invert te timer status
                return true;
            }
            else if (component.equals(btr)) {
                System.err.print("You pressed a button");
                // invert te timer status

                return true;
            }
        }
        else if (eventName.equals("Timer")) {
        }
        // true means event has been handled by us (ie recognised)
        return false;
    }
}