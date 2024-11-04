package service;

import repository.MovementRepository;
import model.Movement;

import java.util.Random;

public class MovementService {
    private MovementRepository movementRepository;

    public MovementService(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
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
}
