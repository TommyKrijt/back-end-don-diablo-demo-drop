package nl.novi.krijt.controller;

import nl.novi.krijt.domain.FeedbackText;
import nl.novi.krijt.service.FeedbackTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/feedback")
public class FeedbackTextController {

    @Autowired
    FeedbackTextService feedbackTextService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllFeedbackTexts() {
        List<FeedbackText> feedbackTexts = feedbackTextService.getAllFeedbackTexts();
        return new ResponseEntity<>(feedbackTexts, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getFeedbackTextById(@PathVariable("id") long id) {
        FeedbackText feedbackText = feedbackTextService.getFeedbackTextById(id);
        return new ResponseEntity<>(feedbackText, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteFeedbackText(@PathVariable("id") long id) {
        feedbackTextService.deleteFeedbackText(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "")
    public ResponseEntity<Object> saveFeedbackText(@RequestBody FeedbackText feedbackText) {
        long newId = feedbackTextService.saveFeedbackText(feedbackText);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateFeedbackText(@PathVariable("id") long id, @RequestBody FeedbackText feedbackText) {
        feedbackTextService.updateFeedbackText(id, feedbackText);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
