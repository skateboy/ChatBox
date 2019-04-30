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
import javafx.event.EventHandler;

public class Controller {
    private String user;
    private String botName = "Huang";
    private String message;
    public static final boolean TRACE_MODE = false;


    String resourcesPath = getResourcesPath();
    Bot bot = new Bot("super",resourcesPath);
    Chat chatSession=new Chat(bot);




    @FXML
    private TextField txtUser, txtInput;

    @FXML
    private TextArea txtDisplay;


    @FXML
    private Button btnUser, btnSend;



    @FXML
    protected void initialize() {


        System.out.println(resourcesPath);
        MagicBooleans.trace_mode=TRACE_MODE;


        bot.writeAIMLFiles();

        bot.brain.nodeStats();

        txtUser.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                user = txtUser.getText();
                txtUser.setVisible(false);
                btnUser.setVisible(false);
                txtInput.setDisable(false);
                btnSend.setDisable(false);
                txtDisplay.setText(botName + ": " + "How may I help you today, " + user +"?\n\n");
            }

        });

        txtInput.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try{

                    String textLine="";
                    textLine=txtInput.getText();
                    String buffer[] = textLine.split(" ");

                    for(String str : buffer){
                        System.out.println(str);
                    }
                    if((textLine.toUpperCase().contains("name".toUpperCase()) && textLine.toUpperCase().contains("my".toUpperCase()))){
                        if(!textLine.toUpperCase().contains("what".toUpperCase())) {
                            user = buffer[buffer.length - 1];
                            System.out.println(user);
                        }
                    }
                    if((textLine.toUpperCase().contains("name".toUpperCase()) && textLine.toUpperCase().contains("your".toUpperCase()))){
                        if(!textLine.toUpperCase().contains("what".toUpperCase())) {
                            botName = buffer[buffer.length - 1];
                            System.out.println(botName);
                        }
                    }


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
                        txtDisplay.appendText(botName + ": " + response + "\n\n");
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        });

    }



    @FXML
    private void sendMessage(ActionEvent event){
        try{





            String textLine="";
            textLine=txtInput.getText();
            String buffer[] = textLine.split(" ");

            for(String str : buffer){
                System.out.println(str);
            }
            if((textLine.toUpperCase().contains("name".toUpperCase()) && textLine.toUpperCase().contains("my".toUpperCase()))){
                if(!textLine.toUpperCase().contains("what".toUpperCase())) {
                    user = buffer[buffer.length - 1];
                    System.out.println(user);
                }
            }
            if((textLine.toUpperCase().contains("name".toUpperCase()) && textLine.toUpperCase().contains("your".toUpperCase()))){
                if(!textLine.toUpperCase().contains("what".toUpperCase())) {
                    botName = buffer[buffer.length - 1];
                    System.out.println(botName);
                }
            }


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
                txtDisplay.appendText(botName + ": " + response + "\n\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void setUserName(ActionEvent event) {
        user = txtUser.getText();
        txtUser.setVisible(false);
        btnUser.setVisible(false);
        txtInput.setDisable(false);
        btnSend.setDisable(false);
        txtDisplay.setText(botName + ": " + "How may I help you today, " + user +"?\n\n");
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
