import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/* LayoutsDemo.java
*
* An application used to demonstrate the basics of 
* creating components such as panels, arranging
* components using layout objects, and nesting
* components inside each other.
*
* Sean Holden, Oct 2014
* - Updated examples from Swing to JavaFX
*/

public class LayoutsDemo extends Application {

	// Instantiate panes of different layout types
	private FlowPane flowPane = new FlowPane(20,20);
	private GridPane gridPane =  new GridPane();
	private BorderPane borderPane = new BorderPane();
	private TilePane tilePane = new TilePane();

	// Create buttons for flow pane
	private Button 
	flowBt1 = new Button("Button 1"),
	flowBt2 = new Button("Button 2"),
	flowBt3 = new Button("Button 3"),
	flowBt4 = new Button("Button 4"),
	flowBt5 = new Button("Button 5"),
	flowBt6 = new Button("Button 6");

	// Create buttons for grid pane
	private Button 
	gridBt1 = new Button("Button 1"),
	gridBt2 = new Button("Button 2"),
	gridBt3 = new Button("Button 3"),
	gridBt4 = new Button("Button 4"),
	gridBt5 = new Button("Button 5"),
	gridBt6 = new Button("Button 6");

	// Create buttons for border pane
	private Button 
	borderBt1 = new Button("Button 1"),
	borderBt2 = new Button("Button 2"),
	borderBt3 = new Button("Button 3"),
	borderBt4 = new Button("Button 4"),
	borderBt5 = new Button("Button 5");

	// Create buttons for stack pane
	private Button 
	tileBt1 = new Button("Button 1"),
	tileBt2 = new Button("Button 2"),
	tileBt3 = new Button("Button 3"),
	tileBt4 = new Button("Button 4"),
	tileBt5 = new Button("Button 5"),
	tileBt6 = new Button("Button 6");
	
	// Create tabs and the tab pane
	private TabPane tabPane = new TabPane();
	private Tab 
	flowTab = new Tab(),
	gridTab = new Tab(),
	borderTab = new Tab(),
	tileTab = new Tab();

	//  constructor
	public LayoutsDemo() {
		
		flowBt2.setFont(new Font("Courier New", 12));
		
		flowBt3.setStyle("-fx-font-size: 12");
		flowBt3.setStyle("-fx-font-family: 'monospace'");
		
		// Get the list of the children nodes for the FlowPane
		ObservableList<Node> flowPaneChildren = flowPane.getChildren();
		// Add the flow buttons to the list
		flowPaneChildren.add(flowBt1);
		flowPaneChildren.add(flowBt2);
		flowPaneChildren.add(flowBt3);
		flowPaneChildren.add(flowBt4);
		flowPaneChildren.add(flowBt5);
		flowPaneChildren.add(flowBt6);

		//set grid gaps
		gridPane.setHgap(20);
		gridPane.setVgap(20);
		
		// Add the grid buttons to the list
		// Usage: (Node, column, row)
		gridPane.add(gridBt1, 0, 0);
		gridPane.add(gridBt2, 1, 0);
		gridPane.add(gridBt3, 0, 1);
		gridPane.add(gridBt4, 1, 1);
		gridPane.add(gridBt5, 0, 2);
		gridPane.add(gridBt6, 1, 2);
		
		borderBt5.setMinHeight(15);
		borderBt5.setShape(new Circle(20));
		borderBt5.setRotate(45);
		

		// Set each part of the BorderPane with a button
		borderPane.setTop(borderBt1);
		borderPane.setBottom(borderBt2);
		borderPane.setLeft(borderBt3);
		borderPane.setRight(borderBt4);
		borderPane.setCenter(borderBt5);
		
		
		tileBt4.setStyle("-fx-background-color: red");
		// Get the list of the children nodes for the StackPane
		ObservableList<Node> tilePaneChildren = tilePane.getChildren();
		// Add the stack buttons to the list
		tilePaneChildren.add(tileBt1);
		tilePaneChildren.add(tileBt2);
		tilePaneChildren.add(tileBt3);
		tilePaneChildren.add(tileBt4);
		tilePaneChildren.add(tileBt5);
		tilePaneChildren.add(tileBt6);
		
		tilePane.setCursor(Cursor.CROSSHAIR);

		
		// Add the panes to the TabPane
		flowTab.setText("Flow");
		flowTab.setContent(flowPane);
		tabPane.getTabs().add(flowTab);

		gridTab.setText("Grid");
		gridTab.setContent(gridPane);
		tabPane.getTabs().add(gridTab);

		borderTab.setText("Border");
		borderTab.setContent(borderPane);
		tabPane.getTabs().add(borderTab);
		
		tileTab.setText("Tile");
		tileTab.setContent(tilePane);
		tabPane.getTabs().add(tileTab);
		
		
		
	}  // end of constructor LayoutsDemo()

	@Override
	public void start(Stage primaryStage) throws Exception {

		Scene scene = new Scene(tabPane, 350, 275);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX Layouts Demo");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		LayoutsDemo.launch(args);
	}
	
} // end of class LayoutDemo
