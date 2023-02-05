package ru.ecosystem.carsale.app.repository.interfases;

import org.springframework.stereotype.Repository;
import ru.ecosystem.carsale.app.dto.enums.SellStatus;
import ru.ecosystem.carsale.app.model.Post;

import java.util.List;

public interface PostRepository  {

    public List<Post> getPostsByUser(long userId);

    public List<Post> getAllPosts();

    public void savePost(Post post);

    public void updateStatus(long postId, SellStatus sellStatus);

    public Post getPostById(long postId);

    public List<Post> getSalesCars();

    public List<Post> getPostsByMarkCar(String mark);

    public void deletePosts(Iterable<Post> posts);








}
