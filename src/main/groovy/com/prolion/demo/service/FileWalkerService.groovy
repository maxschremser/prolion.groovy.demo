package com.prolion.demo.service


import com.prolion.demo.config.FileWalkerConfigurationProperties
import com.prolion.demo.dao.FileWalkerRepository
import com.prolion.demo.model.FileWalker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

import java.nio.file.FileVisitOption
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.sql.Date

@Service
@EnableScheduling
class FileWalkerService {
    private final Logger log = LoggerFactory.getLogger(FileWalkerService)

    private final FileWalkerRepository repository
    private final FileWalkerConfigurationProperties configuration
    private final Path path

    FileWalkerService(FileWalkerConfigurationProperties configuration, FileWalkerRepository repository) {
        this.configuration = configuration
        this.repository = repository
        this.path = Paths.get(this.configuration.dir)
        if (!this.path.toFile().exists()) {
            throw new FileNotFoundException("prolion.service.dir ($configuration.dir) does not exist.");
        }
    }

    @Scheduled(fixedRateString = "#{@fileWalkerConfigurationProperties.interval.toMillis()}")
    private void scanDirectory() throws IOException {
        log.info("Scanning Directory: {}", configuration.dir)
        repository.deleteAll()
        def scanned = new Date(new java.util.Date().getTime())
        Files.walk(path, configuration.maxDepth, FileVisitOption.FOLLOW_LINKS).forEach({ entry ->
            repository.save(new FileWalker(entry, scanned as Date))
        })
    }

    def getFolders() {
        List<FileWalker> folders = new ArrayList<>()
        repository.findAll().each {folders << it }
        return folders
    }

    def getFilesizes(String filetype) {
        def filesizes = [:]
        repository.findAll().each {fw ->
            fw.path.split('/').each {p ->
                if (filetype) {
                    if (fw.directory || fw.type != filetype) {
                        return
                    }
                }
                if (filesizes[p]) {
                    filesizes[p] += fw.size
                } else if (p == fw.name) {
                    return
                } else if (!fw.directory) {
                    filesizes << ["$p" : fw.size]
                }
            }
        }
        return filesizes
    }

    def deleteAll() {
        repository.deleteAll()
    }
}
