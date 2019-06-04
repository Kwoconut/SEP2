package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.SLoginModel;

public class ViewModelInfo implements PropertyChangeListener
{
 private SLoginModel model;
 
 private StringProperty loginProperty;
 
 public ViewModelInfo(SLoginModel model) {
    this.model = model;
    this.model.addListener(this);
    this.loginProperty = new SimpleStringProperty("");
 }
 
 public StringProperty getLoginProperty() {
    return loginProperty;

 }

@Override
public void propertyChange(PropertyChangeEvent evt)
{
   if (evt.getPropertyName().equals("VALIDLOGIN"))
   {
      loginProperty.set((String) evt.getOldValue());
   }
   
}
 
}
