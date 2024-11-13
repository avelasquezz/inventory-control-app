package service;

import repository.MovementRepository;
import model.Movement;

import java.util.Random;
import javax.swing.table.DefaultTableModel;

public class MovementService {
    private MovementRepository movementRepository;

    public MovementService(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public MovementRepository getMovementRepository() {
        return this.movementRepository;
    }

    public int generateId() {
        Random random = new Random();
        boolean findingNewId = true;
        int newId = 0;

        while(findingNewId) {
            findingNewId = false;
            newId = 1000 + random.nextInt(9000);
            for (Movement movement : this.movementRepository.getMovementsList()) {
                findingNewId = findingNewId || movement.getId() == newId;
            }
        }

        return newId;
    }

    public void updateTable(DefaultTableModel movementsTableModel) {
        movementsTableModel.setRowCount(0);
        
        for (Movement movement : movementRepository.getMovementsList()) {
            String[] tableRow = {
                String.valueOf(movement.getId()), 
                movement.getDescription(), 
                movement.getDate().toString(), 
                movement.getProduct().getName(), 
                movement.getType(),
                String.valueOf(movement.getOldQuantity()),
                String.valueOf(movement.getNewQuantity())
            };
            movementsTableModel.addRow(tableRow);
        }

        movementsTableModel.fireTableDataChanged();
    }
}
