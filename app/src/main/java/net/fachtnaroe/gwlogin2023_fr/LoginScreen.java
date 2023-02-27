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
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.util.Ev3Constants;

import org.json.JSONException;
import org.json.JSONObject;

import static net.fachtnaroe.gwlogin2023_fr.bits.dbg;


public class LoginScreen extends Form implements HandlesEventDispatching {

    private
    HorizontalArrangement hzUser,hzPass, grid;
    VerticalArrangement mainArrangement, gridCenter;
    TextBox usernameBox;
    PasswordTextBox passwordBox;
    Button btnLogin, btnRegister;
    Label padTop, padBottom, padBetweenLoginAndRegister, lblU, padBetween, lblP, padAboveLogin;
    Label lblTitleAtTop, gridPadLeft, getGridPadRight;
    Web webAuthenticate;
    JSONObject jsonCredentials=new JSONObject();
    StatusBarTools statusBar;

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

        lblTitleAtTop=new Label(mainArrangement);
        lblTitleAtTop.WidthPercent(100);
        lblTitleAtTop.TextColor(colors.HEADING_TEXT);
        lblTitleAtTop.Text("\n\ngrassworld.fachtnaroe.net");
        lblTitleAtTop.FontTypeface(Ev3Constants.FontType.LARGE_FONT);
        lblTitleAtTop.FontTypeface(Component.TYPEFACE_MONOSPACE);
        lblTitleAtTop.TextAlignment(Component.ALIGNMENT_CENTER);
        padTop=new Label(mainArrangement);
        padTop.WidthPercent(100);
        padTop.HeightPercent(10);

        grid=new HorizontalArrangement(mainArrangement);
        gridPadLeft=new Label(grid);
        gridCenter = new VerticalArrangement(grid);
        getGridPadRight=new Label(grid);
        gridPadLeft.WidthPercent(5);
        int gridCenterWidth=90;
        gridCenter.WidthPercent(gridCenterWidth);
        getGridPadRight.WidthPercent(5);

        lblU=new Label(gridCenter);
        lblU.TextColor(colors.MAIN_TEXT_MUCHO);
        lblU.BackgroundColor(colors.MAIN_BACKGROUND);
        lblU.Text("User email:");
        lblU.WidthPercent(gridCenterWidth);
        lblU.FontBold(false);
        usernameBox=new TextBox(gridCenter);
        usernameBox.FontSize(14);
        usernameBox.FontTypeface(Component.TYPEFACE_MONOSPACE);
        usernameBox.WidthPercent(gridCenterWidth);
        usernameBox.BackgroundColor(colors.TEXTBOX_BACKGROUND);
        usernameBox.TextColor(colors.TEXTBOX_TEXT);
        padBetween=new Label(gridCenter);
        padBetween.WidthPercent(gridCenterWidth);
        padBetween.HeightPercent(1);
        lblP = new Label(gridCenter);
        lblP.TextColor(colors.MAIN_TEXT_MUCHO);
        lblP.BackgroundColor(colors.MAIN_BACKGROUND);
        lblP.Text("Password:");
        lblP.FontBold(false);
        lblP.WidthPercent(gridCenterWidth);
        passwordBox=new PasswordTextBox(gridCenter);
        passwordBox.WidthPercent(gridCenterWidth);
        passwordBox.FontSize(14);
        passwordBox.FontTypeface(Component.TYPEFACE_MONOSPACE);
        passwordBox.BackgroundColor(colors.TEXTBOX_BACKGROUND);
        passwordBox.TextColor(colors.TEXTBOX_TEXT);
        padAboveLogin = new Label(gridCenter);
        padAboveLogin.WidthPercent(gridCenterWidth);
        padAboveLogin.HeightPercent(1);
        btnLogin = new Button(gridCenter);
        btnLogin.WidthPercent(gridCenterWidth);
        btnLogin.Text("login");
        btnLogin.BackgroundColor(colors.BUTTON_BACKGROUND);
        btnLogin.TextColor(colors.BUTTON_TEXT);
        padBetweenLoginAndRegister=new Label(gridCenter);
        padBetweenLoginAndRegister.WidthPercent(gridCenterWidth);
        padBetweenLoginAndRegister.HeightPercent(1);
        btnRegister = new Button(gridCenter);
        btnRegister.WidthPercent(gridCenterWidth);
        btnRegister.Text("register");
        btnRegister.TextColor(colors.BUTTON_TEXT);
        btnRegister.BackgroundColor(colors.BUTTON_BACKGROUND);
        padBottom=new Label(gridCenter);
        padBottom.WidthPercent(100);
        padBottom.Height(Component.LENGTH_FILL_PARENT);
        webAuthenticate=new Web(this);
        webAuthenticate.Url(ApplicationSettings.URL_LOGIN);
        if(ApplicationSettings.TESTING) {
            usernameBox.Text(ApplicationSettings.forTesting_User);
            passwordBox.Text(ApplicationSettings.forTesting_Pass);
        }

        EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "GotText");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

        dbg("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
        if (eventName.equals("BackPressed") || eventName.equals("OtherScreenClosed")) {
            System.exit(0);
            return true;
        }
        else if (eventName.equals("Click")) {
            if (component.equals(btnLogin)) {
//                switchFormWithStartValue("GameScreen","KHKLKJHKJH");
//                return true;
                btnLogin.Text(ui_txt.CONNECTION_SENDING);
                btnLogin.Enabled(false);
                System.err.print("You pressed a button");
                try {
                    jsonCredentials.put("action", "login");
                    jsonCredentials.put("user", usernameBox.Text());
                    jsonCredentials.put("password",passwordBox.Text());
                    dbg("Sending: "+jsonCredentials.toString());
                    String msg=jsonCredentials.toString() ;
                    webAuthenticate.PostText(msg);
                }
                catch (Exception e) {
                    return false;
                }
                return true;
            }
            else if (component.equals(btnRegister)) {
                switchForm("RegistrationScreen");
            }
        }
        else if (eventName.equals("GotText")) {
            if (component.equals(webAuthenticate)) {
                String status = params[1].toString();
                String textOfResponse = (String) params[3];
                if (textOfResponse.equals("")) {
                    textOfResponse=status;
                }
                if (status.equals("200")) {
                    try {
                        JSONObject parser = new JSONObject(textOfResponse);
                        if (parser.getString("status").equals("OK")) {
                            String token=parser.getString("token");
                            switchFormWithStartValue("GameScreen",token);
                        }
                        else {
                            btnLogin.Text(parser.getString("status"));
                            btnLogin.Enabled(true);
                        }
                    }
                    catch (JSONException e){
                        btnLogin.Text("error connecting "+status);
                        btnLogin.Enabled(true);
                    }
                }
                else{
                    btnLogin.Text("error connecting "+status);
                    btnLogin.Enabled(true);
                }
            }
        }
        // true means event has been handled by us (ie recognised)
        return false;
    }

}