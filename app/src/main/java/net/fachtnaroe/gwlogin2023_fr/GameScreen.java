package net.fachtnaroe.gwlogin2023_fr;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.WebViewer;
import com.google.appinventor.components.runtime.util.Ev3Constants;

public class GameScreen extends Form implements HandlesEventDispatching {

    private
    VerticalArrangement mainArrangement;
    Label lblTitleAtTop;
    Button goButton;
    WebViewer wvGame;

    protected void $define() {

        this.Sizing("Responsive");
        mainArrangement=new VerticalArrangement(this);
        mainArrangement.WidthPercent(100);
        mainArrangement.HeightPercent(100);
        mainArrangement.BackgroundColor(colors.MAIN_BACKGROUND);
        mainArrangement.AlignHorizontal(Component.ALIGNMENT_CENTER);
        lblTitleAtTop=new Label(mainArrangement);
        lblTitleAtTop.WidthPercent(100);
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
            if (component.equals(goButton)) {
                goButton.Text("Goodbye");
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