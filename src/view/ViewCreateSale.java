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
      productIDLabel.textProperty().bind(this.viewModel.getProductProperty()
            .get().getIdProperty().asString());
      productNameLabel.textProperty()
            .bind(this.viewModel.getProductProperty().get().getNameProperty());
      productTypeLabel.textProperty()
            .bind(this.viewModel.getProductProperty().get().getTypeProperty());
      productColorLabel.textProperty().bind(
            this.viewModel.getProductProperty().get().getColourProperty());
      productPriceLabel.textProperty().bind(this.viewModel.getProductProperty()
            .get().getPriceProperty().asString());
      errorLabel.textProperty().bind(this.viewModel.getError1());
      errorField.textProperty().bind(this.viewModel.getError2());
      priceField.textProperty().bindBidirectional(
            this.viewModel.getAmountProperty(), new NumberStringConverter());
      priceReductionLabel.textProperty()
            .bind(this.viewModel.getPriceAfterReductionProperty().asString());

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
      getScene().getWindow().hide();
      view.setWindow("saleslist");
   }

   public void onSubmitButtonPressed() throws IOException
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
         getScene().getWindow().hide();
         priceField.setText("");
         view.setWindow("saleslist");
      }
   }

}
