
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application {

	Employees emp;
	
	VBox outerPane;//main Pane
		HBox rbutPane;//holds radio buttons *
			RadioButton managerb;
			RadioButton clerkb;
			RadioButton vetb;
			RadioButton stylistb;
	
		VBox bottom;//holds everything else *
			HBox ltPane;//holds labels and text fields
				VBox labelPane;
					Label lastNameL;
					Label firstNameL;
					Label option1L;
					Label option2L;
					Label empNumL;
				VBox textPane;
					TextField lastNameT;
					TextField firstNameT;
					TextField option1T;
					TextField option2T;
					TextField empNumT;
			HBox but1Pane;//holds first row of buttons
				Button addEmpB;
				Button readFileB;
				Button displaySumRepB;
			HBox but2Pane;//holds second row of buttons
				Button writeSumRepB;
				Button exitB;
	
		Pane responsePane;
			Label responseL;
			
			
	Button doneB;

	
	
	public GUI() {

		
		//Radio buttons and padding
		managerb = new RadioButton("Manager");
		managerb.setPadding(new Insets(5));
		clerkb = new RadioButton("Clerk");
		clerkb.setPadding(new Insets(5));
		vetb = new RadioButton("Vet");
		vetb.setPadding(new Insets(5));
		stylistb = new RadioButton("Stylist");
		stylistb.setPadding(new Insets(5));
		
		//add radio buttons to rbutPane and align them
		rbutPane = new HBox(managerb, clerkb, vetb, stylistb);
		rbutPane.setAlignment(Pos.CENTER);
		
		//Labels and padding
		lastNameL = new Label("Last Name");
		lastNameL.setPadding(new Insets(4));
		firstNameL = new Label("First Name");
		firstNameL.setPadding(new Insets(4));
		option1L = new Label();
		option1L.setPadding(new Insets(4));
		option2L = new Label();
		option2L.setPadding(new Insets(4));
		empNumL = new Label("Employee ID");
		empNumL.setPadding(new Insets(4));
		
		//add labels to labelPane
		labelPane = new VBox(lastNameL, firstNameL, option1L, option2L, empNumL);
		labelPane.setAlignment(Pos.CENTER);
		
		//Text Fields 
		lastNameT = new TextField();
		firstNameT = new TextField();
		option1T = new TextField();
		option1T.setVisible(false);
		option2T = new TextField();
		option2T.setVisible(false);
		empNumT = new TextField();
		
		//add text fields to textPane
		textPane = new VBox(lastNameT, firstNameT, option1T, option2T, empNumT);
		textPane.setAlignment(Pos.CENTER);
		
		//add labelPane and textPane to ltPane
		ltPane = new HBox(labelPane, textPane);
		ltPane.setAlignment(Pos.CENTER);
		
		//first row of bottom buttons
		addEmpB = new Button("Add Employee");
		readFileB = new Button("Read From File");
		displaySumRepB = new Button("Display Summary Pay Report");
		//second row of bottom buttons
		writeSumRepB = new Button("Write Summary Pay Report");
		exitB = new Button("Exit");
		
		//add buttons to but1 and but2 Panes
		but1Pane = new HBox(addEmpB, readFileB, displaySumRepB);
		but1Pane.setAlignment(Pos.CENTER);

		but2Pane = new HBox(writeSumRepB, exitB);
		but2Pane.setAlignment(Pos.CENTER);
		
		//add Panes to bottom Pane
		bottom = new VBox(ltPane, but1Pane, but2Pane);
		bottom.setAlignment(Pos.CENTER);
		
		//response label and Pane
		responseL = new Label();
		responseL.setPadding(new Insets(10));
		responsePane = new Pane(responseL);
		
		//add rbutPane and bottom to outerPane
		outerPane = new VBox(rbutPane, bottom, responsePane);
		outerPane.setAlignment(Pos.CENTER);
		
		//Creat Done button
		doneB = new Button("Done");
		

		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		emp = new Employees();
		
		/* SETUP EVENT HANDLERS FOR RADIO BUTTONS */
		managerb.setOnAction(e -> empTypeChange(1));
		
		clerkb.setOnAction(e -> empTypeChange(2));
		
		vetb.setOnAction(e -> empTypeChange(3));
		
		stylistb.setOnAction(e -> empTypeChange(4));
		
		/* SETUP EVENT HANDLERS FOR BUTTONS */
		
		addEmpB.setOnAction(new addEmployeeHandler()); //input for this function should be the text fields
		
		writeSumRepB.setOnAction(new writeFileHandler()); 
		
		readFileB.setOnAction(new readFileHandler());
		
		displaySumRepB.setOnAction(e -> showSummaryReport(primaryStage));
		
		exitB.setOnAction(e -> System.exit(0));
		
		
		
		/* PUT EVERYTHING TOGETHER */
		Scene scene = new Scene(outerPane, 400, 300);
		
		doneB.setOnAction(e -> switchScene(primaryStage, scene));
		
		// Configure and display the stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("Employee Payroll");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		GUI.launch(args);
	}
	
	//Show and hide specific labels and boxes for different selections
	private void empTypeChange(int paycode){
		switch(paycode){
		case 1:
			//change labels
			option1L.setText("Salary");
			option1T.setVisible(true);
			option2L.setText("");
			option2T.setVisible(false);
			//unselect buttons
			clerkb.setSelected(false);
			vetb.setSelected(false);
			stylistb.setSelected(false);
			break;
		case 2:
			//change labels
			option1L.setText("Hourly Pay");
			option1T.setVisible(true);
			option2L.setText("Hours Worked");
			option2T.setVisible(true);
			//unselect buttons
			managerb.setSelected(false);
			vetb.setSelected(false);
			stylistb.setSelected(false);
			break;
		case 3:
			//change labels
			option1L.setText("");
			option1T.setVisible(false);
			option2L.setText("# Vaccines");
			option2T.setVisible(true);
			//unselect buttons
			managerb.setSelected(false);
			clerkb.setSelected(false);
			stylistb.setSelected(false);
			break;
		case 4:
			//change labels
			option1L.setText("Pay Rate");
			option1T.setVisible(true);
			option2L.setText("# Appointments");
			option2T.setVisible(true);
			//unselect buttons
			managerb.setSelected(false);
			vetb.setSelected(false);
			clerkb.setSelected(false);
			break;
		default:
			break;
		}
	}
	
	//Summary Report window
	private void showSummaryReport(Stage primaryStage){

		//get String for report
		String content = new String(emp.generateWeeklyReport());

		// Create the text label
		Label aboutLabel = new Label();
		aboutLabel.setWrapText(true);
		aboutLabel.setTextAlignment(TextAlignment.LEFT);
		aboutLabel.setText(content);

		// Add the label to a ScrollPane, and that to a VBox with done button
		ScrollPane sPane = new ScrollPane(aboutLabel);
		VBox pane = new VBox(sPane, doneB);
		pane.setAlignment(Pos.CENTER);

		// Create and display the pane in the primary stage 	
		Scene scene = new Scene(pane, 400, 250);
		primaryStage.setScene(scene);
		
	}
	
	//Switches the Scene in a specific Stage
	private void switchScene(Stage primaryStage, Scene scene){
		primaryStage.setScene(scene);
	}
	
	//set response label to new response
	private void updateResponse(String response){
		responseL.setText(response);
	}
	
	//read file and add employees from it
	private class readFileHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			
			//open up file chooser and get file
			FileChooser fileChooser= new FileChooser();
			File inFile = fileChooser.showOpenDialog(null);
			
			try {
				Scanner scan = new Scanner(inFile);
				
				int paycode = 0;
				String firstName = ""; 
				String lastName = "";
				double first = 0;
				int second = 0;
				int empNum = 0;
				
				while(scan.hasNext()){
					
					//get paycode first
					paycode = scan.nextInt();
					
					//assign variables above to the next thing you scan in the right order
					switch(paycode){
					
					//manager
					case 1:
						firstName = scan.next();
						lastName = scan.next();
						empNum = scan.nextInt();
						first = scan.nextDouble();
						break;
					
					//clerk
					case 2:
						firstName = scan.next();
						lastName = scan.next();
						empNum = scan.nextInt();
						first = scan.nextDouble();
						second = scan.nextInt();
						break;
					
					//vet
					case 3:
						firstName = scan.next();
						lastName = scan.next();
						empNum = scan.nextInt();
						second = scan.nextInt();
						break;
					
					//stylist
					case 4:
						firstName = scan.next();
						lastName = scan.next();
						empNum = scan.nextInt();
						first = scan.nextDouble();
						second = scan.nextInt();
						break;
					
					//wrong pay code
					default:
						break;
					}
					
					//add the employee to the list 
					emp.addEmployee(paycode, firstName, lastName, first, second, empNum);
					
				}//end while
				
				//close scanner, sort the employees, and update response
				scan.close();
				emp.sort();
				updateResponse("File scanned and prcoessed with " + emp.getErrorsFound() + " errors found");
				
			} catch (FileNotFoundException e) {
				updateResponse("File not found");
			}
			
		}
	}
	
	//writes summary report to file WeeklySumReport.txt
	private class writeFileHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			try{ 
				Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("WeeklySumReport.txt")));
				writer.write(emp.generateWeeklyReport());
				writer.close();
				updateResponse("Summary Report successfully wrote to file WeeklySumReport.txt");
			}
			catch(Exception e){
				e.printStackTrace();
				updateResponse("Could not write Summary Report");
			}
		}
	}
	
	//add employee based on type selected
	private class addEmployeeHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			if(managerb.isSelected() && !option1T.getText().isEmpty()){
				emp.addEmployee(1, firstNameT.getText(), lastNameT.getText(), Double.parseDouble(option1T.getText()), 0, Integer.parseInt(empNumT.getText()));
				updateResponse("Manager successfully added");
			}
			else if(clerkb.isSelected() && !option1T.getText().isEmpty() && !option2T.getText().isEmpty()){
				emp.addEmployee(2, firstNameT.getText(), lastNameT.getText(), Double.parseDouble(option1T.getText()), Integer.parseInt(option2T.getText()), Integer.parseInt(empNumT.getText()));
				updateResponse("Clerk successfully added");
			}
			else if(vetb.isSelected() && !option2T.getText().isEmpty()){
				emp.addEmployee(3, firstNameT.getText(), lastNameT.getText(), 0, Integer.parseInt(option2T.getText()), Integer.parseInt(empNumT.getText()));
				updateResponse("Vet successfully added");
			}
			else if(stylistb.isSelected() && !option1T.getText().isEmpty() && !option2T.getText().isEmpty()){
				emp.addEmployee(4, firstNameT.getText(), lastNameT.getText(), Double.parseDouble(option1T.getText()), Integer.parseInt(option2T.getText()), Integer.parseInt(empNumT.getText()));
				updateResponse("Stylist successfully added");
			}
			else{
				updateResponse("Cannot add employee without type selected and fields filled in");
			}
			emp.sort();
			firstNameT.setText("");
			lastNameT.setText("");
			option1T.setText("");
			option2T.setText("");
			empNumT.setText("");

			
		}
	}
	
}
