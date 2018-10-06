package ru.slloc.voteforalunch.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.slloc.voteforalunch.model.Vote;
import ru.slloc.voteforalunch.service.VoteService;
import ru.slloc.voteforalunch.web.SecurityUtil;

import java.net.URI;
import java.util.List;

import static ru.slloc.voteforalunch.util.ValidationUtil.assureIdConsistent;
import static ru.slloc.voteforalunch.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(ProfileRestVoteController.REST_URL)
public class ProfileRestVoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/profile/votes";

    @Autowired
    private VoteService voteService;

    @GetMapping
    public List<Vote> getAll(){
        int userId = SecurityUtil.authUserId();
        log.info("getAll votes for user {}", userId);
        return voteService.getAll(userId);
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable("id") int id) {

        int userId = SecurityUtil.authUserId();
        log.info("get vote {} for user {}", id, userId);
        return voteService.get(id, userId);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Vote vote, @PathVariable("id") int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(vote, id);
        log.info("update {} for user {}", vote, userId);
        voteService.update(vote, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote) {
        int userId = SecurityUtil.authUserId();
        checkNew(vote);
        log.info("create {} for user {}", vote, userId);
        Vote created = voteService.create(vote, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {

        int userId = SecurityUtil.authUserId();
        log.info("delete vote {} for user {}", id, userId);
        voteService.delete(id, userId);
    }
}
