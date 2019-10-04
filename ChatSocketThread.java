package chatclient;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import models.Message;
import models.User;


public class ChatSocketThread implements Runnable {
    
    private ObjectInputStream Ois = null;
    private ChatGUI form;
    private Socket socket;
    private User user_self;
    private List<User> clientUsers;
    
    public ChatSocketThread(ChatGUI form, Socket socket, ObjectInputStream Ois, User user_self) {

        this.Ois = Ois;
        this.form = form;
        this.socket = socket;
        this.user_self = user_self;
        
        clientUsers = new ArrayList<>();
    }
    
    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                Object obj = Ois.readObject();
                
                if (obj instanceof User) {
                    User user = (User) obj;
                    System.out.println("user from server " + user.getUsername());
                    if (!user_self.getUsername().equals(user.getUsername())) {
                        form.appendMessage(user.getUsername() + " joined the chat", Color.RED);
                        clientUsers.add(user);
                    }
                } else {
                    Message msgObj = (Message) obj;
                    if (!user_self.getUsername().equals(msgObj.getUser().getUsername())) {
                        if (msgObj.getType().equals("text")) {
                            System.out.println("message from server " + msgObj.getContent());

                            form.appendMessage(msgObj.getContent(), Color.RED);
                        } else {
                            form.appendMessage("", Color.RED); // to go for new line
                            // image message
                            ImageIcon imageIcon = new ImageIcon(msgObj.getFileData());

                            Image image = imageIcon.getImage(); // transform it 
                            // resize image to fit in the chat field
                            Image resizedImg = image.getScaledInstance(250, 250,
                                    java.awt.Image.SCALE_SMOOTH); 

                            form.insertImageToTxtPaneMsgs(resizedImg);
                            form.appendMessage(msgObj.getContent(), Color.RED);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
//            Logger.getLogger(ChatSocketThread.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(form, "Cannot connect to the server due to server has collapsed", 
                        "Notification",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
