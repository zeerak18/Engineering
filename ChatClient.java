package chatclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;


public class ChatClient {

    /**
      command line arguments
     */
    public static void main(String[] args) {
        try {
            RegistrationForm form = new RegistrationForm();
            form.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
