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
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.util.Ev3Constants;

import java.util.regex.*;

import org.json.JSONException;
import org.json.JSONObject;

import static net.fachtnaroe.gwlogin2023_fr.bits.dbg;

import java.util.Date;

public class RegistrationScreen extends Form implements HandlesEventDispatching {
        private
        HorizontalArrangement  grid;
        VerticalArrangement gridCenter,  mainArrangement;;
        TextBox usernameBox, rnBox, bornBox;
        PasswordTextBox passwordBox;
        Button btnRegister;
        Label padTop, padBottom, padBetweenLoginAndRegister, lblU, padBetween, lblP, padAboveLogin;
        Label lblTitleAtTop, gridPadLeft, getGridPadRight, lblRN, lblBorn;
        Web webCheckExists, webRegister;
        JSONObject jsonCredentials = new JSONObject();
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
            lblU.Text(ui_txt.REGISTER_GIVE_EMAIL);
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
            lblP.Text(ui_txt.REGISTER_GIVE_PASSWORD);
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
            lblRN.Text(ui_txt.REGISTER_GIVE_NAME);
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
            lblBorn.Text(ui_txt.REGISTER_GIVE_YOB);
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
            btnRegister.Text(ui_txt.REGISTER);
            btnRegister.TextColor(colors.BUTTON_TEXT);
            btnRegister.BackgroundColor(colors.BUTTON_BACKGROUND);

            padBottom = new Label(mainArrangement);
            padBottom.WidthPercent(100);
            padBottom.Height(Component.LENGTH_FILL_PARENT);

            announce=new Notifier(this);
            webCheckExists = new Web(this);
            webCheckExists.Url(ApplicationSettings.URL_LOGIN);
            webRegister = new Web(this);
            webRegister.Url(ApplicationSettings.URL_LOGIN);

            EventDispatcher.registerEventForDelegation(this, formName, "BackPressed");
            EventDispatcher.registerEventForDelegation(this, formName, "Click");
            EventDispatcher.registerEventForDelegation(this, formName, "GotText");
        }

        public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {

            dbg("dispatchEvent: " + formName + " [" + component.toString() + "] [" + componentName + "] " + eventName);
            if (eventName.equals("BackPressed")) {
                Form.finishActivity();
                return true;
            }
            else if (eventName.equals("Click")) {
                if (component.equals(btnRegister)) {
                    btnRegister.Enabled(false);
                    if (bits.isValidEmailAddress(usernameBox.Text())) {
                        if(testPassword() && testName() && testAge()) {
                            try {
                                jsonCredentials.put("action", "validate");
                                jsonCredentials.put("user", usernameBox.Text());

                                dbg("Sending: " + jsonCredentials.toString());
                                String msg = jsonCredentials.toString();
                                webCheckExists.PostText(msg);
                                btnRegister.Text(ui_txt.CONNECTION_SENDING);
                            }
                            catch (Exception e) {
                                return false;
                            }
                        }
                        else {
                            btnRegister.Enabled(true);
                        }
                    }
                    else {
                        announce.ShowAlert(ui_txt.REGISTER_INVALID_EMAIL);
                        btnRegister.Enabled(true);
                    }
                    return true;
                }
            } else if (eventName.equals("GotText")) {
                if (component.equals(webCheckExists)) {
                    String status = params[1].toString();
                    String textOfResponse = (String) params[3];
//                    from API:
//                    action=”validate”
//                    user=”_an_email_address”
//                    status=”OK”
//                    user=”exists”
//                    status=”error”
//                    detail=”not a valid email format”
//
//                    status=”OK”
//                    user=”unknown”
//

                    if (textOfResponse.equals("")) {
                        textOfResponse = status;
                    }
                    if (status.equals("200")) {
                        try {
                            JSONObject parser = new JSONObject(textOfResponse);
                            if (parser.getString("status").equals("OK")) {
                                String result = parser.getString("user");
                                if(result.contentEquals("exists")){
                                    announce.ShowAlert(ui_txt.REGISTER_USER_EXISTS);
                                    btnRegister.Enabled(true);
                                    btnRegister.Text(ui_txt.REGISTER);
                                }
                            }
                            else {
                                btnRegister.Text(parser.getString("status"));
                                btnRegister.Enabled(true);
                            }
                        }
                        catch (JSONException e) {
                            btnRegister.Text("error connecting " + status);
                            btnRegister.Enabled(true);
                        }
                    }
                    else {
                        btnRegister.Text("error connecting " + status);
                        btnRegister.Enabled(true);
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
            String pw=passwordBox.Text();
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
        boolean testName(){
            dbg("Checking Name validity");
            String rn=rnBox.Text();
            rn.replace(" ","");
            if(rn.length()<1) {
                announce.ShowAlert(ui_txt.REALNAME_LENGTH);
                return false;
            }
            return true;
        }

        boolean testAge(){
            dbg("Checking age validity");
            final Integer min_age=18;
            try {
                Date d=new Date();
                Integer thisyear = d.getYear();
                thisyear+=1900;
                Integer yob = Integer.valueOf(bornBox.Text());
                Integer age=thisyear - yob;
                dbg("Age "+age.toString());
                if(age < min_age) {
                    announce.ShowAlert(ui_txt.AGE_TOO_YOUNG);
                    return false;
                }
            }
            catch (Exception e) {
                announce.ShowAlert(ui_txt.AGE_TOO_YOUNG);
                return false;
            }
            return true;
        }
}
