package net.fachtnaroe.gwlogin2023_fr;

import com.google.android.material.math.MathUtils;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.util.Ev3Constants;

import static net.fachtnaroe.gwlogin2023_fr.colors.MAIN_BACKGROUND;

public class Splash extends Form implements HandlesEventDispatching {

    private
    VerticalArrangement mainArrangement;
    Clock clkScreenChangeTimer;
    StatusBarTools statusBar;
    Label lblTitleAtTop;
    Clock clkColorChange;

    protected void $define() {

        this.Sizing("Responsive");

        mainArrangement = new VerticalArrangement(this);
        mainArrangement.WidthPercent(100);
        mainArrangement.Height(LENGTH_FILL_PARENT);
        mainArrangement.BackgroundColor(MAIN_BACKGROUND);
        mainArrangement.Image("SplashAttempt01.png");
        statusBar=new StatusBarTools(mainArrangement);
        statusBar.BGTransparentColor("#00000000");
        statusBar.BackgroundColor("#00000000");
        Label lblPad=new Label(mainArrangement);
        lblPad.HeightPercent(80);
        lblTitleAtTop=new Label(mainArrangement);
        lblTitleAtTop.WidthPercent(100);
        lblTitleAtTop.TextColor(colors.WHITE);
        lblTitleAtTop.Height(LENGTH_FILL_PARENT);
        lblTitleAtTop.Text("grassworld");
        lblTitleAtTop.FontSize(50);
        lblTitleAtTop.FontTypeface(Ev3Constants.FontType.LARGE_FONT);
        lblTitleAtTop.FontTypeface(Component.TYPEFACE_MONOSPACE);
        lblTitleAtTop.TextAlignment(Component.ALIGNMENT_CENTER);
        clkColorChange =new Clock(this);
        clkColorChange.TimerInterval(50);
        clkColorChange.TimerEnabled(true);
        clkScreenChangeTimer=new Clock(this);
        clkScreenChangeTimer.TimerInterval(2000);
        clkScreenChangeTimer.TimerEnabled(true);
        EventDispatcher.registerEventForDelegation(this, formName, "Timer");
        EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
        EventDispatcher.registerEventForDelegation(this, formName, "OtherScreenClosed");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

        System.err.print("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed")) {
            // this would be a great place to do something useful
            return true;
        }
        else if (eventName.equals("OtherScreenClosed")) {
            System.exit(0);
            return true;
        }
        else if (eventName.equals("Timer")) {
            if (component.equals(clkScreenChangeTimer)) {
                clkScreenChangeTimer.TimerEnabled(false);
                clkColorChange.TimerEnabled(false);
                switchForm("LoginScreen");
                return true;
            }
            else if (component.equals(clkColorChange)) {
                clkColorChange.TimerEnabled(false);
                int newcolor=00;
                //Math.floor(Math.random());
                lblTitleAtTop.TextColor(newcolor);
                clkColorChange.TimerEnabled(true);
                return true;
            }
        }
        return false;
    }
}