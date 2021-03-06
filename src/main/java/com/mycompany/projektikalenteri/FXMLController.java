package com.mycompany.projektikalenteri;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Locale;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import java.awt.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class FXMLController implements Initializable {
	private ResourceBundle resources;
    private DatabaseService dBService;
    private LoggedInUser user;

    @FXML
    private ComboBox langChoice;
    @FXML
    private Text promptRegistrationText;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordConfirmField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField displayNameField;
    @FXML
    private GridPane monthPane;
    @FXML
    private Text tekstikentta;
    @FXML
    private Text promptLoginText;
    @FXML
    private Text monthName;



    // Kirjautumispainikkeen toiminnot

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        System.out.println("Painoit kirjaudu-painiketta!"); //$NON-NLS-1$

        if (usernameTextfield.getText().trim().isEmpty()) {
            promptLoginText.setText(resources.getString("FXMLController.insertUsername")); //$NON-NLS-1$
        } else if (passwordField.getText().trim().isEmpty()) {
            promptLoginText.setText(resources.getString("FXMLController.insertPassword"));

        } else {
            System.out.println("Yritetään kirjautua!"); //$NON-NLS-1$

            try{
                String userName = usernameTextfield.getText();
                String password = passwordField.getText();
                user = dBService.loadLoggedInUser(userName, password);
                if (user != null) {
                	user.setDatabaseService(dBService);
                    moveToCalendarScene(event);

                }

            } catch (Exception ex) {
                System.out.println("Kirjautuminen epäonnistui"); //$NON-NLS-1$
                ex.printStackTrace();

            }

        }

    }

    // Rekisteröimispainikkeen toiminnot
    @FXML
    private void handleRegistrationButtonAction(ActionEvent event) {
        System.out.println("Painoit rekisteröitymispainiketta!"); //$NON-NLS-1$
        if (usernameTextfield.getText().trim().isEmpty()) {
            promptLoginText.setText(resources.getString("FXMLController.insertUsername")); //$NON-NLS-1$
//        } else if (passwordTextfield.getText().trim().isEmpty()) {
//            promptText.setText("Syötä salasana!!");
        }
        if (passwordField.getText().equals(passwordConfirmField.getText())){
	        try{
	            String userName = usernameTextfield.getText();
	            String passwd = passwordField.getText();
	            String displayName = displayNameField.getText();
	            user = dBService.createUser(userName, passwd, displayName);

	            if (user != null) {
	            	System.out.println("Rekisteröityminen onnistui!"); //$NON-NLS-1$
	            }

	        } catch (Exception ex) {
	            System.out.println("Rekisteröityminen epäonnistui"); //$NON-NLS-1$
	            ex.printStackTrace();
	        }
        } else {
        	System.out.println("Salasanat ei täsmää"); //$NON-NLS-1$
        }


    }

    // Siirtyminen kirjautumisnäkymään
    @FXML
    private void handleRegistrationSceneButtonAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScene.fxml"), resources);
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll("/styles/Styles.css", "/styles/LoginStyles.css");
        stage.setTitle(resources.getString("FXMLController.projectCalendar_login"));
        stage.setScene(scene);
        stage.show();
    }

    // Siirtyminen rekisteröintinäkymään
    @FXML
    private void handleLoginSceneButtonAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RegistrationScene.fxml"), resources); //$NON-NLS-1$
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll("/styles/Styles.css", "/styles/Registration.css");         //$NON-NLS-1$ //$NON-NLS-2$
        stage.setTitle(resources.getString("FXMLController.projectCalendar_register")); //$NON-NLS-1$
        stage.setScene(scene);
        stage.show();

    }

   
    private void moveToCalendarScene(ActionEvent event) throws IOException {
    	if (langChoice.getValue().toString().contains("suomi")) {
    		Locale currentLocale = new Locale("fi","FI");
        	Locale.setDefault(currentLocale);
            resources = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        } else {
        	Locale currentLocale = new Locale("ar","AE");
        	Locale.setDefault(currentLocale);
            resources = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        }

    	Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalendarScene.fxml"), resources); //$NON-NLS-1$
        BorderPane pane = loader.load();
        CalendarController controller = loader.getController();

        Scene scene = new Scene(pane);

        stage.setTitle(resources.getString("calendarTitle"));

        stage.setScene(scene);

        stage.show();
        controller.setUser(user);



    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	resources = ResourceBundle.getBundle("MessagesBundle",Locale.getDefault());

    	dBService = new DatabaseService(resources);
    	if(langChoice != null) {
    		langChoice.getSelectionModel().select(1);
    	}

    }


}
