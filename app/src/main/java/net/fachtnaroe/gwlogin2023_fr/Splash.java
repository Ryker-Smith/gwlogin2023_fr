package net.fachtnaroe.gwlogin2023_fr;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.TableArrangement;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.WebViewer;
import com.google.appinventor.components.runtime.util.TimerInternal;

import java.io.Console;

import static net.fachtnaroe.gwlogin2023_fr.colors.MAIN_BACKGROUND;

public class Splash extends Form implements HandlesEventDispatching {

    private
    VerticalArrangement VerticalArrangement1;
    Clock clkScreenChangeTimer;

    protected void $define() {

        this.Sizing("Responsive");

        VerticalArrangement1 = new VerticalArrangement(this);
        VerticalArrangement1.WidthPercent(100);
        VerticalArrangement1.HeightPercent(100);
        VerticalArrangement1.BackgroundColor(MAIN_BACKGROUND);
        clkScreenChangeTimer=new Clock(this);
        clkScreenChangeTimer.TimerInterval(100);
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
            clkScreenChangeTimer.TimerEnabled(false);
            switchForm("LoginScreen");
        }
        return false;
    }
}