package javaFx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static javax.swing.JOptionPane.showMessageDialog;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application 
{
	Stage window;
	Scene sceneLogin, sceneHome;
	ObservableList<Person> persons = FXCollections.observableArrayList();
	

	userQuery query = new userQuery();

	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Student student1 = new Student("Prins","Alvino","IT1","Prins123","prins@gmail.com",80,85);
		Teacher teacher1 = new Teacher("Michael","Corneole","Michael123","michael@gmail.com",2000);
		Manager manager1 = new Manager("Justin","Hewlett","Justin123","justin@gmail.com");
		
		query.addPerson(student1);
		query.addPerson(manager1);
		query.addPerson(teacher1);

		window = primaryStage;
		window.setTitle("University Project");
		
		buildLoginScene();
				
        window.show();
	}
	
	private void buildLoginScene() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(25,25,25,25));
		grid.setHgap(10);
		grid.setVgap(10);
		
		//Name label
		Label nameLabel = new Label("Username");
		grid.add(nameLabel,0,0);
		
		//Name Input
		TextField nameInput = new TextField();
		grid.add(nameInput,1,0);
		
		//Label Password
		Label pw = new Label("Password:");
		grid.add(pw, 0, 1);

		//Password Input
		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 1);
	
		Button loginbtn = new Button("Login");
		grid.add(loginbtn, 1, 2);
		loginbtn.setOnAction(e -> checkLogin(nameInput.getText(), pwBox.getText()));
		
		sceneLogin = new Scene(grid, 300, 200);
		window.setScene(sceneLogin);
	}
	
	private void checkLogin(String email, String password) {
		persons = query.getPersons();
		boolean checkLogin = false;
	
		for(Person person : persons) {
			if (email.trim().equals(person.getEmail()) && password.trim().equals(person.getPassword())) {

				if (person.type == AccessType.ADMIN) {
					managerView managerView = new managerView(query, window);
				}
				else if(person.type == AccessType.EDITOR) {
					teacherView teacherView = new teacherView(window, query);
					
				}
				else if(person.type == AccessType.BASIC) {
					studentView studentView = new studentView(query, window);
				}
				checkLogin = true;
			}
		}
		
		if (checkLogin == false) {
			showMessageDialog(null, "Wrong email or password");
		} 
	}
	

	

}

