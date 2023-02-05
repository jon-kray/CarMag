package ru.ecosystem.carsale.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import ru.ecosystem.carsale.app.dto.AddPostDto;
import ru.ecosystem.carsale.app.dto.enums.SellStatus;
import ru.ecosystem.carsale.app.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ModelAndView getAllPosts(@RequestParam(value = "mark", required = false) String mark) {
        return Objects.isNull(mark)
                ? new ModelAndView("posts", Map.of("posts", postService.getAllPosts()))
                : new ModelAndView("posts", Map.of("posts", postService.getAllPostsByMark(mark)));
    }

    @GetMapping("/user")
    public ModelAndView getUserPosts(HttpServletRequest request) {
        var httpSession = request.getSession();
        var userId = (Long) httpSession.getAttribute("USER_ID");
        var rsl = postService.getPostsByUser(userId);
        return new ModelAndView("user-account", Map.of("posts", rsl));
    }

    @GetMapping("/addPost")
    public ModelAndView addPosts() {
        return new ModelAndView("add-post", Map.of("post", new AddPostDto()));
    }

    @PostMapping("/addPost")
    public void addPostSubmit(@ModelAttribute("post") AddPostDto post,
                              @RequestParam("file") MultipartFile multipartFile,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

        long userId = (Long) request.getSession().getAttribute("USER_ID");
        post.setPhoto(multipartFile.getBytes());
        postService.addPost(post, userId);
        response.sendRedirect(String.format("%s/posts", request.getContextPath()));
    }

    @GetMapping("/info/{postId}")
    public ModelAndView getInfoForPost(@PathVariable("postId") Long id) {
        var infoDto = postService.getPostInfo(id);
        return new ModelAndView("post-info", Map.of("info", infoDto));
    }

    @GetMapping("/update/{postId}/{status}")
    public void updateSellStatus(@PathVariable("postId") Long id,
                                 @PathVariable("status") SellStatus sellStatus,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {

        postService.update(id, reverseStatus(sellStatus));
        response.sendRedirect(String.format("%s/posts/user", request.getContextPath()));
    }

    private SellStatus reverseStatus(SellStatus sellStatus) {
        return sellStatus.ordinal() == 0
                ? SellStatus.values()[1]
                : SellStatus.values()[0];
    }
}
