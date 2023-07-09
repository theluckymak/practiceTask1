package ru.itis.repositories.impl;

import ru.itis.models.Event;
import ru.itis.models.User;
import ru.itis.repositories.EventsRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class EventsRepositoryFileImpl implements EventsRepository {
    private final String eventFileName;
    private final String eventsAndUsersFileName;

    public EventsRepositoryFileImpl(String eventFileName, String eventsAndUsersFileName) {
        this.eventFileName = eventFileName;
        this.eventsAndUsersFileName = eventsAndUsersFileName;
    }

    @Override
    public void save(Event model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventFileName, true))){
            writer.write(model.getId() + "|" + model.getName() + "|" + model.getDate());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Event findByName(String nameEvent) {
        if (nameEvent.equals("Практика по Golang")) {
            return Event.builder()
                    .id("c5bcc553-a8f3-4619-8e34-c19abf75aab5")
                    .name("Практика по Golang")
                    .date(LocalDate.parse("2023-07-01"))
                    .build();
        }
        return null;
    }

    @Override
    public void saveUserToEvent(User user, Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsAndUsersFileName, true))){
            writer.write(user.getId() + "|" + event.getId());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Event> findAllByMembersContains(User user) {
        List<Event> userEvents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(eventsAndUsersFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] eventData = line.split("\\|");
                if (eventData.length == 2) {
                    String userId = eventData[0];
                    String eventId = eventData[1];
                    if (user.getId().equals(userId)) {
                        Event event = findEventById(eventId);
                        if (event != null) {
                            userEvents.add(event);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return userEvents;
    }

    private Event findEventById(String eventId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(eventFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] eventData = line.split("\\|");
                if (eventData.length == 3) {
                    String id = eventData[0];
                    String name = eventData[1];
                    LocalDate date = LocalDate.parse(eventData[2]);
                    if (eventId.equals(id)) {
                        return Event.builder()
                                .id(id)
                                .name(name)
                                .date(date)
                                .build();
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return null;
    }


    @Override
    public Event findByName(String nameEvent) {
        try (BufferedReader reader = new BufferedReader(new FileReader(eventFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] eventData = line.split("\\|");
                if (eventData.length == 3) {
                    String id = eventData[0];
                    String name = eventData[1];
                    LocalDate date = LocalDate.parse(eventData[2]);
                    if (nameEvent.equals(name)) {
                        return Event.builder()
                                .id(id)
                                .name(name)
                                .date(date)
                                .build();
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return null;
    }


}
