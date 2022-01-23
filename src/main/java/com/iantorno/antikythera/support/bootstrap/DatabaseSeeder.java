package com.iantorno.antikythera.support.bootstrap;

import com.github.javafaker.Faker;
import com.iantorno.antikythera.model.EventNode;
import com.iantorno.antikythera.model.LocationNode;
import com.iantorno.antikythera.repository.LocationRepository;
import com.iantorno.antikythera.repository.TimelineRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseSeeder implements ApplicationListener<ContextRefreshedEvent> {

  private final LocationRepository locationRepository;
  private final TimelineRepository timelineRepository;

  private final Faker faker;

  public DatabaseSeeder(LocationRepository locationRepository, TimelineRepository timelineRepository) {
    this.locationRepository = locationRepository;
    this.timelineRepository = timelineRepository;
    this.faker = new Faker(Locale.CANADA);
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    locationRepository.deleteAll();
    timelineRepository.deleteAll();

    Set<String> locationNames = new HashSet<>();
    Set<String> eventNames = new HashSet<>();

    int counter = 0;

    while (counter < 10) {
      String locationName = faker.lordOfTheRings().location();
      if (locationNames.add(locationName)) {
        counter++;
      }
    }

    for(String l: locationNames) {
      createAndPersistLocation(l);
    }
  }

  private void createAndPersistLocation(String locationName) {
    LocationNode locationNode = LocationNode.builder()
            .title(locationName)
            .description(faker.elderScrolls().quote())
            .path(faker.file().fileName())
            .active(faker.random().nextBoolean())
            .build();

    locationRepository.save(locationNode);
//    List<Player> createdPlayers = timelineRepository.saveAll(players);
//
//    Address address = new Address(
//      faker.address().city(),
//      faker.address().zipCode(),
//      faker.address().streetAddress()
//    );
//
//    Team team = new Team()
//        .setName(teamName)
//        .setAcronym(teamName.replaceAll(" ", "").toUpperCase().substring(0, 4))
//        .setAddress(address)
//        .setPlayers(new HashSet<>(createdPlayers));

//    locationRepository.save(team);
  }

  private EventNode createEvent() {
    return EventNode.builder()
            .title(faker.elderScrolls().region() + " important event.")
            .description(faker.elderScrolls().quote())
            .active(faker.random().nextBoolean())
            .build();
  }
}
