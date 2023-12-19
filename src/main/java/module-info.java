module com.sebaescu.losconejosdefibonacci {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sebaescu.losconejosdefibonacci to javafx.fxml;
    exports com.sebaescu.losconejosdefibonacci;
}
