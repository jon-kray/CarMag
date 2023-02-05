package ru.ecosystem.carsale.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.ecosystem.carsale.app.model.Event;
import ru.ecosystem.carsale.app.model.Post;
import ru.ecosystem.carsale.app.repository.interfases.EventRepository;
import ru.ecosystem.carsale.app.repository.interfases.PostRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventService {

    private final PostRepository postRepository;
    private final EventRepository eventRepository;

    @Scheduled(cron = "${job.event.cron}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void clearSellAutoAndWriteToEventStore() {
        var posts = postRepository.getSalesCars();
        eventRepository.saveAll(getListEvents(posts));
        postRepository.deletePosts(posts);
        log.info("Выполнена задача по очистке проданных автомобилей. Все продажи добавлены в историю.");
    }

    private List<Event> getListEvents(List<Post> posts) {
        return posts.stream()
                .map(post -> Event.builder()
                        .sellDate(LocalDate.now())
                        .mark(post.getCar().getMark())
                        .model(post.getCar().getModel())
                        .price(post.getPrice())
                        .transmissionType(post.getCar().getTransmissionType().getNamed())
                        .photo(post.getPhoto())
                        .username(post.getUser().getUsername())
                        .build())
                .collect(Collectors.toList());
    }
}
