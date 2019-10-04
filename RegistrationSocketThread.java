package chatclient;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;


public class RegistrationSocketThread implements Runnable{

    private Socket socket;
    private User user;
    private RegistrationForm registrationForm;
    private ObjectInputStream Ois = null;
    private ObjectOutputStream Oos = null;
    
    public RegistrationSocketThread(Socket socket, User user, RegistrationForm registrationForm) throws IOException{
        
        this.socket = socket;
        this.user = user;
        this.registrationForm = registrationForm;
        Oos = new ObjectOutputStream(socket.getOutputStream());
        Ois = new ObjectInputStream(socket.getInputStream());
    }
    
    @Override
    public void run() {
        try {
            // send object user to check whether this user exist in database or not

            Oos.writeObject(user);

            // Update user from server after server finish checking
            user = (User) Ois.readObject();
            
            ChatGUI chatForum = new ChatGUI(user, socket, Oos, Ois);
            
            chatForum.setVisible(true);
            chatForum.updateMessagesFromServer();

            registrationForm.setVisible(false);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationSocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
