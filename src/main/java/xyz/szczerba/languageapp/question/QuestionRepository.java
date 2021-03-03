package xyz.szczerba.languageapp.question;

import org.springframework.data.jpa.repository.JpaRepository;

interface QuestionRepository extends JpaRepository<Question,Long> {
}
