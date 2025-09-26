// Path of the current file
package com.gaming.repository;

// JPA need to work with games table or Game Model
import com.gaming.model.Game;
// To make use of JPA, we need to import the dependency or header file
// JPA Repository helps use to minimize the usage of MySQL queries and make use of ORM (Object Relational Mapping) and its methods, Ex: save(), findAll(), findById()
import org.springframework.data.jpa.repository.JpaRepository;

// GameRepository Class is inheriting the properties or methods of JpaRepository
// JpaRepository -> GameRepository
// Reference which need to be given: Model - Game & Long - Primary Key (id)
public interface GameRepository extends JpaRepository<Game, Long> {
}