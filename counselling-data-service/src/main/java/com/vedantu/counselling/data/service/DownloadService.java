package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.model.CounsellingDataFile;
import com.vedantu.counselling.data.repository.CounsellingDataFileRepository;
import com.vedantu.counselling.data.view.DownloadedFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return new DownloadedFile(counsellingDataFile.getName(), counsellingDataFile.getContent(), counsellingDataFile.getType());
//        try (
//                InputStream inputStream = counsellingDataFile.getContent();
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
//        ) {
//            final int bufLen = 4 * 0x400; // 4KB
//            byte[] buf = new byte[bufLen];
//            int readLen;
//            while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
//                outputStream.write(buf, 0, readLen);
//
//
//        } catch (SQLException | IOException e) {
//            String error = "Error occurred while reading the download file!!";
//            log.error(error, e);
//            throw new RuntimeException(error);
//        }
    }
}
