package front.controllers.kernel;

import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;


public interface ControllerInterface {

    void ExecuteQuerySQL(String SQL);

    void BuildTableSQL(TableView tableview, String SQL);

    void SetComponentsToNull();

    void LogoutTextHandler(MouseEvent e);

    void ChangeDataTextHandler(MouseEvent e);
}
