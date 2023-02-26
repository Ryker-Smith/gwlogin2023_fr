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

public class RegistrationScreen extends Form implements HandlesEventDispatching {
        private
        HorizontalArrangement  grid;
        VerticalArrangement gridCenter,  mainArrangement;;
        TextBox usernameBox, rnBox, bornBox;
        PasswordTextBox passwordBox;
        Button btnLogin, btnRegister;
        Label padTop, padBottom, padBetweenLoginAndRegister, lblU, padBetween, lblP, padAboveLogin;
        Label lblTitleAtTop, gridPadLeft, getGridPadRight, lblRN, lblBorn;
        Web webValidate;
        JSONObject jsonCredentials = new JSONObject();
        StatusBarTools statusBar;

        protected void $define() {

            this.Sizing("Responsive");
            mainArrangement = new VerticalArrangement(this);
            mainArrangement.WidthPercent(100);
            mainArrangement.Height(LENGTH_FILL_PARENT);
            mainArrangement.BackgroundColor(colors.MAIN_BACKGROUND);
            mainArrangement.AlignHorizontal(Component.ALIGNMENT_CENTER);
            statusBar=new StatusBarTools(mainArrangement);
            statusBar.BGTransparentColor("#00000000");
            statusBar.BackgroundColor("#00000000");
            lblTitleAtTop = new Label(mainArrangement);
            lblTitleAtTop.WidthPercent(100);
            lblTitleAtTop.TextColor(colors.HEADING_TEXT);
            lblTitleAtTop.Text("\n\ngrassworld.fachtnaroe.net");
            lblTitleAtTop.FontTypeface(Ev3Constants.FontType.LARGE_FONT);
            lblTitleAtTop.FontTypeface(Component.TYPEFACE_MONOSPACE);
            lblTitleAtTop.TextAlignment(Component.ALIGNMENT_CENTER);
            padTop = new Label(mainArrangement);
            padTop.WidthPercent(100);
            padTop.HeightPercent(5);
            grid=new HorizontalArrangement(mainArrangement);
            gridPadLeft=new Label(grid);
            gridCenter = new VerticalArrangement(grid);
            getGridPadRight=new Label(grid);

            gridPadLeft.WidthPercent(5);
            int gridCenterWidth=90;
            gridCenter.WidthPercent(gridCenterWidth);
            getGridPadRight.WidthPercent(5);

            lblU = new Label(gridCenter);
            lblU.TextColor(colors.TEXTBOX_TEXT);
            lblU.BackgroundColor(colors.MAIN_BACKGROUND);
            lblU.Text("Please provide an email address which will be your username:");
            lblU.WidthPercent(gridCenterWidth);
            lblU.FontSize(14);
            lblU.TextColor(colors.MAIN_TEXT_MUCHO);
            usernameBox = new TextBox(gridCenter);
            usernameBox.FontSize(14);
            usernameBox.FontTypeface(Component.TYPEFACE_MONOSPACE);
            usernameBox.WidthPercent(gridCenterWidth);
            usernameBox.BackgroundColor(colors.TEXTBOX_BACKGROUND);
            usernameBox.TextColor(colors.TEXTBOX_TEXT);
            padBetween = new Label(gridCenter);
            padBetween.WidthPercent(gridCenterWidth);
            padBetween.HeightPercent(1);
            lblP = new Label(gridCenter);
            lblP.TextColor(colors.MAIN_TEXT_MUCHO);
            lblP.BackgroundColor(colors.MAIN_BACKGROUND);
            lblP.Text("Create a password, minimum length 8 characters, upper and lowercase, with one or more digits:");
            lblP.FontBold(false);
            lblP.WidthPercent(gridCenterWidth);
            lblP.FontSize(14);
            passwordBox = new PasswordTextBox(gridCenter);
            passwordBox.WidthPercent(gridCenterWidth);
            passwordBox.FontSize(14);
            passwordBox.FontTypeface(Component.TYPEFACE_MONOSPACE);
            passwordBox.BackgroundColor(colors.TEXTBOX_BACKGROUND);
            passwordBox.TextColor(colors.TEXTBOX_TEXT);
            padAboveLogin = new Label(gridCenter);
            padAboveLogin.WidthPercent(100);
            padAboveLogin.HeightPercent(1);

            lblRN = new Label(gridCenter);
            lblRN.TextColor(colors.MAIN_TEXT_MUCHO);
            lblRN.BackgroundColor(colors.MAIN_BACKGROUND);
            lblRN.Text("Type your name:");
            lblRN.WidthPercent(gridCenterWidth);
            lblRN.FontSize(14);
            rnBox = new TextBox(gridCenter);
            rnBox.FontSize(14);
            rnBox.FontTypeface(Component.TYPEFACE_MONOSPACE);
            rnBox.WidthPercent(gridCenterWidth);
            rnBox.BackgroundColor(colors.TEXTBOX_BACKGROUND);
            rnBox.TextColor(colors.TEXTBOX_TEXT);

            padAboveLogin = new Label(gridCenter);
            padAboveLogin.WidthPercent(100);
            padAboveLogin.HeightPercent(1);
            lblBorn = new Label(gridCenter);
            lblBorn.TextColor(colors.MAIN_TEXT_MUCHO);
            lblBorn.BackgroundColor(colors.MAIN_BACKGROUND);
            lblBorn.Text("You need to be age 18 or older to use this game. Enter the year you were born:");
            lblBorn.WidthPercent(gridCenterWidth);
            lblBorn.FontSize(14);
            bornBox = new TextBox(gridCenter);
            bornBox.FontSize(14);
            bornBox.FontTypeface(Component.TYPEFACE_MONOSPACE);
            bornBox.WidthPercent(gridCenterWidth);
            bornBox.BackgroundColor(colors.TEXTBOX_BACKGROUND);
            bornBox.TextColor(colors.TEXTBOX_TEXT);

            padBetweenLoginAndRegister = new Label(gridCenter);
            padBetweenLoginAndRegister.WidthPercent(100);
            padBetweenLoginAndRegister.HeightPercent(1);
            btnRegister = new Button(gridCenter);
            btnRegister.WidthPercent(gridCenterWidth);
            btnRegister.Text("register");
            btnRegister.TextColor(colors.BUTTON_TEXT);
            btnRegister.BackgroundColor(colors.BUTTON_BACKGROUND);

            padBottom = new Label(mainArrangement);
            padBottom.WidthPercent(100);
            padBottom.Height(Component.LENGTH_FILL_PARENT);
            webValidate = new Web(this);
            webValidate.Url(ApplicationSettings.URL_LOGIN);

            EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
            EventDispatcher.registerEventForDelegation(this, formName, "Click");
            EventDispatcher.registerEventForDelegation(this, formName, "GotText");
        }

        public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

            dbg("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
            if (eventName.equals("BackPressed")) {
                return true;
            }
            else if (eventName.equals("Click")) {
                if (component.equals(btnRegister)) {
                    btnRegister.Text(ui_txt.CONNECTION_SENDING);
                    btnRegister.Enabled(false);
                    if (testEmail()) {
                        if(testPassword() && testName() && testAge()) {
                            try {
                                jsonCredentials.put("action", "exists");
                                jsonCredentials.put("user", usernameBox.Text());

                                dbg("Sending: " + jsonCredentials.toString());
                                String msg = jsonCredentials.toString();
                                webValidate.PostText(msg);
                            } catch (Exception e) {
                                return false;
                            }
                        }
                        else {
                            btnRegister.Enabled(true);
                        }
                    }

                    return true;
                }
            } else if (eventName.equals("GotText")) {
                if (component.equals(webValidate)) {
                    String status = params[1].toString();
                    String textOfResponse = (String) params[3];
                    if (textOfResponse.equals("")) {
                        textOfResponse = status;
                    }
                    if (status.equals("200")) {
                        try {
                            JSONObject parser = new JSONObject(textOfResponse);
                            if (parser.getString("status").equals("OK")) {
                                String token = parser.getString("token");
                                switchFormWithStartValue("GameScreen", token);
                            } else {
                                btnLogin.Text(parser.getString("status"));
                                btnLogin.Enabled(true);
                            }
                        } catch (JSONException e) {
                            btnLogin.Text("error connecting " + status);
                            btnLogin.Enabled(true);
                        }
                    } else {
                        btnLogin.Text("error connecting " + status);
                        btnLogin.Enabled(true);
                    }
                }
            }
            // true means event has been handled by us (ie recognised)
            return false;
        }
        boolean testEmail(){
            return true;
        }
        boolean testPassword(){
            return true;
        }
        boolean testName(){
            return true;
        }
        boolean testAge(){
            return true;
        }
        public static void dbg(String debugMsg) {
            System.err.print("~~~> " + debugMsg + " <~~~\n");
        }
}
