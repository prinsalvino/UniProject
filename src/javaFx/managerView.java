package javaFx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;


public class managerView extends userView{

	private static final String OUTPUT_FILE = "results/prins.pdf";

	public managerView(userQuery query, Stage window) {
		super(query, window);
		displayHomeScene();
		TableColumn<Teacher,String> salarycolumn = new TableColumn<>("Salary");
		salarycolumn.setMinWidth(200);
		salarycolumn.setCellValueFactory(new  PropertyValueFactory<>("salary"));	
		
		teacherView.getColumns().add(salarycolumn);

		

		Label uname = new Label("Teacher Email");
		TextField emailinput = new TextField();
		
		Label password = new Label("Teacher Password");
		TextField passinput = new TextField();
		
		Label fnamelbl = new Label("Teacher First Name");	
		TextField firstnameInput = new TextField();
		
		Label lnamelbl = new Label("Teacher Last Name");
		TextField lastNameInput = new TextField();
		
		Label salarylbl = new Label("Teacher Salary");
		TextField salaryInput = new TextField();
		
		
		Button addbtn = new Button("Add Teacher");	
		addbtn.setOnAction(e -> addTeacher(emailinput.getText(), passinput.getText(), firstnameInput.getText(),lastNameInput.getText(),salaryInput.getText()));
		
		Button updateTeacher = new Button("Update Teacher Information");	
		updateTeacher.setOnAction(e -> updateTeacherForm());
		
		Button makepdf = new Button("Make PDF");	
		makepdf.setOnAction(e -> {
			try {
				createDocument();
			} 
			catch (java.io.IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		super.vBox.getChildren().addAll(uname,emailinput,password,passinput,fnamelbl,firstnameInput,lnamelbl,lastNameInput,salarylbl,salaryInput
			,addbtn,  updateTeacher);
	
	}
	
	private void addTeacher(String email, String pass, String fname, String lname, String ssalary) {
		try {
			int salary = 0;
			if (ssalary == null) {
				salary = 0;
			} else {
				salary = Integer.parseInt(ssalary);
			}
			query.addPerson(new Teacher(fname, lname, pass, email, salary));
			super.FillTeacherTable();
		} 
		catch (Exception e) {
			showMessageDialog(null, e);
			
		}
	}

	void updateTeacherForm() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(25,25,25,25));
		grid.setHgap(10);
		grid.setVgap(10);
		
		//Name label
		Label nameLabel = new Label("Old Email");
		//column,row
		grid.add(nameLabel,0,0);
		
		//Name Input
		TextField oldemailInput = new TextField();
		grid.add(oldemailInput,1,0);
		
		//Label Password
		Label pw = new Label("New Email");
		grid.add(pw, 0, 1);

		//Password Input
		TextField newemailinput = new TextField();
		grid.add(newemailinput, 1, 1);
		
		Label password = new Label("Teacher Password");
		grid.add(password, 0, 2);
		
		TextField passinput = new TextField();
		grid.add(passinput, 1, 2);

		
		Label fnamelbl = new Label("Teacher First Name");	
		grid.add(fnamelbl, 0, 3);

		
		TextField firstnameInput = new TextField();
		grid.add(firstnameInput, 1, 3);

		Label lnamelbl = new Label("Teacher Last Name");
		grid.add(lnamelbl, 0, 4);

		
		TextField lastNameInput = new TextField();
		grid.add(lastNameInput, 1, 4);

		Label salarylbl = new Label("Teacher Salary");
		grid.add(salarylbl, 0, 5);

		
		TextField salaryInput = new TextField();
		grid.add(salaryInput, 1, 5);

		Button updatebtn = new Button("Update");
		grid.add(updatebtn, 1, 6);
		updatebtn.setOnAction(e ->updateTeacher(oldemailInput.getText(),newemailinput.getText(), passinput.getText(),firstnameInput.getText(),lastNameInput.getText(),salaryInput.getText()));
		
		Scene sceneLogin = new Scene(grid, 500, 400);
		window.setScene(sceneLogin);
	}

	private void updateTeacher(String oldemail, String newemail, String pass, String firstn, String lastn, String ssalary) {
		try {
			int salary = 0;
			if (ssalary == null) {
				salary = 0;
			} else {
				salary = Integer.parseInt(ssalary);
			}
			
			query.modifyTeacher(oldemail,newemail,pass, firstn, lastn, salary);
			managerView managerView = new managerView(query, window);
		} catch (Exception e) {
			showMessageDialog(null, e);
		}
	}
	
	void createDocument() throws  java.io.IOException  {        
		PdfWriter writer = new PdfWriter(OUTPUT_FILE);
		PdfDocument pdfDocument = new PdfDocument(writer);
		pdfDocument.setTagged();
		Document document = new Document(pdfDocument);
		
		ObservableList<Person> perssons = query.getPersons();
		
		for (Person person : perssons) {
			document.add(new Paragraph(person.getEmail() + person.getFirstName() + person.getLastName()));
		}
		document.close();
	}


	

}
