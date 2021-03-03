package xyz.szczerba.languageapp.topic;

import org.springframework.data.jpa.repository.JpaRepository;

interface TopicRepository extends JpaRepository<Topic,Long> {
}
