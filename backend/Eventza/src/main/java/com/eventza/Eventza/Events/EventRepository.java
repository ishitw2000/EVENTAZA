package com.eventza.Eventza.Events;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EventRepository extends CrudRepository<EventModel, UUID> {

  @Query("select e from EventModel e where e.eventName=?1")
  EventModel findByEventName(String eventName);

  List<EventModel> findByCategoryId(UUID categoryId);
  List<EventModel> findByEventLocation(String eventLocation);
  List<EventModel> findByAverageRatingGreaterThanEqual(Double cutoffRating);

  @Transactional
  @Modifying
  @Query("update EventModel event set event.averageRating = ?2, event.totalRating = ?3 where event.id = ?1")
  void setRatingForEventModule(UUID id, Double rating, Integer total_rating);
}
