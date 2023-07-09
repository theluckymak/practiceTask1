
package ru.itis;

import ru.itis.repositories.EventsRepository;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.impl.EventsRepositoryFileImpl;
import ru.itis.repositories.impl.UsersRepositoryFileImpl;
import ru.itis.services.AppService;

public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt");
        EventsRepository eventsRepository = new EventsRepositoryFileImpl("events.txt", "events_users.txt");
        AppService appService = new AppService(usersRepository, eventsRepository);

//        appService.signUp("admin@gmail.com", "qwerty007");
//        appService.signUp("marsel@gmail.com", "qwerty008");

//        appService.addEvent("Практика по Java", LocalDate.now());
//        appService.addEvent("Практика по Golang", LocalDate.now().plusDays(1));

        appService.addUserToEvent("marsel@gmail.com", "Практика по Golang");
    }
}