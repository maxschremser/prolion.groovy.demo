package com.prolion.demo.api

import com.prolion.demo.service.FileWalkerService
import org.springframework.stereotype.Component

import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Component
@Consumes([MediaType.APPLICATION_JSON, MediaType.TEXT_HTML])
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
class FileResource {
    private final FileWalkerService service

    FileResource(FileWalkerService service) {
        this.service = service
    }

    @GET
    @Path("/folders")
    def getFolders() {
        return service.getFolders()
    }

    @GET
    @Path("/filesizes")
    def getFilesizes(@QueryParam("filetype") String filetype) {
        return service.getFilesizes(filetype)
    }

    @GET
    @Path("/purge")
    def deleteAll() {
        service.deleteAll()
        return "OK"
    }
}
