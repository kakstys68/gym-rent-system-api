package com.rentsystemspring.controllers;

import com.google.gson.Gson;
import com.rentsystemspring.errors.CommentNotFound;
import com.rentsystemspring.model.Comment;
import com.rentsystemspring.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @GetMapping(value = "/allComments")
    public @ResponseBody Iterable<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    @GetMapping(value = "/commentById/{id}")
    public EntityModel<Comment> getCommentById(@PathVariable(name = "id") int id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFound(id));
        return EntityModel.of(comment, linkTo(methodOn(CommentController.class).getCommentById(id)).withSelfRel(),
                linkTo(methodOn(CommentController.class).getAllComments()).withRel("AllComments"));
    }
    @PostMapping("/addComment")
    Comment newComment(@RequestBody Comment newComment) {
        return commentRepository.save(newComment);
    }
    @PutMapping(value = "/updateComment/{id}")
    public @ResponseBody String updateComment(@RequestBody String commentInfoToUpdate, @PathVariable int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(commentInfoToUpdate, Properties.class);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFound(id));
        comment.setTitle(properties.getProperty("title"));
        comment.setCommentText(properties.getProperty("commentText"));
        commentRepository.save(comment);
        return "Comment updated";
    }


    @DeleteMapping(value = "/deleteComment/{id}")
    public @ResponseBody String deleteComment(@PathVariable(name = "id") int id){
        commentRepository.deleteById(id);
        return "Comment Successfully deleted";
    }
}
