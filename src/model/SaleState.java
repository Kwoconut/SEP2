package model;

import java.io.Serializable;

public interface SaleState extends Serializable
{
  void setNextState(Sale sale);
  
  double getPrice(Sale sale);
}