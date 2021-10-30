/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Service;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class V_connexionController implements Initializable {

    @FXML
    private TextArea TextfLogin;
    @FXML
    private TextArea TextPassword;
    @FXML
    private Button TextfAnnuler;
    @FXML
    private Button TextfValider;
    @FXML
    private Text TextfErreur;
     private final Service service = new Service();
    
    private static V_connexionController ctrl;
    
    private User user;
    @FXML
    private Button txtInscrire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       TextfErreur.setVisible(false);
         ctrl = this;
    }    
        
    
    @FXML
    private void handleClear(ActionEvent event) {
        TextfLogin.clear();
        TextPassword.clear();
        TextfErreur.setVisible(false);
    }

    @FXML
    private void HandleConnexion(ActionEvent event) {
        String login = TextfLogin.getText().trim();
        String password = TextPassword.getText().trim();
        if(login.isEmpty() || password.isEmpty())
        {
          TextfErreur.setText("login ou le mot de passe Obligatoire");
          TextfErreur.setVisible(true);
        }
        else{
          user = service.login(login, password);
          if(user == null)
          {
               TextfErreur.setText("login ou le mot de passe Incorrect");
               TextfErreur.setVisible(true);
          }
          else
          {
              //Cache la fénétre de connexion
              this.TextfErreur.getScene().getWindow().hide();
              AnchorPane root = null;
              
              try {
                  root = FXMLLoader.load(getClass().getResource("/view/v_patient.fxml"));
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(V_connexionController.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
    }

    public static V_connexionController getCtrl() {
        return ctrl;
    }

    public User getUser() {
        return user;
    }

    @FXML
    private void handleinscription(ActionEvent event) {
        AnchorPane root;
        try {
                  root = FXMLLoader.load(getClass().getResource("/view/v_inscription.fxml"));
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(V_connexionController.class.getName()).log(Level.SEVERE, null, ex);
              }
    }
    
    
    
}
