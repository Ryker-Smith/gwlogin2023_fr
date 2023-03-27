package net.fachtnaroe.gwlogin2023_fr;

import android.app.Application;

import static net.fachtnaroe.gwlogin2023_fr.bits.dbg;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.CheckBox;
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
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.util.Ev3Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountAdminScreen extends Form implements HandlesEventDispatching {
        private
        HorizontalArrangement grid, buttonsLayout;
        VerticalArrangement gridCenter,  mainArrangement;;
        TextBox usernameBox;
        PasswordTextBox passwordBox, newPassBox1, newPassBox2;
        Button btnAction, btnCloseAll;
        Label padTop, padBottom, padBetweenPasswordsAndButton, lblU, padBetween, lblP, padAboveLogin;
        Label lblTitleAtTop, gridPadLeft, getGridPadRight, lblNewPass1, lblNewPass2;
        Web webDeleteAC, webChangePW;
        JSONObject jsonCredentials = new JSONObject();
        CheckBox optionDeleteAC, optionChangePW;
        CheckBox[] optionsList=new CheckBox[2];
        StatusBarTools statusBar;
        Notifier announce;

        protected void $define() {

            this.Sizing("Responsive");
            mainArrangement = new VerticalArrangement(this);
            mainArrangement.WidthPercent(100);
            mainArrangement.Height(LENGTH_FILL_PARENT);
            mainArrangement.BackgroundColor(colors.MAIN_BACKGROUND);
            mainArrangement.AlignHorizontal(Component.ALIGNMENT_CENTER);
            statusBar=new StatusBarTools(mainArrangement);
            statusBar.BGTransparentColor("#000000");
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
            lblU.Text(ui_txt.USERNAME);
            lblU.WidthPercent(gridCenterWidth);
            lblU.FontSize(14);
            lblU.TextColor(colors.MAIN_TEXT_MUCHO);
            usernameBox = new TextBox(gridCenter);
            usernameBox.FontSize(14);
            usernameBox.FontTypeface(Component.TYPEFACE_MONOSPACE);
            usernameBox.WidthPercent(gridCenterWidth);
            usernameBox.BackgroundColor(colors.TEXTBOX_BACKGROUND);
            usernameBox.TextColor(colors.TEXTBOX_TEXT);
            usernameBox.Text("fachtna.roe@tcfe.ie");
            usernameBox.Enabled(false);

            padBetween = new Label(gridCenter);
            padBetween.WidthPercent(gridCenterWidth);
            padBetween.HeightPercent(1);
            lblP = new Label(gridCenter);
            lblP.TextColor(colors.MAIN_TEXT_MUCHO);
            lblP.BackgroundColor(colors.MAIN_BACKGROUND);
            lblP.Text(ui_txt.OLDPASSWORD);
            lblP.FontBold(false);
            lblP.WidthPercent(gridCenterWidth);
            lblP.FontSize(14);
            passwordBox = new PasswordTextBox(gridCenter);
            passwordBox.WidthPercent(gridCenterWidth);
            passwordBox.FontSize(14);
            passwordBox.FontTypeface(Component.TYPEFACE_MONOSPACE);
            passwordBox.BackgroundColor(colors.TEXTBOX_BACKGROUND);
            passwordBox.TextColor(colors.TEXTBOX_TEXT);
            // put radio buttons here for delete/change password/do nothing?

            buttonsLayout=new HorizontalArrangement(gridCenter);
            optionChangePW = new CheckBox(buttonsLayout);
            optionChangePW.Text(ui_txt.CHANGE_PASSWORD);
            optionDeleteAC = new CheckBox(buttonsLayout);
            optionDeleteAC.Text(ui_txt.CHANGE_DELETE);
            optionsList[0]=optionChangePW;
            optionsList[1]=optionDeleteAC;

            padAboveLogin = new Label(gridCenter);
            padAboveLogin.WidthPercent(100);
            padAboveLogin.HeightPercent(1);

            lblNewPass1 = new Label(gridCenter);
            lblNewPass1.TextColor(colors.MAIN_TEXT_MUCHO);
            lblNewPass1.BackgroundColor(colors.MAIN_BACKGROUND);
            lblNewPass1.Text(ui_txt.NEW_PASSWORD);
            lblNewPass1.WidthPercent(gridCenterWidth);
            lblNewPass1.FontSize(14);
            newPassBox1 = new PasswordTextBox(gridCenter);
            newPassBox1.FontSize(14);
            newPassBox1.FontTypeface(Component.TYPEFACE_MONOSPACE);
            newPassBox1.WidthPercent(gridCenterWidth);
            newPassBox1.BackgroundColor(colors.TEXTBOX_BACKGROUND);
            newPassBox1.TextColor(colors.TEXTBOX_TEXT);

            padAboveLogin = new Label(gridCenter);
            padAboveLogin.WidthPercent(100);
            padAboveLogin.HeightPercent(1);
            lblNewPass2 = new Label(gridCenter);
            lblNewPass2.TextColor(colors.MAIN_TEXT_MUCHO);
            lblNewPass2.BackgroundColor(colors.MAIN_BACKGROUND);
            lblNewPass2.Text(ui_txt.CONFIRM_NEW_PASSWORD);
            lblNewPass2.WidthPercent(gridCenterWidth);
            lblNewPass2.FontSize(14);
            newPassBox2 = new PasswordTextBox(gridCenter);
            newPassBox2.FontSize(14);
            newPassBox2.FontTypeface(Component.TYPEFACE_MONOSPACE);
            newPassBox2.WidthPercent(gridCenterWidth);
            newPassBox2.BackgroundColor(colors.TEXTBOX_BACKGROUND);
            newPassBox2.TextColor(colors.TEXTBOX_TEXT);

            padBetweenPasswordsAndButton = new Label(gridCenter);
            padBetweenPasswordsAndButton.WidthPercent(100);
            padBetweenPasswordsAndButton.HeightPercent(1);
            btnAction = new Button(gridCenter);
            btnAction.WidthPercent(gridCenterWidth);
            btnAction.Text();
            btnAction.TextColor(colors.BUTTON_TEXT);
            btnAction.BackgroundColor(colors.BUTTON_BACKGROUND);
            btnAction.Enabled(false);

            btnCloseAll = new Button(gridCenter);
            btnCloseAll.WidthPercent(gridCenterWidth);
            btnCloseAll.Text("Close Program");
            btnCloseAll.TextColor(colors.BUTTON_TEXT);
            btnCloseAll.BackgroundColor(colors.BUTTON_BACKGROUND);
            btnCloseAll.Visible(false);

            padBottom = new Label(mainArrangement);
            padBottom.WidthPercent(100);
            padBottom.Height(Component.LENGTH_FILL_PARENT);
            padBottom.TextAlignment(Component.ALIGNMENT_CENTER);
            padBottom.TextAlignment(AlignVertical());
            padBottom.TextColor(colors.MAIN_TEXT_MUCHO);
            padBottom.FontSize(20);

            announce=new Notifier(this);
            webDeleteAC = new Web(this);
            webDeleteAC.Url(ApplicationSettings.URL_LOGIN);
            webDeleteAC = new Web(this);
            webDeleteAC.Url(ApplicationSettings.URL_LOGIN);

            EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
            EventDispatcher.registerEventForDelegation(this, formName, "Click");
            EventDispatcher.registerEventForDelegation(this, formName, "Changed");
            EventDispatcher.registerEventForDelegation(this, formName, "GotText");

        }

        private void allOptionsOff(){
            // this method of avoiding self-raised events can probably be improved upon
            EventDispatcher.unregisterEventForDelegation(this, formName,"Changed");
            for (CheckBox thing : optionsList) {
                thing.Checked(false);
            }
            EventDispatcher.registerEventForDelegation(this, formName, "Changed");
        }

        @Override
        public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

            dbg("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
            if (eventName.equals("BackPressed")) {
                Form.finishActivity();
                return true;
            }
            else if(eventName.equals("Changed")){
                if (component.equals(optionChangePW)) {
                    if (optionChangePW.Checked()) {
                        lblNewPass1.Visible(optionChangePW.Checked());
                        newPassBox1.Visible(optionChangePW.Checked());
                        lblNewPass2.Visible(optionChangePW.Checked());
                        newPassBox2.Visible(optionChangePW.Checked());
                    }
                }
                allOptionsOff();
                CheckBox tempComp;
                tempComp=(CheckBox) component;
                tempComp.Checked(true);
                lblNewPass1.Visible(optionChangePW.Checked());
                newPassBox1.Visible(optionChangePW.Checked());
                lblNewPass2.Visible(optionChangePW.Checked());
                newPassBox2.Visible(optionChangePW.Checked());
                if (component.equals(optionChangePW)) {
                    btnAction.Text(ui_txt.CHANGE_PASSWORD);
                }
                else {
                    btnAction.Text(ui_txt.CHANGE_DELETE);
                }
                btnAction.Enabled(true);
                return true;
            }
            else if (eventName.equals("Click")) {
                if ((component.equals(btnAction)) && (optionDeleteAC.Checked())) {
                    btnAction.Enabled(false);
                    btnAction.Text(ui_txt.PROCESSING);
                    if (bits.isValidEmailAddress(usernameBox.Text())) {
                        try{
                            jsonCredentials.put("action", "delete");
                            jsonCredentials.put("user", usernameBox.Text());
                            jsonCredentials.put("password", passwordBox.Text());
                            dbg("Sending: " + jsonCredentials.toString());
                            String msg = jsonCredentials.toString();
                            webDeleteAC.PostText(msg);
                            btnAction.Text(ui_txt.CONNECTION_SENDING);
                        }
                        catch (Exception e) {
                            return false;
                        }
                    }
                }
                else if ((component.equals(btnAction)) && (optionChangePW.Checked())) {
                    if (!testPassword()) {
                        announce.ShowAlert(ui_txt.PASSWORD_CONTENT);
                        return true;
                    }
                    btnAction.Enabled(false);
                    btnAction.Text(ui_txt.PROCESSING);
                    if (bits.isValidEmailAddress(usernameBox.Text())) {
                        try{
                            jsonCredentials.put("action", "password");
                            jsonCredentials.put("user", usernameBox.Text());
                            jsonCredentials.put("oldpassword", passwordBox.Text());
                            jsonCredentials.put("newpassword", lblNewPass1.Text());
                            dbg("Sending: " + jsonCredentials.toString());
                            String msg = jsonCredentials.toString();
                            webDeleteAC.PostText(msg);
                            btnAction.Text(ui_txt.CONNECTION_SENDING);
                        }
                        catch (Exception e) {
                            return false;
                        }
                    }
                }
                else if (component.equals(btnCloseAll)) {
                    this.finishApplication();
                    System.exit(0);
                }
                else {
                    announce.ShowAlert(ui_txt.REGISTER_INVALID_EMAIL);
                    btnAction.Enabled(true);
                }
                    return true;
            }
            else if (eventName.equals("GotText")) {
                if (component.equals(webChangePW)) {
                    String status = params[1].toString();
                    btnAction.Text(ui_txt.PROCESSING);
                    String textOfResponse = (String) params[3];
                    if (status.equals("200")) {
                        try {
                            JSONObject parser = new JSONObject(textOfResponse);
                            if (parser.getString("status").equals("OK")) {
                                String result = parser.getString("user");
                                if(result.contentEquals("exists")){
                                    announce.ShowAlert(ui_txt.REGISTER_USER_EXISTS);
                                    btnAction.Enabled(true);
                                    btnAction.Text(ui_txt.REGISTER);
                                }
                                else {

                                }
                            }
                            else {
                                btnAction.Text(parser.getString("status"));
                                btnAction.Enabled(true);
                            }
                        }
                        catch (JSONException e) {
                            btnAction.Text("error connecting " + status);
                            btnAction.Enabled(true);
                        }
                    }
                    else {
                        btnAction.Text("error connecting " + status);
                        btnAction.Enabled(true);
                    }
                    return true;
                }
                else if(component.equals(webDeleteAC)) {
                    String status = params[1].toString();
                    btnAction.Text(ui_txt.PROCESSING);
                    String textOfResponse = (String) params[3];
                    if (status.equals("200")) {
                        try {
                            JSONObject parser = new JSONObject(textOfResponse);
                            if (parser.getString("status").equals("OK")) {
                                EventDispatcher.unregisterEventForDelegation(this, formName, "BackPressed");
                                announce.ShowAlert(ui_txt.CHANGE_DELETE_DONE);
                                btnAction.Visible(false);
                                btnCloseAll.Visible(true);
                            }
                            else {
                                btnAction.Text(parser.getString("detail"));
                                btnAction.Enabled(true);
                            }
                        }
                        catch (JSONException e) {
                            btnAction.Text("error connecting " + status);
                            btnAction.Enabled(true);
                        }
                    }
                    else {
                        btnAction.Text("error connecting " + status);
                        btnAction.Enabled(true);
                    }
                    return true;
                }

            }
            // true means event has been handled by us (ie recognised)
            return false;
        }
        boolean testPassword(){
            final Integer min_pass=8, max_pass=32;
            dbg("Checking password validity");
            if (!newPassBox1.Text().equals(newPassBox2.Text())) {
                announce.ShowAlert(ui_txt.PASSWORD_MISMATCH);
                return false;
            }
            String pw=newPassBox1.Text();
            pw=pw.stripLeading();
            pw=pw.stripTrailing();
            if ((pw.length()<min_pass) || (pw.length()>max_pass)) {
                announce.ShowAlert(ui_txt.PASSWORD_LENGTH+"\n( "+min_pass.toString()+" <= length <="+max_pass.toString()+" )");
                return false;
            }
            /* Testing for:
                one or more digits      \d
                min 1 uppercase         [A-Z]
                min 1 lowercase         [a-z]

                I've put each regex pattern in an array of strings, and
                iterate across the elements of the array, manually counting each
                qty, returning if any error found.

                See: https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
             */
            String[] requirements={"\\d", "[A-Z]","[a-z]"};
            for (String regex : requirements) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(pw);
                Integer count = 0;
                while(matcher.find()) {
                    count++;
                }
                dbg(regex+"="+count.toString());
                if(count < 1){
                    announce.ShowAlert(ui_txt.PASSWORD_CONTENT);
                    return false;
                }
            }
            return true;
        }
}
