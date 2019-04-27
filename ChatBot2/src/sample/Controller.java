package sample;
import javafx.fxml.FXML;
import java.awt.event.ActionListener;
import java.lang.String;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javax.annotation.Resources;
import java.io.File;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

public class Controller {
    private String user;
    private String botHuang = "Huang";
    private String message;
    public static final boolean TRACE_MODE = false;
    static String botName = "Billy Bob Tha IV";

    @FXML
    private TextField txtUser, txtInput;

    @FXML
    private TextArea txtDisplay;


    @FXML
    private Button btnUser, btnSend;



    @FXML
    protected void initialize() {

    }

    @FXML
    private void sendMessage(ActionEvent event){
        try{

            String resourcesPath = getResourcesPath();
            System.out.println(resourcesPath);
            MagicBooleans.trace_mode=TRACE_MODE;
            Bot bot = new Bot("super",resourcesPath);
            Chat chatSession=new Chat(bot);
            bot.brain.nodeStats();
            String textLine="";
            textLine=txtInput.getText();
            txtInput.setText("");
            txtDisplay.appendText(user + ": " + textLine + "\n\n");
            if((textLine==null)||(textLine.length()< 1))
                textLine=MagicStrings.null_input;
            if(textLine.equals("quit")){
                System.exit(0);
            }else {
                String request = textLine;
                if (MagicBooleans.trace_mode)
                    txtDisplay.appendText("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic") + "\n\n");
                String response = chatSession.multisentenceRespond(request);
                while (response.contains("&lt;"))
                    response = response.replace("&lt;", "<");
                while (response.contains("&gt;"))
                    response = response.replace("&gt;", ">");
                txtDisplay.appendText(botHuang + ": " + response + "\n\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

            /*

            try{
                String resourcesPath = getResourcesPath();
                System.out.println(resourcesPath);
                MagicBooleans.trace_mode=TRACE_MODE;
                Bot bot = new Bot("Billy Bob",resourcesPath);
                Chat chatSession=new Chat(bot);
                bot.brain.nodeStats();
                txtInput.setText("");


                while(true){

                    if(txtInput.getText().isEmpty()){

                    } else {
                        message = txtInput.getText();
                        txtDisplay.appendText(user + ": " + message + "\n\n");

                        txtInput.setText("");
                    }


                    txtInput.setText(IOUtils.readInputTextLine());
                    if((txtInput==null)||(txtInput.getLength()< 1))
                        txtInput.setText(MagicStrings.null_input);
                    if(txtInput.equals("q")){
                        System.exit(0);
                    }else if(txtInput.equals("wq")){
                        bot.writeQuit();
                        System.exit(0);
                    }else{
                        String request=textLine;
                        if(MagicBooleans.trace_mode)
                            txtDisplay.setText("STATE="+request+":THAT="+((History)chatSession.thatHistory.get(0)).get(0)+":TOPIC="+chatSession.predicates.get("topic"));
                        String response=chatSession.multisentenceRespond(request);
                        while(response.contains("&lt;"))
                            response=response.replace("&lt;","<");
                        while(response.contains("&gt;"))
                            response=response.replace("&gt;",">");
                        txtDisplay.setText("Billy BOB : "+response);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            */

    }

    @FXML
    private void setUserName(ActionEvent event) {
        user = txtUser.getText();
        txtUser.setVisible(false);
        btnUser.setVisible(false);
        txtInput.setDisable(false);
        btnSend.setDisable(false);
        txtDisplay.setText(botHuang + ": " + "How may I help you today, " + user +"?\n\n");
    }


    public static String getResourcesPath(){
        File currDir=new File(".");
        String path=currDir.getAbsolutePath();
        path=path.substring(0,path.length()-2);
        System.out.println(path);
        String resourcesPath=path+File.separator+"src"+File.separator+"main"+File.separator+"resources";
        return resourcesPath;
    }
}
