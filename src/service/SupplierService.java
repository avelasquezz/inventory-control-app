package service;

import model.Supplier;
import repository.SupplierRepository;

import java.util.List;
import java.util.ArrayList;

public class SupplierService {
    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<String> getSupplierNamesList() {
        List<String> supplierNamesList = new ArrayList<>();
        
        for (Supplier supplier : supplierRepository.getSuppliersList()) {
            supplierNamesList.add(supplier.getName());
        }

        return supplierNamesList;
    }
}
