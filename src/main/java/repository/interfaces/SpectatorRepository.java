package repository.interfaces;

import domain.Spectator;

public interface SpectatorRepository extends Repository<Integer, Spectator> {
    Spectator findByUsername(String username);
}
