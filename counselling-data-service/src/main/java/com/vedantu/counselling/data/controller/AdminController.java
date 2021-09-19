package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.exception.AuthenticationException;
import com.vedantu.counselling.data.exception.InvalidInputException;
import com.vedantu.counselling.data.model.SummaryData;
import com.vedantu.counselling.data.model.Video;
import com.vedantu.counselling.data.response.Response;
import com.vedantu.counselling.data.response.ResponseStatus;
import com.vedantu.counselling.data.service.AuthService;
import com.vedantu.counselling.data.service.DownloadService;
import com.vedantu.counselling.data.service.SummaryDataService;
import com.vedantu.counselling.data.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    private final AuthService authService;
    private final DownloadService downloadService;
    private final VideoService videoService;
    private final SummaryDataService summaryService;
    private final CacheManager cacheManager;

    @Autowired
    public AdminController(AuthService authService, DownloadService downloadService,
                           VideoService videoService, SummaryDataService summaryService, CacheManager cacheManager) {
        this.authService = authService;
        this.downloadService = downloadService;
        this.videoService = videoService;
        this.summaryService = summaryService;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/files/upload")
    public Response<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("description") String description,
            @RequestParam("key") String password
    ) throws InvalidInputException, AuthenticationException {
        authService.authenticate(password);
        downloadService.uploadFile(description, file);
        return new Response<>(ResponseStatus.SUCCESS, "File uploaded successfully!");
    }

    @PostMapping("/videos")
    public Response<Video> uploadVideo(@RequestParam String name, @RequestParam String uri,
                                       @RequestParam("key") String password
    ) throws InvalidInputException, AuthenticationException {
        if(ObjectUtils.isEmpty(uri))
            throw new InvalidInputException("Name or URI cannot be null/empty");
        if(ObjectUtils.isEmpty(name))
            throw new InvalidInputException("Name or URI cannot be null/empty");

        authService.authenticate(password);
        Video videoDb = videoService.createNewVideo(name, uri);
        return new Response<>(ResponseStatus.SUCCESS, videoDb);
    }

    @GetMapping("/videos")
    public Response<List<Video>> getVideos(@RequestParam("key") String password) throws AuthenticationException {
        authService.authenticate(password);
        List<Video> videoDb = videoService.getAllVideos();
        return new Response<>(ResponseStatus.SUCCESS, videoDb);
    }

    @PostMapping("/videos/{id}/Status/{active}")
    public Response<Video> activateVideo(@PathVariable int id, @PathVariable boolean active,
                                         @RequestParam("key") String password
    ) throws InvalidInputException, AuthenticationException {
        authService.authenticate(password);
        Video videoDb = videoService.setActive(id, active);
        return new Response<>(ResponseStatus.SUCCESS, videoDb);
    }

    @PostMapping("/summary")
    public Response<SummaryData> uploadSummary(String description, String disclaimer, @RequestParam("key") String password
    ) throws InvalidInputException, AuthenticationException {
        if(ObjectUtils.isEmpty(description))
            throw new InvalidInputException("Name or URI cannot be null/empty");

        authService.authenticate(password);
        SummaryData videoDb = summaryService.createNewSummary(description, disclaimer);
        return new Response<>(ResponseStatus.SUCCESS, videoDb);
    }

    @PostMapping("/invalidate")
    public Response<String> cacheInvalidate(@RequestParam("key") String password)
            throws InvalidInputException, AuthenticationException {
        authService.authenticate(password);
        cacheManager.getCacheNames()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        return new Response<>(ResponseStatus.SUCCESS, "All caches evicted");
    }

}
