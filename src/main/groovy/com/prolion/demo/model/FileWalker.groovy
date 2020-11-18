package com.prolion.demo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import java.nio.file.Path
import java.sql.Date

@Entity
@Table(name = "file_walker")
class FileWalker {
    @Id
    @GeneratedValue
    private Long id
    private String name
    private String path
    private String type
    private Long size
    private boolean directory
    private Date modified
    private Date scanned

    FileWalker() {}

    FileWalker(Path path, Date scanned) {
        File file = path.toFile()
        String filename = file.getName()
        this.name = path.fileName.toString()
        this.path = path.toString()
        this.directory = path.toFile().isDirectory()
        def index = filename.lastIndexOf('.')
        if (!directory && index >= 0) {
            this.type = filename.substring(filename.lastIndexOf(".") + 1)
        }
        this.size = file.length()
        this.modified = new Date(path.toFile().lastModified())
        this.scanned = scanned
    }

    Long getId() {
        return id
    }

    String getName() {
        return name
    }

    String getPath() {
        return path
    }

    boolean getDirectory() {
        return directory
    }

    Date getModified() {
        return modified
    }

    Date getScanned() {
        return scanned
    }

    String getType() {
        return type
    }

    Long getSize() {
        return size
    }
}
