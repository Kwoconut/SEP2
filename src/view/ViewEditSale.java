package view;

import java.io.IOException;
import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.MyDate;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelSale;

public class ViewEditSale implements View
{

   @FXML
   Label productLabel;
   
   @FXML
   Label startDateLabel;
   
   @FXML
   Label endDateLabel;
   
   @FXML
   Label priceReductionLabel;
   
   @FXML
   DatePicker startDatePicker;
   
   @FXML
   DatePicker endDatePicker;
   
   @FXML
   TextField priceReductionField;
   
   @FXML
   Label priceAfterReductionLabel;
   
   @FXML
   Label currentPriceLabel;
   
   @FXML
   Label errorLabel;
   
   
   private ViewModelSale viewModel;
   private MainView view;
   private Scene scene;
   private String title;

   public ViewEditSale()
   {

   }

   @Override
   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {

      this.viewModel = viewModel.getViewModelSale();
      this.view = view;
      this.scene = scene;
      this.title = title;
      
      productLabel.textProperty().bind(this.viewModel.getProductProperty().get().getNameProperty());
      
      startDateLabel.textProperty().bind(this.viewModel.getStartDateStringProperty());
      
      endDateLabel.textProperty().bind(this.viewModel.getEndDateStringProperty());
      
      priceReductionLabel.textProperty().bind(this.viewModel.getAmountProperty().asString());
      
      currentPriceLabel.textProperty().bind(this.viewModel.getProductProperty().get().getPriceProperty().asString());
      
      errorLabel.textProperty().bind(this.viewModel.getError1());
      
      priceAfterReductionLabel.textProperty().bind(this.viewModel.getPriceAfterReductionProperty().asString());

   }


   @Override
   public Scene getScene()
   {
      return scene;
   }

   @Override
   public String getTitle()
   {
      return title;
   }

   public void onCloseButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      priceReductionField.setText("0");
      errorLabel.setText("");
      startDatePicker.setValue(null);
      endDatePicker.setValue(null);
      view.setWindow("saleslist");
   }

   public void onSubmitButtonPressed() throws IOException
   {
      if (startDatePicker.getValue() == null
            || endDatePicker.getValue() == null)
      {
         errorLabel.setText("Please complete the dates");
      }
      else
      {
         LocalDate localDate = startDatePicker.getValue();
         LocalDate localDate2 = endDatePicker.getValue();
         this.viewModel.getStartDateProperty()
               .set(new MyDate(localDate.getDayOfMonth(),
                     localDate.getMonthValue(), localDate.getYear()));
         this.viewModel.getEndDateProperty()
               .set(new MyDate(localDate2.getDayOfMonth(),
                     localDate2.getMonthValue(), localDate2.getYear()));
         this.viewModel.editSale();

         if (errorLabel.getText().equals(""))
         {
            getScene().getWindow().hide();
            priceReductionField.setText("0");
            startDatePicker.setValue(null);
            endDatePicker.setValue(null);
            view.setWindow("saleslist");
         }
      }
   }

}
