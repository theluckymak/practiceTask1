package ru.itis.repositories;

import ru.itis.models.Event;
import ru.itis.models.User;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public interface EventsRepository extends CrudRepository<Event> {
    Event findByName(String nameEvent);

    List<Event> findAllByMembersContains(User user);

    Event findByName(String nameEvent);



    void saveUserToEvent(User user, Event event);
}
