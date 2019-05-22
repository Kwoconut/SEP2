package DBSConnection;

import model.Sale;

public interface StoreSalePersistence {

	void addSale(Sale sale);

	void changedValue(Sale sale);

	void updateAddSale(Sale sale);

	void removeSale(Sale sale);

	void updateRemoveSale(Sale sale);

}
