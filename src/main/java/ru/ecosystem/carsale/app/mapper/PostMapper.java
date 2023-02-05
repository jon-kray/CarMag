package ru.ecosystem.carsale.app.mapper;

import ru.ecosystem.carsale.app.config.Mapper;
import ru.ecosystem.carsale.app.dto.AddPostDto;
import ru.ecosystem.carsale.app.dto.PostInfoDto;
import ru.ecosystem.carsale.app.dto.enums.SellStatus;
import ru.ecosystem.carsale.app.model.Car;
import ru.ecosystem.carsale.app.model.Post;

import java.time.LocalDateTime;

@Mapper
public class PostMapper {

    public Post addPostDtoToEntityPost(AddPostDto postDto) {
        var post = new Post();
        var car = new Car();
        car.setMark(postDto.getMark());
        car.setModel(postDto.getModel());
        car.setTransmissionType(postDto.getTransmissionType());
        post.setCreated(LocalDateTime.now());
        post.setSellStatus(SellStatus.ON_SALE);
        post.setPhoto(postDto.getPhoto());
        post.setPrice(postDto.getPrice());
        post.setDescription(postDto.getDescription());
        post.setCar(car);
        return post;
    }

    public PostInfoDto postEntityToPostInfoDto(Post post) {
        return PostInfoDto.builder()
                .transmissionType(post.getCar().getTransmissionType())
                .sellStatus(post.getSellStatus())
                .model(post.getCar().getModel())
                .price(post.getPrice())
                .mark(post.getCar().getMark())
                .created(post.getCreated())
                .id(post.getId())
                .build();
    }
}
