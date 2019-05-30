package view;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.converter.NumberStringConverter;
import model.MyDate;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelSale;

public class ViewCreateSale implements View
{

   @FXML
   Label productIDLabel;
   @FXML
   Label productNameLabel;
   @FXML
   Label productTypeLabel;
   @FXML
   Label productColorLabel;
   @FXML
   Label productPriceLabel;
   @FXML
   ImageView productImage;
   @FXML
   DatePicker startDatePicker;
   @FXML
   DatePicker endDatePicker;
   @FXML
   Label errorLabel;
   @FXML
   TextField priceField;
   @FXML
   Label errorField;
   @FXML
   Label priceReductionLabel;

   private ViewModelSale viewModel;
   private MainView view;
   private Scene scene;
   private String title;

   public ViewCreateSale()
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
      
      productIDLabel.textProperty().bind(this.viewModel.getProductIDProperty().asString());

      productNameLabel.textProperty()
            .bind(this.viewModel.getProductNameProperty());

      productTypeLabel.textProperty()
            .bind(this.viewModel.getProductTypeProperty());

      productColorLabel.textProperty().bind(
            this.viewModel.getProductColorProperty());

      productPriceLabel.textProperty().bind(this.viewModel.getProductPriceProperty().asString());

      priceField.textProperty().bindBidirectional(
            this.viewModel.getAmountProperty(), new NumberStringConverter());

      priceReductionLabel.textProperty()
            .bind(this.viewModel.getPriceAfterReductionProperty().asString());

      errorLabel.textProperty().bindBidirectional(this.viewModel.getError1());

      priceField.setOnKeyReleased(event -> {
         this.viewModel.updatePriceAfterReduction();
      });

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

   public void onClosedButtonPressed() throws IOException
   {
      refreshFields();
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
         this.viewModel.addSale();

         if (errorLabel.getText().equals(""))
         {
            refreshFields();
            view.setWindow("saleslist");
         }
      }
   }

   private void refreshFields()
   {
      getScene().getWindow().hide();
      priceField.setText("0");
      errorLabel.setText("");
      startDatePicker.setValue(null);
      endDatePicker.setValue(null);
   }

}
