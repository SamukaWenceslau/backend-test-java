package br.com.fcamara.parkingproject.utils;

import br.com.fcamara.parkingproject.controller.form.AddressForm;
import br.com.fcamara.parkingproject.model.Address;
import br.com.fcamara.parkingproject.model.ParkingLot;

public class ParkingLotConvertTo {

    public ParkingLot convertToParkingLot(AddressForm form, Address address) {

        ParkingLot parkingLot = new ParkingLot(form.getMotocycleSpaces(), form.getCarSpaces());
        parkingLot.setAddress(address);

        return parkingLot;
    }
}
