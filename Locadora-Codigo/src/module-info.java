module ZCruder {
	requires javafx.controls;
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Entity;
}
