package ru.vlubchen.gradjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vlubchen.gradjava.model.Vote;
import ru.vlubchen.gradjava.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudVoteRepository;

    private final CrudUserRepository crudUserRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getReferenceById(userId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudVoteRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id)
                .filter(meal -> meal.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Vote> getByRestaurantInDay(int restaurantId, LocalDate day) {
        return crudVoteRepository.getByRestaurantInDay(restaurantId, day);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }
}