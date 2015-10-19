package Database;


import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;
import javafx.scene.chart.XYChart.Data;

public  class RowSelectChangeListener implements ChangeListener {


	@Override
	public void changed(ObservableValue observable, Object oldValue, Object newValue) {
		  int ix = newValue.intValue();
		  if ((ix = Data.size())) {
         return; // invalid data

	              }


	// Book book = data.get(ix);
Spiel spiel = data.get(ix);
   //  actionStatus.setText(book.toString()); 
		
	}

}
