package com.prolion.demo.dao

import com.prolion.demo.model.FileWalker
import org.springframework.data.repository.CrudRepository

interface FileWalkerRepository extends CrudRepository<FileWalker, Long> {

}