package com.example.restblog.web;
import com.example.restblog.data.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.restblog.services.EmailServices;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")

public class PostsController {
    private final PostsRepository postsRepository;
    private final UserRepository usersRepository;
    private final CategoriesRepository categoriesRepository;
    private final EmailServices emailServices;

    public PostsController(PostsRepository postsRepository, UserRepository userRepository, CategoriesRepository categoriesRepository, EmailServices emailServices) {
        this.postsRepository = postsRepository;
        this.usersRepository = userRepository;
        this.categoriesRepository = categoriesRepository;
        this.emailServices = emailServices;
    }

    @GetMapping
    public List<Post> getAll() {
    return postsRepository.findAll();
    }

   // GET /api/posts/5  <-- fetch the blog with id 5
    @GetMapping("{postId}")
    @PreAuthorize("!hasAuthority('USER') || !hasAuthority('ADMIN')")
    public Post getById(@PathVariable Long postId){
        return postsRepository.getById(postId);
    }

    @PostMapping
    @PreAuthorize("!hasAuthority('USER') || !hasAuthority('ADMIN')")
    public void createPost(@RequestBody Post newPost, OAuth2Authentication auth) {
        String email = auth.getName();
        User author = usersRepository.findByEmail(email);
        newPost.setAuthor(author);
        List<Category> categories = new ArrayList<>();
        categories.add(categoriesRepository.findCategoryByName("food"));
        categories.add(categoriesRepository.findCategoryByName("music"));
        newPost.setCategories(categories);
        postsRepository.save(newPost);
        emailServices.prepareAndSend(newPost,"wes","Work!");
        System.out.println("new Post Created");
    }

    @PutMapping()
    @PreAuthorize("!hasAuthority('USER') || !hasAuthority('ADMIN')")
    public void createUpdate( @RequestBody Post newPost) {
        System.out.println("Ready to update post: " + newPost);
        Post postToUpdate = postsRepository.getById(newPost.getId());
        postToUpdate.setContent(newPost.getContent());
        postToUpdate.setTitle(newPost.getTitle());
//        BeanUtils.copyProperties(newPost,postToUpdate);
//        getNullPropertyNames(newPost);
        postsRepository.save(postToUpdate);
    }

    @DeleteMapping("{postId}")
    public void createDelete(@PathVariable Long postId) {
        postsRepository.deleteById(postId);
        System.out.println("Ready to delete post: " + postId );
    }

//    private static String[] getNullPropertyNames (Object source) {
//        final BeanWrapper src = new BeanWrapperImpl(source);
//        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
//
//        Set<String> emptyNames = new HashSet<String>();
//        for(java.beans.PropertyDescriptor pd : pds) {
//            Object srcValue = src.getPropertyValue(pd.getName());
//            if (srcValue == null) emptyNames.add(pd.getName());
//        }
//
//        String[] result = new String[emptyNames.size()];
//        return emptyNames.toArray(result);
//    }

}
