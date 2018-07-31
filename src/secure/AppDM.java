package secure;

import dal.PersonDAO_MySQL_Hibernate_App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hammer on 30.05.2017.
 */
public class AppDM {
    private PersonDAO_MySQL_Hibernate_App pd = null;
    private App app = null;

    public AppDM() {
        pd = new PersonDAO_MySQL_Hibernate_App();
    }

    public ActionRead appRead = new ActionRead();

    private class ActionRead implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            app = pd.read();
        }
    }
}
