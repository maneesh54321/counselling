package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.exception.InvalidInputException;
import com.vedantu.counselling.data.model.CounsellingDataFile;
import com.vedantu.counselling.data.repository.CounsellingDataFileRepository;
import com.vedantu.counselling.data.view.DownloadedFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class DownloadService {

    private final CounsellingDataFileRepository fileRepository;

    @Autowired
    public DownloadService(CounsellingDataFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public DownloadedFile downloadFile(int fileId) {
        CounsellingDataFile counsellingDataFile = this.fileRepository.findById(fileId);
        return new DownloadedFile(
                counsellingDataFile.getName(),
                counsellingDataFile.getContent()
        );
    }

    public void uploadFile(String fileDescription, MultipartFile fileToUpload) throws InvalidInputException {
        try {
            String fileName = fileToUpload.getOriginalFilename();
            if(!StringUtils.hasText(fileName)){
                throw new InvalidInputException("File name can not be empty");
            }
            CounsellingDataFile counsellingDataFile = new CounsellingDataFile(
                    fileName, fileDescription, fileToUpload.getBytes()
            );
            fileRepository.save(counsellingDataFile);
        } catch (IOException e) {
            throw new InvalidInputException("The file is invalid.");
        }
    }
}
