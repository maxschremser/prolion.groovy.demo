package com.prolion.demo

import com.prolion.demo.dao.FileWalkerRepository
import com.prolion.demo.model.FileWalker
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

import java.nio.file.Paths
import java.sql.Date

import static org.assertj.core.api.Assertions.assertThat

@DataJpaTest
class FileWalkerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager
    @Autowired
    private FileWalkerRepository repository

    @Test
    void shouldPersistAndFindFileWalker() {
        def fw = new FileWalker(Paths.get("."), new Date(2020, 01, 01))
        entityManager.persist(fw)
        entityManager.flush()

        def found = repository.findById(1l)
        println found
        assertThat(fw.id).is(1l)
        assertThat(fw.name).is(".")
    }
}
