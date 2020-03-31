package javaFx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.stage.Stage;

public class Main extends Application 
{
	Stage window;
	Scene sceneLogin, sceneHome;
	ObservableList<Person> persons = FXCollections.observableArrayList();
	TableView<Teacher> teacherView;
	
	Manager currentManager;
	Student currentStudent;
	Teacher currentTeacher;

	public static void main(String[] args) {
		launch(args);
	}
	
	private void generatePerson() {
		Student student1 = new Student("Prins","Alvino","IT1","Prins123","prins@gmail.com");
		Teacher teacher1 = new Teacher("Michael","Corneole","Michael123","michael@gmail.com");
		Manager manager1 = new Manager("Justin","Hewlett","Justin123","justin@gmail.com");
		
		persons.add(student1);
		persons.add(teacher1);
		persons.add(manager1);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		generatePerson();
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
	
		for(Person person : persons) {
			if (email.trim().equals(person.getEmail()) && password.trim().equals(person.getPassword())) {
				if (person instanceof Manager) {
					currentManager = (Manager) person;
				}
				else if(person instanceof Teacher) {
					currentTeacher = (Teacher)person;
				}
				else if(person instanceof Student) {
					currentStudent = (Student)person;
					displayHomeScene();
				}
				break;
			}
		}
	}
	
	private void displayHomeScene() {

		VBox vBox = new VBox();
	
		TableColumn<Teacher,String> fnamecolumn = new TableColumn<>("First Name");
		fnamecolumn.setMinWidth(200);
		fnamecolumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		TableColumn<Teacher,String> lnamecolumn = new TableColumn<>("Last Name");
		lnamecolumn.setMinWidth(200);
		lnamecolumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));		
		
		TableColumn<Teacher,String> emailcolumn = new TableColumn<>("email");
		emailcolumn.setMinWidth(200);
		emailcolumn.setCellValueFactory(new  PropertyValueFactory<>("email"));		
		
		teacherView = new TableView<>();
		//teacherView.setItems(getTeacher());
		
		teacherView.getItems().setAll(getTeacher());
	
		teacherView.getColumns().addAll(fnamecolumn, lnamecolumn,emailcolumn);
		
		vBox.getChildren().addAll(teacherView);
		

		sceneHome = new Scene(vBox, 1000, 1000);
		window.setScene(sceneHome);
	}
	
	
	private ObservableList<Teacher> getTeacher() {
		ObservableList<Teacher> teachers = FXCollections.observableArrayList();
		for(Person person : persons) 
		{
			if (person instanceof Teacher) {
				teachers.add((Teacher) person);
			}		
		}
		
		return teachers;
	}
}

